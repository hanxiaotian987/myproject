package com.xiaohan.coding.designpattern.singleton;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 懒汉式单例模式
 * 线程安全的单例模式：
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 */
public class Singleton {

    private Singleton() {
        System.out.println("single");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getSingle() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for(int i=0; i<ths.length; i++) {
            ths[i] = new Thread(()->{
               Singleton.getSingle();
            });
        }
        Arrays.asList(ths).forEach(o->o.start());
    }
}
