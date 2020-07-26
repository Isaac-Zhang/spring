package com.sxzhongf.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 演示 {@link ApplicationListener}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see EnableAsync
 * @see Async
 * @see Order
 * @see ApplicationEvent
 * @see ApplicationListener
 * @since 2020/7/26
 **/
// 激活异步执行
@EnableAsync
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将当前的启动引导类作为 Configuration 注册到 ApplicationContext
        applicationContext.register(ApplicationListenerDemo.class);

        // 方法一：基于接口的事件添加 ApplicationEvent
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                println("基于接口 ApplicationListener 的事件:" + event);
            }
        });
        applicationContext.register(CustomApplicationListener.class);
        applicationContext.register(CustomRefreshedApplicationListener.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void println(Object printable) {
        System.out.printf("[Thread Name : %s], source : %s\n", Thread.currentThread().getName(), printable);
    }

    /**
     * 监听所有的 ApplicationEvent 类型的事件
     *
     * @param applicationEvent Spring 应用上下文事件源
     */
    @EventListener
    public void onAllApplicationContextEvents(ApplicationEvent applicationEvent) {
        println(applicationEvent);
    }

    /**
     * 仅监听 ContextRefreshedEvent 事件
     *
     * @param contextRefreshedEvent Spring 上下文刷新事件
     */
    @EventListener
    public void onRefreshedEvent(ContextRefreshedEvent contextRefreshedEvent) {
        println("ContextRefreshedEvent");
    }

    /**
     * 仅异步监听  ContextRefreshedEvent 事件
     *
     * @param contextRefreshedEvent Spring 上下文刷新事件
     */
    @EventListener
    @Async
    public void onRefreshedAsyncEvent(ContextRefreshedEvent contextRefreshedEvent) {
        println("Async ContextRefreshedEvent");
    }

    @EventListener
    @Order(Integer.MAX_VALUE - 2)
    public void onRefreshedSortedEvent2(ContextRefreshedEvent contextRefreshedEvent) {
        println("onRefreshedSortedEvent2");
    }

    @EventListener
    @Order(Integer.MAX_VALUE - 1)
    public void onRefreshedSortedEvent1(ContextRefreshedEvent contextRefreshedEvent) {
        println("onRefreshedSortedEvent1");
    }

    static class CustomApplicationListener implements ApplicationListener {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            println("CustomApplicationListener:" + event);
        }
    }

    static class CustomRefreshedApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("CustomRefreshedApplicationListener:" + event);
        }
    }
}
