package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(tags="UserController")
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@Tag(name="UserController",description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<CooltalkUser> loginByAccountByPassword(@RequestBody @ApiParam("登录用户")CooltalkUser user){
        String act=user.getAccount();
        String pwd=user.getPassword();
        CooltalkUser user_db=userService.loginByAccountByPassword(act,pwd);
        return user_db!=null?
                CommonResult.success(user_db):
                CommonResult.fail();
    }
    @ApiOperation("注册用户")
    @PostMapping("/user")
    public CommonResult<Void> createUser(@RequestBody @ApiParam("注册用户") CooltalkUser user){
        int res=userService.createUser(user);
        return switch (res) {
            case (-1) -> CommonResult.fail("用户名已存在");
            case (0) -> CommonResult.fail("用户创建失败");
            case (1) -> CommonResult.success(null);
            default -> CommonResult.fail("发生未知错误");
        };
    }
}
