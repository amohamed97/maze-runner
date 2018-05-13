package mazerunner.model;

import java.io.FileNotFoundException;

public interface Observer {
    public void update(Player p) throws FileNotFoundException;
}
