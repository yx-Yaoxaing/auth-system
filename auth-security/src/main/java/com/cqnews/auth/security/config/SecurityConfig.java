package com.cqnews.auth.security.config;

import com.cqnews.auth.security.custom.CustomAuthenticationProvider;
import com.cqnews.auth.security.custom.SendCodeUserDetailsService;
import com.cqnews.auth.security.filter.LoginSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService defaultUserService;

    @Autowired
    private LoginSecurityFilter loginSecurityFilter;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;


    @Autowired
    private SendCodeUserDetailsService sendCodeUserDetailsService;

    @Autowired
    private CustomAccessDeineHandler customAccessDeineHandler;

    // 创建密码解析器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(defaultUserService);
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        customAuthenticationProvider.setUserDetailsService(sendCodeUserDetailsService);
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .anyRequest().authenticated();
        http.addFilterAfter(loginSecurityFilter,UsernamePasswordAuthenticationFilter.class);
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
        http.exceptionHandling().accessDeniedHandler(customAccessDeineHandler);
    }




}
