package com.weiliai.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: Doug Li
 * @Date 2021/4/25
 * @Describe: 自定义BeanFactory后置处理器
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory");
        final int count = beanFactory.getBeanDefinitionCount();
        final String[] names = beanFactory.getBeanDefinitionNames();
        System.out.printf("当前BeanFactory包含[%d]个Bean \r\n",count);
        System.out.println("当前BeanFactory包含beanName: "+ Arrays.toString(names));
    }
}
