package com.xiaohan.coding.designpattern.singleton;

/**
 * 懒汉式单例模式，实现方式（使用双重同步锁）
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    //私有化构造子,阻止外部直接实例化对象
    private LazySingleton() {
    }

    public  static LazySingleton getInstance() {
        if(instance == null) {
            synchronized (LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
