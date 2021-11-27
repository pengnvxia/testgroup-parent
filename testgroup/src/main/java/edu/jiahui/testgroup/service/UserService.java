package edu.jiahui.testgroup.service;

import edu.jiahui.framework.threadlocal.ParameterThreadLocal;
import edu.jiahui.testgroup.domain.UserRole;
import edu.jiahui.testgroup.domain.Users;
import edu.jiahui.testgroup.mapper.UserRoleMapper;
import edu.jiahui.testgroup.mapper.UsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    public List<Users> userList(){
        UserRole userRole = userRoleMapper.selectByUserId(ParameterThreadLocal.getUid());
        if(userRole.getRole()==1){
            return usersMapper.selectBySource(null);
        }else{
            return usersMapper.selectBySource(ParameterThreadLocal.getUid());
        }
    }

    public Users gerUserInfo(){
        Users users = usersMapper.selectById(ParameterThreadLocal.getUid().toString());
        return users;
    }
}
