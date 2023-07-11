package com.weiliai;

import com.weiliai.config.ExtConfig;
<<<<<<< HEAD
=======
import com.weiliai.ext.Red;
>>>>>>> 12b09ea (关联远程仓库)
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
<<<<<<< HEAD
=======
        Red red = applicationContext.getBean(Red.class);
>>>>>>> 12b09ea (关联远程仓库)
        applicationContext.close();
    }
}
