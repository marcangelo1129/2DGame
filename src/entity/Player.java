/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.Main;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class Player extends Entity {
    GamePanel gp;
    KeyHandler KeyHandler;
    Main Main;
    
    public final int screenX;
    public final int screenY;
    String mDirection;
    
    public object.guns.WeaponObject[] weaponStorage = new object.guns.WeaponObject[4];
    public int equippedWeapon = 0;
    
    public Player (GamePanel gamepanel, KeyHandler KeyHandler, Main Main)
    {
        this.gp = gamepanel;
        this.KeyHandler = KeyHandler;
        this.Main = Main;
        
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        screenX = gamepanel.screenWidth/2 - (gamepanel.tileSize/2);
        screenY = gamepanel.screenHeight/2 - (gamepanel.tileSize/2);
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 20;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage()
    {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }
    public BufferedImage setup(String imageName)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){e.printStackTrace();}
        return image;
    }
    
    public void update()
    {
        if (KeyHandler.onePressed == true)
        {
            equippedWeapon = 0;
            KeyHandler.onePressed = false;
        }
        if (KeyHandler.twoPressed == true)
        {
            equippedWeapon = 1;
            KeyHandler.twoPressed = false;
        }
        if (KeyHandler.threePressed == true)
        {
            equippedWeapon = 2;
            KeyHandler.threePressed = false;
        }
        
        
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
            gp.cChecker.checkTile(this);
            
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            gp.cChecker.checkDecoCollision(this);
            
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
    }
    public void pickUpObject(int i)
    {
        if (i != 999)
        {
            String objectName = gp.obj[i].name;
            
            switch (objectName)
            {
                case "AmmoBox":
                    break;
                case "AssaultRifle":
                    weaponStorage[0] = new object.guns.Gun_AssaultRifle();
                    gp.ui.showMessage("Assault Rifle Equipped", screenX-43, screenY);
                    break;
            }
            gp.obj[i] = null;
        }
    }
    
    public void draw(Graphics2D g2 )
    {
        BufferedImage image = null;
        if (mDirection == "left")
        {
            if (spriteNum == 1)
                image = left1;
            if (spriteNum == 2)
                image = left2;
        }
        else
        {
            if (spriteNum == 1)
                image = right1;
            if (spriteNum == 2)
                image = right2;
        }
        g2.drawImage(image,screenX ,screenY , null);
    }
    public void drawWeapon(Graphics2D g2)
    {   
        Point Mouse = Main.getMouseCoordinates();
        Point Character = new Point(screenX+32,screenY+55);
        double Angle = getAngle(Character, Mouse) - 90;
        
        if (Angle >= -90 && Angle < 90)
            mDirection = "right";
        else
            mDirection = "left";
        
        BufferedImage image = null;
        if (weaponStorage[equippedWeapon] != null)
            {
                image = weaponStorage[equippedWeapon].image;
                int weaponCenterX = screenX - weaponStorage[equippedWeapon].centerX;
                int weaponCenterY = screenY + weaponStorage[equippedWeapon].centerY;
                if (Angle >= -90 && Angle < 90)
                {
                    g2.rotate(Math.toRadians(Angle), screenX+24, screenY+25);
                    g2.drawImage(image, weaponCenterX ,weaponCenterY ,weaponStorage[equippedWeapon].weaponWidth, weaponStorage[equippedWeapon].weaponHeight, null);
                    g2.rotate(Math.toRadians(-Angle), screenX+24, screenY+25);
                }
                else
                {
                    AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                    tx.translate(0, -image.getHeight(null));
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    image = op.filter(image, null);
                    g2.translate(0,-13);
                    g2.rotate(Math.toRadians(Angle), screenX+24, screenY+38);
                    g2.drawImage(image, weaponCenterX ,weaponCenterY ,weaponStorage[equippedWeapon].weaponWidth, weaponStorage[equippedWeapon].weaponHeight, null);
                    g2.rotate(Math.toRadians(-Angle), screenX+24, screenY+38);
                    g2.translate(0,13);
                }
            }
        
    }
    
    public static double getAngle(Point centerPt, Point targetPt)
{
    double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);
    theta += Math.PI/2.0;
    double angle = Math.toDegrees(theta);
    if (angle < 0) {
        angle += 360;
    }
    return angle;
}
}
