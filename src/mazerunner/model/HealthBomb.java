package mazerunner.model;

import java.awt.*;

public class HealthBomb extends Effector {
    public HealthBomb(int row, int col){
        super(row, col, "bomb.png");
    }

    public void effect(Player p){
        p.decreaseHealth(20);
    }
}
