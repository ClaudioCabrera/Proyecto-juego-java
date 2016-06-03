/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficos;

import mapas.cuadro.Cuadro;

public class Pantalla {

    private final int ancho;
    private final int alto;
    private int diferenciaX;
    private int diferenciaY;
    
    public final int[]pixeles;
    
    //temporal
 //   private final static int LADO_SPRITE=32;
 //   private static int MASCARA_SPRITE=LADO_SPRITE-1;
    //fin temporal
    
    public Pantalla(final int ancho, final int alto){
        this.ancho=ancho;
        this.alto=alto;
        pixeles=new int[ancho*alto];
    }
    
    public void limpiar(){
         for(int i=0;i<pixeles.length;i++){
             pixeles[i]=0;//0 muestra negro en pantalla
         }
    }
    //TEMPORAL
   /* public void mostrar(final int compensacionX, final int compensacionY){//movimiento con el teclado
         for(int y=0;y<alto;y++){
             int posicionY=y+compensacionY;
             if(posicionY<0 || posicionY>=alto){//acceder a posicion de array que no existe
                 continue;
             }
             for(int x=0;x<ancho;x++){
                 int posicionX=x+compensacionX;
                 if(posicionX<0 || posicionX>=ancho){
                     continue;
                 }
                //temporal 
                 pixeles[posicionX+posicionY*ancho]=Sprite.SUELO.pixeles
                         [(x & MASCARA_SPRITE )+(y & MASCARA_SPRITE)*LADO_SPRITE];
             }
         }
        
    }//fin temporal
    */
    
    public void mostrarCuadro(int compensacionX, int compensacionY,Cuadro cuadro){
        compensacionX-=diferenciaX;
        compensacionY-=diferenciaY;
        
        for(int y = 0; y < cuadro.sprite.obtenLado();y++){
            int posicionY = y + compensacionY;
            for(int x =0;x<cuadro.sprite.obtenLado();x++){
                int posicionX = x +compensacionX;
                
                if(posicionX < -cuadro.sprite.obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto ){
                    break;//Validacion para no dibujar fuera de pantalla                                                                              
                }
                if(posicionX<0){
                    posicionX=0;
                }
                pixeles[posicionX+posicionY*ancho]=cuadro.sprite.pixeles[x+y*cuadro.sprite.obtenLado()];
            }
        }
    }
    public void estableceDiferencia(final int diferenciaX,final int diferenciaY){
    
        this.diferenciaX=diferenciaX;
        this.diferenciaY=diferenciaY;
        
    }
    
    public int obtenAncho(){
    return ancho;
    }
    public int obtenAlto(){
    return alto;
    }
}
