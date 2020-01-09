package com.demo.demo.factory.abstractFactory;

import com.demo.demo.factory.abstractFactory.factory.AbstractFactory;
import com.demo.demo.factory.abstractFactory.factory.BlackLifeFactory;
import com.demo.demo.factory.abstractFactory.factory.WhiteLifeFactory;
import com.demo.demo.factory.abstractFactory.factory.YellowLifeFactory;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/9 9:37
 */
public class Test {
    public static void main(String[] args) {
        //白人的生活
        AbstractFactory factory=new WhiteLifeFactory();
        factory.createHuman().eat();
        System.out.println(factory.createFelidae().curious().toString());

        //黑人的生活
        factory=new BlackLifeFactory();
        factory.createHuman().eat();
        System.out.println(factory.createFelidae().curious().toString());

        //黄人的生活
        factory=new YellowLifeFactory();
        factory.createHuman().eat();
        System.out.println(factory.createFelidae().curious().toString());
    }
}
