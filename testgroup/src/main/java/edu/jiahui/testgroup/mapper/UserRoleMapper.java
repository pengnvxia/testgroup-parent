package edu.jiahui.testgroup.mapper;

import edu.jiahui.testgroup.domain.UserRole;

public interface UserRoleMapper {
    UserRole selectByPrimaryKey(Integer id);
    UserRole selectByUserId(String userId);
}
