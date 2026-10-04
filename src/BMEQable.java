package src;

import java.util.Spliterator;

/**
 * Any class that implements this has a more lenient equality operator {@link #isEquivalent(Object)}.
 * @since   AugList V2
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     AugList
 * @see     Ticker
 */
public interface BMEQable {
    /**
     * Finds if this Object can be considered Equivalent to the given {@link Object}.
     * Classes that implement {@link BMEQable} agree to the following contract:
     * <p>
     * - Objects with the same class and in the same state MUST be equivalent.
     * - Objects that construct to create an object of the same class and in the same state should also be considered equivalent.
     * <p>
     * Optionally, classes that implement this may also satisfy any of the following:
     * <p>
     * - Objects with the same {@link #hashCode()} may evaluate to equivalent.
     * - Objects with very similar states may evaluate to equivalent if the vast majority of methods will act equivalently,
     *   and for the few that do not, do not alter behaviour in a significant enough manner to cause problems.
     *   (The primary example being {@link AugList#isEquivalent(Object)}, which does not scrutinise based off the behaviour of {@link AugList#spliterator()}{@link Spliterator#characteristics() .characteristics()}).
     * <p>
     * Equivalency does not care about memory addresses.
     * @param   o
     *          The {@link Object} in question
     * @return  Equivalency
     * @see     #equals(Object)
     * @tags    Terminator
     */
    public boolean isEquivalent(Object o);

    /**
     * @deprecated  In favour of the more broadly useful {@link #isEquivalent(Object)}.
     * @return      Equality, which is strict down to the memory address
     * @see         #isEquivalent(Object)
     * @tags        Terminator
     */
    @Deprecated
    public boolean equals(Object o);
}
