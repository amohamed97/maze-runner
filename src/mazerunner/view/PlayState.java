package mazerunner.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import mazerunner.controller.Engine;
import mazerunner.model.Observer;
import mazerunner.model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class PlayState extends WindowState implements Observer {
    Label health;
    Label ammo;
    Label score;
    Window win;

    public PlayState(Window win) throws FileNotFoundException {
        this.win = win;
        Engine.getInstance().getPlayer().attach(this);
        String filename = new String();
        filename = Paths.get("sound", "jazz.mp3").toString();
//        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(filename).toURI().toString()));
//        mediaPlayer.play();
        BorderPane border = new BorderPane();
        border.setCenter(Engine.getInstance().root);
        HBox status = new HBox(167);
        health = new Label("Health : 100");
        ammo = new Label("Ammo : 100");
        score = new Label("Score : 100");
        Button minMenu = new Button();
        minMenu.setShape(new Circle(1.5));
        ImageView im = new ImageView(new Image(new FileInputStream(Paths.get("images", "pause.png").toString())));
        im.setFitWidth(10);
        im.setFitHeight(10);
        status.getChildren().addAll(health, ammo, score);
//        status.setBackground(new Background(
//                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "grey.png").toString())),
//                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
//                        BackgroundSize.DEFAULT)));
        border.setBottom(status);
        scene = new Scene(border, (Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize())+25);
        scene.setOnKeyPressed(e2 -> {
            switch (e2.getCode()) {
                case UP:
                case W:
                    Engine.getInstance().moveRow(-1);
                    break;
                case DOWN:
                case S:
                    Engine.getInstance().moveRow(1);
                    break;
                case LEFT:
                case A:
                    Engine.getInstance().moveCol(-1);
                    break;
                case RIGHT:
                case D:
                    Engine.getInstance().moveCol(1);
                    break;


                case ESCAPE:
                    try {
                        win.setState(new PauseState(win));
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    break;
                case SPACE:
                    Engine.getInstance().shoot();
                    break;

            }
        });
        update(Engine.getInstance().getPlayer());
    }

    public void update(Player p) throws FileNotFoundException {
        if(p.getHealth() == 0){
            if(Engine.getInstance().getCheckpoint() == null) {
                win.setState(new LoseState(win));
            }else{
                Engine.getInstance().backToCheckpoint();
            }

        }
        health.setText("Health :" + p.getHealth());
        ammo.setText("Ammo :" + p.getAmmo());
        score.setText("Score :" + p.getScore());
    }
}
