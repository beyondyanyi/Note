package com.demo.demo.factory.simpleFactory;

import com.demo.demo.factory.entity.man.BlackMan;
import com.demo.demo.factory.entity.man.Human;
import com.demo.demo.factory.entity.man.WhiteMan;
import com.demo.demo.factory.entity.man.YellowMan;

/**
 * @description: 工厂类
 * 简单工厂模式最大的优点在于实现对象的创建和对象的使用分离，将对象的创建交给专门的工厂类负责，
 * 但是其最大的缺点在于工厂类不够灵活，增加新的具体产品需要修改工厂类的判断逻辑代码，而且产品较多时，工厂方法代码逻辑将会非常复杂
 * @author: 毅哥
 * @time: 2020/1/8 15:45
 */
public class SimpleFactory {

    public static Human createHuman(String name){
        Human human=null;
        if("黑人".equals(name)){
            human=new BlackMan();
        }else if ("白人".equals(name)){
            human=new WhiteMan();
        }else if("黄人".equals(name)){
            human=new YellowMan();
        }
        return human;
    }
}
