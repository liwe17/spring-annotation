package com.weiliai.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
@Component
public class Red implements ApplicationContextAware, BeanNameAware , EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的IOC容器"+applicationContext);
        this.applicationContext=applicationContext;
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字"+name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println(resolver.resolveStringValue("你好${os.name} 我是#{20*18}"));
    }
}
