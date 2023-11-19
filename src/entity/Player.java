/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author Dangerouze
 */
public class Player extends Entity {
    GamePanel gamepanel;
    KeyHandler KeyHandler;
    
    public final int screenX;
    public final int screenY;
    
    public Player (GamePanel gamepanel, KeyHandler KeyHandler)
    {
        this.gamepanel = gamepanel;
        this.KeyHandler = KeyHandler;
        
        solidArea = new Rectangle(8, 16, 32, 32);
        
        screenX = gamepanel.screenWidth/2 - (gamepanel.tileSize/2);
        screenY = gamepanel.screenHeight/2 - (gamepanel.tileSize/2);
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX = gamepanel.tileSize * 23;
        worldY = gamepanel.tileSize * 20;
        speed = 3;
        direction = "up";
    }
    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch(IOException ex){ex.printStackTrace();}
    }
    
    
    public void update()
    {
        if (KeyHandler.upPressed == true || KeyHandler.downPressed == true || KeyHandler.leftPressed == true || KeyHandler.rightPressed == true)
        {
            if (KeyHandler.upPressed == true && KeyHandler.leftPressed == true)
                direction = "upLeft";
            else if (KeyHandler.downPressed == true && KeyHandler.leftPressed == true)
                direction = "downLeft";
            else if (KeyHandler.downPressed == true && KeyHandler.rightPressed == true)
                direction = "downRight";
            else if (KeyHandler.upPressed == true && KeyHandler.rightPressed == true)
                direction = "upRight";
            else
            {
                if (KeyHandler.upPressed == true)
                {
                    direction = "up";
                }
                if (KeyHandler.leftPressed == true)
                {
                    direction = "left";
                }
                if (KeyHandler.downPressed == true)
                {
                    direction = "down";
                }
                if (KeyHandler.rightPressed == true)
                {
                    direction = "right";
                }
            }
            
            
            collisionOn = false;
            collisionSide1 = false;
            collisionSide2 = false;
            gamepanel.cChecker.checkTile(this);
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
            switch(direction){
                case "upLeft":
                    if (collisionSide1 == false)
                        worldY -= speed;
                    if (collisionSide2 == false)
                        worldX -= speed;
                    break;
                case "downLeft":
                    if (collisionSide1 == false)
                        worldY += speed;
                    if (collisionSide2 == false)
                        worldX -= speed;
                    break;
                case "downRight":
                    if (collisionSide1 == false)
                        worldY += speed;
                    if (collisionSide2 == false)
                        worldX += speed;
                    break;
                case "upRight":
                    if (collisionSide1 == false)
                        worldY -= speed;
                    if (collisionSide2 == false)
                        worldX += speed;
                    break;
                }
            }
            

            spriteCounter++;
            if (spriteCounter > 12)
            {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        
    }
    public void draw(Graphics2D g2 )
    {
        BufferedImage image = null;
        switch(direction)
        {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;    
                break;
            case "left":
            case "downLeft":
            case "upLeft":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                break;
            case "right":
            case "downRight":
            case "upRight":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                break;
        }
        g2.drawImage(image,screenX ,screenY ,gamepanel.tileSize, gamepanel.tileSize, null);
    }
}
