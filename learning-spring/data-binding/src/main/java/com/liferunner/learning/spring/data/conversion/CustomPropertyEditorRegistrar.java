package com.liferunner.learning.spring.data.conversion;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 实现 自定义 {@link PropertyEditorRegistrar}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/25
 **/
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        // 将我们自定义的 CustomStringToProperty 转换器注册到 Spring 框架
        registry.registerCustomEditor(Person.class, "contextProperties", new CustomStringToProperty());
    }
}
