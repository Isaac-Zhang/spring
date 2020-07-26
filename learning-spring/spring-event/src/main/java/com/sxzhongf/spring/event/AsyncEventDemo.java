package com.sxzhongf.spring.event;

import com.liferunner.learning.spring.pojo.Person;
import com.sxzhongf.spring.event.custom.SxzhongfEventListener;
import com.sxzhongf.spring.event.custom.SxzhongfSpringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * 通过 {@link ApplicationEventMulticaster} 切换线程池来实现 异步事件发布
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see ApplicationEventMulticaster
 * @see java.util.concurrent.Executors
 * @see CustomizableThreadFactory
 * @see ExecutorService
 * @since 2020/7/26
 **/
public class AsyncEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AsyncEventDemo.class);
        context.addApplicationListener(new SxzhongfEventListener());
        context.refresh();

        // 获取 ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            // 创建自定义线程池
            ExecutorService executorService = Executors.newFixedThreadPool(8, new CustomizableThreadFactory("fox-test-thread-pool"));
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);
            // 为 applicationEventMulticaster Bean 创建一个 Spring ContextClosedEvent 的事件监听
            applicationEventMulticaster.addApplicationListener((ContextClosedEvent contextClosedEvent) -> {
                if (!executorService.isShutdown()) {
                    executorService.shutdown();
                }
            });
        }
        context.publishEvent(new SxzhongfSpringEvent(Person.createPerson()));
        context.close();
    }


//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        applicationEventPublisher.publishEvent(new SxzhongfSpringEvent(Person.createPerson()));
//    }
}
