package com.demo.demo.factory.entity.felidae;

/**
 * @description: 猫
 * @author: 毅哥
 * @time: 2020/1/9 9:17
 */
public class Cat implements Felidae{
    @Override
    public Object curious() {
        return "好奇害死猫！";
    }

    @Override
    public Object explosive() {
        return "爆发力捉老鼠！";
    }

    @Override
    public Object endurance() {
        return "猫的耐力弱爆了！";
    }
}
