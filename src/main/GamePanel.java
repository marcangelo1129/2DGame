/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;
import Debug.DebugWindow;
import entity.Entity;
import userInterface.UIGame;
import entity.Player;
import entity.Projectile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.SuperObject;
import object.decoration.decorationPlacement;
import object.guns.WeaponObject;
import tile.TileManager;

/**
 *
 * @author Dangerouze
 */
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int tileScaling = 3;
    
    
    public final int tileSize = originalTileSize * tileScaling;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    //SYSTEM
    public TileManager tileM = new TileManager(this);
    KeyHandler KeyHandler = new KeyHandler(this);
    Sound sound = new Sound();
    Sound music = new Sound();
    Main main = new Main();
    public DebugWindow dw = new DebugWindow(this);
    public AltThreadTool att = new AltThreadTool(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;
    
    
    //ENTITY & OBJECT
    public Player player = new Player(this, KeyHandler, main);
    public SuperObject obj[] = new SuperObject[10];
    public SuperObject objDeco[] = new SuperObject[10];
    public WeaponObject wbj[] = new WeaponObject[4];
    public decorationPlacement deco = new decorationPlacement(this);
    public ArrayList<Projectile> projectileList = new ArrayList<>();
    
    //UI
    public UIGame ui = new UIGame(this, player, main);
    
    
    //other settings
    int FPS = 60; //game's speed
    public int centerScreenX = player.screenX;
    public int centerScreenY = player.screenY;
    public Point pointer = new Point(centerScreenX, centerScreenY);
    
    
    
    public GamePanel()//contructor for window
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler);
        this.addMouseListener(KeyHandler);
        this.setFocusable(true);
        
    }
    
    public void ShowDebug()
    {
        dw.show();
    }
    
    public void setupGame() throws IOException
    {
        aSetter.setObject();
        deco.setDecoration();
        main.setCursor();
        playMusic(0);
        
        //component.setCursor( null ); - To reset the cursor you use
        
    }
    
    public void startGameThread()//starts a method in a new thread (games always uses multiple threads to avoid lag)
    {
        gameThread = new Thread(this);
        gameThread.start();
        att.ThreadStart();
    }
    int spriteCounter = 0;
    @Override
    public void run()//Game engine-like method. this method runs 60 times per second.
    {
        double drawInterval = 1000000000/FPS; //1 second in nanoseconds divided by FPS value. 0.0016 seconds or 16 milliseconds
        double nextDrawTime = System.nanoTime() + drawInterval;//System.nanoTime gets the system's time in nanoseconds
        
        while (gameThread != null)//checks if the thread still exists
        {
            update();
            repaint();//goes to paintComponent method. don't ask me how
            
            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //convert this into millisecond since Thread.sleep accepts millisecond
                
                if (remainingTime < 0)
                    remainingTime = 0;
                
                Thread.sleep((long) remainingTime);//code responsible to run this method at 60 times per second.
            } 
            catch (InterruptedException ex)//ignore this
            {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            spriteCounter++;
            if (spriteCounter > 15)
            {
                spriteCounter = 0;
                dw.jLabel2.setText("X: "+(player.worldX + tileSize) / tileSize+"  Y: "+(player.worldY + tileSize) / tileSize);
            }
            
            nextDrawTime += drawInterval;//the purpose of nextDrawTime is to avoid lag. DrawInterval (16 msec) - (total time taken by update and repaint method)
        }//                              so if those methods processed things slower than normal, there would be less pause on next process to compensate
        //                               previous slow method process time.
    }
    
    public void update()//usually changes variables
    {
        player.update();
        player.cameraPosition();
        for (int i = 0; i < projectileList.size(); i++)
        {
            if (projectileList.get(i) != null)
            {
                int speed = projectileList.get(i).speed;
                double angle = projectileList.get(i).angle;
                angle = (double)((angle - 90) / 180 * Math.PI);
                projectileList.get(i).worldX += (Math.cos(angle) * speed);
                projectileList.get(i).worldY += (Math.sin(angle) * speed);
            }
        }
    }
    
    public void paintComponent(Graphics g)//handles graphics
    {
        //long time = System.nanoTime();
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);
        for (int i = 0; i < obj.length; i++)
        {
            if (obj[i] != null)
            {
                obj[i].draw(g2, this);
            }
        }
        for (int i = 0; i < objDeco.length; i++)
        {
            if (objDeco[i] != null)
            {
                objDeco[i].draw(g2, this);
            }
        }
        
        player.draw(g2);
        for (int i = 0; i < projectileList.size(); i++)
        {
            if (projectileList.get(i) != null)
            {
                int screenX = projectileList.get(i).worldX - player.worldX + player.screenX + projectileList.get(i).centerX;
                int screenY = projectileList.get(i).worldY - player.worldY + player.screenY + projectileList.get(i).centerY;
                double angle = projectileList.get(i).angle;
                angle = (angle - 90) / 180 * Math.PI;
                projectileList.get(i).solidArea.x = (int) (screenX + (Math.cos(angle) * projectileList.get(i).solidAreaOffset.x)) -6;
                projectileList.get(i).solidArea.y = (int) (screenY + (Math.sin(angle) * projectileList.get(i).solidAreaOffset.y)) -2;
                if (dw.jCheckBox2.isSelected())
                    g2.drawRect(projectileList.get(i).solidArea.x, projectileList.get(i).solidArea.y, projectileList.get(i).solidArea.width, projectileList.get(i).solidArea.height);
                g2.rotate(Math.toRadians(projectileList.get(i).angle - 90), screenX + projectileList.get(i).centerX, screenY + projectileList.get(i).centerY);
                g2.drawImage(projectileList.get(i).image,screenX,screenY,null);
                g2.rotate(Math.toRadians(-projectileList.get(i).angle + 90), screenX + projectileList.get(i).centerX, screenY + projectileList.get(i).centerY);
                
            }
        }
        player.drawWeapon(g2);
        for (int i = 0; i < objDeco.length; i++)
        {
            if (objDeco[i] != null)
            {
                objDeco[i].drawDecorationTop(g2, this);
            }
        }
        
        
        ui.draw(g2);
        if (dw.jCheckBox3.isSelected())
            g2.drawRect(pointer.x - 6, pointer.y - 30, 10, 10);
        att.drawProgressBar(g2);
        //System.out.println(System.nanoTime() - time);
        g2.dispose();
    }
    
    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
            
        
    }
    public void stopMusic()
    {
        music.stop();
    }
    public void playSE(int i)
    {
        music.setFile(i);
        music.play();
    }
}
