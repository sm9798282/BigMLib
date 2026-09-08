package tests;

import org.junit.Test;

/**
 * Classes that implement this have multiple tests with shared testdata.
 */
public interface MultiTest {
    /**
     * Initialises the data used for testing.
     */
    public void setupTestData();

    /**
     * Triggers all tests.
     */
    @Test
    public void allTests();
}
