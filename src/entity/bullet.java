/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Point;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class bullet extends Projectile {

    
    
    public bullet (int worldX, int worldY, double angle)
    {
        speed = 40;//40
        width = 163;
        height = 12;
        centerX = 0;
        centerY = 3;
        solidArea = new Rectangle(0,0,10,10);
        solidAreaOffset = new Point(80,80);
        this.worldX = worldX;
        this.worldY = worldY;
        this.angle = angle;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bullet.png"));
            image = uTool.scaleImage(image, (int) (width*scaling), (int) (height*scaling));
        }catch(Exception ex) {ex.printStackTrace();}
    }
}
