//package com.i2dsp.sa.core;
//
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
///**
// * 自定义核心组件Realm中实现简单的认证操作
// */
///**
// * 20200727
// * 因为在这累加的demo中使用shiro出现了 No ServletContext set 问题 ,一直无法解决
// * 所以先删掉shiro相关内容,单独在myshiro项目中使用可以运行
// */
//public class MyRealm extends AuthorizingRealm {
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        // 示例而已,简单写写
//        String username = (String) authenticationToken.getPrincipal();
//        if (!"david".equals(username)) {
//            throw new UnknownAccountException("用户名不存在!");
//        }
//
//        // 满足 david 123的账密就成功
//        return new SimpleAuthenticationInfo(username, "123", getName());
//    }
//}
