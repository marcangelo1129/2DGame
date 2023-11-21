/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Dangerouze
 */
public class OBJ_AssaultRifle extends SuperObject {
    
    public OBJ_AssaultRifle()
    {
        name = "AssaultRifle";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/assault_rifle.png"));
        }catch(IOException ex) {ex.printStackTrace();}
    }
}
