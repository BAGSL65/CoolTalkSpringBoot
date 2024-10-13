package com.yxf.CoolTalk.nosql.elasticsearch.repository;

import com.yxf.CoolTalk.nosql.elasticsearch.document.EsCoolTalkScrollContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @auther macrozheng
 * @description 商品ES操作类
 * @date 2018/6/19
 * @github https://github.com/macrozheng
 */
public interface EsCoolTalkScrollContentRepository extends ElasticsearchRepository<EsCoolTalkScrollContent, Long> {
    /**
     * 搜索查询
     * @param text              查询内容
     * @param page              分页信息
     * @return
     */
    Page<EsCoolTalkScrollContent> findByText(String text, Pageable page);

}
