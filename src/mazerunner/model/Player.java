package mazerunner.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mazerunner.controller.Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
public class Player extends Cell {
    int health = 100;
    int ammo = 6;
    int score = 0;
    private Image playerStand;
    private Image playerMove;

    public Direction getDirection() {
        return direction;
    }

    Direction direction;

    ArrayList<Observer> observers = new ArrayList<Observer>();

    static{
    }

    public Player(int row, int col) {
        super(row, col, "playerGun.png");
        try {
            setPlayerStandImage("playerGun.png");
            setPlayerMoveImage("movement.gif");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setPlayerStandImage(String filename) throws FileNotFoundException{
        playerStand = new Image(new FileInputStream(Paths.get("images", filename).toFile()));
    }

    public void setPlayerMoveImage(String filename) throws FileNotFoundException{
        playerMove = new Image(new FileInputStream(Paths.get("images", filename).toFile()));
    }

    protected Player() {
    }

    void fillHealth(){
        health = 100;
        notifyAllObservers();
    }

    void decreaseHealth(int change){
        health -= change;
        notifyAllObservers();
    }

    void changeScore(int change){
        score += change;
        notifyAllObservers();
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
        direction = this.row < row? Direction.DOWN : Direction.UP;
        setRotate(direction == Direction.DOWN? 180 : 0);
        this.row = row;
        playAnimation(yProperty(), row* Engine.getCellSize());
    }

    public void setCol(int col){
        direction = this.col < col? Direction.RIGHT : Direction.LEFT;
        setRotate(direction == Direction.RIGHT? 90 : 270);
        this.col = col;
        playAnimation(xProperty(), col*Engine.getCellSize());
    }

    public int getHealth(){
        return health;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getScore() {
        return score;
    }

    public void shoot(){
        this.ammo--;
        notifyAllObservers();
    }

    public void increaseAmmo(){
        this.ammo += 5;
        notifyAllObservers();
    }

    public void attach(Observer o){
        observers.add(o);
    }

    public void notifyAllObservers() {
        observers.forEach(o -> o.update(this));
    }


    public void setHealth(int health) {
        this.health = health;
        notifyAllObservers();
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
        notifyAllObservers();
    }

    public void setScore(int score) {
        this.score = score;
        notifyAllObservers();
    }
}
