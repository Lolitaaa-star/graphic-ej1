/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.raster;

import java.awt.image.BufferedImage;

/**
 *
 * @author lgome
 */
public interface Raster {
    
    void clear();
    
    void setPixel(int x, int y, int color);
    
    BufferedImage getImage();
    
    /**
     * 
     * Returns a color of specific pixel
     * @param x colum coordinate
     * @param y row coordinate
     * @return color in integer
     */
    
    int getPixel(int x, int y);
    
    int getColor();
    
    int getWidth();
    
    int getHeight();
    
    void setClearColor(int color);//poner color al fondo de pantalla
    
    
}
