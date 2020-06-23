package com.ghd.observer_pattern.service.impl;

import java.util.Observable;
import java.util.Observer;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 12:28
 */
public class ObserverForJava implements Observer {

    public void registerSubject(Observable observable){
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof SubjectFor3DJavaUtil){
            SubjectFor3DJavaUtil subjectFor3DJavaUtil = (SubjectFor3DJavaUtil) o;
            System.out.println("SubjectFor3DJavaUtil msg = "+ subjectFor3DJavaUtil.getMsg());
        }

        if(o instanceof SubjectForSSQ){
            SubjectForSSQ subjectForSSQ = (SubjectForSSQ) o;
            System.out.println("subjectForSSQ msg = "+ subjectForSSQ.getMsg());
        }
    }
}
