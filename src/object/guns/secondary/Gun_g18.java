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
public class Gun_g18 extends WeaponObject {
    
    public Gun_g18()
    {
        name = "G18";
        
        //Weapon Attributes
        damage = 80;
        recoil = 18;
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 65;//88
        
        reloadTime = 850;
        
        //Ammo
        maxAmmoPerClip = 20;
        ammoInClipRemaining = maxAmmoPerClip;
        secondary = true;
        
        //Weapon Image
        weaponWidth = 101;
        weaponHeight = 100;
        centerX = 0;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 8;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 21;
        UIFireRate = 92;
        UIAccuracy = 29;
        UIPenetration = 20;
        UIReloadSpeed = 84;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 2050;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_g18.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/g18.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_g18.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
