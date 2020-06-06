package com.liferunner.learning.spring.beans;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 注册示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/4
 **/
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory  beanFactory = new ClassPathXmlApplicationContext("META-INF/spring/Bean-Instantiation-Context.xml");

        Person personFromInstantiationMethod =  beanFactory.getBean("person-from-instantiation-method", Person.class);
        Person personFromStaticMethod =  beanFactory.getBean("person-from-static-method", Person.class);
        Person personFactoryBean = beanFactory.getBean("personFactoryBean",Person.class);

        System.out.println(personFromInstantiationMethod);
        System.out.println(personFromStaticMethod);
        System.out.println(personFactoryBean);
    }
}
