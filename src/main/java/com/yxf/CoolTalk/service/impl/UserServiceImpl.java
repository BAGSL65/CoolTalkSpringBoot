package com.yxf.CoolTalk.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.yxf.CoolTalk.dao.CooltalkUserDao;
import com.yxf.CoolTalk.mbg.mapper.CooltalkUserMapper;
import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.mbg.model.CooltalkUserExample;
import com.yxf.CoolTalk.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.StrUtil;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @program: CoolTalkSpringBoot
 * @description: 用户Service
 * @author: YangXueFeng
 * @create: 2024-09-19 16:10
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CooltalkUserDao cooltalkUserDao;
    @Autowired
    private CooltalkUserMapper cooltalkUserMapper;
    @Override
    public CooltalkUser loginByAccountByPassword(String act,String pwd) {
        CooltalkUserExample userExample=new CooltalkUserExample();
        CooltalkUserExample.Criteria criteria=userExample.createCriteria();
        if(StrUtil.isNotBlank(act)){
            criteria.andAccountEqualTo(act);
        }
        if(StrUtil.isNotBlank(pwd)){
            criteria.andPasswordEqualTo(SecureUtil.sha256(pwd));
        }
        List<CooltalkUser> res=cooltalkUserMapper.selectByExample(userExample);
        return !res.isEmpty()? res.get(0): null;
    }

    public boolean isUserExist(String act){
        CooltalkUserExample userExample=new CooltalkUserExample();
        CooltalkUserExample.Criteria criteria=userExample.createCriteria();
        if(StrUtil.isNotBlank(act)){
            criteria.andAccountEqualTo(act);
        }
        List<CooltalkUser> res=cooltalkUserMapper.selectByExample(userExample);
        return !res.isEmpty();
    }

    @Override
    public int createUser(CooltalkUser user){
        int res=0;
        if(isUserExist(user.getAccount())){
            return -1;
        }else {
            String newPwd = SecureUtil.sha256(user.getPassword());
            user.setPassword(newPwd);
            res = cooltalkUserMapper.insert(user);
        }
        return res;
    }


}
