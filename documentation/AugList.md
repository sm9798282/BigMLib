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
- `new AugList<T>(Iterable<T>, Iterable<Integer>)`
- `new AugList<T>(Spliterator<T>)`
- `new AugList<T>(Stream<T>)`
- `AugList<T>___________add(T)`
- `AugList<T>___________addAll(AugList<T>)`
- `AugList<T>___________addAll(T...)`
- `AugList<T>___________addFirst(T)`
- `AugList<T>___________allIndicesOf(T)`
- `boolean______________allSatisfy(Predicate<? super T>)`
- `boolean______________anySatisfy(Predicate<? super T>)`
- `AugList<T>___________applyAll(UnaryOperator<T>)`
- `AugList<AugList<T>>__chunk(int)`
- `AugList<T>___________clear()`
- `AugList<T>___________clone()`
- `boolean______________contains(T)`
- `boolean______________containsAll(AugList<T>)`
- `boolean______________containsAll(T...)`
- `boolean______________containsAny(AugList<T>)`
- `boolean______________containsAny(T...)`
- `int__________________countOf()`
- `AugList<Integer>_____countsOfElements()`
- `AugList<T>___________distinctCopy()`
- `AugList<T>___________distinctSelf()`
- `boolean______________equals(Object)`
- `AugList<T>___________filterCopy(Predicate<? super T>)`
- `AugList<T>___________filterSelf(Predicate<? super T>)`
- `void_________________forEach(Consumer<? super T>)`
- `AugList<AugList<T>>__fragment(int)`
- `T____________________get(int)`
- `Class<?>_____________getClass()`
- `T____________________getLast()`
- `T____________________getRandom()`
- `int__________________hashCode()`
- `int__________________indexOf(Object)`
- `AugList<T>___________insert(int, T)`
- `AugList<T>___________insertAll(int, AugList<T>)`
- `AugList<T>___________insertAll(int, T...)`
- `AugList<T>___________insertAllAtRandom(AugList<T>)`
- `AugList<T>___________insertAllAtRandom(T...)`
- `AugList<T>___________insertAtRandom(T)`
- `boolean______________isEmpty()`
- `boolean______________isEquivalent()`
- `boolean______________isRearrangement()`
- PLANNED `isSet()`
- `Iterator<T>__________iterator()`
- `boolean______________lastIndexOf(Object)`
- `AugList<T>___________listDifference(AugList<T>)`
- `AugList<T>___________listIntersection(AugList<T>)`
- `ListIterator<T>______listIterator()`
- `ListIterator<T>______listIterator(int)`
- `AugList<T>___________listUnion(AugList<T>)`
- PLANNED `massOverwriteRandom(AugList<T>)`
- `AugList<U>___________oneToOneMap(Function<? super T, U>)`
- `void_________________notify()`
- `void_________________notifyAll()`
- PLANNED `overwriteRandom(T)`
- `Hashtable<T,U>_______pairUp(AugList<U>)`
- `Stream<T>____________parallelStream()`
- `String_______________parameterizedTypeDesc()`
- `boolean______________remove(Object)`
- `boolean______________removeAll(AugList<T>)`
- `boolean______________removeAll(T...)`
- `boolean______________removeAt(int)`
- `boolean______________removeIf(Predicate<? super T>)`
- `boolean______________removeLast()`
- `boolean______________removeRandom()`
- `AugList<T>___________retainAll(java.util.Collection<? super T>)`
- `AugList<T>___________reversed()`
- `AugList<T>___________sample(int, boolean)`
- `T____________________set(int, T)`,
- `AugList<T>___________setDifference(AugList<T>)`
- `AugList<T>___________setIntersection(AugList<T>)`
- PLANNED `setMany(AugList<Integer> indices, AugList<T> values)`
- PLANNED `setRange(int, AugList<T>)`
- `AugList<T>___________setUnion(AugList<T>)`
- `AugList<T>___________shuffleCopy()`
- `AugList<T>___________shuffleSelf()`
- `AugList<T>___________skipWhile(Predicate<? super T>)`
- `int__________________size()`
- `AugList<T>___________sort(Comparator<? super T>)`
- PLANNED `split3(int)`
- PLANNED `splitDelim(T)`
- `Spliterator<T>_______spliterator()`
- `Stream<T>____________stream()`
- `AugList<T>___________subList(int, int)`
- `AugList<T>___________swap(int, int)`
- `AugList<T>___________swapRandom(int)`
- `AugList<T>___________swapRandom()`
- `AugList<T>___________takeWhile(Predicate<? super T>)`
- `T[]__________________toArray(T[])`
- `Collection<T>________toCollection()`
- `Enumeration<T>_______toEnumeration()`
- `String_______________toString()`
- `void_________________wait()`
- `void_________________wait(long)`
- `void_________________wait(long, int)`
- `AugList<T>___________without(T)`
- `AugList<T>___________withoutAll(AugList<T>)`
- `AugList<T>___________withoutAll(T...)`
- `AugList<T>___________withoutIndex(int)`
- `AugList<T>___________withoutLast()`
- `AugList<T>___________withoutRandom()`
- `AugList<T>___________withoutWhere(Predicate<? super T>)`

## Internal methods

Some methods share large amounts of their logic.
By unifying their code, the Single Source of Truth principle can be followed, and thus debugging time reduced.

- `boolean containsBulk(AugList<T>, boolean)`

## See Also

- `tests/AugListTest.java`
- PLANNED `src/ALFactory.java`
