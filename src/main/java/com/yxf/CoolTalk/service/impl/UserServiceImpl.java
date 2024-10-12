package com.yxf.CoolTalk.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.yxf.CoolTalk.config.RedisConfig;
import com.yxf.CoolTalk.dao.CooltalkUserDao;
import com.yxf.CoolTalk.mbg.mapper.CooltalkUserMapper;
import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.mbg.model.CooltalkUserExample;
import com.yxf.CoolTalk.po.AdminUserDetails;
import com.yxf.CoolTalk.service.RedisService;
import com.yxf.CoolTalk.service.UserService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.StrUtil;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Random;

/**
 * @program: CoolTalkSpringBoot
 * @description: 用户Service
 * @author: YangXueFeng
 * @create: 2024-09-19 16:10
 **/
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private CooltalkUserDao cooltalkUserDao;
    @Autowired
    private CooltalkUserMapper cooltalkUserMapper;
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
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
        long res=cooltalkUserMapper.countByExample(userExample);
        return res != 0;
    }

    @Override
    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'user:'+#act")
    public int deleteUser(String act) {
        CooltalkUserExample userExample=new CooltalkUserExample();
        CooltalkUserExample.Criteria criteria=userExample.createCriteria();
        if(StrUtil.isNotBlank(act)){
            criteria.andAccountEqualTo(act);
        }
        return cooltalkUserMapper.deleteByExample(userExample);
    }

    @Override
    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'user:'+#user.account")
    public int updateUser(CooltalkUser user) {
        CooltalkUserExample userExample=new CooltalkUserExample();
        CooltalkUserExample.Criteria criteria=userExample.createCriteria();
        String act=user.getAccount();
        String cpwd=SecureUtil.sha256(user.getPassword());
        user.setPassword(cpwd);
        if(StrUtil.isNotBlank(act)){
            criteria.andAccountEqualTo(act);
        }else return 0;
        return cooltalkUserMapper.updateByExampleSelective(user,userExample);
    }

    @Override
    @CachePut(value = RedisConfig.REDIS_KEY_DATABASE, key = "'user:'+#user.account", unless = "#result==null")
    public CooltalkUser createUser(CooltalkUser user){
        int res=0;
        if(isUserExist(user.getAccount())){
            return null;
        }else {
            String newPwd = SecureUtil.sha256(user.getPassword());
            user.setPassword(newPwd);
            String gender=user.getGender();
            user.setGender(gender==null?"unknown":gender);
            res = cooltalkUserMapper.insert(user);
        }
        return getUserByAccount(user.getAccount());
    }

    @Override
    public List<CooltalkUser> listUsers() {
        CooltalkUserExample userExample=new CooltalkUserExample();
        return cooltalkUserMapper.selectByExample(userExample);
    }

    @Override
    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'user:'+#act", unless = "#result==null")
    public CooltalkUser getUserByAccount(String act) {
        log.info("操作了数据库");
        CooltalkUserExample userExample=new CooltalkUserExample();
        CooltalkUserExample.Criteria criteria=userExample.createCriteria();
        if(StrUtil.isNotBlank(act)){
            criteria.andAccountEqualTo(act);
        }
        List<CooltalkUser> res=cooltalkUserMapper.selectByExample(userExample);
        return !res.isEmpty()? res.get(0): null;
    }



    @Override
    public String generateAuthCode(String act) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定账号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + act, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + act, AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }


    //对输入的验证码进行校验
    @Override
    public Boolean verifyAuthCode(String act, String authCode) {
        if (StrUtil.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + act);
        return authCode.equals(realAuthCode);
    }

}
