package com.weiliai.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("cat...contructor...");
    }

    public void destroy() throws Exception {
        System.out.println("cat...destroy...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("cat...afterPropertiesSet...");
    }
}
