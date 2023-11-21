/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Dangerouze
 */
public class weaponBackground extends UIObject {
    public weaponBackground()
    {
        name = "weaponUI";
        area = new Rectangle(1,1,255, 128);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/UI/GameUI/weaponBackground.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
