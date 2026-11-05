/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;

import model.objects.Point;
import model.objects.Polygon;

/**
 *
 * @author lgome
 */
public class PolygonRaster {
    
    private Liner liner;
    
    /**
     * Constructor that indicates the liner we must use
     * @param liner 
     */
    public PolygonRaster(Liner liner){
        this.liner = liner;
    }
    
    /**
     * Method to rasterize the polygon when it is finally defined, so it connects
     * all the points, the last one with the first one, and if it does not 
     * pass the limit it is painted
     * @param ngon polygon that will be rasterize
     */
    public void rasterize(Polygon ngon){
        
        //more than to points to draw
        if (ngon.size() >= 2){
            for(int i=0; i<ngon.size(); i++){
                //we choose each point to draw the line, if is the last we draw de last line
                Point a, b;
                if(i+1 >= ngon.size()){
                    a = ngon.get(i);
                    b = ngon.get(0);
                }else{
                    a = ngon.get(i);
                    b = ngon.get(i+1);
                }
                
                //To verify it does not pass the limit
                if(a.x()>0 && a.x()<liner.raster.getWidth() &&
                   a.y()>0 && a.y()<liner.raster.getHeight() &&     
                   b.x()>0 && b.x()<liner.raster.getWidth() &&
                   b.y()>0 && b.y()<liner.raster.getHeight())
                        liner.drawLine(a, b);
                else{
                    IO.println("OUT OF LIMIT");
                }
            }
        }
    }
    
    /**
     * Method to rasterize the polygon when it has not being defined yet, so it 
     * not connects the last point with the first one
     * @param ngon polygon that will be rasterize
     */
    public void rasterNotEnd(Polygon ngon){
        //more than to points to draw
        if (ngon.size() > 2){
            for(int i=0; i<ngon.size()-1; i++){
                //we choose each point to draw the line, if is the last we draw de last line
                Point a = ngon.get(i);
                Point b = ngon.get(i +1);

                //To verify it does not pass the limit
                if(a.x()>0 && a.x()<liner.raster.getWidth() &&
                   a.y()>0 && a.y()<liner.raster.getHeight() &&     
                   b.x()>0 && b.x()<liner.raster.getWidth() &&
                   b.y()>0 && b.y()<liner.raster.getHeight())
                        liner.drawLine(a, b);
                else
                    IO.println("OUT OF LIMIT");
            }
        }
    }
    
}
