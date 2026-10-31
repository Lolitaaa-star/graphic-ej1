/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster.clip;

import java.util.List;
import model.objects.Line;
import model.objects.Point;
import model.objects.Polygon;

/**
 *
 * @author lgome
 */
public class SutherlandHodgam {
    
    /**
     * Method for clipping the polygons given, it calculate if each point of the 
     * original polygon is not inside the clipping polygon, and calculate
     * the intersections between then to draw the new polygon 
     * @param originalPolygon is the original polygon that will be clip
     * @param clippingPolygon is the polygon that defines the boundaries 
     * @return polygon that has been clipped
     */
    public Polygon clip(Polygon originalPolygon, Polygon clippingPolygon) {    
        List<Line> clippingPolygonEdges = clippingPolygon.toEdges(); 
        Polygon currentPolygon = originalPolygon;
    
        for (Line clipLine : clippingPolygonEdges) { 
            List<Line> currentEdges = currentPolygon.toEdges();
            Polygon newPolygon = new Polygon();
        
            for (Line line : currentEdges) { 
                Point startEndPoint = line.getPointA();
                Point lastEndPoint = line.getPointB(); 
            
                boolean insideStart = clipLine.isInside(startEndPoint); 
                boolean insideLast = clipLine.isInside(lastEndPoint); 
            
                if (insideStart && insideLast) { 
                    newPolygon.add(startEndPoint);
                    newPolygon.add(lastEndPoint);
                } else if (insideStart && !insideLast) { 
                    newPolygon.add(startEndPoint);
                    //intersection 
                    Point inter = clipLine.calculateIntersection(startEndPoint, lastEndPoint);
                    if(inter != null){
                        newPolygon.add(inter);
                    }
                } else if (!insideStart && insideLast) { 
                    //intersection 
                    Point inter = clipLine.calculateIntersection(startEndPoint, lastEndPoint);
                    if(inter != null){
                        newPolygon.add(inter);
                    } 
                    newPolygon.add(lastEndPoint);
                } 
            }
            currentPolygon = newPolygon; // Actualizamos para la siguiente arista de recorte
        } 
        return currentPolygon; 
    }


}


