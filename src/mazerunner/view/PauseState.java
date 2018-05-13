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

        scene = new Scene(pauseMenu,(Engine.getWidth()*Engine.getCellSize()),(Engine.getHeight()*Engine.getCellSize()));
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    if (selected.get() < 2) {
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
//                            try {
//                                Thread.sleep(150);
//                            } catch (InterruptedException e1) {
//                                e1.printStackTrace();
//                            }
                            resume(win);
                            break;

                        case 2:
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
