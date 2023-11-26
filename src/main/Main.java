/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package main;
import Debug.DebugWindow;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dangerouze
 */
public class Main {
    static JFrame window = new JFrame();
    public static javax.swing.JLabel weaponImage;
    
    public static void main(String args[]) throws IOException {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        weaponImage = new JLabel();
        window.add(weaponImage);
        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamepanel.setupGame();
        gamepanel.ShowDebug();
        gamepanel.startGameThread();
    }
    
    public Point getMouseCoordinates()
    {
        PointerInfo a = MouseInfo.getPointerInfo();
	Point point = new Point(a.getLocation());
	SwingUtilities.convertPointFromScreen(point, window);
        
        return point;
    }
    
    public void setCursor() throws IOException
    {
        Image customImage = ImageIO.read(getClass().getResourceAsStream("/objects/Cursor.png"));
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customImage, new Point(15, 15), "customCursor");
        window.setCursor( customCursor );
    }
    
}
