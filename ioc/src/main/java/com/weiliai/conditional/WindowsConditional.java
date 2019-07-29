package com.weiliai.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:判断是否是windows系统
 */
public class WindowsConditional implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        //System.out.println("Windows: "+property);
        return property.contains("Windows");
    }
}
