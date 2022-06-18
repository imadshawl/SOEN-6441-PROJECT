package components;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for DateTimeUtils class.
 */
public class DateTimeUtilsTest {

    /**
     * This test checks the response from getDateStringFromMillis method.
     * The formatted date string should be of the form "YYYY-MM-DD" for any time in millis.
     */
    @Test
    public void test_getDateStringFromMillis_Expect_DateTimeString() {
        final String expectedResult = "2022-03-14";
        final String actualResult = DateTimeUtils.getDateStringFromMillis(new Long(1647289129));
        assertEquals(expectedResult, actualResult);
    }
}
