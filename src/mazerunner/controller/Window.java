package mazerunner.controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mazerunner.model.Cell;
import mazerunner.model.Player;
import mazerunner.model.TreeWall;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Window extends Application {
    Cell[][] walls = new Cell[30][30];
    Player player;

    public void start(Stage stage) throws FileNotFoundException {
        player = new Player(5, 7);
        for(Cell[] row : walls)
            Arrays.fill(row, null);
        walls[10][10] = new TreeWall(10, 10);
        walls[10][11] = new TreeWall(10, 11);
        walls[11][10] = new TreeWall(11, 10);
        Pane root = new Pane();
        root.getChildren().add(player);
        for(Cell[] row : walls)
            root.getChildren().addAll(Arrays.stream(row).filter(s -> s != null).collect(Collectors.toList()));
        root.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "grey.png").toString())),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));

        Scene scene = new Scene(root, 600, 600);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            private boolean noWallExists(int row, int col){
                return walls[row][col] == null;
            }

            private void moveRow(final int rowDiff){
                final int playerRow = player.getRow() + rowDiff;
                final int playerCol = player.getCol();
                if(playerRow >= 0 && playerRow < 30)
                    if (noWallExists(playerRow, playerCol))
                        player.setRow(playerRow);
            }

            private void moveCol(final int colDiff){
                final int playerRow = player.getRow();
                final int playerCol = player.getCol() + colDiff;
                if(playerCol >= 0 && playerCol < 30)
                    if (noWallExists(playerRow, playerCol))
                        player.setCol(playerCol);
            }

            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case UP:
                    case W:
                        moveRow(-1);
                        break;
                    case DOWN:
                    case S:
                        moveRow(1);
                        break;
                    case LEFT:
                    case A:
                        moveCol(-1);
                        break;
                    case RIGHT:
                    case D:
                        moveCol(1);
                }
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
