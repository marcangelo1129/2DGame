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
public class Gun_jackhammer extends WeaponObject {
    
    public Gun_jackhammer()
    {
        name = "JACKHAMMER";
        
        //Weapon Attributes
        damage = 75;
        fireRate = 200;
        recoil = 0;
        penetration = 1;
        bulletSpread = 35;
        bulletCount = 12;
        reloadTime = 1400;
        
        //Ammo
        maxAmmoPerClip = 10;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 100;
        ammoBoxIncrement = 40;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 308;
        weaponHeight = 96;
        centerX = 29;
        centerY = 10;
        defaultCenterX = centerX;
        visualRecoil = 15;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 88;
        UIFireRate = 40;
        UIAccuracy = 12;
        UIPenetration = 20;
        UIReloadSpeed = 53;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 5300;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_jackhammer.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/jackhammer.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_jackhammer.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
