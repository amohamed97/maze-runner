package mazerunner.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mazerunner.controller.Engine;
import mazerunner.model.Cell;
import mazerunner.model.Player;
import mazerunner.model.TreeWall;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Window extends Application {
    Engine engine;

    public void start(Stage stage) throws FileNotFoundException {
        engine = new Engine();
        Scene scene = new Scene(engine.root, 600, 600);
        scene.setOnKeyPressed( e -> {
            switch (e.getCode()) {
                case UP:
                case W:
                    engine.moveRow(-1);
                    break;
                case DOWN:
                case S:
                    engine.moveRow(1);
                    break;
                case LEFT:
                case A:
                    engine.moveCol(-1);
                    break;
                case RIGHT:
                case D:
                    engine.moveCol(1);
            }
        });

        stage.setTitle("Maze Runner");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
