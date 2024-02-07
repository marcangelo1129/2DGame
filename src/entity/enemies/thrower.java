/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.enemies;

import entity.Entity;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class thrower extends Entity {
    
    GamePanel gp;
    
    Point point = new Point();
    Point playerPoint = new Point();
    
    public thrower(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        hp = 65;
        maxHp = 65;
        speed = 3;
        damage = 7;
        moveAnimateFrames = 8;
        attackAnimateFrames = 7;
        deadAnimateFrames = 8;
        spriteWidth = 26;
        spriteHeight = 26;
        imageWidth = 32;
        imageHeight = 32;
        animationSpeed = 2;
        direction = "down";
        solidArea = new Rectangle(23, 34, 32, 44);
        hitBox = new Rectangle (23, 14, 32, 64);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        dyingTime = 1000;
        attackTime = 1000;
        range = 500;
        attackRange = 150;
        onPath = true;
        isAlive = true;
        entityState = "move";
        score = 15;
        try
        {
            imageTileMap = ImageIO.read(getClass().getResourceAsStream("/enemies/thrower.png"));
            for (int i = 0; i < moveAnimateFrames; i++)
            {
                moveAnimation.add(imageTileMap.getSubimage(imageWidth * i, 0, imageWidth, imageHeight));
                moveAnimation.set(i, uTool.scaleImage(moveAnimation.get(i),spriteWidth * gp.tileScaling, spriteHeight * gp.tileScaling));
            }
            for (int i = 0; i < attackAnimateFrames; i++)
            {
                attackAnimation.add(imageTileMap.getSubimage(imageWidth * i, 32, imageWidth, imageHeight));
                attackAnimation.set(i, uTool.scaleImage(attackAnimation.get(i),spriteWidth * gp.tileScaling, spriteHeight * gp.tileScaling));
            }
            for (int i = 0; i < deadAnimateFrames; i++)
            {
                deadAnimation.add(imageTileMap.getSubimage(imageWidth * i, 64, imageWidth, imageHeight));
                deadAnimation.set(i, uTool.scaleImage(deadAnimation.get(i),spriteWidth * gp.tileScaling, spriteHeight * gp.tileScaling));
            }
        } catch (IOException ex) {
            Logger.getLogger(thrower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void dropLoot()
    {
        gp.lootSystem.spawnLoot(worldX, worldY, "rare", 500);
    }
    
    
    @Override
    public void attack()
    {
        
        if (attackCounter <= attackTime - 200)
        {
            if (damaged == false)
            {
                point.x = worldX + (image.getWidth() / 2);
                point.y = worldY + (image.getHeight() / 2);
                playerPoint.x = gp.player.worldX + gp.player.solidArea.x + (gp.player.solidArea.width / 2);
                playerPoint.y = gp.player.worldY + gp.player.solidArea.y + (gp.player.solidArea.height / 2);
                
                double angle = uTool.getAngle(point, playerPoint);
                gp.att.rockCreator(angle, point.x, point.y, damage, 1, false, this);
                
                //gp.player.inflictDamage(damage);
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
}
