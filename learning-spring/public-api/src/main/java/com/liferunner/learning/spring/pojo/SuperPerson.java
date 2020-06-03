package com.liferunner.learning.spring.pojo;

import com.liferunner.learning.spring.annotation.Super;

/**
 * 超级个体
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/3
 **/
@Super
public class SuperPerson extends Person {
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SuperPerson{" +
                "desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
