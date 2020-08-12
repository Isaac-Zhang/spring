package com.liferunner.learning.spring.conditional.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * 演示 {@link Profile} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see Environment
 * @since 2020/8/12
 **/
@Configuration
public class ProfilesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfilesDemo.class);

        // 获取可配置（更改）的 Environment
        ConfigurableEnvironment environment = context.getEnvironment();
        // 设置默认 profiles
        environment.setDefaultProfiles("isa");

        // 设置活跃的 profiles
        environment.setActiveProfiles("isa1");
        context.refresh();
        System.out.println(context.getBean("isa", String.class));
        context.close();
    }

    @Bean(name = "isa")
    @Profile(value = "isa")
    public String isa() {
        return "isa";
    }

    @Bean(name = "isa")
    @Profile(value = "isa1")
    public String isa1() {
        return "isa1";
    }
}
