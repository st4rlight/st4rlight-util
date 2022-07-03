package cn.st4rlight.util.value;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import cn.st4rlight.util.collection.TransformUtil;

/**
 * 数值判断等
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-12
 */
public class NumberUtil {

    private NumberUtil() {
    }


    /**
     * 是否是正数，一般用于判断id是否有效的场景
     */
    public static boolean isPositive(Long num) {
        return Objects.nonNull(num) && num > 0L;
    }
    public static boolean isPositive(Integer num) {
        return Objects.nonNull(num) && num > 0;
    }

    /**
     * 转换并加和
     * NOTE: 使用时确保不会超过数值有效范围
     */
    public static <T> int toIntSum(List<T> originList, Function<T, Integer> mapper) {
        return TransformUtil.safeToStream(originList)
                .map(mapper)
                .map(DefaultUtil::nullToDefault)
                .mapToInt(Integer::intValue)
                .sum();
    }
    public static <T> long toLongSum(List<T> originList, Function<T, Long> mapper) {
        return TransformUtil.safeToStream(originList)
                .map(mapper)
                .map(DefaultUtil::nullToDefault)
                .mapToLong(Long::longValue)
                .sum();
    }
}
