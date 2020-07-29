package com.i2dsp.sa.config;

import com.i2dsp.sa.scheduletest.quartz.MyJob2;
import com.i2dsp.sa.service.QuartzTestService;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.Date;

/**
 * 1 JobDetail的配置有两种方式 MethodInvokingJobDetailFactoryBean 和 JobDetailFactoryBean
 * 2 使用 MethodInvokingJobDetailFactoryBean可以在配置目标Bean的名字和目标方法,但这种方式不支持传参
 * 3 使用 JobDetailFactoryBean 可以配置JobDetail,任务类继承自 QuartzJobBean,这种方式支持传参,参数封装在JObDataMap中进行传递
 * 4 Trigger 是指触发器,Quartz中定义了很多触发器,示例中有介绍两种:SimpleTrigger和CronTrigger
 * 5 SimpleTrigger类似于@Scheduled的基本用法,CronTrigger类似@Scheduled中cron表达式的用法
 */
//@Configuration
public class QuartzConfig {

    // MethodInvokingJobDetailFactoryBean可以在配置目标Bean的名字和目标方法,但这种方式不支持传参
    // 配置MyJob1
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();

        bean.setTargetBeanName("myJob1");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    // JobDetailFactoryBean 可以配置JobDetail,任务类继承自 QuartzJobBean,这种方式支持传参,参数封装在JObDataMap中进行传递
    // 配置MyJob2
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MyJob2.class);
        JobDataMap map = new JobDataMap();
        map.put("quartzTestService", quartzTestService());
        bean.setJobDataMap(map);
        return bean;
    }

    // SimpleTrigger触发器
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setStartTime(new Date());
        bean.setRepeatCount(5);
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        bean.setRepeatInterval(3000);
        return bean;
    }

    // CronTrigger 触发器
    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/5 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }


    // 启用触发器
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronTrigger().getObject(), simpleTriggerFactoryBean().getObject());
        return bean;
    }

    @Bean
    QuartzTestService quartzTestService() {
        return new QuartzTestService();
    }
}

/**
 * 其他启动器(Trigger)还有:
 * CalendarIntervalTrigger , DailyTimeIntervalTrigger等等
 */
