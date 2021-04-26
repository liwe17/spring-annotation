package com.weiliai.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Doug Li
 * @Date 2021/4/26
 * @Describe: 基于注解实现监听
 */
@Component
public class MyEventListener {

    @EventListener
    public void listen(ApplicationEvent event){
        System.out.println("MyEventListener...监听事件: "+event);
    }

}
