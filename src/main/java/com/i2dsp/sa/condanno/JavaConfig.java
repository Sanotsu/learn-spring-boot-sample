package com.i2dsp.sa.condanno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * 条件注解
 */
//@Configuration
public class JavaConfig {
    /*
     * @Profile注解的实现,也是使用@COnditional注解,虽然使用@Profile注解 ,但逻辑判断不是自己实现的,不够灵活
     * */
    @Bean("food")
    //    //条件注解
    //    @Conditional(RiceCondition.class)
    // Profile注解
    @Profile("南方人")
    Food rice() {
        return new Rice();
    }

    @Bean("food")
    //    //条件注解
    //    @Conditional(NoodlesCondition.class)
    // Profile注解
    @Profile("南方人")
    Food noodles() {
        return new Noddles();
    }
}
