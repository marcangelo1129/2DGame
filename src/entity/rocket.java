/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Point;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

/**
 *
 * @author Dangerouze
 */
public class rocket extends Projectile {

    
    
    public rocket ()
    {
        name = "rocket";
        speed = 15;
        width = 174;
        height = 28;
        centerX = 0;
        centerY = 7;
        alive = true;
        solidArea = new Rectangle(50,0,10,10);
        solidAreaOffset = new Point(0,0);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/rocket.png"));
            image = uTool.scaleImage(image, (int) (width*scaling), (int) (height*scaling));
        }catch(Exception ex) {ex.printStackTrace();}
    }
}
