/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import entity.Player;
import java.awt.Rectangle;
import java.util.ArrayList;
import object.SuperObject;

/**
 *
 * @author Dangerouze
 */
public class CollisionChecker {
    
    GamePanel gp;
    
    Rectangle playerSolidArea = new Rectangle(0,0,0,0);
    
    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void checkTile(Entity entity)
    {
        if (gp.dw.NoClip.isSelected())
            return;
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width - 1;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height - 3;
        
        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;
        
        int tileNum1, tileNum2, tileNum3, tileNum4;
        
        switch (entity.direction)
        {
            case "upLeft":
                entityTopRow = ((entityTopY - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                entityTopRow = ((entityTopY + entity.speed) / gp.tileSize);
                entityLeftCol = ((entityLeftX - entity.speed) / gp.tileSize);
                tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionSide1 = true;
                }
                if (gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                {
                    entity.collisionSide2 = true;
                }
                break;
            case "downLeft":
                entityBottomRow = ((entityBottomY + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                entityBottomRow = ((entityBottomY - entity.speed) / gp.tileSize);
                entityLeftCol = ((entityLeftX - entity.speed) / gp.tileSize);
                tileNum3 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionSide1 = true;
                }
                if (gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                {
                    entity.collisionSide2 = true;
                }
                break;
            case "downRight":
                entityBottomRow = ((entityBottomY + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                entityBottomRow = ((entityBottomY - entity.speed) / gp.tileSize);
                entityRightCol = ((entityRightX + entity.speed) / gp.tileSize);
                tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionSide1 = true;
                }
                if (gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                {
                    entity.collisionSide2 = true;
                }
                break;
            case "upRight":
                entityTopRow = ((entityTopY - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                entityTopRow = ((entityTopY + entity.speed) / gp.tileSize);
                entityRightCol = ((entityRightX + entity.speed) / gp.tileSize);
                tileNum3 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionSide1 = true;
                }
                if (gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true)
                {
                    entity.collisionSide2 = true;
                }
                break;
            case "up":
                entityTopRow = ((entityTopY - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = ((entityBottomY + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = ((entityLeftX - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = ((entityRightX + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
        }
        
    }
    public int checkObject (Entity entity, boolean player)
    {
        int index = 999;
        
        for (int i = gp.obj.size() - 1; i >= 0; i--)
        {
            SuperObject object = gp.obj.get(i);
            if (gp.obj.get(i) != null)
            {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                object.solidArea.x = object.worldX + object.solidArea.x;
                object.solidArea.y = object.worldY + object.solidArea.y;
                
                switch (entity.direction)
                {
                    case"up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case"down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case"left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(object.solidArea))
                {
                    if (player == true)
                        index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                object.solidArea.x = object.solidAreaDefaultX;
                object.solidArea.y = object.solidAreaDefaultY;
            }
        }
        
        return index;
    }
    
    //Rectangle decoCollision = new Rectangle(0,0,0,0);
    //SuperObject deco;
    public void checkDecoCollision (Entity entity)
    {
        if (gp.dw.NoClip.isSelected())
            return;
        for (int i = gp.objDeco.size() - 1; i >= 0; i--)
        {
            SuperObject deco = gp.objDeco.get(i);
            if (deco != null)
            {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                deco.solidArea.x = deco.worldX + deco.solidAreaDefaultX;
                deco.solidArea.y = deco.worldY + deco.solidAreaDefaultY;
                
                switch (entity.direction)
                {
                    case"up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case"down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case"left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(deco.solidArea))
                {
                    if (deco.collision == true)
                        entity.collisionOn = true;
                }
                switch (entity.direction)
                {
                    case "upLeft":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "downLeft":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "downRight":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "upRight":
                        entity.solidArea.y -= entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(deco.solidArea))
                {
                    if (deco.collision == true)
                        entity.collisionSide1 = true;
                }
                else
                {
                    switch(entity.direction){
                    case "upLeft":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "downLeft":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "downRight":
                        entity.solidArea.x += entity.speed;
                        break;
                    case "upRight":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                    if (entity.solidArea.intersects(deco.solidArea))
                    {
                        if (deco.collision == true)
                            entity.collisionSide2 = true;
                    }
                }
                
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                //deco.solidArea.x = deco.solidAreaDefaultX;
                //deco.solidArea.y = deco.solidAreaDefaultY;
            }
        }
    }
    public boolean checkBulletCollision(int index, ArrayList<Entity> target, Player player)
    {
        boolean ifCollide = false;
        int bulletX = (gp.projectileList.get(index).solidArea.x + 5) / gp.tileSize;
        int bulletY = (gp.projectileList.get(index).solidArea.y + 5) / gp.tileSize;
        if (bulletX < 0 || bulletX >= gp.maxWorldCol || bulletY < 0 || bulletY >= gp.maxWorldRow)
        {
            gp.projectileList.get(index).alive = false;
            return true;
        }
        else
        {
            int tileNum = gp.tileM.mapTileNum[bulletX][bulletY];
            if (gp.tileM.tile[tileNum].bulletCollision == true)
            {
                gp.projectileList.get(index).alive = false;
                return true;
            }
        }
        
        
        for (int i = gp.objDeco.size() - 1; i >= 0; i--)
        {
            SuperObject deco = gp.objDeco.get(i);
            if (gp.objDeco.get(i) != null)
            {
                deco.solidArea.x = deco.worldX + deco.solidAreaDefaultX;
                deco.solidArea.y = deco.worldY + deco.solidAreaDefaultY;
                if ((deco.solidArea).intersects(gp.projectileList.get(index).solidArea))
                {
                    if (deco.bulletCollision == true)
                    {
                        ifCollide = true;
                        gp.projectileList.get(index).alive = false;
                    }
                }
            }
        }
        
        if (gp.projectileList.get(index).name == "rock")
        {
            playerSolidArea.width = gp.player.solidArea.width;
            playerSolidArea.height = gp.player.solidArea.height;
            playerSolidArea.x = gp.player.solidArea.x + gp.player.worldX;
            playerSolidArea.y = gp.player.solidArea.y + gp.player.worldY;
            
            if (gp.projectileList.get(index).solidArea.intersects(playerSolidArea) && gp.projectileList.get(index).alive == true)
            {
                player.inflictDamage(gp.projectileList.get(index).damage);
                ifCollide = true;
                gp.projectileList.get(index).alive = false;
            }
            
        }
        else
        {
            for (int i = target.size() - 1; i >= 0; i--)
            {
                if (target.get(i) != null)
                {
                    target.get(i).hitBox.x = target.get(i).worldX + target.get(i).hitBox.x;
                    target.get(i).hitBox.y = target.get(i).worldY + target.get(i).hitBox.y;

                    if (gp.projectileList.get(index).solidArea.intersects(target.get(i).hitBox) && target.get(i).isAlive == true && gp.projectileList.get(index).alive == true)
                    {
                        boolean bulletRepeat = false;
                        for (int ii = target.get(i).bulletReceived.size() - 1; ii >= 0; ii--)
                        {
                            if (target.get(i).bulletReceived.get(ii).contains(Integer.toString(gp.projectileList.get(index).index)))
                            {
                                bulletRepeat = true;
                                break;
                            }
                        }
                        if (bulletRepeat == false)
                        {
                            if (gp.projectileList.get(index).projectileType == "bullet")
                                target.get(i).hp -= gp.projectileList.get(index).damage;

                            target.get(i).hpTimer = 3000;

                            if (gp.projectileList.get(index).penetration > 1)
                            {
                                if (target.get(i).bulletReceived.size() > 10)
                                    target.get(i).bulletReceived.remove(0);
                                target.get(i).bulletReceived.add(Integer.toString(gp.projectileList.get(index).index));
                                gp.projectileList.get(index).penetration -= 1;
                                gp.projectileList.get(index).damage = (int) (gp.projectileList.get(index).damage / 0.7f);
                            }
                            else
                            {
                                ifCollide = true;
                                gp.projectileList.get(index).alive = false;
                            }
                        }
                    }

                    target.get(i).hitBox.x = target.get(i).hitBoxDefaultX;
                    target.get(i).hitBox.y = target.get(i).hitBoxDefaultY;
                }
            }
        }
        
        
        return ifCollide;
    }
    
    public void checkEntity (Entity entity, ArrayList<Entity> target)
    {
        int index = 999;
        
        for (int i = target.size() - 1; i >= 0; i--)
        {
            if (target.get(i) != null && target.get(i) != entity && target.get(i).isAlive == true)
            {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                target.get(i).solidArea.x = target.get(i).worldX + target.get(i).solidArea.x;
                target.get(i).solidArea.y = target.get(i).worldY + target.get(i).solidArea.y;
                
                switch (entity.direction)
                {
                    case"up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case"down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case"left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(target.get(i).solidArea))
                {
                    entity.collisionOn = true;
                }
                switch (entity.direction)
                {
                    case "upLeft":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "downLeft":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "downRight":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "upRight":
                        entity.solidArea.y -= entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(target.get(i).solidArea))
                {
                    entity.collisionSide1 = true;
                }
                else
                {
                    switch(entity.direction){
                    case "upLeft":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "downLeft":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "downRight":
                        entity.solidArea.x += entity.speed;
                        break;
                    case "upRight":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                    if (entity.solidArea.intersects(target.get(i).solidArea))
                    {
                        entity.collisionSide2 = true;
                    }
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.get(i).solidArea.x = target.get(i).solidAreaDefaultX;
                target.get(i).solidArea.y = target.get(i).solidAreaDefaultY;
            }
        }
    }
    
    public void checkPlayer(Entity entity)
    {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction)
        {
            case"up":
                entity.solidArea.y -= entity.speed;
                break;
            case"down":
                entity.solidArea.y += entity.speed;
                break;
            case"left":
                entity.solidArea.x -= entity.speed;
                break;
            case"right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea))
        {
            entity.collisionOn = true;
        }
        switch (entity.direction)
        {
            case "upLeft":
                entity.solidArea.y -= entity.speed;
                break;
            case "downLeft":
                entity.solidArea.y += entity.speed;
                break;
            case "downRight":
                entity.solidArea.y += entity.speed;
                break;
            case "upRight":
                entity.solidArea.y -= entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea))
        {
            entity.collisionSide1 = true;
        }
        else
        {
            switch(entity.direction){
            case "upLeft":
                entity.solidArea.x -= entity.speed;
                break;
            case "downLeft":
                entity.solidArea.x -= entity.speed;
                break;
            case "downRight":
                entity.solidArea.x += entity.speed;
                break;
            case "upRight":
                entity.solidArea.x += entity.speed;
                break;
        }
            if (entity.solidArea.intersects(gp.player.solidArea))
            {
                entity.collisionSide2 = true;
            }
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}
