package com.cqnews.auth.mapper;


import com.cqnews.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface UserMapper {

    User findByUserName(String userName);

    Set<String> findPermByUserId(Long userId);

}
