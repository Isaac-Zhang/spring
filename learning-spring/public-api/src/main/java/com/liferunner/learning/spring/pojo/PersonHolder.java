package com.liferunner.learning.spring.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * {@link Person}对象持有类
 * <ul>
 *     <li>第1个回调：{@link BeanNameAware}</li>
 *     <li>第2个回调：{@link BeanClassLoaderAware}</li>
 *     <li>第3个回调：{@link BeanFactoryAware}</li>
 *     <li>前3个加载可以参考：{@see AbstractAutowireCapableBeanFactory#invokeAwareMethods(Object)}</li>
 *     <li>第4个回调 {@link EnvironmentAware}, 只存在于 {@link ApplicationContext}上下文
 *      可参考：{@see ApplicationContextAwareProcessor#invokeAwareInterfaces(Object)}
 *     </li>
 * </ul>
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/14
 **/
public class PersonHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {

    private Person person;
    private ClassLoader classLoader;
    private String beanName;
    private BeanFactory beanFactory;
    private Environment environment;

    public PersonHolder(Person person) {
        this.person = person;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "PersonHolder{" +
                "person=" + person +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
