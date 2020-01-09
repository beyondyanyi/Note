package com.demo.demo.factory.abstractFactory.factory;

import com.demo.demo.factory.entity.felidae.Felidae;
import com.demo.demo.factory.entity.man.Human;

/**
 * @description: 抽象工厂模式
 * 抽象工厂模式最大的好处是易于交换产品系列,让具体的创建实例过程与客户端分离
 * @author: 毅哥
 * @time: 2020/1/8 15:45
 */
public interface AbstractFactory {
    //创建人类
    Human createHuman();
    //创建猫科动物
    Felidae createFelidae();
}
