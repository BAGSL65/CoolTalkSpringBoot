package com.yxf.CoolTalk.service;


import com.yxf.CoolTalk.mbg.model.CooltalkUser;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {
    public CooltalkUser loginByAccountByPassword(String act,String pwd);
    public int createUser(CooltalkUser user);
}
