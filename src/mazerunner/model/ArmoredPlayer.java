package mazerunner.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class ArmoredPlayer extends Player {
    public Player player;

    private static Image playerStand;
    private static Image playerMove;

    static{
        try {
            playerStand = new Image(new FileInputStream(Paths.get("images", "playerGunArmor.png").toFile()));
            playerMove = new Image(new FileInputStream(Paths.get("images", "movementArmor.gif").toFile()));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public ArmoredPlayer(Player player){
        this.player = player;
        player.setImage(playerStand);
        super.setFitHeight(20);
        super.setFitWidth(20);
    }

    public void decreaseHealth(int change){}

    public void playAnimation(Property prop, int val){
        setImage(playerMove);
        final Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(prop, val)));
        timeline.play();
        timeline.setOnFinished(e-> setImage(playerStand));
    }
}
