package com.liferunner.learning.spring.data.conversion;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示自定义的 {@link CustomPropertyEditorRegistrar}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/25
 **/
public class CustomPropertyEditorRegistrarDemo {

    public static void main(String[] args) {
        // 根据 xml 创建 applicationContext, 并且一句自动 refresh
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/custom-property-editor-context.xml");

        Person person = applicationContext.getBean("person", Person.class);

        System.out.println(person);

        applicationContext.close();
    }
}
