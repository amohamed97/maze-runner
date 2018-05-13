package mazerunner.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import mazerunner.model.*;

import java.io.*;
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

    Cell[][] walls = new Cell[HEIGHT][WIDTH];
    Effector[][] effectors = new Effector[HEIGHT][WIDTH];
    Random rand = new Random();

    Player player;

    CellFactory cellFactory = new CellFactory();
    public static Engine engine = new Engine();

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

    public Player getPlayer() {
        return player;
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

    private void generate(int x, int y) {
        walls[x][y] = null;

        // while there is an unvisited neighbor
        while (walls[x][y+1] == null || walls[x+1][y] == null  || walls[x][y-1] == null || walls[x-1][y] == null) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                int r = rand.nextInt(4);
                if (r == 0 && walls[x][y+1] == null) {
                    walls[x][y] = cellFactory.getCell("stoneWall",x,y);
                    walls[x][y+1] = cellFactory.getCell("stoneWall",x,y+1);
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && walls[x+1][y] == null) {
                    walls[x][y] = cellFactory.getCell("stoneWall",x,y);
                    walls[x+1][y] = cellFactory.getCell("stoneWall",x+1,y);
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && walls[x][y-1] == null) {
                    walls[x][y] = cellFactory.getCell("stoneWall",x,y);;
                    walls[x][y-1] = cellFactory.getCell("stoneWall",x,y-1);
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && walls[x-1][y] == null) {
                    walls[x][y] = cellFactory.getCell("stoneWall",x,y);
                    walls[x-1][y] = cellFactory.getCell("stoneWall",x-1,y);
                    generate(x-1, y);
                    break;
                }
            }
        }
    }
}
