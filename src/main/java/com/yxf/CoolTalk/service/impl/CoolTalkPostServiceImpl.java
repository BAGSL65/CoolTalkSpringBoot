package com.yxf.CoolTalk.service.impl;

import com.yxf.CoolTalk.nosql.mongoDB.document.CoolTalkPost;
import com.yxf.CoolTalk.nosql.mongoDB.repository.CoolTalkPostRepository;
import com.yxf.CoolTalk.service.CoolTalkPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther macrozheng
 * @description 会员浏览记录管理Service实现类
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
@Service
public class CoolTalkPostServiceImpl implements CoolTalkPostService {
    @Autowired
    private CoolTalkPostRepository coolTalkPostRepository;
    @Override
    public int create(CoolTalkPost coolTalkPost) {
        coolTalkPost.setId(null);
        coolTalkPost.setCreateTime(new Date());
        coolTalkPostRepository.save(coolTalkPost);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<CoolTalkPost> deleteList = new ArrayList<>();
        for(String id:ids){
            CoolTalkPost coolTalkPost = new CoolTalkPost();
            coolTalkPost.setId(id);
            deleteList.add(coolTalkPost);
        }
        coolTalkPostRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<CoolTalkPost> list(String postId,String authorId) {
        return coolTalkPostRepository.findByPostIdAndAuthorIdOrderByLikes(postId,authorId);
    }
}
