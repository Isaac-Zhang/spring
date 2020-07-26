package com.sxzhongf.spring.event.custom;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see ApplicationEvent
 * @since 2020/7/26
 **/
public class SxzhongfSpringEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SxzhongfSpringEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public String getMessage() {
        return getSource().toString();
    }
}
