package cn.st4rlight.util.execute;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;

import cn.st4rlight.util.exception.ServiceException;
import cn.st4rlight.util.lambda.ThrowableRunnable;
import lombok.extern.slf4j.Slf4j;

/**
 * 执行与重试工具类，默认执行1次，重试2次
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-27
 */
@Slf4j
public class MoreRetry {

    private MoreRetry() { /*空构造器，工具类禁止实例化*/ }

    /**
     * 默认重试次数，执行1次 + 重试n次
     */
    private static final int DEFAULT_RETRY_TIMES = 2;
    /**
     * 重试间隔时间 / 秒
     */
    private static final int RETRY_SLEEP_SEC = 1;


    /**
     * 带重试的执行, 默认重试2次
     *
     * @param callable 需要执行的可能失败的任务, 带返回值, 可抛出异常
     * @return 任务返回值
     * @throws ServiceException 全部执行失败时抛出异常
     */
    public static <T> T runWithRetry(Callable<T> callable) throws ServiceException {
        return runWithRetry(callable, DEFAULT_RETRY_TIMES);
    }
    /**
     * 带重试的执行
     *
     * @param callable 需要执行的可能失败的任务, 带返回值, 可抛出异常
     * @param retryTimes 重试次数
     * @return 任务返回值
     */
    public static <T> T runWithRetry(Callable<T> callable, int retryTimes) throws ServiceException {
        int runCnt = 0;
        Throwable throwable = null;

        while (runCnt < retryTimes  + 1) {
            try {
                return callable.call();
            } catch (Exception ex) {
                log.error("第 {} 次执行失败, {}s 后重试", ++runCnt, RETRY_SLEEP_SEC, ex);
                throwable = ex;
                Uninterruptibles.sleepUninterruptibly(RETRY_SLEEP_SEC, TimeUnit.SECONDS);
            }
        }

        throw ServiceException.of(String.format("执行 %d 次后仍然失败", runCnt), throwable);
    }


    /**
     * 带重试的执行, 默认重试2次
     *
     * @param runnable 需要执行的可能失败的任务, 不带返回值, 可抛出异常
     * @return 是否执行成功
     */
    public static boolean runWithRetryReturnBool(ThrowableRunnable runnable) {
        return runWithRetryReturnBool(runnable, DEFAULT_RETRY_TIMES);
    }
    /**
     * 带重试的执行
     *
     * @param runnable 需要执行的可能失败的任务, 不带返回值, 可抛出异常
     * @param retryTimes 重试次数
     * @return 是否执行成功
     */
    public static boolean runWithRetryReturnBool(ThrowableRunnable runnable, int retryTimes) {
        try {
            runWithRetry(runnable, retryTimes);
            return true;
        } catch (Exception ex) {
            log.warn("", ex);
            return false;
        }
    }


    /**
     * 带重试的执行, 默认重试2次
     *
     * @param runnable 需要执行的可能失败的任务, 不带返回值, 可抛出异常
     * @throws ServiceException 全部执行失败时抛出异常
     */
    public static void runWithRetry(ThrowableRunnable runnable) throws ServiceException {
        runWithRetry(runnable, DEFAULT_RETRY_TIMES);
    }
    /**
     * 带重试的执行
     *
     * @param runnable 需要执行的可能失败的任务, 不带返回值, 可抛出异常
     * @param retryTimes 重试次数
     * @throws ServiceException 全部执行失败时抛出异常
     */
    public static void runWithRetry(ThrowableRunnable runnable, int retryTimes) throws ServiceException {
        int runCnt = 0;
        Throwable throwable = null;

        while (runCnt < retryTimes  + 1) {
            try {
                runnable.run();
                return;
            } catch (Throwable ex) {
                log.error("第 {} 次执行失败, {}s 后重试", ++runCnt, RETRY_SLEEP_SEC, ex);
                throwable = ex;
                Uninterruptibles.sleepUninterruptibly(RETRY_SLEEP_SEC, TimeUnit.SECONDS);
            }
        }

        throw ServiceException.of(String.format("执行 %d 次后仍然失败", runCnt), throwable);
    }
}
