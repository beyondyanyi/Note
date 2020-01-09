package com.demo.demo.factory.simpleFactory;

import com.demo.demo.factory.entity.man.Human;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/8 16:32
 */
public class Test {
    public static void main(String[] args) {
        Human one=SimpleFactory.createHuman("黑人");
        Human two=SimpleFactory.createHuman("白人");
        Human three=SimpleFactory.createHuman("黄人");
        one.drink();
        two.drink();
        three.drink();
        one.eat();
        two.eat();
        three.eat();
    }
}
