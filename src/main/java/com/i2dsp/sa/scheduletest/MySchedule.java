package com.i2dsp.sa.scheduletest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务类
 */
//@Component
public class MySchedule {

    /**
     * 使用@Scheduled注解开启一个定时任务
     * <p>
     * fixedRate表示任务执行之间的时间间隔.具体是两次任务的开始时间间隔,有可能第二次开始第一次还没结束
     * <p>
     * fixedDelay表示执行任务之间的时间间隔.本次结束到下次开始之间的时间间隔
     * <p>
     * initialDelay表示首次任务启动的延迟时间
     * <p>
     * 所有时间单位都是毫秒
     */
    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate--> " + new Date());
    }

    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay--> " + new Date());
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay--> " + new Date());
    }

    // 也支持cron表达式

    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        System.out.println(new Date());
    }
}
