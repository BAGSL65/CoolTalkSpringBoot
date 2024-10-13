package com.yxf.CoolTalk.service.impl;


import com.yxf.CoolTalk.dao.EsCoolTalkScrollContentDao;
import com.yxf.CoolTalk.nosql.elasticsearch.document.EsCoolTalkScrollContent;
import com.yxf.CoolTalk.nosql.elasticsearch.repository.EsCoolTalkScrollContentRepository;
import com.yxf.CoolTalk.service.EsScrollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @auther macrozheng
 * @description 搜索商品管理Service实现类
 * @date 2018/6/19
 * @github https://github.com/macrozheng
 */
@Service
public class EsScrollServiceImpl implements EsScrollService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsScrollServiceImpl.class);
    @Autowired
    private EsCoolTalkScrollContentDao contentDao;
    @Autowired
    private EsCoolTalkScrollContentRepository contentRepository;
    @Override
    public int importAll() {
        List<EsCoolTalkScrollContent> esContentList = contentDao.getAllEsContentList(null);
        Iterable<EsCoolTalkScrollContent> esContentIterable = contentRepository.saveAll(esContentList);
        Iterator<EsCoolTalkScrollContent> iterator = esContentIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    @Override
    public EsCoolTalkScrollContent create(String id) {
        EsCoolTalkScrollContent result = null;
        List<EsCoolTalkScrollContent> esContentList = contentDao.getAllEsContentList(id);
        if (esContentList.size() > 0) {
            EsCoolTalkScrollContent esContent = esContentList.get(0);
            result = contentRepository.save(esContent);
        }
        return result;
    }

    @Override
    public void delete(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsCoolTalkScrollContent> esContentList = new ArrayList<>();
            for (String id : ids) {
                EsCoolTalkScrollContent esContent = new EsCoolTalkScrollContent();
                esContent.setId(id);
                esContentList.add(esContent);
            }
            contentRepository.deleteAll(esContentList);
        }
    }

    @Override
    public Page<EsCoolTalkScrollContent> search(String text, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return contentRepository.findByText(text, pageable);
    }

}
