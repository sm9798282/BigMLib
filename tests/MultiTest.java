package tests;

import org.junit.Test;

/**
 * Classes that implement this have multiple tests with shared testdata.
 */
public interface MultiTest {
    /**
     * Initialise the data used in tests.
     */
    public void setupTestData();

    /**
     * Triggers all tests.
     */
    @Test
    public void allTests();
}
