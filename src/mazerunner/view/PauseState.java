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

public class PauseState extends WindowState {
    private void resume(Window win){
        try {
            win.setState(new PlayState(win));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public PauseState(Window win) throws FileNotFoundException{
        VBox pauseMenu = new VBox();
        pauseMenu.setBackground(new Background(
                new BackgroundImage(new Image(new FileInputStream(Paths.get("images", "back2.jpg").toString())),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setSpacing(50);
        Label[] labels = new Label[4];
        AtomicInteger selected = new AtomicInteger();
        selected.set(0);

        labels[0] = new Label("Resume");
        labels[0].setStyle("-fx-text-fill: red;" +
                " -fx-font-size: 30;");
        labels[1] = new Label("Restart");
        labels[1].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");

        labels[2] = new Label("Save Game");
        labels[2].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");
        labels[3] = new Label("Main Menu");
        labels[3].setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 30;");

        pauseMenu.getChildren().addAll(labels);
        Label saved = new Label("Saved");
        saved.setStyle("-fx-text-fill: green;" +
                "-fx-font-size: 20;");
        pauseMenu.getChildren().add(saved);
        saved.setVisible(false);
        scene = new Scene(pauseMenu,(Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize()));
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
                    if (selected.get() > 0) {
                        labels[selected.get()].setStyle("-fx-text-fill: white;" +
                                "-fx-font-size: 30;");
                        selected.getAndDecrement();
                        labels[selected.get()].setStyle("-fx-text-fill: red;" +
                                "-fx-font-size: 30;");
                    }
                    break;

                case ENTER:
                    switch (selected.get()) {
                        case 0:
                            resume(win);
                            break;

                        case 1:
                            Engine.getInstance().restart();
                            resume(win);
                            break;

                        case 2:
                            Engine.getInstance().save();
                            saved.setVisible(true);
                            break;

                        case 3:
                            try {
                                win.setState(new StartState(win));
                            }catch (FileNotFoundException ex){
                                ex.printStackTrace();
                            }
                            break;
                    }
            }
        });
    }
}
