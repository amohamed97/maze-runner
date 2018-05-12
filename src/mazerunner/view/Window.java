package mazerunner.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mazerunner.controller.Engine;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EventListener;
import java.util.concurrent.atomic.AtomicInteger;

public class Window extends Application {


    public void startGame(Stage stage){
        Scene scene = new Scene(Engine.getInstance().root, 600, 600);
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
            }
        });
        stage.setScene(scene);
    }

    public void start(Stage stage) throws FileNotFoundException {

        stage.setTitle("Maze Runner");
        stage.setResizable(false);

        VBox menuLayout = new VBox();
        menuLayout.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("..", "back2.jpg").toString())),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setSpacing(50);
        Label[] labels = new Label[3];
        AtomicInteger selected = new AtomicInteger();
        selected.set(0);

        labels[0] = new Label("Start Game");
        labels[0].setStyle("-fx-text-fill: red;" +
                " -fx-font-size: 30;");
        labels[0].setLayoutX(200);
        labels[0].setLayoutY(100);
        labels[1] = new Label("Load Game");
        labels[1].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");
        labels[1].setLayoutY(200);
        labels[1].setLayoutX(220);
        labels[2] = new Label("Exit");
        labels[2].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");
        labels[2].setLayoutY(300);
        labels[2].setLayoutX(250);

        menuLayout.getChildren().addAll(labels);

        Scene menu = new Scene(menuLayout,600,600);
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
                            startGame(stage);
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
}
