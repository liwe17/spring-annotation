package com.weiliai.config;

import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Doug Li
 * @Date 2020/11/28
 * @Describe: 扩展部分
 *
 *  0.BeanPostProcessor:bean后置处理器,bean创建对象初始化前后进行拦截工作
 *  1.BeanFactoryPostProcessor:beanFactory后置处理器
 *      在BeanFactory标准初始化之后调用,所有的bean定义已经保存加载到beanFactory中,但是bean的实例还未创建.
 *
 *      1>IOC容器创建
 *      2>invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoryPostProcessor;
 *          如何找到所有的BeanFactoryPostProcessor并执行他们的方法
 *          1).直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件,并执行他们的方法
 *             String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);
 *          2).在初始化创建其他组件前执行
 *
 *  2.BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessorBeanDefinitionRegistry(BeanDefinitionRegistry registry)
 *      在所有bean定义信息将要被加载,bean实例还未创建
 *
 *      优先于BeanFactoryPostProcessor执行;
 *      利用BeanDefinitionRegistryPostProcessor给容器额外添加一些组件
 *      1> IOC创建容器
 *      2> refresh()->invokeBeanFactoryPostProcessors(beanFactory);
 *      3> 从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件
 *          1).依次触发所有的postProcessorBeanDefinitionRegistry方法
 *          2).再次触发postProcessorBeanFactory方法
 *      4> 在从容器中找到BeanFactoryPostProcessor组件,然后依次执行postProcessorBeanFactory方法
 *
 */
@ComponentScan("com.weiliai.ext")
public class ExtConfig {

}
