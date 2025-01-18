package main;
//importing the JPanel,Color and Dimension class

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{

    private static final int tile_side = 48; //each tile will be 48x48 pixels
    
    //maximum number of tile rows and columns that can be displayed
    final static int max_tile_rows = 22; 
    final static int max_tile_columns = 40;

    //max screen size of 1920x1056 pixels
    private static final int max_screen_width = tile_side*max_tile_columns;
    private static final int max_screen_height = tile_side*max_tile_rows;
    
    //setting the default x and y coordinates
    private int character_x = 240;
    private int character_y = 240;

    private static final int character_speed = 4;

    private static final int max_fps = 144;

    KeyInput key_input = new KeyInput();
    

    public GamePanel(){
    
        //setting the dimensions and background color of the panel
        this.setPreferredSize(new Dimension(max_screen_width,max_screen_height));
        this.setBackground(Color.BLACK);

        //helps improve performance by rendering the on screen drawables in an offscreen buffer 
        this.setDoubleBuffered(true);

        //setting the focus of the panel to take input from keyboard
        this.setFocusable(true);
        this.requestFocusInWindow();

        //adding the key listener
        this.addKeyListener(key_input);
    }

    //creating a thread to make the loop of the game
    Thread game_thread;
    public void startGameThread(){
        
        //passing the class to instantiate the thread
        game_thread = new Thread(this);
        
        //automatically calls the run method which contains and runs the game loop
        game_thread.start();
        
    }
    
    @Override
    public void run() {

        double time_interval = 1000/max_fps;
        long last_time = System.currentTimeMillis();

        double delta_time = 0;
        long current_time;

        while(game_thread != null){
        
            current_time = System.currentTimeMillis();

            delta_time += (current_time - last_time)/time_interval;
            last_time = current_time;

            if(delta_time>=1){
                //updating the screen w.r.t. previous inputs
                updateScreen();

                //rendering the updated screen
                repaint(); //for some ungodly reason this is how the paintComponent method is called
                delta_time--;
            }
        }
    }

    public void updateScreen(){

        if(key_input.movement[0]){
            character_y -= character_speed;
        }

        if(key_input.movement[1]){
            character_y += character_speed;
        }
        
        if(key_input.movement[2]){
            character_x -= character_speed;
        }
        
        if(key_input.movement[3]){
            character_x += character_speed;
        }

    }

    //one of in built methods of JPanel to draw components
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        
        //subclass of Graphics class to provide better control over coordinates,geometry 
        Graphics2D g2D = (Graphics2D)g ;

        g2D.setColor(Color.WHITE);
        g2D.fillRect(character_x,character_y,tile_side,tile_side);
        
        //dispose unused resources to make more memory efficient
        g2D.dispose();

    
    }

}
