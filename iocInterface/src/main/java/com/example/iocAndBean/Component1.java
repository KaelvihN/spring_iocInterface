package com.example.iocAndBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : KaelvihN
 * @date : 2023/8/3 13:21
 */
@Component
public class Component1 {
    private static final Logger log = LoggerFactory.getLogger(Component1.class);

    /**
     * 事件监听
     */
    @EventListener
    public void lister(UserRegisteredEvent event) {
        log.info("用户监听:{}",event);
    }

}
