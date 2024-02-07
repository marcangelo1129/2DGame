/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.Main;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class mainMenu {
    GamePanel gp;
    Main main;
    Graphics2D g2;
    private final int screenSizeX;
    private final int screenSizeY;
    BufferedImage mainMenu;
    BufferedImage titleText;
    BufferedImage subtitleText;
    UtilityTool uTool = new UtilityTool();
    Font font;
    String hoveredButtonName;
    String lastHoveredButton = "";
    Color buttonColor = new Color(5,0,42);
    public float flashTransparency = 1f;
    creditText creditText = new creditText();
    
    BufferedImage startImage, developerImage, exitImage;
    
    Rectangle startButton = new Rectangle(0,0,300,100);
    Rectangle devInfoButton = new Rectangle(0,0,300,100);
    Rectangle exitButton = new Rectangle(0,0,300,100);
    Rectangle backButton = new Rectangle(0,0,150,70);
    Rectangle creditNextButton = new Rectangle(0,0, 60,60);
    Rectangle creditPrevButton = new Rectangle(0,0,60,60);
    
    public String menuState = "mainMenu";
    BufferedImage creditsWindow;
    
    //credits variables
    double creditsOutWindowX;
    double creditsInWindowX;
    double creditsWindowX;
    int creditPageNum = 0;
    int creditMaxPage = 4;
    
    public mainMenu(GamePanel gp, Main main)
    {
        this.gp = gp;
        this.main = main;
        screenSizeX = gp.screenSize.width;
        screenSizeY = gp.screenSize.height;
        startButton.x = (screenSizeX / 2) - (startButton.width / 2);
        startButton.y = 250;
        devInfoButton.x = (screenSizeX / 2) - (devInfoButton.width / 2);
        devInfoButton.y = 370;
        exitButton.x = (screenSizeX / 2) - (exitButton.width / 2);
        exitButton.y = 490;
        try {
            mainMenu = ImageIO.read(getClass().getResourceAsStream("/userInterface/mainMenu.jpg"));
            mainMenu = uTool.scaleImage(mainMenu, screenSizeX, screenSizeY);
            creditsWindow = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/creditsWindow.png"));
            creditsWindow = uTool.scaleImage(creditsWindow, 1307, 713);
            titleText = ImageIO.read(getClass().getResourceAsStream("/userInterface/titleText.png"));
            titleText = uTool.scaleImage(titleText, 988, 468);
            subtitleText = ImageIO.read(getClass().getResourceAsStream("/userInterface/subtitleText.png"));
            subtitleText = uTool.scaleImage(subtitleText, 988, 468);
            startImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/m4a1.png"));
            startImage = uTool.scaleImage(startImage, (int) (353*0.45), (int) (124*0.45));
            developerImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/gear.png"));
            developerImage = uTool.scaleImage(developerImage, (int)(960*0.07), (int)(960*0.07));
            exitImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/exit.png"));
            exitImage = uTool.scaleImage(exitImage, (int) (340*0.3), (int) (340*0.3));
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(32f);
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        creditsOutWindowX = screenSizeX + 500;
        creditsInWindowX = (screenSizeX / 2) - (creditsWindow.getWidth() / 2);
        creditsWindowX = creditsOutWindowX;
    }
    
    public void drawMenu(Graphics2D g2)
    {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hoveredButtonName = "";
        this.g2 = g2;
        AlphaComposite alcom;
        if (menuState == "mainMenu")
        {
            creditsWindowX = creditsWindowX + (creditsOutWindowX - creditsWindowX) / 10;
            g2.setFont(font);
            g2.setColor(Color.white);
            g2.drawImage(mainMenu, 0, 0,  null);
            g2.drawImage(titleText, (screenSizeX / 2) - (titleText.getWidth() / 2), (screenSizeY / 2) - (titleText.getHeight() / 2) - 300, null);
            g2.drawImage(subtitleText, (screenSizeX / 2) - (titleText.getWidth() / 2), (screenSizeY / 2) - (titleText.getHeight() / 2) - 200, null);

            //start button
            g2.setColor(buttonColor);
            float textTransparency = 1f;
            if (checkButtonCollision(startButton))
            {
                hoveredButtonName = "start";
            } 
            else 
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
                textTransparency = 0.8f;
                g2.setComposite(alcom);
            }
            g2.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
            g2.drawImage(startImage, startButton.x + (startButton.width / 2) - (startImage.getWidth() / 2), startButton.y + (startButton.height / 2) - (startImage.getHeight() / 2) - 20, null);
            g2.setColor(Color.white);
            centerText("Start", startButton.x + (startButton.width / 2), startButton.y + (startButton.height / 2) + 30, 25, textTransparency);

            //developer info button
            g2.setColor(buttonColor);
            textTransparency = 1f;
            if (checkButtonCollision(devInfoButton))
            {
                hoveredButtonName = "devInfo";
            } 
            else 
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
                textTransparency = 0.8f;
                g2.setComposite(alcom);
            }
            g2.fillRect(devInfoButton.x, devInfoButton.y, devInfoButton.width, devInfoButton.height);
            g2.drawImage(developerImage, devInfoButton.x + (devInfoButton.width / 2) - (developerImage.getWidth() / 2), devInfoButton.y + (devInfoButton.height / 2) - (developerImage.getHeight() / 2) - 17, null);
            g2.setColor(Color.white);
            centerText("Developer Info", devInfoButton.x + (devInfoButton.width / 2), devInfoButton.y + (devInfoButton.height / 2) + 30, 25, textTransparency);

            //exit button
            g2.setColor(buttonColor);
            textTransparency = 1f;
            if (checkButtonCollision(exitButton))
            {
                hoveredButtonName = "exit";
            } 
            else 
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
                textTransparency = 0.8f;
                g2.setComposite(alcom);
            }
            g2.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
            g2.drawImage(exitImage, exitButton.x + (exitButton.width / 2) - (exitImage.getWidth() / 2), exitButton.y + (exitButton.height / 2) - (exitImage.getHeight() / 2) - 15, null);
            g2.setColor(Color.white);
            centerText("Exit", exitButton.x + (exitButton.width / 2), exitButton.y + (exitButton.height / 2) + 30, 25, textTransparency);


            //screenFlash
            g2.setColor(Color.black);
            if (flashTransparency > 0)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, flashTransparency);
                g2.setComposite(alcom);
                flashTransparency -= 0.016;
                g2.fillRect(0, 0, screenSizeX, screenSizeY);
            }
            
            if (lastHoveredButton != hoveredButtonName)
            {
                lastHoveredButton = hoveredButtonName;
                gp.sound.playSFX(getClass().getResource("/ui_button_over.wav"));
            }
            drawCredits();
        }
        else if (menuState == "devInfo")
        {
            g2.drawImage(mainMenu, 0, 0,  null);
            if (flashTransparency < 0.8)
                {
                    flashTransparency += 0.016;
                }
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, flashTransparency);
            g2.setComposite(alcom);
            g2.setColor(Color.black);
            g2.fillRect(0, 0, screenSizeX, screenSizeY);
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2.setComposite(alcom);
            drawCredits();
            creditsWindowX = creditsWindowX + (creditsInWindowX - creditsWindowX) / 7.0;
        }
        else if (menuState == "gameLoad")
        {
            g2.setColor(Color.white);
            g2.drawImage(mainMenu, 0, 0,  null);
            centerText("Loading", screenSizeX / 2, (screenSizeY / 2), 30, 1);
            g2.setColor(Color.black);
            if (flashTransparency > 0)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, flashTransparency);
                g2.setComposite(alcom);
                flashTransparency -= 0.016;
                g2.fillRect(0, 0, screenSizeX, screenSizeY);
            }
        }
        else if (menuState == "gameLoaded")
        {
            g2.drawImage(mainMenu, 0, 0,  null);
            g2.setColor(Color.orange);
            centerText("Click to Start", screenSizeX / 2, (screenSizeY / 2), 30, 1);
            g2.setColor(Color.black);
            if (flashTransparency > 0)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, flashTransparency);
                g2.setComposite(alcom);
                flashTransparency -= 0.016;
                g2.fillRect(0, 0, screenSizeX, screenSizeY);
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    public void drawCredits()
    {
        float textTransparency = 1f;
        AlphaComposite alcom;
        g2.drawImage(creditsWindow, (int) creditsWindowX, (screenSizeY / 2) - (creditsWindow.getHeight() / 2), null);
        g2.setColor(Color.black);
        
        backButton.x = (int) (creditsWindowX - backButton.width - 30) + creditsWindow.getWidth();
        backButton.y = (screenSizeY / 2) - (creditsWindow.getHeight() / 2) + 30;
        if (checkButtonCollision(backButton))
        {
            hoveredButtonName = "back";
        } 
        else 
        {
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
            textTransparency = 0.8f;
            g2.setComposite(alcom);
        }
        g2.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g2.setColor(Color.white);
        centerText("Back", backButton.x + (backButton.width / 2), backButton.y + (backButton.height / 2), 25, textTransparency);
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        textTransparency = 1f;
        g2.setComposite(alcom);
        
        g2.setColor(Color.black);
        creditNextButton.x = (int) (creditsWindowX - creditNextButton.width - 30) + creditsWindow.getWidth();
        creditNextButton.y = (screenSizeY / 2) + (creditsWindow.getHeight() / 2) - creditNextButton.height - 30;
        if (checkButtonCollision(creditNextButton))
        {
            hoveredButtonName = "creditNext";
        } 
        else 
        {
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
            textTransparency = 0.8f;
            g2.setComposite(alcom);
        }
        g2.fillRect(creditNextButton.x, creditNextButton.y, creditNextButton.width, creditNextButton.height);
        g2.setColor(Color.white);
        centerText(">", creditNextButton.x + (creditNextButton.width / 2), creditNextButton.y + (creditNextButton.height / 2), 35, textTransparency);
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        textTransparency = 1f;
        g2.setComposite(alcom);
        
        int pagenum = creditPageNum + 1;
        int maxPage = creditMaxPage + 1;
        centerText(pagenum+"/"+maxPage, creditNextButton.x - 41, creditNextButton.y + (creditNextButton.width / 2), 25, 1f);
        
        g2.setColor(Color.black);
        creditPrevButton.x = (int) (creditsWindowX - creditPrevButton.width - 170) + creditsWindow.getWidth();
        creditPrevButton.y = (screenSizeY / 2) + (creditsWindow.getHeight() / 2) - creditPrevButton.height - 30;
        if (checkButtonCollision(creditPrevButton))
        {
            hoveredButtonName = "creditPrev";
        } 
        else 
        {
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
            textTransparency = 0.8f;
            g2.setComposite(alcom);
        }
        g2.fillRect(creditPrevButton.x, creditPrevButton.y, creditPrevButton.width, creditPrevButton.height);
        g2.setColor(Color.white);
        centerText("<", creditPrevButton.x + (creditPrevButton.width / 2), creditPrevButton.y + (creditPrevButton.height / 2), 35, textTransparency);
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(alcom);
        
        int textX = (int) creditsWindowX + 40;
        int textY = (screenSizeY / 2) - (creditsWindow.getHeight() / 2) + 60;
        font = font.deriveFont(26f);
        g2.setFont(font);
        for (int i = 0; i < 21; i++)
        {
            if (creditText.text[creditPageNum][i] != null)
            {
                g2.drawString(creditText.text[creditPageNum][i], textX, textY);
                font = font.deriveFont(18f);
                g2.setFont(font);
                //centerText(creditText.text[creditPageNum][i], textX, textY, 25, 1f);
            }
            textY += 30;
        }
    }
    
    public boolean checkButtonCollision(Rectangle box)
    {
        Point mouse = main.getMouseCoordinates();
        if (mouse.x >= box.getMinX() && mouse.x <= box.getMaxX()
            && mouse.y >= box.getMinY() && mouse.y <= box.getMaxY())
            {
                return true;
            }
        return false;
    }
    
    public void buttonClick()
    {
        if (hoveredButtonName == "start")
        {
            hoveredButtonName = "";
            gp.sound.playSFX(getClass().getResource("/game_prepare.wav"));
            flashTransparency = 1f;
            menuState = "gameLoad";
            gp.setup();
        }
        if (hoveredButtonName == "devInfo")
        {
            hoveredButtonName = "";
            menuState = "devInfo";
            creditPageNum = 0;
        }
        if (hoveredButtonName == "exit")
        {
            hoveredButtonName = "";
            System.exit(0);
        }
        if (hoveredButtonName == "back")
        {
            hoveredButtonName = "";
            menuState = "mainMenu";
        }
        if (hoveredButtonName == "creditNext")
        {
            hoveredButtonName = "";
            if (creditPageNum != creditMaxPage)
                creditPageNum++;
        }
        if (hoveredButtonName == "creditPrev")
        {
            hoveredButtonName = "";
            if (creditPageNum != 0)
                creditPageNum--;
        }
    }
    
    public void centerText(String text, int x, int y, float size, float transparency)
    {
        if (transparency < 0f)
            transparency = 0f;
        if (transparency > 1f)
            transparency = 1f;
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
        g2.setComposite(alcom);
        font = font.deriveFont(size);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);
        int textCenterX = (metrics.stringWidth(text) / 2);
        int textCenterY = metrics.getHeight() / 2 - metrics.getAscent();
        
        g2.drawString(text, x - textCenterX, y - textCenterY);
        
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(alcom);
    }
}
