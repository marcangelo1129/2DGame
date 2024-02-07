/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.rifles;

import java.awt.Point;
import javax.imageio.ImageIO;
import object.guns.WeaponObject;

/**
 *
 * @author Dangerouze
 */
public class Gun_m16a4 extends WeaponObject {
    
    public Gun_m16a4()
    {
        name = "M16A4";
        
        //Weapon Attributes
        damage = 90;
        recoil = 6;
        penetration = 3;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 73;
        
        fireMode = "burst";
        burstDelay = 200;
        burstCount = 3;
        
        reloadTime = 1700;
        
        //Ammo
        maxAmmoPerClip = 30;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 300;
        ammoBoxIncrement = 120;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 404;
        weaponHeight = 124;
        centerX = 33;
        centerY = 12;
        defaultCenterX = centerX;
        visualRecoil = 7;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 25;
        UIFireRate = 76;
        UIAccuracy = 76;
        UIPenetration = 60;
        UIReloadSpeed = 37;
        detailText = "3 ROUND BURST FIRE";
        weaponCost = 1500;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_m16a4.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/m16a4.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_m16a4.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
