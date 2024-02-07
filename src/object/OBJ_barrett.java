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
import object.guns.Gun_barrett;

/**
 *
 * @author Dangerouze
 */
public class OBJ_barrett extends SuperObject {
    
    GamePanel gp;
    
    Gun_barrett barrett;
    
    public OBJ_barrett(GamePanel gp)
    {
        this.gp = gp;
        barrett = new Gun_barrett();
        solidArea = new Rectangle(0,0,112,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        name = "barrett";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/barrett.png"));
            image = uTool.scaleImage(image, 112, 32);
        }catch(IOException ex) {ex.printStackTrace();}
    }
    
    @Override
    public void pickUp()
    {
        barrett = gp.waveFunction.barrett;
        barrett.ammoInClipRemaining = 5;
        gp.player.weaponStorage[2] = barrett;
        gp.ui.showMessage("Super Weapon Acquired", gp.player.screenX, gp.player.screenY + 32, Color.orange);
        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_pump.wav"));
        gp.player.powerMeterSound = true;
    }
}
