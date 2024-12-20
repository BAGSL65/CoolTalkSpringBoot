package com.yxf.CoolTalk.service;


import com.yxf.CoolTalk.nosql.mongoDB.document.CoolTalkPost;

import java.util.List;

/**
 * @auther macrozheng
 * @description 会员浏览记录管理Service
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
public interface CoolTalkPostService {
    /**
     * 生成浏览记录
     */
    int create(CoolTalkPost coolTalkPost);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<CoolTalkPost> list(String postId,String authorId);
}
