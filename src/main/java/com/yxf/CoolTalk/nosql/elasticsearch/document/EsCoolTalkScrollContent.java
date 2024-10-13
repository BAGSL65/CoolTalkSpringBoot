package com.yxf.CoolTalk.nosql.elasticsearch.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * @auther macrozheng
 * @description 搜索商品的信息
 * @date 2018/6/19
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode
@Document(indexName = "sc")
@Setting(shards = 1,replicas = 0)
public class EsCoolTalkScrollContent implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private String id;
    private String sc_id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String text;
    private String imageurl;
    @Field(type = FieldType.Keyword)
    private String content_state;
}
