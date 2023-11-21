/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Dangerouze
 */
public class Gun_AssaultRifle extends WeaponObject {
    
    public Gun_AssaultRifle()
    {
        name = "Assault Rifle";
        MaxAmmoPerClip = 30;
        AmmoinClipRemaining = MaxAmmoPerClip;
        AmmoRemaining = 120;
        MaxAmmo = AmmoRemaining;
        fireRate = 0;
        damage = 0;
        centerX = 0;
        centerY = 18;
        weaponWidth = 84;
        weaponHeight = 28;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/Gun_assault_rifle.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
