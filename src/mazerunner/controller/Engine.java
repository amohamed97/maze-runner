package mazerunner.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import mazerunner.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Engine {
    public Pane root;
    Cell[][] walls = new Cell[30][30];
    Effector[][] effectors = new Effector[30][30];

    public Player getPlayer() {
        return player;
    }

    Player player;
    CellFactory cellFactory = new CellFactory();

    public static Engine engine = new Engine();

    public static Engine getInstance(){
        return engine;
    }


    private Engine() {
        player = new Player(5, 7);
        for(Cell[] row : walls)
            Arrays.fill(row, null);

//        for(Effector[] row : effectors)
//            Arrays.fill(row, null);



        effectors[13][10] = (AmmoGift) cellFactory.getCell("AmmoGift",13,10);
        effectors[12][10] = (HealthGift) cellFactory.getCell("HealthGift",12,10);
        walls[10][10] = cellFactory.getCell("TreeWall",10, 10);
        walls[9][10] = cellFactory.getCell("TreeWall",9, 10);
        walls[11][10] = cellFactory.getCell("TreeWall",11,10);
        walls[7][10] = cellFactory.getCell("StoneWall",7,10);
        walls[6][10] = cellFactory.getCell("StoneWall",6,10);
        walls[5][10] = cellFactory.getCell("StoneWall",5,10);
        walls[4][10] = cellFactory.getCell("StoneWall",4,10);
        walls[3][10] = cellFactory.getCell("StoneWall",3,10);
        walls[2][10] = cellFactory.getCell("StoneWall",2,10);
        walls[1][10] = cellFactory.getCell("StoneWall",1,10);
        walls[1][10] = cellFactory.getCell("StoneWall",1,10);
        effectors[0][10] = (HealthBomb) cellFactory.getCell("HealthBomb",0,10);


        root = new Pane();
        root.getChildren().add(player);
        for(Cell[] row : walls)
            root.getChildren().addAll(Arrays.stream(row).filter(s -> s != null).collect(Collectors.toList()));

        for(Cell[] row : effectors)
            root.getChildren().addAll(Arrays.stream(row).filter(s -> s != null).collect(Collectors.toList()));

        try {
            root.setBackground(new Background(
                    new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "floor3.png").toString())),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT)));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void restart(){

        player.setRow(5);
        player.setCol(7);
        player.setAmmo(6);
        player.setHealth(100);
        player.setScore(0);


        effectors[13][10] = (AmmoGift) cellFactory.getCell("AmmoGift",13,10);
        effectors[12][10] = (HealthGift) cellFactory.getCell("HealthGift",12,10);
        walls[10][10] = cellFactory.getCell("TreeWall",10, 10);
        walls[9][10] = cellFactory.getCell("TreeWall",9, 10);
        walls[11][10] = cellFactory.getCell("TreeWall",11,10);
        walls[7][10] = cellFactory.getCell("StoneWall",7,10);
        walls[6][10] = cellFactory.getCell("StoneWall",6,10);
        walls[5][10] = cellFactory.getCell("StoneWall",5,10);
        walls[4][10] = cellFactory.getCell("StoneWall",4,10);
        walls[3][10] = cellFactory.getCell("StoneWall",3,10);
        walls[2][10] = cellFactory.getCell("StoneWall",2,10);
        walls[1][10] = cellFactory.getCell("StoneWall",1,10);
        walls[1][10] = cellFactory.getCell("StoneWall",1,10);
        effectors[0][10] = (HealthBomb) cellFactory.getCell("HealthBomb",0,10);


        root = new Pane();
        root.getChildren().add(player);
        for(Cell[] row : walls)
            root.getChildren().addAll(Arrays.stream(row).filter(s -> s != null).collect(Collectors.toList()));

        for(Cell[] row : effectors)
            root.getChildren().addAll(Arrays.stream(row).filter(s -> s != null).collect(Collectors.toList()));

        try {
            root.setBackground(new Background(
                    new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "floor3.png").toString())),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT)));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }



    private boolean noWallExists(int row, int col){
        return walls[row][col] == null;
    }
    private boolean effectorExists(int row, int col){
        return effectors[row][col] != null;
    }

    public void moveRow(final int rowDiff){
        final int playerRow = player.getRow() + rowDiff;
        final int playerCol = player.getCol();
        if(playerRow >= 0 && playerRow < 30)
            if (noWallExists(playerRow, playerCol)) {
                if(effectorExists(playerRow,playerCol)) {
                    effectors[playerRow][playerCol].effect(player);
                    effectors[playerRow][playerCol].setImage(null);
                    effectors[playerRow][playerCol] = null;
                }
                player.setRow(playerRow);


            }
        System.out.println(player.getHealth());
    }

    public void moveCol(final int colDiff){
        final int playerRow = player.getRow();
        final int playerCol = player.getCol() + colDiff;
        if(playerCol >= 0 && playerCol < 30)
            if (noWallExists(playerRow, playerCol)) {
                if(effectorExists(playerRow,playerCol)) {
                    effectors[playerRow][playerCol].effect(player);
                    effectors[playerRow][playerCol].setImage(null);
                    effectors[playerRow][playerCol] = null;
                }
                player.setCol(playerCol);
            }
        System.out.println(player.getHealth());
    }
}
