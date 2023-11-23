/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.decoration;

import java.awt.Graphics2D;
import main.GamePanel;
import object.SuperObject;

/**
 *
 * @author Dangerouze
 */
public class decorationPlacement {
    GamePanel gp;
    public decorationPlacement(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setDecoration()
    {
        gp.objDeco[0] = new object.decoration.bigTree(gp);//decoration's class
        gp.objDeco[0].worldX = 27 * gp.tileSize;//x location of this decoration
        gp.objDeco[0].worldY = 18 * gp.tileSize;//y location of this decoration
        
        gp.objDeco[1] = new object.decoration.bigTree(gp);
        gp.objDeco[1].worldX = 29 * gp.tileSize;
        gp.objDeco[1].worldY = 18 * gp.tileSize;
        
        // to add more decoration, simply copy three codes from the above, change its index
        // ( number inside ---> [ ] ) and change the class name.
    }
    
    
    
}
