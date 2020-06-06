package com.liferunner.learning.spring.config;


import com.liferunner.learning.spring.factory.DefaultPersonFactory;
import com.liferunner.learning.spring.factory.PersonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ConfigDemoClass{

    public ConfigDemoClass() {
    }

    @Bean(initMethod = "initializeBeanCustomMethod",destroyMethod = "destroyBeanCustomMethod")
    @Lazy
    public PersonFactory personFactory(){
        return new DefaultPersonFactory();
    }
}
