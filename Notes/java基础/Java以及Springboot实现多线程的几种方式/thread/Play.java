package com.best.yige.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:springboot有返回异步
 * @Date: 10:06 2020/8/24
 * @Modified By:
 */
@Component
public class Play {


    @Async
    public Future<Object> play(){
        //处理一些业务
        //通过AsyncResult返回值
        return new AsyncResult(System.currentTimeMillis());
    }

}
