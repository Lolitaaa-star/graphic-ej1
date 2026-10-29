/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.objects;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author lgome
 */
public class Polygon {
    
    private List<Point> polygon;
    
    public Polygon(){
        polygon = new ArrayList<>(); //First index on 0
    }
    
    public Polygon(Polygon pol){
        polygon = new ArrayList<>(pol.size());
        for(int i=0; i<pol.size(); i++)
            polygon.add(pol.get(i));
    }
    
    public void add(Point p){
        polygon.add(p);
    }
    
    //en vez de overload el método, mismo nombre distinta cantidad de parámetros hacemos que los parámetros puedan ser "infinitos" con int... a se llaman las variable arguments
    //van siempre las últimas 
    public void addMore(Point... p){
        polygon.addAll( List.of(p) );
    }
    
    public Point get(int index){
        return polygon.get(index);
    }

    public int size(){
        return polygon.size();
    }
    
    public List<Line> toEdges(){
        ArrayList<Line> edges = new ArrayList<>();
        for(int i=0; i<polygon.size(); i++){
            Point a, b;
            if(i+1 >= polygon.size()){
                a = polygon.get(i);
                b = polygon.get(0);
            }else{
                a = polygon.get(i);
                b = polygon.get(i+1);
            }
            Line l = new Line(a, b);
            edges.add(l);
        }
        return edges;
    }
}
