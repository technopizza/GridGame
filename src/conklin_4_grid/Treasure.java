/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conklin_4_grid;

/**
 *
 * @author jconklin2391
 */
public class Treasure {
    public static char characterGlobal = '⚐';
    
    private char character;
    private int positionX;
    private int positionY;
    private boolean alive;

    public Treasure(char character, int positionX, int positionY) {
        this.character = character;
        this.positionX = positionX;
        this.positionY = positionY;
        this.alive = true;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
}
