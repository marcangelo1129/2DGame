/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

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
    
    public AltThreadTool (GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void Sleep (long time)
    {
        long beforeTime = System.nanoTime();
        try
        {
            Thread.sleep(time - timeDifference);
        }catch (InterruptedException a) {a.printStackTrace();}
        timeDifference = ((System.nanoTime() - beforeTime) - (time * 1000000))/1000000;
        if (timeDifference > time) {timeDifference = time;}
        System.out.println(timeDifference);
        
    }
    @Override
    public void run ()
    {
        while (gp.KeyHandler.mouseLeftPressed == true && gp.player.weaponStorage[gp.player.equippedWeapon] != null)
        {
            if (gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining != 0)
            {
                timeDifference = 0;
                //double recoilPositive = gp.player.weaponAngle + gp.player.weaponStorage[gp.player.equippedWeapon].recoil;
                //double recoilNegative = gp.player.weaponAngle - gp.player.weaponStorage[gp.player.equippedWeapon].recoil;
                recoil = random.nextInt(gp.player.weaponStorage[gp.player.equippedWeapon].recoil * -1,gp.player.weaponStorage[gp.player.equippedWeapon].recoil);
                //gp.player.weaponAngle = recoil;
                muzzleFlashTime = 3;
                gp.sound.playSFX(gp.player.weaponStorage[gp.player.equippedWeapon].sound);
                int recoilDelay = 5;
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;
                Sleep (recoilDelay);
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX += 1;

                Sleep (gp.player.weaponStorage[gp.player.equippedWeapon].fireRate - (recoilDelay * 4));
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX = gp.player.weaponStorage[gp.player.equippedWeapon].defaultCenterX;
            }
        }
    }

    public void ThreadStart()
    {
        //thread1 = new Thread(this);
        //thread1.start();
        //shootService.execute(shoot);
        
    }
    
}
