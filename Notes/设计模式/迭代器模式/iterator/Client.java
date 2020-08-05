package com.example.designpatterns.iterator;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:
 * @Date: 13:55 2020/8/3
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
