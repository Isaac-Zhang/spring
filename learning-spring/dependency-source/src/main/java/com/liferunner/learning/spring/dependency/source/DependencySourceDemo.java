package com.liferunner.learning.spring.dependency.source;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 不被Spring IOC 管理的依赖来源测试
 * <ul>
 *     <li>{@link BeanFactory}</li>
 *     <li>{@link ApplicationContext}</li>
 *     <li>{@link ApplicationEventPublisher}</li>
 *     <li>{@link ResourceLoader}</li>
 * </ul>
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ObjectProvider<BeanFactory> beanFactoryObjectProvider;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 检测在 AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)
     * 注入的 不被 Spring IOC 管理的 Bean 对象是否只有 2 个元素
     * 1. BeanFactory
     * 2. AbstractApplicationContext 的实现类对象
     */
    @PostConstruct
    private void initByIOC() {
        System.out.println("beanFactory == objectProviderBeanFactory.getIfAvailable() : " + (beanFactory == beanFactoryObjectProvider.getIfAvailable()));
        System.out.println(beanFactoryObjectProvider.getIfAvailable().getClass().getName());
        System.out.println("beanFactory == applicationContext : " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() : " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("eventPublisher == applicationContext : " + (eventPublisher == applicationContext));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
        System.out.println("AbstractApplicationContext 的实现类对象名：" + applicationContext.getClass().getName());
    }

    @PostConstruct
    private void initByType() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ResourceLoader.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前beanFactory中无法找到" + beanType.getName());
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DependencySourceDemo.class);
        context.refresh();
        context.close();
    }
}
