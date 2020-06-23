package com.ghd.observer_pattern.service.impl;


import com.ghd.observer_pattern.service.Observer;
import com.ghd.observer_pattern.service.SubjectService;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 11:02
 */
public class Observer1 implements Observer {

    private SubjectService subjectService;

    public Observer1 (SubjectService subjectService){
        this.subjectService = subjectService;
        subjectService.registerObjectServer(this);
    }



    @Override
    public void update(String msg) {
        System.out.println("Observer1 得到的号码为："+msg );
    }
}
