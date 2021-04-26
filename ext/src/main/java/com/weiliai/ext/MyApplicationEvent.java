package com.weiliai.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Doug Li
 * @Date 2021/4/26
 * @Describe: 自定义事件
 */
@Component
public class MyApplicationEvent implements ApplicationListener<ApplicationEvent> {

    //当容器中发布此事件后,方法触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件:"+event);
    }
}
