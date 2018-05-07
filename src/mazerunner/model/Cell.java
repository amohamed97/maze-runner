package mazerunner.model;

import java.awt.*;

public abstract class Cell {
    int row,col;
    public abstract void draw(Graphics canvas);

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
