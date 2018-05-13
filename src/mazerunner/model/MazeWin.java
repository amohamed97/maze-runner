package mazerunner.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mazerunner.controller.Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class MazeWin extends Effector {


    public MazeWin(int row, int col){
        super(row, col, "trophy.png");
        soundFile= Paths.get("sound","win.wav").toString();
        System.out.println(soundFile);
        mediaPlayer = new MediaPlayer(new Media(new File(soundFile).toURI().toString()));
    }


    @Override
    public void effect(Player p) {
        try {
            Engine.getInstance().win();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mediaPlayer.play();

    }
}
