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
