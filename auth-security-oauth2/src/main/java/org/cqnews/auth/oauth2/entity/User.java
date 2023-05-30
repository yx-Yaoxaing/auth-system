package org.cqnews.auth.oauth2.entity;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String userName;

    private String password;

    // 禁用 启用
    private Integer status;

    // 关闭
    private boolean enable;

    public boolean getEnable() {
        return enable;
    }
}
