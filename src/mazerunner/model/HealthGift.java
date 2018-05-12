package mazerunner.model;

public class HealthGift extends Effector {
    public HealthGift(int row, int col){
        super(row, col, "gift.png");
    }

    public void effect(Player p){
        p.fillHealth();
    }
}
