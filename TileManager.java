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
        map_tile_num= new int[GamePanel.max_tile_rows][GamePanel.max_tile_columns];
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
            while(col < GamePanel.max_tile_columns  && row < GamePanel.max_tile_rows){

                String line = br.readLine();
                while(col < GamePanel.max_tile_columns){

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
            tiles[0].tile_image=ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));

            tiles[1]=new Tile();
            tiles[1].tile_image=ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tiles[2]=new Tile();
            tiles[2].tile_image=ImageIO.read(getClass().getResourceAsStream("/tiles/EntranceWall.png"));


        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2D){
        int col = 0;
        int row=0;
        int x=0;
        int y=0;
        while(col<GamePanel.max_tile_columns && row<GamePanel.max_tile_rows){

            int num= map_tile_num[row][col];

            g2D.drawImage(tiles[num].tile_image,x,y,GamePanel.tile_side,GamePanel.tile_side,null);
            col++;
            x+=GamePanel.tile_side;
            if(col==GamePanel.max_tile_columns){
                col=0;
                x=0;
                row++;
                y+=GamePanel.tile_side;
            }

        }
        
        


    }

}
