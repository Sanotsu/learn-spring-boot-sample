package com.i2dsp.sa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 本应用所有的方法都会去处理跨域请求
                .allowedOrigins("*")    // 所有的源
                .allowedMethods("*")    // 允许通过的请求数
                .allowedHeaders("*");   // 允许的请求头
    }
}
