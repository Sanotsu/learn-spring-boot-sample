package com.i2dsp.sa.controller;

import com.i2dsp.sa.utils.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author david
 */
@RestController
@ResponseBody
@RequestMapping("/ss")
public class LoginSecurityController {

    /**
     * 在spring security中配置的,没有通过验证之后返回的页面信息接口
     */
    @GetMapping("/withoutLogin")
    public ResponseBean login() {
        return ResponseBean.error("111111111111111111还没登录,请先登录11111111111111111111");
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
