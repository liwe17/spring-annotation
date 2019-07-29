package com.weiliai.config;

import com.weiliai.test.LogAspects;
import com.weiliai.test.MathCalulator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: Doug Li
 * @Date: 2019/7/23
 * @Describe:
 * AOP,[动态代理]
 *  指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 *
 *  1.导入AOP模块:Spring AOP(spring-aspects)
 *  2.定义一个业务逻辑类(MathCaluator),在业务逻辑运行的时候将日志进行打印(方法之前,方法之后,方法出现异常等)
 *  3.定义一个日志切面类(LogAspects),切面类里面方法需要动态感知MathCalculator,div运行到哪里然后执行.
 *        通知方法:
 *          前置通知(@Before):logStart,在目标方法(div)运行之前运行
 *          后置通知(@After):logEnd,在目标方法(div)运行结束之后(无论是否正常结束)运行
 *          返回通知(@AfterReturn):logReturn,在目标方法(div)正常返回之后运行
 *          异常通知(@AfterThrowing):logException,在目标方法(div)运行出现异常后运行
 *          环绕通知(@Around):动态代理,手动推进目标方法运行(joinPoint.proceed())
 *   4.给切面类的目标方法标注何时运行
 *   5.将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 *   6.必须告诉spring哪个类是切面类(给切面类加一个注解@Aspect)
 *   7.给配置类中加@EnableAspectJAutoProxy[开启基于注解aop模式]
 *      在Spring中很多@EnableXXX;
 *
 *  三步:
 *    1>将业务逻辑组件和切面类都加入到容器中,告诉spring哪个是切面类(@Aspect)
 *    2>在切面类上的每一个通知方法上都标注通知注解,告诉spring何时何地的运行(切入点表达式)
 *    3>开启基于注解的aop模式;@EnableAspectJAutoPoxy
 *
 *  AOP原理:[给容器中注册了什么组件,这个组件什么时候工作,这个组件的功能是什么]
 *      @EnableAspectJAutoProxy:
 *  1.@EnableAspectJAutoProxy是什么?
 *      @Import(AspectJAutoProxyRegistrar.class),给容器中导入AspectJAutoProxyRegistrar
 *          利用AspectJAutoProxyRegistrar自定义给容器中注册bean:BeanDefinition
 *          internalAutoProxyCreator=org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
 *          给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 * 2.AnnotationAwareAspectJAutoProxyCreator:
 *      AnnotationAwareAspectJAutoProxyCreator
 *          ->AspectJAwareAdvisorAutoProxyCreator
 *              ->AbstractAdvisorAutoProxyCreator
 *                  ->AbstractAutoProxyCreator
 *                      ->implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      关注后置处理器(在bean初始化完成前后做事情),自动装配BeanFactory
 *
 *
 * AbstractAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)
 * AbstractAutoProxyCreator.后置处理器的逻辑
 *
 * AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)->initBeanFactory((ConfigurableListableBeanFactory) beanFactory);
 *
 * AnnotationAwareAspectJAutoProxyCreator.initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *流程:
 *  1.传入配置类,创建IOC容器
 *  2.注册配置类,调用refresh(),刷新容器
 *  3.registerBeanPostProcessors(beanFactory);注册bean的后置处理器方便拦截bean的创建
 *      1>先获取ioc容器已经定义了的需要创建对象的所有beanPostProcessor
 *      2>给容器中加别的BeanPostProcessor
 *      3>优先注册实现了PriorityOrdered接口的BeanPostProcessor;
 *      4>在给容器注册实现了Ordered接口的BeanPostProcessor;
 *      5>注册没实现优先级接口的BeanPostProcessor;
 *      6>注册BeanPostProcessor,实际上就是创建BeanPostProcessor对象,保存到容器中.
 *          创建internalAutoProxyCreator的BeanPostProceesor
 *          1>创建Bean的实例
 *          2>populateBean,给bean的各种属性赋值
 *          3>initializeBean,初始化bean
 *              1>invokeAwareMethods(),处理Aware接口的方法回调
 *              2>applyBeanPostProcessorsBeforeInitialization(),应用后置处理器postProcessorBeforeInitialization()
 *              3>invokeInitMethods(),执行自定义的初始化方法
 *              4>applyBeanPostProcessorsBeforeInitialization(),应用后置处理器pstProcessorAfterInitialization()
 *          4>BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
 *       7>把BeanPostProcessor注册到BeanFactory中:
 *          beanFactory.addBeanPostProcessor(postProcessor)
 * ==========以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程===============
 *          AnnotationAwareAspectJAutoProxyCreator->InstantiationAwareBeanPostProcessor
 * 4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory的初始化工作,创建剩下的单实例
 *    1>遍历获取容器中所有的Bean,依次创建对象getBean(beanName);
 *      getBean()-doGetBean()->getSingleton()->
 *    2>创建Bean
 *      [AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截,InstantiationAwareBeanPostProcessor,会调用
 *      postProcessorBeforeInstantiation方法]
 *      1>先从缓存中获取当前bean,如果能获取到,说明bean是之前被创建过的,直接使用,否则在创建
 *          只要创建好的bean都会被缓存起来
 *      2>createBean();创建bean
 *           [BeanPostProcessor是在Bean对象创建完成初始化前后调用的]
 *           [InstantiationAwareBeanPostProcessor是在bean实例创建对象之前先尝试用后置处理器返回对象]
 *          1>resolveBeforeInstantiation(beanName,mbdToUse);解析resolveBeforeInstantiation
 *              希望后置处理器在此能返回一个代理对象,如果能返回代理对象就使用,如果不能继续
 *              1>后置处理器先尝试返回对象
 *                  bean = applyBeanPostProcessorBeforeInstantiation(targetType,beanName)
 *                      拿到所有后置处理器,如果是InstantiationAwareBeanPostProcessor,
 *                      就执行PostProcessorBeforeInstantiation
 *                  if(bean !=null){
 *                      bean = applyBeanPostProcessorAfterInstantiation(bean,beanName)
 *                  }
 *          2>doCreateBean(beanName,mbdTouse,args);真正的去创建一个bean实例和3.6流程一样.
 *
 *
 * AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用:
 *  1>每一个bean创建之前,调用postProcessorBeforeInstantiation
 *      关心MathCalculator和LogAspect的创建
 *      1>判断当前bean是否在adviseBeans中(保存了所有需要增强bean)
 *      2>判断当前bean是否是基础类的Advice,Pointcut,Advisor,AopInfrastructureBean,
 *        或者是否是切面(@Aspect)
 *      3>是否需要跳过
 *         1>获取候选的增强器(切面里面的通知方法)[List<Advisor> candidateAdvisors]
 *           每一个封装的通知方法的增强器是InstantiationModelAwarePointAdvisor.
 *           判断每一个增强器是否是AspectJPointcutAdvisor类型的,返回true
 *         2>永远返回false
 *  2>创建对象
 *  postProcessorAfterInstantiation:
 *      return wrapIfNecessary(bean,beanName,cacheKey);//包装如果需要的情况下
 *      1>获取当前bean的所有增强器(通知方法)
 *          找到能在当前bean使用的增强器(找那些通知方法是需要)
 *
 *
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfig1 {

    //业务逻辑类加入容器中
    @Bean
    public MathCalulator mathCalulator(){
        return new MathCalulator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}
