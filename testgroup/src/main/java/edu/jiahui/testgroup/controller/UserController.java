package edu.jiahui.testgroup.controller;
import edu.jiahui.framework.util.ResultCode;
import edu.jiahui.testgroup.domain.Users;
import edu.jiahui.testgroup.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResultCode userList(){
        List<Users> res= new ArrayList<>();
        try{
            res=userService.userList();
        }catch (Exception e){
            log.error("查询用户异常:{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试！");
        }
        return ResultCode.getSuccessReturn(null,"查询成功！",res);
    }

    //临时接口
    @RequestMapping(method = RequestMethod.GET, value = "/userInfo")
    public ResultCode getUserInfo(){
        Users res= new Users();
        try{
            res=userService.gerUserInfo();
        }catch (Exception e){
            log.error("查询用户信息异常:{}",e.getMessage());
            return ResultCode.getFailure(null,"服务器繁忙，请稍后重试验！");
        }
        return ResultCode.getSuccessReturn(null,"查询成功！",res);
    }


}
