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
public class Gun_barrett extends WeaponObject {
    
    public Gun_barrett()
    {
        name = "M821A1 BARRETT";
        
        //Weapon Attributes
        damage = 500;
        recoil = 0;
        penetration = 10;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 900;
        
        reloadTime = 1300;
        
        //Ammo
        maxAmmoPerClip = 999;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 0;
        ammoBoxIncrement = 0;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 550;
        weaponHeight = 120;
        centerX = 27;
        centerY = 14;
        defaultCenterX = centerX;
        visualRecoil = 50;
        intensity = 70;
        screenShake = true;
        weaponScaling = 0.20f;
        UIScaling = 0.35f;
        bulletTrail = true;
        
        //UI variables
        UIDamage = 100;
        UIFireRate = 15;
        UIAccuracy = 100;
        UIPenetration = 100;
        UIReloadSpeed = 0;
        detailText = "BOLT ACTION";
        weaponCost = 9999;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_barrett.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/barrett.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_barrett.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
