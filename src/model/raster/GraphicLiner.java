/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lgome
 */
public class GraphicLiner extends Liner{
    
    /**
     * Constructor that calls the constructor of the father class
     * @param raster the raster 
     */
    public GraphicLiner(Raster raster){
        super(raster);
    }
    
    @Override
    protected void rasterized(int x1, int y1, int x2, int y2) {
        Graphics g = ((RasterBI)raster).getImage().getGraphics();
        g.setColor(new Color(color));
        g.drawLine(x1, y1, x2, y2);
    }
}
