package com.cqnews.auth.security.authority;


import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

public class CustomRoleSecurityAuthority extends SecurityExpressionRoot {


    public CustomRoleSecurityAuthority(Authentication authentication) {
        super(authentication);
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        return super.hasPermission(target, permission);
    }
}
