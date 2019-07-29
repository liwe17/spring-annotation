package com.weiliai.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
//名字默认类名首字母小写
@Repository
public class BookDao {

    private String lable="1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
