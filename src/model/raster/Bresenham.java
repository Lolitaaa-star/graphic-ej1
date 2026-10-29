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
    
     public Bresenham (Raster raster){
        super(raster);
    }

    @Override
    protected void rasterized(int x1, int y1, int x2, int y2) {
        //calculo de las diferencias
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        
        int y = y1;
        int x = x1;
        
        
        if(dx >= dy){
        //siempre aumenta x
            int P = 2*dy -dx;
            for(int i=0; i <=dx; i++){
            raster.setPixel((int)x, (int)y, color); //pintamos 
            if(P>=0){
                //ver si sube o baja
                if(y1 < y2) //sube
                    y++;
                else  //baja
                    y--;
                P = P + 2*dy - 2*dx; // el pixel esta para arriba
            }else{
                P = P + 2*dy;
            }//avance de la x vemos a q direccion va y lo sumamos
                if(x1 < x2) //sube
                    x++;
                else  //baja
                    x--;
            }
        }else{
            //siempre aumenta y
            int P = 2*dx -dy;
            for(int i=0; i <=dy; i++){
            raster.setPixel((int)x, (int)y, color); //pintamos 
            if(P>=0){
                //ver si sube o baja
                if(x1 < x2) //sube
                    x++;
                else  //baja
                    x--;
                P = P + 2*dx - 2*dy; // el pixel esta para arriba
            }else{
                P = P + 2*dx; // el pixel esta hacia abajo
            }//avance de la y
                //ver si sube o baja
                if(y1 < y2) //sube
                    y++;
                else  //baja
                    y--;     
        }
        }
    }
}
