package org.design.pattern.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName: javaconcurrent
 * fileName: Subject.java
 * packageName: org.design.pattern.chapter4
 * date: 2020年01月26日  15:33:01
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class Subject
{
    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState()
    {
        return this.state;
    }


    public void setState(int state)
    {
        if(state == this.state)
            return;
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }
}
