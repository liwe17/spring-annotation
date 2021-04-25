package com.weiliai.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author: Doug Li
 * @Date 2021/4/25
 * @Describe: 自定义BeanDefinitionRegistry后置处理器
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    //Bean信息的保存中心,beanFactory根据BeanDefinitionRegistry创建bean对象
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.printf("MyBeanDefinitionRegistryPostProcessor...bean的数量:[%d] \r\n",registry.getBeanDefinitionCount());
        final RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
//        final BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class);
        registry.registerBeanDefinition("hello",beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.printf("MyBeanDefinitionRegistryPostProcessor...bean的数量:[%d] \r\n",beanFactory.getBeanDefinitionCount());
    }
}
