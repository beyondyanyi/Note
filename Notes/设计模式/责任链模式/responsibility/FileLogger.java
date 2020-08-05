package com.example.designpatterns.responsibility;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:文件记录器
 * @Date: 14:08 2020/8/3
 * @Modified By:
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
