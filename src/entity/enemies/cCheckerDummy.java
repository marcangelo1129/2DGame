/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.enemies;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class cCheckerDummy extends Entity {
    public cCheckerDummy(GamePanel gp)
    {
        super(gp);
        solidArea = new Rectangle(0, 0, 48, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        direction = "down";
    }
    
}
