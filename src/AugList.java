package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An augmented ArrayList which implements most ArrayList methods.
 * <p>(Does not implement {@code addLast()}, {{@code ensureCapacity()}, {@code getFirst()}, {@code trimToSize()} & {@code removeFirst()})
 * <p>Changes:
 * <p>{@code boolean add()} (which always returned {@code true}) has been altered to {@code AugList<T> add()}.</p>
 * <p>{@code clone()} and {@code subList()} produce an {@code AugList<T>} rather than an {@code List<T>}.</p>
 * <p>Overrides {@code equals()}, which is more lenient than {@code ArrayList.equals()} and can match AugLists, Lists and Strings.
 * Notably this means {@code this.equals(this.clone())}.</p>
 * <p>Overrides {@code toString()} to provide a more legible overview of the contents.
 * <p>Adds constructors from {@code List<T>} and varargs.</p>
 * <p>Adds varargs variations of {@code addAll}, {@code containsAll}.</p>
 * <p>Adds {@code allSatisfy}, {@code anySatisfy}, {@code distinct}, {@code filter}, {@code getAndAppendIfEmpty}, {@code pairUp},
 * {@code setDifference}, {@code setIntersect}, {@code setUnion}, {@code subListToEnd}, {@code skipWhile}, {@code takeWhile}</p>
 * <p>Decorates an ArrayList.
 */
public class AugList<T> implements Iterable<T> {
    /**
     * The ArrayList that this AugList decorates.
     */
    private ArrayList<T> ls;

    /**
     * Creates an empty, non-null AugList
     */
    public AugList() {
        this.ls = new ArrayList<T>() {};
    }

    /**
     * Create an AugList from the given ArrayList<T> {@code ls}
     * @param   ls
     *          The list of objects that this AugList will have
     */
    public AugList(ArrayList<T> ls) {
        this.ls = ls;
    }

    /**
     * Create an AugList from the given ArrayList<T> {@code ls}
     * @param   ls
     *          The list of objects that this AugList will have
     */
    public AugList(List<T> ls) {
        this.ls = new ArrayList<T>(ls);
    }

    @SafeVarargs
    /**
     * Create an AugList from the given T[] {@code a}
     * @param   a
     *          An array of objects that this AugList will have
     */
    public AugList(T... a) {
        this.ls = new ArrayList<T>(Arrays.asList(a));
    }

    /**
     * Create a new AugList from an Enumeration over some sequence.
     * @param   enumeration
     *          The Enumeration object to source the input from.
     */
    public AugList(Enumeration<T> enumeration) {
        this.ls = new ArrayList<T>() {};
        while (enumeration.hasMoreElements()) {
            this.ls.add(enumeration.nextElement());
        }
    }

    /**
     * Create a new AugList from an Iterator over some sequence.
     * @param   iterator
     *          The iterator object to source the input from.
     */
    public AugList(Iterator<T> iterator) {
        this.ls = new ArrayList<T>() {};
        while (iterator.hasNext()) {
            this.ls.add(iterator.next());
        }
    }

    /**
     * Appends {@code e} to the end of this AugList.
     *
     * @param   e
     *          The element to be appended to this list.
     * @return  This list, with the given element appended to it.
     * @apiNote Modifies {@code ArrayList<T>.add(T)}: Returns {@code this}, not {@code void}.
     */
    public AugList<T> add(T e) {
        ls.add(e);
        return this;
    }
    
    /**
     * Appends all items in {@code pAugList} to this AugList.
     * <p>For a non-destructive method, use {@code listUnion}.
     * @param   pAugList
     *          The AugList to append onto the end of this AugList.
     * @return  This list, with the given elements appended to it.
     * @apiNote Modifies {@code ArrayList<T>.addAll(Collection<? extends T>)}: Returns {@code this}, not {@code void}.
     */
    public AugList<T> addAll(AugList<T> pAugList) {
        ls.addAll(pAugList.ls);
        return this;
    }

    /**
     * Appends all items in {@code elements} to this AugList.
     * @param   elements
     *          The elements to append onto the end of this AugList.
     * @return  This AugList, with the given items appended to it.
     * @apiNote Custom overload of {@code ArrayList<T>.addAll()}.
     */
    @SafeVarargs
    public final AugList<T> addAll(T... elements) {
        ls.addAll(new AugList<T>(elements).ls);
        return this;
    }

    /**
     * Prepends {@code e} onto this AugList.
     * @param   e
     *          The element to prepend
     * @return  This list, with the given element prepended to it.
     * @apiNote Modifies {@code ArrayList<T>.addFirst(T)}: Returns {@code this}, not {@code void}.
     */
    public AugList<T> addFirst(T e) {
        ls.addFirst(e);
        return this;
    }

    // ArrayList.addLast is not implemented as ArrayList.add performs the same task

    /**
     * Finds all indices at which the given element can be found.
     * @param   e
     *          The element to find.
     * @return  A new AugList with the indices at which it can be found.
     * @apiNote Custom method with functionality that is, to my knowledge, not implemented in Java or C#.
     */
    public AugList<Integer> allIndicesOf(T e) {
        AugList<Integer> ret = new AugList<Integer>();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).equals(e)) {
                ret.add(i);
            }
        }
        return ret;
    }

    /**
     * Returns {@code true} if the {@code predicate} passes for all elements.
     * @param   predicate
     *          The predicate which all elements must pass.
     * @return  {@code true} if all elements satisfy the {@code predicate}, and {@code false} otherwise.
     * @apiNote Custom method which implements the functionality of C# functions {@code IEnumerable<T>.All()} and {@code List<T>.TrueForAll()}.
     */
    public boolean allSatisfy(Predicate<? super T> predicate) {
        for (T e : ls) {
            if (!predicate.test(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns {@code true} if the {@code predicate} passes for any element.
     * @param   predicate
     *          The predicate which at least 1 of the elements must pass.
     * @return  {@code true} if any elements satisfies the {@code predicate}, and {@code false} otherwise.
     * @apiNote Custom method with functionality that is, to my knowledge, not implemented in Java or C#.
     */
    public boolean anySatisfy(Predicate<? super T> predicate) {
        for (T e : ls) {
            if (predicate.test(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Applies the provided function on this AugList.
     * <p>For a non-destructive method, use {@code this.oneToOneMap()}.</p>
     * <p>For a method with return type void, use {@code this.forEach()}.</p>
     * @param   func
     * @return  This AugList, with each element having been transformed.
     * @apiNote Custom method with functionality that is, to my knowledge, not implemented in Java or C#.
     *          <p>Functionality is comparable to the C# function {@code List<T>.ConvertAll()}.</p>
     */
    public AugList<T> applyAll(Function<? super T, T> func) {
        for (int i = 0; i < ls.size(); i++) {
            ls.set(i, func.apply(ls.get(i)));
        }
        return this;
    }

    /**
     * Creates a new AugList of AugLists where each entry has at most {@code size} elements.
     * @param   size
     *          The maximum size of a chunk.
     * @return  This AugList split into smaller AugLists with a maximum length of {@code size}.
     * @throws  IllegalArgumentException
     *          {@code size <= 0}
     * @apiNote Custom method that implements the functionality of C# function {@code List<T>.Chunk()}.
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
     * Clears this AugList.
     * @return  This empty AugList<T>.
     * @apiNote Modifies {@code ArrayList<T>.clear()}: Returns {@code this}, not {@code void}.
     */
    public AugList<T> clear()
    {
        ls.clear();
        return this;
    }

    // Notably, works differently to ArrayList<T>.clone() in that the return type is an AugList<T>.
    /**
     * @return  A clone of this AugList with the same contents as this one, but with a different memory address.
     * @apiNote Pseudo-Overrides {@code ArrayList<T>.clone()} and has {@code AugList<T>} return type.
     */
    public AugList<T> clone() {
        AugList<T> clone = new AugList<T>() {};
        for (T e : ls) {
            clone.add(e);
        }
        return clone;
    }

    /**
     * Attempts to find {@code e} and returns {@code true} if so.
     * @param   e
     *          The element to search for.
     * @return  {@code true} if {@code e} exists within this AugList.
     * @apiNote Encapsulates {@code ArrayList<T>.contains(T)}.
     */
    public boolean contains(T e) {
        return ls.contains(e);
    }
    
    /**
     * Attempts to find all elements in {@code pAugList} inside this AugList and returns {@code true} if so.
     * @param   pAugList
     *          The AugList of elements to find.
     * @return  {@code true} if all elements in {@code pAugList} are found.
     * @apiNote Pseudo-overrides {@code AbstractCollection<T>.containsAll(Collection<?>)}
     */
    public boolean containsAll(AugList<T> pAugList) {
        for (T e : pAugList) {
            if (!ls.contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to find all elements in {@code elements} inside this AugList and returns {@code true} if so.
     * @param   elements
     *          The elements to find.
     * @return  {@code true} if all elements in {@code elements} are found.
     * @apiNote Custom method with functionality that is, to my knowledge, not implemented in Java or C#.
     *          <p>Functionality can be replicated with {@code this.allSatisfy(e -> this.contains(e))}.</p>
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
     * Attempts to find if any element in {@code elements} is inside this AugList and returns {@code true} if so.
     * @param   elements
     *          The elements to find.
     * @return  {@code true} if any of the elements in {@code elements} are found.
     * @apiNote Custom method with functionality that is, to my knowledge, not implemented in Java or C#.
     *          <p>Functionality can be replicated with {@code this.anySatisfy(e -> elements.contains(e))}.</p>
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
     * Attempts to find if any element in {@code elements} id inside this AugList and returns {@code true} if so.
     * @param   elements
     *          The elements to find.
     * @return  {@code true} if any of the elements in {@code elements} are found.
     * @apiNote Custom method with functionality that is, to my knowledge, not implemented in Java or C#.
     *          <p>Functionality can be replicated with {@code this.anySatisfy(e -> elements.contains(e))}.</p>
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
     * @return  A Hashtable pairing each element with number of occurrences.
     */
    public Hashtable<T,Integer> countsOfElements() {
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
     * Creates a new AugList which is a copy of this AugList, except without duplicates.
     * @return  A new AugList with only the distinct elements in this AugList.
     * @apiNote Custom method that implements the functionality of C# function {@code IEnumerable<T>.Distinct()};
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
     * Changes this AugList so it only contains one copy of each item.
     * <p>For obtaining a non-destructive copy, use {@code distinctCopy()}.</p>
     * @return  This AugList, with each element being distinct from each other.
     * @apiNote Custom method similar to C# function {@code IEnumerable<T>.Distinct()}, but changes this list instead of creating a new one.
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

    // ArrayList<T>.ensureCapacity() has no meaningful impact on the state of the list, so is not implemented.

    @Override
    /**
     * In VSCode, it appears it is not be possible to change the hover-tooltip for Object.equals()
     */
    public boolean equals(Object o) {
        if (o instanceof AugList) {
            @SuppressWarnings({ "rawtypes" }) // Suppress the unchecked cast caution as o is already an AugList under the hood.
            AugList oAsAugList = (AugList) o;
            // We know o is an AugList:
            // Firstly, are the lists the same size?
            if (ls.size() != oAsAugList.size()) {
                return false;
            }
            // If they are, are the sequences identical?
            for (int i = 0; i < ls.size(); i++) {
                if (ls.get(i) != oAsAugList.get(i)) {
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
        // If it is not any of the supported types, then assume non-equivalence.
        return false;
    }

    /**
     * Creates a new AugList that only contains the elements that satisfy {@code predicate}.
     * <p>To also change the underlying values, use {@code filterSelf()}.
     * @param   predicate
     *          The condition an element must pass to be included.
     * @return  A new AugList with elements that satisfy the {@code predicate}.
     * @apiNote Custom method that implements the functionality of C# function {@code IEnumerable<T>.Where()};
     */
    public AugList<T> filterCopy(Predicate<? super T> predicate) {
        AugList<T> ret = new AugList<T>() {};
        for (T e : ls) {
            if (predicate.test(e)) {
                ret.add(e);
            }
        }
        return ret;
    }

    /**
     * Alters this AugList to only contains the elements that satisfy {@code predicate}.
     * <p>For a non-destructive method, use {@code filterCopy()}.
     * @param   predicate
     *          The condition an element must pass to be included.
     * @return  This AugList, containing only elements that satisfy the {@code predicate}.
     * @apiNote Custom method similar to C# function {@code IEnumerable<T>.Where()}, but changes this list instead of creating a new one.
     */
    public AugList<T> filterSelf(Predicate<? super T> predicate) {
        AugList<T> ret = new AugList<T>() {};
        for (T e : ls) {
            if (predicate.test(e)) {
                ret.add(e);
            }
        }
        this.ls = ret.ls;
        return this;
    }

    /**
     * Performs the given void {@code action} to each element.
     * <p>For returning a set of values, use {@code this.oneToOneMap()}.</p>
     * <p>For changing the underlying values, use {@code this.applyAll()}.</p>
     * @param   action
     *          The action to perform.
     * @throws  NullPointerException
     *          If the specified action is {@code null}.
     * @apiNote Encapsulates {@code ArrayList<T>.forEach(Consumer<? super E>)}.
     */
    public void forEach(Consumer<? super T> action) {
        ls.forEach(action);
    }

    // Ideally I would add the ability to index with [].
    // However, I don't believe the such is possible in Java.
    /**
     * Returns the element at {@code index}.
     * @param   index
     *          Index of the element to return.
     * @return  The element at {@code index}.
     * @throws  IndexOutOfBoundsException
     *          The index is out of array bounds.
     * @apiNote Encapsulates {@code ArrayList<T>.get(int)}.
     */
    public T get(int index) {
        return ls.get(index);
    }

    // Object.getClass() cannot be overridden and as such is not implemented
    // ArrayList<T>.getFirst() is not implemented as it is made redundant by get(0)
    
    // ArrayList<T>.getLast() has just enough of a fringe usage that it is implemented;
    // Typing ArrayList<T>.get(ArrayList<T>.size() - 1) is a little arduous.
    /**
     * @return  The last item in this AugList.
     * @throws  NoSuchElementException
     *          This AugList is empty.
     * @apiNote Encapsulates {@code AbstractCollection<T>.getLast()}.
     */
    public T getLast() {
        return ls.getLast();
    }

    /**
     * @return  The hash code of the underlying ArrayList.
     * @apiNote Encapsulates {@code ArrayList<T>.getLast()}.
     */
    public int hashCode() {
        return ls.hashCode();
    }

    /**
     * Returns the index of the first occurrence of object {@code o}, or -1 if it is not in this list.
     * @param   o
     *          The object to search for.
     * @return  The index of the first occurrence of {@code o}, or -1 if it is not present.
     * @apiNote Encapsulates {@code ArrayList<T>.indexOf()}.
     */
    public int indexOf(Object o) {
        return ls.indexOf(o);
    }

    /**
     * Inserts {@code e} at the specified position in this list, shifting other elements along if necessary.
     * @param   index
     *          Index at which the specified element is to be inserted.
     * @param   e
     *          Element to be inserted
     * @throws  IndexOutOfBoundsException
     *          The provided index is out of array bounds.
     * @return  This list, with the given element inserted at the given index.
     * @apiNote Modifies {@code ArrayList<T>.add(int, T)}: Returns {@code this}, not {@code void}.
     */
    public AugList<T> insert(int index, T e) {
        ls.add(index, e);
        return this;
    }

    /**
     * Inserts all items in {@code pAugList} to this AugList at the provided {@code index}.
     * @param   index
     *          Index at which the specified element is to be inserted.
     * @param   pAugList
     *          The AugList to append onto the end of this AugList.
     * @throws  IndexOutOfBoundsException
     *          The provided index is out of array bounds.
     * @return  This list, with the given elements inserted at the given index.
     * @apiNote Modifies {@code ArrayList<T>.addAll(int, Collection<? extends T>)}: Returns {@code this}, not {@code void}.
     */
    public AugList<T> insertAll(int index, AugList<T> pAugList) {
        ls.addAll(index, pAugList.ls);
        return this;
    }

    /**
     * Inserts all items in {@code pAugList} to this AugList at the provided {@code index}.
     * @param   index
     *          Index at which the specified element is to be inserted.
     * @param   elements
     *          The elements to append onto the end of this AugList.
     * @throws  IndexOutOfBoundsException
     *          The provided index is out of array bounds.
     * @return  This AugList, with the given items inserted into it at the given location.
     * @apiNote Custom overload of {@code ArrayList<T>.addAll()}.
     */
    @SafeVarargs
    public final AugList<T> insertAll(int index, T... elements) {
        ls.addAll(index, new AugList<T>(elements).ls);
        return this;
    }

    /**
     * @return  {@code true} if there are exactly 0 elements inside this AugList.
     * @apiNote Encapsulates {@code ArrayList<T>.isEmpty()}.
     */
    public boolean isEmpty() {
        return ls.isEmpty();
    }
    
    /**
     * Compares whether or not two lists have the same elements. (Order does not matter)
     * <p>For a stricter equality function, use {@code this.equals()}.</p>
     * @param   otherAL
     *          The second list to compare against.
     * @return  {@code true} if the lists match; {@code false} otherwise.
     * @apiNote Custom method with functionality that, to my knowledge, is not implemented in Java or C#.
     */
    public boolean isRearrangement(AugList<T> otherAL) {
        if (ls.size() != otherAL.size()) {
            // If the two lists are different lengths, there is no world in which an EqRel can exist.
            // So rather than wasting compute time, we can terminate early.
            return false;
        }
        AugList<T> augListA = this.clone();
        AugList<T> augListB = otherAL.clone();
        // Notably this for loop does not reassign i for each loop.
        for (int i = 0; i < augListA.size();) {
            if (!augListB.contains(augListA.get(i))) {
                return false;
            }
            augListB.remove(augListA.get(i));
            augListA.remove(augListA.get(i));
        }
        return true;
    }

    /**
     * @return  An iterator over this AugList that goes in sequence
     * @apiNote Encapsulates {@code ArrayList<T>.iterator()}.
     */
    public Iterator<T> iterator() {
        return ls.iterator();
    }

    /**
     * Returns the index of the final occurrence of object {@code o}, or -1 if it is not in this list.
     * @param   o
     *          The object to search for.
     * @return  The index of the final occurrence of {@code o}, or -1 if it is not present.
     * @apiNote Encapsulates {@code ArrayList<T>.lastIndexOf()}.
     */
    public int lastIndexOf(Object o) {
        return ls.lastIndexOf(o);
    }

    /**
     * Returns the list difference between this and the provided {@code pAugList}. (i.e. A\B)
     * <p>The result is a new AugList.
     * @param   listB
     *          The list of items to remove.
     * @return  The list difference (A\B).
     *          <p>i.e. [1,2]\[1] = [2], [1,1,2]\[1,3] = [1,2], [1,1,2]\[1,1,3] = [2]
     *          <p>Output is a list, which may be a set.
     * @apiNote Custom method tangentially inspired by the C# functions {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
     */
    public AugList<T> listDifference(AugList<T> listB) {
        AugList<T> ret = this.clone();
        for (int i = 0; i < listB.size(); i++) {
            if (ret.contains(listB.get(i))) {
                ret.remove(listB.get(i));
            }
        }
        return ret;
    }

    /**
     * Returns the list intersection between this and the provided {@code pAugList}. (I.e. A∩B)
     * <p>The result is a new AugList.
     * @param   listB
     *          The list of items to intersect with.
     * @return  The list intersection (A∩B), or an AugList with elements that appear in both lists. 
     *          <p>i.e. [1,1,2]∩[1,2,3] = [1,2], [1,1,1,2]∩[1,1,2,3] = [1,1,2].
     *          <p>Output is a list, which may be a set.
     * @apiNote Custom method similar to the C# function {@code IEnumerable<T>.Intersect()}
     */
    public AugList<T> listIntersection(AugList<T> listB) {
        if (this.equals(listB)) {
            return listB;
        }
        listB = listB.clone();
        // As listIntersection needs to alter the state of listB,
        // the state of listB could change outside of scope.
        // This behaviour is not intended, hence replace listB with a clone of itself.
        AugList<T> ret = new AugList<T>() {};
        for (T e : ls) {
            if (listB.contains(e)) {
                ret.add(e);
                listB.remove(e);
            }
        }
        return ret;
    }

    /**
     * @return  A ListIterator over this AugList that goes in sequence
     * @apiNote Encapsulates {@code ArrayList<T>.listIterator()}.
     */
    public ListIterator<T> listIterator() {
        return ls.listIterator();
    }

    /**
     * Creates a ListIterator starting at {@code index}
     * @param   index
     *          The index this ListIterator starts at.
     * @return  A ListIterator over this AugList that goes in sequence and starts at {@code index}
     * @apiNote Encapsulates {@code ArrayList<T>.listIterator(int)}.
     */
    public ListIterator<T> listIterator(int index) {
        return ls.listIterator(index);
    }

    /**
     * Returns the list union between this and the provided {@code pAugList}. (i.e. AUB)
     * <p>The result is a new AugList.
     * @param   listB
     *          The list of items to union with.
     * @return  The list union (AUB).
     *          <p>i.e. [1,1,2]U[1,2,3] = [1,1,2,3], [1,1,1,2]U[1,1,2,3] = [1,1,1,2,3].
     *          <p>Output is a list, which may be a set. DOES NOT PRESERVE ORDER.
     * @apiNote Custom method similar to the C# function {@code IEnumerable<T>.Union()}
     */
    public AugList<T> listUnion(AugList<T> listB) {
        // Hashtable<T, Integer> listACounts = this.countsOfElements();
        // Hashtable<T, Integer> listBCounts = listB.countsOfElements();
        AugList<T> ret = this.clone();
        listB = listB.clone();
        // As listIntersection needs to alter the state of listB,
        // the state of listB could change outside of scope.
        // This behaviour is not intended, hence replace listB with a clone of itself.
        for (int i = 0; i < ret.size(); i++) {
            listB.remove(ret.get(i));
        }
        for (int i = 0; i < listB.size(); i++) {
            ret.add(listB.get(i));
        }
        return ret;
    }

    // Object.notify() and Object.notifyAll() cannot be overridden and so are not implemented

    /**
     * Creates a new {@code AugList<U>} where each item has been transformed according to the supplied {@code func}
     * @param   <U>
     *          The type of the resultant elements.
     * @param   func
     *          The function to apply.
     * @return  An {@code AugList<U>} with each item transformed according to {@code func}.
     * @apiNote Custom method that implements functionality of the C# function {@code IEnumerable<T>.ConvertAll()}
     */
    public <U> AugList<U> oneToOneMap(Function<? super T, U> func) {
        AugList<U> ret = new AugList<U>();
        for (T e : ls) {
            ret.add(func.apply(e));
        }
        return ret;
    }

    /**
     * Creates a Hashtable where each element in this AugList is paired with its corresponding entry in {@code pAugList}
     * @param   <U>
     *          The type of {@code AugList2}.
     * @param   AugList2
     *          The list of items to pair up.
     * @return  A hashtable that pairs up elements,
     *          or an empty Hashtable if either
     *          1) the lengths do not match, or
     *          2) at least 1 entry in either list is null.
     * @apiNote Custom method that implements functionality of the C# function {@code IEnumerable<T>.Zip()}.
     */
    public <U> Hashtable<T,U> pairUp(AugList<U> AugList2) {
        Hashtable<T,U> ret = new Hashtable<T,U>() {};
        if (ls.size() != AugList2.size() || this.anySatisfy(e -> e == null) || AugList2.anySatisfy(e -> e == null)) {
            return ret;
        }
        for (int i = 0; i < ls.size(); i++) {
            ret.put(ls.get(i), AugList2.get(i));
        }
        return ret;
    }

    /**
     * @return  A Stream Object with this AugList as its elements.
     * @apiNote Encapsulates {@code ArrayList<T>.parallelStream()}.
     */
    public Stream<T> parallelStream() {
        return ls.parallelStream();
    }

    /**
     * Attempts to remove {@code o} from this AugList.
     * @param   o
     *          The object to remove if present.
     * @return  {@code true} if removed, and {@code false} if not.
     * @apiNote Encapsulates {@code ArrayList<T>.remove(Object)}.
     * @implNote See {@code this.without(Object)} for a Stream-oriented approach.
     */
    public boolean remove(Object o) {
        return ls.remove(o);
    }

    /**
     * Removes all items in {@code pAugList}, if present, from this AugList.
     * <p>For a non-destructive method, use {@code ListDifference()}.
     * @param   pAugList
     *          The list of items to remove from this AugList.
     * @return  {@code true} if at least 1 item was removed.
     * @apiNote Pseudo-overrides {@code ArrayList<T>.removeAll(Collection<c?>)}.
     * @implNote See {@code this.withoutAll(AugList<T>)} for a Stream-oriented approach.
     */
    public boolean removeAll(AugList<T> pAugList) {
        boolean ret = false;
        for (int i = 0; i < pAugList.size(); i++) {
            if (ls.contains(pAugList.get(i))) {
                ret = true;
                ls.remove(pAugList.get(i));
            }
        }
        return ret;
    }

    /**
     * Removes all items in {@code varargs}, if present, from this AugList.
     * <p>For a non-destructive method, use {@code ListDifference()}.
     * @param   varargs
     *          The list of items to remove from this AugList.
     * @return  {@code true} if at least 1 item was removed.
     * @apiNote Overloads {@code ArrayList<T>.removeAll(Collection<c?>)}.
     * @implNote See {@code this.withoutAll(T...)} for a Stream-oriented approach.
     */
    @SafeVarargs
    public final boolean removeAll(T... varargs) {
        boolean ret = false;
        for (int i = 0; i < varargs.length; i++) {
            if (ls.contains(varargs[i])) {
                ret = true;
                ls.remove(varargs[i]);
            }
        }
        return ret;
    }

    // Works the same as a pop operation from a stack, except can pop any element rather than the top element.
    /**
     * Removes the item at the given {@code index}, and shifts indices as necessary.
     * @param   index
     *          The index of the target item to remove.
     * @return  The item that was removed
     * @throws  IndexOutOfBoundsException
     *          The index is not in array bounds.
     * @apiNote Encapsulates {@code ArrayList<T>.remove(int)}.
     * @implNote See {@code this.withoutIndex(int)} for a Stream-oriented approach.
     */
    public T removeAt(int index) {
        return ls.remove(index);
    }

    // ArrayList<T>.removeFirst() is redundant because of ArrayList<T>.remove(0) so is not implemented

    /**
     * Removes all items in this list that satisfy the given {@code filter}.
     * @param   filter
     *          The condition that, if an element passes it, causes its deletion.
     * @return  {@code true} if at least 1 item was removed.
     * @apiNote Encapsulates {@code ArrayList<T>.removeIf(Predicate<? super T>)}.
     * @implNote See {@code this.withoutWhere(Predicate<? super T>)} for a Stream-oriented approach.
     */
    public boolean removeIf(Predicate<? super T> filter) {
        return ls.removeIf(filter);
    }

    /**
     * Pops the final item in this AugList.
     * @return  The final item in this AugList.
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @apiNote Encapsulates {@code ArrayList<T>.removeLast()}.
     * @implNote See {@code this.withoutLast()} for a Stream-oriented approach.
     */
    public T removeLast() {
        return ls.removeLast();
    }

    /**
     * @return  A new AugList with its elements in reverse order.
     * @apiNote Encapsulates {@code List<T>.reversed()}.
     */
    public AugList<T> reversed() {
        return new AugList<T>(ls.reversed());
    }

    /**
     * Sets the given {@code index} to {@code e}.
     * @param   index
     *          The index of the element to replace.
     * @param   e
     *          The element to replace the current element.
     * @return  The item previously at {@code index}.
     * @throws  OutOfRangeException
     *          The index is out of array bounds.
     * @apiNote Encapsulates {@code ArrayList<T>.set()}.
     */
    public T set(int index, T e) {
        return ls.set(index, e);
    }

    /**
     * Returns the set difference between this and the provided {@code pAugList}. (i.e. A\B)
     * <p>The result is a new AugList.
     * @param   setB
     *          The set of items to remove. (If is not a Set, will be turned into one first.)
     * @return  The set difference (A\B).
     *          <p>i.e. [1,2]\[1,3] = [2], [1,1,2]\[2] = [1]
     *          <p>Output is a set.
     * @apiNote Custom method tangentially inspired by the C# functions {@code IEnumerable<T>.Union()} and {@code IEnumerable<T>.Intersect()}.
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
     * Returns the set intersection between this and the provided {@code pAugList}. (I.e. A∩B)
     * <p>The result is a new AugList.
     * @param   setB
     *          The set of items to intersect with. (If is not a Set, will be turned into one first.)
     * @return  The set intersection (A∩B), or an AugList with elements that appear in both sets. 
     *          <p>i.e. [1,1,2]∩[1,2,3] = [1,2], [1,1,1,2]∩[1,1,2,3] = [1,2].
     *          <p>Output is a set.
     * @apiNote Custom method based on the C# function {@code IEnumerable<T>.Intersect()}.
     */
    public AugList<T> setIntersection(AugList<T> setB) {
        if (this.equals(setB)) {
            return setB;
        }
        setB = setB.distinctCopy();
        // As listIntersection needs to alter the state of listB,
        // the state of listB could change outside of scope.
        // This behaviour is not intended, hence replace listB with a clone of itself.
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
     * Returns the set union between this and the provided {@code setB}. (i.e. AUB)
     * <p>The result is a new AugList.
     * @param   SetB
     *          The set of items to union with. (If not one already, is made into one first.)
     * @return  The set union (AUB).
     *          <p>i.e. [0,1,2]U[1,2,3] = [0,1,2,3], [1,1,1,2]U[1,1,2,3] = [1,2,3].
     *          <p>Output is a set.
     * @apiNote Custom method based on the C# function {@code IEnumerable<T>.Union()}.
     */
    public AugList<T> setUnion(AugList<T> SetB) {
        AugList<T> ret = new AugList<T>() {};
        ret.addAll(this.distinctCopy());
        ret.addAll(SetB.distinctCopy().filterSelf(e -> !ret.contains(e)));
        //ret.filterSelf(x -> ret.allIndicesOf(x).size() == 1); // Removes duplicates
        return ret;
    }

    /**
     * @return  A new AugList which is a shuffled copy of this AugList.
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
     * Skips elements until it finds the first element to fail the given {@code predicate}, then returns all remaining elements.
     * @param   predicate
     *          The predicate that elements are compared against.
     * @return  Elements beyond and including the first to fail the given {@code predicate}.
     * @apiNote Custom method based on the C# method {IEnumerable<T>.skipWhile()}.
     */
    public AugList<T> skipWhile(Predicate<? super T> predicate) {
        AugList<T> ret = new AugList<T>() {};
        for (int i = 0; i < ls.size(); i++) {
            if (!predicate.test(ls.get(i))) {
                for (int j = i; j < ls.size(); j++) {
                    ret.add(ls.get(j));
                }
                return ret;
            }
        }
        return ret;
    }

    /**
     * @return  The size of this AugList.
     * @apiNote Encapsulates {@code ArrayList<T>.size()}.
     */
    public int size() {
        return ls.size();
    }

    /**
     * Sorts this AugList according to the given Comparator.
     * @param   c
     *          The comparator to sort by.
     * @return  This AugList, sorted according to the given Comparator.
     * @apiNote Pseudo-overrides {@code ArrayList<T>.sort()}, returns {@code this} rather than void.
     */
    public AugList<T> sort(Comparator<? super T> c) {
        ls.sort(c);
        return this;
    }

    /**
     * @return  A spliterator over this AugList, which can traverse and split this AugList up.
     * @apiNote Encapsulates {@code ArrayList<T>.spliterator()}.
     */
    public Spliterator<T> spliterator() {
        return ls.spliterator();
    }

    /**
     * @return  A stream with the elements of this AugList.
     * @apiNote Encapsulates {@code Collection<T>.stream()}.
     */
    public Stream<T> stream() {
        return ls.stream();
    }

    // Works slightly differently to ArrayList<T>.subList().
    /**
     * (Augmented ArrayList<T>.subList())
     * Returns a new AugList with exactly the elements between the provided indices, incl-exclusive
     * @param   fromIndex
     *          The start index of the sublist, inclusive.
     * @param   toIndex
     *          The end index of the sublist, exclusive.
     * @return  A new AugList with the specified elements.
     * @throws  IllegalArgumentException
     *          If {@code fromIndex > toIndex}.
     * @throws  IndexOutOfBoundsException
     *          One or both indices are out of array bounds.
     * @apiNote Pseudo-overrides {@code ArrayList<T>.subList()}, returning a {@code AugList<T>} rather than a {@code List<T>}.
     */
    public AugList<T> subList(int fromIndex, int toIndex) {
        return new AugList<T>(ls.subList(fromIndex, toIndex));
    }

    /**
     * Produces a sublist from the given index to the end of the list.
     * @param   fromIndex
     *          The index to start from to the end of the list.
     * @return  A sublist from the {@code fromIndex}
     * @throws  IndexOutOfBoundsException
     *          {@code fromIndex > 0 || fromIndex < ls.size() + 1}
     * @apiNote Custom method that, to my knowledge, does not have a C# or java equivalent.
     */
    public AugList<T> subListToEnd(int fromIndex) {
        return subList(fromIndex, ls.size());
    }

    /**
     * Alters this AugList by swapping the given items.
     * @param   index1
     *          Index of the first item to swap.
     * @param   index2
     *          Index of the second item to swap.
     * @throws  IndexOutOfBoundsException
     *          If {@code Index1 < 0 || Index1 >= this.size() || Index2 < 0 || Index2 >= this.size()}
     * @return  This AugList, with the given elements swapped.
     * @apiNote Custom method that I would like to believe has been implemented somewhere in Java or C#, but I am unable to find any such method in my limited search.
     */
    public AugList<T> swap(int index1, int index2) {
        T temp = ls.get(index1);
        ls.set(index1, ls.get(index2));
        ls.set(index2, temp);
        return this;
    }

    /**
     * Takes elements up and until it finds the first element to fail the given {@code predicate}.
     * @param   predicate
     *          The predicate that elements are compared against.
     * @return  Elements up to and including the first to fail the given {@code predicate}.
     * @apiNote Custom method based on the C# method {IEnumerable<T>.TakeWhile()}.
     */
    public AugList<T> takeWhile(Predicate<? super T> predicate) {
        AugList<T> ret = new AugList<T>() {};
        for (int i = 0; i < ls.size(); i++) {
            ret.add(ls.get(i));
            if (!predicate.test(ls.get(i))) {
                return ret;
            }
        }
        return ret;
    }

    // ArrayList<T>.toArray() without arguments is for all practical purposes useless
    // (as an array of Objects is difficult to parse in any meaningful way), so is not implemented.
    // That being said, if I could instantiate an array of T (T[]), then I would encapsulate and override the toArray() method.

    /**
     * Create an array copy of this AugList with the specified output type.
     * @param   arrType
     *          An array of type {@code T}.
     * @return  An array copy of this AugList.
     * @throws  ArrayStoreException
     *          The type at runtime of the specified array is not a supertype of the type of every element
     * @throws  NullPointerException
     *          The specified array is null
     * @apiNote Encapsulates {@code ArrayList<T>.toArray(T[])}.
     */
    public T[] toArray(T[] arrType) {
        return ls.toArray(arrType);
    }

    // Returns this AugList as a string. Example outputs: "/", "[1,2,3,4]", "[a,b,c,d]"
    @Override
    public String toString() {
        String ListAsString = "[";
        if (ls.size() == 0) {
            return "/";
        }
        for (T e : ls) {
            ListAsString += e.toString() + ", ";
        }
        return ListAsString.substring(0, ListAsString.length() - 2) + "]";
    }

    // ArrayList<T>.trimToSize() has no meaningful impact on the state of the list, so is not implemented.
    // Object.wait() and its parameterized overloads cannot be overridden and so are not implemented.

    /**
     * Attempts to remove {@code o} from this AugList.
     * @param   o
     *          The object to remove if present.
     * @return  {@code this}.
     * @apiNote Stream-focused alternative to {@code this.remove(Object)}.
     */
    public AugList<T> without(Object o) {
        ls.remove(o);
        return this;
    }

    /**
     * Removes all items in {@code pAugList}, if present, from this AugList.
     * <p>For a non-destructive method, use {@code ListDifference()}.
     * @param   pAugList
     *          The list of items to remove from this AugList.
     * @return  {@code this}.
     * @apiNote Stream-focused alternative to {@code this.removeAll(AugList<T>)}.
     */
    public AugList<T> withoutAll(AugList<T> pAugList) {
        for (int i = 0; i < pAugList.size(); i++) {
            if (ls.contains(pAugList.get(i))) {
                ls.remove(pAugList.get(i));
            }
        }
        return this;
    }

    /**
     * Removes all items in {@code varargs}, if present, from this AugList.
     * <p>For a non-destructive method, use {@code ListDifference()}.
     * @param   varargs
     *          The list of items to remove from this AugList.
     * @return  {@code this}.
     * @apiNote Stream-focused alternative to {@code this.removeAll(Collection<c?>)}.
     */
    @SafeVarargs
    public final AugList<T> withoutAll(T... varargs) {
        for (int i = 0; i < varargs.length; i++) {
            if (ls.contains(varargs[i])) {
                ls.remove(varargs[i]);
            }
        }
        return this;
    }

    // Works the same as a pop operation from a stack, except can pop any element rather than the top element.
    /**
     * Removes the item at the given {@code index}, and shifts indices as necessary.
     * @param   index
     *          The index of the target item to remove.
     * @return  {@code this}.
     * @throws  IndexOutOfBoundsException
     *          The index is not in array bounds.
     * @apiNote Stream-focused alternative to {@code this.removeAt(int)}.
     */
    public AugList<T> withoutIndex(int index) {
        ls.remove(index);
        return this;
    }

    // this.withoutFirst() would be redundant because of this.without(0) so is not implemented

    /**
     * Removes the tail from this list.
     * @return  {@code this}
     * @throws  NoSuchElementException
     *          {@code this.size() == 0}
     * @apiNote Stream-based alternative to {@code this.removeLast()}.
     */
    public AugList<T> withoutLast() {
        ls.removeLast();
        return this;
    }

    /**
     * Removes all items in this list that satisfy the given {@code filter}.
     * @param   filter
     *          The condition that, if an element passes it, causes its deletion.
     * @return  {@code this}
     * @apiNote Stream-focused alternative to {@code this.removeIf(Predicate<? super T>)}.
     */
    public AugList<T> withoutWhere(Predicate<? super T> filter) {
        ls.removeIf(filter);
        return this;
    }
}