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
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class loadingScreen {
    GamePanel gp;
    BufferedImage loadingImage, netbeansLogo, mainMenu, titleText, subtitleText;
    UtilityTool uTool = new UtilityTool();
    Rectangle loadingBar = new Rectangle(0,0,700,25);
    public double loadingProgress = 1d;
    public String loadState;
    int screenSizeX;
    int screenSizeY;
    Graphics2D g2;
    Font font;
    int loadingWidth;
    int loadingHeight;
    static ScheduledFuture<?> loadingTimer;
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public int time = 0;
    double imageWidth;
    double imageHeight;
    
    double name1startX, name1endX;
    double name1endY;
    double name2startX, name2endX;
    double name2endY;
    double name3startX, name3endX;
    double name3endY;
    
    public loadingScreen(GamePanel gp)
    {
        this.gp = gp;
        loadState = "loading";
        screenSizeX = gp.screenSize.width;
        screenSizeY = gp.screenSize.height;
        loadingWidth = screenSizeX;
        loadingHeight = (int) (loadingWidth *0.54f);
        imageWidth = loadingWidth * 0.6;
        imageHeight = loadingHeight * 0.6;
        name1endX = (screenSizeX / 2) + 50;
        name1endY = (screenSizeY / 2) - 95;
        name2endX = (screenSizeX / 2) - 240;
        name2endY = (screenSizeY / 2) + 25;
        name3endX = (screenSizeX / 2) + 100;
        name3endY = (screenSizeY / 2) + 90;
        name1startX = name1endX + 1000;
        name2startX = name2endX - 1000;
        name3startX = name3endX + 1000;
        try {
            loadingImage = ImageIO.read(getClass().getResourceAsStream("/userInterface/loadingScreen.jpg"));
            loadingImage = uTool.scaleImage(loadingImage, loadingWidth, loadingHeight);
            netbeansLogo = ImageIO.read(getClass().getResourceAsStream("/userInterface/netbeansLogo.png"));
            netbeansLogo = uTool.scaleImage(netbeansLogo, 100, 100);
            mainMenu = ImageIO.read(getClass().getResourceAsStream("/userInterface/mainMenu.jpg"));
            mainMenu = uTool.scaleImage(mainMenu, screenSizeX, screenSizeY);
            titleText = ImageIO.read(getClass().getResourceAsStream("/userInterface/titleText.png"));
            titleText = uTool.scaleImage(titleText, 988, 468);
            subtitleText = ImageIO.read(getClass().getResourceAsStream("/userInterface/subtitleText.png"));
            subtitleText = uTool.scaleImage(subtitleText, 988, 468);
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Valorax.otf")).deriveFont(32f);
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(loadingScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loading(Graphics2D g2)
    {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2 = g2;   
        if (loadState == "loading")
        {
            g2.setColor(Color.white);
            centerText("Loading Assets", screenSizeX / 2, (screenSizeY / 2) - 65, 25, 1);
            g2.fillRect((screenSizeX / 2) - (loadingBar.width / 2), (screenSizeY / 2) - (loadingBar.height / 2) + 65, (int) (loadingBar.width * loadingProgress), loadingBar.height);
        }
        else if(loadState == "loaded")
        {
            g2.setColor(Color.white);
            AlphaComposite alcom;
            if (time <= 300)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) time / 300);
                g2.setComposite(alcom);
            }
            imageWidth = imageWidth + (loadingImage.getWidth() - imageWidth) / 5.0;
            imageHeight = imageHeight + (loadingImage.getHeight() - imageHeight) / 5.0;
            
            g2.drawImage(loadingImage, (int) ((screenSizeX / 2) - (imageWidth / 2)), (int) ((screenSizeY / 2) - (imageHeight / 2)), (int)imageWidth, (int)imageHeight, null);
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2.setComposite(alcom);
            centerText("Complete", screenSizeX / 2, (screenSizeY / 2) - 65, 25, 1);
            g2.fillRect((screenSizeX / 2) - (loadingBar.width / 2), (screenSizeY / 2) - (loadingBar.height / 2) + 65, (int) (loadingBar.width * loadingProgress), loadingBar.height);
            g2.setColor(Color.orange);
            centerText("Click to Start", screenSizeX / 2, (screenSizeY / 2), 21, 1);
        }
        else if (loadState == "presents")
        {
            g2.setColor(Color.white);
            g2.drawImage(netbeansLogo, ((screenSizeX / 2) - (netbeansLogo.getWidth() / 2)), ((screenSizeY / 2) - (netbeansLogo.getHeight() / 2)),  null);
            
            
            centerText("Marc Angelo Cataluna", (int) name1startX, (int) name1endY, 20, (float) time / 1000);
            name1startX = name1startX + (name1endX - name1startX) / 15.0;
            if (time >= 300)
            {
                centerText("Ryan Cezar Pomperada", (int) name2startX, (int) name2endY, 20, (float) (time - 300) / 1000);
                name2startX = name2startX + (name2endX - name2startX) / 15.0;
            }
            if (time >= 600)
            {
                centerText("John Rey Ortigas", (int) name3startX, (int) name3endY, 20, (float) (time - 600) / 1000);
                name3startX = name3startX + (name3endX - name3startX) / 15.0;
            }
            if (time >= 2000)
                centerText("Presents", screenSizeX / 2, (screenSizeY / 2) + 150, 20, (float) (time - 2000) / 1000);
            g2.setColor(Color.black);
            AlphaComposite alcom;
            if (time >= 4000 && time <= 5000)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (time - 4000) / 1000);
                g2.setComposite(alcom);
                g2.fillRect(0, 0, screenSizeX, screenSizeY);
            }
            else if (time > 5000)
                g2.fillRect(0, 0, screenSizeX, screenSizeY);
            
            if (time >= 8000)
            {
                loadState = "presentsMenu";
                time = 0;
                gp.sound.playIntro();
            }
        }
        else if (loadState == "presentsMenu" || loadState == "presentsMenuFinished")
        {
            g2.setColor(Color.white);
            g2.drawImage(mainMenu, 0, 0,  null);
            g2.drawImage(titleText, (screenSizeX / 2) - (titleText.getWidth() / 2), (screenSizeY / 2) - (titleText.getHeight() / 2) - 40, null);
            g2.drawImage(subtitleText, (screenSizeX / 2) - (titleText.getWidth() / 2), (screenSizeY / 2) - (titleText.getHeight() / 2) + 40, null);
            AlphaComposite alcom;
            g2.setColor(Color.black);
            if (time <= 1000)
            {
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0 - (time / 1000f));
                g2.setComposite(alcom);
                g2.fillRect(0, 0, screenSizeX, screenSizeY);
            }
            alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2.setComposite(alcom);
            g2.setColor(Color.orange);
            if (time >= 2000)
            {   
                centerText("Click To Start", screenSizeX / 2, (screenSizeY / 2) + 100, 21, (float) (time - 2000) / 500);
                loadState = "presentsMenuFinished";
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
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
    
    public void timerSwitch(boolean Switch)
    {
        if (Switch == true)
        {
            loadingTimer = executorService.scheduleAtFixedRate(timer, 0, 10, TimeUnit.MILLISECONDS);
        }
        else
        {
            loadingTimer.cancel(true);
            //executorService.shutdownNow();
        }
    }
    
    Runnable timer = () ->
    {
        time += 10;
    };
}
