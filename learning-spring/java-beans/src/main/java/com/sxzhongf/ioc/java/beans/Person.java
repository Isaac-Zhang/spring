package com.sxzhongf.ioc.java.beans;

/**
 * POJO simple
 *
 * name & age 在传统的java beans中被成为 property
 * get               / set
 * 可读方法（readable）/ 可写方法（writable）
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/5/19
 **/
public class Person {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
