/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.sound.sampled.Clip;
import main.Sound;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class WeaponObject {
    public BufferedImage image;
    public BufferedImage UIimage;
    public URL sound;
    public Point muzzleLoc;
    public String name;
    
    //weapon stats
    public int MaxAmmoPerClip;
    public int AmmoinClipRemaining;
    public int AmmoRemaining;
    public int MaxAmmo;
    public int fireRate;
    public int damage;
    public int recoil;
    public int reloadTime;
    
    public int centerX;
    public int centerY;
    public int defaultCenterX;
    public int weaponWidth;
    public int weaponHeight;
    public double weaponScaling = 0.25f;
    public double UIScaling = 0.50f;
    public boolean reload = false;
    UtilityTool uTool = new UtilityTool();
}
