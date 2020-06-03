package com.liferunner.learning.spring.dependency;

import com.liferunner.learning.spring.pojo.SuperPerson;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名查找示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/3
 **/
public class BeanAliasDemo {

    public static void main(String[] args) {

        // 声明 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/Bean-Definition-Context.xml");

        // 通过 bean name 查找 bean
        System.out.println(beanFactory.getBean("person"));
        System.out.println(beanFactory.getBean("superPerson"));

        // 通过 bean 别名查找
        System.out.println(beanFactory.getAliases("chaojiisaac"));
        System.out.println(beanFactory.getBean("chaojiisaac"));

        SuperPerson superPerson = beanFactory.getBean("superPerson", SuperPerson.class);
        SuperPerson chaojiisaac = beanFactory.getBean("chaojiisaac", SuperPerson.class);
        System.out.println("chaojiisaac 和 superPerson 是否为同一个对象：" + (superPerson == chaojiisaac));


    }
}
