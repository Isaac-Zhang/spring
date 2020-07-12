package com.liferunner.learning.spring.bean.lifecycle;

import com.liferunner.learning.spring.pojo.Person;
import com.liferunner.learning.spring.pojo.SuperPerson;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Merged {@link BeanDefinition} 示例演示
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/12
 **/
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/spring/Bean-Definition-demo-Context.xml";

        beanDefinitionReader.loadBeanDefinitions(location);

        System.out.println(beanFactory.getBean("personForDemo", Person.class));
        System.out.println(beanFactory.getBean("superPerson", SuperPerson.class));
    }
}
