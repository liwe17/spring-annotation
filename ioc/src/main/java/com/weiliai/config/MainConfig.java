package com.weiliai.config;

import com.weiliai.pojo.Person;
import com.weiliai.service.BookService;
import com.weiliai.typefilter.MyTypeFilter;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe: 配置类==配置文件
 */
@Configuration  //告诉spring这是一个配置类
@ComponentScan(value = "com.weiliai",includeFilters ={
        @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes={MyTypeFilter.class})
},useDefaultFilters = false)
//ComponentScan value:指定要扫描的包
//excludeFilters = Filter[] 指定扫描的时候按照什么规则剔除那些组件
//includeFilters = Filter[] 指定扫描的时候只需要包含那些组件,需要指定useDefaultFilters = false
//FilterType.ANNOTATION,按照注解
//FilterType.ASSIGNABLE_TYPE 按照给定的类型
//FilterType.ASPECTJ 按照ASPECTJ表达式
//FilterType.REGEX 按照正则表达式
//FilterType.CUSTOM 按照自定义
//@ComponentScans 包含多个ComponentScan
public class MainConfig {

    //给容器中注册一个Bean,类型为返回值类型,默认是用方法名做ID
    //singleton(单实例,默认,容器启动会自动创建)
    //  懒加载(单实例):容器启动不会创建,第一次获取时候进行创建,完成初始化
    //prototype(多实例,容器启动不会创建对象,每次获取的时候才会调用)
    //request(一次请求创建一个实例)
    //session(同一个session创建一个实例);
    @Bean
    @Scope("prototype")
    public Person person(){
        System.out.println("添加多实例person");
        return new Person("liwei",29);
    }

    @Bean("person1")
    @Lazy
    public Person person1(){
        System.out.println("添加单实例person");
        return new Person("lidog",28);
    }
}