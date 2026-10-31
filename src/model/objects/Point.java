/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.objects;

/**
 *
 * @author lgome
 */

public record Point(int x, int y){
    
    /**
     * Method to create a point with only a coordinate
     * @param x the new coordinate
     * @return the new point
     */
    public Point withX(int x){
        return new Point(x, this.y);
    }
    
    /**
     * Method to create a point with only a coordinate
     * @param y the new coordinate
     * @return the new point
     */
    public Point withY(int y){
        return new Point(this.x, y);
    }
    
    /**
     * Methos to create a point with both coordinates
     * @param x coordinate of the x-coor
     * @param y coordinate of the y-coor
     * @return the new point
     */
    public Point withXY(int x, int y){
        return new Point(x, y);
    }
    
    /**
     * Method to calculate the distance between to coordinates
     * @param x coordinate x
     * @param y coordinate y
     * @return the distance 
     */
    public int distanceTo(int x, int y){
        return y - x;
    }
}

