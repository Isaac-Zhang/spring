package com.liferunner.learning.spring.condition;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 演示自定义装配 Enable 模块(三种模式)
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/8/12
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import({EnableCustomConfiguration.class}) //方式一： 使用 Import Configuration 注入配置
//@Import(EnableIsaacImportSelector.class) //方式二：使用 ImportSelector 实现注入配置
@Import(EnableIsaacImportBeanDefinitionRegistrar.class)//方式三：使用 ImportBeanDefinitionRegistrar 注入 bean
public @interface EnableCustomAction {

}
