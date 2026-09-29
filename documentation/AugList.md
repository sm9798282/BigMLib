# AugList V2

## Preface

Java needs a better way of handling lists.
Lots of features in newer languages - C# for instance - simply do not have an analogue in Java.
AugList Version 1, started 2026-03-06 and finished 2026-05-22, was built as an answer to this problem.
Further revisions were made with the aim to expand on the initial vision - such as the latest version, V2.

## Dependencies

- Java JVM

## Features

AugList decorates an ArrayList, and thus encapsulates or modifies many of its methods.
AugList also adds methods inspired by C# methods, statistics, functional and declarative programming, set theory and more.

The main benefits to AugList are:

- Construct from any Iterable, Iterator variant, Vararg list, Stream, value-count pair or Enumeration
- Bulk operations
- Set theory operations
- Methods inspired by Functional and Declarative Programming

## Feature List

Provided with AugList V2 are the following:

- `new AugList<T>()`
- `new AugList<T>(T...)`
- `new AugList<T>(Enumeration<? super T>)`
- `new AugList<T>(Iterator<? super T>)`
- `new AugList<T>(Iterable<? super T>)`
- `new AugList<T>(ListIterator<? super T>)`
- `new AugList<T>(Iterable<? super T>, Iterable<Integer>)`
- `new AugList<T>(Spliterator<? super T>)`
- `new AugList<T>(Stream<? super T>)`
- `AugList<T>          add(T)`
- `AugList<T>          addAll(AugList<? super T>)`
- `AugList<T>          addAll(T...)`
- `AugList<T>          addFirst(T)`
- `AugList<T>          allIndicesOf(T)`
- `boolean             allSatisfy(Predicate<? super T>)`
- `boolean             anySatisfy(Predicate<? super T>)`
- `AugList<T>          applyAll(UnaryOperator<? super T>)`
- `AugList<AugList<T>> chunk(int)`
- `AugList<T>          clear()`
- `AugList<T>          clone()`
- `boolean             contains(T)`
- `boolean             containsAll(Iterable<? super T>)`
- `boolean             containsAll(T...)`
- `boolean             containsAny(Iterable<? super T>)`
- `boolean             containsAny(T...)`
- `int                 countOf()`
- `AugList<Integer>    countsOfElements()`
- `AugList<AugList<SimpleEntry<T, U>>> crossProduct(Iterable<? super U>)`
- `AugList<T>          distinctCopy()`
- `AugList<T>          distinctSelf()`
- `boolean             equals(Object)`
- `AugList<T>          filterCopy(Predicate<? super T>)`
- `AugList<T>          filterSelf(Predicate<? super T>)`
- `void                forEach(Consumer<? super T>)`
- `AugList<AugList<T>> fragment(int)`
- `T                   get(int)`
- `Class<?>            getClass()`
- `T                   getLast()`
- `T                   getRandom()`
- `int                 hashCode()`
- `int                 indexOf(Object)`
- `AugList<T>          insert(int, T)`
- `AugList<T>          insertAll(int, Iterable<? super T>)`
- `AugList<T>          insertAll(int, T...)`
- `AugList<T>          insertAllAtRandom(Iterable<? super T>)`
- `AugList<T>          insertAllAtRandom(T...)`
- `AugList<T>          insertAtRandom(T)`
- `boolean             isEmpty()`
- `boolean             isEquivalent()`
- `boolean             isPalindrome()`
- `boolean             isRearrangement(Iterable<? super T>)`
- `boolean             isSet()`
- `boolean             isSorted(Comparator<? super T>)`
- `Iterator<T>         iterator()`
- `boolean             lastIndexOf(Object)`
- `AugList<T>          listDifference(Iterable<? super T>)`
- `AugList<T>          listIntersection(Iterable<? super T>)`
- `ListIterator<T>     listIterator()`
- `ListIterator<T>     listIterator(int)`
- `AugList<T>          listUnion(Iterable<? super T>)`
- `AugList<T>          massOverwriteRandom(Iterable<? super T>)`
- `AugList<T>          massOverwriteRandom(T[])`
- `AugList<U>          oneToOneMapCopy(Function<? super T, U>)`
- `AugList<T>          overwriteRandom(T)`
- `void                notify()`
- `void                notifyAll()`
- `Hashtable<T, U>     pairUp(Iterable<U>)`
- `Stream<T>           parallelStream()`
- `String              parameterizedTypeDesc()`
- `boolean             remove(Object)`
- `boolean             removeAll(Iterable<? super T>)`
- `boolean             removeAll(T...)`
- `boolean             removeAt(int)`
- `boolean             removeIf(Predicate<? super T>)`
- `boolean             removeLast()`
- `boolean             removeRandom()`
- `AugList<T>          retainAll(Collection<? super T>)`
- `AugList<T>          reversed()`
- `AugList<T>          sample(int, boolean)`
- `T                   set(int, T)`
- `AugList<T>          setDifference(Iterable<? super T>)`
- `AugList<T>          setFromCallable(int, int, Callable<Boolean>)`
- `AugList<T>          setIntersection(Iterable<? super T>)`
- `AugList<T>          setMany(Iterable<Integer> indices, Iterable<? super T> values)`
- `AugList<T>          setUnion(Iterable<? super T>)`
- `AugList<T>          shuffleCopy()`
- `AugList<T>          shuffleSelf()`
- `int                 size()`
- `AugList<T>          skipWhile(Predicate<? super T>)`
- `AugList<T>          sort(Comparator<? super T>)`
- `AugList<AugList<T>> split3(int)`
- `AugList<AugList<T>> splitDelim(Function<? super T, Boolean>)`
- `Spliterator<T>      spliterator()`
- `Stream<T>           stream()`
- `AugList<T>          subList(int, int)`
- `AugList<T>          swap(int, int)`
- `AugList<T>          swapRandom(int)`
- `AugList<T>          swapRandom()`
- `AugList<T>          takeWhile(Predicate<? super T>)`
- `T[]                 toArray(T[])`
- `Collection<T>       toCollection()`
- `Enumeration<T>      toEnumeration()`
- `String              toString()`
- `void                wait()`
- `void                wait(long)`
- `void                wait(long, int)`
- `AugList<T>          without(T)`
- `AugList<T>          withoutAll(Iterable<? super T>)`
- `AugList<T>          withoutAll(T...)`
- `AugList<T>          withoutIndex(int)`
- `AugList<T>          withoutLast()`
- `AugList<T>          withoutRandom()`
- `AugList<T>          withoutWhere(Predicate<? super T>)`

## Internal methods

Some methods share large amounts of their logic.
By unifying their code, the Single Source of Truth principle can be followed, and thus debugging time reduced.

- `boolean containsBulk(AugList<T>, boolean)`
- `SimpleEntry<AugList<T>, AugList<Integer>> equaliseAndFilter(Iterable<? super T>, Iterable<Integer>, boolean)`
- `static SimpleEntry<AugList<T>, AugList<U>> equaliseLengths(Iterable<? super T>, Iterable<? super U>)`

## See Also

- `tests/AugListTest.java`
- PLANNED `src/ALFactory.java`
