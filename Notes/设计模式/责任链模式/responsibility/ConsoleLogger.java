package com.example.designpatterns.responsibility;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:普通记录器
 * @Date: 14:06 2020/8/3
 * @Modified By:
 */
public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
