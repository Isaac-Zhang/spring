package com.liferunner.learning.spring.dependency.injection;

import com.liferunner.learning.spring.dependency.injection.annotation.CustomPersonAnnotation;
import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Annotation 依赖 Setter 注入示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/9
 **/
public class AnnotationDependencySetterInjectionDemo {

    @CustomPersonAnnotation
    private Person person;

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> customAnnotationTypes = new LinkedHashSet<>(Arrays.asList(
                Autowired.class, Value.class, CustomPersonAnnotation.class
        ));
        beanPostProcessor.setAutowiredAnnotationTypes(customAnnotationTypes);
        return beanPostProcessor;
    }

//    @Bean
//    @Order(value = Ordered.LOWEST_PRECEDENCE - 3)
//    private static AutowiredAnnotationBeanPostProcessor customBeanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        beanPostProcessor.setAutowiredAnnotationType(CustomPersonAnnotation.class);
//        return beanPostProcessor;
//    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/spring/Bean-Definition-demo-Context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);

        applicationContext.refresh();

        System.out.println(applicationContext.getBean(PersonHolder.class));
        AnnotationDependencySetterInjectionDemo demo = applicationContext.getBean(AnnotationDependencySetterInjectionDemo.class);
        System.out.println("CustomPersonAnnotation person = " + demo.person);
        applicationContext.close();
    }


    /**
     * @param person
     * @return
     */
    @Bean
    public PersonHolder personHolder(Person person) {
        //使用 PersonHolder 的构造器注入
//        PersonHolder personHolder = new PersonHolder(person);
//        return personHolder;
        //使用 PersonHolder 的属性注入
        PersonHolder personHolder = new PersonHolder();
        personHolder.setPerson(person);
        return personHolder;
    }
}
