/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.enemies.runner;
import entity.enemies.thrower;
import entity.enemies.zombie;
import java.util.Random;
import object.guns.Gun_barrett;
import object.guns.Gun_smaw;

/**
 *
 * @author Dangerouze
 */
public class waveFunction {
    GamePanel gp;
    int waveTimer;
    int spawnTimer;
    public zombie zombie;
    public thrower thrower;
    public runner runner;
    public Gun_smaw smaw;
    public Gun_barrett barrett;
    Random random = new Random();
    
    public waveFunction(GamePanel gp)
    {
        this.gp = gp;
        zombie = new zombie(gp); 
        thrower = new thrower(gp);
        runner = new runner(gp);
        smaw = new Gun_smaw();
        barrett = new Gun_barrett();
        waveTimer = 0;
        spawnTimer = 1000;
    }
    
    public Runnable waveThread = () ->
    {
        if ((gp.shop != null && gp.shop.showShop == true) || gp.GameState != "inGame" || gp.player.gameOver == true || (gp.pauseMenu != null && gp.pauseMenu.paused == true))
            return;
        
        waveTimer += 10;
        spawnTimer -= 10;
        if (waveTimer >= 30000)
        {
            waveTimer = 0;
            gp.player.waveNumber++;
            zombie.maxHp += zombie.maxHp * 0.15;
            zombie.score += zombie.score * 0.20;
            thrower.maxHp += thrower.maxHp * 0.15;
            thrower.score += thrower.score * 0.20;
            runner.maxHp += runner.maxHp * 0.15;
            runner.score += runner.score * 0.20;
            barrett.damage += barrett.damage * 1.1;
            smaw.damage += smaw.damage * 1.1;
        }
        if (spawnTimer <= 0)
        {
            int chance = random.nextInt(2, 4);
            for (int i = 1; i <= chance; i++)
                //gp.aSetter.addEntityRandomly("zombie", zombie);
                if (gp.dw.disableZombieGen.isSelected() == false)
                    spawnRandomZombie();
            spawnTimer = random.nextInt(2, 6) * 1000;
        }
    };
    
    public void spawnRandomZombie()
    {
        int chances = random.nextInt(1, 1000);
        if (gp.player.waveNumber < 5)
        {
            if (chances < 50) gp.aSetter.addEntityRandomly("thrower", thrower);
            else gp.aSetter.addEntityRandomly("zombie", zombie);
        }
        else if (gp.player.waveNumber < 10)
        {
            if (chances < 100) gp.aSetter.addEntityRandomly("thrower", thrower);
            else if (chances < 200) gp.aSetter.addEntityRandomly("runner", runner);
            else gp.aSetter.addEntityRandomly("zombie", zombie);
        }
        else if (gp.player.waveNumber < 15)
        {
            if (chances < 200) gp.aSetter.addEntityRandomly("thrower", thrower);
            else if (chances < 400) gp.aSetter.addEntityRandomly("runner", runner);
            else gp.aSetter.addEntityRandomly("zombie", zombie);
        }
        else
        {
            if (chances < 300) gp.aSetter.addEntityRandomly("thrower", thrower);
            else if (chances < 600) gp.aSetter.addEntityRandomly("runner", runner);
            else gp.aSetter.addEntityRandomly("zombie", zombie);
        }
        
    }
    
}
