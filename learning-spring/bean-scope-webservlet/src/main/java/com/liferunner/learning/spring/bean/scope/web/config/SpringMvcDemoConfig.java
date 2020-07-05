package com.liferunner.learning.spring.bean.scope.web.config;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring MVC config file
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
@Configuration
@EnableWebMvc
public class SpringMvcDemoConfig {

    @Bean
    @RequestScope
    public Person person() {
        return Person.createPerson();
    }
}
