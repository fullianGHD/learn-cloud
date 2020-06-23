package com.ghd.observer_pattern.service.impl;

import com.ghd.observer_pattern.service.Observer;
import com.ghd.observer_pattern.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/16 18:21
 *
 * 3D彩票接口，实现主题接口
 */
public class ObjectFor3D implements SubjectService {

    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * 3D彩票号码
     */
    private String msg;

    @Override
    public void registerObjectServer(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if(i >= 0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(msg);
        }
    }

    public void setMsg(String msg){
        this.msg = msg;
        notifyObservers();
    }
}