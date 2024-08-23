package com.yxf.CoolTalk_v1.service;

import com.yxf.CoolTalk_v1.mapper.UserMapper;
import com.yxf.CoolTalk_v1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User loginByAccountByPassword(User user){
        return userMapper.loginByAccountByPassword(user);
    }
}
