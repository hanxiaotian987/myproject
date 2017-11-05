package com.xiaohan.coding.designpattern.singleton;

/**
 * 饿汉式单例模式
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    //私有化构造子,阻止外部直接实例化对象
    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
