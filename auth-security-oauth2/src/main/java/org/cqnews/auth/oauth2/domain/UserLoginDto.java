package org.cqnews.auth.oauth2.domain;

import lombok.Data;

@Data
public class UserLoginDto {

    private String userName;

    private String password;

    private String code;

    // 登录类型
    String loginType;

}
