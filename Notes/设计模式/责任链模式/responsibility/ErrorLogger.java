package com.example.designpatterns.responsibility;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:错误记录器
 * @Date: 14:08 2020/8/3
 * @Modified By:
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
