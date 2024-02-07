/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.shotguns;

import java.awt.Point;
import javax.imageio.ImageIO;
import object.guns.WeaponObject;

/**
 *
 * @author Dangerouze
 */
public class Gun_mossberg extends WeaponObject {
    
    public Gun_mossberg()
    {
        name = "MOSSBERG";
        
        //Weapon Attributes
        damage = 40;
        fireRate = 1100;
        recoil = 0;
        penetration = 1;
        bulletSpread = 35;
        bulletCount = 12;
        reloadTime = 450;
        clipReload = false;
        
        //Ammo
        maxAmmoPerClip = 6;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 60;
        ammoBoxIncrement = 24;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 342;
        weaponHeight = 112;
        centerX = 27;
        centerY = 11;
        intensity = 20;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 30;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 75;
        UIFireRate = 12;
        UIAccuracy = 12;
        UIPenetration = 20;
        UIReloadSpeed = 95;
        detailText = "PUMP ACTION";
        weaponCost = 600;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_mossberg.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/mossberg.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_mossberg.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
