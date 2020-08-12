package com.liferunner.learning.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 Enable 配置类
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/8/12
 **/
@Configuration
public class EnableCustomConfiguration {

    @Bean
    public String helloIsaac(){
        return "hello, isaac";
    }
}
