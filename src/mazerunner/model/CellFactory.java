package mazerunner.model;

public class CellFactory {

    public Cell getCell(String type,int row,int column){
        switch (type){
            case "StoneWall":
                return new StoneWall(row,column);

            case "TreeWall":
                return new TreeWall(row,column);

            case "HealthGift":
                return new HealthGift(row,column);

            case "AmmoGift":
                return new AmmoGift(row,column);

            case "HealthBomb":
                return new HealthBomb(row,column);

            case "ScoreBomb":
                return new TimeBomb(row,column);



        }

    return null;
    }
}