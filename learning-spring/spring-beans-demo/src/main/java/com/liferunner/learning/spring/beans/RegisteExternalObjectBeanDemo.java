package com.liferunner.learning.spring.beans;

import com.liferunner.learning.spring.config.ConfigDemoClass;
import com.liferunner.learning.spring.factory.DefaultPersonFactory;
import com.liferunner.learning.spring.factory.PersonFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示如何注册外部化对象/普通对象为一个单例 bean 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/6
 **/
public class RegisteExternalObjectBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        PersonFactory personFactory = new DefaultPersonFactory();
        applicationContext.register(ConfigDemoClass.class);
        applicationContext.refresh();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("singletoneBeanName", personFactory);
        PersonFactory personFactoryFromSingleton = applicationContext.getBean("singletoneBeanName", PersonFactory.class);
        System.out.println(personFactory == personFactoryFromSingleton);
    }
}
