package com.demo.demo.factory.entity.felidae;

/**
 * @description: 泰哥
 * @author: 毅哥
 * @time: 2020/1/9 9:19
 */
public class Tiger implements Felidae{


    @Override
    public Object curious() {
        return "泰哥很好奇！";
    }

    @Override
    public Object explosive() {
        return "泰哥爆发力贼强，万兽之王！";
    }

    @Override
    public Object endurance() {
        return "泰哥耐力很弱！";
    }
}
