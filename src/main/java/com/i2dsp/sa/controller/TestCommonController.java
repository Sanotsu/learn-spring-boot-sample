package com.i2dsp.sa.controller;

import com.i2dsp.sa.entity.Author;
import com.i2dsp.sa.entity.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestCommonController {

    @GetMapping("/hello")
    public String hello(Model model) {

        Map<String, Object> map = model.asMap(); // 取得@ControllerAdvice中定义的全局数据
        System.out.println(map);
        int i = 1 / 0; // 测试错误拦截

        return "this is test";
    }

    /**
     * 因为两个实体类都有一个name属性,从前端传递时无法区分
     * 可以通过@ControllerAdvice的全局预处理解决
     *
     * @param book
     * @param author
     */
    @PostMapping("/book")
//    public void addBook(Book book, Author author) {
    // 接口变量取别名 (全局数据预处理)
    public String addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {

        System.out.println("book");
        System.out.println(book.getName());
        System.out.println(author.getName());

        return "ok";

    }

}
