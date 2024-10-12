package com.yxf.CoolTalk.config;

import com.yxf.CoolTalk.po.AdminUserDetails;
import com.yxf.CoolTalk.service.UmsAdminService;
import com.yxf.CoolTalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @auther macrozheng
 * @description Mall安全自定义配置，用于配置如何获取用户信息
 * @date 2022/5/20
 * @github https://github.com/macrozheng
 */
@Configuration
public class CoolTalkSecurityConfig {

    @Autowired
    private UmsAdminService umsAdminService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            AdminUserDetails admin = umsAdminService.getAdminByUsername(username);
            if (admin != null) {
                return admin;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }
}
