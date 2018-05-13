package mazerunner.model;

import mazerunner.controller.Engine;

public class Checkpoint extends Effector {
    public Checkpoint(int row, int col) {
        super(row, col, "flag.gif");
    }

    @Override
    public void effect(Player p) {
        Engine.getInstance().saveToMemento();
        p.changeScore(50);
    }
}
