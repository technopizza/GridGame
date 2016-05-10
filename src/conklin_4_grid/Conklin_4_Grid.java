/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conklin_4_grid;

import java.util.ArrayList;
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
    Treasure[] treasures = new Treasure[4];

    ArrayList coordinates = new ArrayList();

    Grid grid = new Grid(17, 17, '□');
    Player player = new Player('♜', 6, 6, 5);

    public static void printEmptyLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("");
        }
    }

    public static void printStats(Player p) {
        System.out.println("Health: " + p.getHealth() + "    Score: " + p.getScore());
    }

    public int[] getRandomCoordinate(Grid g) {
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

    void determineEnemyDirection(Grid g, Player player, Player enemy) {
        String dir = "";
        if(player.getPositionX() > enemy.getPositionX()){
            dir+="E";
        }
        else{
            dir+="W";
        }
        if(player.getPositionY() > enemy.getPositionY()){
            dir+="S";
        }
        else{
            dir+="N";
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

    public void endGame(boolean win) {
        printEmptyLines(10);
        if(win){
            System.out.println("\\ \\ / / _ \\| | | | \\ \\      / /_ _| \\ | |\n" +
" \\ V / | | | | | |  \\ \\ /\\ / / | ||  \\| |\n" +
"  | || |_| | |_| |   \\ V  V /  | || |\\  |\n" +
"  |_| \\___/ \\___/     \\_/\\_/  |___|_| \\_|");
        }
        else{
            
        
        System.out.print("\\ \\ / / _ \\| | | | | |   / _ \\/ ___|| ____|\n" +
" \\ V / | | | | | | | |  | | | \\___ \\|  _|  \n" +
"  | || |_| | |_| | | |__| |_| |___) | |___ \n" +
"  |_| \\___/ \\___/  |_____\\___/|____/|_____|");}
        game = false;
    }

    public void initTraps(Trap[] t, Grid g) {
        for (int i = 0; i < t.length; i++) {
            int[] trapCoord = getRandomCoordinate(g);
            coordinates.add(trapCoord);
            t[i] = new Trap(Trap.characterGlobal, trapCoord[0], trapCoord[1]);
            g.setCharAt(t[i].getPositionX(), t[i].getPositionY(), t[i].getCharacter());
        }

    }

    public void initTreasure(Treasure[] t, Trap traps[], Grid g) {
        for (int i = 0; i < t.length; i++) {
            int[] treasureCoord = getRandomCoordinate(g);

            for (int b = 0; b < coordinates.size(); b++) {
                int[] trapCoord = new int[2];
                if (treasureCoord.equals(coordinates.get(b))) {
                    i--;
                }
            }
            coordinates.add(treasureCoord);
            t[i] = new Treasure(Treasure.characterGlobal, treasureCoord[0], treasureCoord[1]);
            g.setCharAt(t[i].getPositionX(), t[i].getPositionY(), t[i].getCharacter());
        }

    }

    public void update(Grid g, Player p, Trap[] t, Treasure[] tx) {

        for (int i = 0; i < tx.length; i++) {
            if (tx[i].isAlive() == false) {
                g.setCharAt(tx[i].getPositionX(), tx[i].getPositionY(), g.getFiller());
            }
            if ((tx[i].getPositionX() == p.getPositionX()) && (tx[i].getPositionY() == p.getPositionY())) {
                if (p.equals(player) && tx[i].isAlive()) {
                    p.setScore(p.getScore() + 1);
                    tx[i].setAlive(false);
                }

            }
        }

        for (int i = 0; i < t.length; i++) {
            if (t[i].isAlive() == false) {
                g.setCharAt(t[i].getPositionX(), t[i].getPositionY(), g.getFiller());
            }
            if ((t[i].getPositionX() == p.getPositionX()) && (t[i].getPositionY() == p.getPositionY())) {
                if (p.equals(player) && t[i].isAlive()) {
                    p.setHealth(p.getHealth() - 1);
                    t[i].setAlive(false);
                } else {
                    p.setAlive(false);
                }

            }
        }

        if (p.getHealth() == 0) {
            endGame(false);
            return;
        }
        if (p.getScore() == 4) {
            endGame(true);
            return;
        }

        g.setCharAt(p.getPositionX(), p.getPositionY(), p.getCharacter());
        if (!((p.getLastPositionX() == p.getPositionX()) && (p.getLastPositionY() == p.getPositionY()))) {
            g.setCharAt(p.getLastPositionX(), p.getLastPositionY(), g.getFiller());
        }

    }

    public void runGame() {
        initTraps(traps, grid);
        initTreasure(treasures, traps, grid);
        update(grid, player, traps, treasures);
        while (game) {

            grid.print();
            printStats(player);
            command = getCommand();
            printEmptyLines(10);
            movePlayer(player, command);
            update(grid, player, traps, treasures);
        }
    }
}
