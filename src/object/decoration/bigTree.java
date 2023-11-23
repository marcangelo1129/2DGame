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
public class bigTree extends SuperObject {
    GamePanel gp;
    Rectangle size;
    Rectangle sizeTop;
    
    public bigTree(GamePanel gp)
    {
        solidArea = new Rectangle(96,48);//Collision Size
        collision = true;
        size = new Rectangle(solidArea.width, solidArea.height);//bottom image size. change if size is not same as the collision size.
        sizeTop = new Rectangle(96, 96);//top image size
        name = "bigtree_bottom";//bottom image name
        nameTop = "bigtree_top";//top image name
        imageLoad();//ignore this
    }
    
    public void imageLoad()//do not touch code below
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/decoration/"+name+".png"));
            image = uTool.scaleImage(image, size.width, size.height);
            imageTop = ImageIO.read(getClass().getResourceAsStream("/objects/decoration/"+nameTop+".png"));
            imageTop = uTool.scaleImage(imageTop, sizeTop.width, sizeTop.height);
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
