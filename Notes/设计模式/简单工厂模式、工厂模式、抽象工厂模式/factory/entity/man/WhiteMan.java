package com.demo.demo.factory.entity.man;

/**
 * @description: 白人实例
 * @author: 毅哥
 * @time: 2020/1/8 16:13
 */
public class WhiteMan implements Human {

    @Override
    public void eat() {
        System.out.println("白人吃肉！");
    }

    @Override
    public void drink() {
        System.out.println("白人喝水！");
    }

    @Override
    public void shit() {
        System.out.println("白人拉！");
    }

    @Override
    public void pee() {
        System.out.println("白人撒！");
    }
}
