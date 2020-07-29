package com.i2dsp.sa.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2dsp.sa.utils.ResponseBean;
import com.i2dsp.sa.utils.VerifyCodeFilter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author david
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /********************************************************
     * 最原始的用法
     *******************************************************/
//
//    /**
//     * 使用BCryptPasswordEncoder作为密码编码工具,实现对密码的自动加密加盐
//     *
//     * @return
//     */
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 在内存中配置两个用户
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("david")
//                .roles("admin")
//                // 123 的密文  https://bcrypt-generator.com/ 上面生成的
//                .password("$2y$12$TmwcYSD3A8ML6B8f8WbbLuCKH9wsxPV0mLwP5AbRqi9AFeznT65S.")
//                .and()
//                .withUser("lisa")
//                .roles("admin")
//                //123
//                .password("$2y$12$TmwcYSD3A8ML6B8f8WbbLuCKH9wsxPV0mLwP5AbRqi9AFeznT65S.");
//    }
//
//    /**
//     * 配置登录逻辑
//     * 在spring security中配置过滤器
//     * 配置登录成功失败的响应,登出成功的响应
//     */
//
//    @NonNull
//    VerifyCodeFilter verifyCodeFilter;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // 添加验证码验证(测试没有页面就可以先不用)
//        // http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
//
//        http.authorizeRequests() // 开启登入配置
//                .anyRequest().authenticated()  // 所有请求都需要验证成功才能访问
//                .and()
//                .formLogin()
//                // 登录处理接口
//                // 这个doLogin是登录的接口,不在Controller里面,前端访问需要例如 POST请求的 http://localhost:8083/doLogin,带上username和password
//                .loginProcessingUrl("/doLogin")
//                // 定义登录时,用户名的key,默认为username,可修改
////                .usernameParameter("uname")
////                // 定义登录时,密码的key,默认为password,可修改
////                .passwordParameter("pwd")
//                // 登录成功之后的处理(前后端分离的,返回json\string之类的即可)
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                        ResponseBean ok = ResponseBean.ok("登录成功", authentication.getPrincipal()); // 登录成功 返回成功信息和用户名
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = response.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(ok));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                // 登录失败之后的处理
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//
//                        ResponseBean error = ResponseBean.error("登录失败"); // 登录成功 返回失败信息
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = response.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(error));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                // 这里相当于用户没有登录时访问页面返回的显示结果
//                // 虽然配置了登录页面为/ss/withoutLogin,实际上不是一个页面,是返回一段json,在 LoginSecurityController 中提供的接口
//                .loginPage("/ss/withoutLogin")
//                .permitAll() // 和表单登录相关的接口统统直接通过
//                .and()
//                .logout()
//                // 这是注销的接口,和登录的接口(本例为doLogin)一样,都没有存在于Controller中
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        ResponseBean ok = ResponseBean.ok("注销成功");
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = response.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(ok));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .accessDeniedHandler(new AccessDeniedHandler() {
//                    @Override
//                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                        ResponseBean error = ResponseBean.error("权限不足,访问失败");
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = response.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(error));
//                        out.flush();
//                        out.close();
//                    }
//                });
//    }
//
//    /**
//     * 忽略拦截
//     * 过滤该 ,不走Spring security过滤链
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/vercode");
//    }

    /******************************************
     * 使用自定义密码检查过滤器之后的配置
     ************************************/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring security角色继承
     *
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        var roleHierarchy = new RoleHierarchyImpl();
        // 自定义角色,user能访问的资源,admin和dba也能访问,admin能访问的资源,dba也能访问
        // 使用字符串描述角色间继承关系
        // 这个单行字符串的配置,可以需要根据RoleHierarchyImpl 实现类里面 buildRolesReachableInOneStepMap()方法看具体操作
        var hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("lisa").password("$2y$12$TmwcYSD3A8ML6B8f8WbbLuCKH9wsxPV0mLwP5AbRqi9AFeznT65S.").roles("user");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/index.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 所有请求都需要验证(有配置antMatchers和hasRole去区分的时候,)
//                .anyRequest().authenticated()

                // 访问 /ss/**接口的角色,必须是dba才行,因为自定义dba > user,所以user能访问的dba也行,反之不行
                // 如果是user或者admin角色访问这个接口,则会报 403 Forbidden
                .antMatchers("/ss/**")
                .hasRole("dba")

                // 访问/mybatisUser/** 接口的角色, 是user就行
                .antMatchers("/mybatisUser/**")
                .hasRole("user")

                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .loginPage("/ss/withoutLogin")
                .permitAll()
                .and().csrf().disable();
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                var respBean = ResponseBean.ok("登录成功!");
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                var respBean = ResponseBean.error("登录失败-----------!");
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}


