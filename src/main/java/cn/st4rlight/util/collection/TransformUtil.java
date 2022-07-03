package cn.st4rlight.util.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

import cn.st4rlight.util.value.DefaultUtil;

/**
 * 集合类型转换
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-11
 */
public class TransformUtil {

    private TransformUtil() {
    }


    /**
     * entryToMap
     */
    public static <K, V> Map<K, V> entrySetToMap(Set<Map.Entry<K, V>> entrySet) {
        return toMap(entrySet, Map.Entry::getKey, Map.Entry::getValue);
    }

    /**
     * toList
     */
    public static <T> List<T> toList(Set<T> originSet) {
        return safeToStream(originSet).collect(Collectors.toList());
    }
    public static <T, R> List<R> toList(List<T> originList, Function<T, R> mapFunction) {
        return safeToStream(originList).map(mapFunction).collect(Collectors.toList());
    }
    public static <T, R> List<R> toList(Set<T> originSet, Function<T, R> mapFunction) {
        return safeToStream(originSet).map(mapFunction).collect(Collectors.toList());
    }

    /**
     * toSet
     */
    public static <T> Set<T> toSet(List<T> originList) {
        return safeToStream(originList).collect(Collectors.toSet());
    }
    public static <T, R> Set<R> toSet(List<T> originList, Function<T, R> mapFunction) {
        return safeToStream(originList).map(mapFunction).collect(Collectors.toSet());
    }
    public static <T, R> Set<R> toSet(Set<T> originSet, Function<T, R> mapFunction) {
        return safeToStream(originSet).map(mapFunction).collect(Collectors.toSet());
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

    /**
     * distinct
     */
    public static <T, R> Predicate<T> distinctByKey(Function<? super T, R> keyMapper) {
        Set<R> everSeen = ConcurrentHashMap.newKeySet();
        return t -> everSeen.add(keyMapper.apply(t));
    }

    /**
     * toMap
     */
    public static <T, K> Map<K, T> toMap(List<T> originList, Function<T, K> keyMapper) {
        return toMap(originList, keyMapper, Function.identity());
    }
    public static <T, K> Map<K, T> toMap(Set<T> originSet, Function<T, K> keyMapper) {
        return toMap(originSet, keyMapper, Function.identity());
    }
    public static <T, K, V> Map<K, V> toMap(List<T> originList, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return safeToStream(originList)
                .filter(Objects::nonNull)
                .collect(HashMap::new,
                    (m, t) -> m.put(keyMapper.apply(t), valueMapper.apply(t)),
                    HashMap::putAll
                );
    }
    public static <T, K, V> Map<K, V> toMap(Set<T> originSet, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return toMap(toList(originSet), keyMapper, valueMapper);
    }

    /**
     * groupByToList
     */
    public static <T, K> Map<K, List<T>> groupByToList(List<T> originList, Function<T, K> keyMapper) {
        return safeToStream(originList).collect(Collectors.groupingBy(keyMapper));
    }
    public static <T, K, V> Map<K, List<V>> groupByToList(List<T> originList, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        // 第一层map转换
        Map<K, List<T>> map = safeToStream(originList)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(keyMapper));
        // 第二层转换
        Map<K, List<V>> resultMap = Maps.newHashMap();
        map.forEach((key, value) -> {
            List<V> valueList = toList(value, valueMapper);
            resultMap.put(key, valueList);
        });

        return resultMap;
    }
    public static <T, K, V> Map<K, List<V>> groupByToList(Set<T> originSet, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return groupByToList(toList(originSet), keyMapper, valueMapper);
    }
}
