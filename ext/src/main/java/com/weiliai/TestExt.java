package com.weiliai;

import com.weiliai.config.ExtConfig;
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
        applicationContext.close();
    }
}
