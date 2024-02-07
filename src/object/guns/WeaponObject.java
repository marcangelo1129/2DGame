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
    public BufferedImage Shopimage;
    public BufferedImage silhuetteImage;
    public URL sound;
    public String name;
    
    //weapon stats
    public int maxAmmoPerClip;
    public int ammoInClipRemaining;
    public int ammoRemaining;
    public int maxAmmo;
    public int fireRate;
    public int damage;
    public int recoil;
    public int bulletSpread;
    public int bulletCount;
    public int reloadTime;
    public int ammoBoxIncrement;
    public int penetration;
    public String fireMode = "auto";
    public boolean secondary = false;
    
    //burst mode stats
    public int burstDelay;
    public int burstCount = 0;
    
    //visual
    public int visualRecoil;
    public int centerX;
    public int centerY;
    public int defaultCenterX;
    public int weaponWidth;
    public int weaponHeight;
    public double weaponScaling;
    public double UIScaling;
    public boolean reload = false;
    public boolean clipReload = true;
    public boolean bulletTrail = false;
    public boolean screenShake = false;
    public int intensity;
    
    //Shop Variables
    public double UIDamage;
    public double UIFireRate;
    public double UIAccuracy;
    public double UIPenetration;
    public double UIReloadSpeed;
    public double shopScaling = 1f;
    public String detailText;
    public int weaponCost;
    public boolean purchased = false;
    public boolean equipped = false;
    
    public UtilityTool uTool = new UtilityTool();
}
