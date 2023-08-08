package com.example.iocAndBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

;

/**
 * @author : KaelvihN
 * @date : 2023/8/3 13:21
 */
@Component
public class Component2 {
    private static final Logger log = LoggerFactory.getLogger(Component2.class);

    @Resource
    private ApplicationEventPublisher publisher;

    /**
     * 发布事件
     */
    public void register() {
        log.info("用户注册");
        publisher.publishEvent(new UserRegisteredEvent(this));
    }
}
