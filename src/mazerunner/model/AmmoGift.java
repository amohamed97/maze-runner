package mazerunner.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class AmmoGift extends Effector {


    public AmmoGift(int row, int col){
        super(row, col, "gift.png");
        soundFile= Paths.get("sound","Powerup12.wav").toString();
        mediaPlayer = new MediaPlayer(new Media(new File(soundFile).toURI().toString()));
    }


    @Override
    public void effect(Player p) {
        p.increaseAmmo();
        mediaPlayer.play();

    }
}
