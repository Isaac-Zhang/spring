package com.liferunner.learning.spring.dependency.injection;

import com.liferunner.learning.spring.pojo.Person;

/**
 * {@link Person} Bean 的 Holder
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/9
 **/
public class PersonHolder {

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonHolder() {
    }

    public PersonHolder(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonHolder{" +
                "person=" + person +
                '}';
    }
}
