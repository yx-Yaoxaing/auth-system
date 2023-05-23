package com.cqnews.auth.security.custom;


import com.cqnews.auth.entity.LoginUser;
import com.cqnews.auth.entity.User;
import com.cqnews.auth.exception.LoginUserNamePasswordException;
import com.cqnews.auth.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService,SendCodeUserDetailsService {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    @Override
    public UserDetails sendCode(String phone, String code) {
        logger.info("发送短信验证码登录,phone:{},code:{}",phone,code);
        User user = userMapper.findByPhone(phone);
        if (Objects.isNull(user)) {
            throw new LoginUserNamePasswordException("请输入正确的手机号");
        }
        if (code.equals("123456")) {
            Set<String> setPerm = userMapper.findPermByUserId(user.getId());
            LoginUser loginUser = new LoginUser(user,setPerm);
            return loginUser;
        }
        throw new LoginUserNamePasswordException("验证码错误！！！");
    }
}
