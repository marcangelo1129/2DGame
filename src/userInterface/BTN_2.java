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
public class BTN_2 extends UIObject {
    public BTN_2()
    {
        name = "BTN2";
        area = new Rectangle(35,20,30, 30);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/UI/GameUI/BTN_2.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
