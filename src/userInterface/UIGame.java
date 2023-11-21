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
    UIObject[] UIObject = new UIObject[6];
    
    public UIGame(GamePanel gp, Player player)
    {
        try {
            this.UIfont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\Valorax.otf")).deriveFont(12f);
        } catch (FontFormatException ex) {
        } catch (IOException ex) {
        }
        this.gp = gp;
        this.player = player;
        MsgFont = new Font("Arial", MsgFont.PLAIN, 15);
        UIObject[0] = new BTN_1();
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
        g2.drawString("Assault Rifle", 25, 50);
        int equippedWeapon = player.equippedWeapon;
        if (player.weaponStorage[equippedWeapon] != null)
        {
            Image image = player.weaponStorage[equippedWeapon].image;
            g2.drawImage(image, 10, 10,96,32,null);
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
