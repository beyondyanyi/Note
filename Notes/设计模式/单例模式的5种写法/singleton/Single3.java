package com.demo.demo.singleton;

/**
 * @description: 饱汉式（懒汉模式） 双重加锁检查
 *  优点：懒加载，线程安全。
 *  注：实例必须有 volatile 关键字修饰，其保证初始化完全。
 * @author: 毅哥
 * @time: 2020/1/8 15:06
 */
public class Single3 {
    private volatile static Single3 instance = null;
    private Single3(){

    }

    public static Single3 getInstance(){
        if(instance == null){
            synchronized(Single3.class){
                if(instance == null){
                    instance = new Single3();
                }
            }
        }
        return instance;
    }
}
