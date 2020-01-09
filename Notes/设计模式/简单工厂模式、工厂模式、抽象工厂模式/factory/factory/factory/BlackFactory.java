package com.demo.demo.factory.factory.factory;


import com.demo.demo.factory.entity.man.BlackMan;
import com.demo.demo.factory.entity.man.Human;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/8 17:16
 */
public class BlackFactory implements Factory {
    @Override
    public Human getHuman() {
        return new BlackMan();
    }
}
