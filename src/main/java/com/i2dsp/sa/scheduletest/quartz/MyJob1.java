package com.i2dsp.sa.scheduletest.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * quartz在使用中有两个关键概念,一个是JobDetail(要做的事情), 另一个是触发器(什么时候做)
 * <p>
 * 先定义Job
 * 1 直接定义一个Bean
 */
@Component
public class MyJob1 {

    public void sayHello() {
        System.out.println("MyJob1-> " + new Date());
    }

}
