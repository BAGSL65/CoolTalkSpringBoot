package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther macrozheng
 * @description Redis测试Controller
 * @date 2020/3/3
 * @github https://github.com/macrozheng
 */
@Api(tags = "RedisController", description = "Redis测试")
@Controller
@RequestMapping("/Redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PreAuthorize("hasAuthority('Redis:visit')")
    @ApiOperation("测试简单缓存")
    @RequestMapping(value = "/visit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> visit() {
        String key = "redis:simple:visit";
        if(redisService.hasKey(key)){
            redisService.incr(key, 1);
        }else{
            redisService.set(key,1,24*60*60);
        }
        Integer res = (Integer)redisService.get(key);
        return CommonResult.success(res);
    }
}
