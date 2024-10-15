package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.nosql.mongoDB.document.CoolTalkPost;
import com.yxf.CoolTalk.service.CoolTalkPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther macrozheng
 * @description 会员商品浏览记录管理Controller
 * @date 2018/8/3
 * @github https://github.com/macrozheng
 */
@Controller
@Api(tags = "CoolTalkPostController")
@Tag(name = "CoolTalkPostController", description = "酷淘圈帖子管理")
@RequestMapping("/post")
public class CoolTalkPostController {
    @Autowired
    private CoolTalkPostService coolTalkPostService;

    @ApiOperation("创建帖子")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody CoolTalkPost coolTalkPost) {
        int count = coolTalkPostService.create(coolTalkPost);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.fail();
        }
    }

    @ApiOperation("删除帖子")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = coolTalkPostService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.fail();
        }
    }

    @ApiOperation("根据帖子Id和作者Id查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CoolTalkPost>> list(String postId,String authorId) {
        List<CoolTalkPost> memberReadHistoryList = coolTalkPostService.list(postId, authorId);
        return CommonResult.success(memberReadHistoryList);
    }
}
