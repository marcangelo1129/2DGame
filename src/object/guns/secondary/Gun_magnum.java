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
public class Gun_magnum extends WeaponObject {
    
    public Gun_magnum()
    {
        name = "MAGNUM";
        
        //Weapon Attributes
        damage = 900;
        recoil = 12;
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 600;//88
        
        reloadTime = 1300;
        
        //Ammo
        maxAmmoPerClip = 6;
        ammoInClipRemaining = maxAmmoPerClip;
        secondary = true;
        
        //Weapon Image
        weaponWidth = 134;
        weaponHeight = 70;
        centerX = 0;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 20;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 88;
        UIFireRate = 20;
        UIAccuracy = 53;
        UIPenetration = 40;
        UIReloadSpeed = 63;
        detailText = "SEMI-AUTOMATIC";
        weaponCost = 3100;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_magnum.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/magnum.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_magnum.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
