/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
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
