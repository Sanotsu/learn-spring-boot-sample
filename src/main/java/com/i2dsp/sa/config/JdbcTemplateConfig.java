//package com.i2dsp.sa.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class JdbcTemplateConfig {
//    /**
//     * 每个JdbcTemplate的创建都需要一个DataSource.
//     * 由于Spring容器中现在存在两个DataSource,默认类型查找会报错
//     * 因此加上@Qualifier注解,表示按名称查找
//     */
//
//    @Bean
//    JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne") DataSource dsOne) {
//        return new JdbcTemplate(dsOne);
//    }
//
//    @Bean
//    JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo") DataSource dsTwo) {
//        return new JdbcTemplate(dsTwo);
//    }
//}
