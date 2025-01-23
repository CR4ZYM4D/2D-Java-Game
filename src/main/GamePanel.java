package main;
//importing the JPanel,Color and Dimension class

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import entities.Player;
import tiles.TileManager;


public class GamePanel extends JPanel implements Runnable{

    // **SCREEN SETTINGS**

    public static final int tile_side = 48; //each tile will be 48x48 pixels
    
    //maximum number of tile rows and columns that can be displayed
    public final static int max_tile_rows = 18; 
    public final static int max_tile_columns = 32;

    //max screen size of 1920x1056 pixels
    private static final int max_screen_width = tile_side*max_tile_columns;
    private static final int max_screen_height = tile_side*max_tile_rows;
   
    private static final int max_fps = 120;

    // **WORLD SETTINGS**
    public static final int max_world_rows=22;
    public static final int max_world_columns=40;

    TileManager tm ;

    KeyInput key_input = new KeyInput();

    public Player player = new Player(this,key_input);

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

        player.update();

    }

    //one of in built methods of JPanel to draw components
    public void paintComponent(Graphics g){
        tm = new TileManager(this);
        super.paintComponent(g);
        
        //subclass of Graphics class to provide better control over coordinates,geometry 
        Graphics2D g2D = (Graphics2D)g ;

        tm.draw(g2D);
        player.draw(g2D);

        
        //dispose unused resources to make more memory efficient
        g2D.dispose();

    
    }

}
