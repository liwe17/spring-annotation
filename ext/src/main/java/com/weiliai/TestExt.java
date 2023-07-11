package com.weiliai;

import com.weiliai.config.ExtConfig;
import com.weiliai.ext.Red;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Doug Li
 * @Date 2021/4/25
 * @Describe: 扩展部分测试
 */
public class TestExt {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
//        applicationContext.publishEvent(new ApplicationEvent("发布一个事件") {
//        });
        Red red = applicationContext.getBean(Red.class);
        applicationContext.close();
    }
}
