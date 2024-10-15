package com.yxf.CoolTalk.nosql.mongoDB.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @auther macrozheng
 * @description 用户商品浏览历史记录
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode
@Document
@CompoundIndexes({
        @CompoundIndex(name = "post_id_author_id_index", def = "{'postId': 1, 'authorId': 1}")
})
public class CoolTalkPost {
    @Id
    private String id;
    private String postId;
    private String authorId;
    @Indexed
    private Long likes;
    private String postStatus;
    private String content;
    private List<CoolTalkReview> reviews;
    private Date createTime;
}
