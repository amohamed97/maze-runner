package mazerunner.model;

abstract public class Effector extends Cell{
    public Effector(int row, int col, String fileName){
        super(row, col, fileName);
    }

    abstract public void effect(Player p);
}
