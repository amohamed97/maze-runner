package mazerunner.model;

import java.io.FileNotFoundException;

public class Player extends Cell {
    public Player(int row, int col) throws FileNotFoundException {
        super(row, col, "player.png");
    }
}
