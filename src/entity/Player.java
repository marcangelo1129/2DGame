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
import java.net.URL;
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
    KeyHandler KeyHandler;
    Main Main;
    
    public int screenX;
    public int screenY;
    String mDirection;
    public Projectile projectile;
    public Point centerPoint;
    public int coins;
    public int score = 0;
    public int waveNumber = 1;
    public boolean gameOver = false;
    public boolean powerMeterSound = false;
    public int powerMeterDelay = 200;
    
    public int regenCooldown = 0;
    public int regenCounter = 0;
    
    public object.guns.WeaponObject[] weaponStorage = new object.guns.WeaponObject[4];
    muzzleFlash muzzleFlash = new muzzleFlash();
    Random random = new Random();
    public BufferedImage weapon;
    public int equippedWeapon = 1;
    public double weaponAngle;
    
    URL[] hitSound = new URL[4];
    public URL[] lostSound = new URL[2];
    
    public Player (GamePanel gp, KeyHandler KeyHandler, Main Main)
    {
        super(gp);
        this.KeyHandler = KeyHandler;
        this.Main = Main;
        
        solidArea = new Rectangle(8, 4, 32, 44);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        screenX = gp.screenSize.width/2 - (gp.tileSize/2);
        screenY = gp.screenSize.height/2 - (gp.tileSize/2);
        
        hitSound[0] = getClass().getResource("/playerSound/1_hit.wav");
        hitSound[1] = getClass().getResource("/playerSound/2_hit.wav");
        hitSound[2] = getClass().getResource("/playerSound/3_hit.wav");
        hitSound[3] = getClass().getResource("/playerSound/4_hit.wav");
        
        lostSound[0] = getClass().getResource("/playerSound/1_lost.wav");
        lostSound[1] = getClass().getResource("/playerSound/2_lost.wav");
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 20;
        speed = 4;
        direction = "left";
        hp = 100;
        coins = 0;
        centerPoint = new Point(screenX+24,screenY+24);
    }
    public void getPlayerImage()
    {
        left[0] = setup("idle_left");
        left[1] = setup("run_left_1");
        left[2] = setup("run_left_2");
        left[3] = setup("run_left_3");
        left[4] = setup("run_left_4");
        left[5] = setup("run_left_5");
        left[6] = setup("run_left_6");
        
        right[0] = setup("idle_right");
        right[1] = setup("run_right_1");
        right[2] = setup("run_right_2");
        right[3] = setup("run_right_3");
        right[4] = setup("run_right_4");
        right[5] = setup("run_right_5");
        right[6] = setup("run_right_6");
    }
    public BufferedImage setup(String imageName)
    {
        double scaling = 1.25f;
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, (int) (29 * scaling), (int) (55 * scaling));
        }catch (IOException e){e.printStackTrace();}
        return image;
    }
    
    public void update()
    {
        if (KeyHandler.onePressed == true)
        {
            if (gp.player.weaponStorage[0] != null)
            {
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX = gp.player.weaponStorage[gp.player.equippedWeapon].defaultCenterX;
                if (gp.att.firing == true)
                {
                    KeyHandler.twoPressed = false;
                    KeyHandler.threePressed = false;
                }
                else
                {
                    if (equippedWeapon != 0)
                        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_equip.wav"));
                    equippedWeapon = 0;
                    if (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                        gp.att.burstCount = gp.player.weaponStorage[gp.player.equippedWeapon].burstCount;
                    KeyHandler.onePressed = false;
                    if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining == 0)
                    {
                        KeyHandler.RPressed = true;
                        KeyHandler.executorService.execute(gp.att);
                    }
                }
            }
            else
                KeyHandler.onePressed = false;
        }
        if (KeyHandler.twoPressed == true)
        {
            if (gp.player.weaponStorage[1] != null)
            {
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX = gp.player.weaponStorage[gp.player.equippedWeapon].defaultCenterX;
                if (gp.att.firing == true)
                {
                    KeyHandler.onePressed = false;
                    KeyHandler.threePressed = false;
                }
                else
                {
                    if (equippedWeapon != 1)
                        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_equip.wav"));
                    equippedWeapon = 1;
                    if (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                        gp.att.burstCount = gp.player.weaponStorage[gp.player.equippedWeapon].burstCount;
                    KeyHandler.twoPressed = false;
                    if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining == 0)
                    {
                        KeyHandler.RPressed = true;
                        KeyHandler.executorService.execute(gp.att);
                    }
                }
            }
            else
                KeyHandler.twoPressed = false;
        }
        if (KeyHandler.threePressed == true)
        {
            if (gp.player.weaponStorage[2] != null)
            {
                gp.player.weaponStorage[gp.player.equippedWeapon].centerX = gp.player.weaponStorage[gp.player.equippedWeapon].defaultCenterX;
                if (gp.att.firing == true)
                {
                    KeyHandler.twoPressed = false;
                    KeyHandler.onePressed = false;
                }
                else
                {
                    if (equippedWeapon != 2)
                    {
                        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_equip.wav"));
                        powerMeterDelay = 0;
                    }
                    equippedWeapon = 2;
                    if (gp.player.weaponStorage[gp.player.equippedWeapon] != null)
                        gp.att.burstCount = gp.player.weaponStorage[gp.player.equippedWeapon].burstCount;
                    KeyHandler.threePressed = false;
                    if (gp.player.weaponStorage[gp.player.equippedWeapon].ammoInClipRemaining == 0)
                    {
                        KeyHandler.RPressed = true;
                        KeyHandler.executorService.execute(gp.att);
                    }
                }
            }
            else
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
            
            gp.cChecker.checkEntity(this, gp.entityList);
            
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
            if (spriteCounter > 2)
            {
                if (direction == "upLeft" || direction == "downLeft")
                {
                    direction = "left";
                }
                if (direction == "upRight" || direction == "downRight")
                {
                    direction = "right";
                }
                if (direction == "down" || direction == "up")
                {
                    direction = mDirection;
                }
                if (direction == "left" || direction == "right")
                {
                    if (direction == mDirection)
                    {
                        int index = spriteNum + 1;
                        if (index > 6)
                            spriteNum = 1;
                        else
                            spriteNum++;
                    }
                    else
                    {
                        int index = spriteNum - 1;
                        if (index < 1)
                            spriteNum = 6;
                        else
                            spriteNum--;
                    }
                        
                }
                spriteCounter = 0;
            }
        }
        else
        {
            spriteNum = 0;
        }
        if (powerMeterDelay < 1000)
            powerMeterDelay += 10;
        if (powerMeterSound == true && powerMeterDelay == 200)
        {
            gp.sound.playSFX(getClass().getResource("/playerSound/power_meter_full.wav"));
            powerMeterSound = false;
        }
        if (regenCooldown <= 0 && hp < 100)
            hp++;
        else
            regenCooldown -= 16;
        
    }
    public void pickUpObject(int i)
    {
        if (i != 999)
        {
            gp.obj.get(i).pickUp();
            gp.obj.remove(i);
        }
    }
    
    public void draw(Graphics2D g2 )
    {
        BufferedImage image = null;
        if (mDirection == "left")
        {
            image = left[spriteNum];
        }
        else
        {
            image = right[spriteNum];
        }
        
        g2.drawImage(image,screenX + (gp.tileSize / 2) - (image.getWidth() / 2) ,screenY  - (image.getHeight() - 48), null);
    }
    public void drawWeapon(Graphics2D g2)
    {   
        centerPoint.x = screenX+24;
        centerPoint.y = screenY+17;
        Point Mouse = Main.getMouseCoordinates();
        if (gameOver == false && gp.shop.showShop == false && gp.pauseMenu.paused == false)
        {
            weaponAngle = uTool.getAngle(centerPoint, Mouse) - 90;
            if (weaponAngle >= -90 && weaponAngle < 90)
                mDirection = "right";
            else
                mDirection = "left";
        }
        
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
    
    public void inflictDamage(int damage)
    {
        if (gameOver == true)
            return;
        
        if (hp - damage > 0)
        {
            regenCooldown = 5000;
            hp -= damage;
            gp.sound.playSFX(hitSound[random.nextInt(0, 3)]);
            gp.att.intensity = 10;
            gp.att.intensityDec = gp.att.intensity / 10;
        }
        else
        {
            hp = 0;
            gp.gameoverScreen.reset();
            gameOver = true;
            gp.sound.stopMusic();
            Main.resetCursor();
            gp.sound.playSFX(getClass().getResource("/game_end.wav"));
            gp.KeyHandler.mouseLeftPressed = false;
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
