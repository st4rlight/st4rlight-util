package cn.st4rlight.util.value;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 默认值
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-11
 */
public class DefaultUtil {

    /**
     * null则转换成各类型默认值
     */
    public static Long nullToDefault(Long value) {
        return Objects.nonNull(value) ? value : 0L;
    }
    public static String blankToDefault(String value) {
        return StringUtils.defaultIfBlank(value, StringUtils.EMPTY);
    }
    public static <T> List<T> nullToDefault(List<T> list) {
        return Objects.nonNull(list) ? list : Lists.newArrayList();
    }
    public static <T> Set<T> nullToDefault(Set<T> set) {
        return Objects.nonNull(set) ? set : Sets.newHashSet();
    }
    public static <T> T nullToDefault(T value, T defaultValue) {
        return Objects.nonNull(value) ? value : defaultValue;
    }


    /**
     * null则获取值
     */
    public static <T> T nullOrSupply(T t, Supplier<T> supplier) {
        return Objects.nonNull(t) ? t : supplier.get();
    }
    public static <T, R> R getOrDefault(T t, Function<T, R> getter, R defaultValue) {
        return Objects.nonNull(t) ? getter.apply(t) : defaultValue;
    }
}
