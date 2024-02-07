/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns.machineguns;

import object.guns.rifles.*;
import java.awt.Point;
import javax.imageio.ImageIO;
import object.guns.WeaponObject;

/**
 *
 * @author Dangerouze
 */
public class Gun_aughbar extends WeaponObject {
    
    public Gun_aughbar()
    {
        name = "AUG-HBAR";
        
        //Weapon Attributes
        damage = 200;
        recoil = 6;//8
        penetration = 3;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 80;//88
        
        reloadTime = 4250;
        
        //Ammo
        maxAmmoPerClip = 50;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 500;
        ammoBoxIncrement = 200;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 323;
        weaponHeight = 134;
        centerX = 23;
        centerY = 14;
        defaultCenterX = centerX;
        visualRecoil = 7;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 46;
        UIFireRate = 72;
        UIAccuracy = 76;
        UIPenetration = 60;
        UIReloadSpeed = 11;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 4000;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_aughbar.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/aughbar.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_aughbar.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
