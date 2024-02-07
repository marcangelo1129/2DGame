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
import java.awt.image.BufferedImage;
import java.io.IOException;
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
public class pauseMenu {
    Graphics2D g2;
    GamePanel gp;
    Main main;
    Font font;
    int screenSizeX;
    int screenSizeY;
    String hoveredButtonName;
    String lastHoveredButton;
    public boolean paused = false;
    Rectangle resumeButton = new Rectangle(0,0,300,100);
    Rectangle exitButton = new Rectangle(0,0,300,100);
    Color buttonColor = new Color(5,0,42);
    float buttonTransparency = 0.8f;
    float textButtonTransparency;
    BufferedImage resumeImage, exitImage, pauseWindow;
    UtilityTool uTool = new UtilityTool();
    
    public pauseMenu(GamePanel gp, Main main)
    {
        this.gp = gp;
        this.main = main;
        screenSizeX = gp.screenSize.width;
        screenSizeY = gp.screenSize.height;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(32f);
            pauseWindow = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/pauseWindow.png"));
            pauseWindow = uTool.scaleImage(pauseWindow, 349, 316);
            resumeImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/playIcon.png"));
            resumeImage = uTool.scaleImage(resumeImage, (int) (90*0.65), (int) (90*0.65));
            exitImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/exit.png"));
            exitImage = uTool.scaleImage(exitImage, (int) (340*0.3), (int) (340*0.3));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(pauseMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void drawPauseMenu(Graphics2D g2)
    {
        this.g2 = g2;
        hoveredButtonName = "";
        AlphaComposite alcom;
        if (paused == true)
        {
            g2.setColor(Color.black);
            centerText("GAME PAUSED", screenSizeX / 2, (screenSizeY / 2) - 130, 32, 1f);
            resumeButton.x = (screenSizeX / 2) - (resumeButton.width / 2);
            resumeButton.y = (screenSizeY / 2) - (resumeButton.height / 2) - 50;
            exitButton.x = (screenSizeX / 2) - (exitButton.width / 2);
            exitButton.y = (screenSizeY / 2) - (exitButton.height / 2) + 70;
            g2.drawImage(pauseWindow, (screenSizeX / 2) - (pauseWindow.getWidth() / 2), (screenSizeY / 2) - (pauseWindow.getHeight() / 2) - 10, null);
            
            if (checkButtonCollision(resumeButton))
            {
                hoveredButtonName = "resume";
            } 
            else 
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, buttonTransparency);
                textButtonTransparency = buttonTransparency;
                g2.setComposite(alcom);
            }
            g2.setColor(buttonColor);
            g2.fillRect(resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height);
            g2.drawImage(resumeImage, resumeButton.x + (resumeButton.width / 2) - (resumeImage.getWidth() / 2), resumeButton.y + (resumeButton.height / 2) - (resumeImage.getHeight() / 2) - 16, null);
            g2.setColor(Color.white);
            centerText("RESUME", resumeButton.x + (resumeButton.width / 2), resumeButton.y + (resumeButton.height / 2) + 30, 25, textButtonTransparency);

            if (checkButtonCollision(exitButton))
            {
                hoveredButtonName = "exit";
            } 
            else 
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, buttonTransparency);
                textButtonTransparency = buttonTransparency;
                g2.setComposite(alcom);
            }
            g2.setColor(buttonColor);
            g2.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
            g2.drawImage(exitImage, exitButton.x + (exitButton.width / 2) - (exitImage.getWidth() / 2), exitButton.y + (exitButton.height / 2) - (exitImage.getHeight() / 2) - 20, null);
            g2.setColor(Color.white);
            centerText("EXIT", exitButton.x + (exitButton.width / 2), exitButton.y + (exitButton.height / 2) + 30, 25, textButtonTransparency);
            
            if (lastHoveredButton != hoveredButtonName)
            {
                lastHoveredButton = hoveredButtonName;
                gp.sound.playSFX(getClass().getResource("/ui_button_over.wav"));
            }
        }
    }
    
    public void pauseToggler()
    {
        if (paused == true)
        {
            paused = false;
            main.setCursor();
        }
        else if (gp.shop.showShop == false)
        {
            paused = true;
            gp.KeyHandler.mouseLeftPressed = false;
            main.resetCursor();
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
    
    public void centerText(String text, int x, int y, float size, float transparency)
    {
        g2.setColor(Color.white);
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
    
    public void buttonClick()
    {
        if (hoveredButtonName == "resume")
        {
            hoveredButtonName = "";
            pauseToggler();
        }
        if (hoveredButtonName == "exit")
        {
            hoveredButtonName = "";
            gp.GameState = "loading";
            gp.sound.stopMusic();
            gp.loadingScreen.loadState = "presentsMenu";
            gp.loadingScreen.timerSwitch(true);
            gp.menu.menuState = "mainMenu";
            gp.loadingScreen.time = 0;
            pauseToggler();
            main.resetCursor();
            gp.sound.playIntro();
        }
    }
    
}
