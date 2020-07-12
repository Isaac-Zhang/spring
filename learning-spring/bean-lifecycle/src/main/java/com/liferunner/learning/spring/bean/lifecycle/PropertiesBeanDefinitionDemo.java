package com.liferunner.learning.spring.bean.lifecycle;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 利用 {@link PropertiesBeanDefinitionReader} 注册 bean
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/12
 **/
public class PropertiesBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 BeanFactoryRegistry
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        // 定义 数据源
        Resource resource = new ClassPathResource("META-INF/isaac.properties");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        beanDefinitionReader.loadBeanDefinitions(encodedResource);
        // 获取 Bean
        System.out.println(beanFactory.getBean("isaac",Person.class));
    }
}
