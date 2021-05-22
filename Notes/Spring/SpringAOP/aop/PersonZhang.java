package com.wuwei.yanyi.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @ClassName PersonZhang
 * @Description: TODO
 * @Author beyondyanyi@gmail.com
 * @Date 2021/5/17
 * @Version V1.0
 **/
@Component
public class PersonZhang implements People  {
    @Override
    public String eat() {
        System.out.println("吃面！");
        return "面";
    }
}
