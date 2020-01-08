package com.demo.demo.singleton;

/**
 * @description: 饿汉式
 *  优点：饿汉模式天生是线程安全的，使用时没有延迟。
 *  缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
 * @author: 毅哥
 * @time: 2019/12/30 8:18
 */
public class Single4 {
    public static Single4 singleton=new Single4();

    private Single4(){

    }

    public static Single4 getInstance(){
        return singleton;
    }
}
