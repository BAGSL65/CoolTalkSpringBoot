package com.yxf.CoolTalk.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.yxf.CoolTalk.common.utils.JwtTokenUtil;
import com.yxf.CoolTalk.po.AdminUserDetails;
import com.yxf.CoolTalk.po.UmsResource;
import com.yxf.CoolTalk.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther macrozheng
 * @description 后台用户管理Service实现类
 * @date 2020/10/15
 * @github https://github.com/macrozheng
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    /**
     * 存放默认用户信息
     */
    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();
    /**
     * 存放默认资源信息
     */
    private List<UmsResource> resourceList = new ArrayList<>();
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init(){
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("redis")
                .password(passwordEncoder.encode("redis"))
                .authorityList(CollUtil.toList("Redis:visit"))
                .build());
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("rabbit")
                .password(passwordEncoder.encode("rabbit"))
                .authorityList(CollUtil.toList("Rabbit:order"))
                .build());
        resourceList.add(UmsResource.builder()
                .id(1L)
                .name("Redis:visit")
                .url("/Redis/visit")
                .build());
        resourceList.add(UmsResource.builder()
                .id(2L)
                .name("Rabbit:order")
                .url("/Rabbit/order")
                .build());
    }
    @Override
    public AdminUserDetails getAdminByUsername(String username) {
        List<AdminUserDetails> findList = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(findList)){
            return findList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsResource> getResourceList() {
        return resourceList;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = getAdminByUsername(username);
            if(userDetails==null){
                return token;
            }
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
