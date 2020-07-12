package com.liferunner.learning.spring.bean.scope.web.customScope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 创建线程安全的 Scope
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/6
 **/
public class ThreadLevelScope implements Scope {

    // scope name
    public static final String SCOPE_NAME = "thread-level-scope";

    // 线程对象存储 map
    private NamedThreadLocal<Map<String, Object>> mapNamedThreadLocal = new NamedThreadLocal<Map<String, Object>>("named-thread-local-map-cache") {
        @Override
        protected Map<String, Object> initialValue() {
            return new LinkedHashMap<String, Object>();
        }
    };

    /**
     * @return 当前线程存储的结果
     */
    private Map<String, Object> getContext() {
        return this.mapNamedThreadLocal.get();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object value = context.get(name);
        if (!Objects.isNull(value)) {
            value = objectFactory.getObject();
            context.put(name, value);
        }
        return value;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //todo 注销毁掉
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> context = getContext();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
