/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author Dangerouze
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public UtilityTool uTool = new UtilityTool();
    
    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        g2.drawImage(image,screenX ,screenY,null);
        if (gp.dw.jCheckBox2.isSelected())
            g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
    
    public void drawDeco(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        g2.drawImage(image,screenX - (image.getWidth() / 4) ,screenY - (image.getHeight() - 48),null);
        if (gp.dw.jCheckBox2.isSelected())
            g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
}
