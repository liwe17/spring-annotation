package com.weiliai;

import com.weiliai.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:通过XML方式
 */
public class TestXml {

    @Test   //测试XML
    public void test(){
        ApplicationContext aplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Person person = (Person)aplicationContext.getBean("person");
        System.out.println(person);
    }





}
