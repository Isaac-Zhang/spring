package com.sxzhongf.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        // Object.class 是为了过滤掉父类的 getClass 方法
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println(propertyDescriptor);

                });
    }

    static class StringToInteger extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            super.setValue(value);
        }
    }
}
