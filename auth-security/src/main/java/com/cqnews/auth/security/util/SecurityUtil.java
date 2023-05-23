package com.cqnews.auth.security.util;

import com.cqnews.auth.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * security 工具类
 */
public class SecurityUtil {


    public static Long getUserId(){
        LoginUser loginUser = getLoginUser();
        Long id = loginUser.getUser().getId();
        return id;
    }

    private static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser;
    }

    private static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    public static LoginUser getCurrentUser(){
        LoginUser loginUser = getLoginUser();
        return loginUser;
    }

}
