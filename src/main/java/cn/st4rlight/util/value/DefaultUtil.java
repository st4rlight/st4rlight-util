package cn.st4rlight.util.value;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 默认值
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-11
 */
public class DefaultUtil {

    public static Long nullToDefault(Long value) {
        return Objects.nonNull(value) ? value : 0L;
    }

    public static String blankToDefault(String value) {
        return StringUtils.defaultIfBlank(value, StringUtils.EMPTY);
    }

    public static <T> List<T> nullToDefault(List<T> list) {
        return Objects.nonNull(list) ? list : Lists.newArrayList();
    }

    public static <T> T nullToDefault(T value, T defaultValue) {
        return Objects.nonNull(value) ? value : defaultValue;
    }

    public static <T, R> R getOrDefault(T t, Function<T, R> function, R defaultValue) {
        return Objects.nonNull(t) ? function.apply(t) : defaultValue;
    }
}
