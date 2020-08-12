package com.liferunner.learning.spring.conditional.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现自定义 {@link Condition}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/8/12
 **/
public class IsaProfileCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取条件应用上下文中的 Environment
        Environment environment = context.getEnvironment();

        return environment.acceptsProfiles("isa1");
    }
}
