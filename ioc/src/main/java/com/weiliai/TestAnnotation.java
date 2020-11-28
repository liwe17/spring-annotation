package com.weiliai;

import com.weiliai.config.*;
import com.weiliai.dao.BookDao;
import com.weiliai.pojo.Boss;
import com.weiliai.pojo.Color;
import com.weiliai.pojo.Employee;
import com.weiliai.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe: 测试类
 */
public class TestAnnotation {

    @Test   //测试注解
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean("person",Person.class);
        System.out.println(person);

        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    //查看容器中的bean
    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    //查看容器中的bean
    @Test
    public void test03(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }


    //测试import注解创建bean
    @Test
    public void test04import(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Object colorFacrotyBean = applicationContext.getBean("colorFactoryBean");
        System.out.println("--->bean:"+colorFacrotyBean.getClass());
        Object colorFacrotyBean1 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("--->&bean:"+colorFacrotyBean1.getClass());
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    //测试bean生命周期
    @Test
    public void test05InitAndDestory(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig3.class);
        System.out.println(applicationContext);
        applicationContext.getBean("car");
        //关闭容器
        applicationContext.close();
    }

    //测试获取值的方法
    @Test
    public void test06Value(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig4.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(applicationContext.getBean("greenPerson"));
        System.out.println(applicationContext.getEnvironment().getProperty("nickName"));
    }

    //测试自动注入
    @Test
    public void test06Autowire(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig5.class);
        System.out.println(applicationContext.getBean(BookDao.class).getLable());
        System.out.println(((BookDao) applicationContext.getBean("bookDao")).getLable());

        System.out.println(applicationContext.getBean(Boss.class));
        System.out.println(applicationContext.getBean(Employee.class));

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color.getEmployee());
    }

    @Test
    public void test07Profile(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig6.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.err::println);
    }

    @Test
    public void test08Profile(){
        //1.创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2.设置要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        //3.注册主配置类
        applicationContext.register(MainConfig6.class);
        //4.启动刷新容器
        applicationContext.refresh();
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.err::println);
    }
}
