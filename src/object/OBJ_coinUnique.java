/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class OBJ_coinUnique extends SuperObject {
    
    GamePanel gp;
    
    public OBJ_coinUnique(GamePanel gp)
    {
        this.gp = gp;
        solidArea = new Rectangle(0,0,48,48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        name = "coinUnique";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coin_unique.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException ex) {ex.printStackTrace();}
    }
    
    @Override
    public void pickUp()
    {
        int coin = random.nextInt(1000, 2000);
        gp.player.coins += coin;
        gp.ui.showMessage("+"+coin, gp.player.screenX, gp.player.screenY + 32, Color.orange);
        gp.sound.playSFX(getClass().getResource("/ui_point.wav"));
    }
}
