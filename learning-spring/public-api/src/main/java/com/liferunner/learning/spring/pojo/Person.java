package com.liferunner.learning.spring.pojo;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 个体类对象
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/3
 **/
public class Person implements BeanNameAware {

    private long id;
    private String name;
    private int age;

    /**
     * 当前bean 的名称
     */
    private transient String beanName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static Person createPerson() {
        Person person = new Person();
        person.setName("张盼");
        person.setId(6L);
        person.setAge(18);
        return person;
    }

    @PostConstruct
    private void init(){
        System.out.println("User ["+beanName+"] 初始化中...");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("User ["+beanName+"] 销毁中...");
    }

}
