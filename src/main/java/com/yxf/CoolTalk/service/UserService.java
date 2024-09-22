package com.yxf.CoolTalk.service;


import com.yxf.CoolTalk.mbg.model.CooltalkUser;

public interface UserService {
    public CooltalkUser loginByAccountByPassword(String act,String pwd);
}
