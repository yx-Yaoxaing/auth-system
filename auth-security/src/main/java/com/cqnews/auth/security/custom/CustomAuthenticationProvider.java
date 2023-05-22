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

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        CustomAuthenticationToken customAuthenticationToken = new CustomAuthenticationToken(loadedUser.getUsername(),loadedUser.getPassword(),loadedUser.getAuthorities());
        return customAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }

}
