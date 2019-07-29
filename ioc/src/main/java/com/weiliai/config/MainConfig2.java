package com.weiliai.config;

import com.weiliai.conditional.LinuxConditional;
import com.weiliai.conditional.WindowsConditional;
import com.weiliai.importselector.MyImportBeanDefinitionRegistrar;
import com.weiliai.importselector.MyImportSelector;
import com.weiliai.pojo.Color;
import com.weiliai.pojo.ColorFactoryBean;
import com.weiliai.pojo.Person;
import com.weiliai.pojo.Red;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:配置类==配置文件
 */
@Configuration  //告诉spring这是一个配置类
@Conditional({WindowsConditional.class}) //给类添加判断注解
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) //id默认全类名
public class MainConfig2 {

    /**
     * @Conditional({Condition}) 按照一定的条件进行判断,满足条件给容器注册bean
     * 如果是windows系统注册bill,linux系统注册linus
     */
    @Bean("bill")
    @Conditional({WindowsConditional.class})
    public Person person02(){
        return new Person("bill",50);
    }

    @Bean("linus")
    @Conditional({LinuxConditional.class})
    public Person person03(){
        return new Person("linus",50);
    }

    /**
     * 给容器中注册组件
     *  1.包扫描+组件注解(@ComponentScan,@Controller,@Service,@Repository,@Component)
     *  2.@Bean[导入第三方包里面的组件]
     *  3.@Import[快速给容器导入组件]
     *      1>@Import(要导入到容器的组件):容器会自动注册这个bean,id默认是全类名
     *      2>ImportSelector:返回需要导入的组件全类名数组
     *      3>ImportBeanDefinitionRegistrar:手工注册bean
     *  4.使用spring提供的FactoryBean(工厂Bean)
     *      1>默认获取到的是工厂bean调用的getObject创建的对象
     *      2>要获取到工厂bean本身,我们需要给id前面加&
     *          &colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}