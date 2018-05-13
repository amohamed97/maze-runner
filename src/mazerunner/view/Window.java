package mazerunner.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mazerunner.controller.Engine;

import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mazerunner.controller.Engine;
import mazerunner.model.Observer;
import mazerunner.model.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Window extends Application {
    Label health;
    Label ammo;
    Label score;
    Scene scene;
    Scene pause;
    Scene menu;
    BorderPane border;
    Stage stage;
    WindowState state;


    public void setState(WindowState state){
        this.state = state;
        stage.setScene(state.getScene());
    }

    public void start(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        stage.setTitle("Maze Runner");
        stage.setResizable(false);

        setState(new StartState(this));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
