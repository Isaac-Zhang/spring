package com.liferunner.learning.spring.bean.config.metadata;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于 Java 注解的 Yaml 外部化配置示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
@PropertySource(
        name = "annotatedYamlSource",
        value = "META-INF/person.yaml",
        factory = CustomYamlPropertySourceFactory.class
)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    private Person person(@Value("${person.id}") Long id,
                          @Value("${person.name}") String name,
                          @Value("${person.age}") int age) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        return person;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedYamlPropertySourceDemo.class);
        context.refresh();
        Person p = context.getBean(Person.class);
        System.out.println(p);
        context.close();
    }
}
