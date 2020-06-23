package com.ghd.singleton_pattern.service.impl;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 17:02
 *
 * 单例 饿汉式
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton(){};

    public static Singleton getInstance(){
        return instance;
    }

}
