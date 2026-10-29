/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.objects;

/**
 *
 * @author lgome
 */

//record es otra clase "mejor q la normal"
public record Point(int x, int y){
    public Point withX(int x){
        return new Point(x, this.y);
    }
    
    //hacer withY, withXY
    public Point withY(int y){
        return new Point(this.x, y);
    }
    
    public Point withXY(int x, int y){
        return new Point(x, y);
    }
    
    //implementar
    public int distanceTo(int x, int y){
        return y - x;
    }
}


/*
public class Point {
    private int x, y; //podria ser double pero por ahora usamos int
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
}*/
