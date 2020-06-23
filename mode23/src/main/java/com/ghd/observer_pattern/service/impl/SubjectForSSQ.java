package com.ghd.observer_pattern.service.impl;

import java.util.Observable;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 12:25
 */
public class SubjectForSSQ extends Observable {

    private String msg;


    public String getMsg(){
        return msg;
    }


    public void setMsg(String msg){
        this.msg = msg;
        setChanged();
        notifyObservers();
    }

}
