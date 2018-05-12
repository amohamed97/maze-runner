package mazerunner.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class ArmorGift extends Effector {
    public ArmorGift(int row, int col){
        super(row, col, "shield.png");
        soundFile= Paths.get("sound","powerup12.mp3").toString();
        System.out.println(soundFile);
        mediaPlayer = new MediaPlayer(new Media(new File(soundFile).toURI().toString()));
    }


    @Override
    public void effect(Player p) {
        p.increaseAmmo();
        mediaPlayer.play();

    }
}
