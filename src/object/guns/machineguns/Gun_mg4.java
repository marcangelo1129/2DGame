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
public class Gun_mg4 extends WeaponObject {
    
    public Gun_mg4()
    {
        name = "MG4";
        
        //Weapon Attributes
        damage = 35;
        recoil = 15;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 80;//88
        
        reloadTime = 5000;
        
        //Ammo
        maxAmmoPerClip = 100;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 700;
        ammoBoxIncrement = 250;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 353;
        weaponHeight = 129;
        centerX = 25;
        centerY = 14;
        defaultCenterX = centerX;
        visualRecoil = 9;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 8;
        UIFireRate = 72;
        UIAccuracy = 41;
        UIPenetration = 40;
        UIReloadSpeed = 5;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 700;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_mg4.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/mg4.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_mg4.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
