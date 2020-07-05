package com.liferunner.learning.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Resolvable Dependency 依赖注入演示
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
public class ResolvableDependencyDemo {

    @Autowired
    private String hello; 

    @PostConstruct
    private void init(){
        System.out.println(hello);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ResolvableDependencyDemo.class);
        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class,"Hello, Resolvable Dependency.");
        });

        context.refresh();
        context.close();
    }
}
