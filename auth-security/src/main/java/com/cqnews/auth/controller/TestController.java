package com.cqnews.auth.controller;

import com.cqnews.auth.util.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:works:add')")
    public Result add(){
        return Result.SUCCESS("新增成功");
    }

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('system:works:query')")
    public Result query(){
        return Result.SUCCESS("查询成功");
    }

}
