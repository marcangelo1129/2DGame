/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import object.OBJ_AmmoBox;
import object.OBJ_M4A1;

/**
 *
 * @author Dangerouze
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter (GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void setObject()
    {
        gp.obj[0] = new OBJ_AmmoBox(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 18 * gp.tileSize;
        
        gp.obj[1] = new OBJ_M4A1(gp);
        gp.obj[1].worldX = 25 * gp.tileSize;
        gp.obj[1].worldY = 18 * gp.tileSize;
        
        
    }
}
