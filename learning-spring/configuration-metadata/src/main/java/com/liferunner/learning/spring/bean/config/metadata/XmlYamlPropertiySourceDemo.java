package com.liferunner.learning.spring.bean.config.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 XML 格式的 yaml 配置文件示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
public class XmlYamlPropertiySourceDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/xml-yaml-property-source-context.xml");

        Map<String, Object> map = beanFactory.getBean("xmlYamlPerson", Map.class);
        System.out.println(map);
    }
}
