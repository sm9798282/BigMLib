package src;

//import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
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
import java.util.Spliterator;
//import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
//import java.util.function.IntFunction;
import java.util.function.Predicate;
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
 * - Constructors will take any {@link Arrays Array, Varargs Array}, {@link Iterable}, {@link Iterator}, {@link ListIterator}, {@link Spliterator}, or {@link Stream}.</p>
 * - Constructor and paired value-count overloads i.e. {@code new AugList<String>(new AugList<Integer>(5, 2), new AugList<String>("a", "bc"))}.</p>
 * - Adds varargs overloads for select bulk-processing methods, such as {@link #addAll(T...) addAll(T...)}, {@link #containsAll(T...) containsAll(T...)} and {@link #removeAll(T...) removeAll(T...)}.
 *
 * <h3>Replacements</h3>
 *
 * - Replaces {@link List#add(int, Object)} with {@link #insert(int, Object)}
 * - Replaces {@link List#addAll(int, Collection)} with {@link #insertAll(int, Iterable)} / {@link #insertAll(int, T...) insertAll(int, T...)}
 * - Replaces {@link List#replaceAll(UnaryOperator)} with {@link #applyAll(UnaryOperator)}
 *
 * <h3>Additions</h3>
 *
 * Adds the following methods:<p>
 * - {@link #allIndicesOf(Object)},</p>
 * - {@link #allSatisfy(Predicate)}, {@link #anySatisfy(Predicate)},</p>
 * - {@link #applyAll(UnaryOperator)}, {@link #oneToOneMap(Function)},</p>
 * - {@link #chunk(int)}, {@link #fragment(int)},</p>
 * - {@link #containsAny(Iterable)}, {@link #containsAny(T...) containsAny(T...)}</p>
 * - {@link #countsOfElements()}, {@link #countOf(Object)},</p>
 * - {@link #distinctSelf()}, {@link #distinctCopy()},</p>
 * - {@link #filterSelf(Predicate)}, {@link #filterCopy(Predicate)}, </p>
 * - {@link #forEach(Consumer)},</p>
 * - {@link #isEquivalent(Object)}, {@link #isRearrangement()},</p>
 * - {@link #listDifference(Iterable)}, {@link #listIntersection(Iterable)}, {@link #listUnion(Iterable)},</p>
 * - {@link #pairUp(Iterable)},</p>
 * - {@link #parameterizedTypeDesc()},</p>
 * - {@link #retainAll(Collection)}, {@link #toCollection()},</p>
 * - {@link #setDifference(Iterable)}, {@link #setIntersection(Iterable)}, {@link #setUnion(Iterable)},</p>
 * - {@link #skipWhile(Predicate)}, {@link #takeWhile(Predicate)},</p>
 * - {@link #swap(int, int)}, {@link #swapRandom(int)}, {@link #swapRandom()},</p>
 * - {@link #toEnumeration()},</p>
 * - {@link #without(T)}, {@link #withoutAll(Iterable)}, {@link #withoutIndex(int)}, {@link #withoutLast()}, {@link #withoutWhere(Predicate)}, {@link #withoutRandom()}</p>
 * - {@link #getRandom()}, {@link #removeRandom()},</p>
 * - {@link #insertAtRandom(T)}, {@link #insertAllAtRandom(Iterable)}, {@link #insertAllAtRandom(T...) insertAllAtRandom(T...)},</p>
 * - {@link #sample(int, boolean)},</p>
 * - {@link #shuffleSelf()}, {@link #shuffleCopy()},</p>
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
 * - {@link #toArray(T[])}
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
 * @see     tests.AugListTest
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @version 2
 */
public class AugList<T> implements Cloneable, Iterable<T> {

    /**
     * The {@link ArrayList} that this {@link AugList} decorates.
     * @since AugList V1
     */
    private ArrayList<T> ls;

    /**
     * Creates a new, empty, non-null {@link AugList}.
     * @since       AugList V1
     * @see         tests.AugListTest#testInstantiateBlank()
     * @overloads   {@link #AugList()}, {@link #AugList(Iterable, Iterable)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(ListIterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList() {
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
        ls = new ArrayList<T>() {};
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
        ls = new ArrayList<T>() {};
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
        if (Objects.isNull(iterable)) {
            ls = new ArrayList<T>() {};
        }
        else {
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
        ls = new ArrayList<T>() {};
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
        if (Objects.isNull(values) || Objects.isNull(counts)) {
            ls = new ArrayList<T>() {};
        }
        else {
            AugList<T> ALvalues = new AugList<T>(values);
            AugList<Integer> ALcounts = new AugList<Integer>(counts);
            while (ALvalues.size() > ALcounts.size()) {
                ALcounts.add(0);
            }
            while (ALcounts.size() > ALvalues.size()) {
                ALcounts.removeLast();
            }
            ALcounts.oneToOneMap(count -> count > 0 ? count : 0);
            ls = new ArrayList<T>() {};
            for (int i = 0; i < ALvalues.size(); i++) {
                for (int j = 0; j < ALcounts.get(i); j++) {
                    ls.add(ALvalues.get(i));
                }
            }
        }
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
        ls = new ArrayList<T>() {};
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
        ls = new ArrayList<T>() {};
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
        if (Objects.isNull(elements)) {
            ls = new ArrayList<T>() {};
        }
        else {
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
        ls.addAll(ALelements.ls);
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
     * @see     #oneToOneMap(Function)
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
            for (int i = 0; i < ls.size(); i++) {   
                ls.set(i, (T)func.apply(ls.get(i)));
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
        ls.clear();
        return this;
    }

    // Notably, works differently to ArrayList<T>.clone() in that the return type is an AugList<T>.
    /**
     * Creates and returns a new {@link AugList} with identical contents but a different reference.
     * @return  A new {@link AugList} with identical contents as this one.
     * @see     tests.AugListTest#testClone()
     * @since   AugList V1
     * @note    Replaces {@link Object#clone()}: Return type {@link AugList}, as opposed to an {@link Object}.
     *          <p>Also note that:</p>
     *          <pre>this.clone() != this, this.clone().isEquivalent(this), this.clone().getClass() == this.getClass()</pre>
     * @tags    Creator
     */
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
            clone.add(e);
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
     * Makes an {@link AugList} from this one, but without duplicates.
     * <p>For a Mutator method, use {@link #distinctSelf()}.</p>
     * @return  A new {@link AugList} with only the distinct elements in this AugList.
     *          Mathematically speaking, the result is also a set.
     * @since   AugList V1
     * @see     #distinctSelf()
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
        this.ls = ret.ls;
        return ret;
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
        this.ls = filterCopy(condition).ls;
        return this;
    }

    /**
     * Supplies the given {@link Consumer} all elements of this {@link AugList}.
     * <p>For a Mutator method, use {@link #applyAll()}.</p>
     * <p>For a Creator method, use {@link #oneToOneMap()}.</p>
     * @param   action
     *          The action to perform.
     * @since   AugList V1
     * @see     #applyAll(Function)
     * @see     #oneToOneMap(Function)
     * @see     tests.AugListTest#testForEach()
     * @note    Encapsulates {@code ArrayList<T>.forEach(Consumer<? super E>)}.
     * @tags    Terminator
     */
    public void forEach(Consumer<? super T> action) {
        if (!Objects.isNull(action)) {
            ls.forEach(action);
        }
    }

    /**
     * Fragments this {@link AugList} into irregularly sized fragments.
     * Preserves order.
     * Always partitions into at least 2 lists, and no list is empty.
     * @return  This AugList fragmented into randomly sized shards.
     * @since   AugList V2
     * @see     #chunk(int)
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
     * @return  The hashcode of the decorated {@link ArrayList}.
     * @since   AugList V1
     * @see     tests.AugListTest#testHashCode()
     * @note    Encapsulates {@link List#hashCode()}.
     *          Note that objects that satisfy {@link #equals(Object)} may not have an equal hashcode, in violation of the general contract.
     * @tags    Terminator
     */
    public int hashCode() {
        return ls.hashCode();
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
        ls.addAll(index, e.ls);
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
     * - {@link Iterator}: Same values, same order
     * - {@link Iterable}: Same values, same order
     * - {@link ListIterator}: Same values, same order
     * - {@link Spliterator}: Same values, same order
     * - {@link Stream}: Same values, same order
     * <p>Separately:
     * - A {@link String} is equivalent if it matches the {@link #toString()} representation.
     * - A {@link Number} is equivalent if it matches the {@link #hashCode()}.
     * <p>If the given {@link Object} is none of the above, it cannot be equivalent.
     * @param   o
     *          The object in question.
     * @since   Method since AugList V2; Functionality since V1
     * @see     #isRearrangement(AugList)
     * @see     #equals(Object)
     * @see     tests.AugListTest#testIsEquivalent()
     * @return  {@code true} if equivalent, and {@code false} otherwise.
     * @note    Breaks the contract that states that two equal objects have equal {@link #hashCode() hashcodes},
     *          <p> and is not <i>symmetric</i> (i.e. For {@code AugList x} and {@code Object y}, {@code x.equals(y)} does not imply {@code y.equals(x)})
     * @tags    Terminator
     */
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
            //try {
            // If o is an Enumeration<? extends T>, it will cast without throwing.
            // Enumerations come from Hashtables, so are usually unordered - hence the isRearrangement leniency.
            return isRearrangement(new AugList<T>((Enumeration<T>)o));
            // Since unchecked casting does not throw (I believe...), this try-catch is unnecessary.
            // } catch (Exception e) {
            //     // If o's parameterized type is not "? extends T", it will throw upon casting.
            //     // Since the parameterized type cannot be meaningfully compared against, return false.
            //     return false;
            // }
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
            String oPType = "", thisPType = "";
                try {
                    oPType = ALo.get(0).getClass().toGenericString();
                } catch (IndexOutOfBoundsException e) {
                    // If an exception is thrown, AL = ∅.
                    try {
                        this.getLast();
                        // If an exception has not been thrown, AL = ∅ and this != ∅
                        // So return false.
                        return false; 
                    } catch (NoSuchElementException ex) {
                        // If an exception is thrown, it is because this = ∅.
                        // Whilst the parameterized type for both o and this are unknown,
                        // from a mathematical standpoint ∅ = ∅, thus return true.
                        return true;
                    }
                }
                try {
                    thisPType = this.getLast().getClass().toGenericString();
                    // If the parameterized type doesn't match, or the lists are different lengths, they cannot be equivalent.
                    if (!thisPType.equals(oPType) || size() != ALo.size()) {
                        return false;
                    }
                    for (int i = 0; i < this.size(); i++) {
                        // If any element mismatches, the lists cannot be equivalent.
                        if (!this.get(i).equals(ALo.get(i))) {
                            return false;
                        }
                    }
                    return true;
                } catch (NoSuchElementException e) {
                    // If an exception has been thrown, AL != ∅, this = ∅
                    // So return false
                    return false;
                }
            }
        return false;
        /** 
         * If o is not any of the supported types, then assume non-equivalence.
         * (Whilst it may be possible that o's Hashcode matches this Hashcode and o is not a supported type,
         *  This equivalence function does not consider that possibility.
         *  (This is mostly because testing the such would be quite difficult.)
         *  Hence, not all objects that are equal will be equivalent, and vice versa.)
         */

        // if (o instanceof AugList) {
        //     @SuppressWarnings({ "rawtypes" })
        //     // Suppress the rawtypes caution as o is an AugList.
        //     // However, as it is not possible to be certain it is an AugList<T>, so cast to AugList.
        //     AugList oAsAugList = (AugList) o;
        //     // We know o is an AugList:
        //     // Firstly, are the lists the same size?
        //     if (ls.size() != oAsAugList.size()) {
        //         return false;
        //     }
        //     // (This has been commented out as I can't figure out how to fix "class java.lang.Class cannot be cast to class java.lang.reflect.ParameterizedType")
        //     {
        //         // // If they are, do they have the same generic type?
        //         // try {
        //         //     /**
        //         //      * Credit: There's no way I would be able to do this without StackOverflow.
        //         //      * Based off the following:
        //         //      * https://stackoverflow.com/questions/1942644/get-generic-type-of-java-util-list
        //         //      */
        //         //     Class<?> testClass = TypeFinder.class;
                    
        //         //     Field oALF = testClass.getDeclaredField("genericAugList");
        //         //     ParameterizedType oALPT = (ParameterizedType) oALF.getGenericType();
        //         //     Class<?> oALClass = (Class<?>) oALPT.getActualTypeArguments()[0];
        //         //     System.out.println(oALClass.toString()); // class java.lang.String

        //         //     Field tALF = testClass.getDeclaredField("thisAugList");
        //         //     ParameterizedType tALPT = (ParameterizedType) tALF.getGenericType();
        //         //     Class<?> tALClass = (Class<?>) tALPT.getActualTypeArguments()[0];
        //         //     System.out.println(tALClass.toString()); // class java.lang.Integer

        //         //     // If the generic fields have different names, the lists are treated as unequal.
        //         //     if (!oALClass.toString().isEquivalent(tALClass.toString())) {
        //         //         return false;
        //         //     }
        //         // } catch (NoSuchFieldException e) {
        //         //     return false;
        //         // }
        //     }
            
        //     // If they are, are the sequences identical?
        //     for (int i = 0; i < ls.size(); i++) {
        //         if (!ls.get(i).equals(oAsAugList.get(i))) {
        //             return false;
        //         }
        //     }
        //     // If they are, assume equality.
        //     return true;
        // }
        // if (o instanceof List) {
        //     // Delegate the job of answering this to built-in methods.
        //     return ls.equals(o);
        // }
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
     * @return  Returns a <i>fail-fast</i> {@link Iterator} over this {@link AugList} in sequence.
     * @since   AugList V1
     * @see     #listIterator()
     * @see     #spliterator()
     * @see     tests.AugListTest#testIterator()
     * @note    Encapsulates {@link List#iterator()}.
     * @tags    Converter
     */
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
        AugList<T> ret = this.clone();
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
        // if (this.equals(augListB)) {
        //     return augListB;
        // }
        augListB = augListB.clone();
        // As listIntersection needs to alter the state of B,
        // the state of B could change outside of scope.
        // This behaviour is not intended, hence replace B with a clone of itself.
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
        AugList<T> ret = this.clone();
        augListB = augListB.clone();
        // As listUnion needs to alter the state of B,
        // the state of B could change outside of scope.
        // This behaviour is not intended, hence replace B with a clone of itself.
        for (int i = 0; i < ret.size(); i++) {
            augListB.remove(ret.get(i));
        }
        for (int i = 0; i < augListB.size(); i++) {
            ret.add(augListB.get(i));
        }
        return ret;
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
     * @since   AugList V1
     * @see     #applyAll(Function)
     * @see     #forEach(Consumer)
     * @see     tests.AugListTest#testOneToOneMap()
     * @note    Replaces {@link ArrayList#forEach(Consumer)}.
     * @tags    Creator
     */
    public <U> AugList<U> oneToOneMap(Function<? super T, U> func) {
        AugList<U> ret = new AugList<U>();
        for (T element : ls) {
            ret.add(func.apply(element));
        }
        return ret;
        // As oneToOneMap holds the contract that this.size() == this.oneToOneMap(func).size(),
        // and as there is no way to instantiate a new T or a new U,
        // This method throws when given a null.
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
     * <p>For a Mutator method, see {@link #without(Object)}.
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
     * @tags    Terminator
     */
    public boolean remove(Object o) {
        return ls.remove(o);
    }

    /**
     * Attempts to remove the first instance of each of the supplied elements, if present, from this {@link AugList}.
     * <p>For a Mutator method, see {@link #withoutAll(AugList)}.
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
     * @tags        Terminator
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
        return ret;
    }

    /**
     * Attempts to removes the first instance of each of the supplied elements, if present, from this {@link AugList}.
     * <p>For a Mutator method, see {@link #withoutAll(T...)}.
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
     * @tags        Terminator
     */
    @SafeVarargs
    public final boolean removeAll(T... elements) {
        return removeAll(new AugList<T>(elements));
    }

    // Works the same as a pop operation from a stack, except can pop any element rather than the top element.
    /**
     * Removes the element at the given index.
     * <p>For a Mutator method, see {@link #withoutIndex(int)}.
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
     * @tags    Terminator
     */
    public T removeAt(int index) {
        return ls.remove(index);
        // Cannot pass index = null without prior error/exception.
    }

    // ArrayList<T>.removeFirst() is redundant because of ArrayList<T>.remove(0) so is not implemented

    /**
     * Removes all elements in this list that satisfy the given {@code filter}.
     * <p>For a Mutator method, see {@link #withoutWhere(Predicate)}.
     * @param   filter
     *          The condition in question. If {@code null}, returns {@code false}.
     * @return  {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @since   AugList V1
     * @see     #remove(Object)
     * @see     #withoutWhere(Predicate)
     * @see     tests.AugListTest#testRemoveIf()
     * @note    Encapsulates {@link List#removeIf(Predicate)}.
     * @tags    Terminator
     */
    public boolean removeIf(Predicate<? super T> filter) {
        if (Objects.isNull(filter)) {
            return false;
        }
        return ls.removeIf(filter);
    }

    /**
     * Pops the final element in this {@link AugList}.
     * <p>For a Mutator method, see {@link #withoutLast()}.
     * @return  The final element, if it exists.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V1
     * @see     #remove(Object)
     * @see     #withoutLast()
     * @see     tests.AugListTest#testRemoveLast()
     * @note    Encapsulates {@link List#removeLast()}.
     * @tags    Terminator
     */
    public T removeLast() {
        return ls.removeLast();
    }

    /**
     * Removes a random element.
     * @return  The item that was removed.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @since   AugList V2
     * @see     #remove(Object)
     * @see     #withoutRandom()
     * @see     tests.AugListTest#testRemoveRandom()
     * @note    Randomized variant of {@link #removeAt(int)}.
     * @tags    Terminator
     */
    public T removeRandom() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot remove an element from an empty list.");
        }
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
     * @see     tests.AugListTest#testSet()
     * @note    Encapsulates {@link List#set()}.
     * @tags    Mutator
     */
    public T set(int index, T element) {
        return ls.set(index, element);
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
     * @see     #listDifference(Iterable)
     * @see     tests.AugListTest#testSetDifference()
     * @note    Inspired by the C# methods {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
     * @tags    Creator
     */
    public AugList<T> setDifference(AugList<? super T> itrB) {
        AugList<T> setB = new AugList<T>(itrB); // Implicit null check
        AugList<T> ret = this.distinctCopy();
        setB = setB.distinctCopy();
        for (int i = 0; i < setB.size(); i++) {
            if (ret.contains(setB.get(i))) {
                ret.remove(setB.get(i));
            }
        }
        return ret;
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
     * @see     #listIntersection(Iterable)
     * @see     tests.AugListTest#testSetIntersection()
     * @note    Based on the C# method {@code IEnumerable<T>.Intersect()}.
     * @tags    Creator
     */
    public AugList<T> setIntersection(Iterable<? super T> itrB) {
        AugList<T> setB = new AugList<T>(itrB); // Implicit null check
        if (this.isEquivalent(setB)) {
            return setB;
        }
        setB = setB.distinctCopy();
        // As setIntersection needs to alter the state of B,
        // the state of B could change outside of scope.
        // This behaviour is not intended, hence replace B with a clone of itself.
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
     * @see     #listUnion(Iterable)
     * @see     tests.AugListTest#testSetUnion()
     * @note    Based on the C# function {@code IEnumerable<T>.Union()}.
     * @tags    Creator
     */
    public AugList<T> setUnion(Iterable<? super T> itrB) {
        AugList<T> setB = new AugList<T>(itrB); // Implicit null check
        AugList<T> ret = new AugList<T>();
        ret.addAll(this.distinctCopy());
        ret.addAll(setB.distinctCopy().filterSelf(e -> !ret.contains(e)));
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
        while (validTarget.size() != 0) {
            Integer selectedIndex = r.nextInt(validTarget.size());
            ret.set(selectedIndex, ls.get(selectedIndex));
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
        this.ls = shuffleCopy().ls;
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
     * @see     tests.AugListTest#testSort()
     * @note    Replaces {@link List#sort()}, returns {@code this} rather than {@code void}.
     * @tags    Mutator
     */
    public AugList<T> sort(Comparator<? super T> comparator) {
        if (!Objects.isNull(comparator)) {
            ls.sort(comparator);
        }
        return this;
    }

    /**
     * @return  A <i>late-binding, fail-fast</i> {@link Spliterator} over this {@link AugList} in proper sequence.
     * @since   AugList V1
     * @see     #iterator()
     * @see     #listIterator()
     * @see     tests.AugListTest#testSpliterator()
     * @note    Encapsulates {@link List#spliterator()}.
     * @tags    Converter
     */
    public Spliterator<T> spliterator() {
        return ls.spliterator();
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
        swap(index, index2);
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
        swap(index1, index2);
        return this;
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
        ls.remove(o);
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
        removeAll(elements);
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
        removeAll(elements);
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
        ls.remove(index);
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
        ls.removeLast();
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
        removeRandom();
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
        removeIf(filter);
        return this;
    }

    // /**
    //  * Create a new AugList that is the given length, filled with the given value.
    //  * @deprecated
    //  * @param       fill
    //  *              What to fill this AugList with.
    //  * @param       size
    //  *              How long the AugList should be.
    //  * @throws      IllegalArgumentException
    //  *              {@code size < 0}
    //  * @tags        Constructor
    //  */
    // public AugList(T fill, int size) {
    //     if (size < 0) {
    //         throw new IllegalArgumentException("size must be positive.");
    //     }
    //     for (int i = 0; i < size; i++) {
    //         this.ls.add(fill);
    //     }
    // }

    // /**
    //  * Increases the maximum number of elements this AugList can take to {@code minCapacity}.
    //  * Does nothing if capacity is already sufficient.
    //  * 
    //  * @deprecated  (Other methods already perform the same task.)
    //  * @param       minCapacity
    //  *              The minimum capacity this AugList is desired to have.
    //  * @note        Encapsulates {@code ArrayList<T>.ensureCapacity()}.
    //  * @tags        Terminator
    //  */
    // public void ensureCapacity(int minCapacity) {
    //     ls.ensureCapacity(minCapacity);
    // }

    // Was a part of an attempted change to .isEquivalent() that went nowhere.
    // /**
    //  * Helper class that is used in isEquivalent(), consisting of 2 fields and a single constructor.
    //  * @deprecated
    //  */
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

    // /**
    //  * Gets the value at the given index. If the given index is out of bounds, creates entries up to that index and returns the default value.
    //  * @deprecated  
    //  * @param       index
    //  *              The index of the item to get.
    //  * @return      The value at that index (which will be the default value if {@code index >= this.size()})
    //  * @throws      IllegalArgumentException
    //  *              If {@code index < 0}
    //  * @tags        Terminator
    //  */
    // public T getAndAppendIfEmpty(int index) {
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

    // Method currently unnecessary, so has been commented.
    /**
     * Finds if the two {@link AugList AugLists} are in an Equivalence Relationship.
     * @deprecated  
     * @param       augListB
     *              The second {@link AugList}.
     * @return      {@code true} if the lists are in a EqRel, and {@code false} otherwise.
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
     * @note        Encapsulates {@link ArrayList#ensureCapacity(int)}.
     *              Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Terminator
     */
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
     * @note        Encapsulates {@link ArrayList#trimToSize()}.
     *              Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Terminator
     */
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
     * @note        Encapsulates {@link ArrayList#toArray(java.util.function.IntFunction)}.
     *              Should be fully functional if uncommented, though even calling this method may prove difficult.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Converter
     */
    // public T[] toArray(java.util.function.IntFunction<T[]> generator) {
    //     return ls.toArray(generator);
    // }

    //#endregion

    //#region Redundant methods
    /*
     * Methods in this section have not been given full annotation / documentation;
     * In particular, Overloads (@overloads) have been omitted for clarity.
     * 
     * All methods within this section should work if uncommented.
     * All methods within this section do not have corresponding tests.
     */

    /**
     * Creates a new {@link AugList} from the given {@link ArrayList}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       arrayList
     *              The {@link ArrayList} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(ArrayList<T> arrayList) {
    //     this.ls = arrayList;
    // }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link List}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}. 
     * @param       list
     *              The {@link List} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(List<T> list) {
    //     this.ls = new ArrayList<T>(list);
    // }

    /**
     * Creates a new {@link AugList} from the given {@link AugList}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       augList
     *              The {@link AugList} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(AugList<T> augList) {
    //     this.ls = new ArrayList<T>(augList.ls);
    // }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link java.util.Deque}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       deque
     *              The {@link java.util.Deque} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(java.util.Deque<T> deque) {
    //     this.ls = new AugList<T>(deque.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.PriorityQueue}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       prioQueue
     *              The {@link java.util.PriorityQueue} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(java.util.PriorityQueue<T> prioQueue) {
    //     this.ls = new AugList<T>(prioQueue.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.HashSet}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       hashSet
     *              The {@link HashSet} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(java.util.HashSet<T> hashSet) {
    //     this.ls = new AugList<T>(hashSet.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.TreeSet}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @param       treeSet
     *              The {@link java.util.TreeSet} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
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
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Constructor
     */
    // public AugList(T fill, int size) {
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
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @tags        Mutator, Terminator
     */
    // public T getAndAppendIfEmpty(int index) {
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