/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapas.cuadro;

import graficos.Pantalla;
import graficos.Sprite;


public abstract class Cuadro {

    public int x;
    public int y;
    public Sprite sprite;
    
    public static final int LADO=32;
    
    //Coleccion cuadro
    public static final Cuadro VACIO= new CuadroVacio(Sprite.VACIO);
    public static final Cuadro PISO = new CuadroPiso(Sprite.SUELO);
    //Fin coleccion cuadros
    
    public Cuadro(Sprite sprite){
    
        this.sprite=sprite;
        
    }
    
    public void mostrar(int x, int y,Pantalla pantalla){
    pantalla.mostrarCuadro(x * 32, y * 32, this);
    }
    
    public boolean solido(){
    return false;
    }
}

    

