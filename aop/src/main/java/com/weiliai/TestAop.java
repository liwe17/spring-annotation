package com.weiliai;

import com.weiliai.config.MainConfig1;
import com.weiliai.test.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: Doug Li
 * @Date: 2019/7/23
 * @Describe:
 */
public class TestAop {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        /*
        //没有使用代理对象,无法打印日志
        MathCalculator mathCalculator1 = new MathCalculator();
        mathCalculator1.div(1,2);
        */
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(1,1);
    }

}
