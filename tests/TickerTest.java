/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import src.Ticker;

/**
 * A JUnit powered automatic tester for {@link src.Ticker}.
 * @since   AugList V2
 * @version TickerTest V1
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     src.Ticker
 */
public final class TickerTest implements MultiTest {
    
    Ticker t = new Ticker();

    /**
     * Initialises the test data - t is reset to -1.
     */
    public void setupTestData() {
        t = new Ticker();
    }

    @Test
    public void allTests() {
        setupTestData();
        testInstantiateBlank();
        testInstantiateValue();
        testIsEquivalent();
        testReset();
        testSet();
        testTick();
        testTickN();
        testToString();
    }

    @Test
    public void testClone() {
        setupTestData(); // Resets t to -1.
        // Check value with toString
        // Check justReset by calling tickN
        assertTrue(t.clone().toString().equals("V-1"));
        assertTrue(t.clone().tick() == 0);
        assertTrue(t.clone().tickN(500) == 0);
        t.tick(); // t is now 0 and justReset is false.
        assertTrue(t.clone().toString().equals("V0"));
        assertTrue(t.clone().tick() == 1);
        assertTrue(t.clone().tickN(500) == 500);
    }

    /**
     * JUnit tester for Argumentless Instantiation
     * @see src.Ticker#Ticker()
     */
    @Test
    public void testInstantiateBlank() {
        setupTestData();
        assertTrue(new Ticker().toString().equals("V-1"));
        assertTrue(new Ticker().getClass().toGenericString().equals("public class src.Ticker"));
    }
    
    /**
     * JUnit tester for 1 Argument Instantiation
     * @see src.Ticker#Ticker(int)
     */
    @Test
    public void testInstantiateValue() {
        setupTestData();
        assertTrue(new Ticker(-1).toString().equals("V-1"));
        assertTrue(new Ticker(0).toString().equals("V0"));
        assertTrue(new Ticker(1).toString().equals("V1"));
        assertTrue(new Ticker(11).toString().equals("V11"));
        assertTrue(new Ticker(99).toString().equals("V99"));
        assertTrue(new Ticker(99).getClass().toGenericString().equals("public class src.Ticker"));
    }

    /**
     * JUnit tester for Equivalence
     * @see src.Ticker#isEquivalent()
     */
    @Test
    public void testIsEquivalent() {
        setupTestData();
        assertTrue(t.isEquivalent(t));
        assertTrue(t.isEquivalent(t.clone()));
        assertTrue(t.isEquivalent(t.hashCode()));
        assertFalse(t.isEquivalent(-1)); // t was just reset so value matches, justReset mismatch.
        assertTrue(t.isEquivalent(t.toString())); // t is now considered not reset (as toString() updates the flag)
        assertTrue(t.isEquivalent(new Ticker(-1)));
        assertFalse(t.isEquivalent(t.clone().tick()));
        assertFalse(t.isEquivalent(0));
        assertFalse(t.isEquivalent(""));
        assertFalse(t.isEquivalent(null));
        t.tick(); // t is V0, false
        assertTrue(t.isEquivalent(0)); // t not reset, so can match.
        assertFalse(t.isEquivalent(1.0));
    }

    /**
     * JUnit tester for Resetting
     * @see src.Ticker#reset()
     */
    @Test
    public void testReset() {
        setupTestData();
        assertTrue(t.reset().toString().equals("V-1"));
        assertTrue(new Ticker(100).reset().toString().equals("V-1"));
        assertTrue(new Ticker(200).reset().toString().equals("V-1"));
        assertTrue(new Ticker(711).reset().toString().equals("V-1"));
    }

    /**
     * JUnit tester for Setting
     * @see src.Ticker#set()
     */
    @Test
    public void testSet() {
        setupTestData();
        assertTrue(t.set(100).toString().equals("V100"));
        assertTrue(t.set(-100).toString().equals("V-100"));
    }

    /**
     * JUnit tester for Ticking
     * @see src.Ticker#tick()
     */
    @Test
    public void testTick() {
        setupTestData();
        assertTrue(t.tick() == 0);
        assertTrue(t.tick() == 1);
        assertTrue(t.tick() == 2);
        assertTrue(t.tick() == 3);
        assertTrue(t.reset().tick() == 0);
        assertTrue(t.set(-5).tick() == -4);
    }

    /**
     * JUnit tester for Ticking N times
     * @see src.Ticker#tickN()
     */
    @Test
    public void testTickN() {
        setupTestData();
        assertTrue(t.tickN(0) == 0); // justReset = true
        assertTrue(t.tickN(0) == 0); // = false
        t.reset();
        assertTrue(t.tickN(1) == 0); // = true
        assertTrue(t.tickN(1) == 1); // = false
        assertTrue(t.tickN(1) == 2);
        t.reset();
        assertTrue(t.tickN(2) == 0); // = true
        assertTrue(t.tickN(2) == 2); // = false
        assertTrue(t.tickN(2) == 4);
        t.set(-1);
        assertTrue(t.tickN(2) == 1);
        assertTrue(t.tickN(3) == 4);
        assertTrue(t.tickN(-1) == 4); // Cannot tick backwards, so value stays static.
    }

    /**
     * JUnit tester for {@link String} "cast"
     * @see src.Ticker#toString()
     */
    @Test
    public void testToString() {
        setupTestData();
        assertTrue(t.clone().toString().equals("V-1"));
        t.tick();
        assertTrue(t.clone().toString().equals("V0"));
        assertTrue(new Ticker(11).toString().equals("V11"));
        assertTrue(new Ticker(99).toString().equals("V99"));
        assertTrue(new Ticker(-99).toString().equals("V-99"));
    }
}
