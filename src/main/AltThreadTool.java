/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Projectile;
import entity.bullet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Random;


/**
 *
 * @author Dangerouze
 */
public class AltThreadTool implements Runnable {
    GamePanel gp;
    public int muzzleFlashTime;
    Random random = new Random();
    public int recoil;
    long timeDifference;
    UtilityTool uTool = new UtilityTool();
    boolean reloading = false;
    
    public AltThreadTool (GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void Sleep (long time)
    {
        long beforeTime = System.nanoTime();
        long sleeptime;
        if (time - timeDifference < 0)
            sleeptime = 0;
        else
            sleeptime = time - timeDifference;
        
        System.out.println(sleeptime);
        try
        {
            Thread.sleep(sleeptime);
        }catch (InterruptedException a) {a.printStackTrace();}
        timeDifference = ((System.nanoTime() - beforeTime) - (time * 1000000))/1000000;
        if (timeDifference > time) {timeDifference = time;}
        
    }
    @Override
    public void run ()
    {
        if(gp.KeyHandler.RPressed == true)
        {
            gp.KeyHandler.RPressed = false;
            Reload();
        }
        while (gp.KeyHandler.mouseLeftPressed == true && gp.player.weaponStorage[gp.player.equippedWeapon] != null)
        {
            if (reloading)
                return;
            if (gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining != 0)
            {
                //variable assignment
                Point Mouse = gp.main.getMouseCoordinates();
                Point Character = new Point(gp.player.screenX+32,gp.player.screenY+55);
                double angle = uTool.getAngle(Character, Mouse);
                int weaponCenterX = gp.player.screenX - gp.player.weaponStorage[gp.player.equippedWeapon].centerX;
                int weaponCenterY = gp.player.screenY + gp.player.weaponStorage[gp.player.equippedWeapon].centerY;
                int vRecoil = 5;
                int recoilDelay = 5;
                recoil = random.nextInt(gp.player.weaponStorage[gp.player.equippedWeapon].recoil * -1,gp.player.weaponStorage[gp.player.equippedWeapon].recoil);
                
                //spawn bullet projectile
                bullet bullet = new bullet(gp.player.worldX + gp.player.weaponStorage[gp.player.equippedWeapon].bulletX, gp.player.worldY + gp.player.weaponStorage[gp.player.equippedWeapon].bulletY, angle + recoil);
                gp.projectileList.add(bullet);
                
                //player camera shake
                angle = (angle + 90) / 180 * Math.PI;
                gp.pointer.x = (int) (gp.pointer.x - (Math.cos(angle) * 10) - random.nextInt(-vRecoil, vRecoil));
                gp.pointer.y = (int) (gp.pointer.y - (Math.sin(angle) * 10) - random.nextInt(-vRecoil, vRecoil));
                
                //weapon visual recoil
                timeDifference = 0;
                muzzleFlashTime = 3;
                gp.sound.playSFX(gp.player.weaponStorage[gp.player.equippedWeapon].sound);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                
                //weapon stats change
                gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining--;

                Sleep (gp.player.weaponStorage[gp.player.equippedWeapon].fireRate - (recoilDelay * 4));
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX = gp.player.weaponStorage[gp.player.equippedWeapon].defaultCenterX;
                if (gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining == 0)
                    Reload();
            }
            else if (gp.player.weaponStorage[gp.player.equippedWeapon].AmmoRemaining == 0)
            {
               gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_empty.wav"));
               Sleep(200);
            }
            else
            {
                Reload();
            }
        }
    }

    public void Reload()
    {
        if (gp.player.weaponStorage[gp.player.equippedWeapon] == null)
            return;
        if (reloading
                || gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining == gp.player.weaponStorage[gp.player.equippedWeapon].MaxAmmoPerClip
                || gp.player.weaponStorage[gp.player.equippedWeapon].AmmoRemaining == 0)
            return;
        
        barValue = 0;
        reloading = true;
        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_reload_start.wav"));
        int sleepCounter = 0;
        int equippedWeapon = gp.player.equippedWeapon;
        timeDifference = 0;
        while (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
            if (sleepCounter <= gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime)
            {
                if (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                    barValue = ((double)sleepCounter / (double)gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime) * 100.00;
                Sleep (10);
                sleepCounter += 10;
                
            }
        else
            break;
        
        if (equippedWeapon != gp.player.equippedWeapon)
            {
                reloading = false;
                return;
            }
        
        if (gp.player.weaponStorage[gp.player.equippedWeapon].AmmoRemaining < gp.player.weaponStorage[gp.player.equippedWeapon].MaxAmmoPerClip)
        {
            gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining = gp.player.weaponStorage[gp.player.equippedWeapon].AmmoRemaining;
            gp.player.weaponStorage[gp.player.equippedWeapon].AmmoRemaining = 0;
        }
        else
        {
            int ammoDifference = gp.player.weaponStorage[gp.player.equippedWeapon].MaxAmmoPerClip - gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining;
            gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining = gp.player.weaponStorage[gp.player.equippedWeapon].MaxAmmoPerClip;
            gp.player.weaponStorage[gp.player.equippedWeapon].AmmoRemaining -= ammoDifference;
        }
        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_reload_end.wav"));
        reloading = false;
    }
    
    public void ThreadStart()
    {
        //thread1 = new Thread(this);
        //thread1.start();
        //shootService.execute(shoot);
        
    }
    
    double barValue = 0;
    
    public void drawProgressBar(Graphics2D g2)
    {
        if (reloading == true)
        {
            if (barValue > 100f)
                barValue = 100;
            Point Mouse = gp.main.getMouseCoordinates();
            g2.setColor(Color.getHSBColor((float) 0.41, (float) barValue / 100, (float) 0.90));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillArc(Mouse.x-32, Mouse.y-55, 50, 50, 90, 360-(int)(3.6*barValue));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }
    
}
