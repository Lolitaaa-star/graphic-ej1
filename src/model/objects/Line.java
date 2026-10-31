/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.objects;

/**
 *
 * @author lgome
 */
public class Line {
    private Point a, b;
    private int color;
    
    /**
     * Constructor for creating a new line, with a selected color 
     * @param a first point
     * @param b second point
     */
    public Line(Point a, Point b){
        this.a = a;
        this.b = b;
        color = 0x008000;
    }
    
    /**
     * Method for getting the first point
     * @return a first point
     */
    public Point getPointA(){
        return a;
    }
    
    /**
     * Method for getting the last point
     * @return b last point
     */
    public Point getPointB(){
        return b;
    }

    /**
     * Method that verify if the line is or not horizontal, it checks both 
     * points to see if the coordinate y is the same, that means that is 
     * horizontal
     * @return true is the corrdinates y are the same, false otherwise
     */
    public boolean isHorizontal() {
        if(a.y() == b.y())
            return true;
        else
            return false;  
    }
    
    /**
     * Method to change the orientation of the line, changes the first point
     * with the second, if the line goes down, the first point is upper the 
     * second
     * @return a new line if it must be orientated, or the same line
     */
    public Line orientate() {
        if(a.y() > b.y()) {
            // orientate
            return new Line(b, a);
        }
        return this;
    }
    
    /**
     * Methos to verify if the point is inside or not, so with the normal
     * vector it must be multiplicated with the product of the first 
     * point and the point that is verify
     * @param p point that must be evaluated if there is inside or not
     * @return true if is inside or false otherwise
     */
    public boolean isInside(Point p){
        boolean isIn= false;
        //Calculate tangent vector
        int x = b.x() - a.x();
        int y = b.y() - a.y();

        //calculate normal vector
        // n=(-t.y, t.x)
        int nX = -y;
        int nY = x;

        //dot product
        int vX = p.x()- a.x();
        int vY = p.y() - a.y();
        double dotP = (vX*nX) + (vY*nY);
        //determine by angle
        double epsilon = 1e-9;
        
        if(dotP <= -epsilon)
            isIn = true;
        else if(dotP < -epsilon)
            isIn = false;
        return isIn;
    }
    
    /**
     * Method to calculate the intersection of the actual line with the one given
     * in the parameters
     * @param p1 first point
     * @param p2 second point
     * @return inter the intersection of both lines
     */ 
    public Point calculateIntersection(Point p1, Point p2){
        int x1 = p1.x();
        int y1 = p1.y();
        int x2 = p2.x();
        int y2 = p2.y();

        int x3 = a.x();
        int y3 = a.y();
        int x4 = b.x();
        int y4 = b.y();

        Point inter = null;

        double den = (double)(x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (den != 0) {
            double x0 = (((x1 * y2 - x2 * y1) * (x3 - x4)) - ((x3 * y4 - x4 * y3) * (x1 - x2))) / den; 
            double y0 = (((x1 * y2 - x2 * y1) * (y3 - y4)) - ((x3 * y4 - x4 * y3) * (y1 - y2))) / den; 
            inter = new Point((int) x0, (int) y0); 
        }
        
        return inter;
    }
}
