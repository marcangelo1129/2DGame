/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import entity.Projectile;
import entity.bullet;
import entity.bullet_Trail;
import entity.explosion;
import entity.rock;
import entity.rocket;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Random;
import object.guns.WeaponObject;


/**
 *
 * @author Dangerouze
 */
public class AltThreadTool implements Runnable {
    GamePanel gp;
    public int muzzleFlashTime;
    Random random = new Random();
    public int recoil;
    public int bulletSpread;
    long timeDifference;
    UtilityTool uTool = new UtilityTool();
    boolean reloading = false;
    int bulletOffsetX = -50;
    int bulletOffsetY = -50;
    double angle;
    double offsetAngle;
    bullet[] bullet = new bullet[100];
    rock[] rock = new rock[100];
    bullet_Trail[] bullet_Trail = new bullet_Trail[400];
    rocket rocket = new rocket();
    public explosion explosion = new explosion();
    int bulletInterval = 0;
    int bulletTrailInterval = 0;
    int bulletDelay = 0;
    int rockInterval = 0;
    WeaponObject currentWeapon;
    
    public int burstCount = 0;
    public boolean firing = false;
    
    public int intensity = 0;
    public int intensityDec = 0;
    int intensityDelay = 0;
    Point pointerTemp = new Point();
    
    //bullet global variable
    int bulletWorldX;
    int bulletWorldY;
    double bulletAngle;
    int damage;
    int penetration;
    boolean bulletTrail;
    
    public AltThreadTool (GamePanel gp)
    {
        this.gp = gp;
        for (int i = 0; i < bullet.length; i++)
        {
            bullet[i] = new bullet();
        }
        for (int i = 0; i < bullet_Trail.length; i++)
        {
            bullet_Trail[i] = new bullet_Trail();
        }
        for (int i = 0; i < rock.length; i++)
        {
            rock[i] = new rock();
        }
    }
    
    public void Sleep (long time)
    {
        long beforeTime = System.nanoTime();
        long sleeptime;
        if (time - timeDifference < 0)
            sleeptime = 0;
        else
            sleeptime = time - timeDifference;
        
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
        if (gp.KeyHandler.mouseLeftPressed == true)
        {
            burstCount = 0;
        }
        while (gp.player.weaponStorage[gp.player.equippedWeapon] != null && (gp.KeyHandler.mouseLeftPressed == true || burstCount < gp.player.weaponStorage[gp.player.equippedWeapon].burstCount))
        {
            firing = true;
            if (reloading)
                return;
            if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining != 0)
            {
                double startTime = System.nanoTime();
                //variable assignment
                Point Mouse = gp.main.getMouseCoordinates();
                angle = uTool.getAngle(gp.player.centerPoint, Mouse);
                int vRecoil = gp.player.weaponStorage[gp.player.equippedWeapon].visualRecoil;
                int recoilDelay = 5;
                if (gp.player.weaponStorage[gp.player.equippedWeapon].recoil > 0)
                    recoil = random.nextInt((int) (gp.player.weaponStorage[gp.player.equippedWeapon].recoil * -0.5), (int) (gp.player.weaponStorage[gp.player.equippedWeapon].recoil * 0.5));
                else
                    recoil = 0;
                
                
                offsetAngle = (angle + recoil + 90) / 180 * Math.PI;
                
                //spawn bullet projectile
                bulletWorldX = (int) (gp.player.worldX + 24 + (Math.cos(offsetAngle) * bulletOffsetX));
                bulletWorldY = (int) (gp.player.worldY + 13 + (Math.sin(offsetAngle) * bulletOffsetY));
                bulletAngle = angle + recoil;
                damage = gp.player.weaponStorage[gp.player.equippedWeapon].damage;
                penetration = gp.player.weaponStorage[gp.player.equippedWeapon].penetration;
                bulletTrail = gp.player.weaponStorage[gp.player.equippedWeapon].bulletTrail;
                bulletCreator(gp.player);
                
                //player camera shake
                if (gp.player.weaponStorage[gp.player.equippedWeapon].screenShake == true)
                {
                    intensity = gp.player.weaponStorage[gp.player.equippedWeapon].intensity;
                    intensityDec = intensity / 10;
                }
                
                double cameraAngle = (angle + 90) / 180 * Math.PI;
                gp.pointer.x = (int) (gp.pointer.x - (Math.cos(cameraAngle) * vRecoil * 2) - random.nextInt(-vRecoil, vRecoil));
                gp.pointer.y = (int) (gp.pointer.y - (Math.sin(cameraAngle) * vRecoil * 2) - random.nextInt(-vRecoil, vRecoil));
                pointerTemp = gp.pointer;
                
                //weapon stats change
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining--;
                
                int totalTime = (int) ((System.nanoTime() - startTime) /1000000);
                
                //weapon visual recoil and firerate delay
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
                
                
                if (gp.player.equippedWeapon == 2 && gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining == 0 && gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining == 0)
                {
                    gp.player.weaponStorage[gp.player.equippedWeapon] = null;
                    gp.player.equippedWeapon = 1;
                }
                else
                
                Sleep (gp.player.weaponStorage[gp.player.equippedWeapon].fireRate - (recoilDelay * 4) - totalTime);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX = gp.player.weaponStorage[gp.player.equippedWeapon].defaultCenterX;
                if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining == 0)
                        Reload();
            }
            else if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining == 0 && gp.player.weaponStorage[gp.player.equippedWeapon].secondary == false)
            {
                recoil = 0;
                gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_empty.wav"));
                Sleep(200);
            }
            else
            {
                Reload();
            }
            burstCount++;
            if (burstCount == gp.player.weaponStorage[gp.player.equippedWeapon].burstCount && gp.player.weaponStorage[gp.player.equippedWeapon].fireMode == "burst")
            {
                Sleep(gp.player.weaponStorage[gp.player.equippedWeapon].burstDelay);
                if (gp.KeyHandler.mouseLeftPressed == true)
                    burstCount = 0;
            }
        }
        if (gp.KeyHandler.mouseLeftPressed == false)
        {
            firing = false;
        }
    }

    public void Reload()
    {
        if (gp.player.weaponStorage[gp.player.equippedWeapon] == null)
            return;
        if (reloading
                || gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining == gp.player.weaponStorage[gp.player.equippedWeapon].maxAmmoPerClip
                || (gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining == 0
                && gp.player.weaponStorage[gp.player.equippedWeapon].secondary == false) )
            return;
        
        firing = false;
        barValue = 0;
        reloading = true;
        int sleepCounter = 0;
        int equippedWeapon = gp.player.equippedWeapon;
        timeDifference = 0;
        
        currentWeapon = gp.player.weaponStorage[gp.player.equippedWeapon];
        if (gp.player.weaponStorage[gp.player.equippedWeapon].clipReload == true)
        {
            gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_reload_start.wav"));
            while (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                if (sleepCounter <= gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime && equippedWeapon == gp.player.equippedWeapon && currentWeapon == gp.player.weaponStorage[gp.player.equippedWeapon])
                {
                    if (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                        barValue = ((double)sleepCounter / (double)gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime) * 100.00;
                    if (sleepCounter == gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime - 300 && gp.player.weaponStorage[gp.player.equippedWeapon].fireMode != "rocket")
                        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_reload_end.wav"));
                        
                    Sleep (10);
                    if ((gp.shop != null && gp.shop.showShop == true) || gp.GameState != "inGame" || gp.player.gameOver == true)
                        continue;
                    
                    sleepCounter += 10;
                }
                else
                    break;

            if (equippedWeapon != gp.player.equippedWeapon || currentWeapon != gp.player.weaponStorage[gp.player.equippedWeapon])
                {
                    reloading = false;
                    return;
                }
            
            if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining < gp.player.weaponStorage[gp.player.equippedWeapon].maxAmmoPerClip)
            {
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining = gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining;
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining = 0;
            }
            else
            {
                int ammoDifference = gp.player.weaponStorage[gp.player.equippedWeapon].maxAmmoPerClip - gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining;
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining = gp.player.weaponStorage[gp.player.equippedWeapon].maxAmmoPerClip;
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining -= ammoDifference;
            }
            if (gp.player.weaponStorage[gp.player.equippedWeapon].secondary == true)
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining = gp.player.weaponStorage[gp.player.equippedWeapon].maxAmmoPerClip;
            if (gp.player.weaponStorage[gp.player.equippedWeapon].fireMode == "rocket")
                gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_reload_end.wav"));
            else
                gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_pump.wav"));
            reloading = false;
        }
        else 
        {
            while (reloading == true && gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining > 0 && gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining != gp.player.weaponStorage[gp.player.equippedWeapon].maxAmmoPerClip)
            {
                sleepCounter = 0;
                barValue = 0;
                while (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                    if (sleepCounter <= gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime && equippedWeapon == gp.player.equippedWeapon && equippedWeapon == gp.player.equippedWeapon && currentWeapon == gp.player.weaponStorage[gp.player.equippedWeapon])
                    {
                        if (gp.KeyHandler.mouseLeftPressed == true && gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining != 0)
                        {
                            reloading = false;
                            return;
                        }
                        if (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                            barValue = ((double)sleepCounter / (double)gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime) * 100.00;
                        if (sleepCounter == gp.player.weaponStorage[gp.player.equippedWeapon].reloadTime - 300)
                            gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_reload_shell_shotgun.wav"));
                        Sleep (10);
                        if ((gp.shop != null && gp.shop.showShop == true) || gp.GameState != "inGame" || gp.player.gameOver == true)
                            continue;
                        sleepCounter += 10;
                    }
                    else
                        break;
                
                if (equippedWeapon != gp.player.equippedWeapon || currentWeapon != gp.player.weaponStorage[gp.player.equippedWeapon])
                {
                    reloading = false;
                    return;
                }
                
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining++;
                gp.player.weaponStorage[gp.player.equippedWeapon].ammoRemaining--;
            }
            gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_pump.wav"));
            reloading = false;
        }
        
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
        if (reloading == true && gp.shop.showShop == false)
        {
            if (barValue > 100f)
                barValue = 100;
            Point Mouse = gp.main.getMouseCoordinates();
            g2.setColor(Color.getHSBColor((float) 0.41, (float) barValue / 100, (float) 0.90));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillArc(Mouse.x-24, Mouse.y-24, 50, 50, 90, 360-(int)(3.6*barValue));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }
    
    public void rockCreator(double angle, int bulletWorldX, int bulletWorldY, int damage, int penetration, boolean bulletTrail, Entity user)
    {
        rock[rockInterval].worldX = bulletWorldX;
        rock[rockInterval].worldY = bulletWorldY;
        rock[rockInterval].damage = damage;
        rock[rockInterval].penetration = penetration;
        rock[rockInterval].bulletTrail = bulletTrail;
        rock[rockInterval].alive = true;
        rock[rockInterval].index = bulletInterval;
        rock[rockInterval].projectileType = "rock";
        rock[rockInterval].user = user;
        rock[rockInterval].angle = angle;
        
        gp.projectileList.add(rock[rockInterval]);
        
        if (rockInterval >= 99)
            rockInterval = 0;
        else
            rockInterval++;
        
    }
    
    public void bulletCreator(Entity user)
    {
        for (int i = 0; i < gp.player.weaponStorage[gp.player.equippedWeapon].bulletCount; i++)
        {   
            if (gp.player.weaponStorage[gp.player.equippedWeapon].bulletSpread > 0)
                bulletSpread = random.nextInt((int) (gp.player.weaponStorage[gp.player.equippedWeapon].bulletSpread * -0.5), (int) (gp.player.weaponStorage[gp.player.equippedWeapon].bulletSpread * 0.5));
            else
                bulletSpread = gp.player.weaponStorage[gp.player.equippedWeapon].bulletSpread;
            
            if (gp.player.weaponStorage[gp.player.equippedWeapon].fireMode == "rocket")
            {
                rocket.worldX = bulletWorldX;
                rocket.worldY = bulletWorldY;
                rocket.damage = damage;
                rocket.penetration = penetration;
                rocket.bulletTrail = bulletTrail;
                rocket.index = bulletInterval;
                rocket.alive = true;
                rocket.projectileType = "rocket";

                rocket.angle = bulletAngle + bulletSpread;
               
                gp.projectileList.add(rocket);
            }
            else
            {
                bullet[bulletInterval].worldX = bulletWorldX;
                bullet[bulletInterval].worldY = bulletWorldY;
                bullet[bulletInterval].damage = damage;
                bullet[bulletInterval].penetration = penetration;
                bullet[bulletInterval].bulletTrail = bulletTrail;
                bullet[bulletInterval].alive = true;
                bullet[bulletInterval].index = bulletInterval;
                bullet[bulletInterval].projectileType = "bullet";
                bullet[bulletInterval].user = user;

                bullet[bulletInterval].angle = bulletAngle + bulletSpread;
                gp.projectileList.add(bullet[bulletInterval]);
            }
            //Sleep(50);
            
            if (bulletInterval >= 99)
                bulletInterval = 0;
            else
                bulletInterval++;
        }
    }
    
    public Runnable update = () ->
    {
        try
        {
            if (gp.GameState != "inGame" || gp.player.gameOver == true || gp.shop.showShop == true || gp.pauseMenu.paused == true)
                return;
            if (intensity > 0 && intensityDelay == 2)
            {
                double cameraAngle = (random.nextInt(0, 360));
                pointerTemp = gp.pointer;
                gp.pointer.x = (int) (pointerTemp.x - (Math.cos(cameraAngle) * intensity));
                gp.pointer.y = (int) (pointerTemp.y - (Math.sin(cameraAngle) * intensity));
                intensity -= intensityDec;
            }


            for (int i = gp.projectileList.size() - 1; i >= 0; i--)
            {
                Projectile projectile = gp.projectileList.get(i);
                if (projectile != null)
                    if (projectile.alive == false)
                        if (projectile.name == "rocket")
                        {
                            explosion.worldX = (int) projectile.worldX;
                            explosion.worldY = (int) projectile.worldY;
                            explosion.totalTime = 250;//200
                            explosion.delay = 25;//20
                            explosion.time = 250;//200
                            intensity = 100;
                            intensityDec = intensity / 10;
                            gp.sound.playSFX(explosion.sound[random.nextInt(0, 2)]);
                            for (int ii = gp.entityList.size() - 1; ii >= 0; ii--)
                            {
                                Entity entity = gp.entityList.get(ii);
                                int damage = 0;
                                if (Math.hypot(projectile.worldX - entity.worldX, projectile.worldY - entity.worldY) <= 100)
                                {
                                    damage = projectile.damage;
                                }
                                else if (Math.hypot(projectile.worldX - entity.worldX, projectile.worldY - entity.worldY) <= 200)
                                {
                                    damage = (int) (projectile.damage * 0.8);
                                }
                                else if (Math.hypot(projectile.worldX - entity.worldX, projectile.worldY - entity.worldY) <= 300)
                                {
                                    damage = (int) (projectile.damage * 0.6);
                                }
                                else if (Math.hypot(projectile.worldX - entity.worldX, projectile.worldY - entity.worldY) <= 400)
                                {
                                    damage = (int) (projectile.damage * 0.4);
                                }
                                entity.hp -= damage;
                                entity.hpTimer = 3000;
                                
                                
                            }
                            gp.projectileList.remove(projectile);
                        }
                        else
                            gp.projectileList.remove(projectile);
            }
                


            for (int i = gp.projectileList.size() - 1; i >= 0; i--)
            {
                if (gp.projectileList.get(i) != null)
                {
                    if (gp.projectileList.get(i).name == "bullet" || gp.projectileList.get(i).name == "rocket" || gp.projectileList.get(i).name == "rock")
                    {
                        double speed = gp.projectileList.get(i).speed;
                        double angle = gp.projectileList.get(i).angle;

                        if (gp.projectileList.get(i).bulletTrail == true)
                        {
                            bullet_Trail[bulletTrailInterval].worldX = gp.projectileList.get(i).worldX;
                            bullet_Trail[bulletTrailInterval].worldY = gp.projectileList.get(i).worldY;
                            bullet_Trail[bulletTrailInterval].angle = gp.projectileList.get(i).angle;
                            bullet_Trail[bulletTrailInterval].alive = true;
                            bullet_Trail[bulletTrailInterval].timer = 1000;

                            gp.projectileList.add(bullet_Trail[bulletTrailInterval]);
                            if (bulletTrailInterval >= 399)
                                bulletTrailInterval = 0;
                            else
                                bulletTrailInterval++;
                        }


                        angle = (double)((angle - 90) / 180 * Math.PI);
                        gp.projectileList.get(i).solidArea.x = (int) (gp.projectileList.get(i).worldX + (Math.cos(angle) * gp.projectileList.get(i).solidAreaOffset.x)) -6;
                        gp.projectileList.get(i).solidArea.y = (int) (gp.projectileList.get(i).worldY + (Math.sin(angle) * gp.projectileList.get(i).solidAreaOffset.y)) -2;
                        if (gp.projectileList.get(i).name == "rock")
                        {
                            if (gp.cChecker.checkBulletCollision(i, null, gp.player) == false)
                            {
                                gp.projectileList.get(i).worldX += (Math.cos(angle) * speed);
                                gp.projectileList.get(i).worldY += (Math.sin(angle) * speed);
                            }
                        }
                        else
                        {
                            if (gp.cChecker.checkBulletCollision(i, gp.entityList, null) == false)
                            {
                                gp.projectileList.get(i).worldX += (Math.cos(angle) * speed);
                                gp.projectileList.get(i).worldY += (Math.sin(angle) * speed);
                            }
                        }
                    }
                    else if (gp.projectileList.get(i).name == "bullet_trail")
                    {
                        double speed = gp.projectileList.get(i).speed;
                        double angle = gp.projectileList.get(i).angle;
                        angle = (double)((angle - 90) / 180 * Math.PI);
                        if (bulletDelay == 5)
                        {
                            gp.projectileList.get(i).worldX += (Math.cos(angle) * speed);
                            gp.projectileList.get(i).worldY += (Math.sin(angle) * speed);

                        }

                        gp.projectileList.get(i).timer -= 10;
                        if (gp.projectileList.get(i).timer == 0)
                            gp.projectileList.get(i).alive = false;
                    }
                }
            }
            if (bulletDelay == 5)
                bulletDelay = 0;
            else
                bulletDelay++;
            if (intensityDelay == 2)
                intensityDelay = 0;
            else
                intensityDelay++;
            explosionUpdate();
        }
        catch (Exception e){e.printStackTrace();}
        
    };
    
    public void explosionUpdate()
    {
        if (explosion.time > 0)
        {
            int delaySeperation = explosion.totalTime;
            float maxTransparency = 1.00f;
            float noStrobe = 0.45f;
            for (int i = 0; i < explosion.transparency.length; i++)
            {
                float transparency = (float) ((Math.abs(explosion.time - delaySeperation)-(float)(explosion.delay * 0.75))/(float)(explosion.delay * 0.75))*-1;
                if (explosion.time <= (double)(explosion.totalTime * 0.16) && i == 8)
                {
                    transparency = (float) ((Math.abs(explosion.time - delaySeperation)-(float)(90.0))/(float)(90.0))*-1;
                    noStrobe = 100;
                }
                if (transparency > noStrobe && i != 0)
                    explosion.transparency[i] = noStrobe;
                else if (transparency > maxTransparency)
                    explosion.transparency[i] = maxTransparency;
                else 
                    explosion.transparency[i] = transparency;
                
                maxTransparency -= 0.08f;
                //explosion.transparency[i] = (float) (explosion.time - (explosion.totalTime - delayTemp)) / delayTemp;
                if (explosion.time <= (double)(explosion.totalTime * 0.16))
                    delaySeperation = 90;
                else
                    delaySeperation -= explosion.delay;
            }
        explosion.time -= 10;
        }
    }
    
    
}
