package com.cqnews.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class LoginUser implements UserDetails {


    private User user;

    private Set<String>  perm;

    private boolean userAccountNonLocked;

    private boolean userEnabled;

    public LoginUser(User user, Set<String> perm) {
        this.user = user;
        this.perm = perm;
    }

    // 返回权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> permissions = perm.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return permissions;
    }

    // 返回密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }


    // 返回用户名
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEnabled;
    }

    public void setUserAccountNonLocked(boolean userAccountNonLocked) {
        this.userAccountNonLocked = userAccountNonLocked;
    }

    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }
}
