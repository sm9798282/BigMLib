package src;

//import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
//import java.util.Deque;
import java.util.Enumeration;
//import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
//import java.util.PriorityQueue;
import java.util.Random;
import java.util.Spliterator;
//import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
//import java.util.function.IntFunction;
import java.util.function.Predicate;
//import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;

/**
 * <h1>AugList<T></h1>
 *
 * A decorator of an ArrayList which provides implementations of many commonly used procedures and commonly required tasks.
 * <p>Draws from a variety of sources, such as C# methods, statistics, set theory and more.
 *
 * <h2>Notable Changes</h2>
 *
 * <h3>Return type changes</h3>
 *
 * - {@code Boolean} {@link List#add(T)}, {@code Object} {@link Object#clone()} and {@code List<T>} {@link List#subList(int, int)}
 * changed to have a return type of {@code AugList<T>}.
 *
 * <h3>Overrides</h3>
 *
 * - Overrides {@link #equals equals()} to more leniently match AugLists, Lists and Strings. Notably this means {@code this.equals(this.clone()) == true}.</p>
 * - Overrides {@link #toString toString()} to provide a more legible overview of the contents.
 *
 * <h3>Overloads</h3>
 *
 * - Constructors will take any {@code ? implements} {@link Iterable}, {@link Spliterator}, or {@link Stream}.</p>
 * - Varargs constructor i.e. {@code new AugList<Integer>(1, 2, 3, 4)}.</p>
 * - Constructor and paired value-count overloads i.e. {@code new AugList<String>(new AugList<Integer>(5, 2), new AugList<String>("a", "bc"))}.</p>
 * - Adds varargs overloads for select bulk-processing methods, such as {@link #addAll(T...)}, {@link #containsAll(T...)} and {@link #removeAll(Object...)}.
 *
 * <h3>Replacements</h3>
 *
 * - Replaced {@link List#replaceAll()} with {@link AugList#oneToOneMap(Function)}
 *
 * <h3>Additions</h3>
 *
 * Adds the following methods:
 * - {@link #allSatisfy(Predicate)}, {@link #anySatisfy(Predicate)},</p>
 * - {@link #applyAll(Function)}, {@link #oneToOneMap(Function)},</p>
 * - {@link #chunk(int)}, {@link #fragment(int)},</p>
 * - {@link #containsAny(Predicate)},</p>
 * - {@link #countsOfElements()},</p>
 * - {@link #distinctSelf()}, {@link #distinctCopy()},</p>
 * - {@link #filterSelf(Predicate)}, {@link #filterCopy(Predicate)}, </p>
 * - {@link #forEach(Consumer)},</p>
 * - {@link #isRearrangement()},</p>
 * - {@link #listDifference(AugList)}, {@link #listIntersection(AugList)}, {@link #listUnion(AugList)},</p>
 * - {@link #pairUp(AugList)},</p>
 * - {@code #retainAll(java.util.Collection)}, {@code #toCollection()},</p>
 * - {@link #setDifference(AugList)}, {@link #setIntersection(AugList)}, {@link #setUnion(AugList)},</p>
 * - {@link #skipWhile(Predicate)}, {@link #takeWhile(Predicate)},</p>
 * - {@link #swap(int, int)}, {@link swapRandom(int)}, {@link #swapRandom()},</p>
 * - {@link #without(T)}, {@link #withoutAll(AugList)}, {@link #withoutIndex(int)}, {@link #withoutLast()}, {@link #withoutWhere(Predicate)}, {@link #withoutRandom()}</p>
 * - {@link #getRandom()}, {@link #removeRandom()},</p>
 * - {@link #insertAtRandom(T)}, {@link #insertAllAtRandom(AugList)}, {@link #insertAllAtRandom(T...)},</p>
 * - {@link #sample(int, boolean)},</p>
 * - {@link #shuffleSelf()}, {@link #shuffleCopy()},</p>
 *
 * <h3>Deprecated</h3>
 *
 * Due to these methods falling under one of the following categories, they have been commented out / left unimplemented:</p>
 * 1: have identical functionality under another name:</p>
 * - {@link List#replaceAll()},</p>
 * 2: are / have been made redundant by other methods:</p>
 * - {@link List#addLast()}, {@link List#removeFirst()}, {@link #subListToEnd()},</p>
 * 3: are impractical to use: </p>
 * - {@link List#toArray()}, {@code List.toArray(IntFunction<T[]>)},</p>
 * 4: have no meaningful impact on internal state:</p>
 * - {@link ArrayList#ensureCapacity()}, {@link ArrayList#trimToSize()}</p>
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @version 2
 */
public class AugList<T> implements Iterable<T> {
    /**
     * The {@link ArrayList} that this {@link AugList} decorates.
     */
    private ArrayList<T> ls;

    /**
     * Creates a new, empty, non-null {@link AugList}.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList() {
        this.ls = new ArrayList<T>() {};
    }

    
    /**
     * Creates a new {@link AugList} from the given {@link T}[].
     * @param       elements
     *              The varargs array of objects that will make up this {@link AugList}.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    @SafeVarargs
    public AugList(T... elements) {
        this.ls = new ArrayList<T>(Arrays.asList(elements));
    }

    /**
     * Creates a new {@link AugList} from the given {@link Enumeration}.
     * @param       enumeration
     *              The {@link Enumeration} object to source the elements for this {@link AugList} from.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Enumeration<T> enumeration) {
        this.ls = new ArrayList<T>() {};
        while (enumeration.hasMoreElements()) {
            this.ls.add(enumeration.nextElement());
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@link Iterator} over some sequence.
     * @param       iterator
     *              The {@link Iterator} object to source the elements for this {@link AugList} from.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)} 
     * @tags        Constructor
     */
    public AugList(Iterator<T> iterator) {
        this.ls = new ArrayList<T>() {};
        while (iterator.hasNext()) {
            this.ls.add(iterator.next());
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link Iterable}.
     * @param       iterable
     *              The {@link Iterable} object to source the elements for this {@link AugList} from.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Iterable<T> iterable) {
        this.ls = new AugList<T>(iterable.iterator()).ls;
    }

    /**
     * Creates a new {@link AugList} from the given value-count pairs.
     * @param       values
     *              The values to use.
     *              If there are more values than counts, ignores values without counts.
     * @param       counts
     *              How many times each value should be repeated.
     *              If there are more counts than values, ignores the counts without associated values. Negative counts are treated as 0.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(AugList<T> values, AugList<Integer> counts) {
        while (values.size() > counts.size()) {
            counts.add(0);
        }
        while (counts.size() > values.size()) {
            counts.removeLast();
        }
        counts.oneToOneMap(count -> count > 0 ? count : 0);
        ls = new ArrayList<T>() {};
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < counts.get(i); j++) {
                ls.add(values.get(i));
            }
        }
    }

    /**
     * Creates a new {@link AugList} from the given {@link Spliterator}.
     * @param       spliterator
     *              The {@link Spliterator} object to source the elements for this {@link AugList} from.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Spliterator<T> spliterator) {
        this.ls = new ArrayList<T>() {};
        spliterator.forEachRemaining(e -> this.ls.add(e));
    }

    /**
     * Creates a new {@link AugList} from the given {@link Stream}.
     * @param       stream
     *              The {@link Stream} object to source the elements for this {@link AugList} from.
     * @overloads   {@link #AugList()}, {@link #AugList(AugList, AugList)}, {@link #AugList(Enumeration)}, {@link #AugList(Iterable)}, {@link #AugList(Iterator)}, {@link #AugList(T...)}, {@link #AugList(Spliterator)}, {@link #AugList(Stream)}
     * @tags        Constructor
     */
    public AugList(Stream<T> stream) {
        // if (stream.anyMatch(e -> e == null)) {
        //     throw new IllegalStateException("The stream must not contain nulls.")
        // }
        this.ls = new AugList<T>(stream.iterator()).ls;
    }

    /**
     * Appends the given element to the end of this {@link AugList}.
     *
     * @param   element
     *          The element in question.
     * @return  This {@link AugList}, with the given element appended to it.
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
     *              The elements in question.
     * @return      This {@link AugList}, with the given elements appended to it.
     * @note        Replaces {@link ArrayList#addAll()}: Returns {@code this}, not {@code void}.
     * @overloads   {@link #addAll(AugList)}, {@link #addAll(T...)}
     * @tags        Mutator
     */
    public AugList<T> addAll(AugList<T> elements) {
        ls.addAll(elements.ls);
        return this;
    }

    /**
     * Appends all given elements to this {@link AugList}.
     * @param       elements
     *              The elements in question.
     * @return      This {@link AugList}, with the given elements appended to it.
     * @note        Varargs variant for {@link ArrayList#addAll()}.
     * @overloads   {@link #addAll(AugList)}, {@link #addAll(T...)}
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> addAll(T... elements) {
        ls.addAll(new AugList<T>(elements).ls);
        return this;
    }

    /**
     * Prepends the given element to this {@link AugList}.
     * @param   element
     *          The element in question.
     * @return  This {@link AugList}, with the given element prepended to it.
     * @note    Replaces {@link ArrayList#addFirst(T)}: Returns {@code this}, not {@code void}.
     * @tags    Mutator
     */
    public AugList<T> addFirst(T element) {
        ls.addFirst(element);
        return this;
    }

    // ArrayList<T>.addLast(T) is not implemented as ArrayList<T>.insert(0, T) performs the same task

    /**
     * Finds all indices at which the given element can be found.
     * @param   element
     *          The element in question.
     * @return  A new {@link AugList} with the indices at which it can be found.
     * @note    Based upon the C# method {@code List<T>.FindAll(Predicate<T>)} with a Predicate that returns true when an element is equal to the target.
     * @see     #indexOf(Object)
     * @see     #lastIndexOf(Object)
     * @tags    Creator
     */
    public AugList<Integer> allIndicesOf(T element) {
        AugList<Integer> ret = new AugList<Integer>();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).equals(element)) {
                ret.add(i);
            }
        }
        return ret;
    }

    /**
     * Sees if all elements pass the given condition.
     * @param   condition
     *          The condition in question.
     * @return  {@code true} if all elements satisfy the condition, and {@code false} otherwise.
     * @see     #anySatisfy(Predicate)
     * @note    Based upon the C# methods {@code IEnumerable<T>.All()} and {@code List<T>.TrueForAll()}.
     * @tags    Terminator
     */
    public boolean allSatisfy(Predicate<? super T> condition) {
        for (T e : ls) {
            if (!condition.test(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sees if any element passes the given condition.
     * @param   condition
     *          The condition in question.
     * @return  {@code true} if any element satisfies the condition, and {@code false} otherwise.
     * @see     #allSatisfy(Predicate)
     * @note    Inspired by {@link #allSatisfy(Predicate)}.
     * @tags    Terminator
     */
    public boolean anySatisfy(Predicate<? super T> condition) {
        for (T e : ls) {
            if (condition.test(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Applies the given {@link Function} to all elements of this {@link AugList}
     * <p>For a Terminator method, use {@link #forEach()}.</p>
     * <p>For a Creator method, use {@link #oneToOneMap()}.</p>
     * @param   func
     *          The {@link Function} in question.
     * @return  This {@link AugList}, with each element transformed as according to the function.
     * @see     #oneToOneMap(Function)
     * @see     #forEach(Consumer)
     * @note    Functionality is, to my knowledge, not implemented in Java or C#.
     *          Inspired by the C# function {@code List<T>.ConvertAll(Converter<T, TOutput>)}.
     * @tags    Mutator
     */
    public AugList<T> applyAll(Function<? super T, T> func) {
        for (int i = 0; i < ls.size(); i++) {
            ls.set(i, func.apply(ls.get(i)));
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
     * @see     #fragment()
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
    }

    /**
     * Clears this {@link AugList}.
     * @return  This {@link AugList}, emptied.
     * @note    Replaces {@link List#clear()}: Returns {@code this}, not {@code void}.
     * @tags    Mutator
     */
    public AugList<T> clear()
    {
        ls.clear();
        return this;
    }

    // Notably, works differently to ArrayList<T>.clone() in that the return type is an AugList<T>.
    /**
     * @return  A new {@code AugList} with identical contents as this one.
     * @note    Replaces {@link Object#clone()}: Return type {@link AugList} (as opposed to {@link Object})
     * @tags    Creator
     */
    public AugList<T> clone() {
        AugList<T> clone = new AugList<T>() {};
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
     * @note    Encapsulates {@link List#contains(T)}.
     * @tags    Terminator
     */
    public boolean contains(T element) {
        return ls.contains(element);
    }
    
    /**
     * Sees if this {@link AugList} contains all the given elements.
     * @param       elements
     *              The elements in question.
     * @return      {@code true} if all elements are found, and {@code false} otherwise.
     * @see         #containsAny(AugList)
     * @see         #containsAny(T...)
     * @note        Replaces {@link java.util.Collection#containsAll(java.util.Collection)}
     * @overloads   {@link #containsAll(AugList)}, {@link #containsAll(T...)}
     * @tags        Terminator
     */
    public boolean containsAll(AugList<T> elements) {
        for (T e : elements) {
            if (!ls.contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sees if this {@link AugList} contains all the given elements.
     * @param       elements
     *              The elements in question.
     * @return      {@code true} if all elements are found, and {@code false} otherwise.
     * @see         #containsAny(AugList)
     * @see         #containsAny(T...)
     * @note        Varargs overload for {@link #containsAll(AugList)}.
     *              <p>Functionality can be replicated with {@code this.allSatisfy(e -> this.contains(e))}.</p>
     * @overloads   {@link #containsAll(AugList)}, {@link #containsAll(T...)}.
     * @tags        Terminator
     */
    @SafeVarargs
    public final boolean containsAll(T... elements) {
        for (T e : elements) {
            if (!ls.contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sees if this {@link AugList} contains any of the given elements.
     * @param       elements
     *              The elements in question.
     * @return      {@code true} if any element is found, and {@code false} if not.
     * @see         #containsAll(AugList)
     * @see         #containsAll(T...)
     * @note        Inspired by {@link #containsAll(AugList)}.
     *              <p>Functionality can be replicated with {@code this.anySatisfy(e -> elements.contains(e))}.</p>
     * @overloads   {@link #containsAll(AugList)}, {@link #containsAll(T...)}.
     * @tags        Terminator
     */
    public final boolean containsAny(AugList<T> elements) {
        for (T e : elements) {
            if (ls.contains(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sees if this {@link AugList} contains any of the given elements.
     * @param       elements
     *              The elements in question.
     * @return      {@code true} if any element is found, and {@code false} if not.
     * @see         #containsAll(AugList)
     * @see         #containsAll(T...)
     * @note        Varargs overload of {@link #containsAny(AugList)}
     *              <p>Functionality can be replicated with {@code this.anySatisfy(e -> elements.contains(e))}.</p>
     * @overloads   {@link #containsAll(AugList)}, {@link #containsAll(T...)}.
     * @tags        Terminator
     */
    @SafeVarargs
    public final boolean containsAny(T... elements) {
        for (T e : elements) {
            if (ls.contains(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of times each element appears and returns a Hashtable with the results.
     * @return  A {@link Hashtable} that pairs each element with its frequency.
     * @tags    Converter
     */
    public Hashtable<T, Integer> countsOfElements() {
        Hashtable<T,Integer> ret = new Hashtable<T, Integer>() {};
        for (int i = 0; i < ls.size(); i++) {
            T key = ls.get(i);
            if (ret.containsKey(key)) {
                ret.put(key, ret.get(key) + 1);
            }
            else {
                ret.put(key, 1);
            }
        }
        return ret;
    }

    /**
     * Makes an {@link AugList} from this one, but without duplicates.
     * <p>For a Mutator method, use {@link #distinctSelf()}.</p>
     * @return  A new {@link AugList} with only the distinct elements in this AugList.
     *          Mathematically speaking, the result is also a set.
     * @see     #distinctSelf()
     * @note    Based off the C# function {@code IEnumerable<T>.Distinct()};
     * @tags    Creator
     */
    public AugList<T> distinctCopy() {
        AugList<T> ret = new AugList<T>() {};
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
     * @see     #distinctCopy()
     * @note    Inspired by {@link #distinctCopy()}.
     * @tags    Mutator
     */
    public AugList<T> distinctSelf() {
        AugList<T> ret = new AugList<T>() {};
        for (T e : ls) {
            if (!ret.contains(e)) {
                ret.add(e);
            }
        }
        this.ls = ret.ls;
        return ret;
    }

    // (Overrides ArrayList<T>.equals()).
    /**
     * Sees if the given {@link Object} may be equal to this {@link AugList}.
     * <p> If the Object in question is any of the following, it can be matched:
     * - {@link AugList}: Same length and Elements are equal for each index.
     * - {@code ? implements} {@link List}: Passes {@link List#equals(Object)}.
     * - {@link String}: Equal to the result of {@link AugList#toString()}.
     * @param   o
     *          The object in question.
     * @see     #isRearrangement(AugList)
     * @return  {@code true} if equivalent, and {@code false} otherwise.
     * @note    Breaks the contract that states that two equal objects have equal {@link #hashCode() hashcodes} 
     * @tags    Terminator
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AugList) {
            @SuppressWarnings({ "rawtypes" })
            // Suppress the rawtypes caution as o is an AugList.
            // However, as it is not possible to be certain it is an AugList<T>, so cast to AugList.
            AugList oAsAugList = (AugList) o;
            // We know o is an AugList:
            // Firstly, are the lists the same size?
            if (ls.size() != oAsAugList.size()) {
                return false;
            }
            // (This has been commented out as I can't figure out how to fix "class java.lang.Class cannot be cast to class java.lang.reflect.ParameterizedType")
            {
                // // If they are, do they have the same generic type?
                // try {
                //     /**
                //      * Credit: There's no way I would be able to do this without StackOverflow.
                //      * Based off the following:
                //      * https://stackoverflow.com/questions/1942644/get-generic-type-of-java-util-list
                //      */
                //     Class<?> testClass = TypeFinder.class;
                    
                //     Field oALF = testClass.getDeclaredField("genericAugList");
                //     ParameterizedType oALPT = (ParameterizedType) oALF.getGenericType();
                //     Class<?> oALClass = (Class<?>) oALPT.getActualTypeArguments()[0];
                //     System.out.println(oALClass.toString()); // class java.lang.String

                //     Field tALF = testClass.getDeclaredField("thisAugList");
                //     ParameterizedType tALPT = (ParameterizedType) tALF.getGenericType();
                //     Class<?> tALClass = (Class<?>) tALPT.getActualTypeArguments()[0];
                //     System.out.println(tALClass.toString()); // class java.lang.Integer

                //     // If the generic fields have different names, the lists are treated as unequal.
                //     if (!oALClass.toString().equals(tALClass.toString())) {
                //         return false;
                //     }
                // } catch (NoSuchFieldException e) {
                //     return false;
                // }
            }
            
            // If they are, are the sequences identical?
            for (int i = 0; i < ls.size(); i++) {
                if (!ls.get(i).equals(oAsAugList.get(i))) {
                    return false;
                }
            }
            // If they are, assume equality.
            return true;
        }
        if (o instanceof List) {
            // Delegate the job of answering this to built-in methods.
            return ls.equals(o);
        }
        if (o instanceof String) {
            // o is a String; If it is the same as this.toString(), it will be treated as equal.
            return o.equals(this.toString());
        }
        // If it is not any of the supported types, then we assume non-equivalence.
        return false;
    }

    /**
     * Creates a new {@link AugList} with exactly the elements that satisfy the given filter.
     * <p>For a Mutator method, use {@link #filterSelf()}.
     * @param   condition
     *          The condition in question.
     * @return  A new {@link AugList} with elements that satisfy the given filter.
     * @see     #filterSelf()
     * @note    Based off the C# function {@code IEnumerable<T>.Where()}
     * @tags    Creator
     */
    public AugList<T> filterCopy(Predicate<? super T> condition) {
        AugList<T> ret = new AugList<T>() {};
        for (T e : ls) {
            if (condition.test(e)) {
                ret.add(e);
            }
        }
        return ret;
    }

    /**
     * Changes this {@link AugList} to contain exactly the elements that satisfy the given filter.
     * <p>For a Creator method, use {@link #filterCopy()}.
     * @param   condition
     *          The condition in question.
     * @return  This {@link AugList} with elements that satisfy the given filter.
     * @see     #filterCopy()
     * @note    Based off the C# function {@code IEnumerable<T>.Where()}
     * @tags    Mutator
     */
    public AugList<T> filterSelf(Predicate<? super T> condition) {
        AugList<T> ret = new AugList<T>() {};
        for (T e : ls) {
            if (condition.test(e)) {
                ret.add(e);
            }
        }
        this.ls = ret.ls;
        return this;
    }

    /**
     * Supplies the given {@link Consumer} all elements of this {@link AugList}.
     * <p>For a Mutator method, use {@link #applyAll()}.</p>
     * <p>For a Creator method, use {@link #oneToOneMap()}.</p>
     * @param   action
     *          The action to perform.
     * @throws  NullPointerException
     *          If the specified action is {@code null}.
     * @see     #applyAll(Function)
     * @see     #oneToOneMap(Function)
     * @note    Encapsulates {@code ArrayList<T>.forEach(Consumer<? super E>)}.
     * @tags    Terminator
     */
    public void forEach(Consumer<? super T> action) {
        ls.forEach(action);
    }

    /**
     * Fragments this {@link AugList} into irregularly sized fragments.
     * Preserves order.
     * Always partitions into at least 2 lists, and no list is empty.
     * @return  This AugList fragmented into randomly sized shards.
     * @see     #chunk(int)
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
     * @see     #getLast()
     * @see     #getRandom()
     * @see     #set(int, T)
     * @note    Encapsulates {@link List#get(int)}
     * @tags    Terminator
     */
    public T get(int index) {
        return ls.get(index);
    }

    // Object.getClass() cannot be overridden and as such is not implemented
    // ArrayList<T>.getFirst() is not implemented as it is made redundant by get(0)
    
    // ArrayList<T>.getLast() has just enough of a fringe usage that it is implemented; Typing ArrayList<T>.get(ArrayList<T>.size() - 1) is a little arduous.
    /**
     * Gets the final element of this {@link AugList}.
     * @return  The last item in this {@link AugList}.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @see     #get(int)
     * @note    Encapsulates {@link List#getLast()}.
     * @tags    Terminator
     */
    public T getLast() {
        return ls.getLast();
    }

    /**
     * @return  A random element of this {@link AugList}.
     * @see     #get(int)
     * @note    Randomized variant of {@link #get()}.
     * @tags    Terminator
     */
    public T getRandom() {
        return ls.get((new Random()).nextInt(ls.size()));
    }

    /**
     * @return  The hashcode of the decorated {@link ArrayList}.
     * @note    Encapsulates {@link List#hashCode()}.
     * Note that objects that satisfy {@link #equals(Object)} may not have an equal hashcode, in violation of the general contract.
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
     * @note    Encapsulates {@link ArrayList#indexOf()}.
     * @see     #allIndicesOf(Object)
     * @see     #lastIndexOf(Object)
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
     * @see     #insertAll(int, AugList)
     * @see     #insertAll(int, Object...)
     * @see     #insertAtRandom(Object)
     * @note    Replaces {@link ArrayList#add(int, T)}: Returns {@code this}, not {@code true}.
     * @tags    Mutator
     */
    public AugList<T> insert(int index, T element) {
        ls.add(index, element);
        return this;
    }

    /**
     * In VSCode, links are usually coloured (Teal Class)(Green Hashtag)(Yellow Method)(Light Blue Params)
     * If the parameters are not set up correctly, though, the method also turns Light Blue.
     * (For example, {@link ArrayList#addAll(int, Collection)} would produce a light blue method.)
     * Light blue method links cannot be ctrl+clicked on hover.
     * However, hovering over the method does produce a useable link.
     */
    /**
     * Inserts the given elements at the given index.
     * @param       index
     *              The index in question.
     * @param       elements
     *              The {@link AugList} of elements in question.
     * @throws      IndexOutOfBoundsException
     *              {@code index < 0 || index >= this.size()}
     * @return      This {@link AugList}, with the given elements inserted at the given index.
     * @see         #insert(int, Object)
     * @see         #insertAllAtRandom(AugList)
     * @see         #insertAllAtRandom(T...) 
     * @note        Replaces {@link ArrayList#addAll(int, java.util.Collection)}:
     *              Elements parameter type changed to AugList, returns {@code this}, not {@code true}.
     * @overloads   {@link #insertAll(int, T...)}
     * @tags        Mutator
     */
    public AugList<T> insertAll(int index, AugList<T> elements) {
        ls.addAll(index, elements.ls);
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
     * @see         #insert(int, Object)
     * @see         #insertAllAtRandom(AugList)
     * @see         #insertAllAtRandom(T...)
     * @note        Varargs overload of {@link #insertAll(int, AugList)}
     * @overloads   {@link #insertAll(int, AugList)}
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> insertAll(int index, T... elements) {
        ls.addAll(index, new AugList<T>(elements).ls);
        return this;
    }

    /**
     * Inserts the given elements to this {@link AugList} at random.
     * @param       elements
     *              The elements in question.
     * @return      This {@link AugList}, with the given elements inserted at random.
     * @see         #insertAll(int, AugList)
     * @see         #insertAll(T...)
     * @see         #insertAtRandom(Object)
     * @note        Randomized bulk non-varargs variant of {@link #insert(int, Object)}
     * @overloads   {@link #insertAllAtRandom(T...)}
     * @tags        Mutator
     */
    public AugList<T> insertAllAtRandom(AugList<T> elements) {
        for (int i = 0; i < elements.size(); i++) {
            insertAtRandom(elements.get(i));
        }
        return this;
    }

    /**
     * Inserts the given elements to this {@link AugList} at random.
     * @param       elements
     *              The elements in question.
     * @return      This {@link AugList}, with the given elements inserted at random.
     * @see         #insertAll(int, AugList)
     * @see         #insertAll(T...)
     * @see         #insertAtRandom(Object)
     * @note        Randomized bulk varargs variant of {@link #insert(int, Object)}
     * @overloads   {@link #insertAllAtRandom(AugList)}
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> insertAllAtRandom(T... elements) {
        for (int i = 0; i < elements.length; i++) {
            insertAtRandom(elements[i]);
        }
        return this;
    }

    /**
     * Inserts the given element at random.
     * @param   element
     *          The element in question.
     * @return  This list, with the given element inserted somewhere into this AugList.
     * @see     #insert(int, Object)
     * @see     #insertAllAtRandom(AugList)
     * @see     #insertAllAtRandom(T...)
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
     * @note    Encapsulates {@link List#isEmpty()}.
     * @see     #size()
     * @tags    Terminator
     */
    public boolean isEmpty() {
        return ls.isEmpty();
    }

    /**
     * Compares whether or not two {@link AugList AugLists} have the same elements. (Order does not matter)
     * <p>For a stricter equality function, use {@link #equals()}.</p>
     * @param   augListB
     *          The second {@link AugList} to compare against.
     * @return  {@code true} if the lists are rearrangements; {@code false} otherwise.
     * @note    Functionality is, to my knowledge, not implemented in Java or C#.
     * @tags    Terminator
     */
    public boolean isRearrangement(AugList<T> augListB) {
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
     * @see     #listIterator()
     * @see     #spliterator()
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
     * @see     #allIndicesOf(Object)
     * @see     #indexOf(Object)
     * @note    Encapsulates {@link ArrayList#lastIndexOf()}.
     * @tags    Terminator
     */
    public int lastIndexOf(Object o) {
        return ls.lastIndexOf(o);
    }

    /**
     * Returns the "list difference" between this (A) and the given {@link AugList} (B).
     * <p>For a method that calculates the set difference, use {@link #setDifference(AugList)}.
     * <p>For methods with similar functionality, see {@link #removeAll(AugList)} and {@code #retainAll(java.util.Collection)} 
     * @param   augListB
     *          The {@link AugList} in question, with which to take the difference of.
     * @return  The "list difference", "A\`B".
     *          <p>i.e. [1,2]\`[1] = [2], 
     *                  [2]\`[1,2] = [], 
     *                  [1,1,2]\`[1,3] = [1,2],
     *                  [1,1,2]\`[1,1,3] = [2],
     *                  [1,1]\`[] = [1,1]
     *          <p>Output is a list, which may be a mathematical set.
     * @see     #listIntersection(AugList)
     * @see     #listUnion(AugList)
     * @see     #setDifference(AugList)
     * @see     #removeAll(AugList)
     * @see     java.util.Collection#retainAll(java.util.Collection)
     * @note    Inspired by the C# methods {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
     *          <p>"List Difference" and "A\`B" are not mathematically endorsed terminology.
     *          Works similarly, but not identically, to {@link java.util.Collection#retainAll(java.util.Collection)}
     * @tags    Creator
     */
    public AugList<T> listDifference(AugList<T> augListB) {
        AugList<T> ret = this.clone();
        for (int i = 0; i < augListB.size(); i++) {
            if (ret.contains(augListB.get(i))) {
                ret.remove(augListB.get(i));
            }
        }
        return ret;
    }

    /**
     * Returns the "list intersection" between this (A) and the given {@link AugList} (B).
     * <p>For a method that calculates the set intersection, use {@link #setIntersection(AugList)}.
     * @param   augListB
     *          The {@link AugList} in question, with which to take the intersection of.
     * @return  The "list intersection", "A∩`B".
     *          <p>i.e. [1,1,2]∩`[1,2,3] = [1,2],
     *                  [1,2,3]∩`[1,1,2] = [1,2],
     *                  [1,1,1,2]∩`[1,1,2,3] = [1,1,2],
     *                  []∩`[1,1,3,7] = [].
     *          <p>Output is a list, which may be a mathematical set.
     * @see     #listDifference(AugList)
     * @see     #listUnion(AugList)
     * @see     #setIntersection(AugList)
     * @note    Based upon the C# method {@code IEnumerable<T>.Intersect()}.
     *          <p>"List Intersection" and "A∩`B" are not mathematically endorsed terminology.
     * @tags    Creator
     */
    public AugList<T> listIntersection(AugList<T> augListB) {
        if (this.equals(augListB)) {
            return augListB;
        }
        augListB = augListB.clone();
        // As listIntersection needs to alter the state of B,
        // the state of B could change outside of scope.
        // This behaviour is not intended, hence replace B with a clone of itself.
        AugList<T> ret = new AugList<T>() {};
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
     * @see         #iterator()
     * @see         #spliterator()
     * @note        Encapsulates {@link List#listIterator()}.
     * @overloads   {@link #listIterator(int)}
     * @tags        Converter
     */
    public ListIterator<T> listIterator() {
        return ls.listIterator();
    }

    /**
     * Returns a <i>fail-fast</i> {@link ListIterator} over this {@link AugList} in proper sequence, starting at the given index.
     * @param       index
     *              The index the {@link ListIterator} will start at.
     * @see         #iterator()
     * @see         #spliterator()
     * @return      A {@link ListIterator} over this {@link AugList} starting at the given index.
     * @note        Encapsulates {@link List#listIterator(int)}.
     * @overloads   {@link #listIterator()}
     * @tags        Converter
     */
    public ListIterator<T> listIterator(int index) {
        return ls.listIterator(index);
    }

    /**
     * Returns the "list union" between this (A) and the given {@link AugList} (B).
     * <p>For a method that calculates the set union, use {@link #setUnion(AugList)}.
     * @param   augListB
     *          The {@link AugList} in question, with which to take the union on.
     * @return  The "list union" , "AU`B".
     *          <p>i.e. [1,1,2]U`[1,2,3] = [1,1,2,3],
     *                  [1,1,1,2]U`[1,1,2,3] = [1,1,1,2,3],
     *                  [1,2]U`[1,3] = [1,2,3],
     *                  [1,3]U`[] = [1,3],
     *                  []U`[1,3] = [1,3].
     *          <p>Output is a list, which may be a set.
     * @see     #listDifference(AugList)
     * @see     #listIntersection(AugList)
     * @see     #setUnion(AugList)
     * @note    Based on the C# method {@code IEnumerable<T>.Union()}.
     *          <p>"List Union" and "AU`B" are not mathematically endorsed terminology.
     * @tags    Creator
     */
    public AugList<T> listUnion(AugList<T> augListB) {
        // Hashtable<T, Integer> listACounts = this.countsOfElements();
        // Hashtable<T, Integer> listBCounts = listB.countsOfElements();
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
     *          If the specified function is {@code null}.
     * @return  A new {@link AugList}, with each element transformed as according to the function.
     * @see     #applyAll(Function)
     * @see     #forEach(Consumer)
     * @note    Replaces {@link ArrayList#forEach(Consumer)}.
     * @tags    Creator
     */
    public <U> AugList<U> oneToOneMap(Function<? super T, U> func) {
        AugList<U> ret = new AugList<U>();
        for (T element : ls) {
            ret.add(func.apply(element));
        }
        return ret;
    }

    /**
     * Creates a {@link Hashtable} where each element in this {@link AugList} is paired with its corresponding entry in the given {@link AugList}.
     * @param   <U>
     *          The type of elements in {@code AugListB}.
     * @param   augListB
     *          The {@link AugList} in question.
     * @return  A {@link HashTable} that pairs up elements,
     *          or an empty {@link Hashtable} if either
     *          <p>- the lengths do not match, or
     *          - at least 1 entry in either list is {@code null}.
     * @note    Based on the C# function {@code IEnumerable<T>.Zip()}.
     * @tags    Converter
     */
    public <U> Hashtable<T,U> pairUp(AugList<U> augListB) {
        Hashtable<T,U> ret = new Hashtable<T,U>() {};
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
     * @see     #stream()
     * @note    Encapsulates {@link ArrayList#parallelStream()}.
     * @tags    Converter
     */
    public Stream<T> parallelStream() {
        return ls.parallelStream();
    }

    /**
     * Attempts to remove the first occurrence of the given {@link Object} from this {@link AugList}, if present.
     * <p>For a Mutator method, see {@link #without(Object)}.
     * @param   o
     *          The {@link Object} to remove if present.
     * @return  {@code true} if removed, and {@code false} if it was not present.
     * @see     #removeAll(AugList)
     * @see     #removeAll(T...)
     * @see     #removeAt(int)
     * @see     #removeIf(Predicate)
     * @see     #removeLast()
     * @see     #removeRandom()
     * @see     #without()
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
     * @param       elements
     *              The elements to remove in question.
     * @return      {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @see         #withoutAll(AugList)
     * @see         #remove(Object)
     * @see         #listDifference(AugList)
     * @see         java.util.Collection#retainAll(java.util.Collection)
     * @note        Replaces {@link List#removeAll(java.util.Collection)}.
     * @overloads   {@link #removeAll(T...)}
     * @tags        Terminator
     */
    public boolean removeAll(AugList<T> elements) {
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
     *              The elements to remove in question.
     * @return      {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @see         #withoutAll(T...)
     * @see         #remove(Object)
     * @see         #listDifference(AugList)
     * @see         java.util.Collection#retainAll(java.util.Collection)
     * @note        Varargs variant of {@link #removeAll(AugList)}.
     * @overloads   {@link #removeAll(AugList)}
     * @tags        Terminator
     */
    @SafeVarargs
    public final boolean removeAll(T... elements) {
        boolean ret = false;
        for (int i = 0; i < elements.length; i++) {
            if (ls.contains(elements[i])) {
                ret = true;
                ls.remove(elements[i]);
            }
        }
        return ret;
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
     * @see     #remove(Object)
     * @see     #removeLast()
     * @see     #removeRandom()
     * @see     #withoutIndex(int)
     * @note    Encapsulates {@code ArrayList<T>.remove(int)}.
     * @tags    Terminator
     */
    public T removeAt(int index) {
        return ls.remove(index);
    }

    // ArrayList<T>.removeFirst() is redundant because of ArrayList<T>.remove(0) so is not implemented

    /**
     * Removes all elements in this list that satisfy the given {@code filter}.
     * <p>For a Mutator method, see {@link #withoutWhere(Predicate)}.
     * @param   filter
     *          The condition in question.
     * @return  {@code true} if at least 1 item was removed, and {@code false} otherwise.
     * @throws  NullPointerException
     *          The given filter is {@code null}.
     * @see     #remove(Object)
     * @see     #withoutWhere(Predicate)
     * @note    Encapsulates {@link List#removeIf(Predicate)}.
     * @tags    Terminator
     */
    public boolean removeIf(Predicate<? super T> filter) {
        return ls.removeIf(filter);
    }

    /**
     * Pops the final element in this {@link AugList}.
     * <p>For a Mutator method, see {@link #withoutLast()}.
     * @return  The final element, if it exists.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @see     #remove(Object)
     * @see     #withoutLast()
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
     * @see     #remove(Object)
     * @see     #withoutRandom()
     * @note    Randomized variant of {@link #removeAt(int)}.
     * @tags    Terminator
     */
    public T removeRandom() {
        if (size() == 0) {
            throw new NoSuchElementException("Cannot remove an element from an empty list.");
        }
        return ls.remove((new Random()).nextInt(ls.size()));
    }

    //TODO: Encapsulate ArrayList<T>.retainAll() (Work will be done on a different branch to avoid branch contamination)
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
     * Returns a new reversed-order {@link AugList}.
     * @return  A new {@link AugList} with the same elements as this one, but in reverse order.
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
     * @note    Encapsulates {@link List#set()}.
     * @tags    Mutator
     */
    public T set(int index, T element) {
        return ls.set(index, element);
    }

    /**
     * Returns the set difference between this (A) and the provided set (B). (i.e. A\B)
     * <p>For a method that calculates the "List difference", see {@link #listDifference(AugList)}
     * @param   setB
     *          The set of elements to take the difference with.
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
     * @see     #setIntersection(AugList)
     * @see     #setUnion(AugList)
     * @see     #listDifference(AugList)
     * @note    Inspired by the C# methods {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
     * @tags    Creator
     */
    public AugList<T> setDifference(AugList<T> setB) {
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
     * Returns the set intersection between this (A) and the given {@link AugList} (B). (I.e. A∩B)
     * <p>For a method that calculates the "List Intersection", see {@link #listIntersection(AugList)}.
     * @param   setB
     *          The set of elements to take the intersection with. 
     *          (If not already a mathematical set, will be turned into one first.)
     * @return  The set intersection (A∩B), or an AugList with elements that appear in both sets. 
     *          <p>i.e. [1,1,2]∩[1,2,3] = {1,2}, 
     *                  [1,1,1,2]∩[1,1,2,3] = {1,2},
     *                  {}∩[1,1,3,7] = {}.
     *          <p>Output is a mathematical set.
     * @see     #setDifference(AugList)
     * @see     #setUnion(AugList)
     * @see     #listIntersection(AugList)
     * @note    Based on the C# method {@code IEnumerable<T>.Intersect()}.
     * @tags    Creator
     */
    public AugList<T> setIntersection(AugList<T> setB) {
        if (this.equals(setB)) {
            return setB;
        }
        setB = setB.distinctCopy();
        // As setIntersection needs to alter the state of B,
        // the state of B could change outside of scope.
        // This behaviour is not intended, hence replace B with a clone of itself.
        AugList<T> ret = new AugList<T>() {};
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
     * <p>For a method that returns the "List Union", see {@link #listUnion(AugList)}.
     * @param   setB
     *          The set of elements to union with. (If not one already, is made into one first.)
     * @return  The set union (AUB).
     *          <p>i.e. {0,1,2}U{1,2,3} = {0,1,2,3},
     *                  [1,1,1,2]U[1,1,2,3] = {1,2,3}.
     *          <p>Output is a mathematical set.
     * @see     #setDifference(AugList)
     * @see     #setIntersection(AugList)
     * @see     #listUnion(AugList)
     * @note    Based on the C# function {@code IEnumerable<T>.Union()}.
     * @tags    Creator
     */
    public AugList<T> setUnion(AugList<T> setB) {
        AugList<T> ret = new AugList<T>() {};
        ret.addAll(this.distinctCopy());
        ret.addAll(setB.distinctCopy().filterSelf(e -> !ret.contains(e)));
        //ret.filterSelf(x -> ret.allIndicesOf(x).size() == 1); // Removes duplicates
        return ret;
    }

    /**
     * Creates a new shuffled {@link AugList}. Can shuffle to itself.
     * <p>For a Mutator method, see {@link #shuffleSelf()}.
     * @return  A new {@link AugList} which is a shuffled copy of this one.
     * @see     #shuffleSelf()
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
     * @see     #shuffleCopy()
     * @tags    Mutator
     */
    public AugList<T> shuffleSelf() {
        this.ls = shuffleCopy().ls;
        return this;
    }

    /**
     * Skips elements until the first element to fail the given condition, then returns the failing and all proceeding elements.
     * @param   condition
     *          The condition in question.
     * @return  A new {@link AugList} with elements beyond and including the first to fail the given condition.
     * @see     #takeWhile(Predicate)
     * @note    Based on the C# method {IEnumerable<T>.skipWhile()}.
     * @tags    Creator
     */
    public AugList<T> skipWhile(Predicate<? super T> condition) {
        AugList<T> ret = new AugList<T>() {};
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
     * Returns the number of elements within this {@link AugList}
     * @return  The size of this {@link AugList}.
     * @note    Encapsulates {@link List#size()}.
     * @tags    Terminator
     */
    public int size() {
        return ls.size();
    }

    /**
     * Sorts this AugList according to the given {@link Comparator}.
     * @param   comparator
     *          The {@link Comparator} in question.
     * @return  This {@link AugList}, sorted according to the given {@link Comparator}.
     * @apiNote Replaces {@link List#sort()}, returns {@code this} rather than {@code void}.
     * @tags    Mutator
     */
    public AugList<T> sort(Comparator<? super T> comparator) {
        ls.sort(comparator);
        return this;
    }

    /**
     * @return  A <i>late-binding, fail-fast</i> {@link Spliterator} over this {@link AugList} in proper sequence.
     * @see     #iterator()
     * @see     #listIterator()
     * @note    Encapsulates {@link List#spliterator()}.
     * @tags    Converter
     */
    public Spliterator<T> spliterator() {
        return ls.spliterator();
    }

    /**
     * Returns a sequential {@link Stream} with this {@link AugList} as its source.
     * @return  A sequential {@link Stream} with the elements of this {@link AugList}.
     * @see     #parallelStream()
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
     * @note    Replaces {@code ArrayList<T>.subList()}, returning a {@link AugList} rather than a {@link List}.
     * @tags    Creator
     */
    public AugList<T> subList(int fromIndex, int toIndex) {
        return new AugList<T>(ls.subList(fromIndex, toIndex));
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
    }

    /**
     * Swaps the given element with a random element. Always alters state.
     * @param   index
     *          The index of the item to swap.
     * @return  This {@link AugList} with the given element swapped into a random position.
     * @throws  IndexOutOfBoundsException
     *          {@code index < 0 || index >= this.size()}
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
    }

    /**
     * Swaps two elements at random. Always alters state.
     * @return  This {@link AugList} with 2 random elements swapped.
     * @see     #swap(int, int)
     * @see     #swapRandom(int)
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
     *          The condition in question.
     * @return  Elements up to and including the first to fail the given condition.
     * @see     #skipWhile(Predicate)
     * @note    Based on the C# method {IEnumerable<T>.TakeWhile()}.
     */
    public AugList<T> takeWhile(Predicate<? super T> condition) {
        AugList<T> ret = new AugList<T>() {};
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
     * @note    Encapsulates {@link List#toArray(T[])}.
     * @tags    Converter
     */
    public T[] toArray(T[] typedArr) {
        return ls.toArray(typedArr);
    }

    /**
     * This {@link AugList}'s contents, as a human-legible {@link String}.
     * @return  This, as a {@link String}.
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
     * @see     #withoutAll(AugList)
     * @see     #withoutAll(T...)
     * @see     #withoutIndex(int)
     * @see     #withoutWhere(Predicate)
     * @see     #withoutLast()
     * @see     #withoutRandom()
     * @see     #remove()
     * @note    Mutator variant of {@link #remove(Object)}.
     * @tags    Mutator
     */
    public AugList<T> without(Object o) {
        ls.remove(o);
        return this;
    }

    /**
     * Attempts to remove the first instance of each of the supplied elements, if present, from this {@link AugList}.
     * <p>For a Terminator method, use {@link #removeAll(AugList)}.
     * @param       elements
     *              The elements in question.
     * @return      {@code this}.
     * @see         #without(Object)
     * @see         #removeAll(AugList)
     * @overloads   {@link #withoutAll(T...)}
     * @note        Mutator variant of {@link #removeAll(AugList)}.
     * @tags        Mutator
     */
    public AugList<T> withoutAll(AugList<T> elements) {
        for (int i = 0; i < elements.size(); i++) {
            if (ls.contains(elements.get(i))) {
                ls.remove(elements.get(i));
            }
        }
        return this;
    }

    /**
     * Attempts to remove the first instance of each of the supplied items, if present, from this {@link AugList}.
     * <p>For a Terminator method, use {@link #removeAll(T...)}.
     * @param       elements
     *              The elements in question.
     * @return      {@code this}.
     * @see         #without(Object)
     * @see         #removeAll(AugList)
     * @overloads   {@link #withoutAll(AugList)}
     * @note        Mutator variant of {@link #removeAll(T...)}.
     * @tags        Mutator
     */
    @SafeVarargs
    public final AugList<T> withoutAll(T... elements) {
        for (int i = 0; i < elements.length; i++) {
            if (ls.contains(elements[i])) {
                ls.remove(elements[i]);
            }
        }
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
     * @see     #without()
     * @see     #withoutRandom()
     * @see     #withoutLast()
     * @see     #removeAt()
     * @note    Mutator variant of {@link #removeAt(int)}.
     * @tags    Mutator
     */
    public AugList<T> withoutIndex(int index) {
        ls.remove(index);
        return this;
    }

    // this.withoutFirst() would be redundant because of this.without(0) so is not implemented

    /**
     * Removes the tail from this list.
     * <p>For a Terminator method, use {@link #removeLast()}.
     * @return  {@code this}.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @see     #without(Object)
     * @see     #withoutIndex(int)
     * @see     #removeAt(int)
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
     * @see     #without(Object)
     * @see     #withoutIndex(int)
     * @see     #removeRandom()
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
     *          The condition that, if an element passes it, causes its deletion.
     * @return  {@code this}.
     * @see     #without(Object)
     * @see     #removeIf(Predicate)
     * @note    Mutator variant of {@link #removeIf(Predicate)}.
     * @tags    Mutator
     */
    public AugList<T> withoutWhere(Predicate<? super T> filter) {
        ls.removeIf(filter);
        return this;
    }

    //#region Former Methods
    /**
     * All methods in this section were available in previous versions, but have since been made redundant.
     * These methods have corresponding tests in AugListTest.java
     */
    /**
     * Produces a sublist from the given index to the end of the list.
     * @param       fromIndex
     *              The index to start from to the end of the list.
     * @return      A sublist from the {@code fromIndex}
     * @throws      IndexOutOfBoundsException
     *              {@code index < 0 || index >= this.size()}
     * @note        Inspired by {@link #subList(int, int)}.
     *              Has testing, which is also commented out: {@link tests.AugListTest#testSubListToEnd()}
     * @deprecated  Due to {@code subList(fromIndex, ls.size())} performing the same task.
     * @tags        Creator
     */
    // public AugList<T> subListToEnd(int fromIndex) {
    //     return subList(fromIndex, ls.size());
    // }
    //#endregion

    //#region Potentially useful code
    /**
     * Finds if the two {@link AugList AugLists} are in an Equivalence Relationship.
     * @param       augListB
     *              The second {@link AugList}.
     * @return      {@code true} if the lists are in a EqRel, and {@code false} otherwise.
     * @note        Inspired by the Equivalence relationship requirements of {@link List#equals()}.
     *              <p>If ~ is a relation (a mapping), then if it fulfils the following:
     *              - Reflexive (x ~ x)
     *              - Symmetric (x ~ y ⇔ y ~ x)
     *              - Transitive (x ~ y ^ y ~ z ⇒ x ~ z)
     *              - Consistent (x ~ y ⇒ x ~ y for as long as x, y are constant)
     *              - Non-null equivalence ( x != null ⇔ x !~ null )
     *              Then they must be equivalent.
     *              <p>Any such relation is called an equivalence relation.
     *              <p>(Notably any such EqRel is a one to one mapping.)
     * @deprecated  Due to current lack of use case. {@link #isRearrangement(AugList)} does the same job.
     * @tags        Terminator
     */
    //public boolean isEqRel(AugList<T> augListB) {
        // if (ls.size() != augListB.size()) {
        //     // If the two lists are different lengths, there is no world in which an EqRel can exist.
        //     // So rather than wasting compute time, we can terminate early.
        //     return false;
        // }
        // if (!this.allSatisfy(x -> x.equals(x)) || !augListB.allSatisfy(x -> x.equals(x))) { // Reflexive check
        //     return false; // Should theoretically never trigger.
        // }
        // AugList<AugList<T>> allRelations = new AugList<AugList<T>>();
        // for (int i = 0; i < ls.size(); i++) {
        //     if (!ls.get(i).equals(ls.get(i)) || !augListB.get(i).equals(augListB.get(i))) { // Consistency check
        //         return false; // (In theory this condition should never fail.)
        //     }
        //     allRelations.add(new AugList<T>());
        //     // (Notably oAsAugList.size() == ls.size().)
        //     for (int j = 0; j < augListB.size(); j++) {
        //         if (ls.get(i).equals(augListB.get(j))) {
        //             if (!ls.get(j).equals(augListB.get(i))) { // Symmetric check
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
        //         if (allRelations.get(i).contains(augListB.get(j)) && !allRelations.get(i).toString().equals(allRelations.get(j).toString()))
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
     * @param       minCapacity
     *              The minimum capacity in question.
     * @note        Encapsulates {@link ArrayList#ensureCapacity(int)}.
     *              Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Due to:
     *              - Being impossible to write tests for
     *              - Enabling only miniscule performance gains for repeated, exceptionally-large-scale addition operations.
     * @tags        Terminator
     */
    // public void ensureCapacity(int minCapacity) {
    //     ls.ensureCapacity(minCapacity);
    // }

    /**
     * Reduces the allocated storage space to this {@link AugList} to the minimum possible.
     * Only useful when dealing with deleting swaths of data from large datasets.
     * @note        Encapsulates {@link ArrayList#trimToSize()}.
     *              Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Due to:
     *              - Being impossible to write tests for
     *              - Enabling only miniscule storage space gains after large-scale deletions of data.
     * @tags        Terminator
     */
    // public void trimToSize() {
    //     ls.trimToSize();
    // }

    /**
     * Create an array copy of this AugList via use of the given {@code generator}
     * @param       generator
     *              The {@link java.util.function.IntFunction} generator in question.
     * @return      An array copy of this AugList.
     * @note        Encapsulates {@link ArrayList#toArray(java.util.function.IntFunction)}.
     *              Should be fully functional if uncommented, though even calling this method may prove difficult.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Due to difficulty of use.
     *              Classes that implement or refer to IntFunctions are very niche,
     *              and examples on how to create and use them are non-existent,
     *              at least from cursory research.
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
     * @param       arrayList
     *              The {@link ArrayList} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @tags        Constructor
     */
    // public AugList(ArrayList<T> arrayList) {
    //     this.ls = arrayList;
    // }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link List}.
     * @param       list
     *              The {@link List} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @tags        Constructor
     */
    // public AugList(List<T> list) {
    //     this.ls = new ArrayList<T>(list);
    // }

    /**
     * Creates a new {@link AugList} from the given {@link AugList}.
     * @param       augList
     *              The {@link AugList} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @tags        Constructor
     */
    // public AugList(AugList<T> augList) {
    //     this.ls = new ArrayList<T>(augList.ls);
    // }

    /**
     * Creates a new {@link AugList} from the given {@code ? implements} {@link java.util.Deque}.
     * @param       deque
     *              The {@link java.util.Deque} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @tags        Constructor
     */
    // public AugList(java.util.Deque<T> deque) {
    //     this.ls = new AugList<T>(deque.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.PriorityQueue}.
     * @param       prioQueue
     *              The {@link java.util.PriorityQueue} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @tags        Constructor
     */
    // public AugList(java.util.PriorityQueue<T> prioQueue) {
    //     this.ls = new AugList<T>(prioQueue.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.HashSet}.
     * @param       hashSet
     *              The {@link HashSet} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
     * @tags        Constructor
     */
    // public AugList(java.util.HashSet<T> hashSet) {
    //     this.ls = new AugList<T>(hashSet.iterator()).ls;
    // }

    /**
     * Creates a new {@link AugList} from the given {@link java.util.TreeSet}.
     * @param       treeSet
     *              The {@link java.util.TreeSet} in question.
     * @see         #AugList(Iterable)
     * @note        Should be fully functional if uncommented.
     *              Does not have a test in {@link tests.AugListTest}.
     * @deprecated  Made redundant by {@code AugList(Iterable<T>))}.
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
     * @param       fill
     *              What to fill this {@link AugList} with.
     * @param       size
     *              How long the {@link AugList} should be.
     * @throws      IllegalArgumentException
     *              {@code size < 0}
     * @deprecated  Due to lack of use cases, at least at current (26-09-04).
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
     * @param       index
     *              The index of the item to get.
     * @return      The value at that index (which will be {@code null} if {@code index >= this.size()})
     * @throws      IllegalArgumentException
     *              If {@code index < 0}
     * @deprecated  Due to lack of use cases, at least at current (26-09-04).
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

    //#region Cut Classes
    // Was a part of an attempted change to .Equals() that went nowhere.
    // /**
    //  * Helper class that is used in Equals(), consisting of 2 fields and a single constructor.
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
    //#endregion
}