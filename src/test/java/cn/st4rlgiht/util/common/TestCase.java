package cn.st4rlgiht.util.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-11-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    private boolean boolValue;

    private int intValue;

    private long longValue;

    private Integer wrapInt;

    private Long wrapLong;

    private String stringValue;

    private InnerNode innerNode;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InnerNode {
        private int innerBasic;

        private String innerComplex;
    }
}
