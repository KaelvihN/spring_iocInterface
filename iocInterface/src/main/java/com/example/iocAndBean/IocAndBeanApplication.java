package com.example.iocAndBean;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * @author : KaelvihN
 * @date : 2023/8/3 12:14
 */

@SpringBootApplication
public class IocAndBeanApplication {

    public static void main(String[] args) throws Exception {
        /**
         * BeanFactory是
         * --Application的父接口
         * --Spring的核心容器，主要的Application都实现BeanFactory的功能
         */
        /**
         * BeanFactory只提供了getBean
         * 依赖注入，控制反转，Bean的生命周期都由BeanFactory的实现类提供
         */
        /**
         * content 就是一个容器
         */
        ConfigurableApplicationContext context =
                SpringApplication.run(IocAndBeanApplication.class, args);
        /**
         * SingletonObject(Map)存放单例Bean
         * key 是Bean的名字
         * value 是Bean的对象实例
         */
        Field singletonObjects =
                DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> singleObjectMap = (Map<String, Object>) singletonObjects.get(beanFactory);
        singleObjectMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("component"))
                .forEach(entry -> System.out.println(entry.getKey() + "=" + entry.getValue()));
        /**
         * Application 的扩展
         * 父接口 MessageSource 国际化
         */
        System.out.println(context.getMessage("hi", null, Locale.JAPAN));
        System.out.println(context.getMessage("hi", null, Locale.SIMPLIFIED_CHINESE));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
        /**
         * Application 的扩展
         * 父接口 ResourcePatternResolver 资源查询
         */
        Resource[] resources = context.getResources("classpath:*.properties");
        for (Resource resource : resources) {
            System.out.println("resource = " + resource);
        }
        /**
         * Application 的扩展
         * 父接口 EnvironmentCapable 获取环境变量
         */
        System.out.println(context.getEnvironment().getProperty("JAVA_HOME"));
        System.out.println(context.getEnvironment().getProperty("server.port"));
    }
}