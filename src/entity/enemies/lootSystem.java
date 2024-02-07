/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.enemies;

import java.util.Random;
import main.GamePanel;
import object.*;

/**
 *
 * @author Dangerouze
 */
public class lootSystem {
    GamePanel gp;
    
    OBJ_coinCommon[] coinCommon = new OBJ_coinCommon[30];
    OBJ_coinRare[] coinRare = new OBJ_coinRare[15];
    OBJ_coinUnique[] coinUnique = new OBJ_coinUnique[10];
    OBJ_smaw[] smaw = new OBJ_smaw[3];
    OBJ_barrett[] barrett = new OBJ_barrett[3];
    OBJ_AmmoBox[] ammoBox = new OBJ_AmmoBox[10];
    
    int coinCommonCounter = 0;
    int coinRareCounter = 0;
    int coinUniqueCounter = 0;
    int smawCounter = 0;
    int barrettCounter = 0;
    int ammoBoxCounter = 0;
    
    Random random = new Random();
    
    public lootSystem(GamePanel gp)
    {
        this.gp = gp;
        for (int i = 0; i < coinCommon.length; i++)
        {
            coinCommon[i] = new OBJ_coinCommon(gp);
        }
        for (int i = 0; i < coinRare.length; i++)
        {
            coinRare[i] = new OBJ_coinRare(gp);
        }
        for (int i = 0; i < coinUnique.length; i++)
        {
            coinUnique[i] = new OBJ_coinUnique(gp);
        }
        for (int i = 0; i < smaw.length; i++)
        {
            smaw[i] = new OBJ_smaw(gp);
        }
        for (int i = 0; i < barrett.length; i++)
        {
            barrett[i] = new OBJ_barrett(gp);
        }
        for (int i = 0; i < ammoBox.length; i++)
        {
            ammoBox[i] = new OBJ_AmmoBox(gp);
        }
    }
    
    public void spawnLoot (int worldX, int worldY, String lootRarity, int dropChance)
    {
        int wave = gp.player.waveNumber;
        SuperObject checkLoot = dropLoot(wave, lootRarity, dropChance);
        if (checkLoot != null)
        {
            checkLoot.isAlive = true;
            checkLoot.timer = 20000;
            gp.obj.add(checkLoot);
            gp.obj.get(gp.obj.size() - 1).worldX = worldX;
            gp.obj.get(gp.obj.size() - 1).worldY = worldY;
        }
    }
    
    
    public SuperObject dropLoot(int wave, String lootRarity, int dropChance)
    {
        int chances = random.nextInt(1, 1000);
        if (chances <= dropChance)
        {
            if (wave < 5)
            {
                switch(lootRarity)
                {
                    case "common":
                        if (chances <= 1) {return legendaryWeapon();}
                        else if (chances <= 20) {return getAmmoBox();}
                        else if (chances <= 50) {return getCoinRare();}
                        else {return getCoinCommon();}
                    case "rare":
                        if (chances <= 3) {return legendaryWeapon();}
                        else if (chances <= 40) {return getAmmoBox();}
                        else if (chances <= 200) {return getCoinRare();}
                        else {return getCoinCommon();}
                }
            }
            else if (wave < 10)
            {
                switch(lootRarity)
                {
                    case "common":
                        if (chances <= 5) {return legendaryWeapon();}
                        else if (chances <= 60) {return getAmmoBox();}
                        else if (chances <= 100) {return getCoinRare();}
                        else {return getCoinCommon();}
                    case "rare":
                        if (chances <= 7) {return legendaryWeapon();}
                        else if (chances <= 10) {return getCoinUnique();}
                        else if (chances <= 80) {return getAmmoBox();}
                        else if (chances <= 400) {return getCoinRare();}
                        else {return getCoinCommon();}
                }
            }
            else if (wave < 15)
            {
                switch(lootRarity)
                {
                    case "common":
                        if (chances <= 7) {return legendaryWeapon();}
                        else if (chances <= 10) {return getCoinUnique();}
                        else if (chances <= 80) {return getAmmoBox();}
                        else if (chances <= 200) {return getCoinRare();}
                        else {return getCoinCommon();}
                    case "rare":
                        if (chances <= 9) {return legendaryWeapon();}
                        else if (chances <= 30) {return getCoinUnique();}
                        else if (chances <= 100) {return getAmmoBox();}
                        else if (chances <= 700) {return getCoinRare();}
                        else {return getCoinCommon();}
                }
            }
            else
            {
                switch(lootRarity)
                {
                    case "common":
                        if (chances <= 10) {return legendaryWeapon();}
                        else if (chances <= 40) {return getCoinUnique();}
                        else if (chances <= 150) {return getAmmoBox();}
                        else if (chances <= 400) {return getCoinRare();}
                        else {return getCoinCommon();}
                    case "rare":
                        if (chances <= 15) {return legendaryWeapon();}
                        else if (chances <= 70) {return getCoinUnique();}
                        else if (chances <= 200) {return getAmmoBox();}
                        else {return getCoinRare();}
                }
            }
        }
        return null;
    }
    
    public SuperObject legendaryWeapon()
    {
        int chances = random.nextInt(1, 100);
        if (chances <= 50)
            return getSmaw();
        else
            return getBarrett();
    }
    
    OBJ_smaw getSmaw()
    {
        if (smawCounter < 2)
            smawCounter++;
        else
            smawCounter = 0;
        
        return smaw[smawCounter];
    }
    OBJ_barrett getBarrett()
    {
        if (barrettCounter < 2)
            barrettCounter++;
        else
            barrettCounter = 0;
        
        return barrett[barrettCounter];
    }
    OBJ_coinCommon getCoinCommon()
    {
        if (coinCommonCounter < 29)
            coinCommonCounter++;
        else
            coinCommonCounter = 0;
        
        return coinCommon[coinCommonCounter];
    }
    OBJ_coinRare getCoinRare()
    {
        if (coinRareCounter < 14)
            coinRareCounter++;
        else
            coinRareCounter = 0;
        
        return coinRare[coinRareCounter];
    }
    OBJ_coinUnique getCoinUnique()
    {
        if (coinUniqueCounter < 9)
            coinUniqueCounter++;
        else
            coinUniqueCounter = 0;
        
        return coinUnique[coinUniqueCounter];
    }
    OBJ_AmmoBox getAmmoBox()
    {
        if (ammoBoxCounter < 2)
            ammoBoxCounter++;
        else
            ammoBoxCounter = 0;
        
        return ammoBox[ammoBoxCounter];
    }
}
