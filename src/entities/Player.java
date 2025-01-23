package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInput;

public class Player extends Entity {

    GamePanel gp;
    KeyInput ki;
    public int boost_speed;

    //setting the coordinates of the player
    public final int player_x , player_y; 

    public Player(GamePanel gp ,KeyInput ki){
        
        this.gp=gp;
        this.ki=ki;

        player_x = GamePanel.max_tile_columns/2*GamePanel.tile_side - GamePanel.tile_side/2;
        player_y = GamePanel.max_tile_rows/2*GamePanel.tile_side - GamePanel.tile_side/2;

        world_x=GamePanel.max_tile_columns/2*GamePanel.tile_side;
        world_y=GamePanel.max_tile_rows/2*GamePanel.tile_side;
        speed=2;
        boost_speed=3;
        direction="left";

    }
    
    public void update(){
        
        if(ki.boost){
            directionUpdate(boost_speed);
        }
        else
            directionUpdate(speed);

        //updating the sprites to make some sort of animation
        spriteCounter++;
        if(spriteCounter==25){
            spriteCounter=0;
            spriteNum= (spriteNum+2)%3;
        }
    }

    private void directionUpdate(int s) {
        if(ki.movement[0]){
            direction="up";
            world_y -= s;
        }

        else if(ki.movement[1]){
            direction="down";
            world_y += s;
        }
        
        else if(ki.movement[2]){
            direction="left";
            world_x -= s;
        }
        
        else if(ki.movement[3]){
            direction="right";
            world_x += s;
        }
    }

    public void draw(Graphics2D g2D){
        
        //rendering the player
        BufferedImage image= null;

        img_path = "/player/" + direction + Integer.toString(spriteNum+1) + ".png";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        g2D.drawImage(image , player_x ,player_y ,null);//null is the ImageObserver //if we want to draw to different size we can specify that as well

    }
}
