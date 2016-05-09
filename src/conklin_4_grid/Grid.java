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
public class Grid {

    private int width;
    private int height;
    private char filler;
    private char[][] gridMap;

    Grid(int w, int h, char f) {
        width = w;
        height = h;
        filler = f;
        gridMap = new char[w][h];

        for (int x = 0; x < gridMap.length; x++) {
            for (int y = 0; y < gridMap[x].length; y++) {
                gridMap[x][y] = filler;
            }
        }

    }

    public void print() {

        for (int x = 0; x < gridMap.length; x++) {
            System.out.print("\n");
            for (int y = 0; y < gridMap[x].length; y++) {
                System.out.print(gridMap[x][y] + " ");
            }
        }
System.out.print("\n");
    }

    public void setCharAt(int x, int y, char c){
        gridMap[y][x] = c;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char getFiller() {
        return filler;
    }

    public void setFiller(char filler) {
        this.filler = filler;
    }

    public char[][] getGridMap() {
        return gridMap;
    }

    public void setGridMap(char[][] gridMap) {
        this.gridMap = gridMap;
    }

}
