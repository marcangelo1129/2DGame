/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns;

import java.awt.image.BufferedImage;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class WeaponObject {
    public BufferedImage image;
    public BufferedImage UIimage;
    public String name;
    public int worldX, worldY;
    public int MaxAmmoPerClip;
    public int AmmoinClipRemaining;
    public int AmmoRemaining;
    public int MaxAmmo;
    public int fireRate;
    public int damage;
    public int centerX;
    public int centerY;
    public int weaponWidth;
    public int weaponHeight;
    public int weaponWidthUI;
    public int weaponHeightUI;
    UtilityTool uTool = new UtilityTool();
}
