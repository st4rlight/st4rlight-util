package cn.st4rlight.util.collection;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.st4rlgiht.util.common.TestCase;
import cn.st4rlgiht.util.common.TestCaseFactory;

/**
 * {@link cn.st4rlight.util.collection.StreamUtil}
 *
 * @author st4rlight <st4rlight@163.com>
 * Created on 2022-11-12
 */
class StreamUtilTest {

    private List<TestCase> testCaseList;

    @BeforeEach
    public void setUp() {
        this.testCaseList = TestCaseFactory.getTestCaseList();
    }


    @Test
    void safeToStream() {
        Stream<Object> nullStream = StreamUtil.safeToStream(null);
        Assertions.assertNotNull(nullStream);
        Stream<TestCase> testCaseStream = StreamUtil.safeToStream(testCaseList);
        Assertions.assertEquals(testCaseStream.count(), 2);
    }

    @Test
    void filter() {
        List<TestCase> resultCollection = StreamUtil.filter(testCaseList, TestCase::isBoolValue);
        Assertions.assertEquals(resultCollection.size(), 1);
    }
}