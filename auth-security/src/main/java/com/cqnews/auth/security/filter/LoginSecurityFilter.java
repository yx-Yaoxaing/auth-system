package com.cqnews.auth.security.filter;

import cn.hutool.json.JSONUtil;
import com.cqnews.auth.entity.LoginUser;
import com.cqnews.auth.exception.LoginUserNamePasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Object cacheValue = redisTemplate.opsForValue().get(token);
        if (token == null) {
            throw new LoginUserNamePasswordException("token过期");
        }
        LoginUser loginUser = JSONUtil.toBean((String) cacheValue, LoginUser.class);
        UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityToken);
        filterChain.doFilter(request, response);
    }
}
