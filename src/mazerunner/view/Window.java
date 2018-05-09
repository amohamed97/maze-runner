package mazerunner.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mazerunner.model.*;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Window extends Application {
    final int width = 60;
    final int height = 30;
    Cell[] cellsArr = new Cell[width*height];

    Image image;
    Image stoneWall;
    Image treeWall;
    Image brownWall;
    Image cyan;
    Image gray;
    Image green;
    Image player;
    Image gift;
    Image bomb;
    int playerIndex=0;
    Stage s;
    Group root;
    Scene scene;
    int j=0;
    Cell pl = new Player();

    public void makeArray() throws FileNotFoundException {
        stoneWall = new Image(new FileInputStream("images\\stone20x20.png"));
        treeWall = new Image(new FileInputStream("images\\tree.png"));
        brownWall = new Image(new FileInputStream("images\\brown.png"));
        gray = new Image(new FileInputStream("images\\grey.png"));
         player = new Image(new FileInputStream("D:\\git\\maze-runner\\images\\player.png"));
         gift = new Image(new FileInputStream("D:\\git\\maze-runner\\images\\cyanpath.png"));
         bomb = new Image(new FileInputStream("D:\\git\\maze-runner\\images\\purplepath.png"));
         ImageView im = new ImageView(player);
         pl.setImageView(im);

        Random random = new Random();
        boolean bo1 = random.nextBoolean();
        boolean bo2 = random.nextBoolean();
        boolean bo3 = random.nextBoolean();
//        boolean added=false;
        for(int j=0;j<height;j++) {
            for(int i=0;i<width;i++) {
                bo1 = random.nextBoolean();
                bo2 = random.nextBoolean();
                bo3 = random.nextBoolean();

                int x = ijton(i, j);

                if(bo1==true && bo2 == true && bo3 == true) {
                    cellsArr[x] = new TreeWall();
                    cellsArr[x].setImageView(new ImageView(treeWall));
                }else if (bo1==false&& bo2 == true ) {
                    cellsArr[x] = new StoneWall();
                    cellsArr[x].setImageView(new ImageView(brownWall));
                } else{
                    cellsArr[x] = new EmptyCell();
                    cellsArr[x].setImageView(new ImageView(gray));
//                    if(added==false) {
//                        if(true) {
//                            cellsArr[x] = new Player();
//                            cellsArr[x].setImageView(new ImageView(player));
//                            added=true;
//                            playerIndex=x;
//                        }
//                    }
                }

                //Setting the position of the image
                cellsArr[x].getImageView().setX(i*20);
                cellsArr[x].getImageView().setY(j*20);


                cellsArr[x].getImageView().setFitHeight(20);
                cellsArr[x].getImageView().setFitWidth(20);
                cellsArr[x].getImageView().setPreserveRatio(true);

            }
        }
    }
    public void draw() {
        root = new Group();
        for(int i=0;i<width*height;i++) {
            root.getChildren().add(cellsArr[i].getImageView());
        }
//        root.getChildren().add(cellsArr[playerIndex].getImageView());
        root.getChildren().add(pl.getImageView());
        scene = new Scene(root, 1200, 600);
        s.setTitle("Multiple views of an image");
        s.setScene(scene);
//        s.show();

    }
    public void initializeGame(){

        root = new Group();
        for(int i=0;i<width*height;i++) {
            root.getChildren().add(cellsArr[i].getImageView());
        }
//        root.res
        scene = new Scene(root);
        s.setTitle("Multiple views of an image");
        s.setScene(scene);
        s.show();
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException, InterruptedException {
        this.s=stage;
        stage.setTitle("Multiple views of an image");
        makeArray();
        initializeGame();
        System.out.println("Drawing");
        draw();
        gdeedFa45();
        gdeedFa45tanya();
    }


    int ijton(int i,int j){
        return(j)*60+(i);
    }

    void ntoij(int num, int i1,int j1){
        j1=num%900;
        i1=num/900;
        j1+=1;
        i1+=1;
    }
    public void update(int i) {
        swapCells(playerIndex,i);
        playerIndex=i;
        System.out.println("swapped");
    }
    public void swapCells(int n1,int n2) {
        ImageView temp = new ImageView(cellsArr[n1].getImageView().getImage()) ;
        cellsArr[n1].setImage(cellsArr[n2].getImageView().getImage());
        cellsArr[n2].setImage(temp.getImage());
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void gdeedFa45(){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
//                            update(++j);
                            draw();
                        }
                    });
                    i++;
                    Thread.sleep(1000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
    public void gdeedFa45tanya(){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            scene.setOnKeyPressed(KeyEvent->{
                                switch(KeyEvent.getCode()){
                                    case UP:
                                        System.out.println("UP");
                                        moveUP();
                                        break;
                                    case DOWN:
                                        System.out.println("DOWN");
                                        moveDown();
                                        break;
                                    case LEFT:
                                        System.out.println("LEFT");
                                        moveLeft();
                                        break;
                                    case RIGHT:
                                        System.out.println("RIGHT");
                                        moveRight();
                                        break;
                                }
                            });
                        }
                    });
                    i++;
                    Thread.sleep(1000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    void moveRight(){
        if(collisionDetection("Right")) {
            ImageView im = pl.getImageView();
//            for(int i=1;i<20;i+=3) {
                im.setX(im.getX() + 20);
                pl.setImageView(im);
//            }
            playerIndex++;
        }
    }
    void moveLeft(){

        if(collisionDetection("Left")) {
            ImageView im = pl.getImageView();
//            for ( int i = 0; i < 1000 ; i++) {
//            }
            im.setX(im.getX() - 20);
            pl.setImageView(im);
            playerIndex--;
        }
    }
    void moveDown(){
        if(collisionDetection("Down")) {
            ImageView im = pl.getImageView();
            im.setY(im.getY() + 20);
            pl.setImageView(im);
            playerIndex += 60;
        }
    }
    void moveUP(){
        if(collisionDetection("Up")) {
            ImageView im = pl.getImageView();
            im.setY(im.getY() - 20);
            pl.setImageView(im);
            playerIndex -= 60;
        }
    }
    boolean collisionDetection(String dir){
        switch(dir){
            case "Right":
                if(cellsArr[playerIndex+1] instanceof StoneWall ||cellsArr[playerIndex+1] instanceof TreeWall)
                    return false;
                break;
            case "Left":
                if(cellsArr[playerIndex-1] instanceof StoneWall ||cellsArr[playerIndex-1] instanceof TreeWall)
                    return false;
                break;
            case "Down":
                if(cellsArr[playerIndex+60] instanceof StoneWall ||cellsArr[playerIndex+60] instanceof TreeWall)
                    return false;
                break;

            case "Up":
                if(cellsArr[playerIndex-60] instanceof StoneWall ||cellsArr[playerIndex-60] instanceof TreeWall)
                    return false;
                break;
        }
        return true;
    }

}
