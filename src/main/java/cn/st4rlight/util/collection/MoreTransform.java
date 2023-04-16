package cn.st4rlight.util.collection;

import static cn.st4rlight.util.collection.MoreStream.safeToStream;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

/**
 * 集合类型转换
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-11
 */
public class MoreTransform {

    private MoreTransform() {
        throw new UnsupportedOperationException();
    }


    /**
     * toList
     */
    public static <T> List<T> toList(Collection<T> originCollection) {
        return safeToStream(originCollection).collect(Collectors.toList());
    }
    public static <T, R> List<R> toList(Collection<T> originCollection, Function<T, R> mapFunction) {
        return safeToStream(originCollection).map(mapFunction).collect(Collectors.toList());
    }

    /**
     * toSet
     */
    public static <T> Set<T> toSet(Collection<T> originCollection) {
        return safeToStream(originCollection).collect(Collectors.toSet());
    }
    public static <T, R> Set<R> toSet(List<T> originCollection, Function<T, R> mapFunction) {
        return safeToStream(originCollection).map(mapFunction).collect(Collectors.toSet());
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
    public static <T, K> Map<K, T> toMap(Collection<T> originCollection, Function<T, K> keyMapper) {
        return toMap(originCollection, keyMapper, Function.identity());
    }
    public static <T, K, V> Map<K, V> toMap(
        Collection<T> originCollection, Function<T, K> keyMapper, Function<T, V> valueMapper
    ) {
        return safeToStream(originCollection).collect(HashMap::new,
                    (map, item) -> map.put(keyMapper.apply(item), valueMapper.apply(item)),
                    HashMap::putAll
                );
    }

    /**
     * entryToMap
     */
    public static <K, V> Map<K, V> entrySetToMap(Set<Map.Entry<K, V>> entrySet) {
        return toMap(entrySet, Map.Entry::getKey, Map.Entry::getValue);
    }

    /**
     * groupByToList
     */
    public static <T, K> Map<K, List<T>> groupByToList(Collection<T> originCollection, Function<T, K> keyMapper) {
        return safeToStream(originCollection).collect(Collectors.groupingBy(keyMapper));
    }
    public static <T, K, V> Map<K, List<V>> groupByToList(
        Collection<T> originCollection, Function<T, K> keyMapper, Function<T, V> valueMapper
    ) {
        // 第一层map转换
        Map<K, List<T>> map = groupByToList(originCollection, keyMapper);
        // 第二层转换
        Map<K, List<V>> resultMap = Maps.newHashMap();
        map.forEach((key, value) -> {
            List<V> valueList = toList(value, valueMapper);
            resultMap.put(key, valueList);
        });

        return resultMap;
    }
}
