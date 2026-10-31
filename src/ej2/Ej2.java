/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej2;
import control.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import view.Window;//la nuestra

/**
 *
 * @author lgome
 */
public class Ej2 {    
    /**
     * Main that creates a new run for creating a new window, that is used
     * in the controller, that manages all the actions, also there is a method
     * that if the button bChangeS, the one for changing the size, is pressed
     * the window and the panel must change, with the dimension that are created
     * before
     * @param args 
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable(){ 
        @Override 
        public void run(){
            Window window = new Window(); 
            window.setVisible(true); 
            
            Controller c = new Controller(window.getPanel(), window); 
            
            final int[] wChange = {600, 1000, 800};
            final int[] hChange = {400, 800, 600};
            final int[] index = {0};
        
            window.getBChangeS().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    int i = index[0];
                    
                    window.getPanel().newSize(wChange[i], hChange[i]);
                    window.setSize(wChange[i], hChange[i]);
                    
                    index[0] = (index[0] + 1) % wChange.length;

                    window.revalidate();
                    window.repaint();
                    IO.println("SIZE CHANGED");
                    c.changeMode(window, window.getPanel());
            }
        }
        );

        }
    });
            
    }
    
}
