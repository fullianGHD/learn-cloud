package com.ghd.observer_pattern.service;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/16 18:16
 *
 * 主题接口，所有主题必须实现此接口
 */
public interface SubjectService {


    /**
     * 注册一个观察者
     * @param observer
     */
    void registerObjectServer(Observer observer);

    /**
     * 移除一个观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
}