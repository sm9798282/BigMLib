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
 * @since   AugList V1
 * @version MultiTest V1
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     tests.ALFactoryTest
 * @see     tests.AugListTest
 * @see     tests.TickerTest
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
