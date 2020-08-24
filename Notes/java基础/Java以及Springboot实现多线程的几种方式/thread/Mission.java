package com.best.yige.thread;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 9:45 2020/8/24
 * @Modified By:
 */
public class Mission extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i=0;i<20;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是一个任务的");
        }
    }
}
