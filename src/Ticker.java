/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
package src;

import java.util.Objects;

/**
 * Provides increasing values with every call.
 * @version Ticker Version 1
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     tests.TickerTest
 */
public class Ticker implements BMCloneable, BMEQable {
    /**
     * Current value of the ticker.
     */
    private int value = 0;

    /**
     * Whether or not the most recent call on the {@link Ticker} reset it.
     */
    private boolean justReset = false;

    /**
     * Create a new {@link Ticker} with the NEXT value being 0.
     * @see         tests.TickerTest#testInstantiateBlank
     * @overloads   {@link #Ticker()}, {@link #Ticker(int)}
     * @tags        Constructor
     */
    public Ticker() {
        reset();
    }

    /**
     * Create a new {@link Ticker}, starting at the given value.
     * @param       value
     *              The value to start this {@link Ticker} at.
     * @see         tests.TickerTest#testInstantiateValue
     * @overloads   {@link #Ticker()}, {@link #Ticker(int)}
     * @note        Also sets the justReset flag to false.
     * @tags        Constructor
     */
    public Ticker(int value) {
        justReset = false;
        this.value = value;
    }

    /**
     * Used to create a clone of this {@link Ticker}.
     * @param   value
     *          The value.
     * @param   justReset
     *          Whether or not this ticker should act as if it were just reset or not.
     * @see     tests.TickerTest#testClone()
     * @note    As this is a private method, it is not listed as an overload.
     * @tags    Constructor
     */
    private Ticker(int value, boolean justReset)
    {
        this.justReset = justReset;
        this.value = value;
    }

    /**
     * @return  A clone of this {@link Ticker}.
     * @see     BMCloneable
     * @tags    Creator
     */
    @Override
    public Ticker clone() {
        return new Ticker(value, justReset);
    }

    /**
     * If the given {@link Object} is an instance of any of the following, it is considered equivalent IF:
     * <p>- {@link Integer}: EITHER this ticker hasn't just reset and its value matches the given {@link Integer};
     * - {@link Integer}: OR has the same {@link #hashCode()} as this {@link Ticker}.
     * - {@link String}: Same as {@link #toString()}.
     * - {@link Ticker}: The value is the same and the justReset flag is the same
     * <p>Any other type of Object will fail to evaluate as equivalent.
     * @param   o
     *          The {@link Object} to find equivalence against.
     * @return  Equivalence
     * @see     tests.TickerTest#testIsEquivalent()
     * @tags    Terminator
     */
    public boolean isEquivalent(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        if (o instanceof Integer) {
            return isEquivalent(new Ticker((Integer)(o))) || hashCode() == o.hashCode();
        }
        if (o instanceof Ticker) {
            return value == ((Ticker)o).value && justReset == ((Ticker)o).justReset;
        }
        if (o instanceof String) {
            return toString().equals(o);
        }
        return false;
    }

    /**
     * Tick this {@link Ticker}, advancing the value once.
     * @return  The calculated value.
     * @see     tests.TickerTest#testTick
     * @tags    Mutator
     */
    public int tick() {
        justReset = false;
        value += 1;
        return value; 
    }

    /**
     * Tick this {@link Ticker} N times, advancing the value by that much.
     * @param   N
     *          The number of times to tick. If negative, set to 0.
     * @return  The calculated value.
     * @see     tests.TickerTest#testTickN
     * @note    Hard-coded to set the value to 0 and return 0 if this is the first time calling this function after a reset.
     * @tags    Mutator
     */
    public int tickN(int N) {
        if (justReset) {
            justReset = false;
            value = 0;
            return 0;
        }
        justReset = false;
        if (N < 0) {
            N = 0;
        }
        value += N;
        return value;
    }

    /**
     * Resets this {@link Ticker} so the NEXT value to be retuned by {@link #tick()} is 0.
     * @return  This {@link Ticker}.
     * @see     tests.TickerTest#testReset
     * @tags    Mutator
     */
    public Ticker reset() {
        justReset = true;
        value = -1;
        return this;
    }

    /**
     * Sets this {@link Ticker} to the given value.
     * @param   value
     *          The value in question.
     * @return  This {@link Ticker}.
     * @see     tests.TickerTest#testSet()
     * @tags    Mutator
     */
    public Ticker set(int value) {
        justReset = false;
        this.value = value;
        return this;
    }

    /**
     * @return  The value of this {@link Ticker}.
     * @see     tests.TickerTest#testToString
     * @tags    Terminator
     */
    @Override
    public String toString() {
        justReset = false;
        return "V" + value;
    }
}
