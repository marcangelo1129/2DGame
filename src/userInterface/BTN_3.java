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
public class BTN_3 extends UIObject {
    public BTN_3()
    {
        name = "BTN3";
        area = new Rectangle(55,20,30, 30);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/UI/GameUI/BTN_3.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
