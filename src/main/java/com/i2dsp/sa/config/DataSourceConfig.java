//package com.i2dsp.sa.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    /**
//     * @ConfigurationProperties 是springboot提供的类型安全的属性绑定
//     * prefix = "spring.datasource.one" 使用spring.datasource.one前缀的数据库配置去创建一个DataSource
//     */
//    @Bean()
//    @ConfigurationProperties(prefix = "spring.datasource.one")
//    DataSource dsOne() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean()
//    @ConfigurationProperties(prefix = "spring.datasource.two")
//    DataSource dsTwo() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//}
