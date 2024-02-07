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
public class Gun_aa12 extends WeaponObject {
    
    public Gun_aa12()
    {
        name = "AA-12";
        
        //Weapon Attributes
        damage = 35;
        fireRate = 100;
        recoil = 0;
        penetration = 1;
        bulletSpread = 40;
        bulletCount = 12;
        reloadTime = 1300;
        
        //Ammo
        maxAmmoPerClip = 8;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 80;
        ammoBoxIncrement = 32;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 310;
        weaponHeight = 134;
        centerX = 27;
        centerY = 12;
        defaultCenterX = centerX;
        visualRecoil = 15;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 71;
        UIFireRate = 56;
        UIAccuracy = 6;
        UIPenetration = 20;
        UIReloadSpeed = 63;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 3000;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_aa12.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/aa12.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_aa12.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
