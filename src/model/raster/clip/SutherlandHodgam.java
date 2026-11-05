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
        //guarantee that the clippingPolygon is convex
        int nVer = clippingPolygon.size();
        boolean sameS = true;
        Point pA = clippingPolygon.get(0);
        Point pB = clippingPolygon.get(1);
        Point pC = clippingPolygon.get(2);
            
        double r = (((pB.x() - pA.x())*(pC.y() - pB.y())) - ((pB.y() - pA.y())*(pC.x() - pB.x())));
        double sign = 0;
        if(r<0)
            sign = -1;
        else
            sign = 1;
        for(int i=1; i<nVer; i++){
            pA = clippingPolygon.get(i);
            pB = clippingPolygon.get((i+1)%nVer);
            pC = clippingPolygon.get((i+2)%nVer);
            
            double r1 = (((pB.x() - pA.x())*(pC.y() - pB.y())) - ((pB.y() - pA.y())*(pC.x() - pB.x())));
            double sign1 = 0;
            if(r1<0)
                sign1=-1;
            else
                sign1=1;
            
            if(sign != sign1)   
                sameS=false;   
        }
        
        if(!sameS){
            IO.println("NOT CONVEX CLIPPING POLYGON");
            return new Polygon();
        }
        
        Polygon clipOrientate = clippingPolygon.orientate();
        //orientate
        List<Line> clippingEdgesOriented = clipOrientate.toEdges();

        Polygon currentPolygon = originalPolygon;
    
        for (Line clipLine : clippingEdgesOriented) { 
            Polygon currentOriented = currentPolygon.orientate();
            List<Line> currentEdgesOriented = currentOriented.toEdges();

            Polygon newPolygon = new Polygon();
        
            for (Line line : currentEdgesOriented) { 
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
        //currentPolygon.add(new Point(1, 1));
        return currentPolygon; 
    }


}


