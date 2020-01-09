package com.demo.demo.factory.factory.factory;


import com.demo.demo.factory.entity.man.Human;
import com.demo.demo.factory.entity.man.YellowMan;

/**
 * @description:
 * @author: 毅哥
 * @time: 2020/1/8 17:16
 */
public class YellowFactory implements Factory {
    @Override
    public Human getHuman() {
        return new YellowMan();
    }
}
