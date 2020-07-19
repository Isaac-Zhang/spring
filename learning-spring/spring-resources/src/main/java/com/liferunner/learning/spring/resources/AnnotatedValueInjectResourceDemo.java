package com.liferunner.learning.spring.resources;

import com.liferunner.learning.spring.resources.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
@Configuration
public class AnnotatedValueInjectResourceDemo {

    @Value("classpath:/META-INF/dev.properties")
    private Resource resource;

    private static Resource resource1;

    @Value("${user.dir}")
    private String currentPath;

    private static String currentPath1;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @PostConstruct
    private void init() {
        System.out.println(ResourceUtils.getContext(resource));
        resource1 = resource;
        System.out.println("===================");
        Stream.of(resources).map(ResourceUtils::getContext).forEach(System.out::println);
        System.out.println("===================");
        System.out.println(currentPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotatedValueInjectResourceDemo.class);
        applicationContext.refresh();

        System.out.println("=================");
        System.out.println("来自于 resource1:" + resource1);

        System.out.println("=================");
        System.out.println("来自于 currentPath1:" + currentPath1);

        applicationContext.close();
    }
}
