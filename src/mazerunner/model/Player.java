package mazerunner.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class Player extends Cell {
    int health = 100;
    int ammo = 0;
    int score = 0;

    public Player(int row, int col) {
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

    void playAnimation(DoubleProperty prop, int val){
        final Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(prop, val)));
        timeline.play();
    }

    public void setRow(int row) {
        this.row = row;
        playAnimation(yProperty(), row*20);
    }

    public void setCol(int col){
        this.col = col;
        playAnimation(xProperty(), col*20);
    }
}
