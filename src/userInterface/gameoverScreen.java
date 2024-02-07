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
import java.util.Random;
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
public class gameoverScreen {
    GamePanel gp;
    Graphics2D g2;
    Main main;
    private final int screenSizeX;
    private final int screenSizeY;
    Rectangle background;
    int backgroundinitialHeight = 70;
    int backgroundMaxHeight = 250;
    int Counter = 0;
    float screenTransparency = 0f;
    float textTransparency = 0f;
    float buttonTransparency = 0f;
    float scoreTransparency = 0f;
    Color darkRed = new Color(127,0,0);
    Font font;
    Color buttonColor = new Color(5,0,42);
    String hoveredButtonName;
    BufferedImage tryagainImage, exitImage;
    Rectangle tryagainButton = new Rectangle(0,0,300,100);
    Rectangle exitButton = new Rectangle(0,0,300,100);
    UtilityTool uTool = new UtilityTool();
    boolean isButtonAnimate = true;
    Random random = new Random();
    boolean lostSound = false;
    
    public gameoverScreen (GamePanel gp, Main main)
    {
        this.gp = gp;
        this.main = main;
        screenSizeX = gp.screenSize.width;
        screenSizeY = gp.screenSize.height;
        background = new Rectangle(0,0,screenSizeX, 0);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(32f);
            tryagainImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/m4a1.png"));
            tryagainImage = uTool.scaleImage(tryagainImage, (int) (353*0.45), (int) (124*0.45));
            exitImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/gunSilhuette/exit.png"));
            exitImage = uTool.scaleImage(exitImage, (int) (340*0.3), (int) (340*0.3));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(gameoverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reset()
    {
        background.y = 0;
        Counter = 0;
        screenTransparency = 0f;
        isButtonAnimate = true;
        Counter = 0;
        screenTransparency = 0f;
        textTransparency = 0f;
        buttonTransparency = 0f;
        scoreTransparency = 0f;
        background.height = 0;
        lostSound = false;
    }
    
    
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        AlphaComposite alcom;
        Counter += 16;
        
        g2.setColor(darkRed);
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, screenTransparency);
        g2.setComposite(alcom);
        g2.fillRect(0, 0, screenSizeX, screenSizeY);
        
        g2.setColor(Color.black);
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        g2.setComposite(alcom);
        g2.fillRect(0, (screenSizeY / 2) - (background.height / 2), background.width, background.height);
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(alcom);
        
        centerText("GAME OVER", background.width / 2, (screenSizeY / 2) - (background.height / 2) + 33, 32, textTransparency);
        centerText("SCORE: "+gp.player.score+"   WAVE: "+gp.player.waveNumber, background.width / 2, (screenSizeY / 2) - (background.height / 2) + 70, 25, scoreTransparency);
        
        tryagainButton.x = (screenSizeX / 2) - tryagainButton.width - 15;
        tryagainButton.y = (screenSizeY / 2) - 10;
        exitButton.x = (screenSizeX / 2) + 15;
        exitButton.y = (screenSizeY / 2) - 10;
        
        //tryagain button
        g2.setColor(buttonColor);
        float textButtonTransparency = 1f;
        if (checkButtonCollision(tryagainButton))
        {
            hoveredButtonName = "tryagain";
        } 
        else 
        {
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, buttonTransparency);
            textButtonTransparency = buttonTransparency;
            g2.setComposite(alcom);
        }
        g2.fillRect(tryagainButton.x, tryagainButton.y, tryagainButton.width, tryagainButton.height);
        g2.drawImage(tryagainImage, tryagainButton.x + (tryagainButton.width / 2) - (tryagainImage.getWidth() / 2), tryagainButton.y + (tryagainButton.height / 2) - (tryagainImage.getHeight() / 2) - 20, null);
        g2.setColor(Color.white);
        centerText("Try Again", tryagainButton.x + (tryagainButton.width / 2), tryagainButton.y + (tryagainButton.height / 2) + 30, 25, textButtonTransparency);

        
        //exit button
        g2.setColor(buttonColor);
        textButtonTransparency = 1f;
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
        g2.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
        g2.drawImage(exitImage, exitButton.x + (exitButton.width / 2) - (exitImage.getWidth() / 2), exitButton.y + (exitButton.height / 2) - (exitImage.getHeight() / 2) - 15, null);
        g2.setColor(Color.white);
        centerText("Exit", exitButton.x + (exitButton.width / 2), exitButton.y + (exitButton.height / 2) + 30, 25, textButtonTransparency);
        
        screenTransparency = screenTransparency + (0.5f - screenTransparency) / 10.0f;
        if (Counter >= 500 && Counter < 1500)
        {
            background.height = (int) (background.height + (backgroundinitialHeight - background.height) / 5.0);
                
        }
        if (Counter >= 1000 && lostSound == false)
        {
            lostSound = true;
            gp.sound.playSFX(gp.player.lostSound[random.nextInt(0, 1)]);
        }
        if (Counter >= 700)
            textTransparency = textTransparency + (1f - textTransparency) / 5.0f;
        if (Counter >= 1500)
            background.height = (int) (background.height + (backgroundMaxHeight - background.height) / 5.0);
        if (Counter >= 2000)
        {
            buttonTransparency = buttonTransparency + (0.8f - buttonTransparency) / 5.0f;
            scoreTransparency = scoreTransparency + (1f - scoreTransparency) / 5.0f;
        }
        if (Counter >= 2300)
            isButtonAnimate = false;
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
    
    public boolean checkButtonCollision(Rectangle box)
    {
        if (isButtonAnimate == true)
            return false;
            
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
        if (hoveredButtonName == "exit")
        {
            hoveredButtonName = "";
            gp.GameState = "loading";
            gp.loadingScreen.loadState = "presentsMenu";
            gp.loadingScreen.timerSwitch(true);
            gp.menu.menuState = "mainMenu";
            gp.loadingScreen.time = 0;
            gp.sound.playIntro();
        }
        if (hoveredButtonName == "tryagain")
        {
            hoveredButtonName = "";
            gp.sound.playSFX(getClass().getResource("/game_prepare.wav"));
            gp.menu.flashTransparency = 1f;
            gp.GameState = "menu";
            gp.menu.menuState = "gameLoad";
            gp.setup();
        }
    }
    
}
