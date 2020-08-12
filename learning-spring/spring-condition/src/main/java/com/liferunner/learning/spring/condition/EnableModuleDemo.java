package com.liferunner.learning.spring.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Enable 模式启动相关模块功能配置 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/8/12
 **/
@EnableCustomAction
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableModuleDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("helloIsaac", String.class));
        applicationContext.close();
    }
}
