/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author lgome
 */
public class Canvas {
    private JFrame window;
    private JPanel panel;
    private int width, height;
    
    private BufferedImage image;
    
    
    public Canvas(int width, int height){
        
        
        this.width = width;
        this.height = height;
        
        //Panel panel = new Panel();
        
        window = new JFrame("APRG1+ 3 Excercise"); 
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
        
       
        panel = new JPanel(){
            
           
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g); 
            
                g.drawImage(image, 0, 0, null);
                draw();
            }
        };
        
        panel.setPreferredSize(new Dimension(width, height)); 
        window.add(panel); 
        
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
   
    public void clean(){
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }
    
    private void draw(){
        clean();
        
        
        image.setRGB(10, 10, 0xFFFFFF); 
        Graphics g = image.getGraphics();
        g.drawLine(20, 20, 100, 20);
        g.drawLine(40, 70, 100, 30);
        g.drawRect(300, 30, 100, 100);
        
        panel.repaint();
    }
}
