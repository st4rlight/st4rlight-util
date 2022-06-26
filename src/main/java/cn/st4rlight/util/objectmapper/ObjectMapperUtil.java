package cn.st4rlight.util.objectmapper;

import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 序列化相关
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-22
 */
public class ObjectMapperUtil {

    /**
     * 关闭INTERN_FIELD_NAMES用于解决GC压力大，内存泄露问题
     */
    public static final ObjectMapper MAPPER =
            new ObjectMapper(new JsonFactory().disable(Feature.INTERN_FIELD_NAMES))
                    .registerModule(new GuavaModule())
                    .registerModule(new JavaTimeModule());

    static {
        // 允许未知属性
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 允许不加引号的key
        MAPPER.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        // 允许注释
        MAPPER.enable(JsonParser.Feature.ALLOW_COMMENTS);
    }



    public static String toJson(@Nullable Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T fromJson(@Nullable String json, Class<T> valueType) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
