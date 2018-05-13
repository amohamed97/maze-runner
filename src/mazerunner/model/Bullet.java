package mazerunner.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;
import mazerunner.controller.Engine;

public class Bullet extends Cell {
    Direction rotation;

    public Bullet(int row, int col, Direction rotation){
        super(row, col, "clash2.png");
        this.rotation = rotation;
        switch(rotation){
            case RIGHT:
                setRotate(90);
                break;
            case DOWN:
                setRotate(180);
                break;
            case LEFT:
                setRotate(270);
                break;
            default:
                ;
        }
        setPreserveRatio(false) ;
    }

    private void playAnimation(DoubleProperty prop, int val){
        final Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50), new KeyValue(prop, val)));
        timeline.play();
    }

    public void animateRow(int row){
        this.row = row;
        playAnimation(yProperty(), row* Engine.getCellSize());
    }

    public void animateCol(int col){
        this.col = col;
        playAnimation(xProperty(), col*Engine.getCellSize());
    }
}