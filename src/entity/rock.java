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
public class rock extends Projectile {

    
    
    public rock ()
    {
        name = "rock";
        speed = 5;//24
        width = 16;
        height = 16;
        centerX = 0;
        centerY = 3;
        alive = true;
        solidArea = new Rectangle(0,0,10,10);
        solidAreaOffset = new Point(0,0);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/enemies/rock.png"));
            image = uTool.scaleImage(image, (int) (width), (int) (height));
        }catch(Exception ex) {ex.printStackTrace();}
    }
}
