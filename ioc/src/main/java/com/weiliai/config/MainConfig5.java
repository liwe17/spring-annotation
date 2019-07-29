package com.weiliai.config;

import com.weiliai.dao.BookDao;
import com.weiliai.pojo.Color;
import com.weiliai.pojo.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:自动装配
 *  Spring利用依赖注入(DI),完成IOC容器中各个组件的依赖关系赋值
 *  1.@Autowire 自动注入
 *      1>默认优先按照类型去容器中找对应的组件:applicationContext.getBean(BookService.class);
 *      2>如果找到多个相同类型的组件,再将属性的名称作为组件id去容器中查找.applicationContext.getBean("bookService")
 *      3>@Qualifier("bookDao"),使用@Qualifier指定需要装配的组件ID,而不是使用属性名
 *      4>自动装配默认一定要将属性赋值好,没有就会报错
 *          可以使用@Autowire(required=false)
 *      5>@Primary:让Spirng进行自动装配的时候,默认使用首选的bean
 *          也可以使用@Qualifier指定需要装配的bean的名字
 *      BookService{
 *          @Autowire
 *          BookDao bookDao;
 *      }
 *  2.Spring还支持@Resource(JSR250)和@Inject(JSR330)[JAVA规范注解]
 *         @Resource
 *              可以和@Autowired一样的装配功能,默认是按照组件名进行装配的,
 *              不支持@Primary以及@Autowire(required=false)
 *         @Inject
 *              导入依赖javax.inject包,和Autowired功能一样.
 *              不支持@Autowire(required=false)
 * @Autowired:Spring定义的,@Resource,@Inject都是Java规范.
 *
 * AutowiredAnnotationBeanPostProcessor:解析完成自动装配.
 *
 * 3.@Aurowired:构造器,参数,方法,属性:都是从容器中获取参数组件的值.
 *      1>标注在方法位置:@Bean+方法参数:参数从容器中获取
 *      2>标在构造器上:如果组件只有一个有参构造器,这个有参构造器@Autowired可以省略,参数位置的组件还是可以自动的从容器中获取
 *      3>放在参数位置
 *
 *  4.自定义组件想要使用Spring容器底层的一些组件(ApplicationContext,BeanFacroty,xxx)
 *      自定义组件实现xxxAware,在创建对象的时候,会调用接口规定的方法注入相关组件:Aware
 *      把Spring底层一些组件注入到自定义的Bean中;
 *      xxxAware:功能使用xxxProcessor
 *          ApplicationContextAware--->ApplicationContextAwareProcessor
 */
@Configuration
@ComponentScan({"com.weiliai.service", "com.weiliai.controller","com.weiliai.dao","com.weiliai.pojo"})
public class MainConfig5 {

    @Bean
    @Primary
    public BookDao bookDao2(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    //@Bean标注的方法创建对象的时候,方法参数的值直接从容器中获取
    @Bean
    public Color color (Employee employee){
        Color color = new Color();
        color.setEmployee(employee);
        return color;
    }
}
