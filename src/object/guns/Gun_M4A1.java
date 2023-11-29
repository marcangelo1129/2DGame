/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns;

import java.awt.Point;
import javax.imageio.ImageIO;

/**
 *
 * @author Dangerouze
 */
public class Gun_M4A1 extends WeaponObject {
    
    public Gun_M4A1()
    {
        name = "M4A1";
        MaxAmmoPerClip = 30;
        AmmoinClipRemaining = MaxAmmoPerClip;
        AmmoRemaining = 300;
        ammoBoxIncrement = 120;
        MaxAmmo = AmmoRemaining;
        fireRate = 88;//88
        damage = 28;
        reloadTime = 1300;
        centerX = 5;
        centerY = 15;
        bulletX = 28;
        bulletY = 22;
        defaultCenterX = 5;
        weaponWidth = 353;
        weaponHeight = 124;
        recoil = 4;
        penetration = 2;
        muzzleLoc = new Point(85,26);
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/M4A1/wpn_m4a1.png"));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            sound = getClass().getResource("/weaponFX/wpn_fire_m4a1.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
