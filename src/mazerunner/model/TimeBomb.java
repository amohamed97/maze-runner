package mazerunner.model;


public class TimeBomb extends Effector {
    public TimeBomb(int row, int col){
        super(row, col, "bomb.png");
    }

    public void effect(){
//        Player.getInstance().decreaseHealth(20);
    }
}
