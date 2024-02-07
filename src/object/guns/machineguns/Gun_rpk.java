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
public class Gun_rpk extends WeaponObject {
    
    public Gun_rpk()
    {
        name = "RPK";
        
        //Weapon Attributes
        damage = 100;
        recoil = 25;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 91;//88
        
        reloadTime = 4000;
        
        //Ammo
        maxAmmoPerClip = 100;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 700;
        ammoBoxIncrement = 250;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 374;
        weaponHeight = 126;
        centerX = 26;
        centerY = 13;
        defaultCenterX = centerX;
        visualRecoil = 11;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 33;
        UIFireRate = 60;
        UIAccuracy = 24;
        UIPenetration = 40;
        UIReloadSpeed = 16;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 1500;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_rpk.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/rpk.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_rpk.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
