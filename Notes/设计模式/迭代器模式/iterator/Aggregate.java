package com.example.designpatterns.iterator;


/**
 * @Author: beyondyanyi@gmail.com
 * @Description:聚合类
 * @Date: 13:53 2020/8/3
 * @Modified By:
 */
public interface Aggregate {
    Iterator createIterator();
}
