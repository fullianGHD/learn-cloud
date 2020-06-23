package com.ghd.observer_pattern.service.impl;

import java.util.Observable;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 12:22
 *
 * java.util 内置观察者 (3D服务号主题)
 */
public class SubjectFor3DJavaUtil extends Observable {

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
