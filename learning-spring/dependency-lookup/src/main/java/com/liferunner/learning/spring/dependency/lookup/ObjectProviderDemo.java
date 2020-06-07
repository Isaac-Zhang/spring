package com.liferunner.learning.spring.dependency.lookup;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/6/7
 **/
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
//        ObjectProvider<Person> personObjectProvider = applicationContext.getBeanProvider(Person.class);
//        System.out.println(personObjectProvider.getObject());

        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(stringObjectProvider.getObject());

        findStringBean(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);
        applicationContext.close();
    }

    private static void findStringBean(ApplicationContext applicationContext) {
        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        // 因为ObjectProvider 继承了 Iterable, 因此它就是 Iterable
        // 类似与：
        Iterable<String> stringIterable = stringObjectProvider;
        for (String str : stringIterable) {
            System.out.println("l am from " + str);
        }

        // 也可以使用 stream 表达式，因为在 5.1的时候也提供了支持
        stringObjectProvider.stream().forEach(System.out::println);
    }

//    @Bean
//    public Person person() {
//        Person person = new Person();
//        person.setId(6L);
//        person.setName("objectProviderPerson");
//        person.setAge(18);
//        return person;
//    }

    // 把上面的 person bean 注释掉可以演示下面的效果
    private static void displayObjectProviderIfAvailable(ApplicationContext applicationContext) {
        ObjectProvider<Person> objectProvider = applicationContext.getBeanProvider(Person.class);
        Person person = objectProvider.getIfAvailable(() -> Person.createPerson());
        Person person1 = objectProvider.getIfAvailable(Person::createPerson);
        System.out.println(person);
        System.out.println("缩略版的Lambda创建：" + person1);
    }

    @Bean
    @Primary // 因为会有多个 String 类型的 bean ，因此需要表明那个是 主 bean，否则会报错
    public String helloWorld() {
        return "hello, World";
    }

    @Bean
    public String message() {
        return "message";
    }

    @Bean
    public String secondStringBean() {
        return "secondStringBean";
    }
}
