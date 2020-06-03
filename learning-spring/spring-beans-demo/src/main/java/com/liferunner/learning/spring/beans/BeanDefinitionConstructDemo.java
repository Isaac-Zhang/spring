package com.liferunner.learning.spring.beans;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构造示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/3
 **/
public class BeanDefinitionConstructDemo {

    public static void main(String[] args) {

        usingBeanDefinitionBuilder();

        usingGenericBeanDefinition();

    }

    private static void usingBeanDefinitionBuilder() {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Person.class)
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "isaac")
                .addPropertyValue("age", 31)
                .getBeanDefinition();
    }

    private static void usingGenericBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(Person.class);

        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id",2L)
                .add("name", "isaac")
                .add("age", 31);
        beanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
