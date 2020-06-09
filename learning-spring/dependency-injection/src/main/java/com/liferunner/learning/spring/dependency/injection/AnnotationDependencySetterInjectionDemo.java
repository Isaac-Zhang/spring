package com.liferunner.learning.spring.dependency.injection;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Annotation 依赖 Setter 注入示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/9
 **/
public class AnnotationDependencySetterInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/spring/Bean-Definition-demo-Context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        applicationContext.refresh();

        System.out.println(applicationContext.getBean(PersonHolder.class));
        applicationContext.close();
    }

    /**
     *
     *
     * @param person
     * @return
     */
    @Bean
    public PersonHolder personHolder(Person person){
        //使用 PersonHolder 的构造器注入
//        PersonHolder personHolder = new PersonHolder(person);
//        return personHolder;
        //使用 PersonHolder 的属性注入
        PersonHolder personHolder = new PersonHolder();
        personHolder.setPerson(person);
        return personHolder;
    }
}
