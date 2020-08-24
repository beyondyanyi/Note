package com.best.yige.thread;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 9:47 2020/8/24
 * @Modified By:
 */
public class Eat implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是一个吃播");
        }
    }
}
