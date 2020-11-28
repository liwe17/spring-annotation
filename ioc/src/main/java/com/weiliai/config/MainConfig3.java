package com.weiliai.config;

import com.weiliai.pojo.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe: bean的生命周期
 *  bean创建---初始化---销毁
 *  容器管理bean的生命周期:
 *  我们可以自定义初始化和销毁方法:容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁
 *  构造(对象创建)
 *      单实例:在容器启动的时候创建对象.
 *      多实例:在每次获取的时候创建对象
 *
 *   BeanPostProcessor.postProcessBeforeInitialization:在初始化之前
 *  初始化:
 *      对象创建完成,并赋值好,调用初始化方法.
 *  BeanPostProcessor.postProcessAfterInitialization:在初始化之后
 *
 *  销毁:
 *      单实例:容器关闭的时候
 *      多实例:容器不会管理这个bean,容器不会调用销毁方法
 *
 *  遍历容器中所有BeanPostProcessor:挨个执行beforeInitalization.一旦返回null,跳出for循环,不在执行后面的BeanPostProcessor.
 *  postProcessBeforeInitialization.
 *
 * --org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
 *  populateBean(beanName, mbd, instanceWrapper);//给bean进行属性赋值
 *  -- AbstractAutowireCapableBeanFactory.initializeBean(beanName, exposedObject, mbd);
 *  {
 *      --org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
 *      applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *      invokeInitMethods(beanName, wrappedBean, mbd);//执行自定义初始化
 *      applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *  }
 *
 * spring底层对BeanPostProcessor的使用
 *      bean赋值,注入其他组件,@Autowired,生命周期注解功能,@Async,xxxBeanPostProccessor
 *
 *
 *
 *  1>指定初始化和销毁方法(car):
 *      指定init-method=""和destroy-method="";
 *  2>通过让bean实现接口(cat)
 *      InitializingBean(定义初始化逻辑)
 *      DisposableBean(定义销毁逻辑)
 *  3>可以使用JSR250(Dog)
 *      @PostConstruct,在bean创建完成并且属性赋值完成,来执行初始化方法
 *      @PreDestory,在容器销bean之前,通知完成清理工作.
 *  4>BeanPostProcessor,bean的后置处理器
 *      在bean初始化前后完成一些处理工作
 *      postProcessBeforeInitialization:在初始化之前
 *      postProcessAfterInitialization:在初始化之后
 *
 */
@Configuration
@ComponentScan({"com.weiliai.pojo", "com.weiliai.beanpostprocessor"})
public class MainConfig3 {

    @Bean(initMethod ="init",destroyMethod = "destory")
    //@Scope("prototype")
    public Car car(){
        return new Car();
    }

}
