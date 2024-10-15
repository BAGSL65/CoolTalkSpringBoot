package com.yxf.CoolTalk.nosql.mongoDB.document;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class CoolTalkReview {
    private String id;
    private String authorId;
    private String content;
    private Date createTime;
}
