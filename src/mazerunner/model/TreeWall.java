package mazerunner.model;

import java.awt.*;
import java.io.FileNotFoundException;

public class TreeWall extends Cell {
    public TreeWall(int row, int col) throws FileNotFoundException {
        super(row, col, "tree.png");
    }
}
