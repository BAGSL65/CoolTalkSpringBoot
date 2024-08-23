package com.yxf.CoolTalk_v1.controller;

import com.yxf.CoolTalk_v1.common.result.CommonResult;
import com.yxf.CoolTalk_v1.pojo.User;
import com.yxf.CoolTalk_v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/loginByAccountByPassword")
    public CommonResult<User> loginByAccountByPassword(@RequestBody User user){
        User user_db=userService.loginByAccountByPassword(user);
        return user_db!=null?CommonResult.success(userService.loginByAccountByPassword(user)):
                CommonResult.fail(userService.loginByAccountByPassword(user));
    }
}
