package com.sxzhongf.spring.event;

import com.liferunner.learning.spring.pojo.Person;
import com.sxzhongf.spring.event.custom.SxzhongfEventListener;
import com.sxzhongf.spring.event.custom.SxzhongfSpringEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 自定义事件 {@link SxzhongfSpringEvent}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see SxzhongfSpringEvent
 * @see SxzhongfEventListener
 * @since 2020/7/26
 **/
public class CustomEventDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    // 第三种方式发布 依赖注入 ApplicationEventPublisher
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    // 第四种发布方式 依赖注入 ApplicationContext
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        applicationContext.publishEvent(new SxzhongfSpringEvent("The event published from applicationContext"));
        applicationEventPublisher.publishEvent(new SxzhongfSpringEvent("The event published from applicationEventPublisher"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CustomEventDemo.class);
        context.addApplicationListener(new SxzhongfEventListener());
        context.refresh();
        context.publishEvent(new SxzhongfSpringEvent(Person.createPerson()));
        context.close();
    }

    /**
     * 优先级第二
     *
     * @param applicationContext
     * @throws BeansException
     * @see ApplicationContextAware
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(
                new SxzhongfSpringEvent(
                        "The event published from setApplicationContext")
        );
    }

    /**
     * 优先级最高，因为在
     * ApplicationContextAwareProcessor#invokeAwareInterfaces(java.lang.Object)
     * 方法执行时，ApplicationEventPublisherAware 在 ApplicationContextAware 之前
     *
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(
                new SxzhongfSpringEvent(
                        "The event published from setApplicationEventPublisher")
        );
    }
}
