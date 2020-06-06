package com.liferunner.learning.spring.factory;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * {@link PersonFactory} 默认实现
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/4
 **/
public class DefaultPersonFactory implements PersonFactory, InitializingBean {

    @Override
    public Person createPerson() {
        Person person=new Person();
        person.setName("i am from default");
        person.setId(888L);
        person.setAge(18);
        return person;
    }

    @PostConstruct
    private void initBeanMethod(){
        System.out.println("@PostConstruct 初始化 : bean 初始化中....");
    }

    @Override
    public void initializeBeanCustomMethod(){
        System.out.println("initializeBeanCustomMethod 初始化 : bean 初始化中....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet 初始化 : bean 初始化中....");
    }
}
