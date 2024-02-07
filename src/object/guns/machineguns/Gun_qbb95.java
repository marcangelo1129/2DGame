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
public class Gun_qbb95 extends WeaponObject {
    
    public Gun_qbb95()
    {
        name = "QBB-95";
        
        //Weapon Attributes
        damage = 190;
        recoil = 10;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 70;//88
        
        fireMode = "burst";
        burstDelay = 140;
        burstCount = 3;
        
        reloadTime = 3750;
        
        //Ammo
        maxAmmoPerClip = 60;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 600;
        ammoBoxIncrement = 240;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 310;
        weaponHeight = 146;
        centerX = 21;
        centerY = 17;
        defaultCenterX = centerX;
        visualRecoil = 7;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 42;
        UIFireRate = 84;
        UIAccuracy = 59;
        UIPenetration = 40;
        UIReloadSpeed = 21;
        detailText = "3 ROUND BURST FIRE";
        weaponCost = 2200;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_qbb95.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/qbb95.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_qbb95.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
