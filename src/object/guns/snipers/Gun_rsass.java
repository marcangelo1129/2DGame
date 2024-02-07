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
public class Gun_rsass extends WeaponObject {
    
    public Gun_rsass()
    {
        name = "RSASS";
        
        //Weapon Attributes
        damage = 325;
        fireRate = 250;
        recoil = 6;
        penetration = 3;
        bulletSpread = 0;
        bulletCount = 1;
        reloadTime = 1800;
        
        //Ammo
        maxAmmoPerClip = 10;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 80;
        ammoBoxIncrement = 25;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 405;
        weaponHeight = 123;
        centerX = 20;
        centerY = 12;
        intensity = 10;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 15;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 67;
        UIFireRate = 32;
        UIAccuracy = 76;
        UIPenetration = 60;
        UIReloadSpeed = 32;
        detailText = "SEMI-AUTOMATIC";
        weaponCost = 3000;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_rsass.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/rsass.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_rsass.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
