package com.liferunner.learning.spring.dependency.lookup;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/7
 **/
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        ObjectProvider<Person> personObjectProvider = applicationContext.getBeanProvider(Person.class);
        System.out.println(personObjectProvider.getObject());

        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(stringObjectProvider.getObject());
        applicationContext.close();
    }

    @Bean
    public Person person(){
        Person person = new Person();
        person.setId(6L);
        person.setName("objectProviderPerson");
        person.setAge(18);
        return person;
    }

    @Bean
    public String helloWorld(){
        return "hello, World";
    }
}
