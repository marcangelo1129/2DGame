/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.guns;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class muzzleFlash {
    public int width = 640;
    public int height = 64;
    public int spriteWidth = 128;
    public int spriteHeight = 64;
    public int spriteCount = 5;
    public double scaling = 0.25f;
    public int centerX = 3;
    public int centerY = (int) (-32 * scaling);
    public BufferedImage[] sprites = new BufferedImage[spriteCount];
    UtilityTool uTool = new UtilityTool();
    
    public muzzleFlash()
    {
        
        
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/muzzleFlash_spriteSheet.png"));
            for (int i = 0; i < spriteCount; i++)
            {
                sprites[i] = image.getSubimage(spriteWidth * i, 0, spriteWidth, spriteHeight);
                sprites[i] = uTool.scaleImage(sprites[i],(int) (spriteWidth * scaling),(int) (spriteHeight * scaling));
            }
        } catch (IOException ex) {ex.printStackTrace();}
    }
}
