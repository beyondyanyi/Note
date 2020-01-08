package com.demo.demo.singleton;

/**
 * @description: 饱汉式（懒汉模式）  线程安全
 *  优点：同上，但加锁了。
 *  缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。
 * @author: 毅哥
 * @time: 2020/1/8 15:03
 */
public class Single2 {
    private static Single2 uniqueInstance = null;
    private Single2() {

    }
    public static synchronized Single2 getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Single2();
        }
        return uniqueInstance;
    }




}
