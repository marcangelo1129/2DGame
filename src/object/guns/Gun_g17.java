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
public class Gun_g17 extends WeaponObject {
    
    public Gun_g17()
    {
        name = "g17";
        
        //Weapon Attributes
        damage = 35;
        recoil = 12;
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 200;//88
        
        reloadTime = 800;
        
        //Ammo
        maxAmmoPerClip = 12;
        ammoInClipRemaining = maxAmmoPerClip;
        secondary = true;
        
        //Weapon Image
        weaponWidth = 98;
        weaponHeight = 69;
        centerX = 0;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 7;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_g17.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_g17.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
