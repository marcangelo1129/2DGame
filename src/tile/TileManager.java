/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import entity.Entity;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author geps
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    boolean drawPath = true;
    private Graphics2D g2;
    BufferedImage map;
    
    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[251];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        map = new BufferedImage(gp.maxWorldCol * gp.tileSize, gp.maxWorldRow * gp.tileSize, BufferedImage.TYPE_INT_ARGB);
        
        getTileImage();
        loadMap("/maps/Isat_Map.txt", false);
    }
    public void getTileImage()
    {
//        setup(0, "grass", false, false);
//        setup(1, "stone", true, true);
//        setup(2, "water", true, false);
//        setup(3, "road", false, false);
        setup(0,"000", true, true);
        setup(1,"001", true, true);
        setup(2,"002", false, false);
        setup(3,"003", false, false);
        setup(4,"004", false, false);
        setup(5,"005", false, false);
        setup(6,"006", false, false);
        setup(7,"007", false, false);
        setup(8,"008", false, false);
        setup(9,"009", false, false);
        setup(10,"010", false, false);
        setup(11,"011", false, false);
        setup(12,"012", true, true);
        setup(13,"013", false, false);
        setup(14,"014", false, false);
        setup(15,"015", false, false);
        setup(16,"016", false, false);
        setup(17,"017", false, false);
        setup(18,"018", false, false);
        setup(19,"019", false, false);
        setup(20,"020", false, false);
        setup(21,"021", false, false);
        setup(22,"022", false, false);
        setup(23,"023", true, true);
        setup(24,"024", false, false);
        setup(25,"025", false, false);
        setup(26,"026", false, false);
        setup(27,"027", false, false);
        setup(28,"028", false, false);
        setup(29,"029", false, false);
        setup(30,"030", false, false);
        setup(31,"031", false, false);
        setup(32,"032", false, false);
        setup(33,"033", false, false);
        setup(34,"034", true, true);
        setup(35,"035", false, false);
        setup(36,"036", false, false);
        setup(37,"037", false, false);
        setup(38,"038", false, false);
        setup(39,"039", false, false);
        setup(40,"040", false, false);
        setup(41,"041", false, false);
        setup(42,"042", false, false);
        setup(43,"043", false, false);
        setup(44,"044", false, false);
        setup(45,"045", true, true);
        setup(46,"046", false, false);
        setup(47,"047", false, false);
        setup(48,"048", false, false);
        setup(49,"049", false, false);
        setup(50,"050", false, false);
        setup(51,"051", false, false);
        setup(52,"052", false, false);
        setup(53,"053", false, false);
        setup(54,"054", false, false);
        setup(55,"055", false, false);
        setup(56,"056", true, true);
        setup(57,"057", false, false);
        setup(58,"058", false, false);
        setup(59,"059", false, false);
        setup(60,"060", false, false);
        setup(61,"061", false, false);
        setup(62,"062", false, false);
        setup(63,"063", false, false);
        setup(64,"064", false, false);
        setup(65,"065", false, false);
        setup(66,"066", false, false);
        setup(67,"067", true, true);
        setup(68,"068", false, false);
        setup(69,"069", false, false);
        setup(70,"070", false, false);
        setup(71,"071", false, false);
        setup(72,"072", false, false);
        setup(73,"073", false, false);
        setup(74,"074", false, false);
        setup(75,"075", false, false);
        setup(76,"076", false, false);
        setup(77,"077", false, false);
        setup(78,"078", true, true);
        setup(79,"079", false, false);
        setup(80,"080", false, false);
        setup(81,"081", false, false);
        setup(82,"082", false, false);
        setup(83,"083", false, false);
        setup(84,"084", false, false);
        setup(85,"085", false, false);
        setup(86,"086", false, false);
        setup(87,"087", false, false);
        setup(88,"088", false, false);
        setup(89,"089", true, true);
        setup(90,"090", false, false);
        setup(91,"091", false, false);
        setup(92,"092", false, false);
        setup(93,"093", false, false);
        setup(94,"094", false, false);
        setup(95,"095", false, false);
        setup(96,"096", false, false);
        setup(97,"097", false, false);
        setup(98,"098", false, false);
        setup(99,"099", false, false);
        setup(100,"100", true, true);
        setup(101,"101", false, false);
        setup(102,"102", false, false);
        setup(103,"103", false, false);
        setup(104,"104", false, false);
        setup(105,"105", false, false);
        setup(106,"106", false, false);
        setup(107,"107", false, false);
        setup(108,"108", false, false);
        setup(109,"109", false, false);
        setup(110,"110", false, false);
        setup(111,"111", true, true);
        setup(112,"112", true, true);
        setup(113,"113", false, false);
        setup(114,"114", false, false);
        setup(115,"115", false, false);
        setup(116,"116", false, false);
        setup(117,"117", false, false);
        setup(118,"118", false, false);
        setup(119,"119", true, true);
        setup(120,"120", true, true);
        setup(121,"121", true, true);
        setup(122,"122", true, true);
        setup(123,"123", true, true);
        setup(124,"124", true, true);
        setup(125,"125", true, true);
        setup(126,"126", true, true);
        setup(127,"127", true, true);
        setup(128,"128", true, true);
        setup(129,"129", true, true);
        setup(130,"130", true, true);
        setup(131,"131", true, true);
        setup(132,"132", true, true);
        setup(133,"133", true, true);
        setup(134,"134", true, true);
        setup(135,"135", true, true);
        setup(136,"136", true, true);
        setup(137,"137", true, true);
        setup(138,"138", true, true);
        setup(139,"139", true, true);
        setup(140,"140", false, false);
        setup(141,"141", false, false);
        setup(142,"142", false, false);
        setup(143,"143", false, false);
        setup(144,"144", false, false);
        setup(145,"145", false, false);
        setup(146,"146", false, false);
        setup(147,"147", false, false);
        setup(148,"148", false, false);
        setup(149,"149", false, false);
        setup(150,"150", false, false);
        setup(151,"151", false, false);
        setup(152,"152", false, false);
        setup(153,"153", false, false);
        setup(154,"154", false, false);
        setup(155,"155", false, false);
        setup(156,"156", false, false);
        setup(157,"157", false, false);
        setup(158,"158", false, false);
        setup(159,"159", false, false);
        setup(160,"160", false, false);
        setup(161,"161", false, false);
        setup(162,"162", false, false);
        setup(163,"163", true, true);
        setup(164,"164", false, false);
        setup(165,"165", false, false);
        setup(166,"166", false, false);
        setup(167,"167", false, false);
        setup(168,"168", false, false);
        setup(169,"169", false, false);
        setup(170,"170", false, false);
        setup(171,"171", false, false);
        setup(172,"172", true, true);
        setup(173,"173", false, false);
        setup(174,"174", false, false);
        setup(175,"175", false, false);
        setup(176,"176", false, false);
        setup(177,"177", false, false);
        setup(178,"178", false, false);
        setup(179,"179", false, false);
        setup(180,"180", false, false);
        setup(181,"181", false, false);
        setup(182,"182", false, false);
        setup(183,"183", true, true);
        setup(184,"184", false, false);
        setup(185,"185", false, false);
        setup(186,"186", false, false);
        setup(187,"187", false, false);
        setup(188,"188", false, false);
        setup(189,"189", false, false);
        setup(190,"190", false, false);
        setup(191,"191", false, false);
        setup(192,"192", false, false);
        setup(193,"193", false, false);
        setup(194,"194", false, false);
        setup(195,"195", false, false);
        setup(196,"196", false, false);
        setup(197,"197", false, false);
        setup(198,"198", false, false);
        setup(199,"199", false, false);
        setup(200,"200", false, false);
        setup(201,"201", false, false);
        setup(202,"202", false, false);
        setup(203,"203", false, false);
        setup(204,"204", false, false);
        setup(205,"205", true, true);
        setup(206,"206", true, true);
        setup(207,"207", false, false);
        setup(208,"208", false, false);
        setup(209,"209", true, false);
        setup(210,"210", true, false);
        setup(211,"211", true, false);
        setup(212,"212", true, false);
        setup(213,"213", true, false);
        setup(214,"214", false, false);
        setup(215,"215", false, false);
        setup(216,"216", true, true);
        setup(217,"217", false, false);
        setup(218,"218", false, false);
        setup(219,"219", false, false);
        setup(220,"220", true, true);
        setup(221,"221", true, false);
        setup(222,"222", false, false);
        setup(223,"223", true, false);
        setup(224,"224", true, false);
        setup(225,"225", true, false);
        setup(226,"226", true, false);
        setup(227,"227", true, true);
        setup(228,"228", true, true);
        setup(229,"229", true, false);
        setup(230,"230", true, false);
        setup(231,"231", true, false);
        setup(232,"232", true, false);
        setup(233,"233", true, false);
        setup(234,"234", true, false);
        setup(235,"235", true, false);
        setup(236,"236", true, true);
        setup(237,"237", true, true);
        setup(238,"238", true, true);
        setup(239,"239", true, true);
        setup(240,"240", true, true);
        setup(241,"241", true, true);
        setup(242,"242", true, true);
        setup(243,"243", true, true);
        setup(244,"244", true, true);
        setup(245,"245", true, true);
        setup(246,"246", true, true);
        setup(247,"247", true, true);
        setup(248,"248", true, true);
        setup(249,"249", true, true);
        setup(250,"250", true, true);
        

    }
    public void setup(int index, String imageName, boolean collision, boolean bulletCollision)
    {
        UtilityTool uTool = new UtilityTool();
        
        try
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tileMap1/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].bulletCollision = bulletCollision;
            
        }catch(IOException ex){ex.printStackTrace();}
    }
        public void loadMap(String filepath, boolean debugMode)
        {
            InputStream is;
            try
            {
                if (debugMode)
                {
                    is = new FileInputStream(filepath);
                }
                else
                {
                    is = getClass().getResourceAsStream(filepath);
                }
                
                
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                
                int col = 0;
                int row = 0;
                
                while(col < gp.maxWorldCol && row < gp.maxWorldRow)
                {
                    String line = br.readLine();
                    while (col < gp.maxWorldCol)
                    {
                        String numbers[] = line.split(" ");
                        
                        int num = Integer.parseInt(numbers[col]);
                        
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if(col == gp.maxWorldCol)
                    {
                        col = 0;
                        row++;
                    }
                }
                br.close();
            }
            catch(IOException e)
            {
                
            }
            drawWholeMap();
        }
        
        public void drawWholeMap()
        {
            Graphics g = map.getGraphics();
            int worldCol = 0;
            int worldRow = 0;

            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
            {
                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;

                g.drawImage(tile[tileNum].image,worldX ,worldY,null);
                worldCol++;
                if(worldCol == gp.maxWorldCol)
                {
                    worldCol = 0;
                    worldRow++;
                }
            }
            g.dispose();
        }
        
        public void draw(Graphics2D g2)
        {
            this.g2 = g2;
            int worldX = 0;
            int worldY = 0;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (map != null)
                g2.drawImage(map, screenX, screenY, null);
            
        if (gp.dw.showCoordinates.isSelected())
        {
            int worldCol = 0;
            int worldRow = 0;
            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
            {
                worldX = worldCol * gp.tileSize;
                worldY = worldRow * gp.tileSize;
                screenX = worldX - gp.player.worldX + gp.player.screenX;
                screenY = worldY - gp.player.worldY + gp.player.screenY;

                    centerText(worldCol+":"+worldRow, screenX+(gp.tileSize/2), screenY+(gp.tileSize/2));
                worldCol++;

                if(worldCol == gp.maxWorldCol)
                {
                    worldCol = 0;
                    worldRow++;
                }
            }
        }
        
//         int worldCol = 0;
//         int worldRow = 0;
//         
////         while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
////         {
////             int tileNum = mapTileNum[worldCol][worldRow];
////             
////             int worldX = worldCol * gp.tileSize;
////             int worldY = worldRow * gp.tileSize;
////             int screenX = worldX - gp.player.worldX + gp.player.screenX;
////             int screenY = worldY - gp.player.worldY + gp.player.screenY;
////             
////             g2.drawImage(tile[tileNum].image,screenX ,screenY,null);
////             if (gp.dw.showCoordinates.isSelected())
////                centerText(worldCol+":"+worldRow, screenX+(gp.tileSize/2), screenY+(gp.tileSize/2));
////             worldCol++;
////             
////             if(worldCol == gp.maxWorldCol)
////             {
////                 worldCol = 0;
////                 worldRow++;
////             }
////         }
        
        if (gp.dw.ShowAIPath.isSelected())
        {
            g2.setColor(new Color(255,0,0,70));
            for (int i = 0; i < gp.entityList.size(); i++)
            {
                if (gp.entityList.get(i) == null || gp.entityList.get(i).idleMode == true)
                    continue;
                Entity entity = gp.entityList.get(i);
                for (int j = 0; j < entity.pFinder.pathList.size(); j++)
                {
                    worldX = entity.pFinder.pathList.get(j).col * gp.tileSize;
                    worldY = entity.pFinder.pathList.get(j).row * gp.tileSize;
                    screenX = worldX - gp.player.worldX + gp.player.screenX;
                    screenY = worldY - gp.player.worldY + gp.player.screenY;

                    g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                }
            }
            g2.setColor(new Color(0,0,0,100));
        }
    }
                
        
        public void centerText(String text, int x, int y)
    {
        g2.setColor(Color.white);
        Font font = g2.getFont();
        FontMetrics metrics = g2.getFontMetrics(font);
        int textCenterX = (metrics.stringWidth(text) / 2);
        int textCenterY = metrics.getHeight() / 2 - metrics.getAscent();
        
        g2.drawString(text, x - textCenterX, y - textCenterY);
    }
}
