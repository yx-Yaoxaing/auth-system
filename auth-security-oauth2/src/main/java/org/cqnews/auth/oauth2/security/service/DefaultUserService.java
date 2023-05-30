package org.cqnews.auth.oauth2.security.service;


import org.cqnews.auth.oauth2.entity.LoginUser;
import org.cqnews.auth.oauth2.entity.User;
import org.cqnews.auth.oauth2.exception.LoginUserNamePasswordException;
import org.cqnews.auth.oauth2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        Long userId = user.getId();
        Set<String> rolePerm = userMapper.findRolePermByUserId(userId);
        Set<String> setPerm = userMapper.findPermByUserId(userId);
        rolePerm.forEach(val->{
            String roleAuth = "ROLE_" + val;
            setPerm.add(roleAuth);
        });
        setPerm.addAll(rolePerm);
        LoginUser loginUser = new LoginUser(user,setPerm);
        // 1 启用  0禁用
        if (user.getStatus() == 1) {
            loginUser.setUserAccountNonLocked(true);
        } else {
            loginUser.setUserAccountNonLocked(false);
        }
        loginUser.setUserEnabled(user.getEnable());
        return loginUser;
    }
}
