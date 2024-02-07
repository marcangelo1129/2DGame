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
public class zombie extends Entity {
    
    GamePanel gp;
    
    public zombie(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        hp = 100;
        maxHp = 100;
        speed = 3;
        damage = 10;
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
        attackTime = 700;
        range = 80;
        attackRange = 150;
        onPath = true;
        isAlive = true;
        entityState = "move";
        score = 15;
        try
        {
            imageTileMap = ImageIO.read(getClass().getResourceAsStream("/enemies/zombie.png"));
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
            Logger.getLogger(zombie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void dropLoot()
    {
        gp.lootSystem.spawnLoot(worldX, worldY, "common", 750);
    }
    
}
