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
public class bullet_Trail extends Projectile {

    
    
    public bullet_Trail ()
    {
        name = "bullet_trail";
        speed = 1;
        width = 163;
        height = 12;
        centerX = 0;
        centerY = 3;
        alive = true;
        solidArea = new Rectangle(0,0,0,0);
        solidAreaOffset = new Point(0,0);
        timer = 1000;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bullet_trail.png"));
            image = uTool.scaleImage(image, (int) (width*scaling), (int) (height*scaling));
        }catch(Exception ex) {ex.printStackTrace();}
    }
}
