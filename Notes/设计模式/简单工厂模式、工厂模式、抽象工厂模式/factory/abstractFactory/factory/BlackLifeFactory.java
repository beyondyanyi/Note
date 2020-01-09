package com.demo.demo.factory.abstractFactory.factory;

import com.demo.demo.factory.entity.felidae.Felidae;
import com.demo.demo.factory.entity.felidae.Leopard;
import com.demo.demo.factory.entity.man.BlackMan;
import com.demo.demo.factory.entity.man.Human;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/9 9:06
 */
public class BlackLifeFactory implements AbstractFactory {

    @Override
    public Human createHuman() {
        return new BlackMan();
    }

    @Override
    public Felidae createFelidae() {
        return new Leopard();
    }
}
