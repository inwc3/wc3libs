package wc3libs.slk;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class SLKTest {
    public static void assertLinesEqual(String actualString, String expectedString) {
        BufferedReader expectedLinesReader = new BufferedReader(new StringReader(expectedString));
        BufferedReader actualLinesReader = new BufferedReader(new StringReader(actualString));

        try {
            int lineNumber = 0;

            String actualLine;
            while ((actualLine = actualLinesReader.readLine()) != null) {
                String expectedLine = expectedLinesReader.readLine();
                Assert.assertEquals(actualLine, expectedLine, "Line " + lineNumber);
                lineNumber++;
            }

            if (expectedLinesReader.readLine() != null) {
                Assert.fail("Actual string does not contain all expected lines");
            }
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        } finally {
            try {
                expectedLinesReader.close();
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
            try {
                actualLinesReader.close();
            } catch (IOException e) {
                Assert.fail(e.getMessage());
            }
        }
    }

}
