package cn.st4rlight.util.Reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * @author st4rlight <st4rlight@163.com>
 * Created on 2023-05-21
 */
public class MoreReflect {

    private MoreReflect() {
        throw new UnsupportedOperationException();
    }

    /**
     * Type转Class
     * @from mybatis-3
     */
    private Class<?> typeToClass(Type src) {
        Class<?> result = null;
        if (src instanceof Class) {
            result = (Class<?>) src;
        } else if (src instanceof ParameterizedType) {
            result = (Class<?>) ((ParameterizedType) src).getRawType();
        } else if (src instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) src).getGenericComponentType();

            if (componentType instanceof Class) {
                result = Array.newInstance((Class<?>) componentType, 0).getClass();
            } else {
                Class<?> componentClass = typeToClass(componentType);
                result = Array.newInstance(componentClass, 0).getClass();
            }
        }

        return result != null ? result : Object.class;
    }

    /**
     * 获取一个类的默认无参构造函数
     */
    private Optional<Constructor<?>> getDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        return Arrays.stream(constructors)
                .filter(constructor -> constructor.getParameterTypes().length == 0)
                .findAny();
    }

    /**
     * 是否是用户自定义属性名
     * NOTE: 这里不考虑无效的命名规则，比如数字开头
     */
    private boolean isValidPropertyName(String name) {
        return (!name.startsWith("$") && !"serialVersionUID".equals(name) && !"class".equals(name));
    }

    /**
     * 判断一个类是否是容器类
     */
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
