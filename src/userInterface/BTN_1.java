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
public class BTN_1 extends UIObject {
    public BTN_1()
    {
        name = "BTN1";
        area = new Rectangle(15,20,30, 30);
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guns/Gun_Assault.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}