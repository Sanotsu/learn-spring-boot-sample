package com.i2dsp.sa.core;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


/**
 * @ControllerAdvice是一个增强的Controller,可以实现 1 全局异常处理
 * 2 全局数据绑定
 * 3 全局数据预处理
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    /**
     * @param e
     * @ExceptionHandler注解用来指明异常的处理类型
     */
//    @ExceptionHandler(Exception.class)
//    public void customException(Exception e) {
//
//        System.out.println("eeeeeeeeeeeeeeeeeeeeeeee");
//        System.out.println(e);
//    }

    /**
     * 使用@ModelAttribute注解标记该方法的返回数据是一个全局数据
     * 默认情况下,这个全局数据的key就是返回的变量名,value就是返回值
     * 可以通过@ModelAttribute注解的name属性重新指定key
     *
     * 之后任意一个Controller接口,都能取到这个定义的数据
     *
     * @return
     */
    @ModelAttribute(name = "md")
    public Map<String, Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }

    /**
     * // @InitBinder("b")注解表示该方法用来处理和Book相关的参数,
     * 在参数添加一个b前缀,即请求参数要有b前缀
     * (也就是在发送请求的时候,不同对象的数据加了前缀,同名的参数可以区分)
     *
     * @param binder
     */
    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }

    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }
}
