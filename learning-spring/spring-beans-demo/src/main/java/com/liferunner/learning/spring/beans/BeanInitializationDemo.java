package com.liferunner.learning.spring.beans;

import com.liferunner.learning.spring.config.ConfigDemoClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * bean 初始化示例
 * <ul>
 *     <li>{@link javax.annotation.PostConstruct}</li>
 *     <li>{@link org.springframework.beans.factory.InitializingBean}</li>
 *     <li>自定义初始化方法</li>
 * </ul>
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/6
 **/
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ConfigDemoClass.class);
        applicationContext.refresh();
        applicationContext.close();
    }

}

