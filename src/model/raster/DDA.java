/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;


/**
 *
 * @author lgome
 */
public class DDA extends Liner{ 
    
    public DDA(Raster raster){
        super(raster);
    }

    /**
     * Method to draw the line with the DDA method, that calculates the 
     * aument of pixels must be painted, and it increments the coordinates
     * @param x1 coordinate x for first point
     * @param y1 coordinate y for first point
     * @param x2 coordinate x for second point
     * @param y2 coordinate y for second point
     */
    @Override
    protected void rasterized(int x1, int y1, int x2, int y2) {
        // k -slope
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        //calculates the increase of each coordinate and the bigger one are is the amount of pixels to draw
        int pixeles;
        if(Math.abs(dx) > Math.abs(dy))
            pixeles = Math.abs(dx);
        else
            pixeles = Math.abs(dy);
        
        //increase of each coordinate
        double incX = (double) dx/pixeles;
        double incY = (double) dy/pixeles;
        
        //the strat point
        double x = x1;
        double y = y1;
        
        raster.setPixel((int)x, (int)y, color); //first pixel
        for ( int i=0; i <= pixeles && x < raster.getWidth() && y < raster.getHeight(); i++){
            x += incX;
            y += incY;
            raster.setPixel((int)x, (int)y, color);
        }
    }
    
}
