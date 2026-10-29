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
    
    private final Panel panel;
    private static final int WIDTH = 800, HEIGHT = 600; 
    private JButton bLine, bPolyg, bFill;
    private JLabel actionC, actionS, actionF, actionE, actionN;
    
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
        JPanel actions = new JPanel();
        actions.add(actionC);
        actions.add(actionS);
        actions.add(actionF);
        actions.add(actionE);
        actions.add(actionN);
        add(actions, BorderLayout.SOUTH);
        
        JPanel buttons = new JPanel();
        bLine = new JButton("Line");
        bPolyg = new JButton("Polygon");
        buttons.add(bLine);
        buttons.add(bPolyg);
        add(buttons, BorderLayout.NORTH);
        
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public Panel getPanel(){
        return panel;
    }
    
    public JButton getBLine(){
        return bLine;
    }
    
    public JButton getBPolyg(){
        return bPolyg;
    }
    
}
