package com.demo.demo.singleton;

/**
 * @description: Holder模式 优点：将懒加载和线程安全完美结合的一种方式（无锁）。（推荐）
 * @author: 毅哥
 * @time: 2020/1/8 15:17
 */
public class Single5 {
    private static class SingletonHolder{
        private static Single5 instance = new Single5();
    }

    private Single5(){
    }
    public static  Single5 getInstance(){
        return SingletonHolder.instance;
    }
}
