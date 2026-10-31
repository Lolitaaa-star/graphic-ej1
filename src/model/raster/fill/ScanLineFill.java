/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster.fill;

import java.util.*;
import model.objects.Line;
import model.objects.Polygon;
import model.raster.DDA;
import model.raster.PolygonRaster;
import model.raster.Raster;
import model.raster.RasterBI;

/**
 *
 * @author lgome
 */
public class ScanLineFill {
    
    private Polygon ngon;
    
    /**
     * Constructor that indicates the polygon that is going to be painted
     * @param ngon 
     */
    public ScanLineFill(Polygon ngon){
        this.ngon = ngon;
    }
    
    /**
     * Method to paint the polygon, with the method of scanLine, it eliminates
     * the horizontal ones and for the min y to the max of the polygon for
     * each one of them it is calculated the intersection with the y and 
     * for each intersection is painted the pixel
     * @param img raster where the polygon will be painted
     * @param fillColor color to paint the polygon
     * @param boundaryColor color of the boundary, that is the lines of the polygon
     */
    public void fill(RasterBI img, int fillColor, int boundaryColor){
        
        //1 preprocessing of polygon to fill 
        List<Line> edges = new ArrayList<>();
            //iterate throught polygon points to save each edge
            for(int i = 0; i< ngon.size(); i++){
                Line edge;
                if(i+1 != ngon.size())
                    edge = new Line(ngon.get(i), ngon.get(i+1));
                else
                    edge = new Line(ngon.get(i), ngon.get(0));
                
                if(!edge.isHorizontal()){
                    edge = edge.orientate();
                    edges.add(edge);
                }
            }
            
            
        //y min, y max
        int ymin = Integer.MAX_VALUE, ymax = Integer.MIN_VALUE;
       
        for(int i=0; i<ngon.size(); i++){
            if(ngon.get(i).y() < ymin )
                ymin = ngon.get(i).y();
            if(ngon.get(i).y() > ymax)
                ymax = ngon.get(i).y();
        }
        
        List<Double> inter = new ArrayList<>();
        
        //2 filling 
        for(int y=ymin; y<=ymax; y++){
            inter.clear();
            for(int z=0; z<edges.size(); z++){
                //with the points of the line
                int x1 = edges.get(z).getPointA().x();
                int y1 = edges.get(z).getPointA().y();
                int x2 = edges.get(z).getPointB().x();
                int y2 = edges.get(z).getPointB().y();
                
                //verify if it pass the limits
                if(y>=y1 && y<y2){
                    double x = (x1 + ((double)(y-y1)*(x2-x1))/(y2-y1));
                    
                    inter.add(x);
                }
            }

                Collections.sort(inter);

                for (int i = 0; i + 1 < inter.size(); i += 2) {

                    int x1 = (int) Math.ceil(inter.get(i));
                    int x2 = (int) Math.floor(inter.get(i + 1));

                    for (int x = x1; x <= x2; x++) {
                        img.setPixel(x, y, fillColor);
                    }
                }
        }
                
        //3 boundary fill
        DDA line = new DDA((Raster) img);
        line.changeColor(boundaryColor);
        PolygonRaster p = new PolygonRaster(line);
        
        p.rasterize(ngon);
        
    }
}
