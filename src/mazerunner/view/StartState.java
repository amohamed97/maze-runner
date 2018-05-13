package mazerunner.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class StartState extends WindowState {
    public StartState(Window win) throws FileNotFoundException {
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

        scene = new Scene(menuLayout, 600, 600);
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
                            try {
                                win.setState(new PlayState(win));
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            break;

                        case 2:
                            win.stage.close();
                            break;
                    }
            }
        });
    }
}
