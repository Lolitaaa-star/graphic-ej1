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
    
    public Line(Point a, Point b){
        this.a = a;
        this.b = b;
        color = 0x008000;
    }
    
    public Point getPointA(){
        return a;
    }
    
    public Point getPointB(){
        return b;
    }

    //si es horizontal la y no cambia
    public boolean isHorizontal() {
        if(a.y() == b.y())
            return true;
        else
            return false;  
       //otra opcion
       //return a.y() == b.y() devuelve verdadero o falso
    }
    
    public Line orientate() {
        if(a.y() > b.y()) {
            // orientate
            return new Line(b, a);

            // only if your line is editable
            // Point temp = a;
            // a = b;
            // b = temp;
        }
        return this;
    }
    
    //presentation 3 slide 17
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
    
    //  Method to calculate de intersection between the line in the instance and 
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
