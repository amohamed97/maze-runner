package mazerunner.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Window extends Application {
    ImageView[] imgArr = new ImageView[1800];
    Image image;
    Image stoneWall;
    Image treeWall;
    Image brownWall;
    Image cyan;
    Image gray;
    Image green;
    Image player;
    int playerIndex=0;
    Stage s;
    Group root;
    Scene scene;
    int j=0;

    public void makeArray() throws FileNotFoundException {
        stoneWall = new Image(new FileInputStream("images\\stone20x20.png"));
        treeWall = new Image(new FileInputStream("images\\tree.png"));
        brownWall = new Image(new FileInputStream("images\\brown.png"));
        gray = new Image(new FileInputStream("images\\grey.png"));
        player = new Image(new FileInputStream("images\\player.png"));


        Random random = new Random();
        boolean bo1 = random.nextBoolean();
        boolean bo2 = random.nextBoolean();
        boolean bo3 = random.nextBoolean();
        boolean added=false;
        for(int j=0;j<30;j++) {
            for(int i=0;i<60;i++) {
                bo1 = random.nextBoolean();
                bo2 = random.nextBoolean();
                bo3 = random.nextBoolean();

                int x = ijton(i, j);

                if(bo1==true && bo2 == true && bo3 == true) {
                    imgArr[x]=new ImageView(treeWall);
                }else if (bo1==false&& bo2 == true ) {
                    imgArr[x]=new ImageView(brownWall);
                } else{
                    imgArr[x]=new ImageView(gray);
                    if(added==false) {
                        if(true) {
                            imgArr[x]=new ImageView(player);
                            added=true;
                            playerIndex=x;
                        }
                    }
                }

                //Setting the position of the image
                imgArr[x].setX(i*20);
                imgArr[x].setY(j*20);


                imgArr[x].setFitHeight(20);
                imgArr[x].setFitWidth(20);
                imgArr[x].setPreserveRatio(true);

            }
        }

    }
    public void draw() {
        root = new Group(imgArr);
        scene = new Scene(root, 1200, 600);
        s.setTitle("Multiple views of an image");
        s.setScene(scene);
        // s.show();
    }
    public void initializeGame(){
        root = new Group(imgArr);
        scene = new Scene(root, 1200, 600);
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
        draw();
        gdeedFa45();
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
        swapCells(playerIndex, i);
        playerIndex=i;
        System.out.println("swapped");
    }
    public void swapCells(int n1,int n2) {
        ImageView temp = new ImageView(imgArr[n1].getImage()) ;
        imgArr[n1].setImage(imgArr[n2].getImage());
        imgArr[n2].setImage(temp.getImage());
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
                            update(++j);
                            draw();
                        }
                    });
                    i++;
                    Thread.sleep(10);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

}
