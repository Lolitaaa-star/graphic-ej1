/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej2;
import control.Controller;
import javax.swing.SwingUtilities;
import view.Window;//la nuestra
import model.objects.*;

/**
 *
 * @author lgome
 */
public class Ej2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //clase anonima, es decir estamos generando una clase como los métodos lamba pero aqui se pueden crear instancias
        SwingUtilities.invokeLater(new Runnable(){ 
        @Override //metodo de la clase runnable
        public void run(){
            Window window = new Window(); // crea la ventana
            window.setVisible(true); // la pone visible
            
            new Controller(window.getPanel(), window); //crea el controlador y como el parametro es el panel le pasa eso
        }
    });
            
    }
    
}
