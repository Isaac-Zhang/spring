package com.liferunner.learning.spring.config;


import com.liferunner.learning.spring.factory.DefaultPersonFactory;
import com.liferunner.learning.spring.factory.PersonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDemoClass{

    public ConfigDemoClass() {
    }

    @Bean(initMethod = "initializeBeanCustomMethod")
    public PersonFactory personFactory(){
        return new DefaultPersonFactory();
    }
}
