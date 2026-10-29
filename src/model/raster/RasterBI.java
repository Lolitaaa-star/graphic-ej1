/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;
import java.awt.image.BufferedImage;
/**
 *
 * @author lgome
 */
//la cuadricula binaria implementa la interfaz 
public class RasterBI implements Raster {
    
    private final BufferedImage image;
    private int clearColor; //el color del fondo q se crea
    
    //poner el color q quieres y crea la imagen 
    public RasterBI(int w, int h){
        image  = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        clearColor = 0x1616133; //einengrau
    }

    //por cada pixel de la pantalla pone el color q tenemos de base
    @Override
    public void clear() {
        for(int x=0; x < image.getWidth(); x++){ //w=800 va 0-799
            for(int y=0; y< image.getHeight(); y++){
                image.setRGB(x, y, clearColor); //en cada pixel pone el color q tenemos para borrar
            }
        }
    }

    
    @Override
    public BufferedImage getImage(){
        return image;
    }
    
    
    @Override
    public void setPixel(int x, int y, int color) {
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight())
            image.setRGB(x, y, color);
    }

    @Override
    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

    @Override
    public int getColor() {
        return clearColor;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void setClearColor(int color) {
        clearColor = color;
    }
    
    public int getClearColor(){
        return clearColor;
    }
}
