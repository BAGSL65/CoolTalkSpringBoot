package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public CommonResult<CooltalkUser> loginByAccountByPassword(@RequestBody CooltalkUser user){
        String act=user.getAccount();
        String pwd=user.getPassword();
        CooltalkUser user_db=userService.loginByAccountByPassword(act,pwd);
        return user_db!=null?
                CommonResult.success(user_db):
                CommonResult.fail();
    }

    @PostMapping("/user")
    public CommonResult<Void> createUser(@RequestBody CooltalkUser user){
        int res=userService.createUser(user);
        return switch (res) {
            case (-1) -> CommonResult.fail("用户名已存在");
            case (0) -> CommonResult.fail("用户创建失败");
            case (1) -> CommonResult.success(null);
            default -> CommonResult.fail("发生未知错误");
        };
    }
}
