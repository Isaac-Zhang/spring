package com.liferunner.learning.spring.bean.config.metadata;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link PropertiesBeanDefinitionReader} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/17
 **/
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        // 定义 BeanDefinitionRegistry -> DefaultListableBeanFactory(子类/实现类)
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 定义 Properties BeanDefinitionReader
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);

        // 直接根据 location 加载 BeanDefinition
        // (因为Properties 文件默认加载的编码是 UTF-8，但是
        // PropertiesBeanDefinitionReader 读取编码默认是 ISO-8859-1，因此会有乱码)
//        String location = "META-INF/person-beandefinition.properties";
//        int loadBeanCounts = reader.loadBeanDefinitions(location);

        // 下面的是经过编码的 加载 BeanDefinition,不会有中文乱码
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/META-INF/person-beandefinition.properties");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int loadBeanCounts = reader.loadBeanDefinitions(encodedResource);
        System.out.printf("总共加载了 %d 个 BeanDefinitions\n", loadBeanCounts);

        System.out.println(beanFactory.getBean("isaacBeanName", Person.class));
    }
}
