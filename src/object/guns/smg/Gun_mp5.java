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
public class Gun_mp5 extends WeaponObject {
    
    public Gun_mp5()
    {
        name = "MP5";
        
        //Weapon Attributes
        damage = 25;
        recoil = 7;//8
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 72;//88
        
        reloadTime = 1100;
        
        //Ammo
        maxAmmoPerClip = 30;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 300;
        ammoBoxIncrement = 120;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 231;
        weaponHeight = 116;
        centerX = 22;
        centerY = 12;
        defaultCenterX = centerX;
        visualRecoil = 6;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 4;
        UIFireRate = 80;
        UIAccuracy = 71;
        UIPenetration = 20;
        UIReloadSpeed = 74;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 450;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_mp5.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/mp5.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_mp5.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
