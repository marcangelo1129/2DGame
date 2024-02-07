/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import ai.PathFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class Entity {
    GamePanel gp;
    public PathFinder pFinder;
    
    public Entity (GamePanel gp)
    {
        this.gp = gp;
        pFinder = new PathFinder(gp);
    }
    
    public int screenX;
    public int screenY;
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage[] left = new BufferedImage[10];
    public BufferedImage[] right = new BufferedImage[10];
    public BufferedImage image;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public int maxHp;
    public int hp;
    
    public Rectangle solidArea = new Rectangle(8, 4, 32, 44);
    public Rectangle hitBox;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;
    public boolean collisionSide1 = false;
    public boolean collisionSide2 = false;
    boolean isLeft = false;
    public boolean isAlive;
    public boolean lootDropped = false;
    
    public UtilityTool uTool = new UtilityTool();
    
    //enemy unit variables
    public String name;
    public BufferedImage imageTileMap;
    public int damage;
    public int range;
    public int attackRange;
    public int animationSpeed;
    public int spriteWidth;
    public int spriteHeight;
    public int imageWidth;
    public int imageHeight;
    
    
    public ArrayList<BufferedImage> moveAnimation = new ArrayList<>();
    public ArrayList<BufferedImage> attackAnimation = new ArrayList<>();
    public ArrayList<BufferedImage> deadAnimation = new ArrayList<>();
    public ArrayList<String> bulletReceived = new ArrayList<>();
    public int dyingTime;
    public int attackTime;
    public int attackCounter;
    public int hpTimer;
    
    public int moveAnimateFrames;
    public int attackAnimateFrames;
    public int deadAnimateFrames;
    
    public int moveFrame = 0;
    public int attackFrame = 0;
    public int deadFrame = 0;
    
    public String entityState;
    public boolean onPath = false;
    public Random random = new Random();
    public boolean damaged;
    public int score;
    
    public boolean idleMode = false;
    public int idleTime = 0;
    
    public void Update()
    {
        spriteCounter++;
        if (spriteCounter > 2)
        {
            if (entityState == "move")
            {
                if (moveFrame < moveAnimateFrames)
                {
                    image = moveAnimation.get(moveFrame);
                    moveFrame++;
                }
                else
                    moveFrame = 0;
            }
            if (entityState == "attack")
            {
                image = attackAnimation.get(attackFrame);
                if (attackFrame < attackAnimateFrames - 1)
                {
                    attackFrame++;
                }
            }
            if (entityState == "dead")
            {
                image = deadAnimation.get(deadFrame);
                if (deadFrame == 0)
                    gp.sound.playZombieDeath();
                if (deadFrame < deadAnimateFrames - 1)
                {
                    deadFrame++;
                }
            }
            spriteCounter = 0;
        }
        
        hpTimer -= 16;
        if (isAlive == false)
        {
            if (lootDropped == false)
            {
                dropLoot();
                gp.player.score += score;
                lootDropped = true;
            }
            direction = "none";
            return;
        }
        if (entityState == "attack")
        {
            attack();
            return;
        }
        if (Math.hypot((gp.player.worldX + gp.player.solidArea.x + (gp.player.solidArea.width / 2)) - (worldX + solidArea.x + (solidArea.width / 2)), (gp.player.worldY + gp.player.solidArea.y + (gp.player.solidArea.height / 2)) - (worldY + solidArea.y + (solidArea.height / 2))) <= range)
        {
            gp.sound.playZombieAttack();
            entityState = "attack";
            attackCounter = attackTime;
            attackFrame = 0;
            damaged = false;
        }
        
        if (idleMode == true)
        {
            if (idleTime >= 3000)
                idleMode = false;
            else
                idleTime += 16;
        }
        else
        {
            if (idleTime >= 1000)
            {
                int chances = random.nextInt(1,100);
                if (chances <= 15)
                {
                    idleMode = true;
                    idleTime = 0;
                    int randDirection = random.nextInt(1,8);

                    switch (randDirection)
                    {
                        case 1:
                            direction = "up";
                            break;
                        case 2:
                            direction = "down";
                            break;
                        case 3:
                            direction = "left";
                            break;
                        case 4:
                            direction = "right";
                            break;
                        case 5:
                            direction = "upLeft";
                            break;
                        case 6:
                            direction = "upRight";
                            break;
                        case 7:
                            direction = "downLeft";
                            break;
                        case 8:
                            direction = "downRight";
                    }
                }
                idleTime = 0;
            }
            else
                idleTime += 10;
        }
        
        
        
        
        
//        if (onPath == true)
//        {
//            int goalColArea = gp.player.worldX;
//            int goalRowArea = gp.player.worldY;
//            int goalCol = goalColArea / gp.tileSize;
//            int goalRow = goalRowArea / gp.tileSize;
//            //System.out.println(gp.player.worldX +" "+ gp.player.worldY);
//            
//            searchPath(goalCol, goalRow);
//        }
        
        checkCollision();
        if (collisionOn == false)
        {
            switch(direction){

                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        if (collisionSide1 == false)
            {
                switch(direction){
                    case "upLeft":
                        worldY -= speed;
                        break;
                    case "downLeft":
                        worldY += speed;
                        break;
                    case "downRight":
                        worldY += speed;
                        break;
                    case "upRight":
                        worldY -= speed;
                        break;
                }
            }
        gp.cChecker.checkDecoCollision(this);
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.entityList);
        if (collisionSide2 == false)
        {
            switch(direction){
                case "upLeft":
                    worldX -= speed;
                    break;
                case "downLeft":
                    worldX -= speed;
                    break;
                case "downRight":
                    worldX += speed;
                    break;
                case "upRight":
                    worldX += speed;
                    break;
            }
        }
    }
    
    public void draw(Graphics2D g2 )
    {
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);
        
        switch(direction){

                case "upRight":
                case "downRight":
                case "right":
                    isLeft = false;
                    break;
                case "upLeft":
                case "downLeft":
                case "left":
                    isLeft = true;
                    break;
            }
        
        if (isLeft == true && image != null)
        {
            g2.drawImage(image,screenX + image.getWidth(),screenY,-image.getWidth(),image.getHeight(), null);
        }
        else if (image != null)
        {
            g2.drawImage(image,screenX,screenY, null);
        }
        Color defaultColor = g2.getColor();
        if (gp.dw.DisplayCollisionBox.isSelected())
        {
            g2.setColor(Color.cyan);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
        if (gp.dw.DisplayHitBox.isSelected())
        {
            g2.setColor(Color.red);
            g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
        }
        g2.setColor(defaultColor);
        if (hpTimer > 0 && isAlive == true)
        {
            int centerX = screenX + solidArea.x + (solidArea.width / 2);
            int centerY = screenY + solidArea.y + (solidArea.height / 2);
            int hpBarWidth = 36;
            int hpBarHeight = 4;
            
            double hpBarPercent = (double) hp / (double) maxHp;
            if (hpBarPercent > 0)
            {
                Color color = g2.getColor();
                g2.setColor(Color.DARK_GRAY);
                g2.fillRect(centerX - (hpBarWidth / 2) - 1, centerY - (hpBarHeight / 2) - 49, hpBarWidth + 2, hpBarHeight + 2);
                g2.setColor(Color.red);
                g2.fillRect(centerX - (hpBarWidth / 2), centerY - (hpBarHeight / 2) - 48, (int) (hpBarWidth * hpBarPercent), hpBarHeight);
                g2.setColor(color);
            }
        }
    }
    public void attack()
    {
        if (attackCounter <= attackTime - 200 && Math.hypot((gp.player.worldX + gp.player.solidArea.x + (gp.player.solidArea.width / 2)) - (worldX + solidArea.x + (solidArea.width / 2)), (gp.player.worldY + gp.player.solidArea.y + (gp.player.solidArea.height / 2)) - (worldY + solidArea.y + (solidArea.height / 2))) <= attackRange)
        {
            if (damaged == false)
            {
                gp.player.inflictDamage(damage);
                damaged = true;
            }
        }
        
        if (attackCounter > 0)
        {
            attackCounter -= 16;
        }
        else
        {
            entityState = "move";
        }
    }
    
    public void dropLoot(){};
    
    public void checkCollision()
    {
        collisionOn = false;
        collisionSide1 = false;
        collisionSide2 = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkDecoCollision(this);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkEntity(this, gp.entityList);
    }
    
    public void searchPath(int goalCol, int goalRow)
    {
        if (idleMode == true)
        {
            //pFinder.resetNodes();
            return;
        }
        
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        
        pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        
        if (pFinder.search() == true)
        {
            if (pFinder.pathList.isEmpty())
                return;
            int nextX = pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = pFinder.pathList.get(0).row * gp.tileSize;
            
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            
            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize)
            {
                if (enLeftX > nextX)
                {
                    direction = "left";
                }
                if (enLeftX < nextX)
                {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX)
            {
                direction = "upLeft";
            }
            else if (enTopY > nextY && enLeftX < nextX)
            {
                direction = "upRight";
            }
            else if (enTopY < nextY && enLeftX > nextX)
            {
                direction = "downLeft";
            }
            else if (enTopY < nextY && enLeftX < nextX)
            {
                direction = "downRight";
            }
            
            int nextCol = pFinder.pathList.get(0).col;
            int nextRow = pFinder.pathList.get(0).row;
            
            if (nextCol == goalCol && nextRow == goalRow)
            {
                //onPath = false;
            }
        }
    }
}
