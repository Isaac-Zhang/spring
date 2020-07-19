package com.liferunner.learning.spring.bean.config.metadata;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * Spring IOC 的配置元信息 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
// 这里会注入 person Bean
@Import(Person.class)
// 这里会注入 personForDemo，superPerson, personHolder 三个bean
@ImportResource(value = "classpath:/META-INF/spring/Bean-Definition-demo-Context.xml")
// 这里会倒入 properties 文件中的属性值（ 支持了 Java 8+ 的 @Repeatable 注解）
@PropertySource(value = "classpath:/META-INF/person-beandefinition.properties",
        encoding = "UTF-8")
@PropertySource(value = "classpath:/META-INF/person-beandefinition.properties",
        encoding = "UTF-8")
// 等价于下面的
//@PropertySources(value = {@PropertySource("1"),@PropertySource("2")})
public class AnnotatedMetadata4IOCDemo {

    @Bean
    // 根据上面 @PropertySource 的倒入属性值生成 Bean
    private Person configuredPerson(@Value("${isaacBeanName.id}") Long id,
                                    @Value("${isaacBeanName.name}") String name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(AnnotatedMetadata4IOCDemo.class);
        // 启动
        applicationContext.refresh();
        Map<String, Person> personsMap = applicationContext.getBeansOfType(Person.class);
        personsMap.entrySet().forEach(es -> {
            System.out.printf("beanName: %s, context: %s\n", es.getKey(), es.getValue());
        });
        //关闭
        applicationContext.close();
    }
}
