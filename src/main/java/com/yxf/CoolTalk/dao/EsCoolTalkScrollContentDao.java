package com.yxf.CoolTalk.dao;

import com.yxf.CoolTalk.nosql.elasticsearch.document.EsCoolTalkScrollContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther macrozheng
 * @description 搜索系统中的商品管理自定义Dao
 * @date 2018/6/19
 * @github https://github.com/macrozheng
 */
public interface EsCoolTalkScrollContentDao {
    List<EsCoolTalkScrollContent> getAllEsContentList(@Param("id") String id);
}
