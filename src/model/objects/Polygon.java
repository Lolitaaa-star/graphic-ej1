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
    private int fColor;
    private boolean fill;
    
    /**
     * Constructor by default creates a new array for the polygon, that is a
     * list of points
     */
    public Polygon(){
        polygon = new ArrayList<>(); //First index on 0
        fill = false;
        fColor = 0;
    }
    
    /**
     * Constructor is made with a polygon, it takes all his points and his information 
     * @param pol the polygon that we are going to copy
     */
    public Polygon(Polygon pol){
        polygon = new ArrayList<>(pol.size());
        for(int i=0; i<pol.size(); i++)
            polygon.add(pol.get(i));
        fill = pol.isFill();
        fColor = pol.getColor();
    }
    
    /**
     * Method to add a point to the polygon
     * @param p point that is going to be added
     */
    public void add(Point p){
        polygon.add(p);
    }
     
    /**
     * Method to add more that one point
     * @param p list of points
     */
    public void addMore(Point... p){
        polygon.addAll( List.of(p) );
    }
    
    /**
     * Method the get the point of the polygon that is indicated
     * @param index the number of the list of points we want
     * @return the point of the list
     */
    public Point get(int index){
        return polygon.get(index);
    }

    /**
     * Method to get the size of the list of points
     * @return the size
     */
    public int size(){
        return polygon.size();
    }
    
    /**
     * Method to create the list of edges, lines, with the list of points, if 
     * is the last one we create with the first one
     * @return the list of edges of the polygon
     */
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
    
    /**
     * Method to put the polygon as filled with the color
     * @param color the color that fills the polygon
     */
    public void isFill(int color){
        fill = true;
        fColor = color;
    }
    
    /**
     * Method to get if the polygon is filled or not, that is indicated in 
     * the boolean fill
     * @return the boolean fill
     */
    public boolean isFill(){
        return fill;
    }
    
    /**
     * Method to get the color of the polygon that is fill
     * @return the fColor
     */
    public int getColor(){
        return fColor;
    }
}
