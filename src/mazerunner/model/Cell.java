package mazerunner.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Cell extends ImageView {
    int row,col;

    public Cell(int row, int col, String fileName) {
        super();
        setRow(row);
        setCol(col);
        FileInputStream stream;
        try{
            stream = new FileInputStream(Paths.get("images", fileName).toFile());
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        setImage(new Image(stream));
        super.setFitHeight(20);
        super.setFitWidth(20);
        super.setPreserveRatio(true);

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        setY(row*20);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
        setX(col*20);
    }
}
