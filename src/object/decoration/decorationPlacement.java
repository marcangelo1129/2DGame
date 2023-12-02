/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.decoration;

import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class decorationPlacement {
    GamePanel gp;
    public decorationPlacement(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setDecoration()
    {
        gp.objDeco.add(new bigTree(gp, 27, 15));
        gp.objDeco.add(new bigTree(gp, 29, 18));
        // to add more decoration, simply copy one code above, change class name
        // with its corresponding worldX and worldY coordinates.
    }
    
    
    
}
