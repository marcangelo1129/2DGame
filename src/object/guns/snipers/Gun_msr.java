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
public class Gun_msr extends WeaponObject {
    
    public Gun_msr()
    {
        name = "MSR";
        
        //Weapon Attributes
        damage = 8500;
        fireRate = 1400;
        recoil = 0;
        penetration = 5;
        bulletSpread = 0;
        bulletCount = 1;
        reloadTime = 2500;
        
        //Ammo
        maxAmmoPerClip = 10;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 80;
        ammoBoxIncrement = 25;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 398;
        weaponHeight = 106;
        centerX = 23;
        centerY = 12;
        intensity = 60;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 40;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 100;
        UIFireRate = 4;
        UIAccuracy = 100;
        UIPenetration = 100;
        UIReloadSpeed = 26;
        detailText = "BOLT-ACTION";
        weaponCost = 7500;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_msr.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/msr.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_msr.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
