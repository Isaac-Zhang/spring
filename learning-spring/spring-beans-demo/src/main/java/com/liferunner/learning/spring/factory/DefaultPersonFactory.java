package com.liferunner.learning.spring.factory;

import com.liferunner.learning.spring.pojo.Person;

/**
 * {@link PersonFactory} 默认实现
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/4
 **/
public class DefaultPersonFactory implements PersonFactory {

    @Override
    public Person createPerson() {
        Person person=new Person();
        person.setName("i am from default");
        person.setId(888L);
        person.setAge(18);
        return person;
    }
}
