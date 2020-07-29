package com.i2dsp.sa.starttask;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * ApplicationRunner 和 CommandLineRunner功能一致,使用方法也基本一致,唯一的区别主要体现在对参数的处理上
 * ApplicationRunner可以接收更多类型的参数
 */

//@Component
//@Order(98)
public class MyApplicationRunner1 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

        // args.getNonOptionArgs(): 获取命令行中的无key参数(和CommandLineRunner一样)
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("MyApplicationRunner1>>> " + nonOptionArgs);

        // args.getOptionNames() :获取所有key/value形式的参数的key
        Set<String> optionNames = args.getOptionNames();
        for (String key : optionNames) {
            // args.getOptionValues(key) :根据key获取key/value参数的value
            System.out.println("MyApplicationRunner1>>> " + key + ":" + args.getOptionValues(key));
        }

        // args.getSourceArgs() :获取命令行中的所有参数
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("MyApplicationRunner1>>> " + Arrays.toString(sourceArgs));
    }
}
