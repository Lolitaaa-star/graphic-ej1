/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;

/**
 *
 * @author lgome
 */
public class NaiveLiner extends Liner{
    
    public NaiveLiner(Raster raster){
        super(raster);
    }
    
    /**
     * Method for rasterize with the NaiveLiner method, that consists in with 
     * the function y = k*x + q for each segment we create the line
     * @param x1 coordinate x for first point
     * @param y1 coordinate y for first point
     * @param x2 coordinate x for second point
     * @param y2 coordinate y for second point
     */
    @Override
    protected void rasterized(int x1, int y1, int x2, int y2) {
        // k - slope
        int dy = (y2-y1);
        int dx = (x2-x1);

        // make sure dx is not 0 if dx == 0 y = x verticals
        if( dx == 0 ){
            if(y1<=y2){
                for(int y = y1; y <= y2; y++){ 
                    int x = x1;
                    raster.setPixel(x, y, color);
                }
            }else{
                for(int y = y2; y >= y1; y--){ 
                    int x = x1;
                    raster.setPixel(x, y, color);
                }
            }
            return;
        }
        
        // function  y = k*x + q
        //           y - (k * x) = q
        
        double k =  (double) dy / dx ;
        double q = y1 - (k * x1);

        if( Math.abs(dx) >= Math.abs(dy) ){
            if(x1<=x2){
                for (int x = x1; x <= x2; x++) {
                    int y = (int) Math.round(k*x + q);
                    raster.setPixel(x, y, color);
                }
            }else{
                for (int x = x1; x >= x2; x--) {
                    int y = (int) Math.round(k*x + q);
                    raster.setPixel(x, y, color);
                }
            }
        }else{
           if(y1<=y2){
                for (int y = y1; y <= y2; y++) {
                    int x = (int) Math.round((y-q) /k);
                    raster.setPixel(x, y, color);
                }
            }else{
                for (int y = y1; y >= y2; y--) {
                    int x = (int) Math.round((y-q)/k);
                    raster.setPixel(x, y, color);
                }
            }
        }
    }
    
}
  