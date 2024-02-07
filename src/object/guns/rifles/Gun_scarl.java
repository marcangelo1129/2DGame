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
public class Gun_scarl extends WeaponObject {
    
    public Gun_scarl()
    {
        name = "SCAR-L";
        
        //Weapon Attributes
        damage = 575;
        recoil = 7;//8
        penetration = 3;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 115;//88
        
        reloadTime = 1350;
        
        //Ammo
        maxAmmoPerClip = 25;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 250;
        ammoBoxIncrement = 100;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 315;
        weaponHeight = 130;
        centerX = 23;
        centerY = 13;
        defaultCenterX = centerX;
        visualRecoil = 8;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 79;
        UIFireRate = 48;
        UIAccuracy = 71;
        UIPenetration = 60;
        UIReloadSpeed = 58;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 6500;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_scarl.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/scarl.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_scarl.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
