package com.i2dsp.sa.starttask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 系统启动任务
 * 项目启动阶段要做的一些初始化操作,只有在启动进行时执行,以后都不在执行
 * (例如web中的Listener(ServletContextListener))
 * <p>
 * 两种实现方式
 * 1 CommandLineRunner
 * 2 ApplicationRunner
 */

/**
 * 简单说明
 * 1 通过@Component注解将 MyCommandLineRunner1 注册为Spring容器的一个Bean
 * 2 添加@Order注解,表示这个启动任务的执行优先级.数字越小,优先级越大.
 * 默认情况下优先级为Integer.MAX_VALUE,表示最低优先级
 * 3 在run方法中写启动任务的核心逻辑,当项目启动时,run方法会被自动执行
 * 4 run方法的参数,来自项目的启动参数,即项目入口类中,main方法的参数会被传到这里
 */
//@Component
//@Order(100)
public class MyCommandLineRunner1 implements CommandLineRunner {

    /**
     * (String ...) is an array of parameters of type String, where as String[] is a single parameter.
     * Now here String[] can full fill the same purpose here but (String ...) provides more readability and easiness to use.
     * It also provides an option that we can pass multiple array of String rather than a single one using String[].
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(args).forEach((n) -> System.out.println("MyCommandLineRunner1--> " + n));

    }

}
