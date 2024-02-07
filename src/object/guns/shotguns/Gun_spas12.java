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
public class Gun_spas12 extends WeaponObject {
    
    public Gun_spas12()
    {
        name = "SPAS-12";
        
        //Weapon Attributes
        damage = 35;
        fireRate = 300;
        recoil = 0;
        penetration = 1;
        bulletSpread = 25;
        bulletCount = 12;
        reloadTime = 500;
        clipReload = false;
        
        //Ammo
        maxAmmoPerClip = 8;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 80;
        ammoBoxIncrement = 32;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 250;
        weaponHeight = 100;
        centerX = 6;
        centerY = 13;
        intensity = 10;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 15;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 71;
        UIFireRate = 28;
        UIAccuracy = 24;
        UIPenetration = 20;
        UIReloadSpeed = 89;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 1800;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_spas12.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/spas12.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_spas12.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
