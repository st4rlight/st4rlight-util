package cn.st4rlight.util.response;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * rest请求响应
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-21
 */
@Data
@AllArgsConstructor
public class RestResponse<T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 泛型数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String message;


    public static RestResponse<Void> ok() {
        return new RestResponse<>(ResultCode.SUCCESS.getCode(), null, StringUtils.EMPTY);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<>(ResultCode.SUCCESS.getCode(), data, StringUtils.EMPTY);
    }

    public static <T> RestResponse<T> error(ResultCode errorCode, String errMsg) {
        return new RestResponse<>(errorCode.getCode(), null, errMsg);
    }
}
