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
public class Gun_smaw extends WeaponObject {
    
    public Gun_smaw()
    {
        name = "MK 153 SMAW";
        
        //Weapon Attributes
        damage = 1500;
        recoil = 0;
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 700;
        fireMode = "rocket";
        
        reloadTime = 3000;
        
        //Ammo
        maxAmmoPerClip = 1;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 1000;//2
        ammoBoxIncrement = 0;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 448;
        weaponHeight = 111;
        centerX = 44;
        centerY = 8;
        intensity = 20;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 50;
        weaponScaling = 0.20f;
        UIScaling = 0.35f;
        
        //UI variables
        UIDamage = 100;
        UIFireRate = 40;
        UIAccuracy = 80;
        UIPenetration = 100;
        UIReloadSpeed = 4;
        detailText = "FREE-FIRE ROCKET LAUNCHER";
        weaponCost = 9999;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_smaw.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/smaw.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_smaw.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
