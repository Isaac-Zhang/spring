package com.liferunner.learning.spring.bean.config.metadata;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于 {@link YamlPropertiesFactoryBean} 的实现
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
@PropertySource(value = "META-INF/person.yaml")
public class YamlPropertiesFactoryBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(YamlPropertiesFactoryBeanDemo.class);
        context.refresh();
        Person p = context.getBean(Person.class);
        System.out.println(p);
        context.close();
    }
}
