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
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Engine {

    public Pane root;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 30;

    MediaPlayer mediaPlayer;
    String filename = new String();

    Cell[][] walls = new Cell[HEIGHT][WIDTH];
    Effector[][] effectors = new Effector[HEIGHT][WIDTH];
    int i,j;


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


//        effectors[13][10] = (AmmoGift) cellFactory.getCell("AmmoGift",13,10);
//        effectors[12][10] = (HealthGift) cellFactory.getCell("HealthGift",12,10);
//        walls[10][10] = cellFactory.getCell("TreeWall",10, 10);
//        walls[9][10] = cellFactory.getCell("TreeWall",9, 10);
//        walls[11][10] = cellFactory.getCell("TreeWall",11,10);
//        walls[7][10] = cellFactory.getCell("StoneWall",7,10);
//        walls[6][10] = cellFactory.getCell("StoneWall",6,10);
//        walls[5][10] = cellFactory.getCell("StoneWall",5,10);
//        walls[4][10] = cellFactory.getCell("StoneWall",4,10);
//        walls[3][10] = cellFactory.getCell("StoneWall",3,10);
//        walls[2][10] = cellFactory.getCell("StoneWall",2,10);
//        walls[1][10] = cellFactory.getCell("StoneWall",1,10);
//        walls[1][10] = cellFactory.getCell("StoneWall",1,10);
//        effectors[0][10] = (HealthBomb) cellFactory.getCell("HealthBomb",0,10);
//        effectors[2][20] = (ArmorGift) cellFactory.getCell("ArmorGift",2,20);
//        effectors[19][29] = (MazeWin) cellFactory.getCell("MazeWin",19,29);
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
//        generate(0,0);
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
        effectors[0][11] = (HealthBomb) cellFactory.getCell("HealthBomb",0,11);
        effectors[0][12] = (HealthBomb) cellFactory.getCell("HealthBomb",0,12);
        effectors[0][13] = (HealthBomb) cellFactory.getCell("HealthBomb",0,13);
        effectors[0][14] = (HealthBomb) cellFactory.getCell("HealthBomb",0,14);
        effectors[0][15] = (HealthBomb) cellFactory.getCell("HealthBomb",0,15);
        effectors[0][16] = (HealthBomb) cellFactory.getCell("HealthBomb",0,16);
        effectors[0][17] = (HealthBomb) cellFactory.getCell("HealthBomb",0,17);
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
        if(playerRow >= 0 && playerRow < HEIGHT)
            if (noWallExists(playerRow, playerCol)) {
                player.setRow(playerRow);
                if(effectorExists(playerRow,playerCol)) {
                    effectors[playerRow][playerCol].effect(player);
                    effectors[playerRow][playerCol].setImage(null);
                    effectors[playerRow][playerCol] = null;
                }


            }
    }

    public void moveCol(final int colDiff){
        player.setDirection(colDiff > 0? Direction.RIGHT : Direction.LEFT);
        final int playerRow = player.getRow();
        final int playerCol = player.getCol() + colDiff;
        if(playerCol >= 0 && playerCol < WIDTH)
            if (noWallExists(playerRow, playerCol)) {
                player.setCol(playerCol);
                if(effectorExists(playerRow,playerCol)) {
                    effectors[playerRow][playerCol].effect(player);
                    effectors[playerRow][playerCol].setImage(null);
                    effectors[playerRow][playerCol] = null;
                }
            }
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
        int x;
        JSONObject rootObj = new JSONObject();
        JSONObject obj;
        JSONArray wallsArr = new JSONArray();
        JSONArray playerArr = new JSONArray();
        JSONArray effectorsArr = new JSONArray();
        JSONArray mementoArr = new JSONArray();
        try(FileWriter file = new FileWriter("save.json")){
            for(int i =0; i< HEIGHT; i++){
                for(int j=0; j<WIDTH; j++){
                    if(walls[i][j] == null){continue;}
                    else {
                        x = ijTOn(i, j);
                        obj = new JSONObject();
                        obj.put("place", x);
                        if (walls[i][j] instanceof StoneWall) {
                            obj.put("type", "StoneWall");
                        } else if (walls[i][j] instanceof TreeWall) {
                            obj.put("type", "TreeWall");
                        }
                        wallsArr.add(obj);
                    }
                }
            }
            rootObj.put("walls",wallsArr);
            for(int i =0; i< HEIGHT; i++){
                for(int j=0; j<WIDTH; j++){
                    if(effectors[i][j] == null){continue;}
                    else {
                        x = ijTOn(i, j);
                        obj = new JSONObject();
                        obj.put("place", x);
                        if (effectors[i][j] instanceof HealthGift) {
                            obj.put("type", "HealthGift");
                        } else if (effectors[i][j] instanceof HealthBomb) {
                            obj.put("type", "HealthBomb");
                        } else if (effectors[i][j] instanceof AmmoGift) {
                            obj.put("type", "AmmoGift");
                        } else if (effectors[i][j] instanceof TimeBomb) {
                            obj.put("type", "TimeBomb");
                        } else if (effectors[i][j] instanceof ArmorGift) {
                            obj.put("type", "ArmorGift");
                        } else if (effectors[i][j] instanceof Checkpoint) {
                            obj.put("type", "CheckPoint");
                        } else if (effectors[i][j] instanceof MazeWin) {
                            obj.put("type", "MazeWin");
                        }
                        effectorsArr.add(obj);
                    }
                }
            }
            rootObj.put("effectors",effectorsArr);
            obj = new JSONObject();
            obj.put("health",getPlayer().getHealth());
            obj.put("ammo",getPlayer().getAmmo());
            obj.put("score",getPlayer().getScore());
            obj.put("row",getPlayer().getRow());
            obj.put("col",getPlayer().getCol());
            playerArr.add(obj);
            rootObj.put("player",playerArr);
            obj = new JSONObject();
            if(Engine.getInstance().getCheckpoint() != null) {
                Integer[] memento =Engine.getInstance().getCheckpoint().getState();
                obj.put("health", memento[0]);
                obj.put("ammo", memento[1]);
                obj.put("row", memento[2]);
                obj.put("col", memento[3]);
            }
            mementoArr.add(obj);
            rootObj.put("memento",mementoArr);
            file.write(rootObj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        for(Cell[] row : walls)
            Arrays.fill(row, null);

        for(Effector[] row : effectors)
            Arrays.fill(row, null);
        JSONParser parser = new JSONParser();
        int n;
        try {
            Object object = parser.parse(new FileReader("save.json"));
            JSONObject objfile = (JSONObject) object;
            JSONArray wallsArr = (JSONArray) objfile.get("walls");
            JSONArray effectorsArr = (JSONArray) objfile.get("effectors");
            JSONArray playerArr = (JSONArray) objfile.get("player");
            JSONArray mementoArr = (JSONArray) objfile.get("memento");
            Iterator<JSONObject> iterator1 = wallsArr.iterator();
            Iterator<JSONObject> iterator2 = effectorsArr.iterator();
            Iterator<JSONObject> iterator3 = playerArr.iterator();
            Iterator<JSONObject> iterator4 = mementoArr.iterator();


            while (iterator1.hasNext()) {
                JSONObject obj = iterator1.next();
                n = Integer.parseInt(obj.get("place").toString());
                nTOij(n);
                walls[i][j] = cellFactory.getCell(obj.get("type").toString(),i,j);
            }
            while (iterator2.hasNext()) {
                JSONObject obj = iterator2.next();
                n = Integer.parseInt(obj.get("place").toString());
                nTOij(n);
                effectors[i][j] = (Effector) cellFactory.getCell(obj.get("type").toString(),i,j);
            }
            while (iterator3.hasNext()) {
                JSONObject obj = iterator3.next();
                getPlayer().setRow(Integer.parseInt(obj.get("row").toString()));
                getPlayer().setCol(Integer.parseInt(obj.get("col").toString()));
                getPlayer().setHealth(Integer.parseInt(obj.get("health").toString()));
                getPlayer().setAmmo(Integer.parseInt(obj.get("ammo").toString()));
                getPlayer().setScore(Integer.parseInt(obj.get("score").toString()));
            }
            if(iterator4.hasNext()) {
                JSONObject obj = iterator4.next();
                Integer[] memento = new Integer[]{Integer.parseInt(obj.get("health").toString()), Integer.parseInt(obj.get("ammo").toString()),
                        Integer.parseInt(obj.get("row").toString()), Integer.parseInt(obj.get("col").toString())};
                Engine.getInstance().setCheckpoint(new Memento(memento));
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace();}
        catch (IOException e) { e.printStackTrace();}
        catch (ParseException e) { e.printStackTrace();}
        catch (Exception e) { e.printStackTrace();}


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
    public void setWindow(Window win){
        this.win = win;
    }

    public void win() throws FileNotFoundException {
        win.setState(new VictoryState(win));
    }

    public int ijTOn(int i, int j){
        return j+(i*WIDTH);
    }
    public void nTOij(int n){
        i = n/WIDTH;
        j = n%WIDTH;
    }


    public Memento getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Memento checkpoint) {
        this.checkpoint = checkpoint;
    }

    public void backToCheckpoint(){
        Integer[] temp = checkpoint.getState();
        player.setHealth(temp[0]);
        player.setAmmo(temp[1]);
        System.out.println(temp[2]+""+temp[3]);
        player.setRow(temp[2]);
        player.setCol(temp[3]);
    }
}
