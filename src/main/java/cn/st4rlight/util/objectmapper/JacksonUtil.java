package cn.st4rlight.util.objectmapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 序列化相关
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-06-22
 */
public class JacksonUtil {

    public static final ObjectMapper MAPPER =
            new ObjectMapper(new JsonFactory().disable(Feature.INTERN_FIELD_NAMES))
                    .registerModule(new GuavaM);
}
