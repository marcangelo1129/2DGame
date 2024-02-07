/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.smg;

import object.guns.rifles.*;
import java.awt.Point;
import javax.imageio.ImageIO;
import object.guns.WeaponObject;

/**
 *
 * @author Dangerouze
 */
public class Gun_p90 extends WeaponObject {
    
    public Gun_p90()
    {
        name = "P90";
        
        //Weapon Attributes
        damage = 38;
        recoil = 5;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 68;//88
        
        reloadTime = 1500;
        
        //Ammo
        maxAmmoPerClip = 50;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 500;
        ammoBoxIncrement = 200;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 212;
        weaponHeight = 105;
        centerX = 17;
        centerY = 15;
        defaultCenterX = centerX;
        visualRecoil = 4;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 13;
        UIFireRate = 88;
        UIAccuracy = 82;
        UIPenetration = 40;
        UIReloadSpeed = 47;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 1100;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_p90.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/p90.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_p90.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
