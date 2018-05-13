package mazerunner.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class HealthGift extends Effector {
    public HealthGift(int row, int col){
        super(row, col, "gift.png");
        soundFile= Paths.get("sound","Powerup22.wav").toString();
        mediaPlayer = new MediaPlayer(new Media(new File(soundFile).toURI().toString()));
    }

    public void effect(Player p){
        p.fillHealth();
        mediaPlayer.play();
    }
}
