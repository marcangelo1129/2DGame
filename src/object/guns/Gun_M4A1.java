/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;

/**
 *
 * @author Dangerouze
 */
public class Gun_M4A1 extends WeaponObject {
    
    public Gun_M4A1()
    {
        name = "M4A1";
        MaxAmmoPerClip = 30;
        AmmoinClipRemaining = MaxAmmoPerClip;
        AmmoRemaining = 120;
        MaxAmmo = AmmoRemaining;
        fireRate = 88;
        damage = 28;
        reloadTime = 1300;
        centerX = 5;
        centerY = 15;
        defaultCenterX = 5;
        weaponWidth = 353;
        weaponHeight = 124;
        recoil = 5;
        muzzleLoc = new Point(85,26);
        
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/M4A1/wpn_m4a1.png"));
            image = uTool.scaleImage(image, (int) (weaponWidth*weaponScaling), (int) (weaponHeight*weaponScaling));
            UIimage = uTool.scaleImage(image, (int) (weaponWidth * UIScaling), (int) (weaponHeight * UIScaling));
            sound = getClass().getResource("/weaponFX/wpn_fire_m4a1.wav");
            //AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource("/weaponFX/wpn_fire_m4a1.wav"));
            
//            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
//            {
//                String line = br.readLine();
//
//                while (col < gp.maxWorldCol)
//                {
//                    String numbers[] = line.split(" ");
//
//                    int num = Integer.parseInt(numbers[col]);
//
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if(col == gp.maxWorldCol)
//                {
//                    col = 0;
//                    row++;
//                }
//            }
            
        }catch(Exception ex) {ex.printStackTrace();}
        
    }
    
}
