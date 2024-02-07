/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;
import Debug.DebugWindow;
import ai.PathFinder;
import entity.Entity;
import userInterface.UIGame;
import entity.Player;
import entity.Projectile;
import entity.enemies.cCheckerDummy;
import entity.enemies.lootSystem;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.SuperObject;
import object.decoration.decorationPlacement;
import object.guns.WeaponObject;
import tile.TileManager;
import userInterface.UIShop;
import userInterface.gameoverScreen;
import userInterface.loadingScreen;
import userInterface.mainMenu;
import userInterface.pauseMenu;

/**
 *
 * @author Dangerouze
 */
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    public final int tileScaling = 3;
    Random random;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    
    public String GameState = "loading";
    public float flashTransparency = 0f;
    
    public final Dimension screenSize = Toolkit. getDefaultToolkit(). getScreenSize();
    public final int tileSize = originalTileSize * tileScaling;
    
    //WORLD SETTINGS
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    
    //SYSTEM
    public TileManager tileM;
    public Sound sound;
    Main main;
    public KeyHandler KeyHandler;
    public DebugWindow dw;
    public AltThreadTool att;
    public CollisionChecker cChecker;
    public AssetSetter aSetter;
    Thread gameThread;
    Thread updateThread1;
    Thread updateThread2;
    Thread updateThread3;
    public loadingScreen loadingScreen;
    public waveFunction waveFunction;
    ScheduledFuture<?> update;
    ScheduledFuture<?> wave;
    
    
    //ENTITY & OBJECT
    public Player player;
    public ArrayList<SuperObject> obj;
    public ArrayList<SuperObject> objDeco;
    public WeaponObject wbj[];
    public decorationPlacement deco;
    public ArrayList<Projectile> projectileList;
    public ArrayList<Entity> entityList;
    public lootSystem lootSystem;
    cCheckerDummy cCheckerDummy;
    public boolean decoCollision[][];
    
    //UI
    public UIGame ui;
    public UIShop shop;
    public mainMenu menu;
    public gameoverScreen gameoverScreen;
    public pauseMenu pauseMenu;
    
    
    //other settings
    int FPS = 60; //game's speed
    public int centerScreenX;
    public int centerScreenY;
    public Point pointer;
    
    Runnable Preload = () ->
    {
        try
        {
            //SYSTEM
            loadingScreen.loadingProgress = 0;
            tileM = new TileManager(this);
            loadingScreen.loadingProgress += 0.055;
            sound = new Sound(this);
            loadingScreen.loadingProgress += 0.055;
            loadingScreen.loadingProgress += 0.055;
            dw = new DebugWindow(this);
            loadingScreen.loadingProgress += 0.055;
            att = new AltThreadTool(this);
            loadingScreen.loadingProgress += 0.055;
            cChecker = new CollisionChecker(this);
            loadingScreen.loadingProgress += 0.055;
            aSetter = new AssetSetter(this);
            waveFunction = new waveFunction(this);
            loadingScreen.loadingProgress += 0.055;
            cCheckerDummy = new cCheckerDummy(this);
            loadingScreen.loadingProgress += 0.055;
            random = new Random();


            //ENTITY & OBJECT
            loadingScreen.loadingProgress += 0.055;
            obj = new ArrayList<>();
            loadingScreen.loadingProgress += 0.055;
            objDeco = new ArrayList<>();
            loadingScreen.loadingProgress += 0.055;
            wbj = new WeaponObject[4];
            loadingScreen.loadingProgress += 0.055;
            deco = new decorationPlacement(this);
            loadingScreen.loadingProgress += 0.055;
            projectileList = new ArrayList<>();
            loadingScreen.loadingProgress += 0.055;
            entityList = new ArrayList<>();
            loadingScreen.loadingProgress += 0.055;
            decoCollision = new boolean[maxWorldCol][maxWorldRow];
            lootSystem = new lootSystem(this);


            //UI
            loadingScreen.loadingProgress += 0.055;
            menu = new mainMenu(this, main);
            loadingScreen.loadingProgress += 0.055;
            gameoverScreen = new gameoverScreen(this, main);
            pauseMenu = new pauseMenu(this, main);


            //other settings
            FPS = 60; //game's speed
            pointer = new Point(centerScreenX, centerScreenY);
            loadingScreen.loadingProgress = 1;

            //loading finished
            sound.playSFX(getClass().getResource("/loadingScreen/loadingFinished.wav"));
            loadingScreen.loadState = "loaded";
            loadingScreen.timerSwitch(true);
            att.ThreadStart();
        }
        catch (Exception e){e.printStackTrace();}
        
    };
    
    
    public GamePanel()//contructor for window
    {
        main = new Main();
        KeyHandler = new KeyHandler(this, main);
        loadingScreen = new loadingScreen(this);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler);
        this.addMouseListener(KeyHandler);
        this.setFocusable(true);
        startGameThread();
        executorService.execute(Preload);
        
    }
    
    public void ShowDebug()
    {
        dw.show();
    }
    public void setup()
    {
        executorService.execute(setupGame);
    }
    
    Runnable setupGame = () ->
    {
//        if (executorService.isShutdown() == false)
//            executorService.shutdown();
        player = new Player(this, KeyHandler, main);
        cCheckerDummy cCheckerDummy = new cCheckerDummy(this);
        while (true)
        {
            int col = random.nextInt(0, maxWorldCol - 1);
            int row = random.nextInt(0, maxWorldRow - 1);
            int tileNum = tileM.mapTileNum[col][row];
            cCheckerDummy.worldX = col * tileSize;
            cCheckerDummy.worldY = row * tileSize;
            cCheckerDummy.collisionOn = false;
            cChecker.checkDecoCollision(cCheckerDummy);
            if (tileM.tile[tileNum].collision == false && cCheckerDummy.collisionOn == false)
            {
                player.worldX = col * tileSize;
                player.worldY = row * tileSize;
                break;
            }
        }
        player.weaponStorage[1] = new object.guns.Gun_g17();
        centerScreenX = player.screenX;
        centerScreenY = player.screenY;
        ui = new UIGame(this, player, main);
        shop = new UIShop(this, main);
        entityList = new ArrayList<>();
        if (!projectileList.isEmpty())
            projectileList.clear();
        if (!obj.isEmpty())
            obj.clear();
        if (!objDeco.isEmpty())
            objDeco.clear();
        deco.setDecoration();
        main.instantiateCursor();
        aSetter.setObject();
        setCollision();
        waveFunction = new waveFunction(this);
        if (update != null)
            update.cancel(true);
        if (wave != null)
            wave.cancel(true);
        
        wave = executorService.scheduleAtFixedRate(att.update,0, 10, TimeUnit.MILLISECONDS);
        update = executorService.scheduleAtFixedRate(waveFunction.waveThread,0, 10, TimeUnit.MILLISECONDS);
        //dw.show();
        menu.menuState = "gameLoaded";
    };
    
    public void startGameThread()//starts a method in a new thread (games always uses multiple threads to avoid lag)
    {
        gameThread = new Thread(this);
        updateThread1 = new Thread(enemiesUpdateCaller1);
        updateThread2 = new Thread(enemiesUpdateCaller2);
        updateThread3 = new Thread(enemiesUpdateCaller3);
        gameThread.start();
        updateThread1.start();
        updateThread2.start();
        updateThread3.start();
        //executorService.scheduleAtFixedRate(enemiesUpdate, 0, 16, TimeUnit.MILLISECONDS);
    }
    int spriteCounter = 0;
    
    boolean updateNow = false;
    @Override
    public void run()//Game engine-like method. this method runs 60 times per second.
    {
        double drawInterval = 1000000000/FPS; //1 second in nanoseconds divided by FPS value. 0.0016 seconds or 16 milliseconds
        double nextDrawTime = System.nanoTime() + drawInterval;//System.nanoTime gets the system's time in nanoseconds
        
        while (gameThread != null)//checks if the thread still exists
        {
            update();
            updateNow = true;
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
            if (spriteCounter > 5 && GameState == "inGame")
            {
                spriteCounter = 0;
                dw.jLabel2.setText("X: "+(player.worldX + tileSize) / tileSize+"  Y: "+(player.worldY + tileSize) / tileSize);
            }
            
            nextDrawTime += drawInterval;//the purpose of nextDrawTime is to avoid lag. DrawInterval (16 msec) - (total time taken by update and repaint method)
        }//                              so if those methods processed things slower than normal, there would be less pause on next process to compensate
        //                               previous slow method process time.
    }
    Runnable enemiesUpdateCaller1 = () ->
    {
        while (updateThread1 != null)//checks if the thread still exists
        {
            if (GameState == "inGame" && updateNow == true)
            {
                updateNow = false;
                executorService.execute(enemiesUpdate(8));
                executorService.execute(enemiesUpdate(16));
                executorService.execute(enemiesUpdate(24));
                executorService.execute(enemiesUpdate(32));
            }
            
            try
            {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex)//ignore this
            {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    Runnable enemiesUpdateCaller2 = () ->
    {
        while (updateThread2 != null)//checks if the thread still exists
        {
            if (GameState == "inGame" && updateNow == true)
            {
                updateNow = false;
                executorService.execute(enemiesUpdate(40));
                executorService.execute(enemiesUpdate(48));
                executorService.execute(enemiesUpdate(56));
                executorService.execute(enemiesUpdate(64));
            }
            
            try
            {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex)//ignore this
            {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    Runnable enemiesUpdateCaller3 = () ->
    {
        while (updateThread3 != null)//checks if the thread still exists
        {
            if (GameState == "inGame" && updateNow == true)
            {
                updateNow = false;
                executorService.execute(enemiesUpdate(72));
                executorService.execute(enemiesUpdate(80));
                executorService.execute(enemiesUpdate(88));
                executorService.execute(enemiesUpdate(96));
            }
            
            try
            {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex)//ignore this
            {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    Runnable nothing = () -> {};
    
    Runnable enemiesUpdate (int range)
    {
        if (entityList == null)
            return nothing;
        for (int i = range - 8; i < range; i++)
        {
            if (i < entityList.size() && entityList.get(i) != null && dw.disableAI.isSelected() == false)
            {
                if (entityList.get(i).onPath == true)
                {
                    int goalColArea = player.worldX + player.solidArea.x + (player.solidArea.width / 2);
                    int goalRowArea = player.worldY + player.solidArea.y + (player.solidArea.height / 2);
                    int goalCol = goalColArea / tileSize;
                    int goalRow = goalRowArea / tileSize;
                    
                    entityList.get(i).searchPath(goalCol, goalRow);
                }
            }
        }
        return nothing;
    }
    
    public void update()//usually changes variables
    {
        if (sound != null)
            sound.musicUpdate();
        
        if ((shop != null && shop.showShop == true) || (pauseMenu != null && pauseMenu.paused == true))
            return;
        
        if (GameState == "inGame" && player.gameOver == false)
        { 
            player.update();
            player.cameraPosition();
            for (int i = 0; i < entityList.size() && entityList.get(i) != null; i++)
            {
                entityList.get(i).Update();
                if (entityList.get(i).dyingTime <= 0)
                {
                    entityList.remove(i);
                    if (entityList.size() == 0)
                        break;
                }
                else if (entityList.get(i).isAlive == false)
                    entityList.get(i).dyingTime -= 16.6;
                else if (entityList.get(i).hp <=0)
                {
                    entityList.get(i).isAlive = false;
                    entityList.get(i).entityState = "dead";
                }
            }
            for (int i = 0; i < obj.size() && obj.get(i) != null; i++)
            {
                obj.get(i).update();
                if (obj.get(i).isAlive == false)
                    obj.remove(i);
            }
            if (entityList != null)
            {
                dw.entityCount.setText(""+entityList.size());
            }
        }
    }
    
    public void paintComponent(Graphics g)//handles graphics
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        if (GameState == "loading")
        {
            loadingScreen.loading(g2);
            g2.dispose();
            return;
        }
        else if (GameState == "menu")
        {
            menu.drawMenu(g2);
            g2.dispose();
            return;
        }
        
        tileM.draw(g2);
        
        for (int i = 0; i <= tileSize * maxWorldRow; i += tileSize)
        {
            for (int j = 0; j < objDeco.size(); j++)
            {
                if (objDeco.get(j) != null)
                {
                    if (objDeco.get(j).worldY >= i && objDeco.get(j).worldY < i+tileSize)
                        objDeco.get(j).drawDeco(g2, this);
                }
            }
            
            for (int j = 0; j < obj.size(); j++)
            {
                if (obj.get(j) != null)
                {
                    if (obj.get(j).worldY >= i && obj.get(j).worldY < i+tileSize)
                        obj.get(j).draw(g2, this);
                }
            }
            
            for (int j = 0; j < entityList.size() && entityList.get(j) != null; j++)
            {
                if (entityList.get(j).worldY >= i && entityList.get(j).worldY < i+tileSize)
                {
                    entityList.get(j).draw(g2);
                }
            }
            
            if (player.worldY >= i && player.worldY < i+tileSize)
            {
                player.draw(g2);
                Color defaultColor = g2.getColor();
                g2.setColor(Color.cyan);
                if (dw.DisplayCollisionBox.isSelected())
                    g2.drawRect(player.screenX+player.solidArea.x, player.screenY+player.solidArea.y, player.solidArea.width, player.solidArea.height);
                g2.setColor(defaultColor);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                player.drawWeapon(g2);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            
            for (int j = 0; j < projectileList.size(); j++)
            {
                Projectile projectileTemp = projectileList.get(j);
                if (projectileTemp != null)
                {
                    if (projectileTemp.solidArea.y >= i && projectileTemp.solidArea.y < i+tileSize)
                    {
                        int centerX = projectileTemp.centerX;
                        int centerY = projectileTemp.centerY;
                        BufferedImage image = projectileTemp.image;
                        double angle = Math.toRadians(projectileTemp.angle - 270);
                        int screenX = (int) (projectileTemp.worldX - player.worldX + player.screenX + projectileTemp.centerX);
                        int screenY = (int) (projectileTemp.worldY - player.worldY + player.screenY + projectileTemp.centerY);
                        

                        if (dw.DisplayCollisionBox.isSelected())
                        {
                            g2.drawRect(projectileTemp.solidArea.x - player.worldX + player.screenX + projectileTemp.centerX, projectileTemp.solidArea.y - player.worldY + player.screenY + projectileTemp.centerY, projectileTemp.solidArea.width, projectileTemp.solidArea.height);
                            //g2.drawRect(screenX + centerX + 78,screenY + centerY, 10, 10);
                        }
                        
                        if (projectileTemp.name == "bullet_trail" && projectileTemp.timer <= 300)
                        {
                            float transparency = (float) (projectileTemp.timer / 300.00);
                            if (transparency < 0)
                                transparency = 0;
                            else if (transparency > 1)
                                transparency = 1;
                            
                            AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
                            g2.setComposite(alcom);
                        }
                        g2.rotate(angle, screenX + centerX, screenY + centerY);
                        g2.drawImage(image,screenX,screenY,null);
                        g2.rotate(-angle, screenX + centerX, screenY + centerY);
                        
                        //g2 reset
                        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                        g2.setComposite(alcom);
                        
                        //this loop can throw multiple exeptions without crashing but according to the internet, "unless you're throwing hundreds or thousands of exceptions, you still won't notice the cost." - by Patashu at https://stackoverflow.com/questions/16451777/is-it-expensive-to-use-try-catch-blocks-even-if-an-exception-is-never-thrown
                    }
                }
            }
        }
        if (att.explosion.time > 0)
        {
            
            for (int i = 0; i <= 8; i++)
            {
                if (att.explosion.transparency[i] >= 0.0 && att.explosion.transparency[i] <= 1.0)
                {
                    int screenX = (int) (att.explosion.worldX - player.worldX + player.screenX + att.explosion.centerX[i]) - (att.explosion.width / 2);
                    int screenY = (int) (att.explosion.worldY - player.worldY + player.screenY + att.explosion.centerY[i]) - (att.explosion.height / 2);
                    AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, att.explosion.transparency[i]);
                    g2.setComposite(alcom);
                    
                    g2.drawImage(att.explosion.image[i], screenX, screenY, null);
                    g2.drawRect(screenX - 5, screenY - 5, 10, 10);
                    
                    //g2 reset
                    alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                    g2.setComposite(alcom);
                }
            }
        }
        
        if (player.gameOver == false)
        {
            ui.draw(g2);
            shop.drawShop(g2); 
            pauseMenu.drawPauseMenu(g2);
        }
        else
        {
            gameoverScreen.draw(g2);
        }
        
        if (dw.ShowCameraPointer.isSelected())
            g2.drawRect(pointer.x - 6, pointer.y - 30, 10, 10);
        att.drawProgressBar(g2);
        if (flashTransparency > 0)
            {
                g2.setColor(Color.black);
                AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, flashTransparency);
                g2.setComposite(alcom);
                flashTransparency -= 0.032;
                g2.fillRect(0, 0, screenSize.width, screenSize.height);
            }
        g2.dispose();
    }
    
    public void setCollision()
    {
        int col = 0;
        int row = 0;
        while (col < maxWorldCol && row < maxWorldRow)
        {
            cCheckerDummy.worldX = col * tileSize;
            cCheckerDummy.worldY = row * tileSize;
            cCheckerDummy.collisionOn = false;
            cChecker.checkDecoCollision(cCheckerDummy);
            if (cCheckerDummy.collisionOn == true)
            {
                decoCollision[col][row] = true;
            }
            col++;
            if (col == maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
    }
    
}



