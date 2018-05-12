package mazerunner.model;

public class Player extends Cell {
    static Player player = new Player(0, 0);

    public static Player getInstance(){
        return player;
    }

    int health = 100;
    int ammo = 0;
    int score = 0;

    private Player(int row, int col) {
        super(row, col, "player.png");
    }

    void fillHealth(){
        health = 100;
    }

    void decreaseHealth(int change){
        health -= change;
    }

    void changeScore(int change){
        score += change;
    }
}
