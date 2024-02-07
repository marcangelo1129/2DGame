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
public class Gun_m1014 extends WeaponObject {
    
    public Gun_m1014()
    {
        name = "M1014";
        
        //Weapon Attributes
        damage = 20;
        fireRate = 300;
        recoil = 0;
        penetration = 1;
        bulletSpread = 30;
        bulletCount = 12;
        reloadTime = 400;
        clipReload = false;
        
        //Ammo
        maxAmmoPerClip = 7;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 70;
        ammoBoxIncrement = 28;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 365;
        weaponHeight = 103;
        centerX = 30;
        centerY = 11;
        intensity = 10;
        screenShake = true;
        defaultCenterX = centerX;
        visualRecoil = 15;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 54;
        UIFireRate = 28;
        UIAccuracy = 18;
        UIPenetration = 20;
        UIReloadSpeed = 100;
        detailText = "SEMI-AUTOMATIC";
        weaponCost = 1100;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_m1014.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/m1014.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_m1014.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
