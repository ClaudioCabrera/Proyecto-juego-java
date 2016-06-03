/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class HojaSprite {
    
    private final int ancho;
    private final int alto;
    public final int [] pixeles;
    
    //coleccion de hojas de sprite
    public static HojaSprite suelo = new HojaSprite("/texturas/sprite_02.png",320,320);
    
    //fin de la coleccion
    
    public HojaSprite(final String ruta, final int ancho, final int alto){
    
        this.ancho=ancho;
        this.alto=alto;
        
        pixeles=new int[ancho*alto];
        
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprite.class.getResource(ruta));
            imagen.getRGB(0,0,ancho,alto,pixeles,0,ancho);
        } catch (IOException ex) {
            Logger.getLogger(HojaSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    
    public int ObtenAncho(){
        return ancho;
            }
}
