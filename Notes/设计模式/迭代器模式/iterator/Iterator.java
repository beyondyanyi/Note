package com.example.designpatterns.iterator;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 13:54 2020/8/3
 * @Modified By:
 */
public interface Iterator<Item> {
    Item next();
    boolean hasNext();
}
