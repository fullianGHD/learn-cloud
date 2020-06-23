package com.ghd.observer_pattern.service.impl;


import com.ghd.observer_pattern.service.Observer;
import com.ghd.observer_pattern.service.SubjectService;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 11:04
 */
public class Observer2 implements Observer {

    private SubjectService subjectService;

    public Observer2(SubjectService subjectService){
        this.subjectService = subjectService;
        subjectService.registerObjectServer(this);
    }


    @Override
    public void update(String msg) {
        System.out.println("Observer2 得到的号码为："+msg);
    }
}
