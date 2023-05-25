package com.cqnews.auth.controller;

import com.cqnews.auth.entity.LoginUser;
import com.cqnews.auth.security.util.SecurityUtil;
import com.cqnews.auth.util.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:works:add')")
    public Result add(){
        Long userId = SecurityUtil.getUserId();
        return Result.SUCCESS(userId+"新增成功");
    }

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('system:works:query')")
    public Result query(){
        return Result.SUCCESS("查询成功");
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('s_role1')")
    public Result role(){
        return Result.SUCCESS("查询成功");
    }

}
