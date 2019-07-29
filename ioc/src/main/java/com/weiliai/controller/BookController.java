package com.weiliai.controller;

import com.weiliai.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
