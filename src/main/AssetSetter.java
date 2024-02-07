/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import entity.enemies.cCheckerDummy;
import entity.enemies.runner;
import entity.enemies.thrower;
import entity.enemies.zombie;
import java.util.Random;
import object.OBJ_AmmoBox;
import object.OBJ_M4A1;
import object.OBJ_barrett;
import object.OBJ_smaw;

/**
 *
 * @author Dangerouze
 */
public class AssetSetter {
    zombie[] zombie = new zombie[50];
    thrower[] thrower = new thrower[50];
    runner[] runner = new runner[50];
    int zombieCounter = 0;
    int throwerCounter = 0;
    int runnerCounter = 0;
    GamePanel gp;
    Random random = new Random();
    cCheckerDummy cCheckerDummy;
    
    public AssetSetter (GamePanel gp)
    {
        this.gp = gp;
        cCheckerDummy = new cCheckerDummy(gp);
        for (int i = 0; i < zombie.length; i++)
        {
            zombie[i] = new zombie(gp);
        }
        for (int i = 0; i < thrower.length; i++)
        {
            thrower[i] = new thrower(gp);
        }
        for (int i = 0; i < runner.length; i++)
        {
            runner[i] = new runner(gp);
        }
    }
    
    public void setObject()
    {
//        gp.obj[0] = new OBJ_AmmoBox(gp);
//        gp.obj[0].worldX = 23 * gp.tileSize;
//        gp.obj[0].worldY = 18 * gp.tileSize;
////        
        gp.obj.add(new OBJ_barrett(gp));
        gp.obj.get(0).worldX = 44 * gp.tileSize;
        gp.obj.get(0).worldY = 75 * gp.tileSize;
        gp.obj.get(0).timer = 99999999;
//        
        gp.obj.add(new OBJ_smaw(gp));
        gp.obj.get(1).worldX = 47 * gp.tileSize;
        gp.obj.get(1).worldY = 75 * gp.tileSize;
        gp.obj.get(1).timer = 99999999;
        
        //addEntity("zombie", 1, 1);
    }
    
    public void addEntityRandomly(String entity, Entity defaultEntity)
    {
        int worldX;
        int worldY;
        while (true)
        {
//            int col = random.nextInt(0, gp.maxWorldCol - 1);
//            int row = random.nextInt(0, gp.maxWorldRow - 1);
            double randomAngle = random.nextInt(0,360);
            int randomDistance = random.nextInt(20,40);
            
            int playerX = (gp.player.worldX + gp.player.solidArea.x + (gp.player.solidArea.width / 2)) / gp.tileSize;
            int playerY = (gp.player.worldY + gp.player.solidArea.y + (gp.player.solidArea.height / 2)) / gp.tileSize;
            
            int col = playerX + (int) (Math.cos(Math.toRadians(randomAngle)) * randomDistance);
            int row = playerY + (int) (Math.sin(Math.toRadians(randomAngle)) * randomDistance);
            
            if (col < 0 || col >= gp.maxWorldCol || row < 0 || row >= gp.maxWorldRow)
                continue;
            
            int tileNum = gp.tileM.mapTileNum[col][row];
            cCheckerDummy.worldX = col * gp.tileSize;
            cCheckerDummy.worldY = row * gp.tileSize;
            cCheckerDummy.collisionOn = false;
            gp.cChecker.checkDecoCollision(cCheckerDummy);
            if (gp.tileM.tile[tileNum].collision == false && cCheckerDummy.collisionOn == false)
            {
                worldX = col;
                worldY = row;
                break;
            }
        }
        addEntity(entity, worldX, worldY, defaultEntity);
    }
    
    public void addEntity(String entity, int worldX, int worldY, Entity defaultEntity)
    {
        if (entity == "zombie")
        {
            setEntityDefaults(zombie[zombieCounter], defaultEntity);
            zombie[zombieCounter].worldX = (worldX * gp.tileSize) - zombie[zombieCounter].solidArea.x;
            zombie[zombieCounter].worldY = (worldY * gp.tileSize) - zombie[zombieCounter].solidArea.y;
            
            gp.entityList.add(zombie[zombieCounter]);
            if (zombieCounter >= zombie.length - 1)
                zombieCounter = 0;
            else
                zombieCounter++;
        }
        if (entity == "thrower")
        {
            setEntityDefaults(thrower[throwerCounter], defaultEntity);
            thrower[throwerCounter].worldX = (worldX * gp.tileSize) - thrower[throwerCounter].solidArea.x;
            thrower[throwerCounter].worldY = (worldY * gp.tileSize) - thrower[throwerCounter].solidArea.y;
            
            gp.entityList.add(thrower[throwerCounter]);
            if (throwerCounter >= thrower.length - 1)
                throwerCounter = 0;
            else
                throwerCounter++;
        }
        if (entity == "runner")
        {
            setEntityDefaults(runner[runnerCounter], defaultEntity);
            runner[runnerCounter].worldX = (worldX * gp.tileSize) - runner[runnerCounter].solidArea.x;
            runner[runnerCounter].worldY = (worldY * gp.tileSize) - runner[runnerCounter].solidArea.y;
            
            gp.entityList.add(runner[runnerCounter]);
            if (runnerCounter >= runner.length - 1)
                runnerCounter = 0;
            else
                runnerCounter++;
        }
    }
    
    public void setEntityDefaults(Entity entity, Entity defaultEntity)
    {
        entity.isAlive = true;
        entity.dyingTime = defaultEntity.dyingTime;
        entity.deadFrame = defaultEntity.deadFrame;
        entity.entityState = defaultEntity.entityState;
        entity.score = defaultEntity.score;
        entity.hpTimer = 0;
        entity.lootDropped = false;
        entity.maxHp = defaultEntity.maxHp;
        entity.hp = entity.maxHp;
        if (entity.bulletReceived != null)
            entity.bulletReceived.clear();
    }
}
