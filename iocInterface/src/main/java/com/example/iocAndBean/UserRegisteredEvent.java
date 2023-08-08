package com.example.iocAndBean;

import org.springframework.context.ApplicationEvent;

/**
 * @author : KaelvihN
 * @date : 2023/8/3 13:49
 */
public class UserRegisteredEvent extends ApplicationEvent {

    /**
     *
     * @param source 事件源
     */
    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
