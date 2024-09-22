package com.yxf.CoolTalk.common.result;

/**
 * @program: CoolTalkSpringBoot
 * @description: 错误码信息枚举类
 * @author: YangXueFeng
 * @create: 2024-09-19 15:49
 **/
public enum EnumResultCode implements IResultCode{
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    private EnumResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
