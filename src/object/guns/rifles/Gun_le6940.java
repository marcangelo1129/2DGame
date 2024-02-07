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
public class Gun_le6940 extends WeaponObject {
    
    public Gun_le6940()
    {
        name = "LE6940";
        
        //Weapon Attributes
        damage = 180;
        recoil = 10;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 68;//88
        
        reloadTime = 1800;
        
        //Ammo
        maxAmmoPerClip = 40;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 400;
        ammoBoxIncrement = 160;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 328;
        weaponHeight = 121;
        centerX = 30;
        centerY = 12;
        defaultCenterX = centerX;
        visualRecoil = 5;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 38;
        UIFireRate = 88;
        UIAccuracy = 59;
        UIPenetration = 40;
        UIReloadSpeed = 32;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 4000;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_le6940.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/le6940.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_le6940.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
