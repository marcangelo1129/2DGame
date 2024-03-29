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
public class bullet extends Projectile {

    
    
    public bullet ()
    {
        name = "bullet";
        speed = 30;//24
        width = 163;
        height = 12;
        centerX = 0;
        centerY = 3;
        alive = true;
        solidArea = new Rectangle(50,0,10,10);
        solidAreaOffset = new Point(0,0);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bullet.png"));
            image = uTool.scaleImage(image, (int) (width*scaling), (int) (height*scaling));
        }catch(Exception ex) {ex.printStackTrace();}
    }
}
