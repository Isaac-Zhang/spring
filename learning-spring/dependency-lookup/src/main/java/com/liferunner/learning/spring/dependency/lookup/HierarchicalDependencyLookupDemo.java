package com.liferunner.learning.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 层次型依赖查找 {@link org.springframework.beans.factory.HierarchicalBeanFactory}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/7
 **/
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();

        // 1. 查找 Hierarchical BeanFactory <- 来自于 ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory 的 Parent BeanFactory 是 ：" + beanFactory.getParentBeanFactory());

        // 2. 设置 Parent BeanFactory
        beanFactory.setParentBeanFactory(createParentBeanFactory());
        System.out.println("当前BeanFactory 的 Parent BeanFactory 是 ：" + beanFactory.getParentBeanFactory());
        applicationContext.close();
    }

    private static BeanFactory createParentBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/spring/Bean-Definition-demo-Context.xml");
        return beanFactory;
    }
}
