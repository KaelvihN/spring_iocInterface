package com.example.iocAndBean;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author : KaelvihN
 * @date : 2023/8/7 23:02
 */

public class MessageResourceTest {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("messageSource", MessageSource.class, () -> {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            //设置编码
            messageSource.setDefaultEncoding("UTF-8");
            //设置国家化资源的 BaseName
            messageSource.setBasename("message");
            return messageSource;
        });

        context.refresh();
        /**
         * Application 的扩展
         * 父接口 MessageSource 国际化
         */
        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));
        System.out.println(context.getMessage("hi", null, Locale.SIMPLIFIED_CHINESE));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
    }
}
