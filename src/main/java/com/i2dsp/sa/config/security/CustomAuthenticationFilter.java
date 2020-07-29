package com.i2dsp.sa.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 在源码中可以看到,默认获取用户名密码是在 UsernamePasswordAuthenticationFilter 过滤器中,通过request的getParameter来提取
 * 可以自定义这个过滤器,替换默认的
 * <p>
 * 例如使用json传递用户名密码,那默认的就获取不到了,更换默认过滤器为自定义过滤器
 *
 * @author david
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (request.getContentType().equals(MediaType.TEXT_PLAIN_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            // ObjectMapper类是Jackson库的主要类。它提供一些功能将转换成Java对象匹配JSON结构，反之亦然。
            // 它使用JsonParser和JsonGenerator的实例实现JSON实际的读/写。
            ObjectMapper mapper = new ObjectMapper();

            UsernamePasswordAuthenticationToken authRequest = null;
            try (var is = request.getInputStream()) {

                // mapper.readValue()是将string串转化为对象 ,mapper.writeValueAsString()是将对象转化为json格式的字符串
                var authenticationBean = mapper.readValue(is, Map.class);

                authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.get("username"), authenticationBean.get("password"));
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
