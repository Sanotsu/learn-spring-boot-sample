package com.i2dsp.sa;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Spring boot 会默认扫描下级目录的组件,所以不需要单独注解ComponentScan
 * 此外,如果不在下级目录中使用相应注解,自然也不会扫描
 *
 * @author david
 */
@SpringBootApplication
// 以下两个可以解决shiro导致aop失效的问题
//@SpringBootApplication(exclude = {ShiroAnnotationProcessorAutoConfiguration.class})
//@EnableTransactionManagement(proxyTargetClass = true)
@EnableScheduling // 启用定时任务
@MapperScan(basePackages = "com.i2dsp.sa.mybatismapper")
// 开启缓存(引入依赖,配置属性,开启缓存之后,spring boot会自动配置一个RedisCacheManage,间接实现了Spring中的Cache接口)
@EnableCaching

public class I2dspAuthorityApplication {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        // 条件注解赋值
////        ctx.getEnvironment().getSystemProperties().put("people", "南方人");
//        // @Profile注解赋值
//        ctx.getEnvironment().setActiveProfiles("南方人");
//        ctx.register(JavaConfig.class);
//        ctx.refresh();
//        Food food = (Food) ctx.getBean("food");
//        System.out.println(food.showName());

        SpringApplication.run(I2dspAuthorityApplication.class, args);
    }
}


