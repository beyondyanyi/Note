package com.demo.demo.factory.abstractFactory.factory;

import com.demo.demo.factory.entity.felidae.Cat;
import com.demo.demo.factory.entity.felidae.Felidae;
import com.demo.demo.factory.entity.man.Human;
import com.demo.demo.factory.entity.man.YellowMan;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/9 9:06
 */
public class YellowLifeFactory implements AbstractFactory {
    @Override
    public Human createHuman() {
        return new YellowMan();
    }

    @Override
    public Felidae createFelidae() {
        return new Cat();
    }
}
