package com.ghd.observer_pattern.service;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/16 18:19
 *
 * 所有得观察者需要实现此接口
 */
public interface Observer {

    void update(String msg);

}