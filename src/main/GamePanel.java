/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
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
    public final int maxWorldWidth = tileSize * maxWorldCol;
    public final int maxWorldHeight = tileSize * maxWorldRow;
    
    
    TileManager tileM = new TileManager(this);
    KeyHandler KeyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, KeyHandler);
    
    int FPS = 60; //game's speed
    
    
    
    public GamePanel()//contructor for window
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler);
        this.setFocusable(true);
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
        player.draw(g2);
        
        g2.dispose();
    }
    
}
