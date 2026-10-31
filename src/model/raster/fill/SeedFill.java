/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster.fill;

import model.raster.RasterBI;

/**
 *
 * @author lgome
 */
public class SeedFill {

    /**
     * Method for filling the area that is selected if the condition that the 
     * clear color of the panel and the one for the pixel are the same, that means
     * that nothing has been fill in that pixel
     * @param img is the panel where we are working
     * @param x coordinate x
     * @param y coordinate y
     * @param fillColor color for filling the polygon
     * 
    */
    public void fill(RasterBI img, int x, int y, int fillColor) {

        int seed = img.getPixel(x, y);

        if( (0xFFFFFF & img.getClearColor()) == (seed & 0xFFFFFF)) {

            img.setPixel(x, y, fillColor);

            fill(img, x+1, y, fillColor);
            fill(img, x-1, y, fillColor);
            fill(img, x, y+1, fillColor);
            fill(img, x, y-1, fillColor);
        }
    }
}
