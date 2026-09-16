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
- `new AugList<T>(Enumeration<T>)`
- `new AugList<T>(Iterator<T>)`
- `new AugList<T>(Iterable<T>)`
- `new AugList<T>(ListIterator<T>)`
- `new AugList<T>(AugList<T>, AugList<Integer>)`
- `new AugList<T>(Spliterator<T>)`
- `new AugList<T>(Stream<T>)`
- `add(T)`
- `addAll(AugList<T>)`
- `addAll(T...)`
- `addFirst(T)`
- `allIndicesOf(T)`
- `allSatisfy(Predicate<? super T>)`
- `anySatisfy(Predicate<? super T>)`
- `applyAll(Function<? super T, T>)`
- `chunk(int)`
- `clear()`
- `clone()`
- `contains(T)`
- `containsAll(AugList<T>)`
- `containsAll(T...)`
- `containsAny(AugList<T>)`
- `containsAny(T...)`
- `countsOfElements()`
- `distinctCopy()`
- `distinctSelf()`
- `equals(Object)`
- `filterCopy(Predicate<? super T>)`
- `filterSelf(Predicate<? super T>)`
- `forEach(Consumer<? super T>)`
- `fragment(int)`
- `get(int)`
- `getClass()`
- `getLast()`
- `getRandom()`
- `hashCode()`
- `indexOf(Object)`
- `insert(int, T)`
- `insertAll(int, AugList<T>)`
- `insertAll(int, T...)`
- `insertAllAtRandom(AugList<T>)`
- `insertAllAtRandom(T...)`
- `insertAtRandom(T)`
- `isEmpty()`
- `isEquivalent()`
- `isRearrangement()`
- PLANNED `isSet()`
- `iterator()`
- `lastIndexOf(Object)`
- `listDifference(AugList<T>)`
- `listIntersection(AugList<T>)`
- `listIterator()`
- `listIterator(int)`
- `listUnion(AugList<T>)`
- PLANNED `massOverwriteRandom(AugList<T>)`
- `oneToOneMap(Function<? super T, U>)`
- `notify()`
- `notifyAll()`
- PLANNED `overwriteRandom(T)`
- `pairUp(AugList<U>)`
- `parallelStream()`
- `parameterizedTypeDesc()`
- `remove(Object)`
- `removeAll(AugList<T>)`
- `removeAll(T...)`
- `removeAt(int)`
- `removeIf(Predicate<? super T>)`
- `removeLast()`
- `removeRandom()`
- `retainAll(java.util.Collection<? super T>)`
- `reversed()`
- `sample(int, boolean)`
- `set(int, T)`,
- `setDifference(AugList<T>)`
- `setIntersection(AugList<T>)`
- PLANNED `setMany(AugList<Integer> indices, AugList<T> values)`
- PLANNED `setRange(int, AugList<T>)`
- `setUnion(AugList<T>)`
- `shuffleCopy()`
- `shuffleSelf()`
- `skipWhile(Predicate<? super T>)`
- `size()`
- `sort(Comparator<? super T>)`
- PLANNED `split3(int)`
- PLANNED `splitDelim(T)`
- `spliterator()`
- `stream()`
- `subList(int, int)`
- `swap(int, int)`
- `swapRandom(int)`
- `swapRandom()`
- `takeWhile(Predicate<? super T>)`
- `toArray(T[])`
- `toCollection()`
- `toEnumeration()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`
- `without(T)`
- `withoutAll(AugList<T>)`
- `withoutAll(T...)`
- `withoutIndex(int)`
- `withoutLast()`
- `withoutRandom()`
- `withoutWhere(Predicate<? super T>)`

## See Also

- `tests/AugListTest.java`
- PLANNED `src/ALFactory.java`
