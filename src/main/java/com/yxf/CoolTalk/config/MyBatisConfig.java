package com.yxf.CoolTalk.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.yxf.CoolTalk.mbg.mapper","com.yxf.CoolTalk.dao"})
public class MyBatisConfig {
}
