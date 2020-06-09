package com.liferunner.learning.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Autowiring XML 依赖注入示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/9
 **/
public class AutoWiringXMLDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String resourcePath = "classpath:/META-INF/spring/Autowiring-Dependency-Setter-Injection-Context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        System.out.println(beanFactory.getBean(PersonHolder.class));
    }
}
