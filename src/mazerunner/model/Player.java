package mazerunner.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Player extends Cell {
    int health = 100;
    int ammo = 6;
    int score = 0;
    private static Image playerStand;
    private static Image playerMove;

    static{
        try {
            playerStand = new Image(new FileInputStream(Paths.get("images", "playerGun.png").toFile()));
            playerMove = new Image(new FileInputStream(Paths.get("images", "movement.gif").toFile()));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public Player(int row, int col) {
        super(row, col, "playerGun.png");
    }

    protected Player() {
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
        setImage(playerMove);
        final Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(prop, val)));
        timeline.play();
        timeline.setOnFinished(e-> setImage(playerStand));
    }

    public void setRow(int row) {
        setRotate(this.row < row? 180 : 0);
        this.row = row;
        playAnimation(yProperty(), row*20);
    }

    public void setCol(int col){
        setRotate(this.col < col? 90 : 270);
        this.col = col;
        playAnimation(xProperty(), col*20);
    }

    public int getHealth(){
        return health;
    }

    public void shoot(){
        this.ammo--;
    }

    public void increaseAmmo(){
        this.ammo += 5;
    }
}
