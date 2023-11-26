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
    Rectangle size;
    Rectangle sizeTop;
    
    public bigTree(GamePanel gp)
    {
        solidArea = new Rectangle(32,16);//Collision Size
        collision = true;
        size = new Rectangle(solidArea.width, solidArea.height);//bottom image size. change if size is not same as the collision size.
        sizeTop = new Rectangle(32, 32);//top image size
        name = "bigtree_bottom";//bottom image name
        nameTop = "bigtree_top";//top image name
        imageLoad();//ignore this
    }
    
    public void imageLoad()//do not touch code below
    {
        solidArea.width = solidArea.width * 3;
        solidArea.height = solidArea.height * 3;
        size.width = size.width * 3;
        size.height = size.height * 3;
        sizeTop.width = sizeTop.width * 3;
        sizeTop.height = sizeTop.height * 3;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/decoration/"+name+".png"));
            image = uTool.scaleImage(image, size.width, size.height);
            imageTop = ImageIO.read(getClass().getResourceAsStream("/objects/decoration/"+nameTop+".png"));
            imageTop = uTool.scaleImage(imageTop, sizeTop.width, sizeTop.height);
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
