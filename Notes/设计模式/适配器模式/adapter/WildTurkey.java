package com.example.designpatterns.adapter;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:野火鸡
 * @Date: 13:42 2020/8/3
 * @Modified By:
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble!!!");
    }
}
