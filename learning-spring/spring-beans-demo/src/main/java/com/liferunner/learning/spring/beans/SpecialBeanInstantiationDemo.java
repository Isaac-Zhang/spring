package com.liferunner.learning.spring.beans;

import com.liferunner.learning.spring.factory.DefaultPersonFactory;
import com.liferunner.learning.spring.factory.PersonFactory;
import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊 bean 注册示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/4
 **/
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/Special-Bean-Instantiation-Context.xml");

        ServiceLoader<PersonFactory> serviceLoader = beanFactory.getBean("personFactoryServiceLoader", ServiceLoader.class);

        displayFromServiceLoader(serviceLoader);
    }

    private static void displayFromServiceLoader(ServiceLoader<PersonFactory> serviceLoader) {
        Iterator<PersonFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            PersonFactory personFactory = iterator.next();
            System.out.println(personFactory.createPerson());
        }
    }

}
