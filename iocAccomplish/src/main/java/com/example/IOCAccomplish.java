package com.example;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigUtils;

import java.util.Arrays;

/**
 * @author : KaelvihN
 * @date : 2023/8/8 0:28
 */


public class IOCAccomplish {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //bean的定义(class,scope,init,destroy)
        /**
         *创建一个BeanDefinition，这是设scope为单例模式
         *设置bean的类型为Config.Class
         */
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class)
                .setScope("singleton")
                .getBeanDefinition();
        /**
         * 将 BeanDefinition 注册到bean工厂
         * registerBeanDefinition 是将 BeanDefinition 添加到一个 ConcurrentHashMap 中
         * key为 BeanDefinition 的名字，value为 BeanDefinition
         */
        beanFactory.registerBeanDefinition("config", beanDefinition);
        /**
         * 往BeanFactory中加入一些常用的Bean后置处理器
         */
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);


        /**
         * 使用BeanFactory的后置处理器
         */
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class)
                .values()
                .stream()
                .forEach(beanFactoryPostProcessor -> {
                    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
                });

        /**
         * 遍历所有的Bean
         */
        Arrays.stream(beanFactory.getBeanDefinitionNames())
                .forEach(System.out::println);

        System.out.println("------------------------------------------------------------");

        Bean2 bean2 = beanFactory.getBean(Bean1.class).getBean2();
        System.out.println("bean2 = " + bean2);
    }
}
