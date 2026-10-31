/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author lgome
 */
public class Window extends JFrame{
    
    private Panel panel;
    private int WIDTH = 800, HEIGHT = 600; 
    private JButton bLine, bPolyg, bChangeS, bChange;
    private JLabel actionC, actionS, actionF, actionE, actionN, actionA;
    
    /**
     * Constructor by default, it puts a WIDTH of 800 and a HEIGHT of 600
     * a title, all the text for the options, and buttons also for changing 
     * the mode
     */
    public Window(){
        //Data for the window, the title and the option for closing it 
        setTitle("APGR Line drawing app");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        panel = new Panel(WIDTH, HEIGHT);
        add(panel, BorderLayout.CENTER);
        
        //text for differents options
        actionC = new JLabel("Clear-C");
        actionS = new JLabel("Stright Lines-Shift");
        actionF = new JLabel("Fill mode-F");
        actionE = new JLabel("Out fill mode-E");
        actionN = new JLabel("Clipping mode-N");
        actionA = new JLabel("Change fill color-A");
        JPanel actions = new JPanel();
        actions.add(actionC);
        actions.add(actionS);
        actions.add(actionF);
        actions.add(actionE);
        actions.add(actionN);
        actions.add(actionA);
        add(actions, BorderLayout.SOUTH);
        
        //buttons for change in the window
        JPanel buttons = new JPanel();
        bLine = new JButton("Line");
        bPolyg = new JButton("Polygon");
        bChangeS = new JButton("Change Size");
        bChange = new JButton("Change color");
        buttons.add(bLine);
        buttons.add(bPolyg);
        buttons.add(bChangeS);
        buttons.add(bChange);
        add(buttons, BorderLayout.NORTH);
        
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Constructor with parameters, makes the same that the other but with
     * new size
     * @param w the new WIDTH
     * @param h the new HEIGHT
     */
    public Window(int w, int h){
        setTitle("APGR Line drawing app");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        panel = new Panel(w, h);
        add(panel, BorderLayout.CENTER);
        
        //text for differents options
        actionC = new JLabel("Clear-C");
        actionS = new JLabel("Stright Lines-Shift");
        actionF = new JLabel("Fill mode-F");
        actionE = new JLabel("Out fill mode-E");
        actionN = new JLabel("Clipping mode-N");
        JPanel actions = new JPanel();
        actions.add(actionC);
        actions.add(actionS);
        actions.add(actionF);
        actions.add(actionE);
        actions.add(actionN);
        add(actions, BorderLayout.SOUTH);
        
        //buttons for change in the window
        JPanel buttons = new JPanel();
        bLine = new JButton("Line");
        bPolyg = new JButton("Polygon");
        bChangeS = new JButton("Change Size");
        bChange = new JButton("Change color");
        buttons.add(bLine);
        buttons.add(bPolyg);
        buttons.add(bChangeS);
        buttons.add(bChange);
        add(buttons, BorderLayout.NORTH);
        
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Method for get the panel
     * @return panel 
     */
    public Panel getPanel(){
        return panel;
    }
    
    /**
     * Method for change the panel for the new one
     * @param panel the new panel we are going to modify
     */
    public void setPanel(Panel panel){
        this.panel = panel;
    }
    
    /**
     * Method for get the button for changing the mode to Line
     * @return bLine
     */
    public JButton getBLine(){
        return bLine;
    }
    
    /**
     * Method for get the button for changing the mode to Polygon
     * @return bPolyg
     */
    public JButton getBPolyg(){
        return bPolyg;
    }
    
    /**
     * Method for get the button for changing the window size
     * @return bChangeS
     */
    public JButton getBChangeS(){
        return bChangeS;
    }
    
    /**
     * Method for get the button for changing the clear color 
     * @return bChange
     */
    public JButton getBChange(){
        return bChange;
    }
    
}
