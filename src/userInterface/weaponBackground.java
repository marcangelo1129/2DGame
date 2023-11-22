/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userInterface;

import java.awt.Rectangle;
import java.io.File;
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
        area = new Rectangle(10,422,288, 144);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/userInterface/GameUI/weaponBackground.png"));
            image = uTool.scaleImage(image, area.width, area.height);
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
