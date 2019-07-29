package com.weiliai.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author: Doug Li
 * @Date: 2019/7/23
 * @Describe:
 *  Profile
 *      Spring为我们提供的可以根据当前环境,动态的激活和切换一些列组件的功能
 *  开发环境,测试环境,生产环境
 *  数据源(/A),(/B),(/C)
 * @Profile:指定组件在哪个环境的情况下才能注册到容器中,不指定任何环境都能注册这个组件
 * 1>加了环境标识的bean,只有这个环境被激活的时候才能注册到容器.默认是default
 * 2>写在配置类上,只有在指定的环境的时候,整个配置类里面的所有配置才嫩生效.
 * 3>没有标注环境标识的bean在任何环境下都会加载.
 * 激活环境
 *  1.命令行动态参数:在JVM虚拟机参数位置加载:-Dspring.profiles.active=test
 *  2.代码的方式:激活某种环境pcom.weiliai.ioc.TestAnnotation.ublic void test08Profile(){
 */
@Profile("test")
@Configuration
@PropertySource("classpath:/jdbc.properties")
public class MainConfig6 implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver resolver;

    private String driverClass;

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("db.password")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("db.password")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("db.password")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prod");
        return dataSource;
    }


    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
       this.resolver = resolver;
       driverClass=resolver.resolveStringValue("${db.driverClass}");
    }
}
