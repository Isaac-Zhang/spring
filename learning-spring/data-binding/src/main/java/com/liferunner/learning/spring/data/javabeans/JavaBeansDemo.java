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

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        System.out.println(beanInfo);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        // 打印出所有的方法名称
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> System.out.println(propertyDescriptor.getDisplayName()));

        System.out.println("=====================================");
        // 打印出所有的属性描述
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
    }
}
