package com.yxf.CoolTalk.mbg.mapper;

import com.yxf.CoolTalk.mbg.model.CooltalkUser;
import com.yxf.CoolTalk.mbg.model.CooltalkUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CooltalkUserMapper {
    long countByExample(CooltalkUserExample example);

    int deleteByExample(CooltalkUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CooltalkUser row);

    int insertSelective(CooltalkUser row);

    List<CooltalkUser> selectByExample(CooltalkUserExample example);

    CooltalkUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") CooltalkUser row, @Param("example") CooltalkUserExample example);

    int updateByExample(@Param("row") CooltalkUser row, @Param("example") CooltalkUserExample example);

    int updateByPrimaryKeySelective(CooltalkUser row);

    int updateByPrimaryKey(CooltalkUser row);
}