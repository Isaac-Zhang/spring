package com.liferunner.learning.spring.bean.config.metadata;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/16
 **/
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
//        BeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setAttribute("name","张盼");
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        beanDefinitionBuilder.addPropertyValue("name", "isaac");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // AttributeAccessor 作为 Bean 的额外参数传递
        beanDefinition.setAttribute("name", "张盼");
        // BeanMetadataElement 作为 Bean 元素来源的辅助判断
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("person", beanName)
                        && bean.getClass().equals(Person.class)) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    System.out.println(bd.getAttribute("name"));
                    // 使用上面的 BeanMetadataElement 的 source 来作为特定判断依据
                    if (bd.getSource().equals(BeanConfigurationMetadataDemo.class)) {
                        Person person = (Person) bean;
                        person.setName(bd.getAttribute("name").toString());
                    }
                }
                return bean;
            }
        });

        beanFactory.registerBeanDefinition("person", beanDefinition);

        System.out.println(beanFactory.getBean("person", Person.class));
    }
}
