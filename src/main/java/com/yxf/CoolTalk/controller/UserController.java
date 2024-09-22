package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.service.UserService;
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
    public CommonResult<CooltalkUser> loginByAccountByPassword(@RequestBody CooltalkUser user){
        String act=user.getAccount();
        String pwd=user.getPassword();
        CooltalkUser user_db=userService.loginByAccountByPassword(act,pwd);
        return user_db!=null?
                CommonResult.success(user_db):
                CommonResult.fail();
    }
}
