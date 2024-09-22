package com.yxf.CoolTalk.dao;

import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CooltalkUserDao {
    @Select("select * from cooltalk_user where account=#{account} and password=#{password}")
    public CooltalkUser loginByAccountByPassword(CooltalkUser user);
}
