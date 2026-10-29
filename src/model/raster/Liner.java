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
    //algoritmos naive, DDA, bresenhma, antialiase, dotted, etc...
    
    Raster raster;
    int color; //color de la linea
    
    public Liner(Raster raster){
        this.raster = raster;
        color = 0xFFFFFF; // white
    }
    
    protected abstract void rasterized(int x1, int y1, int x2, int y2);
    
    //method overload HACER LOS TRES
    public void drawLine(int x1, int y1, int x2, int y2){
        rasterized(x1, y1, x2, y2);
    } 
    
    public void drawLine(Point a, Point b){
        rasterized(a.x(), a.y(), b.x(), b.y());
    }
    
    public void drawLine(Line line){
        rasterized(line.getPointA().x(), line.getPointA().y(), line.getPointB().x(), line.getPointB().y());
    }
    
    public void changeColor(int color){
        this.color = color;
    }
}
