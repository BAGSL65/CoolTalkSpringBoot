package com.yxf.CoolTalk.service;


import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.po.AdminUserDetails;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserService {
    CooltalkUser loginByAccountByPassword(String act, String pwd);
    CooltalkUser createUser(CooltalkUser user);
    int updateUser(CooltalkUser user);
    int deleteUser(String act);
    List<CooltalkUser> listUsers();
    CooltalkUser getUserByAccount(String act);
    String generateAuthCode(String act);
    Boolean verifyAuthCode(String act, String authCode);
}
