/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Dangerouze
 */

//this class handles the player's key inputs
public class KeyHandler implements KeyListener, MouseListener {

    Random random = new Random();
    GamePanel gp;
    Main main;
    
    public KeyHandler (GamePanel gp, Main main)
    {
        this.gp = gp;
        this.main = main;
    }
    
    public ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, onePressed, twoPressed, threePressed;
    public boolean mouseLeftPressed, mouseRightPressed;
    public boolean RPressed;

    @Override
    public void keyTyped(KeyEvent e) {} // useless

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D)
        { 
            rightPressed = true;
        }
        if (code == KeyEvent.VK_1)
        {
            onePressed = true;
        }
        if (code == KeyEvent.VK_2)
        { 
            twoPressed = true;
        }
        if (code == KeyEvent.VK_3)
        { 
            threePressed = true;
        }
        if (code == KeyEvent.VK_R)
        { 
            RPressed = true;
            executorService.execute(gp.att);
        }
        if (code == KeyEvent.VK_B && gp.GameState == "inGame")
        {
            if ((gp.pauseMenu != null && gp.pauseMenu.paused == true) || (gp.player != null && gp.player.gameOver == true))
                return;
            gp.shop.shopToggler();
        }
        if (code == KeyEvent.VK_ESCAPE && gp.GameState == "inGame")
        {
            if ((gp.shop != null & gp.shop.showShop == true) || (gp.player != null && gp.player.gameOver == true))
                return;
            gp.pauseMenu.pauseToggler();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D)
        { 
            rightPressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}// useless

    @Override
    public void mousePressed(MouseEvent e)
    {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1)
        {
            if (gp.loadingScreen.loadState == "loaded")
            {
                gp.sound.playSFX(getClass().getResource("/loadingScreen/presents.wav"));
                gp.loadingScreen.loadState = "presents";
                //gp.GameState = "menu";
                //gp.loadingScreen.loadState = "ehhhh";
                gp.loadingScreen.time = 0;
                return;
            }
            if (gp.loadingScreen.loadState == "presentsMenuFinished")
            {
                gp.sound.playSFX(getClass().getResource("/game_start.wav"));
                gp.menu.flashTransparency = 1f;
                gp.GameState = "menu";
                gp.loadingScreen.loadState = "ehhhh";
                gp.loadingScreen.timerSwitch(false);
                return;
            }
            if (gp.GameState == "menu")
            {
                if (gp.menu.menuState == "gameLoaded")
                {
                    gp.flashTransparency = 1f;
                    gp.sound.playSFX(getClass().getResource("/loadout_equip.wav"));
                    gp.GameState = "inGame";
                    gp.sound.stopMusic();
                    gp.sound.playMusic();
                    Point mouse = gp.main.getMouseCoordinates();
                    gp.pointer.x = mouse.x;
                    gp.pointer.y = mouse.y;
                    return;
                }
                gp.menu.buttonClick();
                return;
            }
            if (gp.GameState == "inGame")
            {
                if (gp.player.gameOver == true)
                {
                    gp.gameoverScreen.buttonClick();
                    return;
                }
                if (gp.shop.showShop == true)
                {
                    gp.shop.buttonClick();
                    return;
                }
                if (gp.pauseMenu.paused == true)
                {
                    gp.pauseMenu.buttonClick();
                    return;
                }
                mouseLeftPressed = true;
                executorService.execute(gp.att);
            }
        }
    }
        

    @Override
    public void mouseReleased(MouseEvent e)
    {
        int code = e.getButton();
        
        if (code == MouseEvent.BUTTON1)
        {
            mouseLeftPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}// useless

    @Override
    public void mouseExited(MouseEvent e) {}// useless
    
}
