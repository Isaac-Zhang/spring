package com.liferunner.learning.spring.bean.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.util.NumberUtils;

/**
 * 注解 {@link BeanDefinition} 注册示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/12
 **/
public class AnnotatedBeanDefinitionParseringDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beforeCount = beanFactory.getBeanDefinitionCount();
        BeanNameGenerator beanNameGenerator = (definition, registry) -> "Bean-isaacGenerateName-";
        annotatedBeanDefinitionReader.setBeanNameGenerator(beanNameGenerator);
        // 注册 BeanDefinition
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionParseringDemo.class);
        int afterCount = beanFactory.getBeanDefinitionCount();
        System.out.println("当前注入的 BeanDefinition 数量：" + (afterCount - beforeCount));
        // 表明注册的 Bean 默认名称 为当前类的 首字母小写
//        System.out.println(beanFactory.getBean("annotatedBeanDefinitionParseringDemo",AnnotatedBeanDefinitionParseringDemo.class));
        System.out.println(beanFactory.getBean(AnnotatedBeanDefinitionParseringDemo.class));


        System.out.println(beanFactory.getBeanNamesForType(AnnotatedBeanDefinitionParseringDemo.class));
    }
}
