package edu.jiahui.testgroup.mapper;

import edu.jiahui.testgroup.domain.Users;
import java.util.List;

public interface UsersMapper {
    Users selectByPrimaryKey(Integer id);
    List<Users> selectBySource(String userId);
    Users selectById(String userId);
}
