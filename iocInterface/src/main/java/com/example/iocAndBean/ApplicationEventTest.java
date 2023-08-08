package com.example.iocAndBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : KaelvihN
 * @date : 2023/8/7 23:28
 */
@SpringBootApplication
public class ApplicationEventTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = SpringApplication.run(ApplicationEventTest.class, args);

        context.getBean(Component2.class).register();
        context.close();
    }
}
