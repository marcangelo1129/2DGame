/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.secondary;

import object.guns.*;
import java.awt.Point;
import javax.imageio.ImageIO;

/**
 *
 * @author Dangerouze
 */
public class Gun_deagle extends WeaponObject {
    
    public Gun_deagle()
    {
        name = "DESERT EAGLE";
        
        //Weapon Attributes
        damage = 250;
        recoil = 10;
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 360;//88
        
        reloadTime = 1300;
        
        //Ammo
        maxAmmoPerClip = 7;
        ammoInClipRemaining = maxAmmoPerClip;
        secondary = true;
        
        //Weapon Image
        weaponWidth = 121;
        weaponHeight = 75;
        centerX = 0;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 10;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 58;
        UIFireRate = 24;
        UIAccuracy = 59;
        UIPenetration = 20;
        UIReloadSpeed = 63;
        detailText = "SEMI-AUTOMATIC";
        weaponCost = 1150;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_deagle.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/deagle.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_deagle.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
