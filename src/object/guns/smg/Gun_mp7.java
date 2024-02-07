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
public class Gun_mp7 extends WeaponObject {
    
    public Gun_mp7()
    {
        name = "MP7";
        
        //Weapon Attributes
        damage = 95;
        recoil = 10;//8
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 60;//88
        
        reloadTime = 1000;
        
        //Ammo
        maxAmmoPerClip = 40;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 400;
        ammoBoxIncrement = 160;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 254;
        weaponHeight = 139;
        centerX = 21;
        centerY = 13;
        defaultCenterX = centerX;
        visualRecoil = 5;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 29;
        UIFireRate = 96;
        UIAccuracy = 59;
        UIPenetration = 20;
        UIReloadSpeed = 79;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 2000;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_mp7.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/mp7.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_mp7.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
