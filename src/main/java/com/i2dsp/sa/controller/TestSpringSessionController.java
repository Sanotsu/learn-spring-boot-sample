package com.i2dsp.sa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 配置完之后使用Spring Session,就是使用普通的HttpSession,其他的Session同步到Redis等操作,框架自动完成了
 * <p>
 * 使用右侧maven -> lifecycle ->package打包成jar包,再执行jar包
 * java -jar sa-0.0.1-SNAPSHOT.jar --server.port=8085 和  最前面加上 nohup 关键字则表示关闭终端应用不停止
 * java -jar sa-0.0.1-SNAPSHOT.jar --server.port=8086
 * <p>
 * 使用一个端口8085执行set,另一个端口8086执行get就能看到8085set的数据
 * (再看redis中,有一堆spring session的内容)
 */
@RestController
public class TestSpringSessionController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("set")
    public String set(HttpSession session) {
        session.setAttribute("user", "david");
        return String.valueOf(port);
    }

    @GetMapping("get")
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }

}
