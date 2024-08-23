package com.yxf.CoolTalk_v1.controller;

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
    public User loginByAccountByPassword(@RequestBody User user){
        return userService.loginByAccountByPassword(user);
    }
}
