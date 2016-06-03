/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapas;

import graficos.Pantalla;
import mapas.cuadro.Cuadro;

public abstract class Mapa {

    protected int ancho;
    protected int alto;
    protected int[] cuadros;
    
    public Mapa(int ancho,int alto){
    this.ancho=ancho;
    this.alto=alto;
    cuadros=new int[ancho*alto];
    generarMapa();
    
    }
    public Mapa(String ruta){
     cargarMapa(ruta);
    }
    protected void generarMapa(){
    
    }
    
    private void cargarMapa(String ruta){
    }
    
    public void actualizar(){
    }
    
    public void mostrar(final int compensacionX,final int compensacionY, Pantalla pantalla){
        pantalla.estableceDiferencia(compensacionX, compensacionY);
        int oeste=compensacionX / 32;
        int este =(compensacionX + pantalla.obtenAncho()+Cuadro.LADO) / 32;
        int norte=compensacionY / 32;
        int sur  =(compensacionY + pantalla.obtenAlto()+Cuadro.LADO)  / 32;
        
        for(int y=norte; y < sur; y++){
            for(int x=oeste; x < este; x++){
                obtenCuadro(x,y).mostrar(x,y,pantalla);
            }
        
        }
        
    }
    
    public Cuadro obtenCuadro(final int x,final int y){
        if(x<0 || y<0 || x>= ancho || y>=alto){
            return Cuadro.VACIO;
        
        }
        switch(cuadros[x + y * ancho]){
        
            case 0:
                return Cuadro.PISO;//return hace misma funcion que break;
            case 1:
            case 2:   
            default:
                return Cuadro.VACIO;
        }
        
    
    }
}
