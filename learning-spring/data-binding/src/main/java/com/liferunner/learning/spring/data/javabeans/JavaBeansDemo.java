package com.liferunner.learning.spring.data.javabeans;

import com.liferunner.learning.spring.pojo.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

/**
 * 演示 {@link Introspector} 返回 POJO {@link Person} 的 {@link BeanInfo}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/24
 **/
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        Person person = new Person();

        // Introspector.getBeanInfo 第二个参数 stopClass 用于排除父类的干扰，比如 Object.class
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        System.out.println(beanInfo);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        // 打印出所有的方法名称
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> System.out.println(propertyDescriptor.getDisplayName()));

        System.out.println("=====================================");
        // 打印出所有的属性描述
        /**
         * java.beans.PropertyDescriptor
         *  [
         *      name=class;
         *      propertyType=class java.lang.Class;
         *      readMethod=public final native java.lang.Class java.lang.Object.getClass()
         *  ]
         */
        // 因为所有的 java 类均继承自 java.lang.Object
        // class 属性来自于 Object#getClass
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
    }
}
