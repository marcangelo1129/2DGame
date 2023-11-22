/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author geps
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/map2.txt", false);
    }
    public void getTileImage()
    {
        setup(0, "grass", false);
        setup(1, "stone", true);
        setup(2, "water", true);
        setup(3, "road", false);
    }
    public void setup(int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();
        
        try
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            
        }catch(IOException ex){ex.printStackTrace();}
    }
        public void loadMap(String filepath, boolean debugMode)
        {
            InputStream is;
            try
            {
                if (debugMode)
                {
                    is = new FileInputStream(filepath);
                }
                else
                {
                    is = getClass().getResourceAsStream(filepath);
                }
                
                
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                
                int col = 0;
                int row = 0;
                
                while(col < gp.maxWorldCol && row < gp.maxWorldRow)
                {
                    String line = br.readLine();
                    
                    while (col < gp.maxWorldCol)
                    {
                        String numbers[] = line.split(" ");
                        
                        int num = Integer.parseInt(numbers[col]);
                        
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if(col == gp.maxWorldCol)
                    {
                        col = 0;
                        row++;
                    }
                }
                br.close();
            }
            catch(IOException e)
            {
                
            }
        }
        public void draw(Graphics2D g2)
        {
         int worldCol = 0;
         int worldRow = 0;
         
         while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
         {
             int tileNum = mapTileNum[worldCol][worldRow];
             
             int worldX = worldCol * gp.tileSize;
             int worldY = worldRow * gp.tileSize;
             int screenX = worldX - gp.player.worldX + gp.player.screenX;
             int screenY = worldY - gp.player.worldY + gp.player.screenY;
             
             g2.drawImage(tile[tileNum].image,screenX ,screenY,null);
             worldCol++;
             
             if(worldCol == gp.maxWorldCol)
             {
                 worldCol = 0;
                 worldRow++;
             }
         }
        }
                
}
