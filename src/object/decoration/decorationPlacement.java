/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object.decoration;

import main.GamePanel;
import object.SuperObject;

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
        //Entrance newsite
        gp.objDeco.add(new bigtree(gp, 33, 62));
        gp.objDeco.add(new bigtree(gp, 35, 66));
        gp.objDeco.add(new bigtree(gp, 32, 70));
        gp.objDeco.add(new bigtree(gp, 36, 73));
        gp.objDeco.add(new bigtree(gp, 25, 73));
        gp.objDeco.add(new bigtree(gp, 31, 76));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 37, 53, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 35, 53, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 33, 53, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 29, 53, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 27, 53, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 25, 53, "horizontal"));
        gp.objDeco.add(new tree(gp, 30, 60));
        gp.objDeco.add(new tree(gp, 27, 60));
        gp.objDeco.add(new tree(gp, 24, 60));
        gp.objDeco.add(new tree(gp, 21, 60));
        gp.objDeco.add(new tree(gp, 18, 60));
        gp.objDeco.add(new tree(gp, 15, 60));
        gp.objDeco.add(new tree(gp, 12, 60));
        gp.objDeco.add(new bigtree(gp, 5, 60));
        gp.objDeco.add(new bigtree(gp, 2, 59));
        
        gp.objDeco.add(new itBuildingBoard(gp, 6, 65));
        
        //overpass newsite
        gp.objDeco.add(new bigtree(gp, 16, 36));
        gp.objDeco.add(new bigtree(gp, 19, 39));
        gp.objDeco.add(new bigtree(gp, 18, 44));
        gp.objDeco.add(new tree(gp, 16, 41));
        gp.objDeco.add(new tree(gp, 19, 34));
        gp.objDeco.add(new bench_ORIENTED(gp, 26, 42, "right"));
        gp.objDeco.add(new bench_ORIENTED(gp, 26, 41, "right"));
        gp.objDeco.add(new bench_ORIENTED(gp, 26, 39, "right"));
        gp.objDeco.add(new bench_ORIENTED(gp, 26, 38, "right"));
        
        gp.objDeco.add(new ceaBuildingBoard(gp, 6, 31));
        
        //Entrance oldsite
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 64, 69, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 64, 65, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 66, 65, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 68, 65, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 72, 65, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 74, 65, "horizontal"));
        gp.objDeco.add(new road_barrier_ORIENTED(gp, 76, 65, "horizontal"));
        
        //oldsite Road
        gp.objDeco.add(new cafeteriaBoard(gp, 91, 48));
        gp.objDeco.add(new presidentOfficeBoard(gp, 82, 71));
        
        
        //Mainroad
        gp.objDeco.add(new car_blue_ORIENTED(gp, 58, 96, "right"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 53, 93, "front"));
        gp.objDeco.add(new bus_ORIENTED(gp, 44, 93, "back"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 48, 89, "left"));
        gp.objDeco.add(new police_car_ORIENTED(gp, 57, 88, "back"));
        gp.objDeco.add(new bus_ORIENTED(gp, 42, 80, "right"));
        gp.objDeco.add(new traffic_cone(gp, 44, 86));
        gp.objDeco.add(new car_orange_ORIENTED(gp, 54, 84, "left"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 59, 81, "front"));
        gp.objDeco.add(new car_green_ORIENTED(gp, 44, 72, "back"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 49, 76, "right"));
        gp.objDeco.add(new car_green_ORIENTED(gp, 56, 74,   "left"));
        gp.objDeco.add(new bus_ORIENTED(gp, 52, 65, "front"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 46, 67, "left"));
        gp.objDeco.add(new police_car_ORIENTED(gp, 40, 61, "left"));
        gp.objDeco.add(new car_blue_ORIENTED(gp, 59, 68, "back"));
        gp.objDeco.add(new car_blue_ORIENTED(gp, 56, 59, "back"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 48, 59, "back"));
        gp.objDeco.add(new bus_ORIENTED(gp, 57, 51, "front"));
        gp.objDeco.add(new police_car_ORIENTED(gp, 53, 53, "right"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 45, 53, "back"));
        gp.objDeco.add(new car_orange_ORIENTED(gp, 49, 48, "right"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 43, 40, "front"));
        gp.objDeco.add(new bus_ORIENTED(gp, 52, 42, "left"));
        gp.objDeco.add(new car_orange_ORIENTED(gp, 56, 39, "left"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 49, 37, "back"));
        gp.objDeco.add(new car_blue_ORIENTED(gp, 41, 34, "right"));
        gp.objDeco.add(new car_green_ORIENTED(gp, 52, 31, "left"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 61, 33, "back"));
        gp.objDeco.add(new car_blue_ORIENTED(gp, 55, 28, "front"));
        gp.objDeco.add(new bus_ORIENTED(gp, 47, 24, "back"));
        gp.objDeco.add(new police_car_ORIENTED(gp, 60, 23, "left"));
        gp.objDeco.add(new car_orange_ORIENTED(gp, 43, 25, "front"));
        gp.objDeco.add(new police_car_ORIENTED(gp, 54, 20, "right"));
        gp.objDeco.add(new car_blue_ORIENTED(gp, 49, 17, "left"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 44, 19, "back"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 58, 15, "right"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 42, 12, "front"));
        gp.objDeco.add(new car_orange_ORIENTED(gp, 55, 11, "back"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 51, 9, "back"));
        gp.objDeco.add(new bus_ORIENTED(gp, 42, 5, "left"));
        gp.objDeco.add(new car_blue_ORIENTED(gp, 54, 3, "right"));
        
        gp.objDeco.add(new fire_hydrant(gp, 60, 16));
        
        //newsite mainroad
        gp.objDeco.add(new bus_ORIENTED(gp, 4, 51, "right"));
        gp.objDeco.add(new traffic_cone(gp, 3, 51));
        gp.objDeco.add(new traffic_cone(gp, 12, 51));
        
        //bottom-left street
        gp.objDeco.add(new bus_ORIENTED(gp, 3, 96, "right"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 10, 94, "left"));
        gp.objDeco.add(new motorcycle_ORIENTED(gp, 21, 94, "right"));
        gp.objDeco.add(new car_red_ORIENTED(gp, 34, 98, "right"));
        
        //mini forest
        gp.objDeco.add(new bigtree(gp, 80, 12));
        gp.objDeco.add(new bigtree(gp, 83, 15));
        gp.objDeco.add(new bigtree(gp, 86, 14));
        gp.objDeco.add(new bigtree(gp, 90, 12));
        gp.objDeco.add(new bigtree(gp, 91, 15));
        
        gp.objDeco.add(new bigtree(gp, 79, 23));
        gp.objDeco.add(new bigtree(gp, 82, 20));
        gp.objDeco.add(new bigtree(gp, 85, 21));
        gp.objDeco.add(new bigtree(gp, 88, 23));
        gp.objDeco.add(new bigtree(gp, 90, 20));
        
        // to add more decoration, simply copy one code above, change class name
        // with its corresponding worldX and worldY coordinates.
    }
}
