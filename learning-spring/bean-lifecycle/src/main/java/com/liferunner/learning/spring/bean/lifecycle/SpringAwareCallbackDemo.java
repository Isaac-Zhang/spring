package com.liferunner.learning.spring.bean.lifecycle;

import com.liferunner.learning.spring.pojo.PersonHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 用于验证Spring Bean Aware 回调接口
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/14
 **/
public class SpringAwareCallbackDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String location = "META-INF/spring/Bean-Definition-demo-Context.xml";
        applicationContext.setConfigLocation(location);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(PersonHolder.class));
        applicationContext.close();
    }
}
