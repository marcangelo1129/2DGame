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
public class Gun_evo3 extends WeaponObject {
    
    public Gun_evo3()
    {
        name = "SCORPION EVO 3";
        
        //Weapon Attributes
        damage = 250;
        recoil = 8;//8
        penetration = 1;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 53;//88
        
        reloadTime = 1200;
        
        //Ammo
        maxAmmoPerClip = 50;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 500;
        ammoBoxIncrement = 200;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 238;
        weaponHeight = 92;
        centerX = 19;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 6;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 58;
        UIFireRate = 100;
        UIAccuracy = 65;
        UIPenetration = 20;
        UIReloadSpeed = 68;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 5700;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_evo3.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/evo3.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_evo3.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
