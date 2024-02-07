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
public class Gun_ak47 extends WeaponObject {
    
    public Gun_ak47()
    {
        name = "AK47";
        
        //Weapon Attributes
        damage = 180;
        recoil = 15;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 110;//88
        
        reloadTime = 1600;
        
        //Ammo
        maxAmmoPerClip = 30;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 240;
        ammoBoxIncrement = 90;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 327;
        weaponHeight = 125;
        centerX = 26;
        centerY = 13;
        defaultCenterX = centerX;
        visualRecoil = 10;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 38;
        UIFireRate = 52;
        UIAccuracy = 41;
        UIPenetration = 40;
        UIReloadSpeed = 42;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 2700;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_ak47.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/ak47.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_ak47.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
