package cn.st4rlight.util.collection;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.st4rlight.util.value.DefaultUtil;

/**
 * 集合类型转换
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-11
 */
public class TransformUtil {

    /**
     * toList
     */
    public static <T, R> List<R> toList(List<T> originList, Function<T, R> mapFunction) {
        return safeToStream(originList).map(mapFunction).collect(Collectors.toList());
    }
    public static <T, R> List<R> toList(Set<T> originSet, Function<T, R> mapFunction) {
        return safeToStream(originSet).map(mapFunction).collect(Collectors.toList());
    }

    /**
     * toSet
     */
    public static <T, R> Set<R> toSet(List<T> originList, Function<T, R> mapFunction) {
        return safeToStream(originList).map(mapFunction).collect(Collectors.toSet());
    }
    public static <T, R> Set<R> toSet(Set<T> originSet, Function<T, R> mapFunction) {
        return safeToStream(originSet).map(mapFunction).collect(Collectors.toSet());
    }

    /**
     * toMap
     */
    public static <T, R> Map<R, List<T>> groupingBy(List<T> originList, Function<T, R> keyMapper) {
        return safeToStream(originList).collect(Collectors.groupingBy(keyMapper));
    }
    public static <T, R, V> Map<R, List<V>> groupingBy(List<T> originList) {
        return null;
    }


    /**
     * toStream
     */
    public static <T> Stream<T> safeToStream(List<T> originList) {
        return DefaultUtil.nullToDefault(originList).stream().filter(Objects::nonNull);
    }
    public static <T> Stream<T> safeToStream(Set<T> originSet) {
        return DefaultUtil.nullToDefault(originSet).stream().filter(Objects::nonNull);
    }
}
