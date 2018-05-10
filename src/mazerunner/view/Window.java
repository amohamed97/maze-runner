package mazerunner.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mazerunner.model.Cell;
import mazerunner.model.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;


public class Window extends Application {
    ArrayList<Cell> cells = new ArrayList<Cell>();

    public void start(Stage stage) throws FileNotFoundException {
        cells.add(new Player(5,7));
        Pane root = new Pane();
        root.getChildren().addAll(cells);
        root.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "grey.png").toString())),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Maze Runner");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
