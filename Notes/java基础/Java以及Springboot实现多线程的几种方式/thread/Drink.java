package com.best.yige.thread;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 9:52 2020/8/24
 * @Modified By:
 */
public class Drink implements Callable {
    @Override
    public Object call() throws Exception {
        //处理一些业务，返回一些值
        return new Random().nextInt();
    }
}
