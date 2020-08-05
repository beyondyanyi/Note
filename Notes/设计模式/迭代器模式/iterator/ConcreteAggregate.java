package com.example.designpatterns.iterator;


/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 13:54 2020/8/3
 * @Modified By:
 */
public class ConcreteAggregate implements Aggregate {
    private Integer[] items;

    public ConcreteAggregate() {
        items = new Integer[10];
        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator<Integer>(items);
    }
}
