package mazerunner.view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import mazerunner.controller.Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class VictoryState extends WindowState {
    public VictoryState(Window w) throws FileNotFoundException {
        VBox victory = new VBox();
        victory.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "back2.jpg").toString())),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
        victory.setAlignment(Pos.CENTER);
        victory.setSpacing(50);
        Label[] labels = new Label[4];
        AtomicInteger selected = new AtomicInteger();
        selected.set(2);


        labels[0] = new Label("You Win!");
        labels[0].setStyle("-fx-text-fill: green;" +
                " -fx-font-size: 100;");
        labels[1] = new Label("Score : "+Engine.getInstance().getPlayer().getScore());
        labels[1].setStyle("-fx-text-fill: green;" +
                " -fx-font-size: 30;");



        labels[2] = new Label("Main Menu");
        labels[2].setStyle("-fx-text-fill: red;" +
                " -fx-font-size: 30;");
        labels[3] = new Label("Exit");
        labels[3].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");

        victory.getChildren().addAll(labels);

        scene = new Scene(victory,(Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize()));
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    if (selected.get() < 3) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndIncrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case UP:
                    if (selected.get() > 2) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndDecrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case ENTER:
                    switch (selected.get()) {
                        case 2:
                            try {
                                w.setState(new StartState(w));
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            break;

                        case 3:
                            w.stage.close();
                            break;
                    }
            }
        });
    }
}
