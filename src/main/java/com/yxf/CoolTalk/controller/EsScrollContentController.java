package com.yxf.CoolTalk.controller;

import com.yxf.CoolTalk.common.result.CommonPage;
import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.nosql.elasticsearch.document.EsCoolTalkScrollContent;
import com.yxf.CoolTalk.service.EsScrollService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther macrozheng
 * @description 搜索商品管理Controller
 * @date 2018/6/19
 * @github https://github.com/macrozheng
 */
@Controller
@Api(tags = "EsScrollContentController")
@Tag(name = "EsScrollContentController", description = "搜索商品管理")
@RequestMapping("/esContent")
public class EsScrollContentController {
    @Autowired
    private EsScrollService esScrollService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = esScrollService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        esScrollService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<String> ids) {
        esScrollService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<EsCoolTalkScrollContent> create(@PathVariable String id) {
        EsCoolTalkScrollContent esProduct = esScrollService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.fail();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsCoolTalkScrollContent>> search(@RequestParam(required = false) String keyword,
                                                                    @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsCoolTalkScrollContent> esProductPage = esScrollService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}
