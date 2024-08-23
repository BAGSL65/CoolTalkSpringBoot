package com.yxf.CoolTalk_v1.mapper;

import com.yxf.CoolTalk_v1.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from cooltalk_user where account=#{account} and password=#{password}")
    public User loginByAccountByPassword(User user);
}
