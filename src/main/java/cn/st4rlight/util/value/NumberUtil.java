package cn.st4rlight.util.value;

import java.util.Objects;

/**
 * 数值判断等
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-12
 */
public class NumberUtil {

    public static boolean isPositive(Long num) {
        return Objects.nonNull(num) && num > 0L;
    }

    public static boolean isPositive(Integer num) {
        return Objects.nonNull(num) && num > 0;
    }
}
