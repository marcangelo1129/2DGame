/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Dangerouze
 */

//this class handles the player's key inputs
public class KeyHandler implements KeyListener, MouseListener {

    GamePanel gp;
    
    public KeyHandler (GamePanel gp)
    {
        this.gp = gp;
    }
    
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    
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
            mouseLeftPressed = true;
            executorService.execute(gp.att);
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
