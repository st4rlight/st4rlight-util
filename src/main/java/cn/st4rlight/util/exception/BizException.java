package cn.st4rlight.util.exception;

import cn.st4rlight.util.response.ResultCode;

/**
 * 业务异常
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-21
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -6388002904445520011L;

    private ResultCode resultCode;


    public static BizException of(String errMsg) {
        return new BizException();
    }
}
