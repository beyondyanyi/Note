package com.wuwei.yanyi.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName SleepAspectj
 * @Description: TODO
 * @Author beyondyanyi@gmail.com
 * @Date 2021/5/17
 * @Version V1.0
 **/

@Aspect
@Component
public class SleepAspectj {

    //切点
    @Pointcut("execution(* com.wuwei.yanyi.spring.aop.People.eat())")
    public void pointOne(){
    }


    //之前执行
    @Before("pointOne()")
    public void play(){
        System.out.println("之前");
    }

    //之后执行
    @After("pointOne()")
    public void sleep(){
        System.out.println("之后");
    }


    //返回之前
    @AfterReturning("pointOne()")
    public void afterRe(){
        System.out.println("返回前");
    }

    //环绕
    @Around("pointOne()")
    public void wash(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("环绕前");
            joinPoint.proceed();
            System.out.println("环绕后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}
