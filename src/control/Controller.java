/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.awt.Graphics;
import java.awt.event.*;
import model.objects.*;
import model.raster.*;
import view.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.raster.clip.SutherlandHodgam;
import model.raster.fill.ScanLineFill;
import model.raster.fill.SeedFill;

/**
 *
 * @author lgome
 */
public class Controller {
    
    private Liner liner;
    private PolygonRaster polygonR;
    private Polygon poly1, poly2, poly3;
    private ArrayList<Polygon> actualP = new ArrayList<>();
    private int x1, y1, x2, y2;
    private BufferedImage copia;
    private boolean dibujando, modeLine=true, modePolyg=false, painted=false; 
    private int[] colors = {0x000000, 0xff0000, 0x00ff00, 0x0000ff, 0x1616133};
    private int[] index = {0};
    
    /**
     * Constructor to create the controller, also manages if there is a change
     * because a button has beeen clicked, in that case the method changeMode is
     * called
     * @param panel
     * @param window 
     */
    public Controller(Panel panel, Window window){
        panel.getRaster().clear();
        
        window.getBLine().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                modeLine=true;
                modePolyg=false;
                IO.println("MODE LINE");
                changeMode(window, panel);
            }
        }
        );
        
        window.getBPolyg().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                modeLine=false;
                modePolyg=true;
                IO.println("MODE POLYGON");
                changeMode(window, panel);
            }
        }
        );
        
        window.getBChange().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panel.getRaster().setClearColor(colors[index[0]]);
                index[0] = (index[0]+1)%colors.length;
                panel.getRaster().clear();
                changeMode(window, panel);
                IO.println("CHANGE COLOR");  
            }
        }
        );
        
        changeMode(window, panel);
        
        }
    
    /**
     * Method to draw lines, it creates initial lines, and also manages if
     * the mouse is clicked or moved, to draw the provisional line and the 
     * final one that the user wants
     * @param panel 
     */
    public void drawLine(Panel panel){
        final boolean shiftPress[] = {false};
        clear(panel);
            panel.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent e){

                    if(x1 == -1){
                        //first click
                        x1 = e.getX();
                        y1 = e.getY();

                        //copia el raster original para cuando movamos el raton
                        BufferedImage original = panel.getRaster().getImage();
                        copia = new BufferedImage(
                            original.getWidth(),
                            original.getHeight(),
                            BufferedImage.TYPE_INT_RGB
                        );

                        Graphics g = copia.getGraphics();
                        g.drawImage(original, 0, 0, null);
                        g.dispose();
                    }else{
                        //coge el segundo punto y dibuja la línea ofical
                        x2 = e.getX();
                        y2 = e.getY();
                        liner.drawLine(x1, y1, x2, y2); // esto si dibuja un punto
                    
                        //reinicio
                        panel.repaint();
                        x1 = y1 = -1;
                
                    }
                }
                });
        
            panel.addMouseMotionListener(new MouseAdapter(){
                @Override
                public void mouseMoved(MouseEvent e){

                    //se está moviendo
                    if(x1 != -1 && !shiftPress[0]){
                        x2 = e.getX();
                        y2 = e.getY();
                
                    //recuperamos el raster original
                    BufferedImage original = panel.getRaster().getImage();

                    Graphics g = original.getGraphics();
                    g.drawImage(copia, 0, 0, null);
                    g.dispose();

                    //línea provisional
                    liner.drawLine(x1, y1, x2, y2);
                    //lo borra por si te vuelves a mover
                    panel.repaint();
                
                    }
                }
            });
            
            panel.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_SHIFT && x1 != -1){
                   shiftPress[0] = true;
                   java.awt.Point p = panel.getMousePosition();
                   int x = p.x;
                   int y = p.y;
                   
               BufferedImage original = panel.getRaster().getImage();
               Graphics g = original.getGraphics();
               g.drawImage(copia, 0, 0, null);
               g.dispose();
               
                   //search the nearset point to horizontal, vertical or diagonal
                   int dx = x-x1;
                   int dy = y-y1;
                   double k = (double)dy/dx;
                   //vertical
                   if(Math.abs(dx) <= 25 ){
                       liner.drawLine(x1, y1, x1, y);
                       x=x1;
                   }
                   else if(k>=-0.25 && k<=0.25){ //horizontal
                       liner.drawLine(x1, y1, x, y1);
                       y=y1;
                   }
                   //diagonales
                   else if(k>0.25 || k<-0.25){
                       double d=(Math.abs(dx)+Math.abs(dy))/2.0;
                       int sx, sy;
                       if(dx<0)
                           sx = -1;
                       else
                           sx = 1;
                       x=(int) Math.round(x1+(sx*d));
                        if(dy<0)
                           sy = -1;
                       else
                           sy = 1;
                       y=(int) Math.round(y1+(sy*d));
                       liner.drawLine(x1, y1, x, y);
                   }


                    
                    x1=y1=-1;
                   shiftPress[0] = false;
                   panel.repaint();
                }
            }
            });
            
        Line l1 = new Line(new Point(50, 200), new Point(200, 50));     // ascending from left to right
        Line l2 = new Line(new Point(300, 50), new Point(450, 200));    // descending from left to right
        Line l3 = new Line(new Point(200, 350), new Point(50, 500));    // descending from right to left
        Line l4 = new Line(new Point(450, 500), new Point(300, 350));   // ascending from right to left
        Line l5 = new Line(new Point(650, 50), new Point(650, 200));    // vertical (from down to top)
        Line l6 = new Line(new Point(650, 350), new Point(650, 500));   // vertical (from top to down)
        Line l7 = new Line(new Point(50, 550), new Point(300, 550));    // horizontal left
        Line l8 = new Line(new Point(400, 550), new Point(650, 550)); 
        
        liner.drawLine(l1);
        liner.drawLine(l2);
        liner.drawLine(l3);
        liner.drawLine(l4);
        liner.drawLine(l5);
        liner.drawLine(l6);
        liner.drawLine(l7);
        liner.drawLine(l8);
    }
    
    /**
     * Method to repaint all the polygons that were in the panel, used when is 
     * been drawn another polygon, that needs to clean the provisional polygon
     * @param panel 
     */
    public void repaint(Panel panel){
        for(int i=0; i<actualP.size(); i++){
            polygonR.rasterize(actualP.get(i));
            if(actualP.get(i) == poly2){
                ScanLineFill f = new ScanLineFill(poly2);
                f.fill((RasterBI)panel.getRaster(), 0XFFFF00, 0xFFFFFF); 
            }
            if(actualP.get(i).isFill()){
                int c = actualP.get(i).getColor();
                ScanLineFill f = new ScanLineFill(actualP.get(i));
                f.fill((RasterBI)panel.getRaster(), c, 0xFFFFFF);
            }
                
        }
    }
    
    /**
     * Method to draw the initial polygons and also to call all the methods
     * that make sure to change is some key is pressed
     * @param panel 
     */
    public void drawPolyg(Panel panel){
        //to check in evey step
        clear(panel);
        fillP(panel);
        clippingP(panel);
        repaint(panel);
        
        poly3 = methodDraw(panel);

        if(!painted){
            poly1 = new Polygon();
            
            poly1.addMore(  new Point(50, 50),
                new Point(250, 50),
                new Point(50, 180));
            
            polygonR.rasterize(poly1);

            poly2 = new Polygon();
            
            poly2.addMore( new Point(300, 50),
                new Point(350, 150),
                new Point(380, 90),
                new Point(500, 110),
                new Point(370, 20),
                new Point(450, 200)
            );
            
            polygonR.rasterize(poly2);
        
            ScanLineFill f = new ScanLineFill(poly2);
            f.fill((RasterBI)panel.getRaster(), 0XFFFF00, 0xFFFFFF); 
            actualP.add(poly1);
            actualP.add(poly2);
            painted = true;
        }
    }
    
    /**
     * Method to check if there must be a change in the window, because some mode
     * is active, so it eliminates all that was before, creates the necesary
     * instances and calls there methods 
     * @param window the one that is going to be modificated
     * @param panel the one that is going to be modificated
     */
    public void changeMode(Window window, Panel panel){
        
        if(modeLine){
            for (java.awt.event.MouseListener ml : panel.getMouseListeners()) panel.removeMouseListener(ml);
            for (java.awt.event.MouseMotionListener mml : panel.getMouseMotionListeners()) panel.removeMouseMotionListener(mml);
            for (java.awt.event.KeyListener kl : panel.getKeyListeners()) {panel.removeKeyListener(kl);}
            
            clear(panel);
            
            panel.getRaster().clear();
            
            liner = new DDA(panel.getRaster());
            x1 = x2 = -1;
            
            drawLine(panel);
        }
        
        if(modePolyg){
            for (java.awt.event.MouseListener ml : panel.getMouseListeners()) panel.removeMouseListener(ml);
            for (java.awt.event.MouseMotionListener mml : panel.getMouseMotionListeners()) panel.removeMouseMotionListener(mml);
            for (java.awt.event.KeyListener kl : panel.getKeyListeners()) {panel.removeKeyListener(kl);}
            
            x1 = x2 = -1;
            panel.getRaster().clear();
            liner = new DDA(panel.getRaster());
            
            polygonR = new PolygonRaster(liner);

            drawPolyg(panel);
        }
    }
    
    /**
     * Method that fills the polygon, that has been selected, with the mode of
     * fill that is when you press the f key, and to go out that is with the key 
     * E and eliminates all the methods of before. Also the key A changes the color.
     * When is filled is searched wich polygon is and edit his values
     * @param panel the one is going to be modificated 
     */
    public void fillP(Panel panel){
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        int[] colors = {0x000000, 0xff0000, 0x00ff00, 0x0000ff, 0xFFFF00};
        int index[] = {0};
        panel.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_A){
                index[0] = (index[0]+1) % colors.length;
                IO.println("FILL COLOR CHANGE");
            }
        }});
        
        panel.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_F){
                
                for (java.awt.event.MouseListener ml : panel.getMouseListeners()) panel.removeMouseListener(ml);
                for (java.awt.event.MouseMotionListener mml : panel.getMouseMotionListeners()) panel.removeMouseMotionListener(mml);
                
                IO.println("FILL MODE");
                panel.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent e){
                    int x = e.getX();
                    int y = e.getY();
                
                    SeedFill seed = new SeedFill();
                    seed.fill((RasterBI)panel.getRaster(), x, y, colors[index[0]]);
                    Point a = new Point(x,y);
                    for(int i=0; i<actualP.size(); i++){
                        List<Line> edges = actualP.get(i).toEdges();
                        boolean isP = true;
                        for(int j=0; j<edges.size(); j++){
                            if(!edges.get(j).isInside(a)){
                                isP=false;
                            }
                        }
                        if(isP)
                            actualP.get(i).isFill(colors[index[0]]);
                    }
                    index[0] = (index[0]+1) % colors.length;
                    }
                });
                }
            }
        });
        
        panel.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_E){
                for (java.awt.event.MouseListener ml : panel.getMouseListeners()) panel.removeMouseListener(ml);
                for (java.awt.event.MouseMotionListener mml : panel.getMouseMotionListeners()) panel.removeMouseMotionListener(mml);
                for (java.awt.event.KeyListener kl : panel.getKeyListeners()) {panel.removeKeyListener(kl);}
                IO.println("OUT OF FILL MODE");
                drawPolyg(panel);
            }
            }
        });
    }

    /**
     * Method that has all the override methods for drawing a polygon with 
     * clicking the mouse 
     * @param panel the one is going to be modificated
     * @return the polygon that has been drawn
     */
    public Polygon methodDraw(Panel panel){
            panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                
                if(x1 == -1){
                    //coge primer click
                    poly3 = new Polygon();
                    x1 = e.getX();
                    y1 = e.getY();
                    dibujando = true;
                    Point a = new Point(x1, y1);
                    poly3.add(a); //añado el primer punto
                    
                    //copia el raster original para cuando movamos el raton
                    BufferedImage original = panel.getRaster().getImage();
                    copia = new BufferedImage(
                        original.getWidth(),
                        original.getHeight(),
                        BufferedImage.TYPE_INT_RGB
                    );

                    Graphics g = copia.getGraphics();
                    g.drawImage(original, 0, 0, null);
                    g.dispose();
                    repaint(panel);
                }else{
                    //coge el segundo punto y dibuja la línea ofical
                    x2 = e.getX();
                    y2 = e.getY();
                    Point b = new Point(x2, y2);
                    poly3.add(b);
                    
                    panel.getRaster().clear();
                    for(int i=0; i<actualP.size(); i++){
                        polygonR.rasterize(actualP.get(i));
                    }
                    
                    polygonR.rasterNotEnd(poly3);
                                        
                    BufferedImage original = panel.getRaster().getImage();

                    copia = new BufferedImage(
                        original.getWidth(),
                        original.getHeight(),
                        BufferedImage.TYPE_INT_RGB
                    );

                    Graphics g = copia.getGraphics();
                    g.drawImage(original, 0, 0, null);
                    g.dispose();
                    
                    x1 = x2;
                    y1 = y2;
                    repaint(panel);
                    panel.repaint();
                }
            }
        });
        
        panel.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                
                //se está moviendo
                if(x1 != -1 && dibujando){
                    x2 = e.getX();
                    y2 = e.getY();                  
                
                //recuperamos el raster original
                BufferedImage original = panel.getRaster().getImage();

                Graphics g = original.getGraphics();
                g.drawImage(copia, 0, 0, null);
                g.dispose();

                if(poly3.size() < 3)
                    liner.drawLine(x1, y1, x2, y2);
                
                Polygon poly4 = new Polygon(poly3);
                Point a = new Point(x2, y2);
                poly4.add(a);
                //posible poligono
                polygonR.rasterize(poly4);
                //lo borra por si te vuelves a mover
                repaint(panel);
                panel.repaint();
                }
            }
        });

        panel.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER && dibujando){
                   dibujando = false;
                   x1 = -1;
                   y1 = -1;
                   //al pulsar enter se acaba el polígono en el punto en el que esté el cursor
                   java.awt.Point p = panel.getMousePosition();
                   int x = p.x;
                   int y = p.y;
                   
                   Point a = new Point(x,y);
                   poly3.add(a);
                   polygonR.rasterize(poly3);
                   panel.repaint();
                   
                   actualP.add(poly3);
                   repaint(panel);
                }
            }
        });
        return poly3;
    }
    
    /**
     * Method to clip to polygons, the last to ones drawn in the panel and after 
     * calling the method for clipping, and then are eliminated both polygons 
     * used for the clipping and is added the new polygon, the one as a result 
     * of the clipping
     * @param panel 
     */
    public void clippingP(Panel panel){
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        
        panel.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_N){
                
                for (java.awt.event.MouseListener ml : panel.getMouseListeners()) panel.removeMouseListener(ml);
                for (java.awt.event.MouseMotionListener mml : panel.getMouseMotionListeners()) panel.removeMouseMotionListener(mml);
                for (java.awt.event.KeyListener kl : panel.getKeyListeners()) {panel.removeKeyListener(kl);}
                
                Polygon clipping=null, originalP=null;
                originalP=actualP.get(actualP.size()-2);
                clipping=actualP.getLast();
         
                SutherlandHodgam methodP = new SutherlandHodgam();
                Polygon newP = methodP.clip(originalP, clipping);
                actualP.add(newP);
                ArrayList<Polygon> aux = new ArrayList<>();
                for(int i=0; i<actualP.size(); i++){
                        if(actualP.get(i) != originalP && actualP.get(i) != clipping)
                            aux.add(actualP.get(i));
                    }
                actualP = aux;
                panel.getRaster().clear();
                for(int i=0; i<actualP.size(); i++){
                            polygonR.rasterize(actualP.get(i));
                    }

                drawPolyg(panel);
                }
            }
        });
        
    }
    
    /**
     * Method that when the c key is pressed the panel is cleaned
     * @param panel 
     */
    private void clear(Panel panel){
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        
        panel.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_C){
                    IO.println("PANEL CLEAN");
                    panel.getRaster().clear();
                    actualP.clear();
                }
            }
        });
    }
}
