package mazerunner.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mazerunner.controller.Engine;

import java.io.FileNotFoundException;

public class Window extends Application {

    public void start(Stage stage) throws FileNotFoundException {
        Scene scene = new Scene(Engine.getInstance().root, 600, 600);
        scene.setOnKeyPressed( e -> {
            switch (e.getCode()) {
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
