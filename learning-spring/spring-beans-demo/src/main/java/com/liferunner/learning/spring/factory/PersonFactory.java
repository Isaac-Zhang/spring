package com.liferunner.learning.spring.factory;

import com.liferunner.learning.spring.pojo.Person;

/**
 * 创建 {@link com.liferunner.learning.spring.pojo.Person} 抽象工厂示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/4
 **/
public interface PersonFactory {
    default Person createPerson(){
        return new Person();
    }
}
