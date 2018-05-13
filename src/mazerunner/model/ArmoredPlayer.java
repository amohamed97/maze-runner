package mazerunner.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mazerunner.controller.Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ArmoredPlayer extends Player {
    public Player player;

    public ArmoredPlayer(Player player){
        this.player = player;
        try {
            player.setPlayerStandImage("playerGunArmor.png");
            player.setPlayerMoveImage("movementArmor.gif");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void decreaseHealth(int change){
        Engine.getInstance().dearmorize();
    }

    public Player getPlayer(){
        return player;
    }

    void fillHealth(){
        player.fillHealth();
    }

    void changeScore(int change){
        player.changeScore(change);
    }

    public void setRow(int row){
        player.setRow(row);
    }

    public void setCol(int col){
        player.setCol(col);
    }

    public int getRow(){
        return player.getRow();
    }

    public int getCol(){
        return player.getCol();
    }

    public int getHealth(){
        return player.getHealth();
    }

    public int getAmmo() {
        return player.getAmmo();
    }

    public int getScore() {
        return player.getScore();
    }

    public void shoot(){
        player.shoot();
    }

    public void increaseAmmo(){
        player.increaseAmmo();
    }

    public void attach(Observer o){
        player.attach(o);
    }

    public void notifyAllObservers() {
        player.notifyAllObservers();
    }


    public void setHealth(int health) {
        player.setHealth(health);
    }

    public void setAmmo(int ammo) {
        player.setAmmo(ammo);
    }

    public void setScore(int score) {
        player.setScore(score);
    }

    public Direction getDirection(){
        return player.getDirection();
    }

    public void setDirection(Direction direction){
        player.setDirection(direction);
    }
}
