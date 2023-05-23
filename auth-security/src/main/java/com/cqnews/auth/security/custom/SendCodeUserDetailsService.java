package com.cqnews.auth.security.custom;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SendCodeUserDetailsService {

    UserDetails sendCode(String phone,String code);

}
