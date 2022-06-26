package cn.st4rlight.util.lambda;

/**
 * 可抛出异常的、无返回值的Runnable
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-27
 */
@FunctionalInterface
public interface ExceptionalRunnable {

    /**
     * 具体执行逻辑
     *
     * @throws Throwable 各种可能的异常
     */
    void run() throws Throwable;
}
