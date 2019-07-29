package com.weiliai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:赋值
 *  使用@Value赋值
 *  1>基本数值
 *  2>可以写SpEL,#{}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreenPerson {

    @Value("张三")
    private String name;

    @Value("#{20-2}")
    private int age ;

    @Value("${nickName}")
    private String nickName;


}
