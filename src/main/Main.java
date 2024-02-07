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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Image customImage;
    Cursor customCursor;
    
    public static void main(String args[]) throws IOException {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        weaponImage = new JLabel();
        window.add(weaponImage);
        GamePanel gamepanel = new GamePanel();
        //gamepanel.Preload();
        window.add(gamepanel);
        window.setUndecorated(true);
        window.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        //gamepanel.setupGame();
        //gamepanel.ShowDebug();
        //gamepanel.startGameThread();
    }
    
    public Point getMouseCoordinates()
    {
        PointerInfo a = MouseInfo.getPointerInfo();
	Point point = new Point(a.getLocation());
	SwingUtilities.convertPointFromScreen(point, window);
        point.x = point.x - window.getInsets().right;
        point.y = point.y - window.getInsets().top;
        
        return point;
    }
    public void instantiateCursor()
    {
        try {
            customImage = ImageIO.read(getClass().getResourceAsStream("/objects/blank.png"));
            customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customImage, new Point(15, 15), "customCursor");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor();
    }
    
    public void setCursor()
    {
        window.setCursor( customCursor );
    }
    
    public void resetCursor()
    {
       window.setCursor(null);
    }
    
}
