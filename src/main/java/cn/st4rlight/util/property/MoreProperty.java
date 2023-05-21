package cn.st4rlight.util.property;

import java.util.Locale;

/**
 * 从mybatis-3中得到的一些工具类
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2023-05-21
 */
public class MoreProperty {

    public MoreProperty() {
        throw new UnsupportedOperationException();
    }


    /**
     * 从方法名中获取字符名
     */
    public static String methodToProperty(String methodName) {
        if (methodName.startsWith("is")) {
            methodName = methodName.substring(2);
        } else if (methodName.startsWith("get") || methodName.startsWith("set")) {
            methodName = methodName.substring(3);
        } else {
            throw new IllegalStateException(
                    "Error parsing property methodName '" + methodName + "'.  Didn't start with 'is', 'get' or 'set'.");
        }

        // 把字段变成小写开头（除非两个连续大写开头）
        if (methodName.length() == 1 || methodName.length() > 1 && !Character.isUpperCase(methodName.charAt(1))) {
            // 只有一个字符，或者多余1个字符且第二个字符是小写（如果是大写一般这个字段就是全大写）
            methodName = methodName.substring(0, 1).toLowerCase(Locale.ENGLISH) + methodName.substring(1);
        }

        return methodName;
    }

    /**
     * 判断方法是不是一个字段操作
     */
    public static boolean isProperty(String methodName) {
        return isGetter(methodName) || isSetter(methodName);
    }

    /**
     * 一般getter是get开头或is开头（bool类型）
     */
    public static boolean isGetter(String methodName) {
        return methodName.startsWith("get") && methodName.length() > 3 || methodName.startsWith("is") && methodName.length() > 2;
    }

    /**
     * setter一般set开头
     */
    public static boolean isSetter(String methodName) {
        return methodName.startsWith("set") && methodName.length() > 3;
    }
}
