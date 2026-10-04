package src;

/**
 * Classes that implement this have a {@link #clone()} method.
 * The clone MUST be a different Object with a different memory reference.
 * @since   AugList V2
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     AugList
 * @see     Ticker
 */
public interface BMCloneable extends Cloneable {
    /**
     * Clones this {@link BMCloneable}.
     * @return  A clone of this {@link BMCloneable}
     */
    public BMCloneable clone();
}
