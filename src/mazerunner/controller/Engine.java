package mazerunner.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import mazerunner.model.*;
import mazerunner.model.memento.Memento;
import mazerunner.view.VictoryState;
import mazerunner.view.Window;

import java.io.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {

    public Pane root;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 30;

    MediaPlayer mediaPlayer;
    String filename = new String();

    Cell[][] walls = new Cell[HEIGHT][WIDTH];
    Effector[][] effectors = new Effector[HEIGHT][WIDTH];


    Memento checkpoint;

    public Player getPlayer() {
        return player;
    }

    Player player;

    CellFactory cellFactory = new CellFactory();
    public static Engine engine = new Engine();
    public Window win;
    private Engine() {

        player = new Player(5, 7);
        for(Cell[] row : walls)
            Arrays.fill(row, null);

        for(Effector[] row : effectors)
            Arrays.fill(row, null);

        filename= Paths.get("sound","shoot.wav").toString();


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
        effectors[19][29] = (MazeWin) cellFactory.getCell("MazeWin",19,29);
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
        effectors[19][29] = (MazeWin) cellFactory.getCell("MazeWin",19,29);


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

    public static Engine getInstance(){
        return engine;
    }


    public void restart(){

        player.setRow(5);
        player.setCol(7);
        player.setAmmo(6);
        player.setHealth(100);
        player.setScore(0);


        effectors[14][10] = (Checkpoint) cellFactory.getCell("Checkpoint",14,10);
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
        effectors[19][29] = (MazeWin) cellFactory.getCell("MazeWin",19,29);



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

    public void armorize(){
        player = new ArmoredPlayer(player);
    }

    public void dearmorize(){
        player = ((ArmoredPlayer) player).getPlayer();
        try {
            player.setPlayerStandImage("playerGun.png");
            player.setPlayerMoveImage("movement.gif");
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
        player.setDirection(rowDiff > 0? Direction.DOWN : Direction.UP);
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
        player.setDirection(colDiff > 0? Direction.RIGHT : Direction.LEFT);
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

    public void saveToMemento(){
        Integer[] state = new Integer[]{player.getHealth(),player.getAmmo(),player.getRow(),player.getCol()};
        checkpoint = new Memento(state);
    }

    public Integer[] loadFromMomento(){
        return checkpoint.getState();
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
            mediaPlayer=new MediaPlayer(new Media(new File(filename).toURI().toString()));
            mediaPlayer.play();
            player.setAmmo(player.getAmmo() - 1);
            Bullet bullet = new Bullet(player.getRow(), player.getCol(), player.getDirection());
            root.getChildren().add(0, bullet);
            KeyFrame kf;
            Duration dur = Duration.millis(50);
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
            timeline.setOnFinished(e -> {
                if (root.getChildren().contains(bullet))
                    timeline.play();
                else
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

    public static int getCellSize() {
        return CELL_SIZE;
    }
    public void save(){
        try {
            File file = new File("save.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(getPlayer().getHealth()+" "+getPlayer().getAmmo()+" "+getPlayer().getScore());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        try (FileReader file = new FileReader("save.txt")) {
            Scanner scanner = new Scanner(file);
            getPlayer().setHealth(Integer.parseInt(scanner.next()));
            getPlayer().setAmmo(Integer.parseInt(scanner.next()));
            getPlayer().setScore(Integer.parseInt(scanner.next()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWindow(Window win){
        this.win = win;
    }

    public void win() throws FileNotFoundException {
        win.setState(new VictoryState(win));
    }

}
