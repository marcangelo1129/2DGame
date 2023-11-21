/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;
import userInterface.UIGame;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.SuperObject;
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
    TileManager tileM = new TileManager(this);
    KeyHandler KeyHandler = new KeyHandler();
    Sound sound = new Sound();
    Main main = new Main();
   
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
     Thread gameThread;
    //ENTITY & OBJECT
    public Player player = new Player(this, KeyHandler, main);
    public UIGame ui = new UIGame(this, player);
    public SuperObject obj[] = new SuperObject[10];
    public WeaponObject wbj[] = new WeaponObject[4];
    
    int FPS = 60; //game's speed
    
    
    
    public GamePanel()//contructor for window
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler);
        this.setFocusable(true);
    }
    
    public void setupGame() throws IOException
    {
        aSetter.setObject();
        main.setCursor();
        playMusic(0);
        
        //component.setCursor( null ); - To reset the cursor you use
        
    }
    
    public void startGameThread()//starts a method in a new thread (games always uses multiple threads to avoid lag)
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

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
            nextDrawTime += drawInterval;//the purpose of nextDrawTime is to avoid lag. drawInterval (16 msec) - (total time taken by update and repaint method)
        }//                              so if those methods processed things slower than normal, there would be no pause needed
    }
    
    public void update()//usually changes variables
    {
        player.update();
    }
    
    public void paintComponent(Graphics g)//handles graphics
    {
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
        
        player.draw(g2);
        ui.draw(g2);
        player.drawWeapon(g2);
        g2.dispose();
    }
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();
            
        
    }
    public void stopMusic()
    {
        sound.stop();
    }
    public void playSE(int i)
    {
        sound.setFile(i);
        sound.play();
    }
}
