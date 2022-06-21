package cn.st4rlight.util.response;

import lombok.Getter;

/**
 * rest请求通用返回码
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-21
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    SYSTEM_ERROR(500, "系统内部异常")
    ;


    @Getter
    private final Integer code;
    @Getter
    private final String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
