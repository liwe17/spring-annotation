package com.weiliai.service;

import com.weiliai.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

}
