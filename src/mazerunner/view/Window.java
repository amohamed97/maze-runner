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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mazerunner.controller.Engine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Window extends Application {


    public void startGame(Stage stage) throws FileNotFoundException {
        BorderPane border = new BorderPane();
                    border.setCenter(Engine.getInstance().root);
                    HBox status = new HBox(112);
                    Label health = new Label("Health : 100");
                    Label ammo = new Label("Ammo : 100");
                    Label score = new Label("Score : 100");
                    Button minMenu = new Button();
                    minMenu.setShape(new Circle(1.5));
                    ImageView im = new ImageView(new Image(new FileInputStream(Paths.get("images", "pause.png").toString())));
                    im.setFitWidth(10);im.setFitHeight(10);
                    minMenu.setGraphic(im);
                    status.getChildren().addAll(health,ammo,score,minMenu);
//        status.setBackground(new Background(
//                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "grey.png").toString())),
//                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
//                        BackgroundSize.DEFAULT)));
                    border.setBottom(status);
        Scene scene = new Scene(border, 600, 625);
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
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "back2.jpg").toString())),
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
        labels[1] = new Label("Load Game");
        labels[1].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");
        labels[2] = new Label("Exit");
        labels[2].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");

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
                            try {
                                startGame(stage);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
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
