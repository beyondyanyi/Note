package com.demo.demo.factory.abstractFactory.factory;

import com.demo.demo.factory.entity.felidae.Felidae;
import com.demo.demo.factory.entity.felidae.Tiger;
import com.demo.demo.factory.entity.man.Human;
import com.demo.demo.factory.entity.man.WhiteMan;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/9 9:04
 */
public class WhiteLifeFactory implements AbstractFactory {
    @Override
    public Human createHuman() {
        return new WhiteMan();
    }

    @Override
    public Felidae createFelidae() {
        return new Tiger();
    }
}
