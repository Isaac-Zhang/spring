package com.liferunner.learning.spring.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用 {@link ImportSelector}示例注入配置
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see ImportSelector
 * @since 2020/8/12
 **/
public class EnableIsaacImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("using ImportSelector injected config class...");
        return new String[]{"com.liferunner.learning.spring.condition.EnableCustomConfiguration"};
    }
}
