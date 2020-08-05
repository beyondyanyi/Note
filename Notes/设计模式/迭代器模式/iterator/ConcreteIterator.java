package com.example.designpatterns.iterator;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 13:55 2020/8/3
 * @Modified By:
 */
public class ConcreteIterator<Item> implements Iterator {

    private Item[] items;
    private int position = 0;

    public ConcreteIterator(Item[] items) {
        this.items = items;
    }

    @Override
    public Object next() {
        return items[position++];
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }
}