package com.weiliai.config;

import com.weiliai.pojo.GreenPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe: 测试取值
 */
@Configuration
//用@PropertySource读取外部配置文件的K/V保存到运行的环境变量中;加载完外部配置文件以后使用${}读取配置文件的值.
@PropertySource(value={"classpath:greenperson.properties"})
public class MainConfig4 {

    @Bean
    public GreenPerson greenPerson(){
        return new GreenPerson();
    }


}
