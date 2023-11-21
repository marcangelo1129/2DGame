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
public class BTN_2_Pressed extends UIObject {
    public BTN_2_Pressed()
    {
        name = "BTN2Pressed";
        area = new Rectangle(15,20,30, 30);
        try
        {
            image = ImageIO.read(new File("Sprites/userInterface/GameUI//BTN_2_Pressed.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
