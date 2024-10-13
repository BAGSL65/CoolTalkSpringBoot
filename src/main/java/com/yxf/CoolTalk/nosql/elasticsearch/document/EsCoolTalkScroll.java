package com.yxf.CoolTalk.nosql.elasticsearch.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auther macrozheng
 * @description 搜索商品的属性信息
 * @date 2018/6/27
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode
public class EsCoolTalkScroll implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String sc_id;
    @Field(type = FieldType.Keyword)
    private String sc_state;
    @Field(type = FieldType.Date)
    private Date sc_date;
    @Field(type =FieldType.Nested)
    private List<EsCoolTalkScrollContent> contentList;
}
