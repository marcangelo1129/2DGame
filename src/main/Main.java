/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;
import javax.swing.JFrame;

/**
 *
 * @author Dangerouze
 */
public class Main {

    public static void main(String args[]) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        
        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamepanel.startGameThread();
    }
}
