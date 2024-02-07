/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.decoration;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import object.SuperObject;

/**
 *
 * @author Dangerouze
 */
public class traffic_cone extends SuperObject {
    Rectangle size;
    
    public traffic_cone(GamePanel gp, int worldX, int worldY)
    {
        //object-specific variable assignment
        solidArea = new Rectangle(0,0,16,16);//Collision offset (x,y) and size (width,height) no need to edit if collision is false
        size = new Rectangle(16, 16);//image size
        name = "traffic_cone";//image name
        collision = true;
        bulletCollision = false;
        
        //constructor variable assignment. Do not edit
        this.worldX = worldX * gp.tileSize;
        this.worldY = worldY * gp.tileSize;
        
        imageLoad(gp);//ignore this
    }
    
    public void imageLoad(GamePanel gp)//do not touch code below
    {
        solidArea.width = solidArea.width * gp.tileScaling;
        solidArea.height = solidArea.height * gp.tileScaling;
        size.width = size.width * gp.tileScaling;
        size.height = size.height * gp.tileScaling;
        solidArea.x = ((size.width - solidArea.width) / 2) + ((solidArea.x * gp.tileScaling) / 2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/decoration/"+name+".png"));
            image = uTool.scaleImage(image, size.width, size.height);
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
