/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class OBJ_AmmoBox extends SuperObject {
    
    GamePanel gp;
    
    public OBJ_AmmoBox(GamePanel gp)
    {
        name = "AmmoBox";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ammo_box.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
