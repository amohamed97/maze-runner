package mazerunner.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;

public class HealthBomb extends Effector {
    public HealthBomb(int row, int col){
        super(row, col, "bomb.png");
        soundFile= Paths.get("sound","bomb.wav").toString();
        mediaPlayer = new MediaPlayer(new Media(new File(soundFile).toURI().toString()));
    }

    public void effect(Player p){
        p.decreaseHealth(20);
        mediaPlayer.play();
    }
}
