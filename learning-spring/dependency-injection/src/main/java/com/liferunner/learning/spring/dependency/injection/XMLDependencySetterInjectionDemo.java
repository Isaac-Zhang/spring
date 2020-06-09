package com.liferunner.learning.spring.dependency.injection;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * XML 依赖注入示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/9
 **/
public class XMLDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String resourcePath = "classpath:/META-INF/spring/Dependency-Setter-Injection-Context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        System.out.println(beanFactory.getBean(PersonHolder.class));
    }
}
