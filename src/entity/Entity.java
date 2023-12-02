/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Dangerouze
 */
public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage[] left = new BufferedImage[10];
    public BufferedImage[] right = new BufferedImage[10];;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public int hp;
    
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean collisionSide1 = false;
    public boolean collisionSide2 = false;
    
}
