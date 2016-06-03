/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import control.Teclado;
import graficos.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mapas.Mapa;
import mapas.MapaGenerado;

public class Juego extends Canvas implements Runnable{
    
    //Opcional agregar serialVersionUID
    
    private static JFrame ventana;
    private static final int ANCHO  =1200;
    private static final int ALTO   =700;
    private static volatile boolean enFuncionamiento = false;//volatile=no puede usarse de manera simultanea en 2 thread 
    private static Thread thread; //segundo thread en ejecucion
    
    private static final  String NOMBRE= "Juego"; //titulo ventana
    private static int aps=0;
    private static int fps=0;
    private static Teclado teclado;
    
    private static int x = 0;
    private static int y = 0;
    private static Pantalla pantalla;
    
    private static Mapa mapa;
    
    private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icono_02.png"));
    
    private static BufferedImage imagen= new BufferedImage(ANCHO,ALTO, BufferedImage.TYPE_INT_RGB );
    private static int [] pixeles =((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
    //Clase de ventana del juego
   
    
    private  Juego(){
        mapa=new MapaGenerado(128,128);//Numero de cuadros o titles
        setPreferredSize(new Dimension(ANCHO,ALTO));
        pantalla = new Pantalla(ANCHO,ALTO);//iniciar pantalla   
        teclado =new Teclado();
        addKeyListener(teclado);
        
    
    ventana=new JFrame(NOMBRE);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Especifica la funcion al presionar el boton cerrar
    ventana.setResizable(false); //No se puede redimensionar
    ventana.setLayout(new BorderLayout());
    ventana.add(this, BorderLayout.CENTER ); 
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    ventana.setIconImage(icono.getImage());
    }
    
    public static void main(String[]args){ //Metodo principal
    Juego juego=new Juego();
    juego.iniciar();
    }
    private synchronized void iniciar(){ 
    
        enFuncionamiento=true;
    
    thread=new Thread(this,"Graficos");
    thread.start();
   
    
    }
    private synchronized void detener(){ //syncronized hace que no puedan manipularse variables mientras este en funcionamiento
    
        enFuncionamiento=false;
        try {
            thread.join();//join espera que los threat se cierren para cerrar la aplicacion
        } catch (InterruptedException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizar(){
        aps++;
        teclado.actualizar();
        if(teclado.arriba){
           y--; 
        }
        if(teclado.abajo){
            y++;
        }
        if(teclado.derecha){
            x++ ;
        }
        if(teclado.izquierda){
           x--;
        }
    } 
    
    private void mostrar(){
        BufferStrategy estrategia=getBufferStrategy();
        if(estrategia==null){
            createBufferStrategy(3);
            return;
         }
        pantalla.limpiar();
        mapa.mostrar(x, y , pantalla);
     //   pantalla.mostrar(x, y);
        //mas eficiente, menos costoso 
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        Graphics g= estrategia.getDrawGraphics();
        g.drawImage(imagen,0,0,getWidth(),getHeight(),null);
        g.setColor(Color.yellow);
        g.fillRect(ANCHO/2,ALTO/2,32,32);
        g.dispose();
        estrategia.show();
        /*for(int i=0;i<pixeles.length;i++){  INEFICIENTE 
            pixeles[i]=pantalla.pixeles[i];
        }*/
        fps++;
    }
    @Override
    public void run() {
        //System.nanoTime();
        final int NS_POR_SEGUNDO =  1000000000;
        final byte APS_OBJETIVO=60;
        final double NS_POR_ACTUALIZACION= NS_POR_SEGUNDO/APS_OBJETIVO;
        
        long referencialActualizacion=System.nanoTime();
        long referenciaContador=System.nanoTime();
        
        double tiempoTranscurrido;
        double delta=0;
        
        requestFocus();
        
        while(enFuncionamiento){
            
            final long inicioBucle=System.nanoTime();
            tiempoTranscurrido=inicioBucle-referencialActualizacion;
            referencialActualizacion=inicioBucle;
            delta +=tiempoTranscurrido/NS_POR_ACTUALIZACION;
            
            while(delta>=1){
             
                actualizar();
                delta--;
            }
            mostrar(); 
            
            if(System.nanoTime()- referenciaContador > NS_POR_SEGUNDO){
            
                ventana.setTitle(NOMBRE+"  APS:"+aps+"  FPS:"+fps);
                aps=0;
                fps=0;
                referenciaContador=System.nanoTime();
            }
        }
    }
   
} 
