package com.demo.demo.singleton;

/**
 * @description:  饱汉式（懒汉模式）  非线程安全
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 * 缺点：非线程安全。
 * @author: 毅哥
 * @time: 2020/1/8 15:00
 */
public class Single1 {
    private static Single1 singleton = null;
    private Single1() {

    }
    public static Single1 getInstance() {
        if (singleton == null) {
            singleton = new Single1();
        }
        return singleton;
    }
}
