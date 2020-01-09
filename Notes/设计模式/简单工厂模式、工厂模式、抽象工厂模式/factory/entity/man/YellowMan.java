package com.demo.demo.factory.entity.man;

/**
 * @description: 黄人实例
 * @author: 毅哥
 * @time: 2020/1/8 16:14
 */
public class YellowMan implements Human {

    @Override
    public void eat() {
        System.out.println("黄人吃饭！");
    }

    @Override
    public void drink() {
        System.out.println("黄人喝水！");
    }

    @Override
    public void shit() {
        System.out.println("黄人拉！");
    }

    @Override
    public void pee() {
        System.out.println("黄人撒！");
    }
}
