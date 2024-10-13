package com.yxf.CoolTalk.service;

import com.yxf.CoolTalk.nosql.elasticsearch.document.EsCoolTalkScrollContent;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @auther macrozheng
 * @description 商品搜索管理Service
 * @date 2018/6/19
 * @github https://github.com/macrozheng
 */
public interface EsScrollService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsCoolTalkScrollContent create(String id);

    /**
     * 批量删除商品
     */
    void delete(List<String> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsCoolTalkScrollContent> search(String text, Integer pageNum, Integer pageSize);

}
