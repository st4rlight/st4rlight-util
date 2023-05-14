package cn.st4rlight.util.execute;

import java.util.Optional;
import java.util.concurrent.Callable;

import cn.st4rlight.util.lambda.ThrowableRunnable;
import lombok.extern.slf4j.Slf4j;

/**
 * @author st4rlight <st4rlight@163.com>
 * Created on 2023-05-14
 */
@Slf4j
public class MoreRunnable {

    private MoreRunnable() {
        throw new UnsupportedOperationException();
    }

    /**
     * 简单封装，用于不想在代码中直接try-catch
     */
    public static void runUncheck(ThrowableRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void runIgnoreException(ThrowableRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    /**
     * 简单封装，用于不想在代码中直接try-catch
     */
    public static <T> T getUncheck(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static <T> Optional<T> getIgnoreException(Callable<T> callable) {
        try {
            return Optional.ofNullable(callable.call());
        } catch (Exception ex) {
            log.error("", ex);
        }

        return Optional.empty();
    }
}
