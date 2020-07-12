package com.liferunner.learning.spring.bean.lifecycle;

import com.liferunner.learning.spring.pojo.Person;
import com.liferunner.learning.spring.pojo.SuperPerson;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * 使用 {@link InstantiationAwareBeanPostProcessor }Bean 类型示例化 演示
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/12
 **/
public class BeanClassInstantiationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessorAdapter() {
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals(beanName, "superPerson")) {
                    SuperPerson sp = new SuperPerson();
                    sp.setDesc("i am from customize bean post processor.");
                    return sp;
                }
                return super.postProcessBeforeInstantiation(beanClass, beanName);
            }

            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals(beanName,"personForDemo") && (bean.getClass().equals(Person.class))){
                    Person person = ((Person) bean);
                    person.setAge(18);
                    person.setId(999L);
                    //返回 false ,阻止后续的属性赋值（填入）
                    //AbstractAutowireCapableBeanFactory.populateBean
                    //InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation
                    return false;
                }
                return super.postProcessAfterInstantiation(bean, beanName);
            }
        });

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/spring/Bean-Definition-demo-Context.xml";

        beanDefinitionReader.loadBeanDefinitions(location);

        System.out.println(beanFactory.getBean("personForDemo", Person.class));
        System.out.println(beanFactory.getBean("superPerson", SuperPerson.class));
    }
}
