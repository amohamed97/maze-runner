package mazerunner.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import mazerunner.model.Cell;
import mazerunner.model.Player;
import mazerunner.model.TreeWall;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Engine {
    public Pane root;
    Cell[][] walls = new Cell[30][30];
    Player player;

    public static Engine engine = new Engine();

    public static Engine getInstance(){
        return engine;
    }


    private Engine() {
        player = new Player(5, 7);
        for(Cell[] row : walls)
            Arrays.fill(row, null);
        walls[10][10] = new TreeWall(10, 10);
        walls[10][11] = new TreeWall(10, 11);
        walls[11][10] = new TreeWall(11, 10);
        root = new Pane();
        root.getChildren().add(player);
        for(Cell[] row : walls)
            root.getChildren().addAll(Arrays.stream(row).filter(s -> s != null).collect(Collectors.toList()));
        try {
            root.setBackground(new Background(
                    new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "grey.png").toString())),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT)));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private boolean noWallExists(int row, int col){
        return walls[row][col] == null;
    }

    public void moveRow(final int rowDiff){
        final int playerRow = player.getRow() + rowDiff;
        final int playerCol = player.getCol();
        if(playerRow >= 0 && playerRow < 30)
            if (noWallExists(playerRow, playerCol))
                player.setRow(playerRow);
    }

    public void moveCol(final int colDiff){
        final int playerRow = player.getRow();
        final int playerCol = player.getCol() + colDiff;
        if(playerCol >= 0 && playerCol < 30)
            if (noWallExists(playerRow, playerCol))
                player.setCol(playerCol);
    }
}
