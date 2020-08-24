package com.best.yige.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:java以及springboot实现多线程的几种方式
 * @Date: 9:40 2020/8/24
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //第一种 直接继承Thread
        Job job=new Job();
        job.start();
        System.out.println("打印测试");
        Mission mission=new Mission();
        mission.start();
        System.out.println("打印测试");
        //第二种 实现runnable接口
        Eat eat=new Eat();
        Thread thread=new Thread(eat);
        thread.start();
        System.out.println("打印测试");
        //第三种 实现callable接口
            //创建服务
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        Drink drinkOne=new Drink();
        Drink drinkTwo=new Drink();
        Drink drinkThree=new Drink();
            //提交执行
        Future<Object> futureOne=executorService.submit(drinkOne);
        Future<Object> futureTwo=executorService.submit(drinkTwo);
        Future<Object> futureThree=executorService.submit(drinkThree);
            //获取结果
        Object one=futureOne.get();
        Object two=futureTwo.get();
        Object three=futureThree.get();
        System.out.println(one+"###"+two+"###"+three);
            //关闭服务
        executorService.shutdownNow();

        //第四种 springboot @Async注解
            //分为无返回值和有返回值
                //无返回值
        Work work=new Work();
        work.working();
                //有返回值
        Play play=new Play();
        Future<Object> future=play.play();
        Object result=future.get();
        System.out.println("异步执行返回值:"+result);

    }
}
