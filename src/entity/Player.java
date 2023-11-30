/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.Main;
import main.UtilityTool;
import object.guns.muzzleFlash;

/**
 *
 * @author Dangerouze
 */
public class Player extends Entity {
    GamePanel gp;
    KeyHandler KeyHandler;
    Main Main;
    
    public int screenX;
    public int screenY;
    String mDirection;
    public Projectile projectile;
    public Point centerPoint;
    
    public object.guns.WeaponObject[] weaponStorage = new object.guns.WeaponObject[4];
    muzzleFlash muzzleFlash = new muzzleFlash();
    Random random = new Random();
    public BufferedImage weapon;
    public int equippedWeapon = 0;
    public double weaponAngle;
    UtilityTool uTool = new UtilityTool();
    
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
        hp = 100;
        centerPoint = new Point(screenX+24,screenY+24);
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
            System.out.print(objectName);
            switch (objectName)
            {
                case "AmmoBox":
                    if (weaponStorage[0] != null)
                    {
                        if (weaponStorage[0].AmmoRemaining + weaponStorage[0].ammoBoxIncrement <= weaponStorage[0].MaxAmmo)
                        {
                            weaponStorage[0].AmmoRemaining += weaponStorage[0].ammoBoxIncrement; 
                        }
                        else
                        {
                            weaponStorage[0].AmmoRemaining = weaponStorage[0].MaxAmmo;
                        }
                        gp.ui.showMessage("Ammo+", screenX-5, screenY);
                            
                    }
                    break;
                case "M4A1":
                    weaponStorage[0] = new object.guns.Gun_M4A1();
                    gp.ui.showMessage("M4A1 Equipped", screenX-23, screenY);
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
        centerPoint.x = screenX+24;
        centerPoint.y = screenY+24;
        Point Mouse = Main.getMouseCoordinates();
        weaponAngle = uTool.getAngle(centerPoint, Mouse) - 90;
        
        if (weaponAngle >= -90 && weaponAngle < 90)
            mDirection = "right";
        else
            mDirection = "left";
        
        if (weaponStorage[equippedWeapon] != null)
            {
                int flashRandomizer = random.nextInt(0, 4);
                weapon = weaponStorage[equippedWeapon].image;
                int recoil = 0;
                if (KeyHandler.mouseLeftPressed == true)
                    recoil = gp.att.recoil;
                    
                if (weaponAngle >= -90 && weaponAngle < 90)
                {
                    g2.rotate(Math.toRadians(weaponAngle + recoil), centerPoint.x, centerPoint.y);//6
                    //gp.att.drawWeaponAnimation(g2, weaponCenterX, weaponCenterY);
                    g2.drawImage(weapon, centerPoint.x - weaponStorage[equippedWeapon].centerX, centerPoint.y - weaponStorage[equippedWeapon].centerY , null);
                    if (gp.att.muzzleFlashTime != 0)
                    {
                        int muzzleLocX = (int) ((weaponStorage[equippedWeapon].weaponWidth * weaponStorage[equippedWeapon].weaponScaling) - weaponStorage[equippedWeapon].centerX);
                        g2.drawImage(muzzleFlash.sprites[flashRandomizer],centerPoint.x + muzzleFlash.centerX + muzzleLocX, centerPoint.y + muzzleFlash.centerY , gp);
                        gp.att.muzzleFlashTime--;
                    }
                    g2.rotate(Math.toRadians(-weaponAngle - recoil), centerPoint.x, centerPoint.y);
                }
                else
                {
                    int invertedY = (int) ((weaponStorage[equippedWeapon].weaponHeight * weaponStorage[equippedWeapon].weaponScaling) - weaponStorage[equippedWeapon].centerY);
                    AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                    tx.translate(0, -weapon.getHeight(null));
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    weapon = op.filter(weapon, null);
                    g2.rotate(Math.toRadians(weaponAngle + recoil), centerPoint.x, centerPoint.y);
                    g2.drawImage(weapon, centerPoint.x - weaponStorage[equippedWeapon].centerX, centerPoint.y - invertedY , null);
                    if (gp.att.muzzleFlashTime != 0)
                    {
                        int muzzleLocX = (int) ((weaponStorage[equippedWeapon].weaponWidth * weaponStorage[equippedWeapon].weaponScaling) - weaponStorage[equippedWeapon].centerX);
                        g2.drawImage(muzzleFlash.sprites[flashRandomizer],centerPoint.x + muzzleFlash.centerX + muzzleLocX, centerPoint.y + muzzleFlash.centerY , gp);
                        gp.att.muzzleFlashTime--;
                    }
                    g2.rotate(Math.toRadians(-weaponAngle - recoil), centerPoint.x, centerPoint.y);
                }
            }
        
    }
    
    public void cameraPosition()
    {
        Point mouse = Main.getMouseCoordinates();
        gp.pointer.x = gp.pointer.x + (mouse.x - gp.pointer.x) / 15;
        gp.pointer.y = gp.pointer.y + (mouse.y - gp.pointer.y) / 15;
        screenX = (int) (gp.centerScreenX - ((gp.pointer.x - gp.centerScreenX) / 1.5));
        screenY = (int) (gp.centerScreenY - ((gp.pointer.y - gp.centerScreenY) / 1.5));
    }
    
}
