package com.liferunner.learning.spring.dependency.injection;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Api 依赖 Setter 注入示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/9
 **/
public class ApiDependencySetterInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinition beanDefinition = createPersonHolderBeanDefinition();
        applicationContext.registerBeanDefinition("personHolder",beanDefinition);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/spring/Bean-Definition-demo-Context.xml");
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(PersonHolder.class));
        applicationContext.close();
    }

    private static BeanDefinition createPersonHolderBeanDefinition(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(PersonHolder.class);

        beanDefinitionBuilder.addPropertyReference("person","superPerson");

        return beanDefinitionBuilder.getBeanDefinition();
    }
}
