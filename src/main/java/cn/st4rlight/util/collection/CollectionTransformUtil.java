package cn.st4rlight.util.collection;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合类型转换
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-11
 */
public class CollectionTransformUtil {

    /**
     * toList
     */
    public static <T, R> List<R> toList(List<T> originList, Function<T, R> mapFunction) {
        return originList.stream().filter(Objects::nonNull)
                .map(mapFunction)
                .collect(Collectors.toList());
    }
    public static <T, R> List<R> toList(Set<T> originSet, Function<T, R> mapFunction) {
        return originSet.stream().filter(Objects::nonNull)
                .map(mapFunction)
                .collect(Collectors.toList());
    }

    /**
     * toSet
     */
    public static <T, R> Set<R> toSet(List<T> originList, Function<T, R> mapFunction) {
        return originList.stream().filter(Objects::nonNull)
                .map(mapFunction)
                .collect(Collectors.toSet());
    }
    public static <T, R> Set<R> toSet(Set<T> originSet, Function<T, R> mapFunction) {
        return originSet.stream().filter(Objects::nonNull)
                .map(mapFunction)
                .collect(Collectors.toSet());
    }
}
