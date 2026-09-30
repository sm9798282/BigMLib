package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.IndexOutOfBoundsException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
//import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
//import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
//import java.util.SortedSet;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.TreeSet;
import java.util.Vector;
import src.AugList;

/**
 * A JUnit 3.x powered automatic tester for {@link src.AugList}.
 * @see     src.AugList
 * @since   AugList V1
 */
public final class AugListTest implements MultiTest {

    private AugList<Double> testDataDouble;
    private AugList<String> testDataString;
    private AugList<Integer> testDataInt;
    AugList<Double> nullAL = null;
    Double[] nullArr = null;
    Double[] emptyArr = new Double[0];

    final ArrayList<Double> ARRLISTDOUBLE = new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926));
    final ArrayList<String> ARRLISTSTR = new ArrayList<String>(Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy dog"));
    final ArrayList<Integer> ARRLISTINT = new ArrayList<Integer>(Arrays.asList(7, 11, 19, -24, 117, 145, -56, 43));

    /**
     * @since   AugList V1
     */
    @Override
    public void setupTestData() {
        testDataString = new AugList<String>("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy dog");
        testDataDouble = new AugList<Double>(1.0, 2.0, 7.11, -2.5, 3.1415926);
        testDataInt = new AugList<Integer>(7, 11, 19, -24, 117, 145, -56, 43);
        // As strings, the test data is:
        // [1.0, 2.0, 7.11, -2.5, 3.1415926]
        // [The, quick, brown, fox, jumps, over, the, lazy dog]
        // [7, 11, 19, -24, 117, 145, -56, 43]
    }

    /**
     * @since   AugList V1
     */
    @Test
    @Override
    public void allTests() {
        setupTestData();
        testAdd();
        testAddAll();
        testAddAllVarargs();
        testAddFirst();
        testAllIndicesOf();
        testAllSatisfy();
        testAnySatisfy();
        testApplyAll();
        testChunk();
        testClear();
        testClone();
        testContains();
        testContainsAll();
        testContainsAllVarargs();
        testContainsAny();
        testContainsAnyVarargs();
        testCountOf();
        testCountsOfElements();
        testCrossProduct();
        testDistinctCopy();
        testDistinctSelf();
        testFilterCopy();
        testFilterSelf();
        testForEach();
        testFragment();
        testGet();
        testGetLast();
        testGetRandom();
        testHashCode();
        testIndexOf();
        testInsert();
        testInsertAll();
        testInsertAllVarargs();
        testInsertAllAtRandom();
        testInsertAllAtRandomVarargs();
        testInsertAtRandom();
        testInstantiateBlank();
        testInstantiateEnumeration();
        testInstantiateIterable();
        testInstantiateIterator();
        testInstantiateListIterator();
        testInstantiatePairs();
        testInstantiateSpliterator();
        testInstantiateStream();
        testInstantiateVarargs();
        testIsEmpty();
        testIsEquivalent();
        testIsPalindrome();
        testIsRearrangement();
        testIsSet();
        testIsSorted();
        testIterator();
        testLastIndexOf();
        testListDifference();
        testListIntersection();
        testListIterator();
        testListUnion();
        testListIteratorFromIndex();
        testMassOverwriteRandom();
        testMassOverwriteRandomVarargs();
        testOneToOneMap();
        testOverwriteRandom();
        testPairUp();
        testParallelStream();
        testParameterizedTypeDesc();
        testRemove();
        testRemoveAll();
        testRemoveAllVarargs();
        testRemoveAtIndex();
        testRemoveIf();
        testRemoveLast();
        testRemoveRandom();
        testRetainAll();
        testReversed();
        testSample();
        testSet();
        testSetDifference();
        testSetFromCallable();
        testSetMany();
        testSetIntersection();
        testSetUnion();
        testShuffleCopy();
        testShuffleSelf();
        testSize();
        testSkipWhile();
        testSort();
        testSplit3();
        testSplitDelim();
        testSpliterator();
        testStream();
        testSubList();
        testSwap();
        testSwapRandom();
        testSwapRandomNoParam();
        testSwapRanges();
        testTakeWhile();
        testToArrayGivenType();
        testToCollection();
        testToEnumeration();
        testToString();
        testWithout();
        testWithoutAll();
        testWithoutAllVarargs();
        testWithoutIndex();
        testWithoutLast();
        testWithoutRandom();
        testWithoutWhere();
        //testEnsureCapacity();
        //testSubListToEnd();
        //testToArrayGivenGenerator();
        //testTrimToSize();
    }

    /**
     * JUnit tester for Single Append
     * @since   AugList V1
     * @see     src.AugList#add(Object)
     */
    @Test
    public void testAdd() { 
        setupTestData();
        // Check append behaviour
        testDataDouble.add(7.0);
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0]"));
        // Check return type (and parameterized type)
        assertTrue(testDataString.add("!").getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataString.parameterizedTypeDesc().equals("public final class java.lang.String"));
        assertTrue(testDataString.isEquivalent("[The, quick, brown, fox, jumps, over, the, lazy dog, !]"));
        // Check functional paradigm style program is plausible
        assertTrue(testDataInt.add(25).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43, 25]"));
        // Check nulls
        assertTrue(Objects.isNull((testDataDouble.add(null)).get(testDataDouble.size() - 1)));
    }

    /**
     * JUnit tester for Bulk Append
     * @since   AugList V1
     * @see     src.AugList#addAll(AugList)
     */
    @Test
    public void testAddAll() {
        setupTestData();
        // Test Bulk Append behaviour
        testDataDouble.addAll(new AugList<Double>(7.0));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0]"));
        // Check functional paradigm
        assertTrue(testDataString.addAll(new AugList<String>("!", "qwerty")).isEquivalent("[The, quick, brown, fox, jumps, over, the, lazy dog, !, qwerty]"));
        // Check return type (and parameterized type)
        assertTrue(testDataInt.addAll(new AugList<Integer>(25, 125, 625)).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataInt.parameterizedTypeDesc().equals("public final class java.lang.Integer"));
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43, 25, 125, 625]"));
        // Check nulls
        Integer[] nullArr = null;
        assertTrue(testDataInt.addAll(nullArr).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43, 25, 125, 625]"));
    }

    /**
     * JUnit tester for Bulk Varargs Append
     * @since   AugList V1
     * @see     src.AugList#addAll(Object...) addAll(T...)
     */
    @Test
    public void testAddAllVarargs() {
        setupTestData();
        // Test Bulk Append
        testDataDouble.addAll(7.0);
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0]"));
        // Check return type
        assertTrue(testDataString.addAll("!", "qwerty").getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataString.parameterizedTypeDesc().equals("public final class java.lang.String"));
        assertTrue(testDataString.isEquivalent("[The, quick, brown, fox, jumps, over, the, lazy dog, !, qwerty]"));
        // Check functional paradigm
        assertTrue(testDataInt.addAll(25, 125, 625).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43, 25, 125, 625]"));
        // Test nulls
        Iterable<Integer> nullIterable = null;
        assertTrue(testDataInt.addAll(nullIterable).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43, 25, 125, 625]"));
    }

    /**
     * JUnit tester for Single Prepend
     * @since   AugList V1
     * @see     src.AugList#addFirst()
     */
    @Test
    public void testAddFirst() {
        setupTestData();
        testDataDouble.addFirst(7.0);
        assertTrue(testDataDouble.getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.isEquivalent("[7.0, 1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.addFirst("!").getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataString.isEquivalent("[!, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataInt.addFirst(25).isEquivalent("[25, 7, 11, 19, -24, 117, 145, -56, 43]"));
        // Check nulls
        assertTrue(Objects.isNull((testDataDouble.addFirst(null)).get(0)));
    }

    /**
     * JUnit tester for finding all indices of a given object
     * @since   AugList V1
     * @see     src.AugList#allIndicesOf(Object)
     */
    @Test
    public void testAllIndicesOf() {
        setupTestData();
        assertTrue(testDataDouble.allIndicesOf(-2.0).size() == 0);
        assertTrue(testDataDouble.allIndicesOf(-2.0).getClass().toGenericString().equals("public class src.AugList<T>"));
        //testDataStr.oneToOneMap(s -> s.toLowerCase()).forEach(System.out::println);
        //System.out.println(testDataString.oneToOneMap(s -> s.toLowerCase()).allIndicesOf("the"));
        assertTrue(testDataString.oneToOneMapCopy(s -> s.toLowerCase()).allIndicesOf("the").size() == 2);
        assertTrue(testDataString.allIndicesOf("the").parameterizedTypeDesc().equals("public final class java.lang.Integer"));
        assertTrue(testDataInt.add(7).allIndicesOf(7).isEquivalent("[0, 8]"));
        assertTrue(new AugList<Double>(null, null, 0.0).allIndicesOf(null).isEquivalent("[0, 1]"));
    }

    /**
     * JUnit tester for Mass Satisfaction (∀, All) 
     * @since   AugList V1
     * @see     src.AugList#allSatisfy(java.util.function.Predicate)
     */
    @Test
    public void testAllSatisfy() {
        setupTestData();
        // As boolean is a primitive type, there is no way to test for the return type.
        assertTrue(testDataDouble.allSatisfy(d -> d > -3.0));
        assertFalse(testDataDouble.allSatisfy(d -> d > -2.0));
        assertTrue(testDataString.allSatisfy(s -> s.length() != 0));
        assertFalse(testDataString.allSatisfy(s -> s.length() == 1));
        assertTrue(testDataInt.allSatisfy(i -> -60 < i && i < 150));
        assertFalse(testDataInt.allSatisfy(i -> 0 < i && i < 100));
        assertFalse(testDataDouble.allSatisfy(null));
    }

    /**
     * JUnit tester for Existence (∃, Exists)
     * @since   AugList V1
     * @see     src.AugList#anySatisfy(java.util.function.Predicate)
     */
    @Test
    public void testAnySatisfy() {
        setupTestData();
        // As boolean is a primitive type, there is no way to test for the return type.
        assertTrue(testDataDouble.anySatisfy(d -> d == -2.5));
        assertFalse(testDataDouble.anySatisfy(d -> 0 < d && d < 0.5));
        assertTrue(testDataString.anySatisfy(s -> s.length() == 4));
        assertFalse(testDataString.anySatisfy(s -> s.length() > 100));
        assertTrue(testDataInt.anySatisfy(i -> 3 < i && i < 8));
        assertFalse(testDataInt.anySatisfy(i -> 4 < i && i < 6));
        assertFalse(testDataDouble.anySatisfy(null));
    }

    /**
     * JUnit tester for Bulk Function application
     * @since   AugList V1
     * @see     src.AugList#applyAll(java.util.function.UnaryOperator)
     */
    @Test
    public void testApplyAll() {
        setupTestData();
        AugList<Double> mappedALD = testDataDouble.applyAll(d -> d + 1);
        assertTrue(mappedALD.getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(mappedALD.parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(mappedALD.isEquivalent("[2.0, 3.0, 8.11, -1.5, 4.1415926]"));
        assertTrue(testDataString.applyAll(s -> s.substring(0, 1)).isEquivalent("[T, q, b, f, j, o, t, l]"));
        assertTrue(testDataInt.applyAll(i -> 2 * i).isEquivalent("[14, 22, 38, -48, 234, 290, -112, 86]"));
        assertTrue(testDataDouble.applyAll(null).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for List Subdivision
     * @since   AugList V1
     * @see     src.AugList#chunk(int)
     */
    @Test
    public void testChunk() {
        setupTestData();
        assertTrue(testDataDouble.chunk(3).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.chunk(3).parameterizedTypeDesc().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.chunk(3).get(0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.chunk(3).isEquivalent("[[1.0, 2.0, 7.11], [-2.5, 3.1415926]]"));
        assertTrue(testDataString.chunk(3).isEquivalent("[[The, quick, brown], [fox, jumps, over], [the, lazy dog]]"));
        assertTrue(testDataInt.chunk(1).isEquivalent("[[7], [11], [19], [-24], [117], [145], [-56], [43]]"));
        assertThrows(IllegalArgumentException.class, 
            () -> { 
                testDataDouble.chunk(0);
            }
        );
    }

    /**
     * JUnit tester for Emptying
     * @since   AugList V1
     * @see     src.AugList#clear()
     */
    @Test
    public void testClear() {
        setupTestData();
        testDataDouble.clear();
        assertTrue(testDataDouble.size() == 0);
        assertTrue(testDataString.clear().isEmpty());
        assertTrue(testDataInt.clear().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataInt.parameterizedTypeDesc().equals("∅"));
        assertTrue(testDataInt.size() == 0);
    }

    /**
     * JUnit tester for Cloning
     * @since   AugList V1
     * @see     src.AugList#clone()
     */
    @Test
    public void testClone() {
        setupTestData();
        assertTrue(testDataDouble != testDataDouble.clone());
        assertTrue(testDataDouble.clone().isEquivalent(testDataDouble));
        assertTrue(testDataDouble.getClass() == (testDataDouble.clone().getClass()));
        // assertNotEquals checks for both contents and memory locations being identical to throw (which by use of clone() will never be true.)
        assertTrue(testDataDouble.clone().oneToOneMapCopy(x -> x).isEquivalent(testDataDouble));
        assertTrue(testDataString.clone().distinctCopy().isEquivalent(testDataString.distinctCopy()));
        assertTrue(testDataInt.clone().filterCopy(x -> x != 100).isEquivalent(testDataInt));
        assertNotSame(testDataDouble, testDataDouble.clone());
        assertNotSame(testDataString, testDataString.clone());
        assertNotSame(testDataInt, testDataInt.clone());
        assertTrue(testDataDouble.parameterizedTypeDesc().equals(testDataDouble.clone().parameterizedTypeDesc()));
    }

    /**
     * JUnit tester for Single Containment (a ∈ A)
     * @since   AugList V1
     * @see     src.AugList#contains(Object)
     */
    @Test
    public void testContains() {
        setupTestData();
        // Return type is primitive so cannot enforce it with testing
        assertTrue(testDataDouble.contains(3.1415926));
        assertFalse(testDataDouble.contains(99999.9));
        assertTrue(testDataString.contains("fox"));
        assertFalse(testDataString.contains("Lizard"));
        assertTrue(testDataInt.contains(11));
        assertFalse(testDataInt.contains(17));
        assertFalse(testDataString.contains(null));
        assertTrue(new AugList<Character>(' ', null).contains(null));
    }

    /**
     * JUnit tester for Mass Containment (A ⊆ B)
     * @since   AugList V1
     * @see     src.AugList#containsAll(AugList)
     */
    @Test
    public void testContainsAll() {
        setupTestData();
        // Return type is primitive so cannot enforce it with testing
        assertTrue(testDataDouble.containsAll(new AugList<Double>(3.1415926d)));
        assertTrue(testDataDouble.containsAll(new AugList<Double>(-2.5, 3.1415926d)));
        assertTrue(testDataDouble.containsAll(new AugList<Double>(3.1415926d, -2.5))); // Test reversed order
        assertFalse(testDataDouble.containsAll(new AugList<Double>(99999.9)));
        assertFalse(testDataDouble.containsAll(new AugList<Double>(-2.5, 99999.9)));
        assertTrue(testDataString.containsAll(new AugList<String>("fox")));
        assertFalse(testDataString.containsAll(new AugList<String>("Lizard")));
        assertTrue(testDataInt.containsAll(new AugList<Integer> (7, 11)));
        assertFalse(testDataInt.containsAll(new AugList<Integer>(7, 11, 17)));
        assertTrue(testDataDouble.containsAll(nullAL));
        assertTrue(new AugList<Double>(null, 1.0).containsAll(nullAL));
        assertTrue(new AugList<Double>(null, 1.0).containsAll(new AugList<Double>(null, 1.0)));
        assertFalse(new AugList<Double>(null, null, 1.0).containsAll(new AugList<Double>(null, null, null, null, null)));
        assertTrue(new AugList<Double>(1.0, null, 1.0).containsAll(new AugList<Double>(null, 1.0)));
    }

    /**
     * JUnit tester for Varargs Mass Containment (A ⊆ B)
     * @since   AugList V1
     * @see     src.AugList#containsAll(Object...) containsAll(T...)
     */
    @Test
    public void testContainsAllVarargs() {
        setupTestData();
        // Return type is primitive so cannot enforce it with testing
        assertTrue(testDataDouble.containsAll(3.1415926d));
        assertTrue(testDataDouble.containsAll(2.0, 3.1415926d));
        assertTrue(testDataDouble.containsAll(3.1415926d, -2.5)); // Test reversed order
        assertFalse(testDataDouble.containsAll(99999.9));
        assertFalse(testDataDouble.containsAll(-2.5, 99999.9));
        assertTrue(testDataString.containsAll("fox"));
        assertFalse(testDataString.containsAll("Lizard"));
        assertTrue(testDataInt.containsAll(7, 11));
        assertFalse(testDataInt.containsAll(7, 11, 17));
        assertTrue(testDataDouble.containsAll(nullArr));
        assertTrue(new AugList<Double>(null, 1.0).containsAll(nullArr));
        assertTrue(new AugList<Double>(null, 1.0).containsAll(null, 1.0));
    }

    /**
     * JUnit tester for Any Contains (∃a ∈ A: a ∈ B)
     * @since   AugList V1
     * @see     src.AugList#containsAny(AugList)
     */
    @Test
    public void testContainsAny() {
        setupTestData();
        // Return type is primitive so cannot enforce it with testing
        assertTrue(testDataDouble.containsAny(new AugList<Double>(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0)));
        assertTrue(testDataString.containsAny(new AugList<String>("", "A", "an", "the", "this", "that", "there")));
        assertTrue(testDataInt.containsAny(new AugList<Integer>(2, 3, 5, 7, 11, 13, 17, 19)));
        assertFalse(testDataDouble.containsAny(new AugList<Double>()));
        assertTrue(new AugList<Double>(null, null, 1.0).containsAny(new AugList<Double>(null, null, null, null, null)));
    }

    /**
     * JUnit tester for Varargs Any Contains (∃a ∈ A: a ∈ B)
     * @since   AugList V1
     * @see     src.AugList#containsAny(Object...) containsAny(T...)
     */
    @Test
    public void testContainsAnyVarargs() {
        setupTestData();
        // Return type is primitive so cannot enforce it with testing
        assertTrue(testDataDouble.containsAny(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0));
        assertTrue(testDataString.containsAny("", "A", "an", "the", "this", "that", "there"));
        assertTrue(testDataInt.containsAny(2, 3, 5, 7, 11, 13, 17, 19));
        assertFalse(testDataDouble.containsAny());
    }

    /**
     * JUnit tester for Individual Frequency
     * @since   AugList V2
     * @see     src.AugList#countOf()
     */
    @Test
    public void testCountOf() {
        setupTestData();
        assertTrue(testDataDouble.countOf(null) == 0);
        assertTrue(testDataDouble.countOf(-12.0) == 0);
        assertTrue(testDataDouble.countOf(7.11) == 1);
        assertTrue(new AugList<Double>(null, null, 1.0).countOf(null) == 2);
        assertTrue(new AugList<Double>(new AugList<Double>(7.0), new AugList<Integer>(11)).countOf(7.0) == 11);
    }

    /**
     * JUnit tester for Frequency
     * @since   AugList V1
     * @see     src.AugList#countsOfElements()
     */
    @Test
    public void testCountsOfElements() {
        setupTestData();
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.countsOfElements().get("the") == 2);
        assertTrue(testDataString.countsOfElements().get("THE") == null);
        testDataDouble.addAll(7.0, 11.0, -2.5, 7.11, 7.11);
        assertTrue(testDataDouble.countsOfElements().get(7.11) == 3);
        assertTrue(testDataDouble.countsOfElements().get(-2.5) == 2);
        assertTrue(testDataDouble.countsOfElements().get(7.0) == 1);
        assertTrue(testDataDouble.countsOfElements().get(12.0) == null);
        String classStr = testDataDouble.toCollection().getClass().toGenericString();      
        assertTrue(classStr.contains("class src.AugList$"));
        AugList<Character> csAL = new AugList<Character>();
        for (int i = 0; i < classStr.toCharArray().length; i++) {
            csAL.add(classStr.toCharArray()[i]);
        }
        csAL.removeIf(ch -> (ch + "").toUpperCase() != (ch + "").toLowerCase());
        csAL.forEach(ch -> {
            try {
                Integer.valueOf((ch + ""));
            } catch (Exception e) {
                System.out.println(ch);
                System.out.println(csAL);
                fail();
            }
        });
        assertTrue(new AugList<Double>(1.0, null).countsOfElements().size() == 1);
    }

    /**
     * JUnit tester for Cross Product
     * @since   AugList V2
     * @see     src.AugList#crossProduct(Iterable)
     */
    @Test
    public void testCrossProduct() {
        setupTestData();
        assertTrue(new AugList<Double>().crossProduct(testDataDouble).size() == 0);
        assertTrue(testDataDouble.crossProduct(new AugList<Double>()).size() == testDataDouble.size());
        assertTrue(testDataDouble.crossProduct(nullAL).size() == testDataDouble.size());
        assertTrue(testDataDouble.crossProduct(testDataInt).isEquivalent("[[1.0=7, 1.0=11, 1.0=19, 1.0=-24, 1.0=117, 1.0=145, 1.0=-56, 1.0=43], [2.0=7, 2.0=11, 2.0=19, 2.0=-24, 2.0=117, 2.0=145, 2.0=-56, 2.0=43], [7.11=7, 7.11=11, 7.11=19, 7.11=-24, 7.11=117, 7.11=145, 7.11=-56, 7.11=43], [-2.5=7, -2.5=11, -2.5=19, -2.5=-24, -2.5=117, -2.5=145, -2.5=-56, -2.5=43], [3.1415926=7, 3.1415926=11, 3.1415926=19, 3.1415926=-24, 3.1415926=117, 3.1415926=145, 3.1415926=-56, 3.1415926=43]]"));
        assertTrue(new AugList<Integer>(1, 2, 3).crossProduct(new AugList<Integer>(4, 5, 6)).isEquivalent("[[1=4, 1=5, 1=6], [2=4, 2=5, 2=6], [3=4, 3=5, 3=6]]"));
    }

    /**
     * JUnit tester for Duplicate Discarding
     * @since   AugList V1
     * @see     src.AugList#distinctCopy()
     */
    @Test
    public void testDistinctCopy() {
        setupTestData();
        testDataDouble.addAll(1.0, 7.2, -9.221, 1.0, 7.11, -2.5, 19.0);
        assertTrue(testDataDouble.distinctCopy().isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.2, -9.221, 19.0]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toUpperCase());
        assertTrue(testDataString.distinctCopy().isEquivalent("[THE, QUICK, BROWN, FOX, JUMPS, OVER, LAZY DOG]"));
        testDataInt.addAll(new AugList<Integer>(7, 8, 11, 19, 117, 119, 145, 145));
        assertTrue(testDataInt.distinctCopy().isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43, 8, 119]"));
        assertNotSame(testDataInt, testDataInt.distinctCopy());
        assertTrue(testDataInt.distinctCopy().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataInt.distinctCopy().parameterizedTypeDesc().equals("public final class java.lang.Integer"));
    }

    /**
     * JUnit tester for Duplicate Discarding
     * @since   AugList V1
     * @see     src.AugList#distinctSelf()
     */
    @Test
    public void testDistinctSelf() {
        setupTestData();
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        final AugList<String> LOWERSTRCLONE = testDataString.clone();
        assertTrue(LOWERSTRCLONE.isEquivalent(testDataString));
        assertFalse(LOWERSTRCLONE.isEquivalent(testDataString.distinctSelf()));
        // distinctSelf() modifies the instance so the equality breaks.
        assertFalse(LOWERSTRCLONE.isEquivalent(testDataString));
        assertTrue(testDataString.isEquivalent("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataInt.distinctSelf().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.distinctSelf().parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for filtering
     * @since   AugList V1
     * @see     src.AugList#filterCopy(java.util.function.Predicate)
     */
    @Test
    public void testFilterCopy() {
        setupTestData();
        assertTrue(testDataDouble.filterCopy(d -> d != 0.023).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataInt.filterCopy(i -> i != 99999).parameterizedTypeDesc().equals("public final class java.lang.Integer"));
        final AugList<Double> TESTDATADOUBLECOPY = testDataDouble.clone();
        final AugList<String> TESTDATASTRCOPY = testDataString.clone();
        final AugList<Integer> TESTDATAINTCOPY = testDataInt.clone();
        assertTrue(testDataDouble.filterCopy(d -> d > 2.5).isEquivalent("[7.11, 3.1415926]"));
        assertTrue(testDataString.filterCopy(s -> s.length() == 3).isEquivalent("[The, fox, the]"));
        assertTrue(testDataInt.filterCopy(i -> i % 2 == 0).isEquivalent("[-24, -56]"));
        assertTrue(testDataDouble.isEquivalent(TESTDATADOUBLECOPY));
        assertTrue(testDataString.isEquivalent(TESTDATASTRCOPY));
        assertTrue(testDataInt.isEquivalent(TESTDATAINTCOPY));
        assertTrue(testDataInt.filterCopy(null).isEquivalent("/"));
    }

    /**
     * JUnit tester for filtering
     * @since   AugList V1
     * @see     src.AugList#filterSelf(java.util.function.Predicate)
     */
    @Test
    public void testFilterSelf() {
        setupTestData();
        final AugList<Double> TESTDATADOUBLECOPY = testDataDouble.clone();
        final AugList<String> TESTDATASTRCOPY = testDataString.clone();
        final AugList<Integer> TESTDATAINTCOPY = testDataInt.clone();
        assertTrue(testDataDouble.filterSelf(d -> d > 2.5).isEquivalent("[7.11, 3.1415926]"));
        assertTrue(testDataString.filterSelf(s -> s.length() == 3).isEquivalent("[The, fox, the]"));
        assertTrue(testDataInt.filterSelf(i -> i % 2 == 0).isEquivalent("[-24, -56]"));
        assertFalse(testDataDouble.isEquivalent(TESTDATADOUBLECOPY));
        assertFalse(testDataString.isEquivalent(TESTDATASTRCOPY));
        assertFalse(testDataInt.isEquivalent(TESTDATAINTCOPY));
        assertTrue(testDataDouble.filterSelf(d -> d != 0.023).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataInt.filterSelf(i -> i != 99999).parameterizedTypeDesc().equals("public final class java.lang.Integer"));
        assertTrue(testDataInt.filterSelf(null).isEquivalent("/"));
    }

    /**
     * JUnit tester for function application
     * @since   AugList V1
     * @see     src.AugList#forEach(java.util.function.Consumer)
     */
    @Test
    public void testForEach() {
        setupTestData();
        // Since the return type is void, there is no way to automatically check the test works.
        // (Similarly, there is no way to confirm the return type)
        // Check the Debug Console and confirm the output matches the following:
        /**
         * 1.0
         * 2.0
         * 7.11
         * -2.5
         * 3.1415926
         * The
         * quick
         * brown
         * fox
         * jumps
         * over
         * the
         * lazy dog
         * 7
         * 11
         * 19
         * -24
         * 117
         * 145
         * -56
         * 43
         */
        testDataDouble.forEach(System.out::println);
        testDataString.forEach(System.out::println);
        testDataInt.forEach(System.out::println);
        testDataInt.forEach(null);
    }

    /**
     * JUnit tester for Random List Subdivision
     * @since   AugList V2
     * @see     src.AugList#fragment()
     */
    @Test
    public void testFragment() {
        setupTestData();
        AugList<AugList<Double>> frag = testDataDouble.fragment();
        int currFList = 0, currFListIndex = 0;
        for (int i = 0; i < testDataDouble.size(); i++) {
            assertTrue(frag.get(currFList).get(currFListIndex) == testDataDouble.get(i));
            currFListIndex++;
            assertTrue(frag.get(currFList).size() != 0 && frag.get(currFList).size() != testDataDouble.size());
            if (frag.get(currFList).size() <= currFListIndex) {
                currFList++;
                currFListIndex = 0;
            }
        }
        assertTrue(currFList <= testDataDouble.size());
        // To trigger fragment's special case branch, making 100 calls without generating exceptions should be sufficient.
        for (int i = 0; i < 100; i++) {
            try {
                testDataDouble.fragment();
            } catch (Exception e) {
                fail();
            }
        }
        assertTrue(frag.getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(frag.parameterizedTypeDesc().equals("public class src.AugList<T>"));
        assertTrue(frag.get(0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Reading/Loading
     * @since   AugList V1
     * @see     src.AugList#get(int)
     */
    @Test
    public void testGet() {
        setupTestData();
        assertTrue(testDataDouble.get(0).getClass().toGenericString().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.get(0) == 1.0);
        assertTrue(testDataDouble.get(2) == 7.11);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testDataDouble.get(1000);
        });
        assertTrue(testDataString.get(0) == "The");
        assertTrue(testDataString.get(3) == "fox");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testDataString.get(-1);
        });
        assertTrue(testDataInt.get(0) == 7);
        assertTrue(testDataInt.get(1) == 11);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testDataInt.get(-711);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new AugList<Character>().get(0);
        });
    }

    /**
     * JUnit tester for Reading/Loading the Tail
     * @since   AugList V1
     * @see     src.AugList#getLast(int)
     */
    @Test
    public void testGetLast() {
        setupTestData();
        assertTrue(testDataDouble.getLast().getClass().toGenericString().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.getLast() == 3.1415926);
        assertTrue(testDataString.getLast() == "lazy dog");
        assertTrue(testDataInt.getLast() == 43);
        assertThrows(NoSuchElementException.class, () -> {
            new AugList<Integer>().getLast();
        });
    }

    /**
     * JUnit tester for Reading/Loading at Random
     * @since   AugList V2
     * @see     src.AugList#getRandom()
     */
    @Test
    public void testGetRandom() {
        setupTestData();
        assertTrue(testDataDouble.getRandom().getClass().toGenericString().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.contains(testDataDouble.getRandom()));
        assertTrue(testDataInt.contains(testDataInt.getRandom()));
        assertTrue(testDataString.contains(testDataString.getRandom()));
        assertThrows(NoSuchElementException.class, () -> {
            new AugList<Integer>().getRandom();
        });
    }

    /**
     * JUnit tester for Hashcodes.
     * @since   AugList V1
     * @see     src.AugList#hashCode()
     */
    @Test
    public void testHashCode() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertTrue(testDataDouble.hashCode() == ARRLISTDOUBLE.hashCode());
        assertTrue(testDataString.hashCode() == ARRLISTSTR.hashCode());
        assertTrue(testDataInt.hashCode() == ARRLISTINT.hashCode());
    }

    /**
     * JUnit tester for Index finding
     * @since   AugList V1
     * @see     src.AugList#indexOf(Object)
     */
    @Test
    public void testIndexOf() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertTrue(testDataDouble.indexOf(1.0) == 0);
        assertTrue(testDataDouble.indexOf(3.1415926) == 4);
        assertTrue(testDataDouble.indexOf(77) == -1);
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.indexOf("the") == 0);
        assertTrue(testDataString.indexOf("jumps") == 4);
        assertTrue(testDataString.indexOf("") == -1);
        assertTrue(testDataInt.indexOf(117) == 4);
        assertTrue(testDataInt.indexOf(43) == 7);
        assertTrue(testDataInt.indexOf(711) == -1);
        assertTrue(testDataInt.indexOf(null) == -1);
        assertTrue(new AugList<Double>(1.0, null, null).indexOf(null) == 1);
    }

    /**
     * JUnit tester for Single Insertion
     * @since   AugList V1
     * @see     src.AugList#insert(int, Object)
     */
    @Test
    public void testInsert() {
        setupTestData();
        testDataDouble.insert(1, 7.0);
        assertTrue(testDataDouble.isEquivalent("[1.0, 7.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataString.insert(0, "!");
        assertTrue(testDataString.isEquivalent("[!, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataInt.insert(2, 25).isEquivalent("[7, 11, 25, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                testDataDouble.insert(1000, 1.0);
            }
        );
        assertTrue(testDataDouble.insert(1, 1.0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.insert(1, 1.0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Bulk Insertion
     * @since   AugList V1
     * @see     src.AugList#insertAll(int, AugList)
     */
    @Test
    public void testInsertAll() {
        setupTestData();
        assertTrue(testDataDouble.insertAll(1, new AugList<Double>(7.0)).isEquivalent("[1.0, 7.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataString.insertAll(0, new AugList<String>("!", "qwerty"));
        assertTrue(testDataString.isEquivalent("[!, qwerty, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        testDataInt.insertAll(2, new AugList<Integer>(25, 125, 625));
        assertTrue(testDataInt.isEquivalent("[7, 11, 25, 125, 625, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                testDataDouble.insertAll(1000, new AugList<Double>(1.0));
            }
        );
        assertTrue(testDataDouble.insertAll(1, new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.insertAll(1, new AugList<Double>()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.insertAll(1, nullAL).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Varargs Bulk Insertion
     * @since   AugList V1
     * @see     src.AugList#insertAll(int, Object...) insertAll(int, T...)
     */
    @Test
    public void testInsertAllVarargs() {
        setupTestData();
        testDataDouble.insertAll(1, 7.0);
        assertTrue(testDataDouble.isEquivalent("[1.0, 7.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataString.insertAll(0, "!", "qwerty");
        assertTrue(testDataString.isEquivalent("[!, qwerty, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        testDataInt.insertAll(2, 25, 125, 625);
        assertTrue(testDataInt.isEquivalent("[7, 11, 25, 125, 625, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                testDataDouble.insertAll(1000, new AugList<Double>(1.0));
            }
        );
        assertTrue(testDataDouble.insertAll(1, 1.0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.insertAll(1, 1.0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.insertAll(1, nullArr).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Random Bulk Insertion
     * @since   AugList V2
     * @see     src.AugList#insertAllAtRandom()
     */
    @Test
    public void testInsertAllAtRandom() {
        setupTestData();
        AugList<Double> expectedALD = testDataDouble.clone().addAll(new AugList<Double>(7.0));
        testDataDouble.insertAllAtRandom(new AugList<Double>(7.0));
        assertTrue(expectedALD.isRearrangement(testDataDouble));
        AugList<String> expectedALS = testDataString.clone().addAll(new AugList<String>("!", "qwerty"));
        testDataString.insertAllAtRandom(new AugList<String>("!", "qwerty"));
        assertTrue(expectedALS.isRearrangement(testDataString));
        AugList<Integer> expectedALI = testDataInt.clone().addAll(new AugList<Integer>(25, 125, 625));
        testDataInt.insertAllAtRandom(new AugList<Integer>(25, 125, 625));
        assertTrue(expectedALI.isRearrangement(testDataInt));
        assertTrue(testDataDouble.insertAllAtRandom(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.insertAllAtRandom(new AugList<Double>()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.insertAllAtRandom(nullAL).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Random Varargs Bulk Insertion
     * @since   AugList V2
     * @see     src.AugList#insertAllAtRandom()
     */
    @Test
    public void testInsertAllAtRandomVarargs() {
        setupTestData();
        AugList<Double> expectedALD = testDataDouble.clone().addAll(new AugList<Double>(7.0));
        testDataDouble.insertAllAtRandom(7.0);
        assertTrue(expectedALD.isRearrangement(testDataDouble));
        AugList<String> expectedALS = testDataString.clone().addAll(new AugList<String>("!", "qwerty"));
        testDataString.insertAllAtRandom("!", "qwerty");
        assertTrue(expectedALS.isRearrangement(testDataString));
        AugList<Integer> expectedALI = testDataInt.clone().addAll(new AugList<Integer>(25, 125, 625));
        testDataInt.insertAllAtRandom(25, 125, 625);
        assertTrue(expectedALI.isRearrangement(testDataInt));
        assertTrue(testDataDouble.insertAllAtRandom(1.0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.insertAllAtRandom(1.0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.insertAllAtRandom(nullArr).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Random Single Insertion
     * @since   AugList V2
     * @see     src.AugList#insertAtRandom()
     */
    @Test
    public void testInsertAtRandom() {
        setupTestData();
        AugList<Double> expectedALD = testDataDouble.clone().add(7.0);
        testDataDouble.insertAtRandom(7.0);
        assertTrue(testDataDouble.isRearrangement(expectedALD));
        AugList<String> expectedALS = testDataString.clone().add("!");
        testDataString.insertAtRandom("!");
        assertTrue(testDataString.isRearrangement(expectedALS));
        AugList<Integer> expectedALI = testDataInt.clone().add(25);
        testDataInt.insertAtRandom(25);
        assertTrue(testDataInt.isRearrangement(expectedALI));
        assertTrue(testDataDouble.insertAtRandom(1.0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.insertAtRandom(1.0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataInt.insertAtRandom(null).contains(null));
    }

    /**
     * JUnit tester for instantiating an empty {@link AugList}.
     * @since   Method since V2, tested since AugList V1
     * @see     src.AugList#AugList()
     */
    @Test
    public void testInstantiateBlank() {
        setupTestData();
        assertTrue(new AugList<String>().size() == 0);
        assertTrue(new AugList<Character>().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Double>().parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for instantiating from an {@link Enumeration}.
     * @since   Method since V2, tested since AugList V1
     * @see     src.AugList#AugList(Enumeration)
     */
    @Test
    public void testInstantiateEnumeration() {
        setupTestData();
        assertTrue(new AugList<String>(testDataString.countsOfElements().keys()).isRearrangement(testDataString));
        Enumeration<Float> en = null;
        assertTrue(new AugList<Float>(en).size() == 0);
        assertTrue(new AugList<Float>(en).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Float>(en).parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for instantiating from an {@link Iterable}.
     * @since   AugList V2
     * @see     src.AugList#AugList(Iterable)
     */
    @Test
    public void testInstantiateIterable() {
        setupTestData();
        // From ? implements List<T> (Which itself implements Iterable<T>)
        assertTrue(new AugList<String>(new ArrayList<String>(Arrays.asList(""))).add("1").get(1).equals("1"));
        // From LinkedList<T> (Which implements Iterable<T>)
        LinkedList<String> l = new LinkedList<String>();
        assertTrue(new AugList<String>(l).isEquivalent("/"));
        // From Vector<T> (Which implements Iterable<T>)
        Vector<String> v = new Vector<String>();
        assertTrue(new AugList<String>(v).isEquivalent("/"));
        // From AugList<T> (Which implements Iterable<T>)
        assertTrue(new AugList<String>(testDataString).isEquivalent(testDataString));
        // From HashSet<T> (and LinkedHashSet<T>) (Which both implement Iterable<T>)
        HashSet<String> h = new HashSet<String>();
        assertTrue(new AugList<String>(h).isEquivalent("/"));
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        assertTrue(new AugList<String>(lhs).isEquivalent("/"));
        h.add("Hello");
        h.add("World");
        h.add("!");
        assertTrue(new AugList<String>(h).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From TreeSet<T> (Which implements Iterable<T>)
        TreeSet<String> t = new TreeSet<String>();
        assertTrue(new AugList<String>(t).isEquivalent("/"));
        t.add("Hello");
        t.add("World");
        t.add("!");
        assertTrue(new AugList<String>(t).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From ? implements Deque<T> (Which implements Iterable<T>)
        ArrayDeque<String> ad = new ArrayDeque<String>();
        assertTrue(new AugList<String>(ad).isEquivalent("/"));
        ad.add("Hello");
        ad.add("World");
        ad.add("!");
        assertTrue(new AugList<String>(ad).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From a PriorityQueue<T> (Which implements Iterable<T>)
        PriorityQueue<String> pq = new PriorityQueue<String>();
        assertTrue(new AugList<String>(pq).isEquivalent("/"));
        pq.add("Hello");
        pq.add("World");
        pq.add("!");
        assertTrue(new AugList<String>(pq).isRearrangement(new AugList<String>("Hello", "World", "!")));
        Iterable<Long> it = null;
        assertTrue(new AugList<Long>(it).size() == 0);
        assertTrue(new AugList<Long>(it).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Long>(it).parameterizedTypeDesc().equals("∅"));
        assertTrue(new AugList<Long>(new AugList<Number>(1, 1, 1)).isEquivalent("[1, 1, 1]"));
    }

    /**
     * JUnit tester for instantiating from an {@link Iterator}.
     * @since   Method since V2, tested since AugList V1
     * @see src.AugList#AugList(Iterator)
     */
    @Test
    public void testInstantiateIterator() {
        setupTestData();
        assertTrue(new AugList<String>(testDataString.iterator()).isEquivalent(testDataString));
        Iterator<Short> it = null;
        assertTrue(new AugList<Short>(it).size() == 0);
        assertTrue(new AugList<Short>(it).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Short>(it).parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for instantiating from an {@link ListIterator}.
     * @since   AugList V2
     * @see src.AugList#AugList(ListIterator)
     */
    @Test
    public void testInstantiateListIterator() {
        setupTestData();
        assertTrue(new AugList<String>(testDataString.listIterator()).isEquivalent(testDataString));
        assertTrue(new AugList<String>(testDataString.listIterator(2)).isEquivalent(testDataString.subList(2, testDataString.size())));
        ListIterator<Byte> li = null;
        assertTrue(new AugList<Byte>(li).size() == 0);
        assertTrue(new AugList<Byte>(li).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Byte>(li).parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for instantiating from Pairs.
     * @since   AugList V2
     * @see     src.AugList#AugList(AugList, AugList)
     */
    @Test
    public void testInstantiatePairs() {
        setupTestData();
        AugList<String> ALS = new AugList<String>(new AugList<String>("a", "b", "cd", "eef"), 
                                                  new AugList<Integer>(1,   3,   4,    2));
        assertTrue(ALS.isEquivalent("[a, b, b, b, cd, cd, cd, cd, eef, eef]"));
        assertTrue(new AugList<String>(new AugList<String>("a", "b", "cd", "eef", "aaaaaaaa"),  new AugList<Integer>(1, 3, 4, 2)     ).isEquivalent("[a, b, b, b, cd, cd, cd, cd, eef, eef]"));
        assertTrue(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"),              new AugList<Integer>(1, 3, 4, 2, 999)).isEquivalent("[a, b, b, b, cd, cd, cd, cd, eef, eef]"));
        assertTrue(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"),              new AugList<Integer>(1, 0, 4, 0)     ).isEquivalent("[a, cd, cd, cd, cd]"                   ));
        assertTrue(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"),              new AugList<Integer>(1, -999, 4, -7) ).isEquivalent("[a, cd, cd, cd, cd]"                   ));
        assertTrue(new AugList<String>(new AugList<String>(), new AugList<Integer>()).size() == 0);
        assertTrue(new AugList<String>(null, null).size() == 0);
        assertTrue(new AugList<String>(new AugList<String>(), null).size() == 0);
        assertTrue(new AugList<String>(null, new AugList<Integer>()).size() == 0);
        assertTrue(ALS.getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALS.parameterizedTypeDesc().equals("public final class java.lang.String"));
    }

    /**
     * JUnit tester for instantiating from an {@link Spliterator}.
     * @since   AugList V2
     * @see     src.AugList#AugList(Spliterator)
     */
    @Test
    public void testInstantiateSpliterator() {
        setupTestData();
        // From Spliterator<T>
        assertTrue(new AugList<String>(new AugList<String>("b", "a", "ce", "ddd").spliterator()).isEquivalent("[b, a, ce, ddd]"));
        Spliterator<Character> sp = null;
        assertTrue(new AugList<Character>(sp).size() == 0);
        assertTrue(new AugList<Character>(sp).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Character>(sp).parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for instantiating from an {@link Stream}.
     * @since   AugList V2
     * @see     src.AugList#AugList(Stream)
     */
    @Test
    public void testInstantiateStream() {
        setupTestData();
        // From a Stream<T>
        Stream<Integer> s = Arrays.stream(Arrays.asList(1).toArray((new Integer[2])));
        AugList<Integer> AL = new AugList<Integer>(s);
        assertTrue(AL.isEquivalent("[1, *null*]"));
        Stream<String> t = null;
        assertTrue(new AugList<String>(t).size() == 0);
        assertTrue(new AugList<String>(t).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<String>(t).parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for instantiating from a varargs {@link Arrays array}.
     * @since   Method since V2, tested since AugList V1
     * @see     src.AugList#AugList(Object...) AugList(T...)
     */
    @Test
    public void testInstantiateVarargs() {
        setupTestData();
        Integer[] i = null;
        // Passing an Array acts the same as passing varargs
        assertTrue(new AugList<Integer>(i).size() == 0); 
        assertTrue(new AugList<Integer>(7, 11, 19, -24, 117, 145, -56, 43).isEquivalent(testDataInt));   
        assertTrue(new AugList<Integer>(i).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(new AugList<Integer>(i).parameterizedTypeDesc().equals("∅"));    
    }

    /**
     * JUnit tester for Emptiness check (A = ∅)
     * @since   AugList V1
     * @see     src.AugList#isEmpty()
     */
    @Test
    public void testIsEmpty() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertFalse(testDataDouble.isEmpty());
        assertFalse(testDataString.isEmpty());
        assertFalse(testDataInt.isEmpty());
        assertTrue(testDataInt.clear().isEmpty());
        assertTrue(new AugList<Integer>().isEmpty());
    }

    /**
     * JUnit tester for Equivalence
     * @since   Method since AugList V2, tested since V1
     * @see     src.AugList#isEquivalent(Object)
     */
    @Test
    public void testIsEquivalent() {
        setupTestData();
        // Return type is primitive so cannot enforce type through testing
        assertTrue(testDataDouble.isEquivalent(testDataDouble.countsOfElements().keys()));
        assertFalse(testDataDouble.isEquivalent(testDataString.countsOfElements().keys()));
        assertTrue(testDataDouble.isEquivalent(testDataDouble.iterator()));
        assertFalse(testDataDouble.isEquivalent(testDataString.iterator()));
        assertTrue(testDataDouble.isEquivalent(testDataDouble.spliterator()));
        assertFalse(testDataDouble.isEquivalent(testDataString.spliterator()));
        assertTrue(testDataDouble.isEquivalent(testDataDouble));
        assertFalse(testDataDouble.isEquivalent(testDataString));
        assertTrue(testDataDouble.isEquivalent(testDataDouble.listIterator()));
        assertFalse(testDataDouble.isEquivalent(testDataString.listIterator()));
        assertTrue(testDataDouble.isEquivalent(testDataDouble.stream()));
        assertFalse(testDataDouble.isEquivalent(testDataString.stream()));
        assertFalse(testDataDouble.isEquivalent(null));
        assertTrue(testDataString.isEquivalent(testDataString.hashCode()));
        assertFalse(testDataDouble.isEquivalent(testDataDouble.hashCode() + 1));
        assertFalse(testDataDouble.isEquivalent(-1.2));
        assertFalse(testDataDouble.isEquivalent('A'));
        assertFalse(new AugList<Character>().isEquivalent(testDataDouble));
        assertTrue(new AugList<String>().isEquivalent(new AugList<String>()));
        assertTrue(new AugList<String>().isEquivalent(new AugList<Integer>()));
        final AugList<String> TESTDATA = new AugList<String>("Hello", "World");
        assertTrue(TESTDATA.isEquivalent(TESTDATA));
        assertTrue(TESTDATA.isEquivalent(new AugList<String>("Hello", "World")));
        assertTrue(TESTDATA.isEquivalent(new AugList<String>("Hello", "World")));
        assertNotSame(TESTDATA, new AugList<String>("Hello", "World"));
        assertFalse(testDataDouble.clone().oneToOneMapCopy(d -> d + 1).isEquivalent(testDataDouble));
        assertFalse(testDataString.clone().oneToOneMapCopy(s -> s.toLowerCase()).isEquivalent(testDataString.distinctCopy()));
        assertFalse(testDataInt.clone().filterCopy(i -> i == 117).isEquivalent(testDataInt));
        assertFalse(testDataDouble.isEquivalent(testDataString));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertFalse(testDataDouble.isEquivalent("[1.0, 2.0, -2.5, 3.1415926, 7.11]"));
        assertFalse(testDataDouble.isEquivalent(testDataDouble.clone().swap(0,1)));
        assertTrue(testDataDouble.isEquivalent(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926))));
        assertFalse(testDataDouble.isEquivalent(new AugList<Double>()));
        assertTrue(new AugList<Double>().isEquivalent(new AugList<Double>()));
        assertTrue(new AugList<Double>().isEquivalent(new AugList<String>()));
        assertFalse(new AugList<Double>().isEquivalent(""));
        assertFalse(new AugList<Double>(7.0).isEquivalent(7.0));
        assertFalse(testDataDouble.clone().oneToOneMapCopy(d -> d + 1).isEquivalent(testDataDouble));
        assertFalse(testDataString.clone().oneToOneMapCopy(s -> s.toLowerCase()).isEquivalent(testDataString.distinctCopy()));
        assertFalse(testDataInt.clone().filterCopy(i -> i == 117).isEquivalent(testDataInt));
        assertFalse(testDataDouble.isEquivalent(testDataString));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertFalse(testDataDouble.isEquivalent("[1.0, 2.0, -2.5, 3.1415926, 7.11]"));
        assertFalse(testDataDouble.isEquivalent(testDataDouble.clone().swap(0,1)));
        assertTrue(testDataDouble.isEquivalent(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926))));
        assertFalse(testDataDouble.isEquivalent(new AugList<Double>()));
        assertTrue(new AugList<Double>().isEquivalent(new AugList<Double>()));
        assertTrue(new AugList<Double>().isEquivalent(new AugList<String>()));
        assertFalse(new AugList<Double>().isEquivalent(""));
        assertFalse(new AugList<Double>(7.0).isEquivalent(7.0));
        assertTrue(testDataDouble.isEquivalent(testDataDouble));
        assertTrue(testDataInt.isEquivalent(testDataInt));
        assertTrue(testDataString.isEquivalent(testDataString));
        assertFalse(testDataDouble.isEquivalent(testDataInt));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertFalse(testDataDouble.isEquivalent("[1, 2, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.isEquivalent(testDataDouble.clone()));
        assertTrue(testDataDouble.isEquivalent(new AugList<Double>(1.0, 2.0, 7.11, -2.5, 3.1415926)));
        assertFalse(testDataInt.isEquivalent(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, -56.0, 43.0)));
        assertTrue(testDataDouble.isEquivalent(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926))));
        AugList<Double> shuffled = testDataDouble.shuffleCopy();
        if (shuffled.isEquivalent(testDataDouble)) {
            shuffled.swap(0, 1);
        }
        assertFalse(testDataDouble.isEquivalent(shuffled));
    }

    /**
     * JUnit tester for Palindrome property
     * @since   AugList V1
     * @see     src.AugList#isPalindrome()
     */
    @Test
    public void testIsPalindrome() {
        setupTestData();
        assertFalse(testDataDouble.isPalindrome());
        assertTrue(new AugList<Character>().isPalindrome());
        assertTrue(new AugList<String>("A", "BC", "A", "BC", "A").isPalindrome());
        assertFalse(new AugList<String>("A", "BC", "A", "CB", "A").isPalindrome());
    }

    // As all 3 of isRearrangement(), shuffleSelf() and shuffleCopy() are tested here,
    // making testShuffleSelf() and testShuffleCopy() call testIsRearrangement()
    // preserves the integrity of the tests whilst saving file size.

    /**
     * JUnit tester for Rearrangement test
     * @since   AugList V1
     * @see     src.AugList#isRearrangement()
     * @see     #testShuffleCopy()
     * @see     #testShuffleSelf()
     */
    @Test
    public void testIsRearrangement() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertTrue(testDataDouble.isRearrangement(testDataDouble));
        assertTrue(testDataDouble.isRearrangement(testDataDouble.shuffleCopy()));
        assertTrue(testDataString.isRearrangement(testDataString.shuffleCopy()));
        assertTrue(testDataInt.isRearrangement(testDataInt.shuffleCopy()));
        AugList<Double> tddClone = testDataDouble.clone();
        assertTrue(tddClone.isRearrangement(testDataDouble.shuffleSelf()));
        // If a list could not shuffle to itself, it would need checking.
        assertFalse(testDataDouble.isRearrangement(new AugList<Double>()));
        assertFalse(new AugList<Double>().isRearrangement(testDataDouble));
        assertTrue(new AugList<Double>().isRearrangement(new AugList<Double>()));
        assertFalse(new AugList<Double>(1.0).isRearrangement(new AugList<Double>(2.0)));
    }

    /**
     * JUnit tester for Set property
     * @since   AugList V1
     * @see     src.AugList#isSet()
     */
    @Test
    public void testIsSet() {
        setupTestData();
        assertTrue(testDataDouble.isSet());
        assertFalse(testDataDouble.add(1.0).isSet());
    }

    /**
     * JUnit tester for Sorted property
     * @since   AugList V1
     * @see     src.AugList#isSorted()
     */
    @Test
    public void testIsSorted() {
        setupTestData();
        // Sort ascending
        Comparator<Double> compAsc = new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int)(o1 - o2);
            }
        };
        testDataDouble.sort(compAsc);
        assertTrue(testDataDouble.isSorted(compAsc));
        // Sort descending
        Comparator<Double> compDesc = new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int)(o2 - o1);
            }
        };
        testDataDouble.sort(compDesc);
        assertFalse(testDataDouble.isSorted(compAsc));
    }

    /**
     * JUnit tester for {@link Iterator} "cast"
     * @since   AugList V1
     * @see     src.AugList#iterator()
     */
    @Test
    public void testIterator() {
        setupTestData();
        Iterator<Double> TDD_iterator = testDataDouble.iterator(),
                         ALD_iterator = ARRLISTDOUBLE.iterator();
        Iterator<String> TDS_iterator = testDataString.iterator(),
                         ALS_iterator = ARRLISTSTR.iterator();
        Iterator<Integer> TDI_iterator = testDataInt.iterator(),
                          ALI_iterator = ARRLISTINT.iterator();
        assertTrue(testDataDouble.iterator().getClass().toGenericString().equals("private class java.util.ArrayList$Itr"));
        assertTrue(new AugList<Double>(testDataDouble.iterator()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        while (TDD_iterator.hasNext()) {
            assertEquals(TDD_iterator.next(), ALD_iterator.next());
        }
        while (TDS_iterator.hasNext()) {
            assertEquals(TDS_iterator.next(), ALS_iterator.next());
        }
        while (TDI_iterator.hasNext()) {
            assertEquals(TDI_iterator.next(), ALI_iterator.next());
        }
        assertTrue(TDD_iterator.getClass().toGenericString().equals("private class java.util.ArrayList$Itr"));
        assertTrue(TDS_iterator.getClass().toGenericString().equals("private class java.util.ArrayList$Itr"));
        assertTrue(TDI_iterator.getClass().toGenericString().equals("private class java.util.ArrayList$Itr"));
        // The hashcodes of i.e. testDataDouble.iterator() and ARRLISTDOUBLE.iterator() do not match,
        // so the only way to be 100% sure if they iterators have the same underlying data,
        // is to take elements from each iterator until they are exhausted,
        // and check each pair is identical.
    }

    /**
     * JUnit tester for Last Index finding
     * @since   AugList V1
     * @see     src.AugList#lastIndexOf()
     */
    @Test
    public void testLastIndexOf() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertTrue(testDataDouble.lastIndexOf(1.0) == 0);
        assertTrue(testDataDouble.lastIndexOf(3.1415926) == 4);
        assertTrue(testDataDouble.lastIndexOf(77) == -1);
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.lastIndexOf("the") == 6);
        assertTrue(testDataString.lastIndexOf("jumps") == 4);
        assertTrue(testDataString.lastIndexOf("") == -1);
        assertTrue(testDataInt.lastIndexOf(117) == 4);
        assertTrue(testDataInt.lastIndexOf(43) == 7);
        assertTrue(testDataInt.lastIndexOf(711) == -1);
        assertTrue(testDataInt.lastIndexOf(null) == -1);
        assertTrue(new AugList<Double>(1.0, null, null).lastIndexOf(null) == 2);
    }

    
    /**
     * JUnit tester for "List Difference"
     * @since   AugList V1
     * @see     src.AugList#listDifference()
     */
    @Test
    public void testListDifference() {
        setupTestData();
        assertTrue(testDataDouble.listDifference(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.listDifference(testDataDouble).isEquivalent("/"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(1.0, 7.11, 3.1415926)).isEquivalent("[2.0, -2.5]"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).isEquivalent("[2.0, -2.5]"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).isEquivalent("[2.0, -2.5]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.listDifference(new AugList<String>()).isEquivalent(testDataString));
        assertTrue(testDataString.listDifference(new AugList<String>("the")).isEquivalent("[quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.listDifference(new AugList<String>("the", "the")).isEquivalent("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.listDifference(new AugList<String>("the", "the", "the")).isEquivalent("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.listDifference(new AugList<String>("the", "fox", "lazy", "dog")).isEquivalent("[quick, brown, jumps, over, the, lazy dog]"));
        assertTrue(testDataInt.listDifference(null).isEquivalent(testDataInt));
    }

    /**
     * JUnit tester for "List Intersection"
     * @since   AugList V1
     * @see     src.AugList#listIntersection()
     */
    @Test
    public void testListIntersection() {
        setupTestData();
        assertTrue(testDataDouble.listIntersection(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.listIntersection(testDataDouble).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).isEquivalent("/"));
        assertTrue(testDataDouble.listIntersection(testDataDouble).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>()).isEquivalent("/"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(1.0, 7.11, 3.1415926)).isEquivalent("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).isEquivalent("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).isEquivalent("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(7.11, 1.0)).isEquivalent("[1.0, 7.11]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.listIntersection(new AugList<String>()).isEquivalent("/"));
        assertTrue(testDataString.listIntersection(new AugList<String>("the")).isEquivalent("[the]"));
        assertTrue(testDataString.listIntersection(new AugList<String>("the", "the")).isEquivalent("[the, the]"));
        assertTrue(testDataString.listIntersection(new AugList<String>("the", "the", "the")).isEquivalent("[the, the]"));
        assertTrue(testDataString.listIntersection(new AugList<String>("the", "fox", "lazy", "dog")).isEquivalent("[the, fox]"));
        assertTrue(testDataInt.listIntersection(null).isEquivalent("/"));
    }
    
    /**
     * JUnit tester for {@link ListIterator} "Cast"
     * @since   AugList V1
     * @see     src.AugList#listIterator()
     */
    @Test
    public void testListIterator() {
        setupTestData();
        ListIterator<Double> TDD_lIterator = testDataDouble.listIterator(),
                             ALD_lIterator = ARRLISTDOUBLE.listIterator();
        ListIterator<String> TDS_lIterator = testDataString.listIterator(),
                             ALS_lIterator = ARRLISTSTR.listIterator();
        ListIterator<Integer> TDI_lIterator = testDataInt.listIterator(),
                              ALI_lIterator = ARRLISTINT.listIterator();
        assertTrue(new AugList<Double>(testDataDouble.listIterator()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        while (TDD_lIterator.hasNext()) {
            assertEquals(TDD_lIterator.hasPrevious(), ALD_lIterator.hasPrevious());
            try {
                assertEquals(TDD_lIterator.previous(), ALD_lIterator.previous());
                assertEquals(TDD_lIterator.previousIndex(), ALD_lIterator.previousIndex());
            } catch (NoSuchElementException e) {
                // This is expected behaviour.
            }
            assertEquals(TDD_lIterator.next(), ALD_lIterator.next());
            assertEquals(TDD_lIterator.nextIndex(), ALD_lIterator.nextIndex());
            assertEquals(TDD_lIterator.next(), ALD_lIterator.next());
            // Since .next and .previous move the tape, calling both cancels out movement.
            // To ensure the test halts, we thus must advance the tape twice for each backwards movement per cycle.
        } 
        while (TDS_lIterator.hasNext()) {
            assertEquals(TDS_lIterator.hasPrevious(), ALS_lIterator.hasPrevious());
            try {
                assertEquals(TDS_lIterator.previous(), ALS_lIterator.previous());
                assertEquals(TDS_lIterator.previousIndex(), ALS_lIterator.previousIndex());
            } catch (NoSuchElementException e) {
                // This is expected behaviour.
            }
            assertEquals(TDS_lIterator.next(), ALS_lIterator.next());
            assertEquals(TDS_lIterator.nextIndex(), ALS_lIterator.nextIndex());
            assertEquals(TDS_lIterator.next(), ALS_lIterator.next());
        } 
        while (TDI_lIterator.hasNext()) {
            assertEquals(TDI_lIterator.hasPrevious(), ALI_lIterator.hasPrevious());
            try {
                assertEquals(TDI_lIterator.previous(), ALI_lIterator.previous());
                assertEquals(TDI_lIterator.previousIndex(), ALI_lIterator.previousIndex());
            } catch (NoSuchElementException e) {
                // This is expected behaviour.
            }
            assertEquals(TDI_lIterator.next(), ALI_lIterator.next());
            assertEquals(TDI_lIterator.nextIndex(), ALI_lIterator.nextIndex());
            assertEquals(TDI_lIterator.next(), ALI_lIterator.next());
        }
        assertTrue(TDD_lIterator.getClass().toGenericString().equals("private class java.util.ArrayList$ListItr"));
        assertTrue(TDS_lIterator.getClass().toGenericString().equals("private class java.util.ArrayList$ListItr"));
        assertTrue(TDI_lIterator.getClass().toGenericString().equals("private class java.util.ArrayList$ListItr"));
        // The hashcodes of i.e. testDataDouble.listIterator() and ARRLISTDOUBLE.listIterator() do not match,
        // so the only way to be 100% sure if they iterators have the same underlying data,
        // is to take elements from each iterator until they are exhausted,
        // and check for each pair, all methods act identically across both copies.
    }

    /**
     * JUnit tester for {@link ListIterator} "Cast"
     * @since   AugList V1
     * @see     src.AugList#listIterator(int)
     */
    @Test
    public void testListIteratorFromIndex() {
        setupTestData();
        ListIterator<Double> TDD_lIterator = testDataDouble.listIterator(3),
                             ALD_lIterator = ARRLISTDOUBLE.listIterator(3);
        ListIterator<String> TDS_lIterator = testDataString.listIterator(2),
                             ALS_lIterator = ARRLISTSTR.listIterator(2);
        ListIterator<Integer> TDI_lIterator = testDataInt.listIterator(1),
                              ALI_lIterator = ARRLISTINT.listIterator(1);
        assertEquals(testDataDouble.listIterator(1).getClass().toGenericString(), "private class java.util.ArrayList$ListItr");
        assertTrue(new AugList<Double>(testDataDouble.listIterator(1)).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        while (TDD_lIterator.hasNext()) {
            assertEquals(TDD_lIterator.hasPrevious(), ALD_lIterator.hasPrevious());
            try {
                assertEquals(TDD_lIterator.previous(), ALD_lIterator.previous());
                assertEquals(TDD_lIterator.previousIndex(), ALD_lIterator.previousIndex());
            } catch (NoSuchElementException e) {
                // This is expected behaviour.
            }
            assertEquals(TDD_lIterator.next(), ALD_lIterator.next());
            assertEquals(TDD_lIterator.nextIndex(), ALD_lIterator.nextIndex());
            assertEquals(TDD_lIterator.next(), ALD_lIterator.next());
            // Since .next and .previous move the tape, calling both cancels out movement.
            // To ensure the test halts, we thus must advance the tape twice for each backwards movement per cycle.
        } 
        while (TDS_lIterator.hasNext()) {
            assertEquals(TDS_lIterator.hasPrevious(), ALS_lIterator.hasPrevious());
            try {
                assertEquals(TDS_lIterator.previous(), ALS_lIterator.previous());
                assertEquals(TDS_lIterator.previousIndex(), ALS_lIterator.previousIndex());
            } catch (NoSuchElementException e) {
                // This is expected behaviour.
            }
            assertEquals(TDS_lIterator.next(), ALS_lIterator.next());
            assertEquals(TDS_lIterator.nextIndex(), ALS_lIterator.nextIndex());
            assertEquals(TDS_lIterator.next(), ALS_lIterator.next());
        } 
        while (TDI_lIterator.hasNext()) {
            assertEquals(TDI_lIterator.hasPrevious(), ALI_lIterator.hasPrevious());
            try {
                assertEquals(TDI_lIterator.previous(), ALI_lIterator.previous());
                assertEquals(TDI_lIterator.previousIndex(), ALI_lIterator.previousIndex());
            } catch (NoSuchElementException e) {
                // This is expected behaviour.
            }
            assertEquals(TDI_lIterator.next(), ALI_lIterator.next());
            assertEquals(TDI_lIterator.nextIndex(), ALI_lIterator.nextIndex());
            assertEquals(TDI_lIterator.next(), ALI_lIterator.next());
        }
        assertTrue(TDD_lIterator.getClass().toGenericString().equals("private class java.util.ArrayList$ListItr"));
        assertTrue(TDS_lIterator.getClass().toGenericString().equals("private class java.util.ArrayList$ListItr"));
        assertTrue(TDI_lIterator.getClass().toGenericString().equals("private class java.util.ArrayList$ListItr"));
        // The hashcodes of i.e. testDataDouble.listIterator(1) and ARRLISTDOUBLE.listIterator(1) do not match,
        // so the only way to be 100% sure if they iterators have the same underlying data,
        // is to take elements from each iterator until they are exhausted,
        // and check for each pair, all methods act identically across both copies.
    }

    /**
     * JUnit tester for "List Union"
     * @since   AugList V1
     * @see     src.AugList#listUnion()
     */
    @Test
    public void testListUnion() {
        setupTestData();
        assertTrue(testDataDouble.listUnion(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.listUnion(new AugList<Double>()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0]"));
        assertTrue(testDataDouble.listUnion(testDataDouble).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.listUnion(new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(1.0, 7.11, 3.1415926)).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 100.0]"));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 1.0]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.listUnion(new AugList<String>()).isEquivalent(testDataString));
        assertTrue(testDataString.listUnion(new AugList<String>("the")).isEquivalent("[the, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.listUnion(new AugList<String>("the", "the")).isEquivalent("[the, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.listUnion(new AugList<String>("the", "the", "the")).isEquivalent("[the, quick, brown, fox, jumps, over, the, lazy dog, the]"));
        assertTrue(testDataString.listUnion(new AugList<String>("the", "fox", "lazy", "dog")).isEquivalent("[the, quick, brown, fox, jumps, over, the, lazy dog, lazy, dog]"));
        assertTrue(testDataDouble.clone().listUnion(nullAL).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for mass random overwriting
     * @since   AugList V2
     * @see     src.AugList#massOverwriteRandom(Iterable)
     */
    @Test
    public void testMassOverwriteRandom() {
        AugList<AugList<Double>> dat = new AugList<AugList<Double>>(
            new AugList<Double>(),
            new AugList<Double>(1.0),
            new AugList<Double>(2.0, 2.0),
            new AugList<Double>(3.0, 3.0, 3.0)
        );
        assertTrue(dat.get(0).clone().massOverwriteRandom(nullAL).isEquivalent(dat.get(0)));
        assertTrue(dat.get(0).clone().massOverwriteRandom(new AugList<Double>()).isEquivalent(dat.get(0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(0).clone().massOverwriteRandom(new AugList<Double>(-7.0)); } );
        assertTrue(dat.get(1).clone().massOverwriteRandom(nullAL).isEquivalent(dat.get(1)));
        assertTrue(dat.get(1).clone().massOverwriteRandom(new AugList<Double>()).isEquivalent(dat.get(1)));
        assertTrue(dat.get(1).clone().massOverwriteRandom(new AugList<Double>(-7.0)).isRearrangement(new AugList<Double>(-7.0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(1).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0)); } );
        assertTrue(dat.get(2).clone().massOverwriteRandom(nullAL).isEquivalent(dat.get(2)));
        assertTrue(dat.get(2).clone().massOverwriteRandom(new AugList<Double>()).isEquivalent(dat.get(2)));
        assertTrue(dat.get(2).clone().massOverwriteRandom(new AugList<Double>(-7.0)).isRearrangement(new AugList<Double>(2.0, -7.0)));
        assertTrue(dat.get(2).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0)).isRearrangement(new AugList<Double>(-7.0, -7.0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(2).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0, -7.0)); } );
        assertTrue(dat.get(3).clone().massOverwriteRandom(nullAL).isEquivalent(dat.get(3)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(new AugList<Double>()).isEquivalent(dat.get(3)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(new AugList<Double>(-7.0)).isRearrangement(new AugList<Double>(3.0, 3.0, -7.0)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0)).isRearrangement(new AugList<Double>(3.0, -7.0, -7.0)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0, -7.0)).isRearrangement(new AugList<Double>(-7.0, -7.0, -7.0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(3).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0, -7.0, -7.0)); } );
    }

    /**
     * JUnit tester for mass random overwriting
     * @since   AugList V2
     * @see     src.AugList#massOverwriteRandom(Object...) massOverwriteRandom(T...)
     */
    @Test
    public void testMassOverwriteRandomVarargs() {
        AugList<AugList<Double>> dat = new AugList<AugList<Double>>(
            new AugList<Double>(),
            new AugList<Double>(1.0),
            new AugList<Double>(2.0, 2.0),
            new AugList<Double>(3.0, 3.0, 3.0)
        );
        assertTrue(dat.get(0).clone().massOverwriteRandom(nullArr).isEquivalent(dat.get(0)));
        assertTrue(dat.get(0).clone().massOverwriteRandom(emptyArr).isEquivalent(dat.get(0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(0).clone().massOverwriteRandom(new AugList<Double>(-7.0)); } );
        assertTrue(dat.get(1).clone().massOverwriteRandom(nullArr).isEquivalent(dat.get(1)));
        assertTrue(dat.get(1).clone().massOverwriteRandom(emptyArr).isEquivalent(dat.get(1)));
        assertTrue(dat.get(1).clone().massOverwriteRandom(-7.0).isRearrangement(new AugList<Double>(-7.0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(1).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0)); } );
        assertTrue(dat.get(2).clone().massOverwriteRandom(nullArr).isEquivalent(dat.get(2)));
        assertTrue(dat.get(2).clone().massOverwriteRandom(emptyArr).isEquivalent(dat.get(2)));
        assertTrue(dat.get(2).clone().massOverwriteRandom(-7.0).isRearrangement(new AugList<Double>(2.0, -7.0)));
        assertTrue(dat.get(2).clone().massOverwriteRandom(-7.0, -7.0).isRearrangement(new AugList<Double>(-7.0, -7.0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(2).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0, -7.0)); } );
        assertTrue(dat.get(3).clone().massOverwriteRandom(nullArr).isEquivalent(dat.get(3)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(emptyArr).isEquivalent(dat.get(3)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(-7.0).isRearrangement(new AugList<Double>(3.0, 3.0, -7.0)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(-7.0, -7.0).isRearrangement(new AugList<Double>(3.0, -7.0, -7.0)));
        assertTrue(dat.get(3).clone().massOverwriteRandom(-7.0, -7.0, -7.0).isRearrangement(new AugList<Double>(-7.0, -7.0, -7.0)));
        assertThrows(IllegalArgumentException.class, () -> { dat.get(3).clone().massOverwriteRandom(new AugList<Double>(-7.0, -7.0, -7.0, -7.0)); } );
    }

    /**
     * JUnit tester for Mapping
     * @since   AugList V1
     * @see     src.AugList#oneToOneMapCopy(java.util.function.UnaryOperator)
     */
    @Test
    public void testOneToOneMap() {
        setupTestData();
        // Identity map (var x -> var x)
        assertTrue(testDataDouble.oneToOneMapCopy(x -> x).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.oneToOneMapCopy(e -> e).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        // Map all to 0 (var x -> int 0)
        assertTrue(testDataDouble.oneToOneMapCopy(x -> 0).allSatisfy(d -> d == 0));
        // Map to 1 less (double d -> double d - 1)
        assertTrue(testDataDouble.oneToOneMapCopy(d -> d - 1).isEquivalent("[0.0, 1.0, 6.11, -3.5, 2.1415926]"));
        // Map to length (String s -> int s.length())
        assertTrue(testDataString.oneToOneMapCopy(s -> s.length()).get(2) == 5);
        // Map to even parity (int i -> boolean i % 2)
        assertTrue(testDataInt.oneToOneMapCopy(i -> i % 2 == 0).countsOfElements().get(true) == 2);
        // Map to 'c' (var x -> char 'c')
        assertTrue(testDataDouble.oneToOneMapCopy(x -> 'c').parameterizedTypeDesc().equals("public final class java.lang.Character"));
        assertThrows(NullPointerException.class, () -> { testDataInt.oneToOneMapCopy(null); });
        testDataInt.oneToOneMapCopy(i -> null).isEquivalent("[*null*, *null*, *null*, *null*, *null*, *null*, *null*, *null*]");
    }

    /**
     * JUnit tester for random overwriting
     * @since   AugList V2
     * @see     src.AugList#overwriteRandom()
     */
    @Test
    public void testOverwriteRandom() {
        setupTestData();
        AugList<AugList<Double>> validStates = new AugList<AugList<Double>>(
            new AugList<Double>(12.0, 2.0, 7.11, -2.5, 3.1415926),
            new AugList<Double>(1.0, 12.0, 7.11, -2.5, 3.1415926),
            new AugList<Double>(1.0, 2.0, 12.0, -2.5, 3.1415926),
            new AugList<Double>(1.0, 2.0, 7.11, 12.0, 3.1415926),
            new AugList<Double>(1.0, 2.0, 7.11, -2.5, 12.0)
        );
        testDataDouble.overwriteRandom(12.0);
        Integer stateIndex = -1;
        /*
         * Starting with 
         * [1.0, 2.0, 7.11, -2.5, 3.1415926], 
         * the possible states after a random deletion are:
         * 
         * testDataDouble                   
         * [12 , 2.0, 7.11, -2.5, 3.1415926],
         * [1.0, 12 , 7.11, -2.5, 3.1415926],
         * [1.0, 2.0, 12  , -2.5, 3.1415926],
         * [1.0, 2.0, 7.11, 12  , 3.1415926],
         * [1.0, 2.0, 7.11, -2.5, 12       ]
         * 
         * If the list is not one of these 5, then auto fail the test.
         */
        if (testDataDouble.get(0) != 1.0) {
            stateIndex = 0;
        }
        else if (testDataDouble.get(1) != 2.0) {
            stateIndex = 1;
        }
        else if (testDataDouble.get(2) != 7.11) {
            stateIndex = 2;
        }
        else if (testDataDouble.get(3) != -2.5) {
            stateIndex = 3;
        }
        else {
            stateIndex = 4;
        }
        assertTrue(validStates.get(stateIndex).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for pairing
     * @since   AugList V1
     * @see     src.AugList#pairUp()
     */
    @Test
    public void testPairUp() {
        setupTestData();
        assertFalse(testDataDouble.pairUp(new AugList<Double>()).elements().asIterator().hasNext());
        testDataInt = testDataInt.subList(0, 5);
        Hashtable<Double, Integer> hash = testDataDouble.pairUp(testDataInt);
        Enumeration<Double> keys = testDataDouble.pairUp(testDataInt).keys();
        Enumeration<Integer> elements = testDataDouble.pairUp(testDataInt).elements();
        //new AugList<Double>(keys).forEach(System.out::println);
        assertTrue(new AugList<Double>(keys).isRearrangement(testDataDouble));
        assertTrue(new AugList<Integer>(elements).isRearrangement(testDataInt));
        assertTrue(hash.get(1.0) == 7);
        assertTrue(hash.get(7.11) == 19);
        assertTrue(hash.get(711.0) == null);
        assertFalse(new AugList<Double>(null, 3.0).pairUp(new AugList<Double>(1.0, 2.0)).elements().asIterator().hasNext());
        assertFalse(new AugList<Double>(2.0, 3.0).pairUp(new AugList<Double>(null, 2.0)).elements().asIterator().hasNext());
        assertTrue(hash.getClass().toGenericString().contains("class src.AugList$"));
        assertTrue(new AugList<Double>(hash.keys()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for {@link Stream ParallelStream} "Cast"
     * @since   AugList V1
     * @see     src.AugList#parallelStream()
     */
    @Test
    public void testParallelStream() {
        setupTestData();
        // Hashcodes don't match, so check every method has same functionality
        // However this involves needing to deal with methods that alter state...
        // For our use cases, assume that the state-altering methods will not be invoked.
        assertTrue(testDataDouble.parallelStream().count() == ARRLISTDOUBLE.parallelStream().count());
        assertTrue(testDataDouble.parallelStream().isParallel() == ARRLISTDOUBLE.parallelStream().isParallel());
        assertTrue(testDataDouble.parallelStream().iterator().hasNext() == ARRLISTDOUBLE.parallelStream().iterator().hasNext());
        assertTrue(testDataString.parallelStream().count() == ARRLISTSTR.parallelStream().count());
        assertTrue(testDataString.parallelStream().isParallel() == ARRLISTSTR.parallelStream().isParallel());
        assertTrue(testDataString.parallelStream().iterator().hasNext() == ARRLISTSTR.parallelStream().iterator().hasNext());
        assertTrue(testDataInt.parallelStream().count() == ARRLISTINT.parallelStream().count());
        assertTrue(testDataInt.parallelStream().isParallel() == ARRLISTINT.parallelStream().isParallel());
        assertTrue(testDataInt.parallelStream().iterator().hasNext() == ARRLISTINT.parallelStream().iterator().hasNext());
        assertTrue(testDataDouble.parallelStream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(testDataString.parallelStream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(testDataInt.parallelStream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(testDataDouble.parallelStream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(new AugList<Double>(testDataDouble.parallelStream()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }    

    /**
     * JUnit tester for getting the parameterized type.
     * @since   AugList V2
     * @see     src.AugList#parameterizedTypeDesc()
     */
    @Test
    public void testParameterizedTypeDesc() {
        setupTestData();
        assertTrue(testDataDouble.parameterizedTypeDesc().getClass().toGenericString().equals("public final class java.lang.String"));
        assertTrue(testDataInt.parameterizedTypeDesc().equals("public final class java.lang.Integer"));
        assertTrue(testDataString.parameterizedTypeDesc().equals("public final class java.lang.String"));
        assertTrue(testDataDouble.parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(new AugList<Character>().parameterizedTypeDesc().equals("∅"));
    }

    /**
     * JUnit tester for Single Removal
     * @since   AugList V1
     * @see     src.AugList#remove()
     */
    @Test
    public void testRemove() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertFalse(testDataDouble.remove(18));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.remove(1.0));
        assertTrue(testDataDouble.isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.remove("quick"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataString.remove("quick"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.remove("lazy dog"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertFalse(testDataInt.remove(8));
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.remove(19));
        assertTrue(testDataInt.isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertFalse(testDataInt.remove(null));
        assertTrue(new AugList<Double>(null, null, null).remove(null));
    }
    
    /**
     * JUnit tester for Bulk Removal
     * @since   AugList V1
     * @see     src.AugList#removeAll(src.AugList)
     */
    @Test
    public void testRemoveAll() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertFalse(testDataDouble.removeAll(new AugList<Double>(18.0)));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.removeAll(new AugList<Double>(1.0)));
        assertTrue(testDataDouble.isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.removeAll(new AugList<String>("quick")));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataString.removeAll(new AugList<String>("quick")));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.removeAll(new AugList<String>("quick", "lazy dog")));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertFalse(testDataInt.removeAll(new AugList<Integer>(8)));
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.removeAll(new AugList<Integer>(19)));
        assertTrue(testDataInt.isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertFalse(testDataDouble.removeAll(nullAL));
    }

    /**
     * JUnit tester for Varargs Bulk Removal
     * @since   AugList V1
     * @see     src.AugList#removeAll(Object...) removeAll(T...)
     */
    @Test
    public void testRemoveAllVarargs() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertFalse(testDataDouble.removeAll(18.0));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.removeAll(1.0));
        assertTrue(testDataDouble.isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.removeAll("quick"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataString.removeAll("quick"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataInt.removeAll(8));
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.removeAll(19));
        assertTrue(testDataInt.isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertFalse(testDataDouble.removeAll(nullArr));
    }

    /**
     * JUnit tester for Single Targeted Removal
     * @since   AugList V1
     * @see     src.AugList#removeAt()
     */
    @Test
    public void testRemoveAtIndex() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.removeAt(18); });
        assertTrue(testDataDouble.removeAt(0) == 1.0f);
        assertTrue(testDataDouble.isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.removeAt(1).equals("quick"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.removeAt(6).equals("lazy dog"));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.removeAt(2) == 19);
        assertTrue(testDataInt.isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
    }

    /**
     * JUnit tester for Selective Removal
     * @since   AugList V1
     * @see     src.AugList#removeIf()
     */
    @Test
    public void testRemoveIf() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertFalse(testDataDouble.removeIf(d -> d > 18));
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.removeIf(d -> d <= 1.0));
        assertTrue(testDataDouble.isEquivalent("[2.0, 7.11, 3.1415926]"));
        assertTrue(testDataString.removeIf(s -> s.equals("quick")));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataString.removeIf(s -> s.equals("quick")));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.removeIf(s -> s.length() == 8));
        assertTrue(testDataString.isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertFalse(testDataInt.removeIf(i -> i == 8));
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.removeIf(i -> 18 <= i && i <= 20));
        assertTrue(testDataInt.isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertFalse(testDataInt.removeIf(null));
    }

    /**
     * JUnit tester for Stack Popping
     * @since   AugList V1
     * @see     src.AugList#removeLast()
     */
    @Test
    public void testRemoveLast() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        assertFalse(testDataDouble.removeLast() == 3.1415926f);
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5]"));
        assertTrue(testDataDouble.removeLast() == -2.5f);
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11]"));
        assertTrue(testDataString.removeLast() == "lazy dog");
        assertTrue(testDataString.isEquivalent("[The, quick, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.removeLast() == 43);
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56]"));
        assertThrows(NoSuchElementException.class, () -> { (new AugList<String>()).removeLast(); });
    }

    /**
     * JUnit tester for Random Removal
     * @since   AugList V2
     * @see     src.AugList#removeRandom()
     */
    @Test
    public void testRemoveRandom() {
        setupTestData();
        // Return type is primitive so cannot be tested for
        AugList<Double> tddClone = testDataDouble.clone();
        AugList<AugList<Double>> validStates = new AugList<AugList<Double>>(
            new AugList<Double>(2.0, 7.11, -2.5, 3.1415926),
            new AugList<Double>(1.0, 7.11, -2.5, 3.1415926),
            new AugList<Double>(1.0, 2.0, -2.5, 3.1415926),
            new AugList<Double>(1.0, 2.0, 7.11, 3.1415926),
            new AugList<Double>(1.0, 2.0, 7.11, -2.5)
        );
        Double removed = testDataDouble.removeRandom();
        Integer stateIndex = -1;
        /*
         * Starting with 
         * [1.0, 2.0, 7.11, -2.5, 3.1415926], 
         * the possible states after a random deletion are:
         * 
         * testDataDouble                       removed
         * [     2.0, 7.11, -2.5, 3.1415926],   1.0
         * [1.0,      7.11, -2.5, 3.1415926],   2.0
         * [1.0, 2.0,       -2.5, 3.1415926],   7.11
         * [1.0, 2.0, 7.11,       3.1415926],   -2.5
         * [1.0, 2.0, 7.11, -2.5           ],   3.1415926
         * 
         * Looking at the removed column, it is identical to tddClone.
         * So, by taking the 5 valid testDataDouble states and putting them into another AugList (validStates),
         * Indexing of the expected list state and its associated removed item is made very simple.
         */
        if (testDataDouble.get(0) != 1.0) {
            stateIndex = 0;
        }
        else if (testDataDouble.get(1) != 2.0) {
            stateIndex = 1;
        }
        else if (testDataDouble.get(2) != 7.11) {
            stateIndex = 2;
        }
        else if (testDataDouble.get(3) != -2.5) {
            stateIndex = 3;
        }
        else {
            stateIndex = 4;
        }
        assertTrue(validStates.get(stateIndex).isEquivalent(testDataDouble));
        assertTrue(tddClone.get(stateIndex).equals(removed));
        assertThrows(NoSuchElementException.class, () -> { new AugList<Double>().removeRandom(); });
    }

    /**
     * Junit tester for retainment.
     * @since   AugList V2
     * @see     src.AugList#retainAll(java.util.Collection)
     */
    @Test
    public void testRetainAll() {
        setupTestData();
        testDataString.applyAll(s -> s.toUpperCase());
        assertTrue(testDataString.clone().retainAll(new AugList<String>("THE").toCollection()).isEquivalent("[THE, THE]"));
        //setupTestData();
        //testDataSting.applyAll(s -> s.toUpperCase());
        assertTrue(testDataString.clone().retainAll(new AugList<String>("THE", "QUICK", "OVER").toCollection()).isEquivalent("[THE, QUICK, OVER, THE]"));
        //setupTestData();
        //testDataSting.applyAll(s -> s.toUpperCase());
        assertTrue(testDataString.clone().retainAll(new AugList<String>("THE", "ALEPH").toCollection()).isEquivalent("[THE, THE]"));
        //setupTestData();
        assertTrue(testDataString.clone().retainAll(new AugList<String>().toCollection()).isEquivalent("/"));
        assertThrows(NullPointerException.class, () -> { testDataString.clone().retainAll(null); });
        assertTrue((testDataDouble.retainAll(new AugList<Double>().toCollection())).getClass().toGenericString().equals("public class src.AugList<T>"));
        System.out.println((testDataDouble.retainAll(testDataDouble.toCollection())).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Reversal
     * @since   AugList V1
     * @see     src.AugList#reversed()
     */
    @Test
    public void testReversed() {
        setupTestData();
        assertTrue(testDataDouble.reversed().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.reversed().parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.reversed().isEquivalent("[3.1415926, -2.5, 7.11, 2.0, 1.0]"));
        assertTrue(testDataString.reversed().isEquivalent("[lazy dog, the, over, jumps, fox, brown, quick, The]"));
        assertTrue(testDataInt.reversed().isEquivalent("[43, -56, 145, 117, -24, 19, 11, 7]"));
    }

    /**
     * JUnit tester for Sampling
     * @since   AugList V2
     * @see     src.AugList#sample()
     */
    @Test
    public void testSample() {
        setupTestData();
        assertTrue(testDataDouble.sample(5, false).isRearrangement(testDataDouble));
        assertTrue(testDataDouble.sample(0, false).isEquivalent("/"));
        assertTrue(testDataDouble.containsAll(testDataDouble.sample(1, false)));
        // Technically speaking this assertion could fail, but the probability of the such is .8^10000, or 1.2302319222*10^-970, which is effectively 0.
        // (Plus, as the sample algorithm is only psuedo-random, I imagine that the above assertion passing can be proven as either guaranteed or not.)
        assertTrue(testDataDouble.sample(10000, true).contains(1.0));
        assertThrows(IllegalArgumentException.class, () -> { testDataString.sample(10000, false); });
        assertThrows(IllegalArgumentException.class, () -> { testDataInt.sample(-10, false); });
        assertTrue(testDataDouble.reversed().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.reversed().parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Setting/Writing
     * @since   AugList V1
     * @see     src.AugList#set()
     */
    @Test
    public void testSet() {
        setupTestData();
        assertTrue(testDataDouble.set(0, 42.0d) == 1.0d);
        assertTrue(testDataDouble.get(0) == 42.0d);
        assertTrue(testDataDouble.isEquivalent("[42.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertThrows(IndexOutOfBoundsException.class, () -> {new AugList<Character>().set(0, ' '); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataDouble.set(-1, 1.0); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataInt.set(100, 1); });
        assertTrue(testDataDouble.set(0, 1.0).getClass().toGenericString().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Set Difference
     * @since   AugList V1
     * @see     src.AugList#setDifference()
     */
    @Test
    public void testSetDifference() {
        setupTestData();
        assertTrue(testDataDouble.setDifference(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.setDifference(testDataDouble).isEquivalent("/"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(1.0, 7.11, 3.1415926)).isEquivalent("[2.0, -2.5]"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).isEquivalent("[2.0, -2.5]"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).isEquivalent("[2.0, -2.5]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        AugList<String> TDStrSet = testDataString.clone();
        TDStrSet.removeAt(6);
        assertTrue(testDataString.setDifference(new AugList<String>()).isEquivalent(TDStrSet));
        assertTrue(testDataString.setDifference(new AugList<String>("the")).isEquivalent("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.setDifference(new AugList<String>("the", "the")).isEquivalent("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.setDifference(new AugList<String>("the", "fox", "lazy", "dog")).isEquivalent("[quick, brown, jumps, over, lazy dog]"));
        assertTrue(testDataDouble.setDifference(nullAL).isEquivalent(testDataDouble));
    }

    /**
     * Ticks up on each call.
     * @note    Could easily be made into its own file...
     */
    public class Ticker {
        private Integer t = 0;

        public Ticker() {
            reset();
        }

        public Integer tick() {
            t += 1;
            return t - 1; 
        }

        public Ticker reset() {
            t = 0;
            return this;
        }
    }

    /**
     * JUnit tester for Callable setting
     * @since   AugList V2
     * @see     src.AugList#setFromCallable()
     * @see     Ticker
     * @note    {@link Ticker} is an example class that could be used alongside this method.
     */
    @Test
    public void testSetFromCallable() {
        setupTestData();
        Callable<Integer> nullCallable = null;
        assertTrue(testDataDouble.clone().setFromCallable(0, 4, () -> { return 0.0; }).isEquivalent("[0.0, 0.0, 0.0, 0.0, 0.0]"));
        Ticker t = new Ticker();
        assertTrue(testDataInt.clone().setFromCallable(0, 9, () -> { return t.tick(); }).isEquivalent("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(1, 2, () -> { return t.tick(); }).isEquivalent("[7, 0, 1, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(2, 1, () -> { return t.tick(); }).isEquivalent("[7, 0, 1, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(-1, 2, () -> { return t.tick(); }).isEquivalent("[0, 1, 2, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(0, 9, () -> { return t.tick(); }).isEquivalent("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(0, 0, () -> { return t.tick(); }).isEquivalent("[0, 11, 19, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(1, -1, () -> { return t.tick(); }).isEquivalent("[0, 1, 19, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(0, 2, () -> null).isEquivalent("[*null*, *null*, *null*, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataInt.clone().setFromCallable(0, 2, nullCallable).isEquivalent("[*null*, *null*, *null*, -24, 117, 145, -56, 43]"));
        t.reset();
        assertTrue(testDataDouble.clone().setFromCallable(
            0, 
            8, 
            () -> { 
                switch (t.tick() % 3) {
                case 0:
                    return 3.0;
                case 1:
                    return 4.5;
                default:
                    return 2.0;
                }
            }
            ).isEquivalent("[3.0, 4.5, 2.0, 3.0, 4.5, 2.0, 3.0, 4.5, 2.0]"));
        AugList<Boolean> ALB = new AugList<Boolean>();
        t.reset();
        assertTrue(ALB.clone().setFromCallable(0, 4, () -> { return t.tick() % 2 == 0; }).isEquivalent("[true, false, true, false, true]"));
        assertTrue(testDataString.clone().setFromCallable(0, 0, () -> { throw new IllegalArgumentException(); }).isEquivalent(testDataString));
        assertTrue(testDataString.setFromCallable(0, 0, null).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.setFromCallable(0, 0, null).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Bulk Setting
     * @since   AugList V2
     * @see     src.AugList#setMany()
     */
    @Test
    public void testSetMany() {
        setupTestData();
        assertTrue(testDataDouble.clone().setMany(null, null).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(null, new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(null, new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(), new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(null, new AugList<Double>(7.0, 5.0, 11.0, -2.5)).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1, 2, 5, 4), null).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(), new AugList<Double>(7.0, 5.0, 11.0, -2.5)).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1, 2, 5, 4), new AugList<Double>()).isEquivalent(testDataDouble));
        AugList<Double> alt = testDataDouble.clone();
        alt.set(1, 2.0);
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1), new AugList<Double>(2.0)).isEquivalent(alt));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1, 1), new AugList<Double>(3.0, 2.0)).isEquivalent(alt));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1, 4), new AugList<Double>(3.0, 2.0)).isEquivalent("[1.0, 3.0, 7.11, -2.5, 2.0]"));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(4, 1), new AugList<Double>(2.0, 3.0)).isEquivalent("[1.0, 3.0, 7.11, -2.5, 2.0]"));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1, 4, 5), new AugList<Double>(3.0, 2.0)).isEquivalent("[1.0, 3.0, 7.11, -2.5, 2.0]"));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(1, 4), new AugList<Double>(3.0, 2.0, 5.0)).isEquivalent("[1.0, 3.0, 7.11, -2.5, 2.0]"));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(-1), new AugList<Double>(-1.5)).isEquivalent("[-1.5, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.clone().setMany(new AugList<Integer>(99), new AugList<Double>(-1.5)).isEquivalent("[1.0, 2.0, 7.11, -2.5, -1.5]"));
        assertTrue(testDataString.clone().setMany(null, null).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.clone().setMany(null, null).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Set Intersection
     * @since   AugList V1
     * @see     src.AugList#setIntersection()
     */
    @Test
    public void testSetIntersection() {
        setupTestData();
        assertTrue(testDataDouble.setIntersection(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.setIntersection(testDataDouble).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).isEquivalent("/"));
        assertTrue(testDataDouble.setIntersection(testDataDouble).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>()).isEquivalent("/"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(1.0, 7.11, 3.1415926)).isEquivalent("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).isEquivalent("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).isEquivalent("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(7.11, 1.0)).isEquivalent("[1.0, 7.11]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        assertTrue(testDataString.setIntersection(new AugList<String>()).isEquivalent("/"));
        assertTrue(testDataString.setIntersection(new AugList<String>("the")).isEquivalent("[the]"));
        assertTrue(testDataString.setIntersection(new AugList<String>("the", "the")).isEquivalent("[the]"));
        assertTrue(testDataString.setIntersection(new AugList<String>("the", "the", "the")).isEquivalent("[the]"));
        assertTrue(testDataString.setIntersection(new AugList<String>("the", "fox", "lazy", "dog")).isEquivalent("[the, fox]"));
        assertTrue(testDataDouble.setIntersection(nullAL).isEquivalent("/"));
    }

    /**
     * JUnit tester for Set Union
     * @since   AugList V1
     * @see     src.AugList#setUnion()
     */
    @Test
    public void testSetUnion() {
        setupTestData();
        assertTrue(testDataDouble.setUnion(new AugList<Double>()).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.setUnion(new AugList<Double>()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        //testDataDouble.setUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).forEach(System.out::println);
        assertTrue(testDataDouble.setUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0]"));
        assertTrue(testDataDouble.setUnion(testDataDouble).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.setUnion(new AugList<Double>()).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.setUnion(new AugList<Double>(1.0, 7.11, 3.1415926)).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.setUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926, 100.0]"));
        assertTrue(testDataDouble.setUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataString = testDataString.oneToOneMapCopy(s -> s.toLowerCase());
        AugList<String> TDStrSet = testDataString.withoutIndex(6);
        assertTrue(testDataString.setUnion(new AugList<String>()).isEquivalent(TDStrSet));
        assertTrue(testDataString.setUnion(new AugList<String>("the")).isEquivalent("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.setUnion(new AugList<String>("the", "the")).isEquivalent("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.setUnion(new AugList<String>("the", "the", "the")).isEquivalent("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataString.setUnion(new AugList<String>("the", "fox", "lazy", "dog")).isEquivalent("[the, quick, brown, fox, jumps, over, lazy dog, lazy, dog]"));
        assertTrue(testDataDouble.setUnion(nullAL).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Shuffling
     * @since   AugList V1
     * @see     src.AugList#shuffleCopy()
     */
    @Test
    public void testShuffleCopy() {
        testIsRearrangement();
        assertTrue(testDataDouble.shuffleCopy().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.shuffleCopy().parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Shuffling
     * @since   AugList V2
     * @see     src.AugList#shuffleSelf()
     */
    @Test
    public void testShuffleSelf() {
        testIsRearrangement();
        assertTrue(testDataDouble.shuffleSelf().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.shuffleSelf().parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Length
     * @since   AugList V1
     * @see     src.AugList#size()
     */
    @Test
    public void testSize() {
        setupTestData();
        // Return type is primitive so cannot be tested
        assertTrue(testDataDouble.size() == 5);
        assertTrue(testDataString.size() == 8);
        assertTrue(testDataInt.size() == 8);
    }

    /**
     * JUnit tester for Conditional Omittance (Forwards)
     * @since   AugList V1
     * @see     src.AugList#skipWhile()
     */
    @Test
    public void testSkipWhile() {
        setupTestData();
        assertTrue(testDataDouble.skipWhile(d -> false).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.skipWhile(d -> false).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.skipWhile(d -> d < 0).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.skipWhile(d -> d > 0).isEquivalent("[-2.5, 3.1415926]"));
        assertTrue(testDataString.skipWhile(s -> s.length() != 9).isEquivalent("/"));
        assertTrue(testDataDouble.skipWhile(null).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for sorting
     * @since   AugList V1
     * @see     src.AugList#sort()
     */
    @Test
    public void testSort() {
        setupTestData();
        // Sort ascending
        testDataDouble.sort(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int)(o1 - o2);
            }
        });
        assertTrue(testDataDouble.isEquivalent("[-2.5, 1.0, 2.0, 3.1415926, 7.11]"));
        // Sort descending
        testDataDouble.sort(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int)(o2 - o1);
            }
        });
        assertTrue(testDataDouble.isEquivalent("[7.11, 3.1415926, 2.0, 1.0, -2.5]"));
        // Sort descending, then sort by 3rd digit ascending
        testDataDouble.sort(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                // Correct for negative values
                if (o1 < 0) { o1 = -o1; }
                if (o2 < 0) { o2 = -o2; }
                String left = new String (new char[] { o1.toString().charAt(2) });
                String right = new String (new char[] { o2.toString().charAt(2) });
                return (int)(Integer.parseInt(left) - Integer.parseInt(right));
            }
        });
        assertTrue(testDataDouble.isEquivalent("[2.0, 1.0, 7.11, 3.1415926, -2.5]"));
        Comparator<Double> allEqual = new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return 0;
            }
        };
        assertTrue(testDataDouble.sort(allEqual).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.sort(allEqual).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.sort(null).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Splitting
     * @since   AugList V2
     * @see     src.AugList#split3(int)
     */
    @Test
    public void testSplit3() {
        setupTestData();
        assertTrue(testDataDouble.split3(1).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.split3(1).get(0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.split3(1).get(1).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.split3(1).get(2).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.split3(1).get(0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.split3(1).get(1).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.split3(1).get(2).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.split3(0).get(0).size() == 0);
        assertTrue(testDataDouble.split3(1).get(0).size() == 1);
        assertTrue(testDataDouble.split3(4).get(2).size() == 0);
        for (int i = 0; i < testDataDouble.size(); i++) {
            assertTrue(testDataDouble.split3(i).get(1).size() == 1);
            assertTrue(testDataDouble.split3(i).get(1).get(0).equals(testDataDouble.get(i)));
        }
        assertTrue(new AugList<Double>().split3(0).get(0).isEmpty());
        assertTrue(new AugList<Double>().split3(0).get(1).isEmpty());
        assertTrue(new AugList<Double>().split3(0).get(2).isEmpty());
        assertTrue(new AugList<Double>().split3(-99).get(0).isEmpty());
        assertTrue(new AugList<Double>().split3(99).get(1).isEmpty());
        assertTrue(new AugList<Double>().split3(9).get(2).isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.split3(-1); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.split3(99); });
        assertTrue(testDataDouble.split3(0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.split3(0).parameterizedTypeDesc().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.split3(1).get(0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Splitting by delimiter
     * @since   AugList V2
     * @see     src.AugList#splitDelim()
     */
    @Test
    public void testSplitDelim() {
        AugList<String> TDS = new AugList<String>("The", " ", "fat", " ", "cat", " ", "sat", " ", "on", " ", "the", " ", "mat.");
        assertTrue(TDS.splitDelim(s -> s.equals(" ")).isEquivalent("[[The], [fat], [cat], [sat], [on], [the], [mat.]]"));
        // TDS without " ": "[The, fat, cat, sat, on, the, mat.]"
        assertTrue(TDS.clone().withoutWhere(s -> s.equals(" ")).splitDelim(s -> s.length() == 2).isEquivalent("[[The, fat, cat, sat], [the, mat.]]"));
        assertTrue(TDS.clone().withoutWhere(s -> s.equals(" ")).splitDelim(s -> s.contains("at")).isEquivalent("[[The], /, /, [on, the], /]"));
        assertTrue(TDS.splitDelim(null).get(0).isEquivalent(TDS));
        assertTrue(TDS.splitDelim(null).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(TDS.splitDelim(null).parameterizedTypeDesc().equals("public class src.AugList<T>"));
        assertTrue(TDS.splitDelim(null).get(0).parameterizedTypeDesc().equals("public final class java.lang.String"));
    }

    /**
     * JUnit tester for {@link Spliterator} "Cast"
     * @since   AugList V1
     * @see     src.AugList#spliterator()
     */
    @Test
    public void testSpliterator() {
        setupTestData();
        Spliterator<Double> TDD_spliterator = testDataDouble.spliterator(),
                            ALD_spliterator = ARRLISTDOUBLE.spliterator();
        Spliterator<String> TDS_spliterator = testDataString.spliterator(),
                            ALS_spliterator = ARRLISTSTR.spliterator();
        Spliterator<Integer> TDI_spliterator = testDataInt.spliterator(),
                             ALI_spliterator = ARRLISTINT.spliterator();
        assertEquals(testDataDouble.spliterator().getClass().toGenericString(), ("final class java.util.ArrayList$ArrayListSpliterator"));
        assertTrue(new AugList<Double>(testDataDouble.spliterator()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertEquals(TDD_spliterator.characteristics(), ALD_spliterator.characteristics());
        assertEquals(TDD_spliterator.estimateSize(), ALD_spliterator.estimateSize());
        assertEquals(TDD_spliterator.getExactSizeIfKnown(), ALD_spliterator.getExactSizeIfKnown());
        assertEquals(TDS_spliterator.characteristics(), ALS_spliterator.characteristics());
        assertEquals(TDS_spliterator.estimateSize(), ALS_spliterator.estimateSize());
        assertEquals(TDS_spliterator.getExactSizeIfKnown(), ALS_spliterator.getExactSizeIfKnown());
        assertEquals(TDI_spliterator.characteristics(), ALI_spliterator.characteristics());
        assertEquals(TDI_spliterator.estimateSize(), ALI_spliterator.estimateSize());
        assertEquals(TDI_spliterator.getExactSizeIfKnown(), ALI_spliterator.getExactSizeIfKnown());
        assertTrue(TDD_spliterator.getClass().toGenericString().equals("final class java.util.ArrayList$ArrayListSpliterator"));
        assertTrue(TDS_spliterator.getClass().toGenericString().equals("final class java.util.ArrayList$ArrayListSpliterator"));
        assertTrue(TDI_spliterator.getClass().toGenericString().equals("final class java.util.ArrayList$ArrayListSpliterator"));
        // The hashcodes of i.e. testDataDouble.spliterator() and ARRLISTDOUBLE.spliterator() do not match,
        // so the only way to be 100% sure if the spliterators have the same underlying data,
        // is to take the spliterators and check functionality is identical.
    }

    /**
     * JUnit tester for {@link Stream} "cast"
     * @since   AugList V1
     * @see     src.AugList#stream()
     */
    @Test
    public void testStream() {
        setupTestData();
        assertTrue(testDataDouble.stream().count() == ARRLISTDOUBLE.stream().count());
        assertTrue(testDataDouble.stream().isParallel() == ARRLISTDOUBLE.stream().isParallel());
        assertTrue(testDataDouble.stream().iterator().hasNext() == ARRLISTDOUBLE.stream().iterator().hasNext());
        assertTrue(testDataString.stream().count() == ARRLISTSTR.stream().count());
        assertTrue(testDataString.stream().isParallel() == ARRLISTSTR.stream().isParallel());
        assertTrue(testDataString.stream().iterator().hasNext() == ARRLISTSTR.stream().iterator().hasNext());
        assertTrue(testDataInt.stream().count() == ARRLISTINT.stream().count());
        assertTrue(testDataInt.stream().isParallel() == ARRLISTINT.stream().isParallel());
        assertTrue(testDataInt.stream().iterator().hasNext() == ARRLISTINT.stream().iterator().hasNext());
        assertTrue(testDataDouble.stream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(testDataString.stream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(testDataInt.stream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(testDataDouble.stream().getClass().toGenericString().equals("static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>"));
        assertTrue(new AugList<Double>(testDataDouble.stream()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Sublist
     * @since   AugList V1
     * @see     src.AugList#subList()
     */
    @Test
    public void testSubList() {
        setupTestData();
        assertThrows(IllegalArgumentException.class, () -> { testDataDouble.subList(1, 0); });
        assertTrue(testDataString.subList(0, 0).size() == 0);
        assertTrue(testDataDouble.subList(1, 1).isEquivalent("/"));
        assertTrue(testDataDouble.subList(0, 1).isEquivalent("[1.0]"));
        assertTrue(testDataDouble.subList(0, 3).isEquivalent("[1.0, 2.0, 7.11]"));
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.subList(0, 999); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.subList(-1, 0); });
        assertTrue(testDataDouble.subList(0, 1).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.subList(0, 1).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Swapping
     * @since   AugList method since V1, tested since V2
     * @see     src.AugList#swap()
     */
    @Test
    public void testSwap() {
        setupTestData();
        assertTrue(testDataDouble.swap(0, 0).isEquivalent(testDataDouble));
        assertTrue(testDataDouble.swap(0, 1).isEquivalent("[2.0, 1.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataInt.swap(1, 0).isEquivalent("[11, 7, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataString.swap(-1, 1); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataString.swap(9999, 1); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataString.swap(1, -1); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataString.swap(1, 9999); });
        assertTrue(testDataDouble.swap(0, 0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.swap(0, 0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Partial Random Swapping
     * @since   AugList V2
     * @see     src.AugList#swapRandom(int)
     */
    @Test
    public void testSwapRandom() {
        setupTestData();
        AugList<Double> tddClone = testDataDouble.clone();
        int discrepancies = 0;
        testDataDouble.swapRandom(0);
        assertTrue(testDataDouble.get(0) != 1.0);
        for (int i = 1; i < testDataDouble.size(); i++) {
            if (testDataDouble.get(i) != tddClone.get(i)) {
                discrepancies++;
                assertTrue(testDataDouble.get(i) == 1.0);
            }
        }
        assertTrue(discrepancies == 1);
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRandom(999); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRandom(-1); });
        assertTrue(testDataDouble.swapRandom(0).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.swapRandom(0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for Full Random Swapping
     * @since   AugList V2
     * @see     src.AugList#swapRandom()
     */
    @Test
    public void testSwapRandomNoParam() {
        setupTestData();
        AugList<Double> tddClone = testDataDouble.clone();
        AugList<Integer> swappedIndices = new AugList<Integer>();
        int discrepancies = 0;
        testDataDouble.swapRandom();
        for (int i = 0; i < testDataDouble.size(); i++) {
            if (testDataDouble.get(i) != tddClone.get(i)) {
                discrepancies++;
                swappedIndices.add(i);
            }
        }
        assertTrue(discrepancies == 2);
        assertTrue(testDataDouble.get(swappedIndices.get(0)) == tddClone.get(swappedIndices.get(1)));
        assertTrue(testDataDouble.get(swappedIndices.get(1)) == tddClone.get(swappedIndices.get(0)));
        assertTrue(testDataDouble.swapRandom().parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.swapRandom().getClass().toGenericString().equals("public class src.AugList<T>"));
    }

    /**
     * JUnit tester for Bulk Swapping
     * @since   AugList V2
     * @see     src.AugList#swapRanges()
     */
    @Test
    public void testSwapRanges() {
        setupTestData();
        assertTrue(testDataDouble.clone().swapRanges(0, 1, 1, 2).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.clone().swapRanges(0, 1, 1, 2).getClass().toGenericString().equals("public class src.AugList<T>"));
        //                                                                                          Original: [1.0, 2.0, 7.11, -2.5, 3.1415926]
        assertTrue(testDataDouble.clone().swapRanges(0, 1, 1, 2).isEquivalent("[2.0, 1.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.clone().swapRanges(0, 1, 2, 3).isEquivalent("[7.11, 2.0, 1.0, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.clone().swapRanges(0, 2, 2, 3).isEquivalent("[7.11, 1.0, 2.0, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.clone().swapRanges(0, 2, 3, 4).isEquivalent("[-2.5, 7.11, 1.0, 2.0, 3.1415926]"));
        assertTrue(testDataDouble.clone().swapRanges(0, 2, 4, 5).isEquivalent("[3.1415926, 7.11, -2.5, 1.0, 2.0]"));
        assertTrue(testDataDouble.clone().swapRanges(1, 0, 1, 2).isEquivalent("[2.0, 1.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.clone().swapRanges(0, 1, 2, 1).isEquivalent("[2.0, 1.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.clone().swapRanges(1, 2, 0, 1).isEquivalent("[2.0, 1.0, 7.11, -2.5, 3.1415926]"));
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(-1, 0, 0, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(11, 0, 0, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(0, -1, 0, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(0, 11, 0, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(0, 0, -1, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(0, 0, 11, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(0, 0, 0, -1); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.swapRanges(0, 0, 0, 11); });
        assertThrows(IllegalArgumentException.class, () -> { testDataDouble.swapRanges(0, 3, 1, 2); });
    }

    /**
     * JUnit tester for Conditional Collection
     * @since   AugList V1
     * @see     src.AugList#takeWhile()
     */
    @Test
    public void testTakeWhile() {
        setupTestData();
        assertTrue(testDataDouble.takeWhile(d -> true).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.takeWhile(d -> true).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.takeWhile(d -> d < 0).isEquivalent("[1.0]"));
        assertTrue(testDataDouble.takeWhile(d -> d > 0).isEquivalent("[1.0, 2.0, 7.11, -2.5]"));
        assertTrue(testDataString.takeWhile(s -> s.length() == 9).isEquivalent("[The]"));
        assertTrue(testDataString.takeWhile(s -> s.length() != 9).isEquivalent(testDataString));
        assertTrue(testDataDouble.clone().takeWhile(null).isEquivalent("/"));
    }

    /**
     * JUnit tester for Array Casting
     * @since   AugList V1
     * @see     src.AugList#toArray()
     */
    @Test
    public void testToArrayGivenType() {
        setupTestData();
        assertTrue(testDataDouble.toArray(new Double[] { })[2] == 7.11);
        String[] tDStrArr = testDataString.toArray(new String[] { });
        tDStrArr[2] = "red";
        assertTrue(tDStrArr[2] == "red");
        assertTrue(tDStrArr.getClass().toGenericString().equals("java.lang.String[]"));
        assertTrue(new AugList<String>(tDStrArr).parameterizedTypeDesc().equals("public final class java.lang.String"));
        assertThrows(NullPointerException.class, () -> { testDataDouble.toArray(nullArr); });
    }

    /**
     * JUnit tester for {@link Collection} casting
     * @since   AugList V2
     * @see     src.AugList#toCollection()
     */
    @Test
    public void testToCollection() {
        setupTestData();
        String collStr = testDataDouble.toCollection().getClass().toGenericString();
        // A class string is formatted in the following manner:
        //      <MODIFIERS> <VISIBILITY> class <CLASSNAME>$<OTHER>
        // i.e. public class Object
        //      final class java.util.ArrayList$ArrayListSpliterator
        //      static class java.util.stream.ReferencePipeline$Head<E_IN,E_OUT>
        // Lambda and anonymous expressions get a number, so an example for collStr is:
        //      class src.AugList$18
        //      
        assertTrue(collStr.contains("class src.AugList$"));
        AugList<Character> collAL = new AugList<Character>();
        for (int i = 0; i < collStr.toCharArray().length; i++) {
            collAL.add(collStr.toCharArray()[i]);
        }
        collAL.removeIf(ch -> (ch + "").toUpperCase() != (ch + "").toLowerCase());
        collAL.forEach(ch -> {
            try {
                Integer.valueOf((ch + ""));
            } catch (Exception e) {
                System.out.println(ch);
                System.out.println(collAL);
                fail();
            }
        });
        assertTrue(testDataDouble.toCollection().getClass().toGenericString().contains("class src.AugList$"));
        assertTrue(new AugList<Double>(testDataDouble.toCollection()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for {@link Enumeration} casting
     * @since   AugList V2
     * @see     src.AugList#toEnumeration()
     */
    @Test
    public void testToEnumeration() {
        setupTestData();
        String enumDesc = testDataDouble.toEnumeration().getClass().toGenericString();
        assertTrue(enumDesc.equals("private class java.util.Hashtable$Enumerator<T>"));
        assertTrue(new AugList<Double>(testDataDouble.toEnumeration()).isRearrangement(testDataDouble));
        assertTrue(testDataDouble.toEnumeration().getClass().toGenericString().equals("private class java.util.Hashtable$Enumerator<T>"));
        assertTrue(new AugList<Double>(testDataDouble.toEnumeration()).parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    /**
     * JUnit tester for {@link String} representation (Or String casting)
     * @since   AugList V1
     * @see     src.AugList#toString()
     */
    @Test
    public void testToString() {
        setupTestData();
        assertTrue(testDataDouble.isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.isEquivalent("[The, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataInt.isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(new AugList<String>("").isEquivalent("[]"));
        assertTrue(new AugList<String>("", "").isEquivalent("[, ]"));
        assertTrue(new AugList<String>("", "", "").isEquivalent("[, , ]"));
        assertTrue(new AugList<String>("", ",", ",,").isEquivalent("[, ,, ,,]"));
        assertTrue(new AugList<String>(" ", ", ", "  ,, ").isEquivalent("[ , , ,   ,, ]"));
        assertTrue(new AugList<String>(null, null, null).isEquivalent("[*null*, *null*, *null*]"));
        assertTrue(testDataDouble.toString().getClass().toGenericString().equals("public final class java.lang.String"));
    }

    /**
     * JUnit tester for Single Removal
     * @since   AugList V1
     * @see     src.AugList#without()
     */
    @Test
	public void testWithout() {
        setupTestData();
        assertTrue(testDataDouble.without(18).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.without(1.0).isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.without("quick").isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.without("quick").isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.without("lazy dog").isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.without(8).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.without(19).isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.without(null).isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertTrue(testDataDouble.without(-7.27).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.without(-7.27).parameterizedTypeDesc().equals("public final class java.lang.Double"));
	}

    /**
     * JUnit tester for Bulk Removal
     * @since   AugList V1
     * @see     src.AugList#withoutAll(src.AugList)
     */
    @Test
	public void testWithoutAll() {
        setupTestData();
        assertTrue(testDataDouble.withoutAll(new AugList<Double>(18.0)).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.withoutAll(new AugList<Double>(1.0)).isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.withoutAll(new AugList<String>("quick")).isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutAll(new AugList<String>("quick")).isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutAll(new AugList<String>("quick", "lazy dog")).isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutAll(new AugList<Integer>(8)).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutAll(new AugList<Integer>(19, 43, 7, 7)).isEquivalent("[11, -24, 117, 145, -56]"));
        assertTrue((new AugList<Integer>(1, 2).withoutAll(new AugList<Integer>()).isEquivalent("[1, 2]")));
        assertTrue(testDataDouble.withoutAll(new AugList<Double>(-7.27)).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.withoutAll(new AugList<Double>(-7.27)).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.clone().withoutAll(nullAL).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Varargs Bulk Removal
     * @since   AugList V1
     * @see     src.AugList#withoutAll(Object...) withoutAll(T...)
     */
    @Test
	public void testWithoutAllVarargs() {
		setupTestData();
        assertTrue(testDataDouble.withoutAll(18.0).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.withoutAll(1.0).isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.withoutAll("quick").isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutAll("quick").isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutAll("quick", "lazy dog").isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutAll(8).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutAll(19, 43, 7, 7).isEquivalent("[11, -24, 117, 145, -56]"));
        assertTrue((new AugList<Integer>(1, 2).withoutAll().isEquivalent("[1, 2]")));
        assertTrue(testDataDouble.withoutAll(-7.27).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.withoutAll(-7.27).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.clone().withoutAll(nullArr).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Targeted Removal
     * @since   AugList V1
     * @see     src.AugList#withoutIndex()
     */
    @Test
	public void testWithoutIndex() {
        setupTestData();
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.withoutIndex(18); });
        assertTrue(testDataDouble.withoutIndex(0).isEquivalent("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataString.withoutIndex(1).isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutIndex(6).isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutIndex(2).isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertTrue(testDataDouble.withoutIndex(0).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.withoutIndex(0).getClass().toGenericString().equals("public class src.AugList<T>"));
	}

    /**
     * JUnit tester for Tail Removal
     * @since   AugList V1
     * @see     src.AugList#withoutLast()
     */
    @Test
	public void testWithoutLast() {
        setupTestData();
        assertTrue(testDataDouble.withoutLast().isEquivalent("[1.0, 2.0, 7.11, -2.5]"));
        assertTrue(testDataDouble.withoutLast().isEquivalent("[1.0, 2.0, 7.11]"));
        assertTrue(testDataString.withoutLast().isEquivalent("[The, quick, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutLast().isEquivalent("[7, 11, 19, -24, 117, 145, -56]"));
        assertTrue(testDataDouble.withoutLast().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.withoutLast().parameterizedTypeDesc().equals("public final class java.lang.Double"));
	}

    /**
     * JUnit tester for Conditional Removal
     * @since   AugList V1
     * @see     src.AugList#withoutWhere()
     */
    @Test
	public void testWithoutWhere() {
        setupTestData();
        assertTrue(testDataDouble.withoutWhere(d -> d > 18).isEquivalent("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.withoutWhere(d -> d <= 1.0).isEquivalent("[2.0, 7.11, 3.1415926]"));
        assertTrue(testDataString.withoutWhere(s -> s.equals("quick")).isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutWhere(s -> s.equals("quick")).isEquivalent("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataString.withoutWhere(s -> s.length() == 8).isEquivalent("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutWhere(i -> i == 8).isEquivalent("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutWhere(i -> 18 <= i && i <= 20).isEquivalent("[7, 11, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutWhere(a -> a.equals(null)).isEquivalent(testDataInt));
        assertTrue(testDataDouble.withoutWhere(d -> false).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.withoutWhere(d -> false).parameterizedTypeDesc().equals("public final class java.lang.Double"));
        assertTrue(testDataDouble.clone().withoutWhere(null).isEquivalent(testDataDouble));
    }

    /**
     * JUnit tester for Random Removal
     * @since   AugList V2
     * @see     src.AugList#withoutRandom()
     */
    @Test
	public void testWithoutRandom() {
        setupTestData();
        AugList<AugList<Double>> validStates = new AugList<AugList<Double>>(
            new AugList<Double>(2.0, 7.11, -2.5, 3.1415926),
            new AugList<Double>(1.0, 7.11, -2.5, 3.1415926),
            new AugList<Double>(1.0, 2.0, -2.5, 3.1415926),
            new AugList<Double>(1.0, 2.0, 7.11, 3.1415926),
            new AugList<Double>(1.0, 2.0, 7.11, -2.5)
        );
        AugList<Double> tddWithout = testDataDouble.withoutRandom();
        Integer stateIndex = -1;
        /*
         * Starting with 
         * [1.0, 2.0, 7.11, -2.5, 3.1415926], 
         * the possible states after a random deletion are:
         * 
         * tddWithout
         * [     2.0, 7.11, -2.5, 3.1415926],
         * [1.0,      7.11, -2.5, 3.1415926],
         * [1.0, 2.0,       -2.5, 3.1415926],
         * [1.0, 2.0, 7.11,       3.1415926],
         * [1.0, 2.0, 7.11, -2.5           ],
         * 
         * Looking at the removed column, it is identical to tddClone.
         * So, by taking the 5 valid testDataDouble states and putting them into another AugList (validStates),
         * Indexing of the expected list state and its associated removed item is made very simple.
         */
        if (testDataDouble.get(0) != 1.0) {
            stateIndex = 0;
        }
        else if (testDataDouble.get(1) != 2.0) {
            stateIndex = 1;
        }
        else if (testDataDouble.get(2) != 7.11) {
            stateIndex = 2;
        }
        else if (testDataDouble.get(3) != -2.5) {
            stateIndex = 3;
        }
        else {
            stateIndex = 4;
        }
        assertTrue(validStates.get(stateIndex).isEquivalent(tddWithout));
        assertThrows(NoSuchElementException.class, () -> { new AugList<Double>().withoutRandom(); });
        assertTrue(testDataDouble.withoutRandom().getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(testDataDouble.withoutRandom().parameterizedTypeDesc().equals("public final class java.lang.Double"));
    }

    // #region Deprecated tests
    
    /**
     * JUnit tester for Capacity Increase
     * @deprecated  Since V2
     * @since       AugList V1
     * @see         src.AugList#ensureCapacity()
     */
    // @Test
    // public void testEnsureCapacity() {
    //     setupTestData();
    //     // As there is no way to measure the outcome of ArrayList<T>.EnsureCapacity,
    //     // (As the relevant fields are private)
    //     // This test is an auto-pass.
    // }

    /**
     * JUnit tester for Sublist
     * @deprecated  Since V2
     * @since       AugList V1
     * @see         src.AugList#subListToEnd()
     */
    // @SuppressWarnings("unlikely-arg-type")
    // @Test
    // public void testSubListToEnd() {
    //     setupTestData();
    //     assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.subListToEnd(-1); });
    //     assertThrows(IllegalArgumentException.class, () -> { testDataDouble.subListToEnd(999); });
    //     assertEquals(testDataDouble.subListToEnd(1), ("[2.0, 7.11, -2.5, 3.1415926]"));
    //     assertTrue(testDataDouble.subListToEnd(0).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
    //     assertTrue(testDataDouble.subListToEnd(3).equals("[-2.5, 3.1415926]"));
    // }

    /**
     * JUnit tester for Capacity Decrease
     * @deprecated  Since V2
     * @since       AugList V1
     * @see         src.AugList#trimToSize()
     */
    // @Test
    // public void testTrimToSize() {
    //     setupTestData();
    //     // There is no way to see how this method performs (due to being type void and the relevant field being private)
    //     // So this test auto-succeeds
    // }
    
    //#endregion
}
