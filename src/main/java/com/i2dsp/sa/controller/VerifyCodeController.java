package com.i2dsp.sa.controller;

import com.i2dsp.sa.utils.VerifyCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class VerifyCodeController {
    @GetMapping("/vercode")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();  // 随机验证码的内容
        HttpSession session = req.getSession();
        session.setAttribute("index_code", text);
        System.out.println(text);
        VerifyCode.output(image, resp.getOutputStream()); // 传回去的是个图片,就可以用个例如<img>标签接收
    }
}