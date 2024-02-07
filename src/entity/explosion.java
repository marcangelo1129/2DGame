/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class explosion {

    UtilityTool uTool = new UtilityTool();
    
    public String name;
    public BufferedImage[] image = new BufferedImage[9];
    public int worldX;
    public int worldY;
    public int time;
    public int delay;
    public int totalTime;
    public int range;
    public float[] transparency = new float[9];
    public int[] centerX = new int[9];
    public int[] centerY = new int[9];
    public int width = 1000;
    public int height = 1000;
    double scaleDecrement = 0.04;
    public URL[] sound = new URL[3];
    
    public explosion ()
    {
        name = "explosion";
        try
        {
            sound[0] = getClass().getResource("/weaponFX/explosion_1.wav");
            sound[1] = getClass().getResource("/weaponFX/explosion_2.wav");
            sound[2] = getClass().getResource("/weaponFX/explosion_3.wav");
            for (int i = 8; i >= 0; i--)
            {
                image[i] = ImageIO.read(getClass().getResourceAsStream("/objects/exp_1.png"));
                //image[i] = uTool.scaleImage(image[i], (int) (width * (1 - (scaleDecrement * (8-i)))), (int) (height * (1 - (scaleDecrement * (8-i)))));
                image[i] = uTool.scaleImage(image[i], (int) (width - (width * scaleDecrement * (8-i))), (int) (height - (height * scaleDecrement * (8-i))));
            }
            
        }catch(Exception ex) {ex.printStackTrace();}
        
        centerX[8] = image[8].getWidth() / 2;
        centerY[8] = image[8].getHeight() / 2;
        for (int i = 0; i < 8; i++)
        {
            centerX[i] = centerX[8] - (image[i].getWidth() / 2);
            centerY[i] = centerY[8] - (image[i].getHeight() / 2);
        }
        centerX[8] = 0;
        centerY[8] = 0;
    }
}
