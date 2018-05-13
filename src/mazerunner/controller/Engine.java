package mazerunner.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import mazerunner.model.*;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Engine {
    public Pane root;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 30;

    Cell[][] walls = new Cell[HEIGHT][WIDTH];
    Effector[][] effectors = new Effector[HEIGHT][WIDTH];

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

        for(Effector[] row : effectors)
            Arrays.fill(row, null);



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
        effectors[2][20] = (ArmorGift) cellFactory.getCell("ArmorGift",2,20);

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

    public void newGame(){
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


        root.getChildren().clear();
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
        System.out.println(playerRow < HEIGHT);
        if(playerRow >= 0 && playerRow < HEIGHT)
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
        System.out.println(playerCol < WIDTH);
        if(playerCol >= 0 && playerCol < WIDTH)
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

    void bulletMoveRow(Bullet bullet, int rowDiff){
        final int bulletRow = bullet.getRow() + rowDiff;
        final int bulletCol = bullet.getCol();
        System.out.println(bulletRow < HEIGHT);
        if(bulletRow >= 0 && bulletRow < HEIGHT) {
            Cell wall = walls[bulletRow][bulletCol];
            if (wall == null) {
                bullet.animateRow(bulletRow);
                return;
            } else if (wall instanceof TreeWall ){
                root.getChildren().remove(wall);
                walls[bulletRow][bulletCol] = null;
            }
        }
        root.getChildren().remove(bullet);
    }

    void bulletMoveColumn(Bullet bullet, int colDiff){
        final int bulletRow = bullet.getRow();
        final int bulletCol = bullet.getCol() + colDiff;
        System.out.println(bulletRow < WIDTH);
        if(bulletCol >= 0 && bulletCol < WIDTH) {
            Cell wall = walls[bulletRow][bulletCol];
            if (wall == null) {
                bullet.animateCol(bulletCol);
                return;
            } else if (wall instanceof TreeWall ){
                root.getChildren().remove(wall);
                walls[bulletRow][bulletCol] = null;
            }
        }
        root.getChildren().remove(bullet);

    }

    public void shoot(){
        if(player.getAmmo() > 0) {
            player.setAmmo(player.getAmmo() - 1);
            Bullet bullet = new Bullet(player.getRow(), player.getCol(), player.getDirection());
            root.getChildren().add(0, bullet);
            KeyFrame kf;
            Duration dur = Duration.millis(100);
            switch (player.getDirection()) {
                case UP:
                    kf = new KeyFrame(dur, e -> bulletMoveRow(bullet, -1));
                    break;
                case DOWN:
                    kf = new KeyFrame(dur, e -> bulletMoveRow(bullet, 1));
                    break;
                case RIGHT:
                    kf = new KeyFrame(dur, e -> bulletMoveColumn(bullet, 1));
                    break;
                default:
                    kf = new KeyFrame(dur, e -> bulletMoveColumn(bullet, -1));
            }
            Timeline timeline = new Timeline(kf);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setOnFinished(e -> {
                if (!root.getChildren().contains(bullet))
                    timeline.stop();
            });
            timeline.play();
        }
    }

    public static int getWidth(){
        return WIDTH;
    }

    public static int getHeight(){
        return HEIGHT;
    }

    public static int getCellSize(){
        return CELL_SIZE;
    }
}
