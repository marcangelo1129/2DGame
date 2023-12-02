/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class OBJ_M4A1 extends SuperObject {
    
    GamePanel gp;
    
    public OBJ_M4A1(GamePanel gp)
    {
        solidArea = new Rectangle(0,0,48,48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        name = "M4A1";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/M4A1.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
