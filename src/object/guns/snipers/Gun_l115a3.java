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
public class Gun_l115a3 extends WeaponObject {
    
    public Gun_l115a3()
    {
        name = "L115A3";
        
        //Weapon Attributes
        damage = 3000;
        fireRate = 1200;
        recoil = 2;
        penetration = 4;
        bulletSpread = 0;
        bulletCount = 1;
        reloadTime = 1800;
        
        //Ammo
        maxAmmoPerClip = 5;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 40;
        ammoBoxIncrement = 15;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 448;
        weaponHeight = 94;
        centerX = 25;
        centerY = 10;
        intensity = 40;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 30;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 96;
        UIFireRate = 8;
        UIAccuracy = 94;
        UIPenetration = 80;
        UIReloadSpeed = 32;
        detailText = "BOLT-ACTION";
        weaponCost = 4500;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_l115a3.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/l115a3.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_l115a3.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
