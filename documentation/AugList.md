# AugList V2

A class providing enhanced List functionality (compared to the offerings of 1st party Java classes)

## Copyright and Licensing notice

This file is supplied under a modified GNU LGPL V2.1 License.
Please see the provided LICENSE file for more information.

## Preface

Java needs a better way of handling lists.
Lots of features in newer languages - C# for instance - simply do not have an analogue in Java.
AugList Version 1, started 2026-03-06 and finished 2026-05-22, was built as an answer to this problem.
Further revisions were made with the aim to expand on the initial vision - such as the latest version, V2.

## Dependencies

- Java JVM
- `java.lang.*`
- `java.util.*`

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

|Return Type                          |Method name                                      |
|-------------------------------------|-------------------------------------------------|
|`new AugList<T>`                     |`AugList()`                                      |
|`new AugList<T>`                     |`AugList(T...)`                                  |
|`new AugList<T>`                     |`AugList(Enumeration<? super T>)`                |
|`new AugList<T>`                     |`AugList(Iterator<? super T>)`                   |
|`new AugList<T>`                     |`AugList(Iterable<? super T>)`                   |
|`new AugList<T>`                     |`AugList(ListIterator<? super T>)`               |
|`new AugList<T>`                     |`AugList(Iterable<? super T>, Iterable<Integer>)`|
|`new AugList<T>`                     |`AugList(Spliterator<? super T>)`                |
|`new AugList<T>`                     |`AugList(Stream<? super T>)`                     |
|`AugList<T>`                         |`add(T)`                                         |
|`AugList<T>`                         |`addAll(AugList<? super T>)`                     |
|`AugList<T>`                         |`addAll(T...)`                                   |
|`AugList<T>`                         |`addFirst(T)`                                    |
|`AugList<T>`                         |`allIndicesOf(T)`                                |
|`boolean`                            |`allSatisfy(Predicate<? super T>)`               |
|`boolean`                            |`anySatisfy(Predicate<? super T>)`               |
|`AugList<T>`                         |`applyAll(UnaryOperator<? super T>)`             |
|`AugList<AugList<T>>`                |`chunk(int)`                                     |
|`AugList<T>`                         |`clear()`                                        |
|`AugList<T>`                         |`clone()` (Overrides `Object.clone()`)           |
|`boolean`                            |`contains(T)`                                    |
|`boolean`                            |`containsAll(Iterable<? super T>)`               |
|`boolean`                            |`containsAll(T...)`                              |
|`boolean`                            |`containsAny(Iterable<? super T>)`               |
|`boolean`                            |`containsAny(T...)`                              |
|`int`                                |`countOf()`                                      |
|`AugList<Integer>`                   |`countsOfElements()`                             |
|`AugList<AugList<SimpleEntry<T, U>>>`|`crossProduct(Iterable<? super U>)`              |
|`AugList<T>`                         |`distinctCopy()`                                 |
|`AugList<T>`                         |`distinctSelf()`                                 |
|`AugList<T>`                         |`filterCopy(Predicate<? super T>)`               |
|`AugList<T>`                         |`filterSelf(Predicate<? super T>)`               |
|`void`                               |`forEach(Consumer<? super T>)`                   |
|`AugList<AugList<T>>`                |`fragment(int)`                                  |
|`T`                                  |`get(int)`                                       |
|`T`                                  |`getLast()`                                      |
|`T`                                  |`getRandom()`                                    |
|`int`                                |`indexOf(Object)`                                |
|`AugList<T>`                         |`insert(int, T)`                                 |
|`AugList<T>`                         |`insertAll(int, Iterable<? super T>)`            |
|`AugList<T>`                         |`insertAll(int, T...)`                           |
|`AugList<T>`                         |`insertAllAtRandom(Iterable<? super T>)`         |
|`AugList<T>`                         |`insertAllAtRandom(T...)`                        |
|`AugList<T>`                         |`insertAtRandom(T)`                              |
|`boolean`                            |`isEmpty()`                                      |
|`boolean`                            |`isEquivalent()`                                 |
|`boolean`                            |`isPalindrome()`                                 |
|`boolean`                            |`isRearrangement(Iterable<? super T>)`           |
|`boolean`                            |`isSet()`                                        |
|`boolean`                            |`isSorted(Comparator<? super T>)`                |
|`Iterator<T>`                        |`iterator()`                                     |
|`boolean`                            |`lastIndexOf(Object)`                            |
|`AugList<T>`                         |`listDifference(Iterable<? super T>)`            |
|`AugList<T>`                         |`listIntersection(Iterable<? super T>)`          |
|`ListIterator<T>`                    |`listIterator()`                                 |
|`ListIterator<T>`                    |`listIterator(int)`                              |
|`AugList<T>`                         |`listUnion(Iterable<? super T>)`                 |
|`AugList<T>`                         |`massOverwriteRandom(Iterable<? super T>)`       |
|`AugList<T>`                         |`massOverwriteRandom(T...)`                      |
|`AugList<U>`                         |`oneToOneMapCopy(Function<? super T, U>)`        |
|`AugList<T>`                         |`overwriteRandom(T)`                             |
|`Hashtable<T, U>`                    |`pairUp(Iterable<U>)`                            |
|`Stream<T>`                          |`parallelStream()`                               |
|`String`                             |`parameterizedTypeDesc()`                        |
|`boolean`                            |`remove(Object)`                                 |
|`boolean`                            |`removeAll(Iterable<? super T>)`                 |
|`boolean`                            |`removeAll(T...)`                                |
|`boolean`                            |`removeAt(int)`                                  |
|`boolean`                            |`removeIf(Predicate<? super T>)`                 |
|`boolean`                            |`removeLast()`                                   |
|`boolean`                            |`removeRandom()`                                 |
|`AugList<T>`                         |`retainAll(Collection<? super T>)`               |
|`AugList<T>`                         |`reversed()`                                     |
|`AugList<T>`                         |`sample(int, boolean)`                           |
|`T`                                  |`set(int, T)`                                    |
|`AugList<T>`                         |`setDifference(Iterable<? super T>)`             |
|`AugList<T>`                         |`setFromCallable(int, int, Callable<Boolean>)`   |
|`AugList<T>`                         |`setIntersection(Iterable<? super T>)`           |
|`AugList<T>`                         |`setMany(Iterable<Integer>, Iterable<? super T>)`|
|`AugList<T>`                         |`setUnion(Iterable<? super T>)`                  |
|`AugList<T>`                         |`shuffleCopy()`                                  |
|`AugList<T>`                         |`shuffleSelf()`                                  |
|`int`                                |`size()`                                         |
|`AugList<T>`                         |`skipWhile(Predicate<? super T>)`                |
|`AugList<T>`                         |`sort(Comparator<? super T>)`                    |
|`AugList<AugList<T>>`                |`split3(int)`                                    |
|`AugList<AugList<T>>`                |`splitDelim(Function<? super T, Boolean>)`       |
|`Spliterator<T>`                     |`spliterator()`                                  |
|`Stream<T>`                          |`stream()`                                       |
|`AugList<T>`                         |`subList(int, int)`                              |
|`AugList<T>`                         |`swap(int, int)`                                 |
|`AugList<T>`                         |`swapRandom(int)`                                |
|`AugList<T>`                         |`swapRandom()`                                   |
|`AugList<T>`                         |`swapRanges(int, int, int, int)`                 |
|`AugList<T>`                         |`takeWhile(Predicate<? super T>)`                |
|`T[]`                                |`toArray(T[])`                                   |
|`Collection<T>`                      |`toCollection()`                                 |
|`Enumeration<T>`                     |`toEnumeration()`                                |
|`String`                             |`toString()` (Overrides `Object.toString()`)     |
|`AugList<T>`                         |`without(T)`                                     |
|`AugList<T>`                         |`withoutAll(Iterable<? super T>)`                |
|`AugList<T>`                         |`withoutAll(T...)`                               |
|`AugList<T>`                         |`withoutIndex(int)`                              |
|`AugList<T>`                         |`withoutLast()`                                  |
|`AugList<T>`                         |`withoutRandom()`                                |
|`AugList<T>`                         |`withoutWhere(Predicate<? super T>)`             |

### Object inherited methods

These methods have not been overridden in a way that alters base functionality.
Apart from `equals(Object)` and `hashCode()`, are untested.

|Return Type|Method Name      |
|-----------|-----------------|
|`boolean`  |`equals(Object)` |
|`void`     |`finalize()`     |
|`Class<?>` |`getClass()`     |
|`int`      |`hashCode()`     |
|`Class<?>` |`getClass()`     |
|`void`     |`notify()`       |
|`void`     |`notifyAll()`    |
|`void`     |`wait()`         |
|`void`     |`wait(long)`     |
|`void`     |`wait(long, int)`|

## Internal methods

Some methods share large amounts of their logic.
These methods serve to unify common code between them, which then reduces on the amount of duplicate code (thus removing some debugging headaches and satisfying the single source of truth principle.)

|Return Type                                |Method Name                                                         |
|-------------------------------------------|--------------------------------------------------------------------|
|`boolean`                                  |`containsBulk(AugList<T>, boolean)`                                 |
|`SimpleEntry<AugList<T>, AugList<Integer>>`|`equaliseAndFilter(Iterable<? super T>, Iterable<Integer>, boolean)`|
|`SimpleEntry<AugList<T>, AugList<U>>`      |`equaliseLengths(Iterable<? super T>, Iterable<? super U>)`         |

## Interfaces Implemented

- `Cloneable`
- `Iterable<T>`

## See Also

- `tests/AugListTest.java`
- `src/ALFactory.java`
