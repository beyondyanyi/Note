package com.demo.demo.factory.entity.felidae;

/**
 * @description: 猎豹
 * @author: 毅哥
 * @time: 2020/1/9 9:23
 */
public class Leopard implements Felidae {
    @Override
    public Object curious() {
        return "猎豹很好奇！";
    }

    @Override
    public Object explosive() {
        return "猎豹是陆地上最快的动物！";
    }

    @Override
    public Object endurance() {
        return "猎豹耐力很弱！";
    }
}
