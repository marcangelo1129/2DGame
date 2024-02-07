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
public class Gun_hk417 extends WeaponObject {
    
    public Gun_hk417()
    {
        name = "HK417";
        
        //Weapon Attributes
        damage = 700;
        fireRate = 220;
        recoil = 6;
        penetration = 4;
        bulletSpread = 0;
        bulletCount = 1;
        reloadTime = 1800;
        
        //Ammo
        maxAmmoPerClip = 15;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 100;
        ammoBoxIncrement = 35;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 344;
        weaponHeight = 121;
        centerX = 26;
        centerY = 13;
        intensity = 20;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 15;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 83;
        UIFireRate = 36;
        UIAccuracy = 76;
        UIPenetration = 80;
        UIReloadSpeed = 32;
        detailText = "SEMI-AUTOMATIC";
        weaponCost = 5200;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_hk417.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/hk417.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_hk417.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
