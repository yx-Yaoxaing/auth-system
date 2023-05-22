package com.cqnews.auth.security.config;


import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.cqnews.auth.util.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录全局
 */
@Configuration
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = null;
        if (authException instanceof BadCredentialsException) {
             result = Result.FAIL(authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException){
            result = Result.FAIL("请先登录");
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
