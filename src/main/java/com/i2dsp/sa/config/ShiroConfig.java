//package com.i2dsp.sa.config;
//
//import com.i2dsp.sa.core.MyRealm;
//import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
//import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 20200727
// * 因为在这累加的demo中使用shiro出现了 No ServletContext set 问题 ,一直无法解决
// * 所以先删掉shiro相关内容,单独在myshiro项目中使用可以运行
// */
//
//@Configuration
//public class ShiroConfig {
//
//    // 提供Realm的实例
//    @Bean
//    MyRealm myRealm() {
//        return new MyRealm();
//    }
//
//    // 配置一个SecurityManage,在SecurityManage中配置Realm
//    @Bean
//    DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(myRealm());
//        return manager;
//    }
//
//    // 定义Shiro的路径匹配规则
//    @Bean
//    ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
//        definition.addPathDefinition("/doLogin", "anon");
//        definition.addPathDefinition("/**", "authc");
//
//        return definition;
//    }
//
//}
