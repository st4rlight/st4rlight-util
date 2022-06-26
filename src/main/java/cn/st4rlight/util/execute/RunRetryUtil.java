package cn.st4rlight.util.execute;

import lombok.extern.slf4j.Slf4j;

/**
 * 执行与重试工具类，默认执行1次，重试2次
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-27
 */
@Slf4j
public class RunRetryUtil {

    /**
     * 默认最大总共执行次数，执行1次 + 重试n-1次
     */
    private static final int MAX_TRY_TIMES = 3;

    /**
     * 重试间隔时间 / 秒
     */
    private static final int RETRY_SLEEP_SEC = 1;
}
