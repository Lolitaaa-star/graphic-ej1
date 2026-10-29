/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.raster;


/**
 *
 * @author lgome
 */
public class DDA extends Liner{ 
    
    public DDA(Raster raster){
        super(raster);
    }

    @Override
    protected void rasterized(int x1, int y1, int x2, int y2) {
        // k -slope
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        //se calcula el incremento de cada coordenada y según cual sea mayor se elige para ver cuantos pixeles se pinta
        int pixeles;
        if(Math.abs(dx) > Math.abs(dy))
            pixeles = Math.abs(dx);
        else
            pixeles = Math.abs(dy);
        
        //incremento de cada coordenada para ver por donde hay que pintar
        double incX = (double) dx/pixeles;
        double incY = (double) dy/pixeles;
        
        
        //ponemos los puntos iniciales
        double x = x1;
        double y = y1;
        
        raster.setPixel((int)x, (int)y, color); //pintamos el primer pixel
        //y se pinta pixel por pixel según la cantidad q debe pintarse q hemos calculado 
        for ( int i=0; i <= pixeles && x < raster.getWidth() && y < raster.getHeight(); i++){
            x += incX;
            y += incY;
            raster.setPixel((int)x, (int)y, color);
        }
    }
    
}
