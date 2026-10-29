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
//el panel donde pasar las cosas
public class Panel extends JPanel{
    
    private RasterBI raster;
    
    //pone el tamaño y crea el raster(la cuadrícula)
    Panel(int w, int h){
        setPreferredSize(new Dimension(w,h));
        raster = new RasterBI(w, h);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //dibuja un punto
        g.drawImage(raster.getImage(), 0, 0, null); //el getImage es xq el metodo necesita una BufferedImage
        //raster.clear();
        
        repaint();
    }
    
    public Raster getRaster(){
        return raster;
    }
}
