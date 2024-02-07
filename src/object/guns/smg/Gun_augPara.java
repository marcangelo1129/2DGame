/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.smg;

import object.guns.rifles.*;
import java.awt.Point;
import javax.imageio.ImageIO;
import object.guns.WeaponObject;

/**
 *
 * @author Dangerouze
 */
public class Gun_augPara extends WeaponObject {
    
    public Gun_augPara()
    {
        name = "AUG-PARA";
        
        //Weapon Attributes
        damage = 210;
        recoil = 6;//8
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 80;//88
        
        reloadTime = 1200;
        
        //Ammo
        maxAmmoPerClip = 30;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 300;
        ammoBoxIncrement = 120;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 244;
        weaponHeight = 120;
        centerX = 22;
        centerY = 14;
        defaultCenterX = centerX;
        visualRecoil = 6;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 50;
        UIFireRate = 72;
        UIAccuracy = 76;
        UIPenetration = 20;
        UIReloadSpeed = 68;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 3400;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_augpara.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/augpara.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_augpara.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
