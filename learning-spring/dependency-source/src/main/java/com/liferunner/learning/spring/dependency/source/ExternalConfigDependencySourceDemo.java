package com.liferunner.learning.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置属性信息
 * {@link Value}
 * {@link PropertySource}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
@Configuration
@PropertySource(value = "application.properties", encoding = "UTF-8")
public class ExternalConfigDependencySourceDemo {

    @Value("${my.id:0}")
    private Long id;

    @Value("${my.name:isaac}")
    private String name;

    @Value("${my.resource}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(ExternalConfigDependencySourceDemo.class);
        configApplicationContext.refresh();

        ExternalConfigDependencySourceDemo demo = configApplicationContext.getBean(ExternalConfigDependencySourceDemo.class);

        System.out.println("id: " + demo.id);
        System.out.println("name: " + demo.name);
        System.out.println("resource: " + demo.resource);
        configApplicationContext.close();
    }

}
