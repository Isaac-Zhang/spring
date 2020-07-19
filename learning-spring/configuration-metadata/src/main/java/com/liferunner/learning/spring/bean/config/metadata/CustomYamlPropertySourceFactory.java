package com.liferunner.learning.spring.bean.config.metadata;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 基于 Yaml 格式的 {@link PropertySourceFactory} 实现
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
public class CustomYamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        // way 1: 基于 Properties 的实现
//        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//        yamlPropertiesFactoryBean.setResources(resource.getResource());
//        Properties yamlProperties = yamlPropertiesFactoryBean.getObject();

        // way 2: 基于 Map 的实现  -> 等效于上面的实现
        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(resource.getResource());
        Map<String, Object> map = yamlMapFactoryBean.getObject();
        Properties yamlProperties = new Properties();
        map.entrySet().forEach(m -> {
            String key = m.getKey();
            LinkedHashMap<String, Object> linkedHashMap = ((LinkedHashMap<String, Object>) m.getValue());
            linkedHashMap.forEach((k, v) -> {
                yamlProperties.setProperty(key + "." + k, v.toString());
            });

        });

        return new PropertiesPropertySource(name, yamlProperties);
    }
}
