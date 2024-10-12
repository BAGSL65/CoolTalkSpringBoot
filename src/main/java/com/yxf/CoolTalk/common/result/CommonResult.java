package com.yxf.CoolTalk.common.result;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResult<T> {
    private long code;
    private String msg;
    private T data;

    /**
     * 成功返回结果
     * @param data 获取数据
     */
    public static<T> CommonResult<T> success(T data){
        return new CommonResult<T>(EnumResultCode.SUCCESS.getCode(),EnumResultCode.SUCCESS.getMessage(),data);
    }
    /**
     * 成功返回结果（自定义信息）
     * @param data 获取数据
     * @param msg 提示信息
     */
    public static<T> CommonResult<T> success(T data,String msg){
        return new CommonResult<T>(EnumResultCode.SUCCESS.getCode(), msg,data);
    }
    /**
     * 失败返回结果
     */
    public static<T> CommonResult<T> fail(){
        return new CommonResult<T>(EnumResultCode.FAILED.getCode(), EnumResultCode.FAILED.getMessage(),null);
    }

    /**
     * 失败返回结果（自定义信息）
     * @param msg 提示信息
     */
    public static<T> CommonResult<T> fail(String msg){
        return new CommonResult<T>(EnumResultCode.FAILED.getCode(), msg,null);
    }

    /**
     * 失败返回结果（自定义响应码）
     * @param res 错误响应码
     */
    public static<T> CommonResult<T> fail(IResultCode res){
        return new CommonResult<T>(res.getCode(), res.getMessage(),null);
    }


    public static<T> CommonResult<T> unauthorized(String msg) {
        return new CommonResult<T>(EnumResultCode.UNAUTHORIZED.getCode(),msg,null);
    }

    public static<T> CommonResult<T> forbidden(String msg) {
        return new CommonResult<T>(EnumResultCode.FORBIDDEN.getCode(),msg,null);
    }

    public static<T> CommonResult<T> validateFailed(String msg) {
        return new CommonResult<T>(EnumResultCode.VALIDATE_FAILED.getCode(),msg,null);
    }
}
