/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficos;

public final class Sprite {
    
    private final int lado;//MARCA TAMAÑO DEL CUADRO DEL SPRITE
    
    private int x;
    private int y;
    
    public int [] pixeles;
    private HojaSprite hoja;
    
    //coleccion de sprite
    public static final Sprite VACIO = new Sprite(32,0x000000);
    public static final Sprite SUELO = new Sprite(32,0,0,HojaSprite.suelo);
    
    //fin de la la coleccion
    
    public Sprite(final int lado,final int columna,final int fila,final HojaSprite hoja){
    
        this.lado=lado;
        this.hoja=hoja;
        pixeles=new int[lado * lado];
        this.x=columna * lado;
    
        for(int y=0; y<lado ;y++){
        
            for(int x=0; x<lado; x++){
        
                pixeles[x+y * lado] = hoja.pixeles[(x + this.x) + (y+this.y)*hoja.ObtenAncho()]; 
            } 
        }
    }
    
    public Sprite(final int lado, final int color){
      
        this.lado=lado;
        pixeles=new int[lado*lado];
        for(int i=0;i<pixeles.length;i++){
            pixeles[i]=color;
        }
    }

    public int obtenLado(){
    return lado;
    }
}
