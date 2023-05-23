package com.cqnews.auth.security.custom;

import com.cqnews.auth.entity.LoginUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 自定义授权提供器
 */

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private SendCodeUserDetailsService sendCodeUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();
        UserDetails loadedUser = this.getUserDetailsService().sendCode(phone,code);
        CustomAuthenticationToken customAuthenticationToken = new CustomAuthenticationToken(loadedUser,loadedUser.getPassword(),loadedUser.getAuthorities());
        return customAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public void setUserDetailsService(SendCodeUserDetailsService sendCodeUserDetailsService) {
        this.sendCodeUserDetailsService = sendCodeUserDetailsService;
    }

    protected SendCodeUserDetailsService getUserDetailsService() {
        return this.sendCodeUserDetailsService;
    }

}
