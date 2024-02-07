/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.snipers;

import object.guns.shotguns.*;
import java.awt.Point;
import javax.imageio.ImageIO;
import object.guns.WeaponObject;

/**
 *
 * @author Dangerouze
 */
public class Gun_m40a3 extends WeaponObject {
    
    public Gun_m40a3()
    {
        name = "M40A3";
        
        //Weapon Attributes
        damage = 1300;
        fireRate = 950;
        recoil = 4;
        penetration = 3;
        bulletSpread = 0;
        bulletCount = 1;
        reloadTime = 400;
        clipReload = false;
        
        //Ammo
        maxAmmoPerClip = 5;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 40;
        ammoBoxIncrement = 15;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 429;
        weaponHeight = 101;
        centerX = 22;
        centerY = 12;
        intensity = 30;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 30;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 92;
        UIFireRate = 16;
        UIAccuracy = 88;
        UIPenetration = 60;
        UIReloadSpeed = 100;
        detailText = "BOLT-ACTION";
        weaponCost = 1600;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_m40a3.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/m40a3.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_m40a3.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
