package com.liferunner.learning.spring.beans;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 注册示例
 * <ul>
 *     <li>XML 注册 Bean</li>
 *     <li>注解 注册 Bean</li>
 *     <li>核心API 注册 Bean</li>
 * </ul>
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/3
 **/
@Import(BeanDefinitionRegistryDemo.AnnotationBeansConfigurationClazz.class)
public class BeanDefinitionRegistryDemo {

    public static void main(String[] args) {
        // 1. XML 注册 bean
        registryBeanUsingXML();
        // 2. 注解 注册 bean
        registryBeanUsingAnnotation();
        // 3. @Import 注册 bean
        registryBeanUsingImport();
        // 4. 使用 API 注册 bean(带 beanName)
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        registryBeanUsingAPI(applicationContext, "isa-bean", Person.class);
        // 5. 使用 API 注册 bean(不带 beanName)
        registryBeanUsingAPI(applicationContext, null, Person.class);
        applicationContext.refresh();
        // 获取注册的 beans
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println("come from main..." + personMap);
        applicationContext.close();
    }

    private static void registryBeanUsingAPI(BeanDefinitionRegistry registry, String beanName, Class<?> beanClazz) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz)
                .addPropertyValue("id", 3L)
                .addPropertyValue("name", "isa")
                .addPropertyValue("age", 18);

        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void registryBeanUsingImport() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将标注 @Import 的配置类 注册
        applicationContext.register(BeanDefinitionRegistryDemo.class);
        // 启动上下文
        applicationContext.refresh();
        // 获取注册的 beans
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println("come from import..." + personMap);
        // 关闭应用上下文
        applicationContext.close();
    }


    public static void registryBeanUsingXML() {
        // 创建英文上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/Bean-Definition-Context.xml");
        // 启动上下文
        applicationContext.refresh();
        // 获取注册的 beans
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println("come from xml..." + personMap);
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void registryBeanUsingAnnotation() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeansConfigurationClazz.class);
        // 启动上下文
        applicationContext.refresh();
        // 获取注册的 beans
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println("come from annotation..." + personMap);
        // 关闭应用上下文
        applicationContext.close();
    }

    @Component
    public static class AnnotationBeansConfigurationClazz {

        @Bean(name = {"annotated-person,isa,dy"})
        public Person person() {
            Person person = new Person();
            person.setAge(18);
            person.setId(2L);
            person.setName("dy");
            return person;
        }
    }
}
