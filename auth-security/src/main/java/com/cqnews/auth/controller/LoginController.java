package com.cqnews.auth.controller;

import cn.hutool.json.JSONUtil;
import com.cqnews.auth.domain.UserLoginDto;
import com.cqnews.auth.entity.LoginUser;
import com.cqnews.auth.security.custom.CustomAuthenticationToken;
import com.cqnews.auth.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;
import java.util.UUID;

@RestController
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginDto userLoginDto){
        String code = userLoginDto.getCode();
        String userName = userLoginDto.getUserName();
        String password = userLoginDto.getPassword();
        Authentication authenticate = null;
        if (StringUtils.hasText(code)) {
            log.info("用户请求登录,用户名:{},密码:{}",userName,password);
            authenticate = authenticationManager.authenticate(new CustomAuthenticationToken(userName, code));
        } else {
            log.info("短信验证码登录,用户名:{},验证码:{}",userName,code);
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        }
        if (Objects.isNull(authenticate)){
            return Result.FAIL("登录失败");
        }
        LoginUser user = (LoginUser) authenticate.getPrincipal();
        String token = UUID.randomUUID().toString().toUpperCase();
        redisTemplate.opsForValue().set(token, JSONUtil.toJsonStr(user));
        return Result.SUCCESS(token);
    }

}
