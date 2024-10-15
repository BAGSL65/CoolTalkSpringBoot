package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.config.RedisConfig;
import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags="UserController")
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/user")
@Tag(name="UserController",description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/listAll")
    public CommonResult<List<CooltalkUser>> listUsers(){
        List<CooltalkUser> data=userService.listUsers();
        return CommonResult.success(data);
    }

    @ApiOperation("根据账户名查询用户")
    @GetMapping("/{account}")
    public CommonResult<CooltalkUser> getUserByAccount(@PathVariable("account") @ApiParam("账户名")String account){
        CooltalkUser data=userService.getUserByAccount(account);
        return CommonResult.success(data);
    }

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
    @PostMapping("/create")
    public CommonResult<Void> createUser(@RequestBody @ApiParam("注册用户") CooltalkUser user){
        log.info("注册时，用户性别为"+user.getGender());
        CooltalkUser res=userService.createUser(user);
        log.info("注册后，用户性别为"+user.getGender());
        return res!=null?CommonResult.success(null):CommonResult.fail("用户创建失败");
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public CommonResult<Void> updateUser(@RequestBody @ApiParam("更新用户信息") CooltalkUser user){
        int data=userService.updateUser(user);
        return data>0?CommonResult.success(null):CommonResult.fail("更新用户失败");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public CommonResult<Void> deleteUser(@RequestBody @ApiParam("删除用户账号") String account){
        int data=userService.deleteUser(account);
        return data>0?CommonResult.success(null):CommonResult.fail("删除用户失败");
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> getAuthCode(@ApiParam("账户名") @RequestParam String act) {
        return CommonResult.success(userService.generateAuthCode(act));
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Void> updatePassword(@ApiParam("账户名") @RequestParam String act,
                                             @ApiParam("验证码") @RequestParam String authCode) {
        return userService.verifyAuthCode(act,authCode)?CommonResult.success(null):CommonResult.fail();
    }
}
