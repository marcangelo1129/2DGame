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
public class bench_ORIENTED extends SuperObject {
    Rectangle size;
    
    public bench_ORIENTED(GamePanel gp, int worldX, int worldY, String orientation)
    {
        //object-specific variable assignment
        switch (orientation) {
            case "front":
                solidArea = new Rectangle(0,0,22,8);//Collision offset (x,y) and size (width,height) no need to edit if collision is false
                size = new Rectangle(27, 22);//image size
                name = "bench_front";//image name
                break;
            case "back":
                solidArea = new Rectangle(0,0,22,8);//Collision offset (x,y) and size (width,height) no need to edit if collision is false
                size = new Rectangle(27, 22);//image size
                name = "bench_back";//image name
                break;
            case "left":
                solidArea = new Rectangle(0,0,19,18);//Collision offset (x,y) and size (width,height) no need to edit if collision is false
                size = new Rectangle(24, 26);//image size
                name = "bench_left";//image name
                break;
            case "right":
                solidArea = new Rectangle(0,0,19,18);//Collision offset (x,y) and size (width,height) no need to edit if collision is false
                size = new Rectangle(24, 26);//image size
                name = "bench_right";//image name
                break;
        }
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
