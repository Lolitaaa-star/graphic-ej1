/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;
import model.objects.*;
/**
 *
 * @author lgome
 */
public abstract class Liner {
    Raster raster;
    int color; 
    
    /**
     * Constructor with the raster were the line is going to be rasterized and 
     * the color of the line
     * @param raster 
     */
    public Liner(Raster raster){
        this.raster = raster;
        color = 0xFFFFFF; // white
    }
    
    /**
     * Method to rasterize the line in the raster with the coordinates 
     * of each point, it can be made with different methods
     * @param x1 coordinate x of the first point
     * @param y1 coordinate y of the first point
     * @param x2 coordinate x of the second point
     * @param y2 coordinate y of the second point
     */
    protected abstract void rasterized(int x1, int y1, int x2, int y2);
    
    /**
     * Method to draw the line with each coordinate of both points
     * @param x1 coordinate x of the first point
     * @param y1 coordinate y of the first point
     * @param x2 coordinate x of the second point
     * @param y2 coordinate y of the second point
     */
    public void drawLine(int x1, int y1, int x2, int y2){
        rasterized(x1, y1, x2, y2);
    } 
    
    /**
     * Method to draw the line only with the points, form them is taken
     * each coordinate
     * @param a first point
     * @param b second point
     */
    public void drawLine(Point a, Point b){
        rasterized(a.x(), a.y(), b.x(), b.y());
    }
    
    /**
     * Method to draw the line, given the literal line, it gets each coordinate
     * of each point
     * @param line the line we must draw
     */
    public void drawLine(Line line){
        rasterized(line.getPointA().x(), line.getPointA().y(), line.getPointB().x(), line.getPointB().y());
    }
    
    /**
     * Method to change the color of the line
     * @param color that must be changed
     */
    public void changeColor(int color){
        this.color = color;
    }
}
