package com.cqnews.auth.entity;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String userName;

    private String password;

    private Integer status;


}
