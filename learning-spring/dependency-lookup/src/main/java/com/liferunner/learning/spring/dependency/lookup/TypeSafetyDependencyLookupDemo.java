package com.liferunner.learning.spring.dependency.lookup;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 依赖查找的安全性比较 演示
 * <ul>
 *     <li>BeanFactory 非安全</li>
 *     <li>ObjectFactory 非安全</li>
 *     <li>ObjectProvider 安全</li>
 * </ul>
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/7
 **/
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetIfAvailable(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Person> objectProvider = applicationContext.getBeanProvider(Person.class);
        printError("displayObjectProviderGetIfAvailable", () -> objectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<Person> objectFactory = applicationContext.getBeanProvider(Person.class);
        printError("displayObjectFactoryGetObject", () -> objectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printError("displayBeanFactoryGetBean", () -> {
            beanFactory.getBean(Person.class);
        });
    }

    private static void printError(String source, Runnable runnable) {
        System.err.println("错误来自于：" + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
