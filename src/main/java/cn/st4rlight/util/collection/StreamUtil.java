package cn.st4rlight.util.collection;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.st4rlight.util.value.DefaultUtil;

/**
 * 对java8的stream功能做扩展封装
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-10-15
 */
public class StreamUtil {

    private StreamUtil() { /*空构造器，工具类禁止实例化*/ }

    /**
     * 扩充常用的stream
     */
    public static <T> Collection<T> filter(Collection<T> originCollection, Predicate<T> predicate) {
        return safeToStream(originCollection).filter(predicate).collect(Collectors.toList());
    }

    /**
     * toStream
     */
    public static <T> Stream<T> safeToStream(Collection<T> originCollection) {
        return DefaultUtil.nullToDefault(originCollection).stream().filter(Objects::nonNull);
    }
}
