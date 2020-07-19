package com.liferunner.learning.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 注入{@link ResourceLoader} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
public class AutowiredResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Autowired
    private ResourceLoader autowiredResourceLoader;


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    private void init() {
        System.out.println("resourceLoader==applicationContext : " + (resourceLoader == applicationContext));
        System.out.println("resourceLoader==autowiredResourceLoader : " + (resourceLoader == autowiredResourceLoader));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AutowiredResourceLoaderDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }

}
