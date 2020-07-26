package com.sxzhongf.spring.event.custom;

import org.springframework.context.ApplicationListener;

/**
 * 自定义事件监听器
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/26
 **/
public class SxzhongfEventListener implements ApplicationListener<SxzhongfSpringEvent> {

    @Override
    public void onApplicationEvent(SxzhongfSpringEvent event) {
        System.out.printf("[thread ： %s] 监听到事件:%s\n", Thread.currentThread().getName()
                , event);
    }
}
