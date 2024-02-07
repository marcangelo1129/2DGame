/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class Projectile {
    
    public BufferedImage[] explosion = new BufferedImage[9];
    public double worldX;
    public double worldY;
    public double speed;
    public Entity user;
    public double angle;
    public int damage;
    public int penetration;
    public BufferedImage image;
    public double scaling = 0.50f;
    public Rectangle solidArea;
    public Point solidAreaOffset;
    public int width;
    public int height;
    public int centerX;
    public int centerY;
    public boolean alive;
    public String name;
    public int timer;
    public boolean bulletTrail;
    public int index;
    public String projectileType;
    public UtilityTool uTool = new UtilityTool();
    

}
