package com.wuwei.yanyi;

import com.wuwei.yanyi.spring.aop.People;
import com.wuwei.yanyi.spring.aop.PersonMing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * @ClassName YanyiApplication
 * @Description: TODO 测试
 * @Author beyondyanyi@gmail.com
 * @Date 2021/3/12
 * @Version V1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class YanyiApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(YanyiApplication.class);
        PersonMing personMing=run.getBean(PersonMing.class);
        personMing.eat();
    }
}
