package com.liferunner.learning.spring.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

import java.lang.annotation.*;

/**
 * 自定义注解，并通过{@link AutowiredAnnotationBeanPostProcessor}注入
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomPersonAnnotation {
}
