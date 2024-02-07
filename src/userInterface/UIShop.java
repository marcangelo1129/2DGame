/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import object.guns.secondary.*;
import object.guns.shotguns.*;
import object.guns.smg.*;
import object.guns.rifles.*;
import object.guns.machineguns.*;
import object.guns.snipers.*;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.Main;
import main.UtilityTool;
import object.guns.*;

/**
 *
 * @author Dangerouze
 */
public class UIShop {
    
    GamePanel gp;
    Main Main;
    
    public boolean showShop = false;
    BufferedImage shopWindow;
    BufferedImage categoryButton;
    BufferedImage weaponButton;
    BufferedImage backButton;
    BufferedImage weaponSpecsBackground;
    BufferedImage ammoIcon;
    BufferedImage coinIcon;
    BufferedImage equippedBackground;
    int windowCenterX;
    int windowCenterY;
    Point shopWindowLoc = new Point();
    Point categoryStartLoc = new Point();
    Point weaponStartLoc = new Point();
    Point backButtonLoc = new Point();
    Font backButtonFont;
    Font categoryTextFont;
    Font specsFont;
    Font weaponNameFont;
    String[] categoryText = new String[6];
    int selectedCategory = 0;
    int selectedWeapon = 0;
    
    Rectangle backButtonBox;
    Rectangle categoryBox[] = new Rectangle[6];
    Rectangle weaponBox[] = new Rectangle[5];
    
    String hoveredButtonName;
    int hoveredButtonNumber;
    int lastHoveredButton;
    boolean showWeaponSpecs = false;
    
    WeaponObject[] secondary = new WeaponObject[5];
    WeaponObject[] shotguns = new WeaponObject[5];
    WeaponObject[] smg = new WeaponObject[5];
    WeaponObject[] rifles = new WeaponObject[5];
    WeaponObject[] machineguns = new WeaponObject[5];
    WeaponObject[] sniperRifles = new WeaponObject[5];
    
    double currentWeaponStats[] = new double[5];
    double selectedWeaponStats[] = new double[5];
    String weaponSelectedName[] = new String[3];
    BufferedImage hoveredImage;
    String hoveredWeaponName;
    String hoveredDetailText;
    int hoveredAmmoInClipRemaining;
    int hoveredAmmoRemaining;
    int hoveredWeaponCost;
    boolean ifPurchased;
    boolean equipped;
    boolean isSecondary;
    
    double weaponStartX;
    double weaponEndX;
    
    UtilityTool uTool = new UtilityTool();
    
    public UIShop (GamePanel gp, Main Main)
    {
        this.gp = gp;
        this.Main = Main;
        windowCenterX = gp.screenSize.width / 2;
        windowCenterY = gp.screenSize.height / 2;
        
        categoryText[0] = "SECONDARY";
        categoryText[1] = "SHOTGUNS";
        categoryText[2] = "SMGS";
        categoryText[3] = "RIFLES";
        categoryText[4] = "MACHINEGUNS";
        categoryText[5] = "SNIPER RIFLES";
        
        try
        {
            shopWindow = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/shopWindow.png"));
            shopWindow = uTool.scaleImage(shopWindow, 1027, 548);
            categoryButton = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/category_BTN.png"));
            categoryButton = uTool.scaleImage(categoryButton, 161, 24);
            weaponButton = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/weapon_Button.png"));
            weaponButton = uTool.scaleImage(weaponButton, 410, 78);
            backButton = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/back_BTN.png"));
            backButton = uTool.scaleImage(backButton, 135, 50);
            ammoIcon = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/ammo_icon.png"));
            ammoIcon = uTool.scaleImage(ammoIcon, 30, 30);
            coinIcon = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/coin.png"));
            coinIcon = uTool.scaleImage(coinIcon, 23, 23);
            equippedBackground = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/equippedBackground.png"));
            equippedBackground = uTool.scaleImage(equippedBackground, 392, 65);
            
            weaponSpecsBackground = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/specsBackground.png"));
            weaponSpecsBackground = uTool.scaleImage(weaponSpecsBackground, 562, 176);
            
            backButtonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(32f);
            categoryTextFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(17f);
            specsFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(15f);
            weaponNameFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(25f);
        }
        catch(IOException | FontFormatException e){}
        
        shopWindowLoc.x = windowCenterX - (shopWindow.getWidth() / 2);
        shopWindowLoc.y = windowCenterY - (shopWindow.getHeight() / 2);
        backButtonLoc.x = shopWindowLoc.x + 869;
        backButtonLoc.y = shopWindowLoc.y + 16;
        categoryStartLoc.x = shopWindowLoc.x + 17;
        categoryStartLoc.y = shopWindowLoc.y + 75;
        weaponStartLoc.x = shopWindowLoc.x + 17;
        weaponStartLoc.y = shopWindowLoc.y + 110;
        weaponEndX = shopWindowLoc.x + 441;
        
        backButtonBox = new Rectangle(backButtonLoc.x, backButtonLoc.y, backButton.getWidth(), backButton.getHeight());
        for (int i = 0; i < categoryBox.length; i++)
        {
            categoryBox[i] = new Rectangle(0,0,categoryButton.getWidth(),categoryButton.getHeight());
        }
        for (int i = 0; i < weaponBox.length; i++)
        {
            weaponBox[i] = new Rectangle(0,0,weaponButton.getWidth(),weaponButton.getHeight());
        }
        
        //secondary
        secondary[0] = new Gun_fiveseven();
        secondary[1] = new Gun_uspm();
        secondary[2] = new Gun_deagle();
        secondary[3] = new Gun_g18();
        secondary[4] = new Gun_magnum();
        
        ///shotgun
        shotguns[0] = new Gun_mossberg();
        shotguns[1] = new Gun_m1014();
        shotguns[2] = new Gun_spas12();
        shotguns[3] = new Gun_aa12();
        shotguns[4] = new Gun_jackhammer();
        
        //smg
        smg[0] = new Gun_mp5();
        smg[1] = new Gun_p90();
        smg[2] = new Gun_mp7();
        smg[3] = new Gun_augPara();
        smg[4] = new Gun_evo3();
        
        //rifles
        rifles[0] = new Gun_m4a1();
        rifles[1] = new Gun_m16a4();
        rifles[2] = new Gun_ak47();
        rifles[3] = new Gun_le6940();
        rifles[4] = new Gun_scarl();
        
        //machineguns
        machineguns[0] = new Gun_mg4();
        machineguns[1] = new Gun_rpk();
        machineguns[2] = new Gun_qbb95();
        machineguns[3] = new Gun_aughbar();
        machineguns[4] = new Gun_tridant();
        
        //sniper rifles
        sniperRifles[0] = new Gun_m40a3();
        sniperRifles[1] = new Gun_rsass();
        sniperRifles[2] = new Gun_l115a3();
        sniperRifles[3] = new Gun_hk417();
        sniperRifles[4] = new Gun_msr();
        
        for (int i = 0; i < 5; i++)
        {
            currentWeaponStats[i] = 0;
        }
        
    }
    
    public void shopToggler()
    {
        if (showShop == true)
        {
            showShop = false;
            Main.setCursor();
        }
        else if (showShop == false)
        {
            showShop = true;
            gp.KeyHandler.mouseLeftPressed = false;
            Main.resetCursor();
        }
    }
    
    public void drawShop (Graphics2D g2)
    {
        if (showShop == true)
        {
            hoveredButtonName = "";
            hoveredButtonNumber = 999;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            showWeaponSpecs = false;
            //variable initiation
            AlphaComposite alcom;
            
            g2.drawImage(shopWindow,shopWindowLoc.x,shopWindowLoc.y,null);
            
            if (checkButtonCollision(backButtonBox))
            {
                hoveredButtonName = "back";
                hoveredButtonNumber = 0;
            } 
            else 
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
                g2.setComposite(alcom);
            }
            
            g2.drawImage(backButton,backButtonLoc.x,backButtonLoc.y,null);
            displayCenterText(g2, backButton, "BACK", backButtonLoc.x, backButtonLoc.y, backButtonFont);
            //g2 reset
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2.setComposite(alcom); 
            
            //draw category
            int categoryNextX = 0;
            for (int i = 0; i < 6; i++)
            {
                //calculate button location
                if (i != 0)
                    categoryNextX = (i * categoryButton.getWidth()) + (4 * i);
                
                int categoryLocX = categoryStartLoc.x + categoryNextX;
                int categoryLocY = categoryStartLoc.y;
                
                //set x,y values
                categoryBox[i].x = categoryLocX;
                categoryBox[i].y = categoryLocY;
                
                //functions for button focus effect
                if (checkButtonCollision(categoryBox[i]) == true)
                {
                    hoveredButtonName = "category";
                    hoveredButtonNumber = i;
                } 
                else if (selectedCategory != i)
                {
                    alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
                    g2.setComposite(alcom);
                }
                g2.drawImage(categoryButton,categoryLocX,categoryLocY,null);
                displayCenterText(g2, categoryButton, categoryText[i], categoryLocX, categoryLocY, categoryTextFont);
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                g2.setComposite(alcom);   
            }
            
            //draw weapon choices
            int weaponNextY = 0;
            for (int i = 0; i < 5; i++)
            {
                if (i != 0)
                    weaponNextY = (i * weaponButton.getHeight()) + (6 * i);
                
                weaponBox[i].x = weaponStartLoc.x;
                weaponBox[i].y = weaponStartLoc.y + weaponNextY;
                
                
                if (checkButtonCollision(weaponBox[i]) == true)
                {
                    hoveredButtonName = "weapon";
                    hoveredButtonNumber = i;
                    showWeaponSpecs = true;
                }
                else
                {
                    alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
                    g2.setComposite(alcom);   
                }
                g2.drawImage(weaponButton,weaponStartLoc.x,weaponStartLoc.y + weaponNextY,null);
                
                FontMetrics metrics = g2.getFontMetrics(g2.getFont());
                int textCenterY = metrics.getHeight() / 2 - metrics.getAscent();
                if (selectedCategory == 0 && secondary[i] != null)
                {
                    g2.drawString(secondary[i].name, weaponStartLoc.x + 5, weaponStartLoc.y + weaponNextY - textCenterY + (weaponButton.getHeight() / 2));
                    g2.drawImage(secondary[i].silhuetteImage, weaponStartLoc.x + 175, weaponStartLoc.y - (secondary[i].UIimage.getHeight() / 2) + (weaponButton.getHeight() / 2) + weaponNextY, null);
                    if (secondary[i].equipped == true)
                    {
                        g2.drawImage(equippedBackground, weaponStartLoc.x + (weaponButton.getWidth() / 2) - (equippedBackground.getWidth() / 2), weaponStartLoc.y + weaponNextY + (weaponButton.getHeight() / 2) - (equippedBackground.getHeight() / 2), null);
                        displayCenterText(g2, weaponButton, "EQUIPPED", weaponStartLoc.x, weaponStartLoc.y + weaponNextY, categoryTextFont);
                    }
                        
                }
                if (selectedCategory == 1 && shotguns[i] != null)
                {
                    g2.drawString(shotguns[i].name, weaponStartLoc.x + 5, weaponStartLoc.y + weaponNextY - textCenterY + (weaponButton.getHeight() / 2));
                    g2.drawImage(shotguns[i].silhuetteImage, weaponStartLoc.x + 175, weaponStartLoc.y - (shotguns[i].UIimage.getHeight() / 2) + (weaponButton.getHeight() / 2) + weaponNextY, null);
                    if (shotguns[i].equipped == true)
                    {
                        g2.drawImage(equippedBackground, weaponStartLoc.x + (weaponButton.getWidth() / 2) - (equippedBackground.getWidth() / 2), weaponStartLoc.y + weaponNextY + (weaponButton.getHeight() / 2) - (equippedBackground.getHeight() / 2), null);
                        displayCenterText(g2, weaponButton, "EQUIPPED", weaponStartLoc.x, weaponStartLoc.y + weaponNextY, categoryTextFont);
                    }
                        
                }
                if (selectedCategory == 2 && smg[i] != null)
                {
                    g2.drawString(smg[i].name, weaponStartLoc.x + 5, weaponStartLoc.y + weaponNextY - textCenterY + (weaponButton.getHeight() / 2));
                    g2.drawImage(smg[i].silhuetteImage, weaponStartLoc.x + 175, weaponStartLoc.y - (smg[i].UIimage.getHeight() / 2) + (weaponButton.getHeight() / 2) + weaponNextY, null);
                    if (smg[i].equipped == true)
                    {
                        g2.drawImage(equippedBackground, weaponStartLoc.x + (weaponButton.getWidth() / 2) - (equippedBackground.getWidth() / 2), weaponStartLoc.y + weaponNextY + (weaponButton.getHeight() / 2) - (equippedBackground.getHeight() / 2), null);
                        displayCenterText(g2, weaponButton, "EQUIPPED", weaponStartLoc.x, weaponStartLoc.y + weaponNextY, categoryTextFont);
                    }
                        
                }
                if (selectedCategory == 3 && rifles[i] != null)
                {
                    g2.drawString(rifles[i].name, weaponStartLoc.x + 5, weaponStartLoc.y + weaponNextY - textCenterY + (weaponButton.getHeight() / 2));
                    g2.drawImage(rifles[i].silhuetteImage, weaponStartLoc.x + 175, weaponStartLoc.y - (rifles[i].UIimage.getHeight() / 2) + (weaponButton.getHeight() / 2) + weaponNextY, null);
                    if (rifles[i].equipped == true)
                    {
                        g2.drawImage(equippedBackground, weaponStartLoc.x + (weaponButton.getWidth() / 2) - (equippedBackground.getWidth() / 2), weaponStartLoc.y + weaponNextY + (weaponButton.getHeight() / 2) - (equippedBackground.getHeight() / 2), null);
                        displayCenterText(g2, weaponButton, "EQUIPPED", weaponStartLoc.x, weaponStartLoc.y + weaponNextY, categoryTextFont);
                    } 
                }
                if (selectedCategory == 4 && machineguns[i] != null)
                {
                    g2.drawString(machineguns[i].name, weaponStartLoc.x + 5, weaponStartLoc.y + weaponNextY - textCenterY + (weaponButton.getHeight() / 2));
                    g2.drawImage(machineguns[i].silhuetteImage, weaponStartLoc.x + 175, weaponStartLoc.y - (machineguns[i].UIimage.getHeight() / 2) + (weaponButton.getHeight() / 2) + weaponNextY, null);
                    if (machineguns[i].equipped == true)
                    {
                        g2.drawImage(equippedBackground, weaponStartLoc.x + (weaponButton.getWidth() / 2) - (equippedBackground.getWidth() / 2), weaponStartLoc.y + weaponNextY + (weaponButton.getHeight() / 2) - (equippedBackground.getHeight() / 2), null);
                        displayCenterText(g2, weaponButton, "EQUIPPED", weaponStartLoc.x, weaponStartLoc.y + weaponNextY, categoryTextFont);
                    }  
                }
                if (selectedCategory == 5 && sniperRifles[i] != null)
                {
                    g2.drawString(sniperRifles[i].name, weaponStartLoc.x + 5, weaponStartLoc.y + weaponNextY - textCenterY + (weaponButton.getHeight() / 2));
                    g2.drawImage(sniperRifles[i].silhuetteImage, weaponStartLoc.x + 175, weaponStartLoc.y - (sniperRifles[i].UIimage.getHeight() / 2) + (weaponButton.getHeight() / 2) + weaponNextY, null);
                    if (sniperRifles[i].equipped == true)
                    {
                        g2.drawImage(equippedBackground, weaponStartLoc.x + (weaponButton.getWidth() / 2) - (equippedBackground.getWidth() / 2), weaponStartLoc.y + weaponNextY + (weaponButton.getHeight() / 2) - (equippedBackground.getHeight() / 2), null);
                        displayCenterText(g2, weaponButton, "EQUIPPED", weaponStartLoc.x, weaponStartLoc.y + weaponNextY, categoryTextFont);
                    }
                }
                //g2 reset
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                g2.setComposite(alcom); 
                
                
            }
            if (lastHoveredButton != hoveredButtonNumber)
            {
                lastHoveredButton = hoveredButtonNumber;
                weaponStartX = shopWindowLoc.x + 500;
                gp.sound.playSFX(getClass().getResource("/ui_button_over.wav"));
            }
                
            if (showWeaponSpecs == true)
                drawWeaponSpecs(g2);
            
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }
    
    public boolean checkButtonCollision(Rectangle box)
    {
        Point mouse = Main.getMouseCoordinates();
        if (mouse.x >= box.getMinX() && mouse.x <= box.getMaxX()
            && mouse.y >= box.getMinY() && mouse.y <= box.getMaxY())
            {
                return true;
            }
        return false;
    }
    
    public void displayCenterText(Graphics2D g2, BufferedImage button, String text, int locationX, int locationY, Font font)
    {
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);
        int categoryCenterX = (button.getWidth() / 2) + locationX;
        int categoryCenterY = (button.getHeight() / 2) + locationY;
        int textCenterX = (metrics.stringWidth(text) / 2);
        int textCenterY = metrics.getHeight() / 2 - metrics.getAscent();
        
        g2.drawString(text, categoryCenterX - textCenterX, categoryCenterY - textCenterY);
    }
    
    public void buttonClick()
    {
        if (hoveredButtonName == "back")
            shopToggler();
        if (hoveredButtonName == "category")
            selectedCategory = hoveredButtonNumber;
        if (hoveredButtonName == "weapon")
        {
            selectedWeapon = hoveredButtonNumber;
            if (checkPrice(selectedCategory, selectedWeapon) == true || checkPurchased(selectedCategory, selectedWeapon) == true)
                purchaseWeapon(selectedCategory, selectedWeapon);
            else
                gp.sound.playSFX(getClass().getResource("/ui_error.wav"));
        }
    }
    public boolean checkPrice(int selectedCategory, int selectedWeapon)
    {
        switch(selectedCategory)
        {
            case 0:
                return (gp.player.coins >= secondary[selectedWeapon].weaponCost == true);
            case 1:
                return (gp.player.coins >= shotguns[selectedWeapon].weaponCost == true);
            case 2:
                return (gp.player.coins >= smg[selectedWeapon].weaponCost == true);
            case 3:
                return (gp.player.coins >= rifles[selectedWeapon].weaponCost == true);
            case 4:
                return (gp.player.coins >= machineguns[selectedWeapon].weaponCost == true);
            case 5:
                return (gp.player.coins >= sniperRifles[selectedWeapon].weaponCost == true);
        }
        return false;
    }
    
    public boolean checkPurchased(int selectedCategory, int selectedWeapon)
    {
        switch(selectedCategory)
        {
            case 0:
                return (secondary[selectedWeapon].purchased);
            case 1:
                return (shotguns[selectedWeapon].purchased);
            case 2:
                return (smg[selectedWeapon].purchased);
            case 3:
                return (rifles[selectedWeapon].purchased);
            case 4:
                return (machineguns[selectedWeapon].purchased);
            case 5:
                return (sniperRifles[selectedWeapon].purchased);
        }
        return false;
    }
    
    public void purchaseWeapon(int selectedCategory, int selectedWeapon)
    {
        switch(selectedCategory)
        {
            case 0:
                if (secondary[selectedWeapon].equipped == false)
                    gp.sound.loadoutSelectSound();
                if (gp.player.weaponStorage[1] != null)
                    gp.player.weaponStorage[1].equipped = false;
                if (secondary[selectedWeapon].purchased == false)
                {
                    gp.player.coins -= secondary[selectedWeapon].weaponCost;
                    secondary[selectedWeapon].purchased = true;
                }
                gp.player.weaponStorage[1] = secondary[selectedWeapon];
                gp.player.weaponStorage[1].equipped = true;
                break;
            case 1:
                if (shotguns[selectedWeapon].equipped == false)
                    gp.sound.loadoutSelectSound();
                if (gp.player.weaponStorage[0] != null)
                    gp.player.weaponStorage[0].equipped = false;
                if (shotguns[selectedWeapon].purchased == false)
                {
                    gp.player.coins -= shotguns[selectedWeapon].weaponCost;
                    shotguns[selectedWeapon].purchased = true;
                }
                gp.player.weaponStorage[0] = shotguns[selectedWeapon];
                gp.player.weaponStorage[0].equipped = true;
                break;
            case 2:
                if (smg[selectedWeapon].equipped == false)
                    gp.sound.loadoutSelectSound();
                if (gp.player.weaponStorage[0] != null)
                    gp.player.weaponStorage[0].equipped = false;
                if (smg[selectedWeapon].purchased == false)
                {
                    gp.player.coins -= smg[selectedWeapon].weaponCost;
                    smg[selectedWeapon].purchased = true;
                }
                gp.player.weaponStorage[0] = smg[selectedWeapon];
                gp.player.weaponStorage[0].equipped = true;
                break;
            case 3:
                if (rifles[selectedWeapon].equipped == false)
                    gp.sound.loadoutSelectSound();
                if (gp.player.weaponStorage[0] != null)
                    gp.player.weaponStorage[0].equipped = false;
                if (rifles[selectedWeapon].purchased == false)
                {
                    gp.player.coins -= rifles[selectedWeapon].weaponCost;
                    rifles[selectedWeapon].purchased = true;
                }
                gp.player.weaponStorage[0] = rifles[selectedWeapon];
                gp.player.weaponStorage[0].equipped = true;
                break;
            case 4:
                if (machineguns[selectedWeapon].equipped == false)
                    gp.sound.loadoutSelectSound();
                if (gp.player.weaponStorage[0] != null)
                    gp.player.weaponStorage[0].equipped = false;
                if (machineguns[selectedWeapon].purchased == false)
                {
                    gp.player.coins -= machineguns[selectedWeapon].weaponCost;
                    machineguns[selectedWeapon].purchased = true;
                }
                gp.player.weaponStorage[0] = machineguns[selectedWeapon];
                gp.player.weaponStorage[0].equipped = true;
                break;
            case 5:
                if (sniperRifles[selectedWeapon].equipped == false)
                    gp.sound.loadoutSelectSound();
                if (gp.player.weaponStorage[0] != null)
                    gp.player.weaponStorage[0].equipped = false;
                if (sniperRifles[selectedWeapon].purchased == false)
                {
                    gp.player.coins -= sniperRifles[selectedWeapon].weaponCost;
                    sniperRifles[selectedWeapon].purchased = true;
                }
                gp.player.weaponStorage[0] = sniperRifles[selectedWeapon];
                gp.player.weaponStorage[0].equipped = true;
                break;
        }
        
    }
    
    public void drawWeaponSpecs(Graphics2D g2)
    {
        setWeaponStats();
        
        g2.setColor(Color.black);
        int weaponSpecsBackgroundLocX = shopWindowLoc.x + 441;
        int weaponSpecsBackgroundLocY = shopWindowLoc.y + 345;
        int barLocX = weaponSpecsBackgroundLocX + 7;
        int barLocY = weaponSpecsBackgroundLocY + 41;
        int barStartLocX = barLocX;
        int barStartLocY = barLocY;
        
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
        g2.setComposite(alcom); 
        g2.drawImage(weaponSpecsBackground, weaponSpecsBackgroundLocX, weaponSpecsBackgroundLocY, null);
        //g2 reset
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(alcom);
        
        g2.setFont(categoryTextFont);
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        g2.drawString("WEAPON SPECS", weaponSpecsBackgroundLocX + 6, weaponSpecsBackgroundLocY + metrics.getHeight());
        
        g2.setFont(weaponNameFont);
        metrics = g2.getFontMetrics(g2.getFont());
        g2.setColor(Color.getHSBColor(0.57f, 1.0f, 0.25f));
        g2.drawString(hoveredWeaponName, shopWindowLoc.x + 441, shopWindowLoc.y + 103 + metrics.getHeight());
        
        
        g2.setFont(specsFont);
        metrics = g2.getFontMetrics(g2.getFont());
        g2.drawString(hoveredDetailText, shopWindowLoc.x + 441, shopWindowLoc.y + 130 + metrics.getHeight());
        
        g2.drawImage(ammoIcon, shopWindowLoc.x + 441, shopWindowLoc.y + 145, null);
        if (isSecondary == true)
            g2.drawString(hoveredAmmoInClipRemaining+" / INFINITE", shopWindowLoc.x + 471, shopWindowLoc.y + 150 + metrics.getHeight());
        else
            g2.drawString(hoveredAmmoInClipRemaining+" / "+hoveredAmmoRemaining, shopWindowLoc.x + 471, shopWindowLoc.y + 150 + metrics.getHeight());
        
        g2.drawImage(coinIcon, shopWindowLoc.x + 445, shopWindowLoc.y + 175, null);
        if (ifPurchased == true)
            g2.drawString("Purchased", shopWindowLoc.x + 471, shopWindowLoc.y + 175 + metrics.getHeight());
        else
            g2.drawString(Integer.toString(hoveredWeaponCost), shopWindowLoc.x + 471, shopWindowLoc.y + 175 + metrics.getHeight());
        
        
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.abs((float)((weaponStartX - weaponEndX - 59) / 59)));
        g2.setComposite(alcom); 
        g2.drawImage(hoveredImage, (int) weaponStartX, shopWindowLoc.y + 260 - (hoveredImage.getHeight() / 2), null);
        //g2 reset
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(alcom);
        weaponStartX = weaponStartX + (weaponEndX - weaponStartX) / 5.0;
        
        g2.setColor(Color.black);
        for (int i = 0; i < 5; i++)
        {   
            currentWeaponStats[i] = currentWeaponStats[i] + (selectedWeaponStats[i] - currentWeaponStats[i]) / 10.0;
            
            String specText = "";
            switch (i)
            {
                case 0: specText = "DAMAGE"; break;
                case 1: specText = "FIRE RATE"; break;
                case 2: specText = "ACCURACY"; break;
                case 3: specText = "PENETRATION"; break;
                case 4: specText = "RELOAD SPEED"; break;
            }
            for (int j = 0; j < 5; j++)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
                g2.setComposite(alcom); 
                g2.fillRect(barStartLocX, barStartLocY, 108, 8);
                //g2 reset
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                g2.setComposite(alcom);
                
                if (currentWeaponStats[i] < 20 * (j+1))
                    g2.fillRect(barStartLocX, barStartLocY, (int) ((currentWeaponStats[i] - (20 * j)) * 5.4), 8);
                else
                    g2.fillRect(barStartLocX, barStartLocY, 108, 8);
                
                barStartLocX += 110;
                    
            }
            barStartLocX = barLocX;
            
            g2.drawString(specText, barStartLocX + 10, barStartLocY - 3);
            barStartLocY += 29;
        }
    }
    
    public void setWeaponStats()
    {
        switch (selectedCategory)
        {
            case 0:
                selectedWeaponStats[0] = secondary[hoveredButtonNumber].UIDamage;
                selectedWeaponStats[1] = secondary[hoveredButtonNumber].UIFireRate;
                selectedWeaponStats[2] = secondary[hoveredButtonNumber].UIAccuracy;
                selectedWeaponStats[3] = secondary[hoveredButtonNumber].UIPenetration;
                selectedWeaponStats[4] = secondary[hoveredButtonNumber].UIReloadSpeed;
                hoveredImage = secondary[hoveredButtonNumber].Shopimage;
                hoveredWeaponName = secondary[hoveredButtonNumber].name;
                hoveredDetailText = secondary[hoveredButtonNumber].detailText;
                hoveredAmmoInClipRemaining = secondary[hoveredButtonNumber].ammoInClipRemaining;
                hoveredAmmoRemaining = secondary[hoveredButtonNumber].ammoRemaining;
                hoveredWeaponCost = secondary[hoveredButtonNumber].weaponCost;
                ifPurchased = secondary[hoveredButtonNumber].purchased;
                isSecondary = true;
                break;
            case 1:
                selectedWeaponStats[0] = shotguns[hoveredButtonNumber].UIDamage;
                selectedWeaponStats[1] = shotguns[hoveredButtonNumber].UIFireRate;
                selectedWeaponStats[2] = shotguns[hoveredButtonNumber].UIAccuracy;
                selectedWeaponStats[3] = shotguns[hoveredButtonNumber].UIPenetration;
                selectedWeaponStats[4] = shotguns[hoveredButtonNumber].UIReloadSpeed;
                hoveredImage = shotguns[hoveredButtonNumber].Shopimage;
                hoveredWeaponName = shotguns[hoveredButtonNumber].name;
                hoveredDetailText = shotguns[hoveredButtonNumber].detailText;
                hoveredAmmoInClipRemaining = shotguns[hoveredButtonNumber].ammoInClipRemaining;
                hoveredAmmoRemaining = shotguns[hoveredButtonNumber].ammoRemaining;
                hoveredWeaponCost = shotguns[hoveredButtonNumber].weaponCost;
                ifPurchased = shotguns[hoveredButtonNumber].purchased;
                isSecondary = false;
                break;
            case 2:
                selectedWeaponStats[0] = smg[hoveredButtonNumber].UIDamage;
                selectedWeaponStats[1] = smg[hoveredButtonNumber].UIFireRate;
                selectedWeaponStats[2] = smg[hoveredButtonNumber].UIAccuracy;
                selectedWeaponStats[3] = smg[hoveredButtonNumber].UIPenetration;
                selectedWeaponStats[4] = smg[hoveredButtonNumber].UIReloadSpeed;
                hoveredImage = smg[hoveredButtonNumber].Shopimage;
                hoveredWeaponName = smg[hoveredButtonNumber].name;
                hoveredDetailText = smg[hoveredButtonNumber].detailText;
                hoveredAmmoInClipRemaining = smg[hoveredButtonNumber].ammoInClipRemaining;
                hoveredAmmoRemaining = smg[hoveredButtonNumber].ammoRemaining;
                hoveredWeaponCost = smg[hoveredButtonNumber].weaponCost;
                ifPurchased = smg[hoveredButtonNumber].purchased;
                isSecondary = false;
                break;
            case 3:
                selectedWeaponStats[0] = rifles[hoveredButtonNumber].UIDamage;
                selectedWeaponStats[1] = rifles[hoveredButtonNumber].UIFireRate;
                selectedWeaponStats[2] = rifles[hoveredButtonNumber].UIAccuracy;
                selectedWeaponStats[3] = rifles[hoveredButtonNumber].UIPenetration;
                selectedWeaponStats[4] = rifles[hoveredButtonNumber].UIReloadSpeed;
                hoveredImage = rifles[hoveredButtonNumber].Shopimage;
                hoveredWeaponName = rifles[hoveredButtonNumber].name;
                hoveredDetailText = rifles[hoveredButtonNumber].detailText;
                hoveredAmmoInClipRemaining = rifles[hoveredButtonNumber].ammoInClipRemaining;
                hoveredAmmoRemaining = rifles[hoveredButtonNumber].ammoRemaining;
                hoveredWeaponCost = rifles[hoveredButtonNumber].weaponCost;
                ifPurchased = rifles[hoveredButtonNumber].purchased;
                isSecondary = false;
                break;
            case 4:
                selectedWeaponStats[0] = machineguns[hoveredButtonNumber].UIDamage;
                selectedWeaponStats[1] = machineguns[hoveredButtonNumber].UIFireRate;
                selectedWeaponStats[2] = machineguns[hoveredButtonNumber].UIAccuracy;
                selectedWeaponStats[3] = machineguns[hoveredButtonNumber].UIPenetration;
                selectedWeaponStats[4] = machineguns[hoveredButtonNumber].UIReloadSpeed;
                hoveredImage = machineguns[hoveredButtonNumber].Shopimage;
                hoveredWeaponName = machineguns[hoveredButtonNumber].name;
                hoveredDetailText = machineguns[hoveredButtonNumber].detailText;
                hoveredAmmoInClipRemaining = machineguns[hoveredButtonNumber].ammoInClipRemaining;
                hoveredAmmoRemaining = machineguns[hoveredButtonNumber].ammoRemaining;
                hoveredWeaponCost = machineguns[hoveredButtonNumber].weaponCost;
                ifPurchased = machineguns[hoveredButtonNumber].purchased;
                isSecondary = false;
                break;
            case 5:
                selectedWeaponStats[0] = sniperRifles[hoveredButtonNumber].UIDamage;
                selectedWeaponStats[1] = sniperRifles[hoveredButtonNumber].UIFireRate;
                selectedWeaponStats[2] = sniperRifles[hoveredButtonNumber].UIAccuracy;
                selectedWeaponStats[3] = sniperRifles[hoveredButtonNumber].UIPenetration;
                selectedWeaponStats[4] = sniperRifles[hoveredButtonNumber].UIReloadSpeed;
                hoveredImage = sniperRifles[hoveredButtonNumber].Shopimage;
                hoveredWeaponName = sniperRifles[hoveredButtonNumber].name;
                hoveredDetailText = sniperRifles[hoveredButtonNumber].detailText;
                hoveredAmmoInClipRemaining = sniperRifles[hoveredButtonNumber].ammoInClipRemaining;
                hoveredAmmoRemaining = sniperRifles[hoveredButtonNumber].ammoRemaining;
                hoveredWeaponCost = sniperRifles[hoveredButtonNumber].weaponCost;
                ifPurchased = sniperRifles[hoveredButtonNumber].purchased;
                isSecondary = false;
                break;
        }
    }
}
