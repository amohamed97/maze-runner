package mazerunner.model.memento;

public class Memento {
    Integer[] state;

    public Memento(Integer[] state){
        this.state = state;
    }

    public Integer[] getState(){
        return state;
    }

}
