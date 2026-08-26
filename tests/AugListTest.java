package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import src.*;
import java.lang.IndexOutOfBoundsException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
//import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
//import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;

public class AugListTest implements MultiTest {

    private AugList<Double> testDataDouble;
    private AugList<String> testDataStr;
    private AugList<Integer> testDataInt;

    final ArrayList<Double> ARRLISTDOUBLE = new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926));
    final ArrayList<String> ARRLISTSTR = new ArrayList<String>(Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy dog"));
    final ArrayList<Integer> ARRLISTINT = new ArrayList<Integer>(Arrays.asList(7, 11, 19, -24, 117, 145, -56, 43));

    @Override
    public void setupTestData() {
        //if (testDataDouble == null)
        //{
            testDataDouble = new AugList<Double>(1.0, 2.0, 7.11, -2.5, 3.1415926);
            testDataStr = new AugList<String>("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy dog");
            testDataInt = new AugList<Integer>(7, 11, 19, -24, 117, 145, -56, 43);
            // As strings, the test data is:
            // [1.0, 2.0, 7.11, -2.5, 3.1415926]
            // [The, quick, brown, fox, jumps, over, the, lazy dog]
            // [7, 11, 19, -24, 117, 145, -56, 43]
        //}
    }

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
        testCountsOfElements();
        testDistinctCopy();
        testDistinctSelf();
        // testEnsureCapacity();
        testEquals();
        testFilterCopy();
        testFilterSelf();
        testForEach();
        testFragment();
        testGet();
        testGetLast();
        testGetRandom();
        testHashCode();
        testIndexOf();
        testInitSpecial();
        testInsert();
        testInsertAll();
        testInsertAllVarargs();
        testInsertAllAtRandom();
        testInsertAllAtRandomVarargs();
        testInsertAtRandom();
        testIsEmpty();
        testIsEqual();
        testIsRearrangement();
        testIterator();
        testLastIndexOf();
        testListDifference();
        testListIntersection();
        testListIterator();
        testListUnion();
        testListIteratorFromIndex();
        testOneToOneMap();
        testPairUp();
        testParallelStream();
        testRemove();
        testRemoveAll();
        testRemoveAllVarargs();
        testRemoveAtIndex();
        testRemoveIf();
        testRemoveLast();
        testRemoveRandom();
        testReversed();
        testSample();
        testSet();
        testSetDifference();
        testSetIntersection();
        testSetUnion();
        testSize();
        testShuffleCopy();
        testShuffleSelf();
        testSkipWhile();
        testSort();
        testSpliterator();
        testStream();
        testSwap();
        testSwapRandom();
        testSwapRandomNoParam();
        testSubList();
        testSubListToEnd();
        testTakeWhile();
        //testToArrayGivenGenerator();
        testToArrayGivenType();
        //TODO
        testToCollection();
        testToString();
        //testTrimToSize();
        testWithout();
        testWithoutAll();
        testWithoutAllVarargs();
        testWithoutIndex();
        testWithoutIndex();
        testWithoutLast();
        testWithoutRandom();
        testWithoutWhere();
    }

    @Test
    public void testAdd() {
        setupTestData();
        testDataDouble.add(7.0);
        assertTrue(testDataDouble.toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0]"));
        testDataStr.add("!");
        assertTrue(testDataStr.toString().equals("[The, quick, brown, fox, jumps, over, the, lazy dog, !]"));
        testDataInt.add(25);
        assertTrue(testDataInt.toString().equals("[7, 11, 19, -24, 117, 145, -56, 43, 25]"));
    }

    @Test
    public void testAddAll() {
        setupTestData();
        testDataDouble.addAll(new AugList<Double>(7.0));
        assertTrue(testDataDouble.toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0]"));
        testDataStr.addAll(new AugList<String>("!", "qwerty"));
        assertTrue(testDataStr.toString().equals("[The, quick, brown, fox, jumps, over, the, lazy dog, !, qwerty]"));
        testDataInt.addAll(new AugList<Integer>(25, 125, 625));
        assertTrue(testDataInt.toString().equals("[7, 11, 19, -24, 117, 145, -56, 43, 25, 125, 625]"));
    }

    @Test
    public void testAddAllVarargs() {
        setupTestData();
        testDataDouble.addAll(7.0);
        assertTrue(testDataDouble.toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0]"));
        testDataStr.addAll("!", "qwerty");
        assertTrue(testDataStr.toString().equals("[The, quick, brown, fox, jumps, over, the, lazy dog, !, qwerty]"));
        testDataInt.addAll(25, 125, 625);
        assertTrue(testDataInt.toString().equals("[7, 11, 19, -24, 117, 145, -56, 43, 25, 125, 625]"));
    }

    @Test
    public void testAddFirst() {
        setupTestData();
        testDataDouble.addFirst(7.0);
        assertTrue(testDataDouble.toString().equals("[7.0, 1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataStr.addFirst("!");
        assertTrue(testDataStr.toString().equals("[!, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        testDataInt.addFirst(25);
        assertTrue(testDataInt.toString().equals("[25, 7, 11, 19, -24, 117, 145, -56, 43]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    // A string is a type which has the possibility of equivalence to an AugList, so the compiler warning can be suppressed.
    @Test
    public void testAllIndicesOf() {
        setupTestData();
        assertTrue(testDataDouble.allIndicesOf(-2.0).size() == 0);
        //testDataStr.oneToOneMap(s -> s.toLowerCase()).forEach(System.out::println);
        System.out.println(testDataStr.oneToOneMap(s -> s.toLowerCase()).allIndicesOf("the"));
        assertTrue(testDataStr.oneToOneMap(s -> s.toLowerCase()).allIndicesOf("the").size() == 2);
        assertTrue(testDataInt.add(7).allIndicesOf(7).equals("[0, 8]"));
    }

    @Test
    public void testAllSatisfy() {
        setupTestData();
        assertTrue(testDataDouble.allSatisfy(d -> d > -3.0));
        assertFalse(testDataDouble.allSatisfy(d -> d > -2.0));
        assertTrue(testDataStr.allSatisfy(s -> s.length() != 0));
        assertFalse(testDataStr.allSatisfy(s -> s.length() == 1));
        assertTrue(testDataInt.allSatisfy(i -> -60 < i && i < 150));
        assertFalse(testDataInt.allSatisfy(i -> 0 < i && i < 100));
    }

    @Test
    public void testAnySatisfy() {
        setupTestData();
        assertTrue(testDataDouble.anySatisfy(d -> d == -2.5));
        assertFalse(testDataDouble.anySatisfy(d -> 0 < d && d < 0.5));
        assertTrue(testDataStr.anySatisfy(s -> s.length() == 4));
        assertFalse(testDataStr.anySatisfy(s -> s.length() > 100));
        assertTrue(testDataInt.anySatisfy(i -> 3 < i && i < 8));
        assertFalse(testDataInt.anySatisfy(i -> 4 < i && i < 6));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testApplyAll() {
        setupTestData();
        assertTrue(testDataDouble.applyAll(d -> d + 1).equals("[2.0, 3.0, 8.11, -1.5, 4.1415926]"));
        assertEquals(testDataStr.applyAll(s -> s.substring(0, 1)), "[T, q, b, f, j, o, t, l]");
        assertTrue(testDataInt.applyAll(i -> 2 * i).equals("[14, 22, 38, -48, 234, 290, -112, 86]"));
    }

    @Test
    public void testChunk() {
        setupTestData();
        assertTrue(testDataDouble.chunk(3).toString().equals("[[1.0, 2.0, 7.11], [-2.5, 3.1415926]]"));
        assertTrue(testDataStr.chunk(3).toString().equals("[[The, quick, brown], [fox, jumps, over], [the, lazy dog]]"));
        assertTrue(testDataInt.chunk(1).toString().equals("[[7], [11], [19], [-24], [117], [145], [-56], [43]]"));
        assertThrows(IllegalArgumentException.class, 
            () -> { 
                testDataDouble.chunk(0);
            }
        );
    }

    @Test
    public void testClear() {
        setupTestData();
        testDataDouble.clear();
        assertTrue(testDataDouble.size() == 0);
        testDataStr.clear();
        assertTrue(testDataStr.isEmpty());
        testDataInt.clear();
        assertTrue(testDataInt.size() == 0);
    }

    @Test
    public void testClone() {
        setupTestData();
        // assertNotEquals checks for both contents and memory locations being identical to throw (which by use of clone() will never be true.)
        assertEquals(testDataDouble.clone().oneToOneMap(x -> x), testDataDouble);
        assertEquals(testDataStr.clone().distinctCopy(), testDataStr.distinctCopy());
        assertEquals(testDataInt.clone().filterCopy(x -> x != 100), testDataInt);
        assertNotSame(testDataDouble, testDataDouble.clone());
        assertNotSame(testDataStr, testDataStr.clone());
        assertNotSame(testDataInt, testDataInt.clone());
    }

    @Test
    public void testContains() {
        setupTestData();
        assertTrue(testDataDouble.contains(3.1415926));
        assertFalse(testDataDouble.contains(99999.9));
        assertTrue(testDataStr.contains("fox"));
        assertFalse(testDataStr.contains("Lizard"));
        assertTrue(testDataInt.contains(11));
        assertFalse(testDataInt.contains(17));
    }

    @Test
    public void testContainsAll() {
        setupTestData();
        assertTrue(testDataDouble.containsAll(new AugList<Double>(3.1415926d)));
        assertTrue(testDataDouble.containsAll(new AugList<Double>(-2.5, 3.1415926d)));
        assertTrue(testDataDouble.containsAll(new AugList<Double>(3.1415926d, -2.5))); // Test reversed order
        assertFalse(testDataDouble.containsAll(new AugList<Double>(99999.9)));
        assertFalse(testDataDouble.containsAll(new AugList<Double>(-2.5, 99999.9)));
        assertTrue(testDataStr.containsAll(new AugList<String>("fox")));
        assertFalse(testDataStr.containsAll(new AugList<String>("Lizard")));
        assertTrue(testDataInt.containsAll(new AugList<Integer> (7, 11)));
        assertFalse(testDataInt.containsAll(new AugList<Integer>(7, 11, 17)));
    }

    @Test
    public void testContainsAllVarargs() {
        setupTestData();
        assertTrue(testDataDouble.containsAll(3.1415926d));
        assertTrue(testDataDouble.containsAll(2.0, 3.1415926d));
        assertTrue(testDataDouble.containsAll(3.1415926d, -2.5)); // Test reversed order
        assertFalse(testDataDouble.containsAll(99999.9));
        assertFalse(testDataDouble.containsAll(-2.5, 99999.9));
        assertTrue(testDataStr.containsAll("fox"));
        assertFalse(testDataStr.containsAll("Lizard"));
        assertTrue(testDataInt.containsAll(7, 11));
        assertFalse(testDataInt.containsAll(7, 11, 17));
    }

    @Test
    public void testContainsAny(){
        setupTestData();
        assertTrue(testDataDouble.containsAny(new AugList<Double>(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0)));
        assertTrue(testDataStr.containsAny(new AugList<String>("", "A", "an", "the", "this", "that", "there")));
        assertTrue(testDataInt.containsAny(new AugList<Integer>(2, 3, 5, 7, 11, 13, 17, 19)));
        assertFalse(testDataDouble.containsAny(new AugList<Double>()));
    }

    @Test
    public void testContainsAnyVarargs() {
        setupTestData();
        assertTrue(testDataDouble.containsAny(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0));
        assertTrue(testDataStr.containsAny("", "A", "an", "the", "this", "that", "there"));
        assertTrue(testDataInt.containsAny(2, 3, 5, 7, 11, 13, 17, 19));
        assertFalse(testDataDouble.containsAny());
    }

    @Test
    public void testCountsOfElements() {
        setupTestData();
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.countsOfElements().get("the") == 2);
        assertTrue(testDataStr.countsOfElements().get("THE") == null);
        testDataDouble.addAll(7.0, 11.0, -2.5, 7.11, 7.11);
        assertTrue(testDataDouble.countsOfElements().get(7.11) == 3);
        assertTrue(testDataDouble.countsOfElements().get(-2.5) == 2);
        assertTrue(testDataDouble.countsOfElements().get(7.0) == 1);
        assertTrue(testDataDouble.countsOfElements().get(12.0) == null);
    }

    @Test
    public void testDistinctCopy() {
        setupTestData();
        testDataDouble.addAll(1.0, 7.2, -9.221, 1.0, 7.11, -2.5, 19.0);
        assertTrue(testDataDouble.distinctCopy().toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.2, -9.221, 19.0]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toUpperCase());
        assertTrue(testDataStr.distinctCopy().toString().equals("[THE, QUICK, BROWN, FOX, JUMPS, OVER, LAZY DOG]"));
        testDataInt.addAll(new AugList<Integer>(7, 8, 11, 19, 117, 119, 145, 145));
        assertTrue(testDataInt.distinctCopy().toString().equals("[7, 11, 19, -24, 117, 145, -56, 43, 8, 119]"));
    }

    @Test
    public void testDistinctSelf() {
        setupTestData();
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        final AugList<String> LOWERSTRCLONE = testDataStr.clone();
        assertEquals(LOWERSTRCLONE, testDataStr);
        assertNotEquals(LOWERSTRCLONE, testDataStr.distinctSelf());
        // distinctSelf() modifies the instance so the equality breaks.
        assertNotEquals(LOWERSTRCLONE, testDataStr);
        assertEquals(testDataStr, "[the, quick, brown, fox, jumps, over, lazy dog]");
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals() {
        setupTestData();
        final AugList<String> TESTDATA = new AugList<String>("Hello", "World");
        assertTrue(TESTDATA.equals(TESTDATA));
        // AugList.Equals() has been overridden so that as long as the contents of the lists match, they evaluate as equal.
        assertTrue(TESTDATA.equals(new AugList<String>("Hello", "World")));
        assertEquals(TESTDATA, new AugList<String>("Hello", "World"));
        assertNotSame(TESTDATA, new AugList<String>("Hello", "World"));
        assertNotEquals(testDataDouble.clone().oneToOneMap(d -> d + 1), testDataDouble);
        assertNotEquals(testDataStr.clone().oneToOneMap(s -> s.toLowerCase()), testDataStr.distinctCopy());
        assertNotEquals(testDataInt.clone().filterCopy(i -> i == 117), testDataInt);
        assertNotEquals(testDataDouble, testDataStr);
        assertEquals(testDataDouble, "[1.0, 2.0, 7.11, -2.5, 3.1415926]");
        assertNotEquals(testDataDouble, "[1.0, 2.0, -2.5, 3.1415926, 7.11]");
        assertNotEquals(testDataDouble, testDataDouble.clone().swap(0,1));
        assertEquals(testDataDouble, new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926)));
        assertNotEquals(testDataDouble, new AugList<Double>());
        assertEquals(new AugList<Double>(), new AugList<Double>());
        assertTrue(new AugList<Double>().equals(new AugList<String>()));
        assertFalse(new AugList<Double>().equals(""));
        assertFalse(new AugList<Double>(7.0).equals(7.0));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testFilterCopy() {
        setupTestData();
        final AugList<Double> TESTDATADOUBLECOPY = testDataDouble.clone();
        final AugList<String> TESTDATASTRCOPY = testDataStr.clone();
        final AugList<Integer> TESTDATAINTCOPY = testDataInt.clone();
        assertTrue(testDataDouble.filterCopy(d -> d > 2.5).equals("[7.11, 3.1415926]"));
        assertTrue(testDataStr.filterCopy(s -> s.length() == 3).equals("[The, fox, the]"));
        assertTrue(testDataInt.filterCopy(i -> i % 2 == 0).equals("[-24, -56]"));
        assertEquals(testDataDouble, TESTDATADOUBLECOPY);
        assertEquals(testDataStr, TESTDATASTRCOPY);
        assertEquals(testDataInt, TESTDATAINTCOPY);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testFilterSelf() {
        setupTestData();
        final AugList<Double> TESTDATADOUBLECOPY = testDataDouble.clone();
        final AugList<String> TESTDATASTRCOPY = testDataStr.clone();
        final AugList<Integer> TESTDATAINTCOPY = testDataInt.clone();
        assertTrue(testDataDouble.filterSelf(d -> d > 2.5).equals("[7.11, 3.1415926]"));
        assertTrue(testDataStr.filterSelf(s -> s.length() == 3).equals("[The, fox, the]"));
        assertTrue(testDataInt.filterSelf(i -> i % 2 == 0).equals("[-24, -56]"));
        assertNotEquals(testDataDouble, TESTDATADOUBLECOPY);
        assertNotEquals(testDataStr, TESTDATASTRCOPY);
        assertNotEquals(testDataInt, TESTDATAINTCOPY);
    }

    @Test
    public void testForEach() {
        setupTestData();
        // Since the return type is void, there is no way to automatically check the test works.
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
        testDataStr.forEach(System.out::println);
        testDataInt.forEach(System.out::println);
    }

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
                assertTrue(false);
            }
        }
    }

    @Test
    public void testGet() {
        setupTestData();
        assertTrue(testDataDouble.get(0) == 1.0);
        assertTrue(testDataDouble.get(2) == 7.11);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testDataDouble.get(1000);
        });
        assertTrue(testDataStr.get(0) == "The");
        assertTrue(testDataStr.get(3) == "fox");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testDataStr.get(-1);
        });
        assertTrue(testDataInt.get(0) == 7);
        assertTrue(testDataInt.get(1) == 11);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testDataInt.get(-711);
        });
    }

    @Test
    public void testGetLast() {
        setupTestData();
        assertTrue(testDataDouble.getLast() == 3.1415926);
        assertTrue(testDataStr.getLast() == "lazy dog");
        assertTrue(testDataInt.getLast() == 43);
        assertThrows(NoSuchElementException.class, () -> {
            new AugList<Integer>().getLast();
        });
    }

    @Test
    public void testGetRandom() {
        setupTestData();
        assertTrue(testDataDouble.contains(testDataDouble.getRandom()));
        assertTrue(testDataInt.contains(testDataInt.getRandom()));
        assertTrue(testDataStr.contains(testDataStr.getRandom()));
    }

    @Test
    public void testHashCode() {
        setupTestData();
        assertTrue(testDataDouble.hashCode() == ARRLISTDOUBLE.hashCode());
        assertTrue(testDataStr.hashCode() == ARRLISTSTR.hashCode());
        assertTrue(testDataInt.hashCode() == ARRLISTINT.hashCode());
    }

    @Test
    public void testIndexOf() {
        setupTestData();
        assertTrue(testDataDouble.indexOf(1.0) == 0);
        assertTrue(testDataDouble.indexOf(3.1415926) == 4);
        assertTrue(testDataDouble.indexOf(77) == -1);
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.indexOf("the") == 0);
        assertTrue(testDataStr.indexOf("jumps") == 4);
        assertTrue(testDataStr.indexOf("") == -1);
        assertTrue(testDataInt.indexOf(117) == 4);
        assertTrue(testDataInt.indexOf(43) == 7);
        assertTrue(testDataInt.indexOf(711) == -1);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testInitSpecial() {
        setupTestData();
        // From List<T> (Or any class that implements it) (Which implements Iterable<T>)
        assertTrue(new AugList<String>(new ArrayList<String>(Arrays.asList(""))).add("1").get(1).equals("1"));
        // From LinkedList<T> (Which implements Iterable<T>)
        LinkedList<String> l = new LinkedList<String>();
        assertTrue(new AugList<String>(l).equals("/"));
        // From Vector<T> (Which implements Iterable<T>)
        Vector<String> v = new Vector<String>();
        assertTrue(new AugList<String>(v).equals("/"));
        // From Iterator<T>
        assertTrue(new AugList<String>(testDataStr.iterator()).equals(testDataStr));
        // From Enumeration<T>
        assertTrue(new AugList<String>(testDataStr.countsOfElements().keys()).isRearrangement(testDataStr));
        // From Spliterator<T>
        assertTrue(new AugList<String>(new AugList<String>("b", "a", "ce", "ddd").spliterator()).equals("[b, a, ce, ddd]"));
        // From AugList<T> (Which implements Iterable<T>)
        assertTrue(new AugList<String>(testDataStr).equals(testDataStr));
        // From HashSet<T> (and LinkedHashSet<T>) (Which both implement Iterable<T>)
        HashSet<String> h = new HashSet<String>();
        assertTrue(new AugList<String>(h).equals("/"));
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        assertTrue(new AugList<String>(lhs).equals("/"));
        h.add("Hello");
        h.add("World");
        h.add("!");
        assertTrue(new AugList<String>(h).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From TreeSet<T> (Which implements Iterable<T>)
        TreeSet<String> t = new TreeSet<String>();
        assertTrue(new AugList<String>(t).equals("/"));
        t.add("Hello");
        t.add("World");
        t.add("!");
        assertTrue(new AugList<String>(t).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From any class that implements Deque<T> (Which implements Iterable<T>)
        ArrayDeque<String> ad = new ArrayDeque<String>();
        assertTrue(new AugList<String>(ad).equals("/"));
        ad.add("Hello");
        ad.add("World");
        ad.add("!");
        assertTrue(new AugList<String>(ad).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From a PriorityQueue<T> (Which implements Iterable<T>)
        PriorityQueue<String> pq = new PriorityQueue<String>();
        assertTrue(new AugList<String>(pq).equals("/"));
        pq.add("Hello");
        pq.add("World");
        pq.add("!");
        assertTrue(new AugList<String>(pq).isRearrangement(new AugList<String>("Hello", "World", "!")));
        // From a Stream<T>
        Stream<Integer> s = Arrays.stream(Arrays.asList(1).toArray((new Integer[2])));
        assertTrue(new AugList<Integer>(s).equals("[1, *null*]"));
        // From a set of values and counts
        assertEquals(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"), new AugList<Integer>(1, 3, 4, 2)), "[a, b, b, b, cd, cd, cd, cd, eef, eef]");
        assertEquals(new AugList<String>(new AugList<String>("a", "b", "cd", "eef", "aaaaaaaa"), new AugList<Integer>(1, 3, 4, 2)), "[a, b, b, b, cd, cd, cd, cd, eef, eef]");
        assertEquals(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"), new AugList<Integer>(1, 3, 4, 2, 999)), "[a, b, b, b, cd, cd, cd, cd, eef, eef]");
        assertEquals(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"), new AugList<Integer>(1, 0, 4, 0)), "[a, cd, cd, cd, cd]");
        assertEquals(new AugList<String>(new AugList<String>("a", "b", "cd", "eef"), new AugList<Integer>(1, -999, 4, -7)), "[a, cd, cd, cd, cd]");
    }

    @Test
    public void testInsert() {
        setupTestData();
        testDataDouble.insert(1,7.0);
        assertTrue(testDataDouble.toString().equals("[1.0, 7.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataStr.insert(0, "!");
        assertTrue(testDataStr.toString().equals("[!, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        testDataInt.insert(2, 25);
        assertTrue(testDataInt.toString().equals("[7, 11, 25, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                testDataDouble.insert(1000, 1.0);
            }
        );
    }

    @Test
    public void testInsertAll() {
        setupTestData();
        testDataDouble.insertAll(1, new AugList<Double>(7.0));
        assertTrue(testDataDouble.toString().equals("[1.0, 7.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataStr.insertAll(0, new AugList<String>("!", "qwerty"));
        assertTrue(testDataStr.toString().equals("[!, qwerty, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        testDataInt.insertAll(2, new AugList<Integer>(25, 125, 625));
        assertTrue(testDataInt.toString().equals("[7, 11, 25, 125, 625, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                testDataDouble.insertAll(1000, new AugList<Double>(1.0));
            }
        );
    }

    @Test
    public void testInsertAllVarargs() {
        setupTestData();
        testDataDouble.insertAll(1, 7.0);
        assertTrue(testDataDouble.toString().equals("[1.0, 7.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataStr.insertAll(0, "!", "qwerty");
        assertTrue(testDataStr.toString().equals("[!, qwerty, The, quick, brown, fox, jumps, over, the, lazy dog]"));
        //It is not possible to use the varargs overload here due to overload ambiguity between varargs only and index + varargs arguments, so this test is skipped.
        testDataInt.insertAll(2, 25, 125, 625);
        assertTrue(testDataInt.toString().equals("[7, 11, 25, 125, 625, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                testDataDouble.insertAll(1000, new AugList<Double>(1.0));
            }
        );
    }

    @Test
    public void testInsertAllAtRandom() {
        setupTestData();
        AugList<Double> expectedALD = testDataDouble.clone().addAll(new AugList<Double>(7.0));
        testDataDouble.insertAllAtRandom(new AugList<Double>(7.0));
        assertTrue(expectedALD.isRearrangement(testDataDouble));
        AugList<String> expectedALS = testDataStr.clone().addAll(new AugList<String>("!", "qwerty"));
        testDataStr.insertAllAtRandom(new AugList<String>("!", "qwerty"));
        assertTrue(expectedALS.isRearrangement(testDataStr));
        AugList<Integer> expectedALI = testDataInt.clone().addAll(new AugList<Integer>(25, 125, 625));
        testDataInt.insertAllAtRandom(new AugList<Integer>(25, 125, 625));
        assertTrue(expectedALI.isRearrangement(testDataInt));
    }

    @Test
    public void testInsertAllAtRandomVarargs() {
        setupTestData();
        AugList<Double> expectedALD = testDataDouble.clone().addAll(new AugList<Double>(7.0));
        testDataDouble.insertAllAtRandom(7.0);
        assertTrue(expectedALD.isRearrangement(testDataDouble));
        AugList<String> expectedALS = testDataStr.clone().addAll(new AugList<String>("!", "qwerty"));
        testDataStr.insertAllAtRandom("!", "qwerty");
        assertTrue(expectedALS.isRearrangement(testDataStr));
        AugList<Integer> expectedALI = testDataInt.clone().addAll(new AugList<Integer>(25, 125, 625));
        testDataInt.insertAllAtRandom(25, 125, 625);
        assertTrue(expectedALI.isRearrangement(testDataInt));
    }

    @Test
    public void testInsertAtRandom() {
        setupTestData();
        AugList<Double> expectedALD = testDataDouble.clone().add(7.0);
        testDataDouble.insertAtRandom(7.0);
        assertTrue(testDataDouble.isRearrangement(expectedALD));
        AugList<String> expectedALS = testDataStr.clone().add("!");
        testDataStr.insertAtRandom("!");
        assertTrue(testDataStr.isRearrangement(expectedALS));
        AugList<Integer> expectedALI = testDataInt.clone().add(25);
        testDataInt.insertAtRandom(25);
        assertTrue(testDataInt.isRearrangement(expectedALI));
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testIsEmpty() {
        setupTestData();
        assertFalse(testDataDouble.isEmpty());
        assertFalse(testDataStr.isEmpty());
        assertFalse(testDataInt.isEmpty());
        assertTrue(testDataInt.clear().isEmpty());
        assertTrue(new AugList().isEmpty());
    }

    @Test
    public void testIsEqual() {
        setupTestData();
        assertEquals(testDataDouble, testDataDouble);
        assertEquals(testDataInt, testDataInt);
        assertEquals(testDataStr, testDataStr);
        assertNotEquals(testDataDouble, testDataInt);
        assertEquals(testDataDouble, "[1.0, 2.0, 7.11, -2.5, 3.1415926]");
        assertNotEquals(testDataDouble, "[1, 2, 7.11, -2.5, 3.1415926]");
        assertEquals(testDataDouble, testDataDouble.clone());
        assertEquals(testDataDouble, new AugList<Double>(1.0, 2.0, 7.11, -2.5, 3.1415926));
        assertNotEquals(testDataInt, new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, -56.0, 43.0));
        assertEquals(testDataDouble, new ArrayList<Double>(Arrays.asList(1.0, 2.0, 7.11, -2.5, 3.1415926)));
        AugList<Double> shuffled = testDataDouble.shuffleCopy();
        if (shuffled.equals(testDataDouble)) {
            shuffled.swap(0, 1);
        }
        assertNotEquals(testDataDouble, shuffled);
    }

    // As all 3 of isRearrangement(), shuffleSelf() and shuffleCopy() are tested here,
    // making testShuffleSelf() and testShuffleCopy() call testIsRearrangement()
    // preserves the integrity of the tests whilst saving file size.

    @Test
    public void testIsRearrangement() {
        setupTestData();
        assertTrue(testDataDouble.isRearrangement(testDataDouble));
        assertTrue(testDataDouble.isRearrangement(testDataDouble.shuffleCopy()));
        assertTrue(testDataStr.isRearrangement(testDataStr.shuffleCopy()));
        assertTrue(testDataInt.isRearrangement(testDataInt.shuffleCopy()));
        AugList<Double> tddClone = testDataDouble.clone();
        assertTrue(tddClone.isRearrangement(testDataDouble.shuffleSelf()));
        // If a list could not shuffle to itself, it would need checking.
        assertFalse(testDataDouble.isRearrangement(new AugList<Double>()));
        assertFalse(new AugList<Double>().isRearrangement(testDataDouble));
        assertTrue(new AugList<Double>().isRearrangement(new AugList<Double>()));
        assertFalse(new AugList<Double>(1.0).isRearrangement(new AugList<Double>(2.0)));
    }

    @Test
    public void testShuffleCopy() {
        testIsRearrangement();
    }

    @Test
    public void testShuffleSelf() {
        testIsRearrangement();
    }

    @Test
    public void testIterator() {
        setupTestData();
        Iterator<Double> TDD_iterator = testDataDouble.iterator(),
                         ALD_iterator = ARRLISTDOUBLE.iterator();
        Iterator<String> TDS_iterator = testDataStr.iterator(),
                         ALS_iterator = ARRLISTSTR.iterator();
        Iterator<Integer> TDI_iterator = testDataInt.iterator(),
                          ALI_iterator = ARRLISTINT.iterator();
        while (TDD_iterator.hasNext()) {
            assertEquals(TDD_iterator.next(), ALD_iterator.next());
        }
        while (TDS_iterator.hasNext()) {
            assertEquals(TDS_iterator.next(), ALS_iterator.next());
        }
        while (TDI_iterator.hasNext()) {
            assertEquals(TDI_iterator.next(), ALI_iterator.next());
        }
        // The hashcodes of i.e. testDataDouble.iterator() and ARRLISTDOUBLE.iterator() do not match,
        // so the only way to be 100% sure if they iterators have the same underlying data,
        // is to take elements from each iterator until they are exhausted,
        // and check each pair is identical.
    }

    @Test
    public void testLastIndexOf() {
        setupTestData();
        assertTrue(testDataDouble.lastIndexOf(1.0) == 0);
        assertTrue(testDataDouble.lastIndexOf(3.1415926) == 4);
        assertTrue(testDataDouble.lastIndexOf(77) == -1);
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.lastIndexOf("the") == 6);
        assertTrue(testDataStr.lastIndexOf("jumps") == 4);
        assertTrue(testDataStr.lastIndexOf("") == -1);
        assertTrue(testDataInt.lastIndexOf(117) == 4);
        assertTrue(testDataInt.lastIndexOf(43) == 7);
        assertTrue(testDataInt.lastIndexOf(711) == -1);
    }

    @SuppressWarnings("unlikely-arg-type") // A string can be equal to an AugList, so this warning is suppressed.
    @Test
    public void testListDifference() {
        setupTestData();
        assertTrue(testDataDouble.listDifference(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).equals(testDataDouble));
        assertTrue(testDataDouble.listDifference(testDataDouble).equals("/"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>()).equals(testDataDouble));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(1.0, 7.11, 3.1415926)).equals("[2.0, -2.5]"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).equals("[2.0, -2.5]"));
        assertTrue(testDataDouble.listDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).equals("[2.0, -2.5]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.listDifference(new AugList<String>()).equals(testDataStr));
        assertTrue(testDataStr.listDifference(new AugList<String>("the")).equals("[quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.listDifference(new AugList<String>("the", "the")).equals("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataStr.listDifference(new AugList<String>("the", "the", "the")).equals("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataStr.listDifference(new AugList<String>("the", "fox", "lazy", "dog")).equals("[quick, brown, jumps, over, the, lazy dog]"));
    }

    @SuppressWarnings("unlikely-arg-type") // A string can be equal to an AugList, so this warning is suppressed.
    @Test
    public void testListIntersection() {
        setupTestData();
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).equals("/"));
        assertTrue(testDataDouble.listIntersection(testDataDouble).equals(testDataDouble));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>()).equals("/"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(1.0, 7.11, 3.1415926)).equals("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).equals("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).equals("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.listIntersection(new AugList<Double>(7.11, 1.0)).equals("[1.0, 7.11]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.listIntersection(new AugList<String>()).equals("/"));
        assertTrue(testDataStr.listIntersection(new AugList<String>("the")).equals("[the]"));
        assertTrue(testDataStr.listIntersection(new AugList<String>("the", "the")).equals("[the, the]"));
        assertTrue(testDataStr.listIntersection(new AugList<String>("the", "the", "the")).equals("[the, the]"));
        assertTrue(testDataStr.listIntersection(new AugList<String>("the", "fox", "lazy", "dog")).equals("[the, fox]"));
    }
    
    @Test
    public void testListIterator() {
        setupTestData();
        ListIterator<Double> TDD_lIterator = testDataDouble.listIterator(),
                             ALD_lIterator = ARRLISTDOUBLE.listIterator();
        ListIterator<String> TDS_lIterator = testDataStr.listIterator(),
                             ALS_lIterator = ARRLISTSTR.listIterator();
        ListIterator<Integer> TDI_lIterator = testDataInt.listIterator(),
                              ALI_lIterator = ARRLISTINT.listIterator();
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
        // The hashcodes of i.e. testDataDouble.listIterator() and ARRLISTDOUBLE.listIterator() do not match,
        // so the only way to be 100% sure if they iterators have the same underlying data,
        // is to take elements from each iterator until they are exhausted,
        // and check for each pair, all methods act identically across both copies.
    }

    @Test
    public void testListIteratorFromIndex() {
        setupTestData();
        ListIterator<Double> TDD_lIterator = testDataDouble.listIterator(3),
                             ALD_lIterator = ARRLISTDOUBLE.listIterator(3);
        ListIterator<String> TDS_lIterator = testDataStr.listIterator(2),
                             ALS_lIterator = ARRLISTSTR.listIterator(2);
        ListIterator<Integer> TDI_lIterator = testDataInt.listIterator(1),
                              ALI_lIterator = ARRLISTINT.listIterator(1);
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
        // The hashcodes of i.e. testDataDouble.listIterator() and ARRLISTDOUBLE.listIterator() do not match,
        // so the only way to be 100% sure if they iterators have the same underlying data,
        // is to take elements from each iterator until they are exhausted,
        // and check for each pair, all methods act identically across both copies.
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testListUnion() {
        setupTestData();
        //testDataDouble.listUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).forEach(System.out::println);
        assertTrue(testDataDouble.listUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0]"));
        assertTrue(testDataDouble.listUnion(testDataDouble).equals(testDataDouble));
        assertTrue(testDataDouble.listUnion(new AugList<Double>()).equals(testDataDouble));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(1.0, 7.11, 3.1415926)).equals(testDataDouble));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 100.0]"));
        assertTrue(testDataDouble.listUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 1.0]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.listUnion(new AugList<String>()).equals(testDataStr));
        assertTrue(testDataStr.listUnion(new AugList<String>("the")).equals("[the, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.listUnion(new AugList<String>("the", "the")).equals("[the, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.listUnion(new AugList<String>("the", "the", "the")).equals("[the, quick, brown, fox, jumps, over, the, lazy dog, the]"));
        assertTrue(testDataStr.listUnion(new AugList<String>("the", "fox", "lazy", "dog")).equals("[the, quick, brown, fox, jumps, over, the, lazy dog, lazy, dog]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testOneToOneMap() {
        setupTestData();
        assertTrue(testDataDouble.oneToOneMap(d -> 0).allSatisfy(d -> d == 0));
        assertTrue(testDataDouble.oneToOneMap(d -> d - 1).equals("[0.0, 1.0, 6.11, -3.5, 2.1415926]"));
        assertTrue(testDataStr.oneToOneMap(d -> d.length()).get(2) == 5);
        assertTrue(testDataInt.oneToOneMap(i -> i % 2 == 0).countsOfElements().get(true) == 2);
    }

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
    }

    @Test
    public void testParallelStream() {
        setupTestData();
        // Hashcodes don't match so we need to check every method has same functionality
        // However this involves needing to deal with methods that alter state...
        // For our use cases, we will assume that the state-altering methods will not be invoked.
        assertTrue(testDataDouble.parallelStream().count() == ARRLISTDOUBLE.parallelStream().count());
        assertTrue(testDataDouble.parallelStream().isParallel() == ARRLISTDOUBLE.parallelStream().isParallel());
        assertTrue(testDataDouble.parallelStream().iterator().hasNext() == ARRLISTDOUBLE.parallelStream().iterator().hasNext());
        assertTrue(testDataStr.parallelStream().count() == ARRLISTSTR.parallelStream().count());
        assertTrue(testDataStr.parallelStream().isParallel() == ARRLISTSTR.parallelStream().isParallel());
        assertTrue(testDataStr.parallelStream().iterator().hasNext() == ARRLISTSTR.parallelStream().iterator().hasNext());
        assertTrue(testDataInt.parallelStream().count() == ARRLISTINT.parallelStream().count());
        assertTrue(testDataInt.parallelStream().isParallel() == ARRLISTINT.parallelStream().isParallel());
        assertTrue(testDataInt.parallelStream().iterator().hasNext() == ARRLISTINT.parallelStream().iterator().hasNext());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testRemove() {
        setupTestData();
        assertFalse(testDataDouble.remove(18));
        assertTrue(testDataDouble.equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.remove(1.0));
        assertTrue(testDataDouble.equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.remove("quick"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataStr.remove("quick"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.remove("lazy dog"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the]"));
        assertFalse(testDataInt.remove(8));
        assertTrue(testDataInt.equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.remove(19));
        assertTrue(testDataInt.equals("[7, 11, -24, 117, 145, -56, 43]"));
        assertFalse(testDataInt.remove(null));
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testRemoveAll() {
        setupTestData();
        assertFalse(testDataDouble.removeAll(new AugList<Double>(18.0)));
        assertTrue(testDataDouble.equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.removeAll(new AugList<Double>(1.0)));
        assertTrue(testDataDouble.equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.removeAll(new AugList<String>("quick")));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataStr.removeAll(new AugList<String>("quick")));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.removeAll(new AugList<String>("quick", "lazy dog")));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the]"));
        assertFalse(testDataInt.removeAll(new AugList<Integer>(8)));
        assertTrue(testDataInt.equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.removeAll(new AugList<Integer>(19)));
        assertTrue(testDataInt.equals("[7, 11, -24, 117, 145, -56, 43]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testRemoveAllVarargs() {
        setupTestData();
        assertFalse(testDataDouble.removeAll(18.0));
        assertTrue(testDataDouble.equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.removeAll(1.0));
        assertTrue(testDataDouble.equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.removeAll("quick"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataStr.removeAll("quick"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataInt.removeAll(8));
        assertTrue(testDataInt.equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.removeAll(19));
        assertTrue(testDataInt.equals("[7, 11, -24, 117, 145, -56, 43]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testRemoveAtIndex() {
        setupTestData();
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.removeAt(18); });
        assertTrue(testDataDouble.removeAt(0) == 1.0f);
        assertTrue(testDataDouble.equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.removeAt(1).equals("quick"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.removeAt(6).equals("lazy dog"));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.removeAt(2) == 19);
        assertTrue(testDataInt.equals("[7, 11, -24, 117, 145, -56, 43]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testRemoveIf() {
        setupTestData();
        assertFalse(testDataDouble.removeIf(d -> d > 18));
        assertTrue(testDataDouble.equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.removeIf(d -> d <= 1.0));
        assertTrue(testDataDouble.equals("[2.0, 7.11, 3.1415926]"));
        assertTrue(testDataStr.removeIf(s -> s.equals("quick")));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertFalse(testDataStr.removeIf(s -> s.equals("quick")));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.removeIf(s -> s.length() == 8));
        assertTrue(testDataStr.equals("[The, brown, fox, jumps, over, the]"));
        assertFalse(testDataInt.removeIf(i -> i == 8));
        assertTrue(testDataInt.equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.removeIf(i -> 18 <= i && i <= 20));
        assertTrue(testDataInt.equals("[7, 11, -24, 117, 145, -56, 43]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testRemoveLast() {
        setupTestData();
        assertFalse(testDataDouble.removeLast() == 3.1415926f);
        assertTrue(testDataDouble.equals("[1.0, 2.0, 7.11, -2.5]"));
        assertTrue(testDataDouble.removeLast() == -2.5f);
        assertTrue(testDataDouble.equals("[1.0, 2.0, 7.11]"));
        assertTrue(testDataStr.removeLast() == "lazy dog");
        assertTrue(testDataStr.equals("[The, quick, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.removeLast() == 43);
        assertTrue(testDataInt.equals("[7, 11, 19, -24, 117, 145, -56]"));
        assertThrows(NoSuchElementException.class, () -> { (new AugList<String>()).removeLast(); });
    }

    @Test
    public void testRemoveRandom() {
        setupTestData();
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
        assertTrue(validStates.get(stateIndex).equals(testDataDouble));
        assertTrue(tddClone.get(stateIndex).equals(removed));
        assertThrows(NoSuchElementException.class, () -> { new AugList<Double>().removeRandom(); });
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testReversed() {
        setupTestData();
        assertTrue(testDataDouble.reversed().equals("[3.1415926, -2.5, 7.11, 2.0, 1.0]"));
        assertTrue(testDataStr.reversed().equals("[lazy dog, the, over, jumps, fox, brown, quick, The]"));
        assertTrue(testDataInt.reversed().equals("[43, -56, 145, 117, -24, 19, 11, 7]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSample() {
        setupTestData();
        assertTrue(testDataDouble.sample(5, false).isRearrangement(testDataDouble));
        assertTrue(testDataDouble.sample(0, false).equals("/"));
        assertTrue(testDataDouble.containsAll(testDataDouble.sample(1, false)));
        // Technically speaking this assertion could fail, but the probability of the such is .8^100000000, a value so small it is effectively 0.
        // (Plus, as the sample algorithm is only psuedo-random, I imagine that this assertion passing can be proven as either guaranteed or not.)
        assertTrue(testDataDouble.sample(100000000, true).contains(1.0));
        assertThrows(IllegalArgumentException.class, () -> { testDataStr.sample(10000000, false); });
        assertThrows(IllegalArgumentException.class, () -> { testDataInt.sample(-10, false); });
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSet() {
        setupTestData();
        assertTrue(testDataDouble.set(0, 42.0d) == 1.0d);
        assertTrue(testDataDouble.get(0) == 42.0d);
        assertTrue(testDataDouble.equals("[42.0, 2.0, 7.11, -2.5, 3.1415926]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSetDifference() {
        setupTestData();
        assertTrue(testDataDouble.setDifference(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).equals(testDataDouble));
        assertEquals(testDataDouble.setDifference(testDataDouble), ("/"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>()).equals(testDataDouble));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(1.0, 7.11, 3.1415926)).equals("[2.0, -2.5]"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).equals("[2.0, -2.5]"));
        assertTrue(testDataDouble.setDifference(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).equals("[2.0, -2.5]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        AugList<String> TDStrSet = testDataStr.clone();
        TDStrSet.removeAt(6);
        assertEquals(testDataStr.setDifference(new AugList<String>()), (TDStrSet));
        assertTrue(testDataStr.setDifference(new AugList<String>("the")).equals("[quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataStr.setDifference(new AugList<String>("the", "the")).equals("[quick, brown, fox, jumps, over, lazy dog]"));
        assertEquals(testDataStr.setDifference(new AugList<String>("the", "fox", "lazy", "dog")), ("[quick, brown, jumps, over, lazy dog]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSetIntersection() {
        setupTestData();
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).equals("/"));
        assertTrue(testDataDouble.setIntersection(testDataDouble).equals(testDataDouble));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>()).equals("/"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(1.0, 7.11, 3.1415926)).equals("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).equals("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).equals("[1.0, 7.11, 3.1415926]"));
        assertTrue(testDataDouble.setIntersection(new AugList<Double>(7.11, 1.0)).equals("[1.0, 7.11]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        assertTrue(testDataStr.setIntersection(new AugList<String>()).equals("/"));
        assertTrue(testDataStr.setIntersection(new AugList<String>("the")).equals("[the]"));
        assertTrue(testDataStr.setIntersection(new AugList<String>("the", "the")).equals("[the]"));
        assertTrue(testDataStr.setIntersection(new AugList<String>("the", "the", "the")).equals("[the]"));
        assertTrue(testDataStr.setIntersection(new AugList<String>("the", "fox", "lazy", "dog")).equals("[the, fox]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSetUnion() {
        setupTestData();
        //testDataDouble.setUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).forEach(System.out::println);
        assertTrue(testDataDouble.setUnion(new AugList<Double>(7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 7.0, 11.0, 19.0, -24.0, 117.0, 145.0, -56.0, 43.0]"));
        assertEquals(testDataDouble.setUnion(testDataDouble), (testDataDouble));
        assertTrue(testDataDouble.setUnion(new AugList<Double>()).equals(testDataDouble));
        assertTrue(testDataDouble.setUnion(new AugList<Double>(1.0, 7.11, 3.1415926)).equals(testDataDouble));
        assertTrue(testDataDouble.setUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 100.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926, 100.0]"));
        assertTrue(testDataDouble.setUnion(new AugList<Double>(1.0, 7.11, 3.1415926, 1.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        testDataStr = testDataStr.oneToOneMap(s -> s.toLowerCase());
        AugList<String> TDStrSet = testDataStr.withoutIndex(6);
        assertEquals(testDataStr.setUnion(new AugList<String>()), (TDStrSet));
        assertEquals(testDataStr.setUnion(new AugList<String>("the")), ("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataStr.setUnion(new AugList<String>("the", "the")).equals("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertTrue(testDataStr.setUnion(new AugList<String>("the", "the", "the")).equals("[the, quick, brown, fox, jumps, over, lazy dog]"));
        assertEquals(testDataStr.setUnion(new AugList<String>("the", "fox", "lazy", "dog")), ("[the, quick, brown, fox, jumps, over, lazy dog, lazy, dog]"));
    }

    @Test
    public void testSize() {
        setupTestData();
        assertTrue(testDataDouble.size() == 5);
        assertTrue(testDataStr.size() == 8);
        assertTrue(testDataInt.size() == 8);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSkipWhile() {
        setupTestData();
        assertTrue(testDataDouble.skipWhile(d -> d < 0).equals(testDataDouble));
        assertTrue(testDataDouble.skipWhile(d -> d > 0).equals("[-2.5, 3.1415926]"));
        assertTrue(testDataStr.skipWhile(s -> s.length() != 9).equals("/"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSort() {
        setupTestData();
        // Sort ascending
        testDataDouble.sort(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int)(o1 - o2);
            }
        });
        assertTrue(testDataDouble.equals("[-2.5, 1.0, 2.0, 3.1415926, 7.11]"));
        // Sort descending
        testDataDouble.sort(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int)(o2 - o1);
            }
        });
        assertTrue(testDataDouble.equals("[7.11, 3.1415926, 2.0, 1.0, -2.5]"));
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
        assertEquals(testDataDouble, ("[2.0, 1.0, 7.11, 3.1415926, -2.5]"));
    }

    @Test
    public void testSpliterator() {
        setupTestData();
        Spliterator<Double> TDD_spliterator = testDataDouble.spliterator(),
                            ALD_spliterator = ARRLISTDOUBLE.spliterator();
        Spliterator<String> TDS_spliterator = testDataStr.spliterator(),
                            ALS_spliterator = ARRLISTSTR.spliterator();
        Spliterator<Integer> TDI_spliterator = testDataInt.spliterator(),
                             ALI_spliterator = ARRLISTINT.spliterator();
        assertEquals(TDD_spliterator.characteristics(), ALD_spliterator.characteristics());
        assertEquals(TDD_spliterator.estimateSize(), ALD_spliterator.estimateSize());
        assertEquals(TDD_spliterator.getExactSizeIfKnown(), ALD_spliterator.getExactSizeIfKnown());
        assertEquals(TDS_spliterator.characteristics(), ALS_spliterator.characteristics());
        assertEquals(TDS_spliterator.estimateSize(), ALS_spliterator.estimateSize());
        assertEquals(TDS_spliterator.getExactSizeIfKnown(), ALS_spliterator.getExactSizeIfKnown());
        assertEquals(TDI_spliterator.characteristics(), ALI_spliterator.characteristics());
        assertEquals(TDI_spliterator.estimateSize(), ALI_spliterator.estimateSize());
        assertEquals(TDI_spliterator.getExactSizeIfKnown(), ALI_spliterator.getExactSizeIfKnown());
        // The hashcodes of i.e. testDataDouble.spliterator() and ARRLISTDOUBLE.spliterator() do not match,
        // so the only way to be 100% sure if the spliterators have the same underlying data,
        // is to take the spliterators and check functionality is identical.
    }

    @Test
    public void testStream() {
        setupTestData();
        assertTrue(testDataDouble.stream().count() == ARRLISTDOUBLE.stream().count());
        assertTrue(testDataDouble.stream().isParallel() == ARRLISTDOUBLE.stream().isParallel());
        assertTrue(testDataDouble.stream().iterator().hasNext() == ARRLISTDOUBLE.stream().iterator().hasNext());
        assertTrue(testDataStr.stream().count() == ARRLISTSTR.stream().count());
        assertTrue(testDataStr.stream().isParallel() == ARRLISTSTR.stream().isParallel());
        assertTrue(testDataStr.stream().iterator().hasNext() == ARRLISTSTR.stream().iterator().hasNext());
        assertTrue(testDataInt.stream().count() == ARRLISTINT.stream().count());
        assertTrue(testDataInt.stream().isParallel() == ARRLISTINT.stream().isParallel());
        assertTrue(testDataInt.stream().iterator().hasNext() == ARRLISTINT.stream().iterator().hasNext());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSwap() {
        setupTestData();
        assertTrue(testDataDouble.swap(0, 0).equals(testDataDouble));
        assertTrue(testDataDouble.swap(0, 1).equals("[2.0, 1.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataInt.swap(1, 0).equals("[11, 7, 19, -24, 117, 145, -56, 43]"));
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataStr.swap(-1, 1); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataStr.swap(9999, 1); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataStr.swap(1, -1); });
        assertThrows(IndexOutOfBoundsException.class, () -> {testDataStr.swap(1, 9999); });
    }

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
    }

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
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSubList() {
        setupTestData();
        assertThrows(IllegalArgumentException.class, () -> { testDataDouble.subList(1, 0); });
        assertTrue(testDataDouble.subList(1, 1).equals("/"));
        assertTrue(testDataDouble.subList(0, 1).equals("[1.0]"));
        assertTrue(testDataDouble.subList(0, 3).equals("[1.0, 2.0, 7.11]"));
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.subList(0, 999); });
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.subList(-1, 0); });
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testSubListToEnd() {
        setupTestData();
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.subListToEnd(-1); });
        assertThrows(IllegalArgumentException.class, () -> { testDataDouble.subListToEnd(999); });
        assertEquals(testDataDouble.subListToEnd(1), ("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.subListToEnd(0).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.subListToEnd(3).equals("[-2.5, 3.1415926]"));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testTakeWhile() {
        setupTestData();
        assertTrue(testDataDouble.takeWhile(d -> d < 0).equals("[1.0]"));
        assertTrue(testDataDouble.takeWhile(d -> d > 0).equals("[1.0, 2.0, 7.11, -2.5]"));
        assertEquals(testDataStr.takeWhile(s -> s.length() == 9), ("[The]"));
        assertEquals(testDataStr.takeWhile(s -> s.length() != 9), (testDataStr));
    }

    @Test
    public void testToArrayGivenType() {
        setupTestData();
        assertTrue(testDataDouble.toArray(new Double[] { })[2] == 7.11);
        String[] tDStrArr = testDataStr.toArray(new String[] { });
        tDStrArr[2] = "red";
        assertTrue(tDStrArr[2] == "red");
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testToString() {
        setupTestData();
        assertTrue(testDataDouble.toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.toString().equals("[The, quick, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataInt.toString().equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertEquals(new AugList<String>(""), "[]");
        assertEquals(new AugList<String>("", ""), "[, ]");
        assertEquals(new AugList<String>("", "", ""), "[, , ]");
        assertEquals(new AugList<String>("", ",", ",,"), "[, ,, ,,]");
        assertEquals(new AugList<String>(" ", ", ", "  ,, "), "[ , , ,   ,, ]");
        assertTrue(new AugList<String>(null, null).equals("[*null*, *null*]"));
    }

	@SuppressWarnings("unlikely-arg-type")
    @Test
	public void testWithout() {
        setupTestData();
        assertTrue(testDataDouble.without(18).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.without(1.0).equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.without("quick").equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.without("quick").equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.without("lazy dog").equals("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.without(8).equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.without(19).equals("[7, 11, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.without(null).equals("[7, 11, -24, 117, 145, -56, 43]"));
	}

	@SuppressWarnings("unlikely-arg-type")
    @Test
	public void testWithoutAll() {
        setupTestData();
        assertTrue(testDataDouble.withoutAll(new AugList<Double>(18.0)).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.withoutAll(new AugList<Double>(1.0)).equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.withoutAll(new AugList<String>("quick")).equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutAll(new AugList<String>("quick")).equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutAll(new AugList<String>("quick", "lazy dog")).equals("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutAll(new AugList<Integer>(8)).equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutAll(new AugList<Integer>(19, 43, 7, 7)).equals("[11, -24, 117, 145, -56]"));
        assertTrue((new AugList<Integer>(1, 2).withoutAll(new AugList<Integer>()).equals("[1, 2]")));
	}

	@SuppressWarnings("unlikely-arg-type")
    @Test
	public void testWithoutAllVarargs() {
		setupTestData();
        assertTrue(testDataDouble.withoutAll(18.0).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.withoutAll(1.0).equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.withoutAll("quick").equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutAll("quick").equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutAll("quick", "lazy dog").equals("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutAll(8).equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutAll(19, 43, 7, 7).equals("[11, -24, 117, 145, -56]"));
        assertTrue((new AugList<Integer>(1, 2).withoutAll().equals("[1, 2]")));
	}

	@SuppressWarnings("unlikely-arg-type")
    @Test
	public void testWithoutIndex() {
        setupTestData();
        assertThrows(IndexOutOfBoundsException.class, () -> { testDataDouble.withoutIndex(18); });
        assertTrue(testDataDouble.withoutIndex(0).equals("[2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataStr.withoutIndex(1).equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutIndex(6).equals("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutIndex(2).equals("[7, 11, -24, 117, 145, -56, 43]"));
	}

	@SuppressWarnings("unlikely-arg-type")
    @Test
	public void testWithoutLast() {
        setupTestData();
        assertTrue(testDataDouble.withoutLast().equals("[1.0, 2.0, 7.11, -2.5]"));
        assertTrue(testDataDouble.withoutLast().equals("[1.0, 2.0, 7.11]"));
        assertTrue(testDataStr.withoutLast().equals("[The, quick, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutLast().equals("[7, 11, 19, -24, 117, 145, -56]"));
	}

	@SuppressWarnings("unlikely-arg-type")
    @Test
	public void testWithoutWhere() {
        setupTestData();
        assertTrue(testDataDouble.withoutWhere(d -> d > 18).equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(testDataDouble.withoutWhere(d -> d <= 1.0).equals("[2.0, 7.11, 3.1415926]"));
        assertTrue(testDataStr.withoutWhere(s -> s.equals("quick")).equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutWhere(s -> s.equals("quick")).equals("[The, brown, fox, jumps, over, the, lazy dog]"));
        assertTrue(testDataStr.withoutWhere(s -> s.length() == 8).equals("[The, brown, fox, jumps, over, the]"));
        assertTrue(testDataInt.withoutWhere(i -> i == 8).equals("[7, 11, 19, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutWhere(i -> 18 <= i && i <= 20).equals("[7, 11, -24, 117, 145, -56, 43]"));
        assertTrue(testDataInt.withoutWhere(a -> a.equals(null)).equals(testDataInt));
	}

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
        assertTrue(validStates.get(stateIndex).equals(tddWithout));
        assertThrows(NoSuchElementException.class, () -> { new AugList<Double>().withoutRandom(); });
    }

    // @Test
    // public void testEnsureCapacity() {
    //     setupTestData();
    //     // As there is no way to measure the outcome of ArrayList<T>.EnsureCapacity,
    //     // (As the relevant fields are private)
    //     // This test is an auto-pass.
    // }

    // @Test
    // public void testTrimToSize() {
    //     setupTestData();
    //     // There is no way to see how this method performs (due to being type void and the relevant field being private)
    //     // So this test auto-succeeds
    // }
}
