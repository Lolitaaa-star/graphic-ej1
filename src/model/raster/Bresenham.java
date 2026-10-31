/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;

/**
 *
 * @author lgome
 */
public class Bresenham extends Liner{
    
    /**
     * Constructor that calls the constructor of the father
     * @param raster 
     */
     public Bresenham (Raster raster){
        super(raster);
    }

     /**
      * Method to draw the line with Bresenham method, that consists in calculate
      * the increase of each coordinate, and then look for the next pixel
     * @param x1 coordinate x for first point
     * @param y1 coordinate y for first point
     * @param x2 coordinate x for second point
     * @param y2 coordinate y for second point
      */
    @Override
    protected void rasterized(int x1, int y1, int x2, int y2) {
        //calculo de las diferencias
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        
        int y = y1;
        int x = x1;

        if(dx >= dy){
        //always increase the coordinate x
            int P = 2*dy -dx;
            for(int i=0; i <=dx; i++){
            raster.setPixel((int)x, (int)y, color); 
            if(P>=0){
                //see if it goes up or down
                if(y1 < y2) //up
                    y++;
                else  //down
                    y--;
                P = P + 2*dy - 2*dx; // pixel is up
            }else{
                P = P + 2*dy; //pixel is down
            }//see the direction of the coordinate x
                if(x1 < x2) //up
                    x++;
                else  //down
                    x--;
            }
        }else{
            //always increase the coordinate y
            int P = 2*dx -dy;
            for(int i=0; i <=dy; i++){
            raster.setPixel((int)x, (int)y, color);  
            if(P>=0){
                //see if it goes up or down
                if(x1 < x2) //up
                    x++;
                else  //down
                    x--;
                P = P + 2*dx - 2*dy; //pixel is up
            }else{
                P = P + 2*dx; //pixel is down
            }///see the direction of the coordinate y
                if(y1 < y2) //up
                    y++;
                else  //down
                    y--;     
        }
        }
    }
}
