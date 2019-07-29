package com.weiliai.pojo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Dog(){

    }

    //在对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("Dog...PostConstruct...");
    }

    //容器移除对象之前调用
    @PreDestroy
    public void destory(){
        System.out.println("Dog...PreDestroy...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


}
