package mazerunner.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public abstract class Cell {
    int row,col;
    ImageView imageView;


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    public void setImage(Image image) {
        this.imageView.setImage(image);
    }
    public abstract void draw(Graphics canvas);

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
