package com.liferunner.learning.spring.bean.config.metadata;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;

/**
 * {@link BeanFactoryAware} 扩展实现 yaml properties
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
@Configuration
public class PersonYamlConfig implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("这是来自于yaml config" + beanFactory.getBean("yamlPropertiesPerson"));
    }
}
