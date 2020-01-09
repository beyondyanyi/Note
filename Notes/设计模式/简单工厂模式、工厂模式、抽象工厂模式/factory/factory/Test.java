package com.demo.demo.factory.factory;

import com.demo.demo.factory.entity.man.Human;
import com.demo.demo.factory.factory.factory.BlackFactory;
import com.demo.demo.factory.factory.factory.Factory;
import com.demo.demo.factory.factory.factory.WhiteFactory;
import com.demo.demo.factory.factory.factory.YellowFactory;

/**
 * @description:
 *  * 工厂方法模式是对简单工厂模式进一步的解耦，因为在工厂方法模式中是一个子类对应一个工厂类，
 *  * 而这些工厂类都实现于一个抽象接口。这相当于是把原本会因为业务代码而庞大的简单工厂类，
 *  * 拆分成了一个个的工厂类，这样代码就不会都耦合在同一个类里了
 * @author: 毅哥
 * @time: 2020/1/8 17:19
 */
public class Test {
    public static void main(String[] args) {
        Factory factory=new BlackFactory();
        Human human=factory.getHuman();
        human.drink();
        factory=new WhiteFactory();
        human=factory.getHuman();
        human.drink();
        factory=new YellowFactory();
        human=factory.getHuman();
        human.drink();
    }
}
