package com.example.designpatterns.adapter;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:适配器模式
 * @Date: 13:45 2020/8/3
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) {
        //野生火鸡，冒充鸭子叫
        Turkey turkey=new WildTurkey();
        Duck duck=new TurkeyAdapter(turkey);
        duck.quack();
    }
}
