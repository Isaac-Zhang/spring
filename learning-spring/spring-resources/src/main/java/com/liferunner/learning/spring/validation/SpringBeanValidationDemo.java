package com.liferunner.learning.spring.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring Bean Validation 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see Validator
 * @see LocalValidatorFactoryBean
 * @see MethodValidationPostProcessor
 * @since 2020/7/22
 **/
public class SpringBeanValidationDemo {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

        Validator validator = applicationContext.getBean(Validator.class);
        System.out.println(validator instanceof LocalValidatorFactoryBean);
        PersonProcessor personProcessor = applicationContext.getBean(PersonProcessor.class);
        personProcessor.process(new PersonModel());
        applicationContext.close();
    }

    @Component
    @Validated
    static class PersonProcessor {
        public void process(@Valid PersonModel person) {
            System.out.println(person);
        }
    }

    static class PersonModel {

        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "PersonModel{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
