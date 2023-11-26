/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


/**
 *
 * @author Dangerouze
 */
public class AltThreadTool implements Runnable {
    GamePanel gp;
    Thread thread1;
    int soundposition;
    public int muzzleFlashTime;
    
    public AltThreadTool (GamePanel gp)
    {
        soundposition = 1;
        this.gp = gp;
    }
    
    public void Sleep (long time)
    {
        try
        {
            Thread.sleep(time);
        }catch (InterruptedException a) {a.printStackTrace();}
        
    }
    @Override
    public void run ()
    {
        while (true)
        {
            System.out.print("");
            if (gp.KeyHandler.mouseLeftPressed == true && gp.player.weaponStorage[gp.player.equippedWeapon] != null)
            {
                if (gp.player.weaponStorage[gp.player.equippedWeapon].AmmoinClipRemaining != 0)
                {
                    muzzleFlashTime = 3;
                    System.out.println(soundposition);
                    switch (soundposition) {
                        case 1:
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip2.setFramePosition(0);
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip1.start();
                            break;
                        case 2:
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip3.setFramePosition(0);
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip2.start();
                            break;
                        case 3:
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip4.setFramePosition(0);
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip3.start();
                            break;
                        case 4:
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip1.setFramePosition(0);
                            gp.player.weaponStorage[gp.player.equippedWeapon].clip4.start();
                            break;
                        default:
                            break;
                    }
                    if (soundposition == 4)
                        soundposition = 1;
                    else
                        soundposition++;
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
                    gp.player.weaponStorage[gp.player.equippedWeapon].centerX -= 5;
                }
            }
            else
                Sleep(16);      
        }
    }

    public void ThreadStart()
    {
        thread1 = new Thread(this);
        thread1.start();
        //shootService.execute(shoot);
        
    }
    
}
