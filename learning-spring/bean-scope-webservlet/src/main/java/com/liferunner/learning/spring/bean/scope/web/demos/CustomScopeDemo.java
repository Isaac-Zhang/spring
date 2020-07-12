package com.liferunner.learning.spring.bean.scope.web.demos;

import com.liferunner.learning.spring.bean.scope.web.customScope.ThreadLevelScope;
import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义 scope {@link ThreadLevelScope}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/6
 **/
public class CustomScopeDemo {

    @Bean
    @Scope(ThreadLevelScope.SCOPE_NAME)
    private static Person person() {
        return genericPerson();
    }

    private static Person genericPerson() {
        Person person = new Person();
        person.setId(System.nanoTime());
        return person;
    }

    @Autowired
    @Qualifier(value = "person")
    private Person person;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CustomScopeDemo.class);
        // 设置 注册自定义的 scope,在 beanPostProcessor 回调中
        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLevelScope.SCOPE_NAME, new ThreadLevelScope());
        });
        context.refresh();
        CustomScopeDemo demo = context.getBean(CustomScopeDemo.class);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                System.out.printf("[Thread id : %d] 创建的 Person : %s \n",
                        Thread.currentThread().getId(), context.getBean(Person.class));
            });
            // 启动线程
            thread.start();
            try {
                // 强制等待线程执行结束
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        context.close();
    }
}
