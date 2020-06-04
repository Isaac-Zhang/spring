package com.liferunner.learning.spring.factory;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link Person} 的{@link FactoryBean} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/4
 **/
public class PersonFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return Person.createPerson();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
