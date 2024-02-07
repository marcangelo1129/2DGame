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
public class Gun_uspm extends WeaponObject {
    
    public Gun_uspm()
    {
        name = "USP MATCH";
        
        //Weapon Attributes
        damage = 40;
        recoil = 16;
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 90;//88
        
        reloadTime = 1000;
        
        //Ammo
        maxAmmoPerClip = 12;
        ammoInClipRemaining = maxAmmoPerClip;
        secondary = true;
        
        //Weapon Image
        weaponWidth = 107;
        weaponHeight = 81;
        centerX = 0;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 8;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 17;
        UIFireRate = 64;
        UIAccuracy = 35;
        UIPenetration = 20;
        UIReloadSpeed = 79;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 600;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_uspm.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/uspm.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_uspm.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
