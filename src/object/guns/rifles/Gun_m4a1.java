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
public class Gun_m4a1 extends WeaponObject {
    
    public Gun_m4a1()
    {
        name = "M4A1";
        
        //Weapon Attributes
        damage = 40;
        recoil = 8;//8
        penetration = 2;
        bulletSpread = 0;
        bulletCount = 1;
        fireRate = 88;//88
        
        reloadTime = 1500;
        
        //Ammo
        maxAmmoPerClip = 30;
        ammoInClipRemaining = maxAmmoPerClip;
        ammoRemaining = 300;
        ammoBoxIncrement = 120;
        maxAmmo = ammoRemaining;
        
        //Weapon Image
        weaponWidth = 353;
        weaponHeight = 124;
        centerX = 31;
        centerY = 12;
        defaultCenterX = centerX;
        visualRecoil = 7;
        weaponScaling = 0.25f;
        UIScaling = 0.45f;
        
        //UI variables
        UIDamage = 17;
        UIFireRate = 68;
        UIAccuracy = 65;
        UIPenetration = 40;
        UIReloadSpeed = 47;
        detailText = "FULLY AUTOMATIC";
        weaponCost = 800;
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/wpn_m4a1.png"));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            Shopimage = uTool.scaleImage(image, (int) (weaponWidth * shopScaling), (int) (weaponHeight * shopScaling));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            
            silhuetteImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/m4a1.png"));
            silhuetteImage = uTool.scaleImage(silhuetteImage, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            
            sound = getClass().getResource("/weaponFX/wpn_fire_m4a1.wav");
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
