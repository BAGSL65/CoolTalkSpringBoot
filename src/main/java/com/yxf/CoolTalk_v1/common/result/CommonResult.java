package com.yxf.CoolTalk_v1.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResult<T> {
    int code;
    String msg;
    T data;
    public static<T> CommonResult<T> success(T data){
        return new CommonResult<T>(200,"请求成功",data);
    }
    public static<T> CommonResult<T> fail(T data){
        return new CommonResult<T>(400,"请求失败",data);
    }
}
