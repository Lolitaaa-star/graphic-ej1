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
    
    /**
     * Method to clean all the panel
     */
    void clear();
    
    /**
     * Method to put a color in the point, checking if the point is in the raster
     * @param x coordinate x
     * @param y coordinate y
     * @param color for the paint
     */
    void setPixel(int x, int y, int color);
    
    /**
     * Method to get the image of the raster
     * @return the image
     */
    BufferedImage getImage();
    
    /**
     * Returns a color of specific pixel
     * @param x colum coordinate
     * @param y row coordinate
     * @return color in integer
     */
    int getPixel(int x, int y);
    
    /**
     * Method to get the color that is for painting the pixel
     * @return the color
     */
    int getColor();
    
    /**
     * Method to get the width of the panel
     * @return width
     */
    int getWidth();
    
    /**
     * Method to get the height of the panel
     * @return height
     */
    int getHeight();
    
    /**
     * Method to change the color for cleaning the raster
     * @param color 
     */
    void setClearColor(int color);
    
    /**
     * Method to get the clear color of the raster
     * @return clearColor
     */
    int getClearColor();
}
