/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;
import model.raster.*;
/**
 *
 * @author lgome
 */

public class Panel extends JPanel{
    
    private RasterBI raster;
    
    /**
     * Constructor it creates a new raster with the parameters given
     * @param w the WIDHT of the panel
     * @param h the HEIGHT of the panel
     */
    public Panel(int w, int h){
        setPreferredSize(new Dimension(w,h));
        raster = new RasterBI(w, h);
    }
    
    /**
     * Method for painting a component in the panel
     * @param g the component that we want to paint
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(raster.getImage(), 0, 0, null); 
        
        repaint();
    }
    
    /**
     * Method for getting the raster
     * @return raster
     */
    public Raster getRaster(){
        return raster;
    }

    /**
     * Method for changing the size of the new panel
     * @param w new WIDTH
     * @param h new HEIGHT
     */
    public void newSize(int w, int h) {
        setPreferredSize(new Dimension(w, h));
        raster = new RasterBI(w, h);
    }
}
