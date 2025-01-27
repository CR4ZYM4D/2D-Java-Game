package tiles;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile tiles[]; //array to store the different types of tiles like water,wall etc.
    int[][] map_tile_num;

    public TileManager(GamePanel gp){

        this.gp = gp;
        tiles=new Tile[10];
        map_tile_num= new int[GamePanel.max_world_rows][GamePanel.max_world_columns];
        getTileImage();
        loadMap();
    }

    //loading the map
    public void loadMap(){

        try {
            
            InputStream i = getClass().getResourceAsStream("/Maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(i));
            int col=0;
            int row=0;
            while(col < GamePanel.max_world_columns  && row < GamePanel.max_world_rows){

                String line = br.readLine();
                while(col < GamePanel.max_world_columns){

                    String numbers[] = line.split(" ");
                    map_tile_num[row][col] = Integer.parseInt(numbers[col++]);

                } 
                row++;
                col=0;

            }
            br.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //loading the tile images into the array
    public void getTileImage(){

        try {
            
            tiles[0]=new Tile();
            tiles[0].tile_image=ImageIO.read(getClass().getResourceAsStream("/tiles/Grass3.png"));

            tiles[1]=new Tile();
            tiles[1].tile_image=ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tiles[2]=new Tile();
            tiles[2].tile_image=ImageIO.read(getClass().getResourceAsStream("/tiles/Grass2.png"));


        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2D){
        int world_col = 0;
        int world_row=0;

        while(world_col<GamePanel.max_world_columns && world_row<GamePanel.max_world_rows){

            int num= map_tile_num[world_row][world_col];
            
            //calculating global position of a tile
            int world_x = world_col * GamePanel.tile_side;
            int world_y = world_row * GamePanel.tile_side;

            //calculating relative position of point w.r.t. player
            int screen_x = world_x - gp.player.world_x + gp.player.player_x;
            int screen_y = world_y - gp.player.world_y + gp.player.player_y;

            if(isInBounds(world_x,world_y)) //function for optimization
            g2D.drawImage(tiles[num].tile_image, screen_x , screen_y , GamePanel.tile_side , GamePanel.tile_side ,null);
            world_col++;
            
            if(world_col==GamePanel.max_world_columns){
                world_col=0;
                
                world_row++;
                
            }

        }
    
    }

    public boolean isInBounds(int world_x , int world_y){

        return (world_x + GamePanel.tile_side> gp.player.world_x - gp.player.player_x && world_x -GamePanel.tile_side< gp.player.world_x+gp.player.player_x &&
        world_y + GamePanel.tile_side > gp.player.world_y - gp.player.player_y && world_y - GamePanel.tile_side < gp.player.world_y + gp.player.player_y);

    }

}
