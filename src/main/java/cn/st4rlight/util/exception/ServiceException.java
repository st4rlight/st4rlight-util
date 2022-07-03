package cn.st4rlight.util.exception;

/**
 * 异常封装
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-07-03
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * 异常封装，避免直接new
     *
     * @param errMsg 自定义错误信息
     * @return 封装异常
     */
    public static ServiceException of(String errMsg) {
        return new ServiceException(errMsg);
    }
    /**
     * 异常封装，避免直接new
     *
     * @param errMsg 自定义错误信息
     * @param throwable 原有异常
     * @return 封装异常
     */
    public static ServiceException of(String errMsg, Throwable throwable) {
        return new ServiceException(errMsg, throwable);
    }
}
