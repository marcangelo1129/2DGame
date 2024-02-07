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
public class Gun_tridant extends WeaponObject {
    
    public Gun_tridant()
    {
        name = "TRIDANT";
        
        //Weapon Attributes
        damage = 275;
        recoil = 18;//8
        penetration = 3;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 60;//88
        
        reloadTime = 4000;
        
        //Ammo
        maxAmmoPerClip = 100;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 800;
        ammoBoxIncrement = 300;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 328;
        weaponHeight = 107;
        centerX = 27;
        centerY = 6;
        defaultCenterX = centerX;
        visualRecoil = 7;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 63;
        UIFireRate = 96;
        UIAccuracy = 29;
        UIPenetration = 60;
        UIReloadSpeed = 16;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 7000;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_tridant.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/tridant.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_tridant.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
