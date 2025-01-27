package entities;
import java.awt.image.BufferedImage;
public class Entity {

    public int world_x, world_y,speed;
    BufferedImage image;
    public String direction , img_path;

    //will be used to update player Sprites
    public int spriteCounter=0;
    public int spriteNum=1;
    

}
