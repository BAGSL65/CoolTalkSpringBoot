package com.yxf.CoolTalk.nosql.mongoDB.repository;


import com.yxf.CoolTalk.nosql.mongoDB.document.CoolTalkPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @auther macrozheng
 * @description 会员商品浏览历史Repository
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
public interface CoolTalkPostRepository extends MongoRepository<CoolTalkPost,String> {
    /**
     * 根据会员id按时间倒序获取浏览记录
     * @param postId 帖子id
     * @param authorId 作者id
     */
    List<CoolTalkPost> findByPostIdAndAuthorIdOrderByLikes(String postId, String authorId);
}
