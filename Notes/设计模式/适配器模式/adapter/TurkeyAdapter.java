package com.example.designpatterns.adapter;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:鸭子适配器，让火鸡冒充鸭子
 * @Date: 13:43 2020/8/3
 * @Modified By:
 */
public class TurkeyAdapter implements Duck{

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}
