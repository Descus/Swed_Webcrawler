package swed4.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();
    public void Attach(Observer observer){
        observers.add(observer);
    }
    public void Detach(Observer observer){
        observers.remove(observer);
    }
    public void Notify(){
        for(Observer observer : observers){
            observer.update();
        }
    }
}
