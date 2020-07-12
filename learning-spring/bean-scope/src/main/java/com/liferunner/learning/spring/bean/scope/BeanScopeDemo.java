package com.liferunner.learning.spring.bean.scope;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Collection;
import java.util.Map;

/**
 * Spring Bean Scope Demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
public class BeanScopeDemo implements DisposableBean {

    // 作用域查找
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //默认就是 singleton
    private static Person singletonPerson() {
        return newPerson();
    }

    // 作用域查找
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private static Person prototypePerson() {
        return newPerson();
    }

    // 作用域注入
    @Autowired
    @Qualifier(value = "singletonPerson")
    private Person singletonPerson;

    @Autowired
    @Qualifier(value = "singletonPerson")
    private Person singletonPerson1;

    @Autowired
    @Qualifier(value = "prototypePerson")
    private Person prototypePerson;

    @Autowired
    @Qualifier(value = "prototypePerson")
    private Person prototypePerson1;

    @Autowired
    @Qualifier(value = "prototypePerson")
    private Person prototypePerson2;

    @Autowired
    private Map<String, Person> personMap;

    @Autowired
    private Collection<Person> personCollection;

    @Autowired
    private ConfigurableBeanFactory beanFactory; // 注入的是 Resolvable Dependency

    private static Person newPerson() {
        Person person = new Person();
        person.setId(System.nanoTime());
        return person;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(BeanScopeDemo.class);
        configApplicationContext.refresh();

        createPersonByLookup(configApplicationContext);

        createPersonByInjection(configApplicationContext);
        configApplicationContext.close();
    }

    private static void createPersonByLookup(AnnotationConfigApplicationContext context) {
        for (int i = 0; i < 5; i++) {
            System.out.println("singletonPerson : " + context.getBean("singletonPerson"));
            System.out.println("prototypePerson : " + context.getBean("prototypePerson"));
        }
    }

    private static void createPersonByInjection(AnnotationConfigApplicationContext configApplicationContext) {
        BeanScopeDemo demo = configApplicationContext.getBean(BeanScopeDemo.class);
        System.out.println("injected singletonPerson : " + demo.singletonPerson);
        System.out.println("injected singletonPerson1 : " + demo.singletonPerson1);
        System.out.println("injected prototypePerson : " + demo.prototypePerson);
        System.out.println("injected prototypePerson1 : " + demo.prototypePerson1);
        System.out.println("injected prototypePerson2 : " + demo.prototypePerson2);
        System.out.println("injected personMap : " + demo.personMap);
        System.out.println("injected personCollection : " + demo.personCollection);

    }

    /**
     * 将当前 BeanScopeDemo 对象执行完后销毁
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("BeanScopeDemo 正在销毁中...");

        this.prototypePerson.destroy();
        this.prototypePerson1.destroy();
        this.prototypePerson2.destroy();
        //销毁 map 中的 prototype 的 Person Bean
        for (Map.Entry<String, Person> entry : this.personMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition bd = this.beanFactory.getMergedBeanDefinition(beanName);
            if (bd.isPrototype()) {
                entry.getValue().destroy();
            }
        }
        System.out.println("BeanScopeDemo 销毁结束。");
    }
}
