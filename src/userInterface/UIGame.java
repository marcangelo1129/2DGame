/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import entity.Player;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.Main;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class UIGame {
    GamePanel gp;
    Player player;
    Font UIfont;
    Font MsgFont;
    public String message = "";
    Color messageColor;
    public boolean messageOn = false;
    Point messageloc = null;
    int messageCounter = 0;
    float transparency = 1;
    UIObject[] UIObject = new UIObject[8];
    BufferedImage statsBackground, hpIcon, coin, waveBackground;
    BufferedImage cursor;
    Main Main;
    UtilityTool uTool = new UtilityTool();
    Graphics2D g2;
    
    public UIGame(GamePanel gp, Player player, Main Main)
    {
        try {
            UIfont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(17f);
            cursor = ImageIO.read(getClass().getResourceAsStream("/objects/Cursor.png"));
            cursor = uTool.scaleImage(cursor, 36, 36);
            
            statsBackground = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/playerStatsBackground.png"));
            statsBackground = uTool.scaleImage(statsBackground, 413, 67);
            hpIcon = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/hpIcon.png"));
            hpIcon = uTool.scaleImage(hpIcon, 21, 21);
            coin = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/coin.png"));
            coin = uTool.scaleImage(coin, 21, 21);
            
            waveBackground = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/waveBackground.png"));
            waveBackground = uTool.scaleImage(waveBackground, 170, 67);
        } catch (FontFormatException | IOException ex) {}
        
        this.gp = gp;
        this.player = player;
        this.Main = Main;
        MsgFont = new Font("Arial", MsgFont.PLAIN, 15);
        UIObject[0] = new BTN_1();
        UIObject[1] = new BTN_2();
        UIObject[2] = new BTN_3();
        UIObject[3] = new BTN_1_Pressed();
        UIObject[4] = new BTN_2_Pressed();
        UIObject[5] = new BTN_3_Pressed();
        UIObject[6] = new weaponBackground();
        UIObject[7] = new ammo_icon();
    }
    
    public void showMessage(String text, int x, int y, Color color)
    {
        transparency = 1;
        message = text;
        messageOn = true;
        messageCounter = 0;
        messageloc = new Point(x,y);
        messageColor = color;
    }
    
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        Point Mouse = Main.getMouseCoordinates();
        if (gp.shop.showShop == false && gp.pauseMenu.paused == false)
        {
            g2.drawImage(cursor, Mouse.x-17, Mouse.y-17, null);
        }
        g2.setFont(UIfont);
        g2.setColor(Color.white);
        
        //windows
        drawWeaponUI(g2);
        drawPlayerStats(g2);
        drawWave(g2);
        
        if (messageOn == true)
        {
            g2.setFont(MsgFont);
            AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
            g2.setComposite(alcom);
            //g2.drawString(message, messageloc.x, messageloc.y);
            Color colorTemp = g2.getColor();
            g2.setColor(messageColor);
            centerText(message, messageloc.x, messageloc.y);
            g2.setColor(colorTemp);
            if (messageCounter % 2 == 1)
                messageloc.y--;
            if (messageCounter > 120)
            {
                messageCounter = 0;
                messageOn = false;
            }
            else if (messageCounter > 60)
            {
                transparency -= 0.016f;
            }
            
            messageCounter++;
        }
        
        //g2 reset
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(alcom);
            
    }
    
    public void centerText(String text, int x, int y)
    {
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        int textCenterX = (metrics.stringWidth(text) / 2);
        int textCenterY = metrics.getHeight() / 2 - metrics.getAscent();
        
        g2.drawString(text, x - textCenterX, y - textCenterY);
    }
    
    public void drawWave(Graphics2D g2)
    {
        int waveX = 10;
        int waveY = 10;
        g2.drawImage(waveBackground, waveX, waveY, null);
        g2.drawString("WAVE: "+player.waveNumber, waveX + 20, waveY + 37);
    }
    
    public void drawPlayerStats(Graphics2D g2)
    {
        int SBX = gp.screenSize.width - statsBackground.getWidth() - 10;
        int SBY = gp.screenSize.height - statsBackground.getHeight() - 10;
        g2.drawImage(statsBackground, SBX, SBY, null);
        
        g2.drawString("Score: "+player.score, SBX + 20, SBY + 37);
        g2.drawImage(coin, SBX + 190, SBY + (statsBackground.getHeight() / 2) - (coin.getHeight() / 2) - 3, null);
        g2.drawString(""+player.coins, SBX + 220, SBY + 37);
        g2.drawImage(hpIcon, SBX + 310, SBY + (statsBackground.getHeight() / 2) - (hpIcon.getHeight() / 2) - 3, null);
        Color color = g2.getColor();
        if (player.hp > 50)
            g2.setColor(Color.green);
        else if (player.hp > 25)
            g2.setColor(Color.orange);
        else
            g2.setColor(Color.red);
            
        g2.drawString(""+player.hp, SBX + 340, SBY + 37);
        g2.setColor(color);
    }
    
    public void drawWeaponUI(Graphics2D g2)
    {
        
        int WBX = UIObject[6].area.x;
        int WBY = gp.screenSize.height - UIObject[6].area.y;
        g2.drawImage(UIObject[6].image, WBX, WBY,null);
        int equippedWeapon = player.equippedWeapon;
        if (player.weaponStorage[equippedWeapon] != null)
        {
            BufferedImage UIimage = player.weaponStorage[equippedWeapon].UIimage;
            g2.drawString(player.weaponStorage[equippedWeapon].name, WBX+25, WBY+29);
            g2.drawImage(UIimage, WBX+35, WBY+55 - (UIimage.getHeight() / 2),null);
            g2.drawImage(UIObject[7].image, WBX+40, WBY+85,null);
            
            int AmmoinClipRemaining = player.weaponStorage[equippedWeapon].ammoInClipRemaining;
            int AmmoRemaining = player.weaponStorage[equippedWeapon].ammoRemaining;
            String AmmoDisplay;
            if (gp.player.weaponStorage[gp.player.equippedWeapon].secondary == true)
                AmmoDisplay = ""+AmmoinClipRemaining+" / UNLI";
            else
                AmmoDisplay = ""+AmmoinClipRemaining+" / "+AmmoRemaining;
            
            g2.drawString(AmmoDisplay, WBX+75, WBY+111);
        }
        if (equippedWeapon == 0)
        {
            g2.drawImage(UIObject[3].image, WBX+202, WBY+90, null);
            g2.drawImage(UIObject[1].image, WBX+235, WBY+90, null);
            g2.drawImage(UIObject[2].image, WBX+232, WBY+55, null);
        }
        else if (equippedWeapon == 1)
        {
            g2.drawImage(UIObject[0].image, WBX+202, WBY+90, null);
            g2.drawImage(UIObject[4].image, WBX+235, WBY+90, null);
            g2.drawImage(UIObject[2].image, WBX+232, WBY+55, null);
        }
        else if (equippedWeapon == 2)
        {
            g2.drawImage(UIObject[0].image, WBX+202, WBY+90, null);
            g2.drawImage(UIObject[1].image, WBX+235, WBY+90, null);
            g2.drawImage(UIObject[5].image, WBX+232, WBY+55, null);
        }
    }
}
