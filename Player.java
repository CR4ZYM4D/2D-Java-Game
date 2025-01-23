package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInput;

public class Player extends Entity {

    GamePanel gp;
    KeyInput ki;

   public Player(GamePanel gp ,KeyInput ki){
        
        this.gp=gp;
        this.ki=ki;
        world_x=240;
        world_y=240;
        speed=3;
        direction="left";

        // ** VERY IMPORTANT DO NOT UNCOMMENT OR ELSE INPUT BREAKS. THIS SHOULD NOT HAPPEN BUT FOR SOME REASON DOES **
        //getPlayerImage();

    }
    
    //importing the player images 
    public void getPlayerImage(){

        try {
            
//            up1=ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
//            up2=ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
//            down1=ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
//            down2=ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(){
        
            if(ki.movement[0]){
                direction="up";
                world_y -= speed;
            }

            else if(ki.movement[1]){
                direction="down";
                world_y += speed;
            }
        
            else if(ki.movement[2]){
                direction="left";
                world_x -= speed;
            }
        
            else if(ki.movement[3]){
                direction="right";
                world_x += speed;
            }

        //updating the sprites to make some sort of animation
            spriteCounter++;
            if(spriteCounter==50){
                spriteCounter=0;
                spriteNum= (spriteNum+1)%2;
            }
        

    }

    public void draw(Graphics2D g2D){
        getPlayerImage();
        // g2D.setColor(Color.WHITE);
        // g2D.fillRect(x,y,GamePanel.tile_side,GamePanel.tile_side);

        //rendering the player
        BufferedImage image= null;

        switch(direction){

            case "up": 
            if(spriteNum==1)
                image=up1;
            else
                image=up2;
            break;
            case "down":            
            if(spriteNum==1)
                image=down1;
            else
                image=down2;
            break;
            case "left":
            if(spriteNum==1)
                image=left1;
            else
                image=left2;
            break;
            case "right":
            if(spriteNum==1)
                image=right1;
            else
                image=right2;
            break;

        }
        // if(spriteNum==1){
        //     g2D.setColor(Color.WHITE);
        //     g2D.fillRect(x,y,GamePanel.tile_side,GamePanel.tile_side);
        // }
        // else{
        //     g2D.setColor(Color.RED);
        //     g2D.fillRect(x,y,GamePanel.tile_side,GamePanel.tile_side);
        // }
        g2D.drawImage(image , world_x ,world_y ,null);//null is the ImageObserver //if we want to draw to different size we can specify that as well



    }
}
