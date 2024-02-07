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
public class Gun_fiveseven extends WeaponObject {
    
    public Gun_fiveseven()
    {
        name = "FIVESEVEN";
        
        //Weapon Attributes
        damage = 40;
        recoil = 14;
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 170;//88
        
        reloadTime = 850;
        
        //Ammo
        maxAmmoPerClip = 12;
        ammoInClipRemaining = maxAmmoPerClip;
        secondary = true;
        
        //Weapon Image
        weaponWidth = 92;
        weaponHeight = 68;
        centerX = 0;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 8;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 17;
        UIFireRate = 44;
        UIAccuracy = 47;
        UIPenetration = 20;
        UIReloadSpeed = 84;
        detailText = "SEMI-AUTOMATIC";
        weaponCost = 400;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_fiveseven.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/fiveseven.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_fiveseven.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
