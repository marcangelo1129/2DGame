/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ai;

import entity.Entity;
import entity.enemies.cCheckerDummy;
import java.util.ArrayList;
import main.GamePanel;

/**
 *
 * @author Dangerouze
 */
public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    
    public PathFinder (GamePanel gp)
    {
        this.gp = gp;
        instantiateNode();
        //setCollision();
    }
    public void instantiateNode()
    {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow)
        {
            node[col][row] = new Node(col,row);
            
            col++;
            if (col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
    }
    
    public void resetNodes()
    {
        int col = 0;
        int row = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow)
        {
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            
            col++;
            if (col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
        
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    
    public void setNodes (int startCol, int startRow, int goalCol, int goalRow, Entity entity)
    {
        resetNodes();
        if (goalCol >= gp.maxWorldCol || goalRow >= gp.maxWorldRow || startCol >= gp.maxWorldCol || startRow >= gp.maxWorldRow)
            return;
        
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
        
        int col = 0;
        int row = 0;
        
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow)
        {
            
            //set solid node
            //check tiles
            int tileNum = gp.tileM.mapTileNum[col][row];
            if (gp.tileM.tile[tileNum].collision == true)
            {
                node[col][row].solid = true;
            }
            
            //check decoObjects
            if (gp.decoCollision[col][row] == true)
                node[col][row].solid = true;
            
            //set cost
            getCost(node[col][row]);
            
            col++;
            if (col == gp.maxWorldCol)
            {
                col = 0;
                row++;
            }
        }
    }
    public void getCost(Node node)
    {
        // G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        
        //H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        
        //F cost
        node.fCost = node.gCost + node.hCost;
    }
    public boolean search()
    {
        while (goalReached == false && step < 2500)
        {
            try
            {
                int col = currentNode.col;
                int row = currentNode.row;

                //check the current node
                currentNode.checked = true;
                openList.remove(currentNode);

                //open the nodes
                if (row - 1 >= 0)
                {
                    openNode(node[col][row-1]);
                }
                if (col - 1 >= 0)
                {
                    openNode(node[col-1][row]);
                }
                if (row + 1 < gp.maxWorldRow)
                {
                    openNode(node[col][row+1]);
                }
                if (col + 1 < gp.maxWorldCol)
                {
                    openNode(node[col+1][row]);
                }
                if (row - 1 >= 0 && col - 1 >= 0)
                {
                    openNode(node[col-1][row-1]);
                }
                if (row + 1 < gp.maxWorldRow && col - 1 >= 0)
                {
                    openNode(node[col-1][row+1]);
                }
                if (row + 1 < gp.maxWorldRow && col + 1 < gp.maxWorldCol)
                {
                    openNode(node[col+1][row+1]);
                }
                if (row - 1 >= 0 && col + 1 < gp.maxWorldCol)
                {
                    openNode(node[col+1][row-1]);
                }


                //find the best node
                int bestNodeIndex = 0;
                int bestNodefCost = 999;

                for (int i = 0; i < openList.size(); i++)
                {
                    Node nodeSet = openList.get(i);
                    //check if this node's F cost is better
                    if (nodeSet != null)
                    {
                        if (nodeSet.fCost < bestNodefCost)
                        {
                            bestNodeIndex = i;
                            bestNodefCost = nodeSet.fCost;
                        }
                        //if F cost is equal, check the G cost
                        else if (nodeSet.fCost == bestNodefCost)
                        {
                            if (openList.get(bestNodeIndex) != null && (nodeSet.gCost < openList.get(bestNodeIndex).gCost))
                            {
                                bestNodeIndex = i;
                            }
                        }
                    }

                }
                // if there is no node in the openList, end this loop
                if (openList.size() == 0)
                {
                    break;
                }

                //after the loop, openList[bestNodeIndex] is the next step (= currentNode)
                currentNode = openList.get(bestNodeIndex);

                if (currentNode == goalNode)
                {
                    goalReached = true;
                    trackThePath();
                }
                step++;
            }
            catch (Exception e){e.printStackTrace();}
        }
        return goalReached;
    }
    public void openNode (Node node)
    {
        if (node.open == false && node.checked == false && node.solid == false)
        {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    public void trackThePath()
    {
        Node current = goalNode;
        int antiLock = 0;
        
        while (current != startNode)
        {
            antiLock++;
            pathList.add(0,current);
            current = current.parent;
            if (antiLock > 200)
                break;
        }
    }
}
