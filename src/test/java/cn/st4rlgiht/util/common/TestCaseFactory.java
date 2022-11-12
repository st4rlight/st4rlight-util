package cn.st4rlgiht.util.common;

import java.util.Arrays;
import java.util.List;

import cn.st4rlgiht.util.common.TestCase.InnerNode;

/**
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-11-12
 */
public class TestCaseFactory {

    public static List<TestCase> getTestCaseList() {
        InnerNode innerNode1 = InnerNode.builder()
                .innerBasic(1)
                .innerComplex("inner_string")
                .build();
        TestCase testCase1 = TestCase.builder()
                .boolValue(false)
                .intValue(100)
                .longValue(100)
                .wrapInt(100)
                .wrapLong(100L)
                .stringValue("test")
                .innerNode(innerNode1)
                .build();

        InnerNode innerNode2 = InnerNode.builder()
                .innerBasic(2)
                .innerComplex(null)
                .build();
        TestCase testCase2 = TestCase.builder()
                .boolValue(true)
                .intValue(200)
                .longValue(200)
                .wrapInt(null)
                .wrapLong(null)
                .stringValue(null)
                .innerNode(innerNode2)
                .build();

        return Arrays.asList(testCase1, testCase2, null);
    }
}
