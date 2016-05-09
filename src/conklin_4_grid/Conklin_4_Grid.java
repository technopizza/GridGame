/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conklin_4_grid;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jconklin2391
 */
 public class Conklin_4_Grid {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    int coord_X, coord_Y;
    char[] command;
    static boolean game = true;

    Trap[] traps = new Trap[19];
   
    
    Grid grid = new Grid(17, 17, '□');
    Player player = new Player('♜', 6, 6, 5);

    public static void printEmptyLines(int n){
        for(int i = 0; i < n; i++){
            System.out.println("");
        }
    }
    
    public static void printStats(Player p){
        System.out.println("Health: " + p.getHealth() + "    Enemies Slain: " + p.getScore());
    }
    
    public int[] getRandomCoordinate(Grid g){
       int x = random.nextInt(g.getHeight());
       int y = random.nextInt(g.getWidth());
       int[] coords = new int[2];
       coords[0] = x;
       coords[1] = y;
       return coords;
    }
    
    public char[] getCommand() {
        System.out.println("Which direction do you want to travel?");
        String output = scanner.next();
        output = output.toUpperCase();
        return output.toCharArray();
    }

    void determineEnemyDirection(Grid g, Player player, Player enemy){
        String dir = "";
        if(player.getPositionX() > (g.getWidth() - 1)){
            dir = dir + "E";
        }
        else{
            dir = dir + "W";
        }
        if(player.getPositionY() > (g.getHeight() - 1)){
            dir = dir + "S";
        }
        else{
            dir = dir + "N";
        }
    }
    
    public void movePlayer(Player player, char[] direction) {
        player.setLastPositionX(player.getPositionX());
        player.setLastPositionY(player.getPositionY());
        for (int i = 0; i < direction.length; i++) {
            switch (direction[i]) {
                case 'N':
                    if (!((player.getPositionY() - 1) < 0)) {
                        player.setPositionY(player.getPositionY() - 1);
                    }

                    break;
                case 'S':
                    if (!((player.getPositionY() + 1) > grid.getHeight() - 1)) {
                        player.setPositionY(player.getPositionY() + 1);
                    }
                    break;
                case 'E':
                    if (!((player.getPositionX() + 1) > grid.getWidth() - 1)) {
                        player.setPositionX(player.getPositionX() + 1);
                    }
                    break;
                case 'W':
                    if (!((player.getPositionX() - 1) < 0)) {
                        player.setPositionX(player.getPositionX() - 1);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void endGame(){
        printEmptyLines(10);
        System.out.println("YOU LOSE");
        game = false;
    }
    
    public void initTraps(Trap[] t, Grid g){
        for(int i = 0; i < t.length; i++){
            int[] trapCoord = getRandomCoordinate(g);
            
            t[i] = new Trap(Trap.characterGlobal, trapCoord[0], trapCoord[1]);
            g.setCharAt(t[i].getPositionX(), t[i].getPositionY(), t[i].getCharacter());
        }
        
        
        
        
    }
       public void initTreasure(Treasure[] t, Trap traps[], Grid g){
        for(int i = 0; i < t.length; i++){
            int[] treasureCoord = getRandomCoordinate(g);
            
            for(int b =0; b < traps.length; b++){
                int[] trapCoord = new int[2];
                trapCoord[0] = traps[b].getPositionX();
                trapCoord[1] = traps[b].getPositionY();
                if(treasureCoord.equals(trapCoord)){
                    i++;
                }
            }
            
            t[i] = new Treasure(Treasure.characterGlobal, treasureCoord[0], treasureCoord[1]);
            g.setCharAt(t[i].getPositionX(), t[i].getPositionY(), t[i].getCharacter());
        }
        
        
        
        
    }
    public void update(Grid g, Player p, Trap[] t) {
        
        
        
        for(int i = 0; i< t.length; i++){
            if(t[i].isAlive() == false){
                g.setCharAt(t[i].getPositionX(), t[i].getPositionY(), g.getFiller());
            }
            if((t[i].getPositionX() == p.getPositionX()) && (t[i].getPositionY() == p.getPositionY())){
                if(p.equals(player) && t[i].isAlive()){
                    p.setHealth(p.getHealth() - 1);
                    t[i].setAlive(false);
                                    }
                else{
                    p.setAlive(false);
                }
                
            }
        }
        
        if(p.getHealth() == 0){
            endGame();
            return;
        }
        
        g.setCharAt(p.getPositionX(), p.getPositionY(), p.getCharacter());
        if (!((p.getLastPositionX() == p.getPositionX()) && (p.getLastPositionY() == p.getPositionY()))) {
            g.setCharAt(p.getLastPositionX(), p.getLastPositionY(), g.getFiller());
        }

    }

    public  void runGame() {
        initTraps(traps, grid);
        update(grid, player, traps);
        while (game) {
            
            grid.print();
            printStats(player);
            command = getCommand();
            printEmptyLines(10);
            movePlayer(player, command);
            update(grid, player, traps);
        }
    }
}
