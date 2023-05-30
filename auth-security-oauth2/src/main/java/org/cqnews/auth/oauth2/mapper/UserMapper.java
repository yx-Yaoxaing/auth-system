package org.cqnews.auth.oauth2.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.cqnews.auth.oauth2.entity.User;

import java.util.Set;

@Mapper
public interface UserMapper {

    User findByUserName(String userName);

    Set<String> findPermByUserId(Long userId);

    User findByPhone(String phone);

    Set<String> findRolePermByUserId(Long userId);

}
