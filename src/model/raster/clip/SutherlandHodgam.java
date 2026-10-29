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
//Esto es para con un polígono definir el área q quieres ver y así crear otro polígono
public class SutherlandHodgam {
    //IN
    //originalPolygon - polygon you can clip
    //clippingPolygon - must be convex


    //OUT
    //newPolygon
    //SE TIENE Q BORRAR EL OTRO POLÍGONO DICE Q LO TENGAMOS EN EL CONTROLLER UNA LISTA CON LOS POLÍGONOS EXISTENTES Y QUITARLO CUANDO SE HAGA EL CLIPPING
  
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


