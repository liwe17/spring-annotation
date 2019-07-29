package com.weiliai.importselector;

import com.weiliai.pojo.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    //AnnotationMetadata:当前类的注解信息
    //BeanDefinitionRegistry:BeanDefinition:注册类
    //  把所有需要添加到容器中的bean:BeanDefinitionRegistry.registerBeanDefinition手工注册
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean beanDefinition = registry.containsBeanDefinition("com.weiliai.pojo.Red");
        boolean beanDefinition2 = registry.containsBeanDefinition("com.weiliai.pojo.Blue");
        if(beanDefinition && beanDefinition2){
            //注册bean名
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow",rootBeanDefinition);
        }
    }
}
