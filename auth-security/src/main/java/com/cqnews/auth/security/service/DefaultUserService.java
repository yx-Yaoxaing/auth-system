package com.cqnews.auth.security.service;

import com.cqnews.auth.entity.LoginUser;
import com.cqnews.auth.entity.User;
import com.cqnews.auth.exception.LoginUserNamePasswordException;
import com.cqnews.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Set;

@Service
public class DefaultUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    // 处理数据库的
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(username);
        if (Objects.isNull(user)) {
            throw new LoginUserNamePasswordException("用户名或密码错误");
        }
        Set<String> setPerm = userMapper.findPermByUserId(user.getId());
        LoginUser loginUser = new LoginUser(user,setPerm);
        return loginUser;
    }
}