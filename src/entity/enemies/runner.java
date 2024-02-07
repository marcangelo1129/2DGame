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
public class runner extends Entity {
    
    GamePanel gp;
    
    public runner(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        hp = 50;
        maxHp = 50;
        speed = 6;
        damage = 5;
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
        attackTime = 500;
        range = 80;
        attackRange = 150;
        onPath = true;
        isAlive = true;
        entityState = "move";
        score = 20;
        try
        {
            imageTileMap = ImageIO.read(getClass().getResourceAsStream("/enemies/runner.png"));
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
            Logger.getLogger(runner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void dropLoot()
    {
        gp.lootSystem.spawnLoot(worldX, worldY, "rare", 750);
    }
    
}
