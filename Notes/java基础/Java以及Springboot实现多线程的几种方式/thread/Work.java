package com.best.yige.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:springboot无返回异步
 * @Date: 10:05 2020/8/24
 * @Modified By:
 */
@Component
public class Work {
    @Async
    public  void working(){
        System.out.println("我在工作"+System.currentTimeMillis());
    }
}
