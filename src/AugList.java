/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
package src;

//import static org.junit.Assert.*;

//import java.util.ArrayDeque;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.Callable;
//import java.util.Deque;
import java.util.Enumeration;
//import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
//import java.util.PriorityQueue;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Spliterator;
//import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
//import java.util.function.IntFunction;
import java.util.function.Predicate;
//import java.util.function.Supplier;
import java.util.function.UnaryOperator;
//import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;

/**
 * <h1>AugList<T></h1>
 *
 * A decorator of an {@link ArrayList} which provides implementations of many commonly used procedures and commonly required tasks.
 * <p>Draws inspiration from a variety of sources, such as C# methods, statistics, functional and declarative programming, set theory and more.
 *
 * <h2>Notable Changes</h2>
 *
 * <h3>Return type changes</h3>
 *
 * - {@code boolean} {@link List#add(T)}, {@code Object} {@link Object#clone()} and {@code List<T>} {@link List#subList(int, int)}
 * changed to have a return type of {@code AugList<T>}.
 *
 * <h3>Overrides</h3>
 *
 * - Overrides {@link Object#toString()} to provide a more legible overview of the contents.
 *
 * <h3>Overloads</h3>
 *
 * - Constructors overloaded to take nearly any source of data - see Additions or "documentation/AugList.md" for the full list.
 * - Adds varargs overloads for select bulk-processing methods, such as {@link #addAll(T...) addAll(T...)}, {@link #containsAll(T...) containsAll(T...)} and {@link #removeAll(T...) removeAll(T...)}.
 * 
 * <h3>Replacements</h3>
 *
 * <p>
 * - Replaces {@link List#add(int, Object)} with {@link #insert(int, Object)}.
 * - Replaces {@link List#addAll(int, Collection)} with {@link #insertAll(int, Iterable)} / {@link #insertAll(int, T...) insertAll(int, T...)}.
 * - Replaces {@link List#replaceAll(UnaryOperator)} with {@link #applyAll(UnaryOperator)}.
 *
 * <h3>Additions</h3>
 *
 * AugList adds the following:<p>
 * - {@link #AugList()},
 * - {@link #AugList(T...) AugList(T...)}, {@link #AugList(Iterable)},
 * - {@link #AugList(Iterable, Iterable)},
 * - {@link #AugList(Iterable, Function)},
 * - {@link #AugList(Iterator)}, {@link #AugList(ListIterator)},  {@link #AugList(Spliterator)},
 * - {@link #AugList(Enumeration)}, {@link #AugList(Stream)},
 * - {@link #allIndicesOf(Object)},
 * - {@link #allSatisfy(Predicate)}, {@link #anySatisfy(Predicate)},
 * - {@link #applyAll(UnaryOperator)}, {@link #oneToOneMapCopy(Function)},
 * - {@link #chunk(int)}, {@link #fragment(int)},
 * - {@link #containsAny(Iterable)}, {@link #containsAny(T...) containsAny(T...)}
 * - {@link #countsOfElements()}, {@link #countOf(Object)},
 * - {@link #distinctSelf()}, {@link #distinctCopy()},
 * - {@link #filterSelf(Predicate)}, {@link #filterCopy(Predicate)}, 
 * - {@link #forEach(Consumer)},
 * - {@link #isEquivalent(Object)}, {@link #isRearrangement()},
 * - {@link #isPalindrome()}, {@link #isSet()}, {@link #isSorted(Comparator)},
 * - {@link #listDifference(Iterable)}, {@link #listIntersection(Iterable)}, {@link #listUnion(Iterable)},
 * - {@link #overwriteRandom(T)}, {@link #massOverwriteRandom(Iterable)}, {@link #massOverwriteRandom(T...) massOverwriteRandom(T...)}
 * - {@link #pairUp(Iterable)},
 * - {@link #parameterizedTypeDesc()},
 * - {@link #retainAll(Collection)}, {@link #toCollection()},
 * - {@link #setDifference(Iterable)}, {@link #setIntersection(Iterable)}, {@link #setUnion(Iterable)}, {@link #crossProduct(Iterable)},
 * - {@link #setMany(Iterable, Iterable)}, {@link #setFromCallable(int, int, Callable)},
 * - {@link #skipWhile(Predicate)}, {@link #takeWhile(Predicate)},
 * - {@link #split3(int)}, {@link #splitDelim(Function)},
 * - {@link #swap(int, int)}, {@link #swapRandom(int)}, {@link #swapRandom()},
 * - {@link #toEnumeration()},
 * - {@link #without(T)}, {@link #withoutAll(Iterable)}, {@link #withoutIndex(int)}, {@link #withoutLast()}, {@link #withoutWhere(Predicate)}, {@link #withoutRandom()}
 * - {@link #getRandom()}, {@link #removeRandom()},
 * - {@link #insertAtRandom(T)}, {@link #insertAllAtRandom(Iterable)}, {@link #insertAllAtRandom(T...) insertAllAtRandom(T...)},
 * - {@link #sample(int, boolean)},
 * - {@link #shuffleSelf()}, {@link #shuffleCopy()},
 *
 * <h3>Encapsulations</h3> 
 *
 * {@link AugList} internally uses an {@link ArrayList} to store its elements.
 * <p>Most, but not all {@link ArrayList} methods have been encapsulated without further alteration.
 * <p>(See "Deprecated" for the unimplemented encapsulations, and "Return type changes", "Overrides" and "Replacements" for altered methods.)
 *
 * The encapsulated methods are:<p>
 * - {@link #contains(T)},
 * - {@link #forEach(Consumer)},
 * - {@link #get(int)}, {@link #getLast()}, {@link #set(int, Object)},
 * - {@link #hashCode()},
 * - {@link #indexOf(Object)}, {@link #lastIndexOf(Object)},
 * - {@link #isEmpty()}, {@link #size()},
 * - {@link #iterator()}, {@link #listIterator()}, {@link #listIterator(int)}, {@link #spliterator()},
 * - {@link #parallelStream()}, {@link #stream()},
 * - {@link #remove(Object)}, {@link #removeAt(int)}, {@link #removeIf(Predicate)}, {@link #removeLast()},
 * - {@link #reversed()},
 * - {@link #toArray(T[]) toArray(T[])}
 *
 * <h3>Deprecated</h3>
 *
 * Due to these methods falling under one of the following categories, they have been commented out / left unimplemented:</p>
 * 1: have identical functionality under another name:</p>
 * - {@link List#add(int, Object)}, {@link List#addAll(int, Collection)}, {@link List#replaceAll(UnaryOperator)},</p>
 * 2: are / have been made redundant by other methods:</p>
 * - {@link List#addLast()}, {@link List#removeFirst()}, {@code AugList<T>.subListToEnd()},</p>
 * 3: are impractical to use: </p>
 * - {@link List#toArray()}, {@link List#toArray(java.util.function.IntFunction)},</p>
 * 4: have no meaningful impact on internal state:</p>
 * - {@link ArrayList#ensureCapacity()}, {@link ArrayList#trimToSize()}</p>
 * 
 * <h2>Other information</h2>
 * 
 * For a full alphabetical list of methods callable on an AugList, see "documentation/AugList.md".
 * 
 * @param   <T>
 *          The data type of the elements within this AugList.
 * @version AugList Version 2
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     src.ALFactory
 * @see     tests.AugListTest
 */
public class AugList<T> implements BMCloneable, BMEQable, RandomAccess, Iterable<T> {

    /**
     * The {@link ArrayList} that this {@link AugList} decorates.
     * @since   AugList V1
     */
    private ArrayList<T> ls;

    /**
     * The {@link Comparator} over this {@link AugList}.
     * Is null if the state changed since the most recent sort call.
     * (This usually means a Mutator was called and made a change.)
     * @since   AugList V2
     */
    private Comparator<? super T> cmp = null;

    /**
     * Creates a new, empty, non-null {@link AugList}.
     * @since       AugList V1
     * @see         tests.AugListTest#testInstantiateBlank()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @note        Sets the underlying {@link ArrayList} to be empty and the underlying {@link Comparator} over this {@link AugList} to be null.
     * @tags        Constructor
     */
    public AugList() {
        cmp = null;
        ls = new ArrayList<T>() {};
    }

    /**
     * Creates a new {@link AugList} from the given {@link Enumeration}.
     * @param       enumeration
     *              The {@link Enumeration} object to source the elements for this {@link AugList} from.
     *              <p>If {@code enumeration.equals(null)}, the resulting {@link AugList} is empty.
     * @since       AugList V1
     * @see         tests.AugListTest#testInstantiateEnumeration()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    @SuppressWarnings("unchecked")
    public AugList(Enumeration<? super T> enumeration) {
        this();
        if (!Objects.isNull(enumeration)) {
            while (enumeration.hasMoreElements()) {
                ls.add((T)enumeration.nextElement());
                // Unchecked cast SHOULD be fine as any type that is a supertype of T should be able to cast to T
            }
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@link Iterator} over some sequence.
     * @param       iterator
     *              The {@link Iterator} object to source the elements for this {@link AugList} from.
     *              If {@code iterator.equals(null)}, the resulting {@link AugList} is empty.
     * @since       AugList V1
     * @see         tests.AugListTest#testInstantiateIterator()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)} 
     * @tags        Constructor
     */
    @SuppressWarnings("unchecked")
    public AugList(Iterator<? super T> iterator) {
        this();
        if (!Objects.isNull(iterator)) {
            while (iterator.hasNext()) {
                ls.add((T)iterator.next());
            }
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link Iterable}.
     * @param       iterable
     *              The {@link Iterable} object to source the elements for this {@link AugList} from.
     * @since       AugList V2
     * @see         tests.AugListTest#testInstantiateIterable()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Iterable<? super T> iterable) {
        this();
        if (!Objects.isNull(iterable)) {
            ls = new AugList<T>(iterable.iterator()).ls;
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@link ListIterator}.
     * @param       listIterator
     *              The {@link ListIterator} object to source the elements this {@link AugList} from.
     * @since       AugList V2
     * @see         tests.AugListTest#testInstantiateListIterator()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)} ,{@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    @SuppressWarnings("unchecked")
    public AugList(ListIterator<? super T> listIterator) {
        this();
        if (!Objects.isNull(listIterator)) {
            listIterator.forEachRemaining(element -> ls.add((T)element));
        }
    }

    /**
     * Creates a new {@link AugList} from the given value-count pairs.
     * @param       values
     *              The values to use.
     *              If there are more values than counts, ignores values without counts.
     * @param       counts
     *              How many times each value should be repeated.
     *              If there are more counts than values, ignores the counts without associated values. Negative counts are treated as 0.
     * @since       AugList V2
     * @see         tests.AugListTest#testInstantiatePairs()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Iterable<? super T> values, Iterable<Integer> counts) {
        this();
        if (!(Objects.isNull(values) || Objects.isNull(counts))) {
            SimpleEntry<AugList<T>, AugList<Integer>> res = equaliseAndFilter(values, counts, false);
            AugList<T> ALvalues = res.getKey();
            AugList<Integer> ALcounts = res.getValue();
            for (int i = 0; i < ALvalues.size(); i++) {
                for (int j = 0; j < ALcounts.get(i); j++) {
                    ls.add(ALvalues.get(i));
                }
            }
        }
    }

    /**
     * Internal method that takes 2 {@link Iterable Iterables}, most likely of different lengths, and trim the longer one down to the length of the shorter.
     * @param       keys
     *              The {@link Iterable} containing the keys to use.
     *              <p>Removes keys without corresponding values.
     * @param       values
     *              Their {@link Iterable} containing the values.
     *              <p>Removes values without corresponding keys.
     * @return      A Tuple ({@link SimpleEntry}) with equally {@link #size() sized} {@link AugList AugLists}.
     * @since       AugList V2
     * @see         #equaliseAndFilter(Iterable, Iterable, boolean)
     * @note        Could easily be transferred to a helper class, as no instance data is required.
     * @tags        Converter
     */
    private static <T, U> SimpleEntry<AugList<T>, AugList<U>> equaliseLengths(Iterable<? super T> keys, Iterable<? super U> values) {
        AugList<T> ALvalues = new AugList<T>(keys);
        AugList<U> ALkeys = new AugList<U>(values);
        // Shorten values until matches keys
        while (ALvalues.size() > ALkeys.size()) {
            ALvalues.removeLast();
        }
        // Shorten keys until matches values
        while (ALkeys.size() > ALvalues.size()) {
            ALkeys.removeLast();
        }
        return new SimpleEntry<AugList<T>, AugList<U>>(ALvalues, ALkeys);
    }

    /**
     * Internal method that takes 2 {@link Iterable Iterables}, most likely of different lengths, and trim the longer one down to the length of the shorter.
     * @param       keys
     *              The keys which will be used.
     *              <p>Removes keys without corresponding integers.
     * @param       ints
     *              Their corresponding integer values.
     *              <p>Removes ints without corresponding keys.
     *              <p>Negative ints are treated as 0.
     *              <p>If {@code intsAreIndices == true}, clamps ints higher than {@link #size()} to 1 less than the size.
     * @param       intsAreIndices
     *              Whether the supplied ints should be treated as Counts (Unbounded positive value) or as Indices (Bounded by AugList size)
     * @return      A Tuple ({@link SimpleEntry}) with equally {@link #size() sized} {@link AugList AugLists}, with the ints filtered as requested.
     * @see         #equaliseLengths(Iterable, Iterable)
     * @see         #setMany(Iterable, Iterable)
     * @since       AugList V2
     * @tags        Converter
     */
    private SimpleEntry<AugList<T>, AugList<Integer>> equaliseAndFilter(Iterable<? super T> keys, Iterable<Integer> ints, boolean intsAreIndices) {
        SimpleEntry<AugList<T>, AugList<Integer>> res = equaliseLengths(keys, ints);
        AugList<T> ALvalues = res.getKey();
        AugList<Integer> ALints = res.getValue();
        ALints.applyAll(count -> count > 0 ? count : 0); // Set negatives to 0
        if (intsAreIndices) {
            // Set indices over the maximum index to within bounds
            ALints.applyAll(count -> count >= size() ? size() - 1 : count);
        }
        // Repackage result
        return new SimpleEntry<AugList<T>, AugList<Integer>>(ALvalues, ALints);
    }

    /**
     * Creates a new {@link AugList} from the given {@link Spliterator}.
     * @param       spliterator
     *              The {@link Spliterator} object to source the elements for this {@link AugList} from.
     * @since       AugList V2
     * @see         tests.AugListTest#testInstantiateSpliterator()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    @SuppressWarnings("unchecked")
    public AugList(Spliterator<? super T> spliterator) {
        this();
        if (!Objects.isNull(spliterator)) {
            spliterator.forEachRemaining(e -> ls.add((T)e));
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@link Stream}.
     * @param       stream
     *              The {@link Stream} object to source the elements for this {@link AugList} from.
     * @see         tests.AugListTest#testInstantiateStream()
     * @since       AugList V2
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Stream<? super T> stream) {
        this();
        if (!Objects.isNull(stream)) {
            ls = new AugList<T>(stream.iterator()).ls;
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@link T}[].
     * @param       elements
     *              The varargs array of objects that will make up this {@link AugList}.
     *              <p>If {@code elements.equals(null)}, the resulting {@link AugList} is empty.
     * @since       AugList V1
     * @see         tests.AugListTest#testInstantiateVarargs()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    @SafeVarargs
    public AugList(T... elements) {
        this();
        if (!Objects.isNull(elements)) {
            ls = new ArrayList<T>(Arrays.asList(elements));
        }
    }

    /**
     * Appends the given element to the end of this {@link AugList}.
     *
     * @param   element
     *          The element in question.
     * @return  This {@link AugList}, with the given element appended to it.
     * @since   AugList V1
     * @see     tests.AugListTest#testAdd()
     * @note    Replaces {@link List#add(T)}: Returns {@code this}, not {@code void}.
     * @tags    Mutator
     */
    public AugList<T> add(T element) {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        ls.add(element);
        return this;
    }
    
    /**
     * Appends all given elements to this {@link AugList}.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code this}.
     * @return      This {@link AugList}, with the given elements appended to it.
     * @since       AugList V1
     * @see         tests.AugListTest#testAddAll()
     * @note        Replaces {@link ArrayList#addAll()}: Returns {@code this}, not {@code void}.
     * @overloads   {@link #addAll(Iterable)}, {@link #addAll(T...) addAll(T...)}
     * @tags        Mutator
     */
    public AugList<T> addAll(Iterable<? super T> elements) {
        if (Objects.isNull(elements)) {
            return this;
        }
        AugList<T> ALelements = new AugList<T>(elements);
        if (ls.addAll(ALelements.ls)) {
            cmp = null; // Mutators destroy the value of cmp if a change occurs.
        }
        return this;
    }

    /**
     * Appends all given elements to this {@link AugList}.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code this}.
     * @return      This {@link AugList}, with the given elements appended to it.
     * @since       AugList V1
     * @see         tests.AugListTest#testAddAllVarargs()
     * @note        Varargs variant for {@link ArrayList#addAll()}.
     * @overloads   {@link #addAll(Iterable)}, {@link #addAll(T...) addAll(T...)}
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> addAll(T... elements) {
        return addAll(new AugList<T>(elements));
    }

    /**
     * Prepends the given element to this {@link AugList}.
     * @param   element
     *          The element in question.
     * @return  This {@link AugList}, with the given element prepended to it.
     * @since   AugList V1
     * @see     tests.AugListTest#testAddFirst()
     * @note    Replaces {@link ArrayList#addFirst(T)}: Returns {@code this}, not {@code void}.
     * @tags    Mutator
     */
    public AugList<T> addFirst(T element) {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        ls.addFirst(element);
        return this;
    }

    // ArrayList<T>.addLast(T) is not implemented as AugList<T>.insert(0, T) performs the same task

    /**
     * Finds all indices at which the given element can be found.
     * @param   element
     *          The element in question.
     * @return  A new {@link AugList} with the indices at which it can be found.
     * @since   AugList V1
     * @see     #indexOf(Object)
     * @see     #lastIndexOf(Object)
     * @see     tests.AugListTest#testAllIndicesOf()
     * @note    Based upon the C# method {@code List<T>.FindAll(Predicate<T>)} with a Predicate that returns true when an element is equal to the target.
     * @tags    Creator
     */
    public AugList<Integer> allIndicesOf(T element) {
        AugList<Integer> ret = new AugList<Integer>();
        for (int i = 0; i < ls.size(); i++) {
            if (Objects.isNull(element)) {
                if (Objects.isNull(ls.get(i))) {
                    ret.add(i);
                }
            }
            else if (ls.get(i).equals(element)) {
                ret.add(i);
            }
        }
        return ret;
    }

    /**
     * Sees if all elements pass the given condition.
     * @param   condition
     *          The condition in question. If {@code null}, returns {@code false}.
     * @return  {@code true} if all elements satisfy the given condition, and {@code false} otherwise.
     * @since   AugList V1
     * @see     #anySatisfy(Predicate)
     * @see     tests.AugListTest#testAllSatisfy()
     * @note    Based upon the C# methods {@code IEnumerable<T>.All()} and {@code List<T>.TrueForAll()}.
     * @tags    Terminator
     */
    public boolean allSatisfy(Predicate<? super T> condition) {
        for (T e : ls) {
            if (Objects.isNull(condition) || !condition.test(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sees if any element passes the given condition.
     * @param   condition
     *          The condition in question. If {@code null}, returns {@code false}.
     * @return  {@code true} if any element satisfies the given condition, and {@code false} otherwise.
     * @since   AugList V1
     * @see     #allSatisfy(Predicate)
     * @see     tests.AugListTest#testAnySatisfy()
     * @note    Inspired by {@link #allSatisfy(Predicate)}.
     * @tags    Terminator
     */
    public boolean anySatisfy(Predicate<? super T> condition) {
        if (Objects.isNull(condition)) {
            return false;
        }
        for (T e : ls) {
            if (condition.test(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Applies the given {@link UnaryOperator} to all elements of this {@link AugList}
     * <p>For a Terminator method, use {@link #forEach()}.</p>
     * <p>For a Creator method, use {@link #oneToOneMap()}.</p>
     * @param   func
     *          The {@link UnaryOperator} in question. If {@code null}, does not alter this {@link AugList}.
     * @return  This {@link AugList}, with each element transformed as according to the function.
     * @since   AugList V1
     * @see     #oneToOneMapCopy(Function)
     * @see     #forEach(Consumer)
     * @see     List#replaceAll(UnaryOperator)
     * @see     tests.AugListTest#testApplyAll()
     * @note    Inspired by the C# function {@code List<T>.ConvertAll(Converter<T, TOutput>)}.
     *          <p>Very similar to {@link List#replaceAll(UnaryOperator)}.
     * @tags    Mutator
     */
    @SuppressWarnings("unchecked")
    public AugList<T> applyAll(UnaryOperator<? super T> func) {
        if (!Objects.isNull(func)) {
            AugList<T> clone = clone();
            for (int i = 0; i < ls.size(); i++) {   
                ls.set(i, (T)func.apply(ls.get(i)));
            }
            if (!isEquivalent(clone)) {
                cmp = null; // Destroy cmp if the function was not the identity function.
            }
        }
        return this;
    }

    /**
     * Creates a new {@link AugList} of AugLists where each entry has at most {@code size} elements.
     * @param   size
     *          The maximum size of a chunk. The final chunk may be smaller than this size.
     * @return  This {@link AugList} split into smaller AugLists with a maximum length of {@code size}.
     * @throws  IllegalArgumentException
     *          {@code size <= 0}
     * @since   AugList V1
     * @see     #fragment()
     * @see     #split3(int)
     * @see     #splitDelim(Function)
     * @see     tests.AugListTest#testChunk()
     * @note    Based upon the C# method {@code List<T>.Chunk(int)}.
     * @tags    Creator
     */
    public AugList<AugList<T>> chunk(int size) {
        if (size <= 0) {
            throw new java.lang.IllegalArgumentException("AugList<T>.chunk(int size): size may not be smaller than 1.");
        }
        AugList<AugList<T>> ret = new AugList<AugList<T>>();
        for (int i = 0; i < (int)Math.ceil(ls.size() / (size + 0d)); i++) {
            ret.add(new AugList<T>());
        }
        int i = 0;
        for (T e : ls) {
            ret.get((int)Math.floor(i / (size + 0d))).add(e);
            i++;
        }
        return ret;
        /**
         * There is no way to supply null to this method without an earlier warning being produced.
         * Consider the following examples:
         * 
         * int j = null; // Produces compile error (type mismatch)
         * ALD.chunk(j);
         * 
         * AND
         * 
         * Integer i = null;
         * AugList<Double> ALD = new AugList<Double>(1.0, 1.0, 1.0);
         * ALD.chunk(i); // Produces runtime exception (cannot invoke methods on null)
         * 
         * In other words, it is impossible that:
         * 
         * Objects.isNull(size) == true
         * 
         * Hence there is no need to handle the such.
         */
    }

    /**
     * Clears this {@link AugList}.
     * @return  This {@link AugList}, emptied.
     * @since   AugList V1
     * @see     tests.AugListTest#testClear()
     * @note    Replaces {@link List#clear()}: Returns {@code this}, not {@code void}.
     * @tags    Mutator
     */
    public AugList<T> clear() {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        ls.clear();
        return this;
    }

    // Notably, works differently to ArrayList<T>.clone() in that the return type is an AugList<T>.
    /**
     * Creates and returns a new {@link AugList} with identical contents but a different reference.
     * @return  A new {@link AugList} with identical contents as this one.
     * @see     BMCloneable
     * @see     tests.AugListTest#testClone()
     * @since   AugList V1
     * @note    Replaces {@link Object#clone()}: Return type {@link AugList}, as opposed to an {@link Object}.
     *          <p>Also note that:</p>
     *          <p>If the parameterised type is not {@link BMCloneable}, the same Object reference will be added to the resultant {@link AugList}.
     *          <p>(I.e. altering the elements of the clone will also alter the elements of the original and vice versa.)
     *          <pre>this.clone() != this, this.clone().isEquivalent(this), this.clone().getClass() == this.getClass()</pre>
     * @tags    Creator
     */
    @SuppressWarnings("unchecked")
    @Override
    public AugList<T> clone() {
        /**
         * If clone is defined as `new AugList<T>() {}`,
         *  Then the resulting Class string is "class src.AugList$NN" (for some number NN)
         * However, with clone defined as `new AugList<T>()`,
         *  Then the resulting Class string is "class src.AugList".
         * 
         * The latter is preferable so that `this.getClass() == this.clone().getClass()`.
         */
        AugList<T> clone = new AugList<T>();
        for (T e : ls) {
            if (e instanceof BMCloneable) {
                // As e, type T, is BMCloneable, casting back to T should not be an issue.
                clone.add((T)((BMCloneable)e).clone());
            }
            else {
                clone.add(e);
            }
        }
        return clone;
    }

    /**
     * Sees if this {@link AugList} contains the given element.
     * @param   element
     *          The element to search for.
     * @return  {@code true} if found, and {@code false} otherwise
     * @since   AugList V1
     * @see     #containsAll(AugList)
     * @see     #containsAny(AugList)
     * @see     tests.AugListTest#testContains()
     * @note    Encapsulates {@link List#contains(T)}.
     * @tags    Terminator
     */
    public boolean contains(T element) {
        return ls.contains(element);
    }
    
    /**
     * Internal method used to calculate {@link #containsAny(AugList)}, {@link #containsAny(T...)}, {@link #containsAll(AugList)} and {@link #containsAll(T...) containsAll(T...)}.
     * 
     * @param   elements
     *          The elements to use. (see param {@code allOrAny})
     * @param   allOrAny
     *          If {@code true}, returns {@code true} if ALL elements are present.
     *          If {@code false}, returns {@code true} if ANY element is present.
     * @return  The desired result. (see param {@code allOrAny})
     * @since   AugList V2
     * @see     #containsAll(AugList)
     * @see     #containsAll(T...) containsAll(T...)
     * @see     #containsAny(AugList)
     * @see     #containsAny(T...) containsAny(T...)
     * @tags    Terminator
     */
    private boolean containsBulk(AugList<? super T> elements, boolean allOrAny) {
        // Since containsBulk can only be called from 4 places,
        // all of which already handle nulls, handling nulls here is pointless. 
        // That being said,
        // If AugList is extended and the extending class were to call containsBulk,
        // Be aware that at the current moment there is no Null detection.
        //if (!Objects.isNull(elements)) {
        AugList<T> thisClone = this.clone();
        for (int i = 0; i < elements.size(); i++) {
            if (Objects.isNull(elements.get(i))) {
                if (!thisClone.remove(null)) {
                    return !allOrAny;
                }
            }
            else if ((allOrAny ^ thisClone.remove(elements.get(i)))) {
                return !allOrAny;
            }
        }
        //}
        return allOrAny;
    }

    /**
     * Sees if this {@link AugList} contains all the given elements.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code true}.
     * @return      {@code true} if all elements are found, and {@code false} otherwise.
     * @since       AugList V1
     * @see         #contains(Object)
     * @see         #containsAny(Iterable)
     * @see         #containsAny(T...) containsAny(T...)
     * @see         #containsBulk(AugList, boolean)
     * @see         tests.AugListTest#testContainsAll()
     * @note        Replaces {@link java.util.Collection#containsAll(java.util.Collection)}
     * @overloads   {@link #containsAll(Iterable)}, {@link #containsAll(T...) containsAll(T...)}
     * @tags        Terminator
     */
    public boolean containsAll(Iterable<? super T> elements) {
        return containsBulk(new AugList<T>(elements), true);
    }

    /**
     * Sees if this {@link AugList} contains all the given elements.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code true}.
     * @return      {@code true} if all elements are found, and {@code false} otherwise.
     * @since       AugList V1
     * @see         #contains(Object)
     * @see         #containsAny(Iterable)
     * @see         #containsAny(T...) containsAny(T...)
     * @see         #containsBulk(AugList, boolean)
     * @see         tests.AugListTest#testContainsAllVarargs()
     * @note        Varargs overload for {@link #containsAll(Iterable)}.
     *              <p>Functionality can be replicated with {@code this.allSatisfy(e -> this.contains(e))}.</p>
     * @overloads   {@link #containsAll(Iterable)}, {@link #containsAll(T...) containsAll(T...)}.
     * @tags        Terminator
     */
    @SafeVarargs
    public final boolean containsAll(T... elements) {
        return containsAll(new AugList<T>(elements));
    }

    /**
     * Sees if this {@link AugList} contains any of the given elements.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code false}.
     * @return      {@code true} if any element is found, and {@code false} if not.
     * @since       AugList V1
     * @see         #contains(Object)
     * @see         #containsAll(Iterable)
     * @see         #containsAll(T...) containsAll(T...)
     * @see         #containsBulk(AugList, boolean)
     * @see         tests.AugListTest#testContainsAny()
     * @note        Inspired by {@link #containsAll(Iterable)}.
     *              <p>Functionality can be replicated with {@code this.anySatisfy(e -> elements.contains(e))}.</p>
     * @overloads   {@link #containsAny(Iterable)}, {@link #containsAny(T...) containsAny(T...)}.
     * @tags        Terminator
     */
    public final boolean containsAny(Iterable<? super T> elements) {
        return containsBulk(new AugList<T>(elements), false);
    }

    /**
     * Sees if this {@link AugList} contains any of the given elements.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code false}.
     * @return      {@code true} if any element is found, and {@code false} if not.
     * @since       AugList V1
     * @see         #contains(Object)
     * @see         #containsAll(Iterable)
     * @see         #containsAll(T...) containsAll(T...)
     * @see         #containsBulk(AugList, boolean)
     * @see         tests.AugListTest#testContainsAnyVarargs()
     * @note        Varargs overload of {@link #containsAny(Iterable)}
     *              <p>Functionality can be replicated with {@code this.anySatisfy(e -> elements.contains(e))}.</p>
     * @overloads   {@link #containsAny(Iterable)}, {@link #containsAny(T...) containsAny(T...)}.
     * @tags        Terminator
     */
    @SafeVarargs
    public final boolean containsAny(T... elements) {
        return containsAny(new AugList<T>(elements));
    }

    /**
     * Returns the number of times the given element occurs.
     * @param   element
     *          The element in question.
     * @return  The number of times that element occurs.
     * @since   AugList V2
     * @see     #countsOfElements()
     * @see     tests.AugListTest#testCountOf()
     * @tags    Terminator
     */
    public int countOf(T element) {
        Integer count = 0;
        if (Objects.isNull(element)) {
            for (int i = 0; i < size(); i++) {
                if (Objects.isNull(get(i))) {
                    count++;
                }
            }
        }
        else {
            count = countsOfElements().get(element);
            if (Objects.isNull(count)) {
                return 0;
            }
        }
        return count;
    }

    /**
     * Counts the number of times each non-null element appears and returns a {@link Hashtable} with the results.
     * @return  A {@link Hashtable} that pairs each non-null element with its frequency.
     * @since   AugList V1
     * @see     #countOf()
     * @see     tests.AugListTest#testCountsOfElements()
     * @note    Does NOT provide a count of the number of nulls.
     *          <p>To count the number of nulls, see {@link #countOf(T)}.
     * @tags    Converter
     */
    public Hashtable<T, Integer> countsOfElements() {
        Hashtable<T, Integer> ret = new Hashtable<T, Integer>() {};
        for (int i = 0; i < ls.size(); i++) {
            T key = ls.get(i);
            if (!Objects.isNull(key)) {
                if (ret.containsKey(key)) {
                    ret.put(key, ret.get(key) + 1);
                }
                else {
                    ret.put(key, 1);
                }
            }
        }
        return ret;
    }

    /**
     * Takes the cross product of this {@link AugList}, A, with the elements sourced from the given {@link Iterable}, B.
     * @param   itrB
     *          The source of the second selection of elements, B.
     * @return  The Mathematical cross product of the two lists, A x B.
     * @since   AugList V2
     * @see     #setDifference(AugList)
     * @see     #setIntersection(Iterable)
     * @see     #setUnion(Iterable)
     * @see     tests.AugListTest#testCrossProduct()
     * @tags    Creator
     */
    public <U> AugList<AugList<SimpleEntry<T, U>>> crossProduct(Iterable<? super U> itrB) {
        AugList<U> ALb = new AugList<U>(itrB); // Implicit null correction
        AugList<AugList<SimpleEntry<T, U>>> ret = new AugList<AugList<SimpleEntry<T, U>>>();
        for (T itemA : this) {
            ret.add(new AugList<SimpleEntry<T, U>>());
            for (U itemB : ALb) {
                ret.getLast().add(new SimpleEntry<T, U>(itemA, itemB));
            }
        }
        return ret;
    }

    /**
     * Makes an {@link AugList} from this one, but without duplicates.
     * <p>For a Mutator method, use {@link #distinctSelf()}.</p>
     * @return  A new {@link AugList} with only the distinct elements in this AugList.
     *          Mathematically speaking, the result is also a set.
     * @since   AugList V1
     * @see     #distinctSelf()
     * @see     #isSet()
     * @see     tests.AugListTest#testDistinctCopy()
     * @note    Based off the C# function {@code IEnumerable<T>.Distinct()};
     * @tags    Creator
     */
    public AugList<T> distinctCopy() {
        AugList<T> ret = new AugList<T>();
        for (T e : ls) {
            if (!ret.contains(e)) {
                ret.add(e);
            }
        }
        return ret;
    }

    /**
     * Changes this {@link AugList} to contain exactly one copy of each item.
     * <p>For a Creator method, use {@link #distinctCopy()}.</p>
     * @return  This AugList, with each element being distinct from each other.
     *          Mathematically speaking, the result is also a set.
     * @since   AugList V1
     * @see     #distinctCopy()
     * @see     #isSet()
     * @see     tests.AugListTest#testDistinctSelf()
     * @note    Inspired by {@link #distinctCopy()}.
     * @tags    Mutator
     */
    public AugList<T> distinctSelf() {
        AugList<T> ret = new AugList<T>();
        for (T e : ls) {
            if (!ret.contains(e)) {
                ret.add(e);
            }
        }
        if (!isEquivalent(ret)) {
            cmp = null; // Destroy cmp if at least one element was removed
        }
        ls = ret.ls;
        return ret;
    }

    /**
     * @deprecated  Use {@link #isEquivalent(Object)} or {@link #isRearrangement(Iterable)} instead for more broadly useful equality functions.
     * {@inheritDoc}
     * 
     * @return  {@code Object.equals(o)}
     * @since   AugList V1, Deprecated V2
     * @see     #hashCode()
     * @see     #isEquivalent(Object)
     * @see     #isRearrangement(Iterable)
     * @tags    Terminator
     */
    @Override
    @Deprecated(since = "2", forRemoval = false)
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Creates a new {@link AugList} with exactly the elements that satisfy the given filter.
     * <p>For a Mutator method, use {@link #filterSelf()}.
     * @param   condition
     *          The condition in question. If {@code null}, returns an empty {@link AugList}.
     * @return  A new {@link AugList} with elements that satisfy the given filter.
     * @since   AugList V1
     * @see     #filterSelf()
     * @see     tests.AugListTest#testFilterCopy()
     * @note    Based off the C# function {@code IEnumerable<T>.Where()}
     * @tags    Creator
     */
    public AugList<T> filterCopy(Predicate<? super T> condition) {
        AugList<T> ret = new AugList<T>();
        if (!Objects.isNull(condition)) {
            for (T e : ls) {
                if (condition.test(e)) {
                    ret.add(e);
                }
            }
        }
        return ret;
    }

    /**
     * Changes this {@link AugList} to contain exactly the elements that satisfy the given filter.
     * <p>For a Creator method, use {@link #filterCopy()}.
     * @param   condition
     *          The condition in question. If {@code null}, returns an empty {@link AugList}.
     * @return  This {@link AugList} with elements that satisfy the given filter.
     * @since   AugList V1
     * @see     #filterCopy()
     * @see     tests.AugListTest#testFilterSelf()
     * @note    Based off the C# function {@code IEnumerable<T>.Where()}
     * @tags    Mutator
     */
    public AugList<T> filterSelf(Predicate<? super T> condition) {
        AugList<T> clone = clone();
        this.ls = filterCopy(condition).ls;
        if (!isEquivalent(clone)) {
            cmp = null; // At least 1 element was removed, so destroy cmp.
        }
        return this;
    }

    /**
     * Supplies the given {@link Consumer} all elements of this {@link AugList}.
     * <p>For a non-terminal method, use {@link #applyAll()}.</p>
     * <p>For a Creator method, use {@link #oneToOneMap()}.</p>
     * @param   action
     *          The action to perform.
     * @since   AugList V1
     * @see     #applyAll(Function)
     * @see     #oneToOneMapCopy(Function)
     * @see     tests.AugListTest#testForEach()
     * @note    Encapsulates {@link List#forEach(Consumer)}.
     *          <p> Is only a Mutator if the action is also a Mutator.
     * @tags    Mutator, Terminator
     */
    public void forEach(Consumer<? super T> action) {
        if (!Objects.isNull(action)) {
            AugList<T> clone = clone();
            ls.forEach(action);
            if (!isEquivalent(clone)) {
                cmp = null; // At least 1 mutation occurred, so destroy cmp.
            }
        }
    }

    /**
     * Fragments this {@link AugList} into irregularly sized fragments.
     * Preserves order.
     * Always partitions into at least 2 lists, and no list is empty.
     * @return  This AugList fragmented into randomly sized shards.
     * @since   AugList V2
     * @see     #chunk(int)
     * @see     #split3(int)
     * @see     #splitDelim(Function)
     * @see     tests.AugListTest#testFragment()
     * @note    Randomized variant of {@link #chunk()}.
     * @tags    Creator
     */
    public AugList<AugList<T>> fragment() {
        AugList<AugList<T>> ret = new AugList<AugList<T>>(new AugList<T>());
        Integer lower = 0, fragmentSize = (new Random().nextInt(1, ls.size()));
        for (int i = 0; i < ls.size(); i++) {
            if (i == fragmentSize - lower) {
                lower = i;
                // Branch only triggers if a list split would be scheduled after the last index
                if (lower != ls.size() - 1) {
                    fragmentSize = ((new Random()).nextInt(1, ls.size() - lower));
                }
                ret.add(new AugList<T>());
            }
            ret.getLast().add(ls.get(i));
        }
        return ret;
    }

    // Ideally I would add the ability to index with [].
    // However, I don't believe the such is even possible in Java.
    /**
     * Returns the element at the given {@code index}.
     * @param   index
     *          Index of the element to return.
     * @return  The element at {@code index}.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @since   AugList V1
     * @see     #getLast()
     * @see     #getRandom()
     * @see     #set(int, T)
     * @see     tests.AugListTest#testGet()
     * @note    Encapsulates {@link List#get(int)}
     * @tags    Terminator
     */
    public T get(int index) {
        return ls.get(index);
        // It is not possible to pass null into this method without generating an exception or error prior to this method.
    }

    // Object.getClass() cannot be overridden and as such is not implemented
    // ArrayList<T>.getFirst() is not implemented as it is made redundant by get(0)
    
    // ArrayList<T>.getLast() has just enough of a fringe usage that it is implemented; Typing ArrayList<T>.get(ArrayList<T>.size() - 1) is a little arduous.
    /**
     * Gets the final element of this {@link AugList}.
     * @return  The last item in this {@link AugList}.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V1
     * @see     #get(int)
     * @see     tests.AugListTest#testGetLast()
     * @note    Encapsulates {@link List#getLast()}.
     * @tags    Terminator
     */
    public T getLast() {
        return ls.getLast();
    }

    /**
     * @return  A random element of this {@link AugList}.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V2
     * @see     #get(int)
     * @see     tests.AugListTest#testGetRandom()
     * @note    Randomized variant of {@link #get()}.
     * @tags    Terminator
     */
    public T getRandom() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return ls.get((new Random()).nextInt(ls.size()));
    }

    /**
     * {@inheritDoc}
     * 
     * @return  The hashcode of this {@link AugList}.
     * @since   AugList V1
     * @see     #equals(Object)
     * @see     tests.AugListTest#testHashCode()
     * @note    Used to encapsulate {@link List#hashCode()}.
     *          Behaviour reverted in V2 due to breaking the {@link #equals()} contract.
     *          (Objects that satisfy {@link #equals(Object)} should by contract have an equal hashcode, and vice versa.
     *           The V1 implementation broke the FORWARDS direction of that contract only.)
     * @tags    Terminator
     */
    @Override
    public int hashCode() {
        //return ls.hashCode();
        return super.hashCode();
    }

    /**
     * Returns the index of the first occurrence of object {@code o}, or -1 if it is not in this list.
     * @param   o
     *          The object to search for.
     * @return  The index of the first occurrence of {@code o}, or -1 if it is not present.
     * @since   AugList V1
     * @see     #allIndicesOf(Object)
     * @see     #lastIndexOf(Object)
     * @see     tests.AugListTest#testIndexOf()
     * @note    Encapsulates {@link ArrayList#indexOf()}.
     * @tags    Terminator
     */
    public int indexOf(Object o) {
        return ls.indexOf(o);
    }

    /**
     * Inserts the given element at the given index.
     * @param   index
     *          The index in question.
     * @param   element
     *          The element in question.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @return  This {@link AugList}, with the given element inserted at the given index.
     * @since   AugList V1
     * @see     #insertAll(int, AugList)
     * @see     #insertAll(int, Object...)
     * @see     #insertAtRandom(Object)
     * @see     tests.AugListTest#testInsert()
     * @note    Replaces {@link ArrayList#add(int, T)}: Returns {@code this}, not {@code true}.
     * @tags    Mutator
     */
    public AugList<T> insert(int index, T element) {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        ls.add(index, element);
        return this;
        // It is not possible to pass index = null into this method without generating an exception or error prior to this method.
    }

    /**
     * Inserts the given elements at the given index.
     * @param       index
     *              The index in question.
     * @param       elements
     *              The {@link AugList} of elements in question.
     * @throws      IndexOutOfBoundsException
     *              {@code index < 0 || index >= this.size()}
     * @return      This {@link AugList}, with the given elements inserted at the given index.
     * @since       AugList V1
     * @see         #insert(int, Object)
     * @see         #insertAllAtRandom(Iterable)
     * @see         #insertAllAtRandom(T...)  insertAllAtRandom(T...) 
     * @see         tests.AugListTest#testInsertAll()
     * @note        Replaces {@link ArrayList#addAll(int, java.util.Collection)}:
     *              Elements parameter type changed to AugList, returns {@code this}, not {@code true}.
     * @overloads   {@link #insertAll(int, T...) insertAll(int, T...)}, {@link #insertAll(int, Iterable)}
     * @tags        Mutator
     */
    public AugList<T> insertAll(int index, Iterable<? super T> elements) {
        AugList<T> e = new AugList<T>(elements); // Implicit null handling
        if (ls.addAll(index, e.ls)) {
            cmp = null; // At least 1 element was added, so destroy cmp.
        }
        return this;
    }

    /**
     * Inserts the given elements at the given index.
     * @param       index
     *              The index in question.
     * @param       elements
     *              The elements in question.
     * @throws      IndexOutOfBoundsException
     *              {@code index < 0 || index >= this.size()}
     * @return      This {@link AugList}, with the given elements inserted at the given index.
     * @since       AugList V1
     * @see         #insert(int, Object)
     * @see         #insertAllAtRandom(AugList)
     * @see         #insertAllAtRandom(T...)  insertAllAtRandom(T...)
     * @see         tests.AugListTest#testInsertAllVarargs()
     * @note        Varargs overload of {@link #insertAll(int, AugList)}
     * @overloads   {@link #insertAll(int, T...) insertAll(int, T...)}, {@link #insertAll(int, Iterable)} 
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> insertAll(int index, T... elements) {
        return insertAll(index, new AugList<T>(elements));
    }

    /**
     * Inserts the given elements to this {@link AugList} at random.
     * @param       elements
     *              The elements in question. If {@code null}, does not alter this {@link AugList}.
     * @return      This {@link AugList}, with the given elements inserted at random.
     * @since       AugList V2
     * @see         #insertAll(int, AugList)
     * @see         #insertAll(T...) insertAll(T...)
     * @see         #insertAtRandom(Object)
     * @see         tests.AugListTest#testInsertAllAtRandom()
     * @note        Randomized bulk non-varargs variant of {@link #insert(int, Object)}
     * @overloads   {@link #insertAllAtRandom(Iterable)}, {@link #insertAllAtRandom(T...) insertAllAtRandom(T...)}
     * @tags        Mutator
     */
    public AugList<T> insertAllAtRandom(Iterable<? super T> elements) {
        AugList<T> e = new AugList<T>(elements); // Implicit null handling
        if (!e.isEmpty()) {
            cmp = null; // At least 1 element will be inserted, so destroy cmp.
        }
        for (int i = 0; i < e.size(); i++) {
            insertAtRandom(e.get(i));
        }
        return this;
    }

    /**
     * Inserts the given elements to this {@link AugList} at random.
     * @param       elements
     *              The elements in question. If {@code null}, does not alter this {@link AugList}.
     * @return      This {@link AugList}, with the given elements inserted at random.
     * @since       AugList V2
     * @see         #insertAll(int, AugList)
     * @see         #insertAll(T...) insertAll(T...)
     * @see         #insertAtRandom(Object)
     * @see         tests.AugListTest#testInsertAllAtRandomVarargs()
     * @note        Randomized bulk varargs variant of {@link #insert(int, Object)}
     * @overloads   {@link #insertAllAtRandom(Iterable)}, {@link #insertAllAtRandom(T...) insertAllAtRandom(T...)}
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> insertAllAtRandom(T... elements) {
        return insertAllAtRandom(new AugList<T>(elements));
    }

    /**
     * Inserts the given element at random.
     * @param   element
     *          The element in question.
     * @return  This list, with the given element inserted somewhere into this AugList.
     * @since   AugList V2
     * @see     #insert(int, Object)
     * @see     #insertAllAtRandom(AugList)
     * @see     #insertAllAtRandom(T...)  insertAllAtRandom(T...)
     * @see     tests.AugListTest#testInsertAtRandom()
     * @note    Randomized variant of {@link #insert(T, int)}
     * @tags    Mutator
     */
    public AugList<T> insertAtRandom(T element) {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        ls.add((new Random()).nextInt(ls.size()), element);
        return this;
    }

    /**
     * Returns {@code true} if this {@link AugList} is empty.
     * @return  {@code this.size() == 0}.
     * @since   AugList V1
     * @see     #size()
     * @see     tests.AugListTest#testIsEmpty()
     * @note    Encapsulates {@link List#isEmpty()}.
     * @tags    Terminator
     */
    public boolean isEmpty() {
        return ls.isEmpty();
    }

    /** 
     * Sees if the given {@link Object} may be equal to this {@link AugList}.
     * <p>If the {@link Object} in question is any of the following,
     * it will be considered equivalent if that object constructs to an {@link AugList} that:
     * - {@link Enumeration}: Same values, even if a {@link #isRearrangement(AugList) Rearrangement}
     * - {@link Iterator}, {@link Iterable}, {@link ListIterator}, {@link Spliterator}, {@link Stream} or Array:
     *   has the same {@link #toString()}, which <i>USUALLY</i> means same values, same order
     * 
     * <p>Separately:
     * - A {@link String} is equivalent if it matches the {@link #toString()} representation.
     * - A {@link Number} is equivalent if it matches the {@link #hashCode()}.
     * <p>If the given {@link Object} is none of the above, it cannot be equivalent.
     * @param   o
     *          The object in question.
     * @since   Method since AugList V2; Functionality since V1
     * @see     #isRearrangement(Iterable)
     * @see     #equals(Object)
     * @see     {@link BMEQable#isEquivalent(Object)}
     * @see     tests.AugListTest#testIsEquivalent()
     * @return  {@code true} if equivalent, and {@code false} otherwise.
     * @note    Has expanded V1 {@link #equals(Object)} behaviour.
     *          That behaviour was moved to this method because it breaks the {@link #equals(Object)} contract that states that two equal objects have equal {@link #hashCode() hashcodes},
     *          <p>and is not <i>symmetric</i> (i.e. For {@code AugList x} and {@code Object y}, {@code x.equals(y)} does not imply {@code y.equals(x)})
     *          <p>Notably, does NOT check for the state of cmp.
     * @tags    Terminator
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean isEquivalent(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        if (o instanceof String) {
            // o is a String; If it is the same as this.toString(), it will be treated as equal.
            return o.equals(this.toString());
        }
        if (o instanceof Number) {
            return ((Number)(o)).intValue() == this.hashCode();
        }
        if (o instanceof Enumeration) {
            // If o is an Enumeration<? extends T>, it will cast without throwing.
            // Enumerations come from Hashtables, so are usually unordered - hence the isRearrangement leniency.
            return isRearrangement(new AugList<T>((Enumeration<T>)o));
            // Since unchecked casting does not throw (I believe...), this try-catch is unnecessary.
        }
        AugList ALo = new AugList();
        // ListIterator is an Iterator, so they do not need to explicitly be included in this filter.
        if (o instanceof Iterator || o instanceof Spliterator || o instanceof Iterable || o instanceof Stream) {
            if (o instanceof ListIterator) {
                ListIterator oListIterator = (ListIterator)o;
                ALo = new AugList(oListIterator);
            }
            if (o instanceof Iterator && !(o instanceof ListIterator)) {
                Iterator oIterator = (Iterator)o;
                ALo = new AugList(oIterator);
            }
            if (o instanceof Iterable) {
                Iterable oIterable = (Iterable)o;
                ALo = new AugList(oIterable);
            }
            if (o instanceof Spliterator) {
                Spliterator oSpliterator = (Spliterator)o;
                ALo = new AugList(oSpliterator);
            }
            if (o instanceof Stream) {
                Stream oStream = (Stream)o;
                ALo = new AugList(oStream);
            }
            if (ALo.size() != size()) {
                return false;
            }
            return ALo.toString().equals(toString());
        }
        if (o.getClass().isArray()) {
            return isEquivalent(new AugList((Object[])o));
        }
        /** 
         * If o is not any of the supported types, then assume non-equivalence.
         * (Whilst it may be possible that o's Hashcode matches this Hashcode and o is not a supported type,
         *  This equivalence function does not consider that possibility.
         *  (This is mostly because testing the such would be quite difficult.)
         *  Hence, not all objects that are equal will be equivalent, and vice versa.)
         */
        return false;
    }

    /**
     * Returns whether this {@link AugList} is a Palindrome.
     * @return  {@code isEquivalent(reversed()) == true}
     * @since   AugList V2
     * @see     #reversed()
     * @see     tests.AugListTest#testIsPalindrome()
     * @tags    Terminator
     */
    public boolean isPalindrome() {
        return isEquivalent(reversed());
    }

    /**
     * Compares whether or not this {@link AugList} has the same elements as the given {@link Iterable}. (Order does not matter)
     * <p>For a stricter equality function, use {@link #equals()}.</p>
     * @param   itrB
     *          The {@link Iterable} B to use in the comparison.
     * @return  {@code true} if the lists are rearrangements; {@code false} otherwise.
     * @since   AugList V1
     * @see     #equals(Object)
     * @see     #isEquivalent(Object)
     * @see     tests.AugListTest#testIsRearrangement()
     * @note    Functionality is, to my knowledge, not implemented in Java or C#.
     * @tags    Terminator
     */
    public boolean isRearrangement(Iterable<? super T> itrB) {
        AugList<T> augListB = new AugList<T>(itrB);
        if (ls.size() != augListB.size()) {
            // If the two lists are different lengths, there is no world in which they are rearrangements of eachother.
            // So rather than wasting compute time, we can terminate early.
            return false;
        }
        AugList<T> CloneA = this.clone();
        AugList<T> CloneB = augListB.clone();
        // Notably this for loop does not reassign i for each loop.
        for (int i = 0; i < CloneA.size();) {
            if (!CloneB.contains(CloneA.get(i))) {
                return false;
            }
            CloneB.remove(CloneA.get(i));
            CloneA.remove(CloneA.get(i));
        }
        return true;
    }

    /**
     * Returns whether this {@link AugList} is a mathematical Set.
     * @return  {@code isEquivalent(distinctCopy()) == true}
     * @since   AugList V2
     * @see     #distinctCopy()
     * @see     #distinctSelf()
     * @see     tests.AugListTest#testIsSet()
     * @tags    Terminator
     */
    public boolean isSet() {
        return isEquivalent(distinctCopy());
    }

    /**
     * Returns whether this {@link AugList} is sorted. Always false if the given {@link Comparator} is {@link null}.
     * @param   comparator
     *          The way in which this {@link AugList} should be sorted
     * @return  {@code isEquivalent(clone().sort(comparator))) == true}
     * @since   AugList V2
     * @see     #sort(Comparator)
     * @see     tests.AugListTest#testIsSorted()
     * @tags    Terminator
     */
    public boolean isSorted(Comparator<? super T> comparator) {
        return comparator == null ? false : isEquivalent(clone().sort(comparator));
    }

    /**
     * @return  Returns a <i>fail-fast</i> {@link Iterator} over this {@link AugList} in sequence.
     * @since   AugList V1
     * @see     #listIterator()
     * @see     #spliterator()
     * @see     tests.AugListTest#testIterator()
     * @note    Encapsulates {@link List#iterator()}.
     * @tags    Converter
     */
    @Override
    public Iterator<T> iterator() {
        return ls.iterator();
    }

    /**
     * Returns the index of the final occurrence of the given object, or -1 if not present.
     * @param   o
     *          The object to search for.
     * @return  The index of the final occurrence of {@code o}, or -1 if not present.
     * @since   AugList V1
     * @see     #allIndicesOf(Object)
     * @see     #indexOf(Object)
     * @see     tests.AugListTest#testLastIndexOf()
     * @note    Encapsulates {@link ArrayList#lastIndexOf()}.
     * @tags    Terminator
     */
    public int lastIndexOf(Object o) {
        return ls.lastIndexOf(o);
    }

    /**
     * Returns the "list difference" between this (A) and the given {@link Iterable} (B).
     * <p>For a method that calculates the set difference, use {@link #setDifference(Iterable)}.
     * <p>For methods with similar functionality, see {@link #removeAll(Iterable)} and {@link #retainAll(java.util.Collection)}.
     * @param   itrB
     *          The {@link Iterable} in question, with which to take the difference of. If {@code null}, treated as an empty {@link AugList}.
     * @return  The "list difference", "A\`B".
     *          <p>i.e. [1,2]\`[1] = [2], 
     *                  [2]\`[1,2] = [], 
     *                  [1,1,2]\`[1,3] = [1,2],
     *                  [1,1,2]\`[1,1,3] = [2],
     *                  [1,1]\`[] = [1,1]
     *          <p>Output is a list, which may be a mathematical set.
     * @since   AugList V1
     * @see     #listIntersection(Iterable)
     * @see     #listUnion(Iterable)
     * @see     #setDifference(Iterable)
     * @see     #removeAll(Iterable)
     * @see     java.util.Collection#retainAll(java.util.Collection)
     * @see     tests.AugListTest#testListDifference()
     * @note    Inspired by the C# methods {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
     *          <p>"List Difference" and "A\`B" are not mathematically endorsed terminology.
     *          Works similarly, but not identically, to {@link java.util.Collection#retainAll(java.util.Collection)}
     * @tags    Creator
     */
    public AugList<T> listDifference(Iterable<? super T> itrB) {
        AugList<T> augListB = new AugList<T>(itrB); // Implicit null correction
        AugList<T> ret = clone();
        for (int i = 0; i < augListB.size(); i++) {
            if (ret.contains(augListB.get(i))) {
                ret.remove(augListB.get(i));
            }
        }
        return ret;
    }

    /**
     * Returns the "list intersection" between this (A) and the given {@link Iterable} (B).
     * <p>For a method that calculates the set intersection, use {@link #setIntersection(Iterable)}.
     * @param   itrB
     *          The {@link Iterable} in question, with which to take the intersection of. If {@code null}, treated as an empty {@link AugList}.
     * @return  The "list intersection", "A∩`B".
     *          <p>i.e. [1,1,2]∩`[1,2,3] = [1,2],
     *                  [1,2,3]∩`[1,1,2] = [1,2],
     *                  [1,1,1,2]∩`[1,1,2,3] = [1,1,2],
     *                  []∩`[1,1,3,7] = [].
     *          <p>Output is a list, which may be a mathematical set.
     * @since   AugList V1
     * @see     #listDifference(Iterable)
     * @see     #listUnion(Iterable)
     * @see     #setIntersection(Iterable)
     * @see     tests.AugListTest#testListIntersection()
     * @note    Based upon the C# method {@code IEnumerable<T>.Intersect()}.
     *          <p>"List Intersection" and "A∩`B" are not mathematically endorsed terminology.
     * @tags    Creator
     */
    public AugList<T> listIntersection(Iterable<? super T> itrB) {
        AugList<T> augListB = new AugList<T>(itrB); // Implicit null correction
        AugList<T> ret = new AugList<T>();
        for (T e : ls) {
            if (augListB.contains(e)) {
                ret.add(e);
                augListB.remove(e);
            }
        }
        return ret;
    }

    /**
     * Returns a <i>fail-fast</i> {@link ListIterator} over this {@link AugList} in proper sequence.
     * @return      A {@link ListIterator} over this {@link AugList}.
     * @since       AugList V1
     * @see         #iterator()
     * @see         #spliterator()
     * @see         tests.AugListTest#testListIterator()
     * @note        Encapsulates {@link List#listIterator()}.
     * @overloads   {@link #listIterator()}, {@link #listIterator(int)}
     * @tags        Converter
     */
    public ListIterator<T> listIterator() {
        return ls.listIterator();
    }

    /**
     * Returns a <i>fail-fast</i> {@link ListIterator} over this {@link AugList} in proper sequence, starting at the given index.
     * @param       index
     *              The index the {@link ListIterator} will start at.
     * @since       AugList V1
     * @see         #iterator()
     * @see         #spliterator()
     * @see         tests.AugListTest#testListIteratorFromIndex()
     * @return      A {@link ListIterator} over this {@link AugList} starting at the given index.
     * @note        Encapsulates {@link List#listIterator(int)}.
     * @overloads   {@link #listIterator()}, {@link #listIterator(int)}
     * @tags        Converter
     */
    public ListIterator<T> listIterator(int index) {
        return ls.listIterator(index);
        // Cannot pass index = null without another exception/error being thrown prior to this method
    }

    /**
     * Returns the "list union" between this (A) and the given {@link Iterable} (B).
     * <p>For a method that calculates the set union, use {@link #setUnion(Iterable)}.
     * @param   itrB
     *          The {@link Iterable} in question, with which to take the union on. If {@code null}, treated as an empty {@link AugList}.
     * @return  The "list union", "AU`B".
     *          <p>i.e. [1,1,2]U`[1,2,3] = [1,1,2,3],
     *                  [1,1,1,2]U`[1,1,2,3] = [1,1,1,2,3],
     *                  [1,2]U`[1,3] = [1,2,3],
     *                  [1,3]U`[] = [1,3],
     *                  []U`[1,3] = [1,3].
     *          <p>Output is a list, which may be a set.
     * @since   AugList V1
     * @see     #listDifference(Iterable)
     * @see     #listIntersection(Iterable)
     * @see     #setUnion(Iterable)
     * @see     tests.AugListTest#testListUnion()
     * @note    Based on the C# method {@code IEnumerable<T>.Union()}.
     *          <p>"List Union" and "AU`B" are not mathematically endorsed terminology.
     * @tags    Creator
     */
    public AugList<T> listUnion(Iterable<? super T> itrB) {
        AugList<T> augListB = new AugList<T>(itrB); // Implicit null correction
        AugList<T> ret = clone();
        for (int i = 0; i < ret.size(); i++) {
            augListB.remove(ret.get(i));
        }
        for (int i = 0; i < augListB.size(); i++) {
            ret.add(augListB.get(i));
        }
        return ret;
    }

    /**
     * Writes the given elements to random positions in this {@link AugList}. Cannot self-overwrite.
     * @param       elements
     *              The element in question.
     * @return      This {@link AugList}.
     * @throws      IllegalArgumentException
     *              The number of elements is larger than this {@link AugList}.
     * @since       AugList V2
     * @see         #setMany(Iterable, Iterable)
     * @see         #overwriteRandom(T)
     * @see         tests.AugListTest#testMassOverwriteRandom()
     * @overloads   {@link #massOverwriteRandom(Iterable)}, {@link #massOverwriteRandom(T...) massOverwriteRandom(T...)}
     * @tags        Mutator
     */
    public AugList<T> massOverwriteRandom(Iterable<T> elements) {
        AugList<T> ALelements = new AugList<T>(elements);
        AugList<T> clone = clone();
        if (ALelements.size() > size()) {
            throw new IllegalArgumentException("Number of elements to write cannot be longer than the size of this AugList.");
        }
        AugList<Integer> validIndices = new AugList<Integer>();
        for (int i = 0; i < ALelements.size(); i++) {
            validIndices.add(i);
        }
        for (int i = 0; i < ALelements.size(); i++) {
            int idx = validIndices.removeRandom(); // Get a random index and remove it as a valid option
            set(idx, ALelements.get(i));
        }
        if (!isEquivalent(clone)) {
            cmp = null; // At least 1 element had its value changed, so destroy cmp.
        }
        return this;
    }

    /**
     * Writes the given elements to random positions in this {@link AugList}. Cannot self-overwrite.
     * @param       elements
     *              The element in question.
     * @return      This {@link AugList}.
     * @throws      IllegalArgumentException
     *              The number of elements is larger than this {@link AugList}.
     * @since       AugList V2
     * @see         #setMany(Iterable, Iterable)
     * @see         #overwriteRandom(T)
     * @see         tests.AugListTest#testMassOverwriteRandomVarargs()
     * @overloads   {@link #massOverwriteRandom(Iterable)}, {@link #massOverwriteRandom(T...) massOverwriteRandom(T...)}
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> massOverwriteRandom(T... elements) {
        return massOverwriteRandom(new AugList<T>(elements));
    }

    // Object.notify() and Object.notifyAll() cannot be overridden and so are not implemented

    /**
     * Creates a new {@link AugList} with each element having the {@link Function} applied on it.
     * <p>For a Mutator method, use {@link #applyAll()}.</p>
     * <p>For a Terminator method, use {@link #forEach()}.</p>
     * @param   <U>
     *          The type of the resultant elements.
     * @param   func
     *          The {@link Function} to apply.
     * @throws  NullPointerException
     *          If the given {@link Function} is {@code null}.
     * @return  A new {@link AugList}, with each element transformed as according to the function.
     * @since   Method name since AugList V2, functionality since V1
     * @see     #applyAll(Function)
     * @see     #forEach(Consumer)
     * @see     tests.AugListTest#testOneToOneMap()
     * @note    Replaces {@link ArrayList#forEach(Consumer)}.
     * @tags    Creator
     */
    public <U> AugList<U> oneToOneMapCopy(Function<? super T, U> func) {
        AugList<U> ret = new AugList<U>();
        AugList<T> clone = clone();
        for (T element : ls) {
            ret.add(func.apply(element));
        }
        ls = clone.ls;
        // Function application may have side effects?
        // Thus, force no side effects by resetting to state prior to mapping.
        return ret;
        // As oneToOneMap holds the contract that this.size() == this.oneToOneMap(func).size(),
        // and as there is no way to instantiate a new T or a new U,
        // This method throws when given a null.
    }

    /**
     * Writes the given element to a random position in this {@link AugList}.
     * @param   element
     *          The element in question.
     * @return  This {@link AugList}.
     * @since   AugList V2
     * @see     #set(int, Object)
     * @see     #massOverwriteRandom(T...) massOverwriteRandom(T...)
     * @see     #massOverwriteRandom(Iterable)
     * @see     tests.AugListTest#testOverwriteRandom()
     * @tags    Mutator
     */
    public AugList<T> overwriteRandom(T element) {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        set(new Random().nextInt(size()), element);
        return this;
    }

    /**
     * Creates a {@link Hashtable} where each element in this {@link AugList} is paired with its corresponding entry in the given {@link Iterable}.
     * @param   <U>
     *          The type of elements in {@code AugListB}.
     * @param   itrB
     *          The {@link Iterable} in question.
     * @return  A {@link Hashtable} that pairs up elements,
     *          or an empty {@link Hashtable} if either:<p>
     *          - the lengths do not match, or
     *          - at least 1 entry in either list is {@code null}.
     * @since   AugList V1
     * @see     tests.AugListTest#testPairUp()
     * @note    Based on the C# function {@code IEnumerable<T>.Zip()}.
     * @tags    Converter
     */
    public <U> Hashtable<T, U> pairUp(Iterable<? super U> itrB) {
        AugList<U> augListB = new AugList<U>(itrB);
        Hashtable<T, U> ret = new Hashtable<T, U>() {};
        if (ls.size() != augListB.size() || this.anySatisfy(e -> e == null) || augListB.anySatisfy(e -> e == null)) {
            return ret;
        }
        for (int i = 0; i < ls.size(); i++) {
            ret.put(ls.get(i), augListB.get(i));
        }
        return ret;
    }

    /**
     * Returns a Parallel {@link Stream} that contains this {@link AugList}'s elements.
     * @return  A {@link Stream} with this {@link AugList} as its source.
     * @since   AugList V1
     * @see     #stream()
     * @see     tests.AugListTest#testParallelStream()
     * @note    Encapsulates {@link ArrayList#parallelStream()}.
     * @tags    Converter
     */
    public Stream<T> parallelStream() {
        return ls.parallelStream();
    }

    /**
     * Returns a {@link String} that describes the parameterized type of this {@link AugList}.
     * @see     tests.AugListTest#testParameterizedTypeDesc()
     * @return  A {@link String} describing the parameterized type, or "∅" if {@link #isEmpty()}.
     * @since   AugList V2
     * @tags    Terminator
     */
    public String parameterizedTypeDesc() {
        if (isEmpty()) {
            return "∅";
        }
        // Reaching this branch means this AugList cannot be empty,
        // so there is no need to catch NoSuchElementException.
        return this.getLast().getClass().toGenericString();
    }

    /**
     * Attempts to remove the first occurrence of the given {@link Object} from this {@link AugList}, if present.
     * <p>For a non-Terminal method, see {@link #without(Object)}.
     * @param   o
     *          The {@link Object} to remove if present.
     * @return  {@code true} if removed, and {@code false} if it was not present.
     * @since   AugList V1
     * @see     #removeAll(Iterable)
     * @see     #removeAll(T...) removeAll(T...)
     * @see     #removeAt(int)
     * @see     #removeIf(Predicate)
     * @see     #removeLast()
     * @see     #removeRandom()
     * @see     #without()
     * @see     tests.AugListTest#testRemove()
     * @note    Encapsulates {@link ArrayList#remove(Object)}.
     * @tags    Mutator, Terminator
     */
    public boolean remove(Object o) {
        if (ls.remove(o)) {
            cmp = null; // Element removed, so destroy cmp.
            return true;
        }
        return false;
    }

    /**
     * Attempts to remove the first instance of each of the supplied elements, if present, from this {@link AugList}.
     * <p>For a non-terminal method, see {@link #withoutAll(AugList)}.
     * <p>For methods with similar functionality, use {@link #listDifference(AugList)} and {@code #retainAll(java.util.Collection)}.
     * @param       iterable
     *              The source of the elements to remove in question. If {@code null}, returns {@code false}.
     * @return      {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @since       AugList V1
     * @see         #withoutAll(Iterable)
     * @see         #remove(Object)
     * @see         #listDifference(AugList)
     * @see         java.util.Collection#retainAll(java.util.Collection)
     * @see         tests.AugListTest#testRemoveAll()
     * @note        Replaces {@link List#removeAll(java.util.Collection)}.
     * @overloads   {@link #removeAll(Iterable)}, {@link #removeAll(T...) removeAll(T...)}
     * @tags        Mutator, Terminator
     */
    public boolean removeAll(Iterable<? super T> iterable) {
        AugList<T> elements = new AugList<T>(iterable); // Implicit null check
        boolean ret = false;
        for (int i = 0; i < elements.size(); i++) {
            if (ls.contains(elements.get(i))) {
                ret = true;
                ls.remove(elements.get(i));
            }
        }
        if (ret) {
            cmp = null; // At least 1 element removed, so destroy cmp.
        }
        return ret;
    }

    /**
     * Attempts to removes the first instance of each of the supplied elements, if present, from this {@link AugList}.
     * <p>For a non-terminal method, see {@link #withoutAll(T...)}.
     * <p>For methods with similar functionality, use {@link #listDifference(AugList)} and {@code #retainAll(java.util.Collection)}.
     * @param       elements
     *              The elements to remove in question. If {@code null}, returns {@code false}.
     * @return      {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @since       AugList V1
     * @see         #withoutAll(T...) withoutAll(T...)
     * @see         #remove(Object)
     * @see         #listDifference(Iterable)
     * @see         java.util.Collection#retainAll(java.util.Collection)
     * @see         tests.AugListTest#testRemoveAllVarargs()
     * @note        Varargs variant of {@link #removeAll(Iterable)}.
     * @overloads   {@link #removeAll(Iterable)}, {@link #removeAll(T...) removeAll(T...)}
     * @tags        Mutator, Terminator
     */
    @SafeVarargs
    public final boolean removeAll(T... elements) {
        return removeAll(new AugList<T>(elements));
    }

    // Works the same as a pop operation from a stack, except can pop any element rather than the top element.
    /**
     * Removes the element at the given index.
     * <p>For a Non-terminal method, see {@link #withoutIndex(int)}.
     * @param   index
     *          The index in question.
     * @return  The element that was removed.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @since   AugList V1
     * @see     #remove(Object)
     * @see     #removeLast()
     * @see     #removeRandom()
     * @see     #withoutIndex(int)
     * @see     tests.AugListTest#testRemoveAtIndex()
     * @note    Encapsulates {@code ArrayList<T>.remove(int)}.
     * @tags    Mutator, Terminator
     */
    public T removeAt(int index) {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        return ls.remove(index);
        // Cannot pass index = null without prior error/exception.
    }

    // ArrayList<T>.removeFirst() is redundant because of ArrayList<T>.remove(0) so is not implemented

    /**
     * Removes all elements in this list that satisfy the given {@code filter}.
     * <p>For a Non-terminal method, see {@link #withoutWhere(Predicate)}.
     * @param   filter
     *          The condition in question. If {@code null}, returns {@code false}.
     * @return  {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @since   AugList V1
     * @see     #remove(Object)
     * @see     #withoutWhere(Predicate)
     * @see     tests.AugListTest#testRemoveIf()
     * @note    Encapsulates {@link List#removeIf(Predicate)}.
     * @tags    Mutator, Terminator
     */
    public boolean removeIf(Predicate<? super T> filter) {
        if (Objects.isNull(filter)) {
            return false;
        }
        if (ls.removeIf(filter)) {
            cmp = null; // At least 1 element removed, so destroy cmp.
            return true;
        }
        return false;
    }

    /**
     * Pops the final element in this {@link AugList}.
     * <p>For a Non-terminal method, see {@link #withoutLast()}.
     * @return  The final element, if it exists.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V1
     * @see     #remove(Object)
     * @see     #withoutLast()
     * @see     tests.AugListTest#testRemoveLast()
     * @note    Encapsulates {@link List#removeLast()}.
     * @tags    Mutator, Terminator
     */
    public T removeLast() {
        cmp = null; // Guaranteed mutation, so destroy cmp.
        return ls.removeLast();
    }

    /**
     * Removes a random element.
     * <p>For a non-terminal method, see {@link #withoutRandom()}.
     * @return  The item that was removed.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V2
     * @see     #remove(Object)
     * @see     #withoutRandom()
     * @see     tests.AugListTest#testRemoveRandom()
     * @note    Randomized variant of {@link #removeAt(int)}.
     * @tags    Mutator, Terminator
     */
    public T removeRandom() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot remove an element from an empty list.");
        }
        cmp = null; // Guaranteed mutation, so destroy cmp.
        return ls.remove((new Random()).nextInt(ls.size()));
    }

    /*
     * (The reason as to why implementation has so far been pushed back is that
     *  listIntersection() and setIntersection() do ALMOST the same job.
     *  For the particular nuances, consider the following:
     *  With this = [1, 1, 1, 2, 2, 4] and augList2 = [1, 1, 2, 3], then:
     *  retainAll()         would yield [1, 1, 1, 2, 2],
     *  listIntersection()  would yield [1, 1, 2],
     *  setIntersection()   would yield [1, 2].
     *  )
     */

    /**
     * Retains elements that appear in both this and the given {@link Collection}.
     * If an element occurs multiple times and the given {@link Collection} contains it, each copy is kept.
     * @param   collection
     *          The {@link Collection} in question.
     * @return  {@code this}
     * @throws  NullPointerException
     *          {@code collection.equals(null)}
     * @since   AugList V2
     * @see     tests.AugListTest#testRetainAll()
     * @tags    Converter
     */
    public AugList<T> retainAll(Collection<? super T> collection) {
        ls.retainAll(collection);
        return this;
    }

    /**
     * Returns a new reversed-order {@link AugList}.
     * @return  A new {@link AugList} with the same elements as this one, but in reverse order.
     * @since   AugList V1
     * @see     #isPalindrome()
     * @see     tests.AugListTest#testReversed()
     * @note    Encapsulates {@link List#reversed()}.
     * @tags    Creator
     */
    public AugList<T> reversed() {
        return new AugList<T>(ls.reversed());
    }

    /**
     * Takes {@code size} random samples of this {@link AugList}.
     * @param   size
     *          The size of the sample to take
     * @param   withReplacements
     *          Whether or not the same element can be picked multiple times.
     * @return  A random sample of the given size.
     * @throws  IllegalArgumentException
     *          {@code (!withReplacements && size > this.size()) || size < 0}
     * @since   AugList V2
     * @see     #subList(int, int)
     * @see     tests.AugListTest#testSample()
     * @note    Randomized variant of {@link #subList(int, int)} when not using {@code withReplacements}.
     * @tags    Creator
     */
    public AugList<T> sample(int size, boolean withReplacements) {
        if (!withReplacements && size > size()) {
            throw new IllegalArgumentException("Cannot create a sample that is longer than the original list without repetition.");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Cannot create a sample with negative elements.");
        }
        AugList<T> population = this.clone(),
                   sample = new AugList<T>();
        for (int i = 0; i < size; i++) {
            T item = population.getRandom();
            if (!withReplacements) {
                population.remove(item);
            }
            sample.add(item);
        }
        return sample;
        // Neither size nor withReplacements can be set to null without an earlier exception/error.
    }

    /**
     * Sets the given index to the given element.
     * @param   index
     *          The index in question
     * @param   element
     *          The element in question
     * @return  The replaced element.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @since   AugList V1
     * @see     #setFromCallable(int, int, Callable)
     * @see     #setMany(Iterable, Iterable)
     * @see     #overwriteRandom(Object)
     * @see     tests.AugListTest#testSet()
     * @note    Encapsulates {@link List#set()}.
     * @tags    Mutator
     */
    public T set(int index, T element) {
        AugList<T> clone = clone();
        T ret = ls.set(index, element);
        if (!isEquivalent(clone)) {
            cmp = null; // The element was changed to a different value than before, so destroy cmp.
        }
        return ret;
        // index cannot be null without an earlier exception/error.
    }

    /**
     * Returns the set difference between this (A) and the provided set (B). (i.e. A\B)
     * <p>For a method that calculates the "List difference", see {@link #listDifference(Iterable)}
     * @param   itrB
     *          The source of the set of elements to take the difference with. If {@code null}, treated as an empty set.
     *          (If not already a mathematical set, will be turned into one first.)
     * @return  The set difference (A\B).
     *          <p>i.e. {1,2}\{1} = {2}, 
     *                  {2}\{1,2} = {}, 
     *                  [1,1,2]\{1,3} = {1,2},
     *                  [1,1,2]\[1,1,3] = {2},
     *                  [1,1]\{} = {1}
     *                  {1,2}\{1,3} = {2}, 
     *                  [1,1,2]\{2} = {1}
     *          <p>Output is a mathematical set.
     * @since   AugList V1
     * @see     #setIntersection(Iterable)
     * @see     #setUnion(Iterable)
     * @see     #crossProduct(Iterable)
     * @see     #listDifference(Iterable)
     * @see     tests.AugListTest#testSetDifference()
     * @note    Inspired by the C# methods {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
     * @tags    Creator
     */
    public AugList<T> setDifference(AugList<? super T> itrB) {
        AugList<T> setB = new AugList<T>(itrB).distinctSelf(); // Implicit null check
        AugList<T> ret = distinctCopy();
        for (int i = 0; i < setB.size(); i++) {
            if (ret.contains(setB.get(i))) {
                ret.remove(setB.get(i));
            }
        }
        return ret;
    }

    /**
     * Sets between the given indices with the values supplied by the given {@link Callable}.
     * <p>If the given {@link Callable} throws, does not alter this {@link AugList}.
     * @param   start
     *          The starting index, inclusive. Swaps with end if larger than it.
     * @param   end
     *          The ending index, inclusive. Expands this {@link AugList} if larger than the current size.
     * @param   callable
     *          The {@link Callable} in question, which returns the desired value(s). If {@code null}, is set to {@code () -> { return null; }}.
     * @return  This {@link AugList}.
     * @since   AugList V2
     * @see     #setMany(Iterable, Iterable)
     * @see     tests.AugListTest#testSetFromCallable()
     * @tags    Mutator
     */
    @SuppressWarnings("unchecked")
    public AugList<T> setFromCallable(int start, int end, Callable<? super T> callable) {
        if (size() != 0) {
            start = Math.clamp(start, 0, size() - 1);
        }
        if (end < 0) {
            end = 0;
        }
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        end += 1; // Make end inclusive.
        if (Objects.isNull(callable)) {
            return this; // State unaltered, so cmp survives.
        }
        AugList<T> clone = clone(), original = clone();
        for (int i = start; i < end; i++) {
            try {
                if (i >= size()) {
                    clone.add((T)callable.call());
                }
                else {
                    clone.set(i, (T)callable.call());
                }
            } catch (Exception e) {
                // If the callable throws, return the unaltered list.
                // (This means the state has not changed, so cmp survives.)
                return this;
            }
        }
        // If the callable does not throw, return the altered list.
        if (!original.isEquivalent(clone)) {
            cmp = null; // At least 1 element changed values, so destroy tmp.
        }
        return clone;
    }

    /**
     * Sets each of the given indices to its corresponding value.
     * @param   indices
     *          The indices that should be changed.
     * @param   values
     *          The values that will be written to the given indices.
     * @return  This {@link AugList}.
     * @since   AugList V2
     * @see     #set(int, Object)
     * @see     #setFromCallable(int, int, Callable)
     * @see     #massOverwriteRandom(Iterable)
     * @see     #massOverwriteRandom(T...) massOverwriteRandom(T...)
     * @see     tests.AugListTest#testSetMany()
     * @note    If the same index appears more than once, that index will be set to the value corresponding to the last occurrence of that index.
     *          <p>Such a case would rarely occur on purpose though, as i.e. setMany([1, 2, 1], [1.0, 2.0, 3.0]) can be written more quickly and concisely as setMany([1, 2], [3.0, 2.0]).
     * @tags    Mutator
     */
    public AugList<T> setMany(Iterable<Integer> indices, Iterable<? super T> values) {
        SimpleEntry<AugList<T>, AugList<Integer>> res = equaliseAndFilter(values, indices, true);
        AugList<T> ALvals = res.getKey();
        AugList<Integer> ALIdxs = res.getValue();
        for (int i = 0; i < ALIdxs.size(); i++) {
            set(ALIdxs.get(i), ALvals.get(i));
        }
        // Each individual set will destroy cmp if a state change occurs.
        return this;
    }

    /**
     * Returns the set intersection between this (A) and the given set (B). (I.e. A∩B)
     * <p>For a method that calculates the "List Intersection", see {@link #listIntersection(Iterable)}.
     * @param   itrB
     *          The source of the set of elements to take the intersection with. If {@code null}, treated as an empty set.
     *          (If not already a mathematical set, will be turned into one first.)
     * @return  The set intersection (A∩B), or an AugList with elements that appear in both sets. 
     *          <p>i.e. [1,1,2]∩[1,2,3] = {1,2}, 
     *                  [1,1,1,2]∩[1,1,2,3] = {1,2},
     *                  {}∩[1,1,3,7] = {}.
     *          <p>Output is a mathematical set.
     * @since   AugList V1
     * @see     #setDifference(Iterable)
     * @see     #setUnion(Iterable)
     * @see     #crossProduct(Iterable)
     * @see     #listIntersection(Iterable)
     * @see     tests.AugListTest#testSetIntersection()
     * @note    Based on the C# method {@code IEnumerable<T>.Intersect()}.
     * @tags    Creator
     */
    public AugList<T> setIntersection(Iterable<? super T> itrB) {
        AugList<T> setB = new AugList<T>(itrB).distinctSelf(); // Implicit null check
        if (isEquivalent(setB)) {
            return setB;
        }
        AugList<T> ret = new AugList<T>();
        for (T e : ls) {
            if (setB.contains(e)) {
                ret.add(e);
                setB.remove(e);
            }
        }
        return ret;
    }

    /**
     * Returns the set union between this and the given set. (i.e. AUB)
     * <p>For a method that returns the "List Union", see {@link #listUnion(Iterable)}.
     * @param   itrB
     *          The source of the set of elements to union with. (If not one already, is made into one first.) If {@code null}, treated as an empty set.
     * @return  The set union (AUB).
     *          <p>i.e. {0,1,2}U{1,2,3} = {0,1,2,3},
     *                  [1,1,1,2]U[1,1,2,3] = {1,2,3}.
     *          <p>Output is a mathematical set.
     * @since   AugList V1
     * @see     #setDifference(Iterable)
     * @see     #setIntersection(Iterable)
     * @see     #crossProduct(Iterable)
     * @see     #listUnion(Iterable)
     * @see     tests.AugListTest#testSetUnion()
     * @note    Based on the C# function {@code IEnumerable<T>.Union()}.
     * @tags    Creator
     */
    public AugList<T> setUnion(Iterable<? super T> itrB) {
        AugList<T> setB = new AugList<T>(itrB).distinctSelf(); // Implicit null check
        AugList<T> ret = new AugList<T>();
        ret.addAll(distinctCopy());
        ret.addAll(setB.filterSelf(e -> !ret.contains(e)));
        return ret;
    }

    /**
     * Creates a new shuffled {@link AugList}. Can shuffle to itself.
     * <p>For a Mutator method, see {@link #shuffleSelf()}.
     * @return  A new {@link AugList} which is a shuffled copy of this one.
     * @since   AugList V1
     * @see     #shuffleSelf()
     * @see     tests.AugListTest#testShuffleCopy()
     * @tags    Creator
     */
    public AugList<T> shuffleCopy() {
        Random r = new Random();
        AugList<Integer> validTarget = new AugList<Integer>();
        for (int i = 0; i < ls.size(); i++) {
            validTarget.add(i);
        }
        AugList<T> ret = this.clone();
        for (int i = 0; i < ls.size(); i++) {
            Integer selectedIndex = r.nextInt(validTarget.size());
            ret.set(i, ls.get(validTarget.get(selectedIndex)));
            validTarget.removeAt(selectedIndex);
        }
        return ret;
    }

    /**
     * Shuffles this {@link AugList}. Can shuffle to itself.
     * <p>For a Creator method, see {@link #shuffleCopy()}.
     * @return  This {@link AugList}, shuffled into a random order.
     * @since   AugList V2
     * @see     #shuffleCopy()
     * @see     tests.AugListTest#testShuffleSelf()
     * @tags    Mutator
     */
    public AugList<T> shuffleSelf() {
        AugList<T> clone = clone();
        AugList<T> shuffled = shuffleCopy();
        this.clear().addAll(shuffled);
        if (!isEquivalent(clone)) {
            cmp = null; // Order has changed, so destroy cmp.
        }
        return this;
    }

    /**
     * Returns the number of elements within this {@link AugList}
     * @return  The size of this {@link AugList}.
     * @since   AugList V1
     * @see     tests.AugListTest#testSize()
     * @note    Encapsulates {@link List#size()}.
     * @tags    Terminator
     */
    public int size() {
        return ls.size();
    }

    /**
     * Skips elements until the first element to fail the given condition, then returns the failing and all proceeding elements.
     * @param   condition
     *          The condition in question. If {@code null}, returns a {@link #clone()} of {@code this}.
     * @return  A new {@link AugList} with elements beyond and including the first to fail the given condition.
     * @since   AugList V1
     * @see     #takeWhile(Predicate)
     * @see     tests.AugListTest#testSkipWhile()
     * @note    Based on the C# method {IEnumerable<T>.skipWhile()}.
     * @tags    Creator
     */
    public AugList<T> skipWhile(Predicate<? super T> condition) {
        if (Objects.isNull(condition)) {
            return this.clone();
        }
        AugList<T> ret = new AugList<T>();
        for (int i = 0; i < ls.size(); i++) {
            if (!condition.test(ls.get(i))) {
                for (int j = i; j < ls.size(); j++) {
                    ret.add(ls.get(j));
                }
                return ret;
            }
        }
        return ret;
    }

    /**
     * Sorts this AugList according to the given {@link Comparator}.
     * @param   comparator
     *          The {@link Comparator} in question. If {@code null}, returns {@code this}.
     * @return  This {@link AugList}, sorted according to the given {@link Comparator}.
     * @since   AugList V1
     * @see     #isSorted(Comparator)
     * @see     tests.AugListTest#testSort()
     * @note    Replaces {@link List#sort()}, returns {@code this} rather than {@code void}.
     *          <p>The only mutator that does not destroy cmp.
     *          <p>(Instead, sets cmp to the given comparator if non-null, and preserves if null.)
     * @tags    Mutator
     */
    public AugList<T> sort(Comparator<? super T> comparator) {
        if (!Objects.isNull(comparator)) {
            ls.sort(comparator);
            cmp = comparator;
        }
        return this;
    }

    /**
     * Creates a new {@link AugList} of AugLists, that partitions this AugList in the following manner:
     * <p>Index 0 contains all elements to the left of the pivot
     * <p>Index 1 contains the pivot only
     * <p>Index 2 contains all elements to the right of the pivot 
     * @param   pivot
     *          The pivot in question
     * @throws  IndexOutOfBoundsException
     *          {@code size() != 0 && (pivot < 0 || pivot >= size())}
     * @return  A partitioned AugList - see body text.
     * @since   AugList V2
     * @see     #chunk(int)
     * @see     #fragment()
     * @see     #splitDelim(Function)
     * @see     tests.AugListTest#testSplit3()
     * @note    Creator
     */
    public AugList<AugList<T>> split3(int pivot) {
        if (size() == 0) {
            return new AugList<AugList<T>>(new AugList<T>(), new AugList<T>(), new AugList<T>());
        }
        if (pivot < 0 || pivot >= size()) {
            throw new IndexOutOfBoundsException("AugList<T>.split3(pivot) Exception: pivot must be within bounds.");
        }
        AugList<AugList<T>> ret = new AugList<AugList<T>>(new AugList<T>(), new AugList<T>(), new AugList<T>());
        for (int i = 0; i < size(); i++) {
            if (i < pivot) {
                ret.get(0).add(get(i));
            }
            else if (i == pivot) {
                ret.get(1).add(get(i));
            }
            else {
                ret.get(2).add(get(i));
            }
        }
        return ret;
    }

    /**
     * Splits this {@link AugList} into an AugList of AugLists, delimiting by elements that pass the given condition.
     * @param   condition
     *          The condition in question. Elements that pass this will be used to delimit the resultant AugList, and are not included in it.
     *          If {@code null}, returns an AugList of AugLists with 1 entry - that entry is {@code this}.
     * @return  A delimited AugList of AugLists
     * @since   AugList V2
     * @see     #chunk(int)
     * @see     #fragment()
     * @see     #split3(int)
     * @see     tests.AugListTest#testSplitDelim()
     * @note    Creator
     */
    public AugList<AugList<T>> splitDelim(Function<? super T, Boolean> condition) {
        if (Objects.isNull(condition)) {
            return new AugList<AugList<T>>(this);
        }
        AugList<AugList<T>> ret = new AugList<AugList<T>>(new AugList<T>());
        for (int i = 0; i < size(); i++) {
            if (condition.apply(get(i))) {
                ret.add(new AugList<T>());
            }
            else {
                ret.getLast().add(get(i));
            }
        }
        return ret;
    }

    /**
     * @return  A <i>late-binding, fail-fast</i> {@link Spliterator} over this {@link AugList} in proper sequence.
     * @since   AugList V1
     * @see     #iterator()
     * @see     #listIterator()
     * @see     Spliterator#DISTINCT
     * @see     Spliterator#NONNULL
     * @see     Spliterator#ORDERED
     * @see     Spliterator#SIZED
     * @see     Spliterator#SORTED
     * @see     Spliterator#SUBSIZED
     * @see     tests.AugListTest#testSpliterator()
     * @note    Encapsulates {@link List#spliterator()}.
     *          <p>An {@link ArrayList.ArrayListSpliterator} is ORDERED, SIZED and SUBSIZED.
     *          <p>If this {@link AugList} is a set, contains no nulls or is sorted, then sets those fields too.
     * @tags    Converter
     */
    public Spliterator<T> spliterator() {
        final ArrayList<T> LS = ls;
        Spliterator<T> spl = LS.spliterator();
        final Comparator<? super T> CMP = cmp;
        final boolean ISSET = isSet(),
                      HASNONULLS = countOf(null) == 0,
                      ISSORTED = isSorted(cmp);
        if (!(ISSET || HASNONULLS || ISSORTED)) {
            return LS.spliterator();
        }
        return new Spliterator<T>() {

            /**
             * If a remaining element exists: performs the given action on it,
             * returning {@code true}; else returns {@code false}.  If this
             * Spliterator is {@link Spliterator#ORDERED} the action is performed on the
             * next element in encounter order.  Exceptions thrown by the
             * action are relayed to the caller.
             * <p>
             * Subsequent behavior of a Spliterator is unspecified if the action throws
             * an {@link Exception}.
             *
             * @param   action
             *          The action whose operation is performed at-most once
             * @return  {@code false} if no remaining elements existed
             *          upon entry to this method, else {@code true}.
             * @throws  NullPointerException
             *          if the specified action is null
             * @note    Encapsulates {@link ArrayList.ArrayListSpliterator#tryAdvance(Consumer)}.
             *          Doc string is a near copy of the linked method's doc string.
             *          <p>As ArrayListSpliterator is a private class, hovering over the doc string in VSCode does not bring up ArrayListSpliterator's documentation box.
             *          <p>Similarly, trying to ctrl-click {@code #tryAdvance(Consumer)} fails.
             *          <p>Instead, hover over {@link #tryAdvance(Consumer)} and click the link in the documentation box.
             * @tags    Terminator
             */
            public boolean tryAdvance(Consumer<? super T> action) {
                return spl.tryAdvance(action);
            }

            /**
             * If this {@link Spliterator} can be partitioned, returns a Spliterator
             * covering elements, that will, upon return from this method, not
             * be covered by this Spliterator.
             *
             * <p>If this Spliterator is {@link Spliterator#ORDERED}, the returned Spliterator
             * must cover a strict prefix of the elements.
             *
             * <p>Unless this Spliterator covers an infinite number of elements,
             * repeated calls to {@link #trySplit()} must eventually return {@code null}.
             * Upon non-null return:
             * <ul>
             * <li>the value reported for {@link #estimateSize()} before splitting,
             * must, after splitting, be greater than or equal to {@link #estimateSize()}
             * for this and the returned Spliterator; and</li>
             * <li>if this Spliterator is {@link Spliterator#SUBSIZED}, then {@link #estimateSize()}
             * for this spliterator before splitting must be equal to the sum of
             * {@link #estimateSize()} for this and the returned Spliterator after
             * splitting.</li>
             * </ul>
             *
             * <p>This method may return {@code null} for any reason,
             * including emptiess, inability to split after traversal has
             * commenced, data structure constraints, and efficiency
             * considerations.
             *
             * @apiNote
             * An ideal {@code trySplit} method efficiently (without
             * traversal) divides its elements exactly in half, allowing
             * balanced parallel computation.  Many departures from this ideal
             * remain highly effective; for example, only approximately
             * splitting an approximately balanced tree, or for a tree in
             * which leaf nodes may contain either one or two elements,
             * failing to further split these nodes.  However, large
             * deviations in balance and/or overly inefficient {@code trySplit}
             * mechanics typically result in poor parallel
             * performance.
             *
             * @return  A {@link Spliterator} covering some portion of the
             *          elements, or {@code null} if this spliterator cannot be split
             * @note    Encapsulates {@link ArrayList.ArrayListSpliterator#trySplit()}.
             *          Doc string is a near copy of the linked method's doc string.
             * @tags    Terminator
             */
            public Spliterator<T> trySplit() {
                return spl.trySplit();
            }

            /**
             * Returns an estimate of the number of elements that would be
             * encountered by a {@link #forEachRemaining} traversal, or returns
             * {@link Long#MAX_VALUE} if infinite, unknown, or too expensive to compute.
             *n
             * <p>If this Spliterator is {@link Spliterator#SIZED} and has not yet been partially
             * traversed or split, or this Spliterator is {@link Spliterator#SUBSIZED} and has
             * not yet been partially traversed, this estimate must be an accurate
             * count of elements that would be encountered by a complete traversal.
             * Otherwise, this estimate may be arbitrarily inaccurate, but must decrease
             * as specified across invocations of {@link #trySplit}.
             *
             * @apiNote
             * Even an inexact estimate is often useful and inexpensive to compute.
             * For example, a sub-spliterator of an approximately balanced binary tree
             * may return a value that estimates the number of elements to be half of
             * that of its parent; if the root Spliterator does not maintain an
             * accurate count, it could estimate size to be the power of two
             * corresponding to its maximum depth.
             *
             * @return  The estimated size, or {@link Long#MAX_VALUE} if infinite,
             *          unknown, or too expensive to compute.
             * @note    Encapsulates {@link ArrayList.ArrayListSpliterator#estimateSize()}.
             *          Doc string is a near copy of the linked method's doc string.
             * @tags    Terminator
             */
            public long estimateSize() {
                return spl.estimateSize();
            }

            /**
             * Returns a set of characteristics of this Spliterator and its
             * elements. The result is represented as ORed values from
             * {@link Spliterator#ORDERED}, {@link Spliterator#DISTINCT}, {@link Spliterator#SORTED}, {@link #SIZED},
             * {@link Spliterator#NONNULL}, {@link Spliterator#IMMUTABLE}, {@link Spliterator#CONCURRENT},
             * {@link Spliterator#SUBSIZED}.  Repeated calls to {@link #characteristics()} on
             * a given spliterator, prior to or in-between calls to {@link #trySplit},
             * should always return the same result.
             *
             * <p>If a Spliterator reports an inconsistent set of
             * characteristics (either those returned from a single invocation
             * or across multiple invocations), no guarantees can be made
             * about any computation using this Spliterator.
             *
             * @apiNote The characteristics of a given spliterator before splitting
             * may differ from the characteristics after splitting.  For specific
             * examples see the characteristic values {@link Spliterator#SIZED}, {@link Spliterator#SUBSIZED}
             * and {@link Spliterator#CONCURRENT}.
             *
             * @return  A representation of characteristics
             * @note    Overrides {@link ArrayList.ArrayListSpliterator#characteristics()}.
             *          <p>Also sets {@link Spliterator#DISTINCT}, {@link Spliterator#SORTED} and {@link Spliterator#NONNULL} if this {@link AugList} satisfies them.
             *          <p>Doc string is a near copy of the linked method's doc string.
             * @tags    Terminator
             */
            @Override
            public int characteristics() {
                return spl.characteristics() | (ISSET ? Spliterator.DISTINCT : 0) | (ISSORTED ? Spliterator.SORTED : 0) | (HASNONULLS ? Spliterator.NONNULL : 0);
            }

            /**
             * If this {@link Spliterator}'s source is {@link Spliterator#SORTED} by a {@link Comparator},
             * returns that {@link Comparator}. Otherwise,
             * if the source is not {@link Spliterator#SORTED}, throws {@link IllegalStateException}.
             *
             * @return  The {@link Comparator} that orders this {@link Spliterator}'s elements, if it exists.
             * @throws  IllegalStateException
             *          If the spliterator does not report a characteristic of {@code SORTED}.
             */
            @Override
            public Comparator<? super T> getComparator() {
                if (Objects.isNull(CMP)) {
                    throw new IllegalStateException();
                }
                // Since a comparator over an unknown parameterised type cannot be in a natural order,
                // this will never return null.
                return CMP;
            }
        };
    }

    /**
     * Returns a sequential {@link Stream} with this {@link AugList} as its source.
     * @return  A sequential {@link Stream} with the elements of this {@link AugList}.
     * @since   AugList V1
     * @see     #parallelStream()
     * @see     tests.AugListTest#testStream()
     * @note    Encapsulates {@link java.util.Collection#stream()}.
     * @tags    Converter
     */
    public Stream<T> stream() {
        return ls.stream();
    }

    /**
     * Returns a new {@link AugList} with exactly the elements between the provided indices, incl-exclusive
     * @param   fromIndex
     *          The start index of the sublist, inclusive.
     * @param   toIndex
     *          The end index of the sublist, exclusive.
     * @return  A new {@link AugList} with the specified elements.
     * @throws  IllegalArgumentException
     *          If {@code fromIndex > toIndex}.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @since   AugList V1
     * @see     #sample(int, boolean)
     * @see     tests.AugListTest#testSubList()
     * @note    Replaces {@code ArrayList<T>.subList()}, returning a {@link AugList} rather than a {@link List}.
     * @tags    Creator
     */
    public AugList<T> subList(int fromIndex, int toIndex) {
        return new AugList<T>(ls.subList(fromIndex, toIndex));
        // Neither index can be passed in as null without a prior error/exception.
    }

    /**
     * Swaps the given elements. Does nothing if the given indices are equal.
     * @param   index1
     *          Index of the first element to swap.
     * @param   index2
     *          Index of the second element to swap.
     * @throws  IndexOutOfBoundsException
     *          {@code Index1 < 0 || Index1 >= this.size() || Index2 < 0 || Index2 >= this.size()}
     * @return  This {@link AugList}, with the given elements swapped.
     * @see     #swapRandom(int)
     * @see     #swapRandom()
     * @see     #swapRanges(int, int, int, int)
     * @see     tests.AugListTest#testSwap()
     * @since   AugList V1
     * @note    Inspired by the (Binary) Insertion Sort algorithm, which requires the ability to swap 2 elements.
     * @tags    Mutator
     */
    public AugList<T> swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size() || index2 < 0 || index2 >= size()) {
            throw new IndexOutOfBoundsException("Indices must be in bounds.");
        }
        // If the indices are the same, then swapping can be skipped.
        if (index1 == index2) {
            return this;
        }
        T temp = ls.get(index1);
        ls.set(index1, ls.get(index2));
        ls.set(index2, temp);
        // The sets will destroy cmp if a state change occurs.
        return this;
        // Neither index can be passed in as null without a prior error/exception.
    }

    /**
     * Swaps the given element with a random element. Always alters state.
     * @param   index
     *          The index of the item to swap.
     * @return  This {@link AugList} with the given element swapped into a random position.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @since   AugList V2
     * @see     #swap(int, int)
     * @see     #swapRandom()
     * @note    Semi-randomized variant of {@link #swap(int, int)}.
     * @tags    Mutator
     */
    public AugList<T> swapRandom(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index must be within bounds.");
        }
        AugList<Integer> availableIndices = new AugList<Integer>();
        for (int i = 0; i < size(); i++) {
            availableIndices.add(i);
        }
        availableIndices.remove(index);
        int index2 = availableIndices.removeRandom();
        swap(index, index2); // Swap will destroy cmp if a state change occurs.
        return this;
        // index can be passed in as null without a prior error/exception.
    }

    /**
     * Swaps two elements at random. Always alters state.
     * @return  This {@link AugList} with 2 random elements swapped.
     * @since   AugList V2
     * @see     #swap(int, int)
     * @see     #swapRandom(int)
     * @see     tests.AugListTest#testSwapRandomNoParam()
     * @note    Fully-randomized variant of {@link #swap(int, int)}.
     * @tags    Mutator
     */
    public AugList<T> swapRandom() {
        AugList<Integer> availableIndices = new AugList<Integer>();
        for (int i = 0; i < size(); i++) {
            availableIndices.add(i);
        }
        int index1 = availableIndices.removeRandom();
        availableIndices.remove(index1);
        int index2 = availableIndices.removeRandom();
        swap(index1, index2); // swap destroys cmp if a state change occurs.
        return this;
    }

    /**
     * Swaps ranges A and B.
     * @param   leftA
     *          The left of range A, inclusive. Swaps with right if necessary.
     * @param   rightA
     *          The right of range A, exclusive. Swaps with left if necessary.
     * @param   leftB
     *          The left of range B, inclusive. Swaps with right if necessary.
     * @param   rightB
     *          The right of range A, inclusive. Swaps with left if necessary.
     * @throws  IndexOutOfBoundsException
     *          If either left parameter satisfies {@code left < 0 || left >= size()} or either right parameter satisfies {@code right < 0 || right >= size() + 1}
     * @throws  IllegalArgumentException
     *          Ranges A and B overlap (After swapping, {@code rightA > leftB})
     * @return  this
     * @since   AugList V2
     * @see     #swap(int, int)
     * @see     tests.AugListTest#testSwapRanges()
     * @tags    Mutator
     */
    public AugList<T> swapRanges(int leftA, int rightA, int leftB, int rightB) {
        if (leftA < 0 || leftA >= size() || rightA < 0 || rightA >= size() + 1 || leftB < 0 || leftB >= size() || rightB < 0 || rightB >= size() + 1) {
            throw new IndexOutOfBoundsException("Cannot swap non-existent elements.");
        }
        if (leftA > rightA) {
            //throw new IllegalArgumentException();
            int temp = leftA;
            leftA = rightA;
            rightA = temp;
        }
        if (leftB > rightB) {
            //throw new IllegalArgumentException();
            int temp = leftB;
            leftB = rightB;
            rightB = temp;
        }
        // If the ranges are valid, but the range A starts after the range B, swap them
        if (leftB < rightA) {
            int temp = leftA;
            leftA = leftB;
            leftB = temp;
            temp = rightA;
            rightA = rightB;
            rightB = temp;
        }
        /**
         * At this point, the ranges can either look like this:
         *   a   A  b   B
         * --|===|--|===|--
         * 
         * Or these erroneous possibilities:
         *   a   b  A   B
         * --|===|++|===|--
         * 
         *   a   b  B   A
         * --|===|++|===|--
         * 
         * Both erroneous possibilities have leftB (b) less than rightA (A)
         */
        if (leftB < rightA) {
            throw new IllegalArgumentException("Cannot swap overlapping ranges");
        }
        AugList<T>  ALleft  = subList(0, leftA), 
                    ALa     = subList(leftA , rightA), 
                    ALmid   = subList(rightA, leftB), 
                    ALb     = subList(leftB , rightB), 
                    ALright = subList(rightB, size());
        AugList<T> ret = ALleft.addAll(ALb).addAll(ALmid).addAll(ALa).addAll(ALright);
        if (!isEquivalent(ret)) {
            cmp = null; // Order changed, so destroy cmp.
        }
        return ret;
    }

    /**
     * Takes elements up and until it finds the first element to fail the given condition.
     * @param   condition
     *          The condition in question. If {@code null}, returns a new empty {@link AugList}.
     * @return  Elements up to and including the first to fail the given condition.
     * @since   AugList V1
     * @see     #skipWhile(Predicate)
     * @see     tests.AugListTest#testTakeWhile()
     * @note    Based on the C# method {IEnumerable<T>.TakeWhile()}.
     * @tags    Creator
     */
    public AugList<T> takeWhile(Predicate<? super T> condition) {
        if (Objects.isNull(condition)) {
            return new AugList<T>();
        }
        AugList<T> ret = new AugList<T>();
        for (int i = 0; i < ls.size(); i++) {
            ret.add(ls.get(i));
            if (!condition.test(ls.get(i))) {
                return ret;
            }
        }
        return ret;
    }

    // ArrayList<T>.toArray() without arguments is for all practical purposes useless
    // (as an array of Objects is difficult to parse in any meaningful way), so is not implemented.
    // That being said, if I could instantiate an array of T (T[]), then I would encapsulate and override the toArray() method.

    /**
     * Create an {@link Arrays Array} copy of this AugList with the specified output type.
     * @param   typedArr
     *          An {@link Arrays Array} of type {@code T}.
     * @return  An {@link Arrays Array} copy of this {@link AugList}.
     * @throws  ArrayStoreException
     *          The type at runtime of the specified array is not a supertype of the type of every element
     * @throws  NullPointerException
     *          The specified array is {@code null}
     * @since   AugList V1
     * @see     tests.AugListTest#testToArrayGivenType()
     * @note    Encapsulates {@link List#toArray(T[])}.
     * @tags    Converter
     */
    public T[] toArray(T[] typedArr) {
        return ls.toArray(typedArr);
    }

    /**
     * Casts this {@link AugList} to a {@link Collection}.
     * @return  This {@link AugList}, as a {@link Collection}.
     * @since   AugList V2
     * @see     tests.AugListTest#testToCollection()
     * @tags    Converter
     */
    public Collection<T> toCollection() {
        Collection<T> ret = new ArrayList<T>() {};
        for (int i = 0; i < size(); i++) {
            ret.add(get(i));
        }
        return ret;
    }

    /**
     * Casts this {@link AugList} to an {@link Enumeration}. DOES NOT PRESERVE ORDER.
     * @return  This {@link AugList}, as a {@link Enumeration}.
     * @since   AugList V2
     * @see     tests.AugListTest#testToEnumeration()
     * @tags    Converter
     */
    public Enumeration<T> toEnumeration() {
        Hashtable<T, T> hashtable = new Hashtable<T, T>() {};
        for (T element : ls) {
            hashtable.put(element, element);
        }
        return (Enumeration<T>)hashtable.elements();
    }

    // Returns this AugList as a string. Example outputs: "/", "[1,2,3,4]", "[a,b,c,d]"
    /**
     * This {@link AugList}'s contents, as a human-legible {@link String}.
     * @return  This, as a {@link String}.
     * @since   AugList V1
     * @see     tests.AugListTest#testToString()
     * @note    Example outputs: {@code "[1, 2, 3]"}, {@code "[d, c, a]"} and {@code "[2.0, 7.11, -3.2]"}.
     *          <p>Special cases:
     *          - Empty lists are represented as {@code "/"}.<p>
     *          - If this {@link AugList} contains a {@code null}, it is printed as {@code "*null*"} - i.e. {@code "[*null*, ae]"}.
     * @tags    Terminator         
     */
    @Override
    public String toString() {
        String ListAsString = "[";
        if (ls.size() == 0) {
            return "/";
        }
        for (T element : ls) {
            String elementString = null;
            try {
                elementString = element.toString();
            } catch (Exception ex) {
                elementString = "*null*";
            }
            ListAsString += elementString + ", ";
        }
        return ListAsString.substring(0, ListAsString.length() - 2) + "]";
    }

    // Object.wait() and its parameterized overloads cannot be overridden and so are not implemented.

    /**
     * Attempts to remove the first instance of {@code o} from this {@link AugList}, if it exists.
     * <p>For a Terminator method, see {@link #remove(Object)}.
     * @param   o
     *          The {@link Object} to remove if present.
     * @return  {@code this}.
     * @since   AugList V1
     * @see     #withoutAll(Iterable)
     * @see     #withoutAll(T...) withoutAll(T...)
     * @see     #withoutIndex(int)
     * @see     #withoutWhere(Predicate)
     * @see     #withoutLast()
     * @see     #withoutRandom()
     * @see     #remove()
     * @see     tests.AugListTest#testWithout()
     * @note    Mutator variant of {@link #remove(Object)}.
     * @tags    Mutator
     */
    public AugList<T> without(Object o) {
        ls.remove(o); // Destroys cmp if state changes
        return this;
    }

    /**
     * Attempts to remove the first instance of each of the supplied elements, if present, from this {@link AugList}.
     * <p>For a Terminator method, use {@link #removeAll(Iterable)}.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code this} unaltered.
     * @return      {@code this}.
     * @since       AugList V1
     * @see         #without(Object)
     * @see         #removeAll(Iterable)
     * @see         tests.AugListTest#testRemoveAll()
     * @overloads   {@link #withoutAll(Iterable)}, {@link #withoutAll(T...) withoutAll(T...)}
     * @note        Mutator variant of {@link #removeAll(Iterable)}.
     * @tags        Mutator
     */
    public AugList<T> withoutAll(Iterable<? super T> elements) {
        removeAll(elements); // Destroys cmp if state changes
        return this;
    }

    /**
     * Attempts to remove the first instance of each of the supplied items, if present, from this {@link AugList}.
     * <p>For a Terminator method, use {@link #removeAll(T...) removeAll(T...)}.
     * @param       elements
     *              The elements in question. If {@code null}, returns {@code this} unaltered.
     * @return      {@code this}.
     * @since       AugList V1
     * @see         #without(Object)
     * @see         #removeAll(Iterable)
     * @see         tests.AugListTest#testWithoutAllVarargs()
     * @overloads   {@link #withoutAll(Iterable)}, {@link #withoutAll(T...) withoutAll(T...)}
     * @note        Mutator variant of {@link #removeAll(T...) removeAll(T...)}.
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> withoutAll(T... elements) {
        removeAll(elements); // Destroys cmp if state changes
        return this;
    }

    /**
     * Removes the item at the given index.
     * <p>For a Terminator method, use {@link #removeAt(int)}.
     * @param   index
     *          The index in question.
     * @return  {@code this}.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
     * @since   AugList V1
     * @see     #without()
     * @see     #withoutRandom()
     * @see     #withoutLast()
     * @see     #removeAt()
     * @see     tests.AugListTest#testWithoutIndex()
     * @note    Mutator variant of {@link #removeAt(int)}.
     * @tags    Mutator
     */
    public AugList<T> withoutIndex(int index) {
        ls.remove(index); // Destroys cmp when state changes
        return this;
        // index cannot be null without a prior exception/error
    }

    // this.withoutFirst() would be redundant because of this.without(0) so is not implemented

    /**
     * Removes the tail from this list.
     * <p>For a Terminator method, use {@link #removeLast()}.
     * @return  {@code this}.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V1
     * @see     #without(Object)
     * @see     #withoutIndex(int)
     * @see     #removeAt(int)
     * @see     tests.AugListTest#testWithoutLast()
     * @note    Mutator variant of {@link #removeLast()}.
     * @tags    Mutator
     */
    public AugList<T> withoutLast() {
        ls.removeLast(); // Destroys cmp when state changes
        return this;
    }

    /**
     * Removes a random element.
     * <p>For a Terminator method, use {@link #removeRandom()}.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @return  {@code this}.
     * @since   AugList V2
     * @see     #without(Object)
     * @see     #withoutIndex(int)
     * @see     #removeRandom()
     * @see     tests.AugListTest#testWithoutRandom()
     * @note    Mutator variant of {@link #removeRandom()}.
     * @tags    Mutator
     */
    public AugList<T> withoutRandom() {
        removeRandom(); // Destroys cmp when state changes
        return this;
    }

    /**
     * Removes all elements in this list that satisfy the given filter.
     * <p>For a Terminator method, use {@link #removeIf()}.
     * @param   filter
     *          The condition that, if an element passes it, causes its deletion. If {@code null}, returns {@code this} unaltered.
     * @return  {@code this}.
     * @since   AugList V1
     * @see     #without(Object)
     * @see     #removeIf(Predicate)
     * @see     tests.AugListTest#testWithoutWhere()
     * @note    Mutator variant of {@link #removeIf(Predicate)}.
     * @tags    Mutator
     */
    public AugList<T> withoutWhere(Predicate<? super T> filter) {
        removeIf(filter); // Destroys cmp if state changes
        return this;
    }

    //#region Misc deprecated
    // /**
    //  * Create a new AugList that is the given length, filled with the given value.
    //  * @deprecated  Due to a lack of use cases.
    //  * @param       fill
    //  *              What to fill this AugList with.
    //  * @param       size
    //  *              How long the AugList should be.
    //  * @throws      IllegalArgumentException
    //  *              {@code size < 0}
    //  * @since       AugList V1, Deprecated since V2
    //  * @tags        Constructor
    //  */
    // @Deprecated(since = "2", forRemoval = true)
    // public AugList(T fill, int size) {
    //     this();
    //     if (size < 0) {
    //         throw new IllegalArgumentException("size must be positive.");
    //     }
    //     for (int i = 0; i < size; i++) {
    //         this.ls.add(fill);
    //     }
    // }

    // Was a part of an attempted change to .isEquivalent() that went nowhere.
    // /**
    //  * Helper class that is used in isEquivalent(), consisting of 2 fields and a single constructor.
    //  * @deprecated   Due to not working as intended
    //  * @since        AugList pre-alpha, deprecated V1
    //  */
    // @Deprecated(since = "1", forRemoval = true)
    // private class TypeFinder {
    //     // This only works if we don't force a parameter on the AugList.
    //     @SuppressWarnings({ "rawtypes", "unused" })
    //     AugList genericAugList;
    //     AugList<T> thisAugList;

    //     public TypeFinder(@SuppressWarnings("rawtypes") AugList genericAugList, AugList<T> thisAugList) {
    //         this.genericAugList = genericAugList;
    //         this.thisAugList = thisAugList;
    //     }
    // }

    // Method currently unnecessary, so has been commented.
    /**
     * Finds if the two {@link AugList AugLists} are in an Equivalence Relationship.
     * @deprecated  Due to lack of use cases.
     * @param       augListB
     *              The second {@link AugList}.
     * @return      {@code true} if the lists are in a EqRel, and {@code false} otherwise.
     * @since       AugList pre-alpha, deprecated V1
     * @note        Custom Method that is a pseudo-reimplementation of {@code ArrayList<T>.isEquivalent()}.
     *              <p>If ~ is a relation (a mapping), then if it fulfils the following:
     *               <p>Reflexive (x ~ x)
     *               <p>Symmetric (x ~ y <=> y ~ x)
     *               <p>Transitive (x ~ y ^ y ~ z => x ~ z)
     *               <p>Consistent (x ~ y => x ~ y for as long as x, y are constant)
     *               <p>Non-null equivalence ( x != null <=> x !~ null )
     *               <p>Then they must be equivalent.
     *               <p>Any such relation is called an equivalence relation.
     *               <p>(Notably any such EqRel is a one to one mapping.)
     * @tags        Terminator
     */
    // @Deprecated(since = "1", forRemoval = true)
    // public boolean isEqRel(AugList<T> augListB) {
        // if (ls.size() != augListB.size()) {
        //     // If the two lists are different lengths, there is no world in which an EqRel can exist.
        //     // So rather than wasting compute time, we can terminate early.
        //     return false;
        // }
        // if (!this.allSatisfy(x -> x.isEquivalent(x)) || !augListB.allSatisfy(x -> x.isEquivalent(x))) { // Reflexive check
        //     return false; // Should theoretically never trigger.
        // }
        // AugList<AugList<T>> allRelations = new AugList<AugList<T>>();
        // for (int i = 0; i < ls.size(); i++) {
        //     if (!ls.get(i).isEquivalent(ls.get(i)) || !augListB.get(i).isEquivalent(augListB.get(i))) { // Consistency check
        //         return false; // (In theory this condition should never fail.)
        //     }
        //     allRelations.add(new AugList<T>());
        //     // (Notably oAsAugList.size() == ls.size().)
        //     for (int j = 0; j < augListB.size(); j++) {
        //         if (ls.get(i).isEquivalent(augListB.get(j))) {
        //             if (!ls.get(j).isEquivalent(augListB.get(i))) { // Symmetric check
        //                 return false;
        //             }
        //             if ((ls.get(i) == null && augListB.get(j) != null) || (ls.get(i) != null && augListB.get(j) == null))
        //             {
        //                 // Non-null equivalence check
        //                 return false;
        //             }
        //             allRelations.get(i).add(augListB.get(j)); // Setting up for Transitivity check
        //         }
        //     }
        // }

        // for (int i = 0; i < allRelations.size(); i++) { // Relations of i
        //     for (int j = 0; j < allRelations.get(i).size(); j++) { // Relations of j
        //         // For all relations of i, check all relations of j relate to them.
        //         // (in other words, if i relates to j, are their relations equivalent?)
        //         if (allRelations.get(i).contains(augListB.get(j)) && !allRelations.get(i).toString().isEquivalent(allRelations.get(j).toString()))
        //         {
        //             // If i is equivalent to j but the list of equivalences of i is not the same as the list of equivalences of j,
        //             // then Transitivity is broken, so return false.
        //             return false;
        //         }
        //     }
        // }
        //
        //return true;
        //// Notably, this change means that as long as the correct elements are present, any reordering is considered valid.
        //// I.e. [1,2] = [2,1]
        //// For cases in which this is not desired behaviour, please use Equal().
    //}
    //#endregion

    //#region Niche Encapsulations

    /**
     * Increases the maximum number of elements this {@link AugList} can take to at least the given capacity.
     * Reduces number of array resizing operations needed when handling repeated, large-scale additions to this AugList.
     * Does nothing if capacity is already sufficient.
     * @deprecated  Due to:
     *              - Being impossible to write tests for
     *              - Enabling only miniscule performance gains for repeated, exceptionally-large-scale addition operations.
     * @param       minCapacity
     *              The minimum capacity in question.
     * @see         tests.AugListTest#testEnsureCapacity()
     * @since       AugList pre-alpha, deprecated V1.
     * @note        Encapsulates {@link ArrayList#ensureCapacity(int)}.
     *              Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Terminator
     */
    //@Deprecated(since = "1", forRemoval = true)
    // public void ensureCapacity(int minCapacity) {
    //     ls.ensureCapacity(minCapacity);
    // }

    /**
     * Reduces the allocated storage space to this {@link AugList} to the minimum possible.
     * Only useful when dealing with deleting swaths of data from large datasets.
     * @deprecated  Due to:
     *              - Being impossible to write tests for
     *              - Enabling only miniscule storage space gains after large-scale deletions of data.
     * @see         tests.AugListTest#testTrimToSize()
     * @since       AugList pre-alpha, deprecated V1.
     * @note        Encapsulates {@link ArrayList#trimToSize()}.
     *              Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Terminator
     */
    //@Deprecated(since = "1", forRemoval = true)
    // public void trimToSize() {
    //     ls.trimToSize();
    // }

    /**
     * Create an array copy of this AugList via use of the given generator.
     * @deprecated  Due to difficulty of use.
     *              Classes that implement or refer to IntFunctions are very niche,
     *              and examples on how to create and use them are non-existent,
     *              at least from cursory research.
     * @param       generator
     *              The {@link java.util.function.IntFunction} generator in question.
     * @return      An array copy of this AugList.
     * @since       AugList pre-alpha, deprecated V1.
     * @note        Encapsulates {@link ArrayList#toArray(java.util.function.IntFunction)}.
     *              Should be fully functional if uncommented, though even calling this method may prove difficult.
     *              Does not have a test in {@link tests.AugListTest}.
     * 
     *              <p> However, with the recent experience with IntFunctions (from creating ALFactory),
     *              This method may be un-deprecated in the future.
     * @tags        Converter
     */
    //@Deprecated(since = "1", forRemoval = true)
    // public T[] toArray(java.util.function.IntFunction<T[]> generator) {
    //     return ls.toArray(generator);
    // }
    
    // /**
    //  * Creates a new {@link AugList} by mapping the given source with the given function.
    //  * @deprecated  Due to lack of use cases. 
    //  * @param       <U>
    //  *              The type of elements from the source.
    //  * @param       source
    //  *              The source in question.
    //  * @param       func
    //  *              The {@link Function} in question. If {@code null}, maps the source's elements to {@code null}.
    //  * @since       AugList V2 dev, deprecated V2
    //  * @see         tests.AugListTest#testInstantiateFromFunc()
    //  * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Iterable, Function)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
    //  * @tags        Constructor
    //  */
    //@Deprecated(since = "2", forRemoval = true)
    // public <U> AugList(Iterable<U> source, Function<? super U, T> func) {
    //     AugList<U> ALsource = new AugList<U>(source); // Implicit null correction
    //     ls = new ArrayList<T>();
    //     if (Objects.isNull(func)) {
    //         func = e -> null;
    //     }
    //     for (int i = 0; i < ALsource.size(); i++) {
    //         ls.add(func.apply(ALsource.get(i)));
    //     }
    // }

    //#endregion

    //#region Redundant methods
    /*
     * Methods in this section have not been given full annotation / documentation;
     * In particular, Overloads (@overloads) have been omitted for clarity.
     * 
     * All methods within this section should work if uncommented.
     * All methods within this section do not have corresponding tests.
     * These methods have no null protection and do not account for the state of cmp.
     */

    /**
     * Creates a new {@link AugList} from the given {@link ArrayList}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       arrayList
     *              The {@link ArrayList} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V1, deprecated V2
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(ArrayList<T> arrayList) {
    //     this.ls = arrayList;
    // }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link List}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}. 
     * @param       list
     *              The {@link List} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V1, deprecated V2
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(List<T> list) {
    //     this.ls = new ArrayList<T>(list);
    // }

    /**
     * Creates a new {@link AugList} from the given {@link AugList}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       augList
     *              The {@link AugList} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V2 dev, deprecated V2
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(AugList<T> augList) {
    //     this.ls = new ArrayList<T>(augList.ls);
    // }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link java.util.Deque}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       deque
     *              The {@link java.util.Deque} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V2 dev, deprecated V2
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(java.util.Deque<T> deque) {
    //     this.ls = new AugList<T>(deque.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.PriorityQueue}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       prioQueue
     *              The {@link java.util.PriorityQueue} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V2 dev, deprecated V2.
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(java.util.PriorityQueue<T> prioQueue) {
    //     this.ls = new AugList<T>(prioQueue.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.HashSet}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       hashSet
     *              The {@link HashSet} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V2 dev, deprecated V2.
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(java.util.HashSet<T> hashSet) {
    //     this.ls = new AugList<T>(hashSet.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.TreeSet}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       treeSet
     *              The {@link java.util.TreeSet} in question.
     * @see         #AugList(Iterable)
     * @since       AugList V2 dev, deprecated V2.
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "2", forRemoval = true)
    // public AugList(java.util.TreeSet<T> treeSet) {
    //     this.ls = new AugList<T>(treeSet.iterator()).ls;
    // }

    //#endregion

    //#region Methods without Usecases
    /*
     * Whilst the methods here are not redundant, they currently have no real use case.
     */

    /**
     * Creates a new {@link AugList} of the given length, filled with the given value.
     * @deprecated  Due to lack of use cases, at least at current (2026-09-24).
     * @param       fill
     *              What to fill this {@link AugList} with.
     * @param       size
     *              How long the {@link AugList} should be.
     * @throws      IllegalArgumentException
     *              {@code size < 0}
     * @since       AugList pre-alpha, deprecated V1
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    //@Deprecated(since = "1", forRemoval = true)
    // public AugList(T fill, int size) {
    //     this();
    //     if (size < 0) {
    //         throw new IllegalArgumentException("size must be positive.");
    //     }
    //     for (int i = 0; i < size; i++) {
    //         this.ls.add(fill);
    //     }
    // }

    /**
     * Gets the value at the given index. If the given index is out of bounds, fills the missing entries with {@code null} and returns {@code null}.
     * @deprecated  Due to lack of use cases, at least at current (2026-09-24).
     * @param       index
     *              The index of the item to get.
     * @return      The value at that index (which will be {@code null} if {@code index >= this.size()})
     * @throws      IllegalArgumentException
     *              If {@code index < 0}
     * @since       AugList pre-alpha, deprecated V1
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Mutator, Terminator
     */
    //@Deprecated(since = "1", forRemoval = true)
    // public T getAndAppendIfEmpty(int index) {
    //     cmp = null; // Guaranteed mutation, so destroy cmp.
    //     if (index < 0) {
    //         throw new IllegalArgumentException("index was negative.");
    //     }
    //     // Add empty entries until the given index if necessary.
    //     if (index >= ls.size()) {
    //         ArrayList<T> newLs = new ArrayList<T>(index + 1);
    //         for (int i = 0; i < ls.size(); i++) {
    //             newLs.set(i, ls.get(i));
    //         }
    //         ls = newLs;
    //     }
    //     return get(index);
    // }
    //#endregion
}