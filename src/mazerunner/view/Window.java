package mazerunner.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mazerunner.controller.Engine;

import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mazerunner.controller.Engine;
import mazerunner.model.Observer;
import mazerunner.model.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Window extends Application implements Observer {
    Label health;
    Label ammo;
    Label score;
    Scene scene;
    Scene pause;
    Scene menu;
    BorderPane border;



    public void initPause(Stage stage) throws FileNotFoundException {
        VBox pauseMenu = new VBox();
        pauseMenu.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "back2.jpg").toString())),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setSpacing(50);
        Label[] labels = new Label[3];
        AtomicInteger selected = new AtomicInteger();
        selected.set(0);

        labels[0] = new Label("Resume");
        labels[0].setStyle("-fx-text-fill: red;" +
                " -fx-font-size: 30;");
        labels[1] = new Label("Restart");
        labels[1].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");
        labels[2] = new Label("Main Menu");
        labels[2].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");

        pauseMenu.getChildren().addAll(labels);

        pause = new Scene(pauseMenu,(Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize()));
        pause.setOnKeyPressed(e->{
            switch (e.getCode()){
                case DOWN:
                    if(selected.get() < 2) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndIncrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case UP:
                    if(selected.get() > 0) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndDecrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case ENTER:
                    switch (selected.get()){
                        case 0:
                            resume(stage);
                            break;

                        case 1:
                            Engine.getInstance().restart();
                            border.setCenter(Engine.getInstance().root);
                            stage.setScene(scene);
                            break;

                        case 2:
                            stage.setScene(menu);
                            break;
                    }
            }
        });
    }



    public void startGame(Stage stage) throws FileNotFoundException {
        String filename = new String();
        filename = Paths.get("sound","jazz.mp3").toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(filename).toURI().toString()));
        mediaPlayer.play();
                    initPause(stage);
                    border = new BorderPane();
                    border.setCenter(Engine.getInstance().root);
                    HBox status = new HBox(167);
                    health = new Label("Health : 100");
                    ammo = new Label("Ammo : 100");
                    score = new Label("Score : 100");
                    Button minMenu = new Button();
                    minMenu.setShape(new Circle(1.5));
                    ImageView im = new ImageView(new Image(new FileInputStream(Paths.get("images", "pause.png").toString())));
                    im.setFitWidth(10);im.setFitHeight(10);
                    status.getChildren().addAll(health,ammo,score);
                    border.setBottom(status);
        scene = new Scene(border, (Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize())+25);
        scene.setOnKeyPressed(e2 -> {
            switch (e2.getCode()) {
                case UP:
                case W:
                    Engine.getInstance().moveRow(-1);
                    break;
                case DOWN:
                case S:
                    Engine.getInstance().moveRow(1);
                    break;
                case LEFT:
                case A:
                    Engine.getInstance().moveCol(-1);
                    break;
                case RIGHT:
                case D:
                    Engine.getInstance().moveCol(1);
                    break;

                case ESCAPE:
                    pause(stage);
            }
        });
        stage.setScene(scene);
    }



    public void resume(Stage stage){
        stage.setScene(scene);
    }


    public void pause(Stage stage){
        stage.setScene(pause);
    }



    public void start(Stage stage) throws FileNotFoundException {
        Engine.getInstance().getPlayer().attach(this);
        stage.setTitle("Maze Runner");
        stage.setResizable(false);

        VBox menuLayout = new VBox();
        menuLayout.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "back2.jpg").toString())),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setSpacing(50);
        Label[] labels = new Label[3];
        AtomicInteger selected = new AtomicInteger();
        selected.set(0);

        labels[0] = new Label("Start Game");
        labels[0].setStyle("-fx-text-fill: red;" +
                " -fx-font-size: 30;");
        labels[1] = new Label("Load Game");
        labels[1].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");
        labels[2] = new Label("Exit");
        labels[2].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");

        menuLayout.getChildren().addAll(labels);

        menu = new Scene(menuLayout,(Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize()));
        menu.setOnKeyPressed(e->{
            switch (e.getCode()){
                case DOWN:
                    if(selected.get() < 2) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndIncrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case UP:
                    if(selected.get() > 0) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndDecrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case ENTER:
                    switch (selected.get()){
                        case 0:
                            try {
                                startGame(stage);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            Engine.getInstance().restart();
                            border.setCenter(Engine.getInstance().root);
                            stage.setScene(scene);
                            break;

                        case 2:
                            stage.close();
                            break;
                    }



            }
        });

        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void update(Player p){
        health.setText("Health :"+p.getHealth());
        ammo.setText("Ammo :"+p.getAmmo());
        score.setText("Score :"+p.getScore());
    }
}
