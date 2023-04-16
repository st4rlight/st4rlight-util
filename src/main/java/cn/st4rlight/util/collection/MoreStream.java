package cn.st4rlight.util.collection;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.st4rlight.util.value.MoreDefault;

/**
 * 对java8的stream功能做扩展封装
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-10-15
 */
public class MoreStream {

    private MoreStream() {
        throw new UnsupportedOperationException();
    }

    /**
     * toStream
     */
    public static <T> Stream<T> safeToStream(Collection<T> originCollection) {
        return MoreDefault.nullToDefault(originCollection).stream().filter(Objects::nonNull);
    }

    /**
     * 扩充常用的stream
     */
    public static <T> List<T> filter(Collection<T> originCollection, Predicate<T> predicate) {
        return safeToStream(originCollection).filter(predicate).collect(Collectors.toList());
    }
}
