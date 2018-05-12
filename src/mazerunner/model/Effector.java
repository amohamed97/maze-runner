package mazerunner.model;

import javafx.scene.media.MediaPlayer;

abstract public class Effector extends Cell{
    MediaPlayer mediaPlayer;
    String soundFile=new String();
    public Effector(int row, int col, String fileName){
        super(row, col, fileName);
    }

    abstract public void effect(Player p);
}
