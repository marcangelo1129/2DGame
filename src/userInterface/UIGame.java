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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class UIGame {
    GamePanel gp;
    Player player;
    Font UIfont;
    Font MsgFont;
    BufferedImage UIImage;
    public String message = "";
    public boolean messageOn = false;
    Point messageloc = null;
    int messageCounter = 0;
    float transparency = 1;
    UIObject[] UIObject = new UIObject[8];
    
    public UIGame(GamePanel gp, Player player)
    {
        try {
            this.UIfont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\Valorax.otf")).deriveFont(17f);
        } catch (FontFormatException ex) {
        } catch (IOException ex) {
        }
        this.gp = gp;
        this.player = player;
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
    
    public void showMessage(String text, int x, int y)
    {
        message = text;
        messageOn = true;
        messageloc = new Point(x,y);
    }
    
    public void draw(Graphics2D g2)
    {
        g2.setFont(UIfont);
        g2.setColor(Color.white);
        int WBX = UIObject[6].area.x;
        int WBY = UIObject[6].area.y;
        g2.drawImage(UIObject[6].image, WBX, WBY,null);
        int equippedWeapon = player.equippedWeapon;
        if (player.weaponStorage[equippedWeapon] != null)
        {
            Image image = player.weaponStorage[equippedWeapon].image;
            Image UIimage = player.weaponStorage[equippedWeapon].UIimage;
            g2.drawString(player.weaponStorage[equippedWeapon].name, WBX+25, WBY+36);
            g2.drawImage(UIimage, WBX+35, WBY+40,null);
            g2.drawImage(UIObject[7].image, WBX+40, WBY+85,null);
            
            int MaxAmmoPerClip = player.weaponStorage[equippedWeapon].MaxAmmoPerClip;
            int AmmoinClipRemaining = player.weaponStorage[equippedWeapon].AmmoinClipRemaining;
            int AmmoRemaining = player.weaponStorage[equippedWeapon].AmmoRemaining;
            String AmmoDisplay = ""+AmmoinClipRemaining+"/"+MaxAmmoPerClip+" "+AmmoRemaining;
            
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
        
        if (messageOn == true)
        {
            g2.setFont(MsgFont);
            AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
            g2.setComposite(alcom);
            g2.drawString(message, messageloc.x, messageloc.y);
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
}
