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
import object.guns.Gun_smaw;

/**
 *
 * @author Dangerouze
 */
public class OBJ_smaw extends SuperObject {
    
    GamePanel gp;
    
    Gun_smaw smaw;
    
    public OBJ_smaw(GamePanel gp)
    {
        this.gp = gp;
        smaw = new Gun_smaw();
        solidArea = new Rectangle(0,0,112,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        name = "smaw";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/smaw.png"));
            image = uTool.scaleImage(image, 112, 32);
        }catch(IOException ex) {ex.printStackTrace();}
    }
    
    @Override
    public void pickUp()
    {
        smaw = gp.waveFunction.smaw;
        smaw.ammoInClipRemaining = 1;
        smaw.ammoRemaining = 2;
        gp.player.weaponStorage[2] = smaw;
        gp.ui.showMessage("Super Weapon Acquired", gp.player.screenX, gp.player.screenY + 32, Color.orange);
        gp.sound.playSFX(getClass().getResource("/weaponFX/wpn_pump.wav"));
        gp.player.powerMeterSound = true;
    }
}
