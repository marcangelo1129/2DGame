/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;

/**
 *
 * @author Dangerouze
 */
public class CollisionChecker {
    
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void checkTile(Entity entity)
    {
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.x + entity.solidArea.height;
        
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
}
