package com.weiliai.pojo;

import org.springframework.stereotype.Component;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
//默认加在ioc容器中的组件,容器启动会调用无参构造器创建对象,再进行初始化赋值等操作.
@Component
public class Boss {

    private Employee employee;

    //@Autowired
    //构造器要用组件,都是从容器中获取
    public Boss(/*@Autowired*/Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    //@Autowired
    //标注在方法上,spring容器创建当前对象,就会调用方法,完成赋值.
    //方法使用的参数,自定义类型的值从ioc容器中获取
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "employee=" + employee +
                '}';
    }
}
