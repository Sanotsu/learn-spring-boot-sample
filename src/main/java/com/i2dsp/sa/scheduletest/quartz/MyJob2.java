package com.i2dsp.sa.scheduletest.quartz;

import com.i2dsp.sa.service.QuartzTestService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 第二种定义Job方式:继承QuartzJobBean
 * 这种方式支持传参,任务启动时,executeInternal方法将会被执行
 */
public class MyJob2 extends QuartzJobBean {

    /**
     * 引入service
     */
    QuartzTestService quartzTestService;

    public QuartzTestService getQuartzTestService() {
        return quartzTestService;
    }

    public void setQuartzTestService(QuartzTestService quartzTestService) {
        this.quartzTestService = quartzTestService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("MyJob2-> " + quartzTestService.sayHello());
    }

}
