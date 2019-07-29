package com.weiliai.pojo;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    public Color getObject() throws Exception {
        System.out.println("factoryBean...Color...");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    //是否单例:ture,单例;false:多实例
    public boolean isSingleton() {
        return true;
    }
}
