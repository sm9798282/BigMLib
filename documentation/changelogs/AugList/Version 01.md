# AugList V1

## Preface

After 2 years of C# coding, I transitioned to Java and was quite surprised with the functionality differences with lists. Plenty of helpful methods - like C#'s `IEnumerable<T>.Distinct()`, `IEnumerable<T>.Where()` and `List<T>.TrueForAll()`, for example - just did not have a Java analogue.

As most projects inevitably will require the bulk processing of lists in some way, this gave me an idea.
Using the Decorator design pattern, I could expand on Java's `ArrayList<T>` to make an Augmented List class - or `AugList<T>`, as I would end up calling it.

`AugList<T>` went through many changes during its inception on a separate project, with the oldest version on this git tree being V1, the subject of this changelog.

## Features at a glance

Features included with AugList V1 include:

- Construct from any List, Vararg list or Enumeration
- Bulk operations
- Set theory operations
- Methods inspired by Functional and Declarative Programming

## Features in depth

`AugList<T>` V1 makes changes to some `ArrayList<T>` methods, namely:

- `.add(T)` has return type `AugList<T>` (as opposed to always returning `true`)
- `.clone()` has return type `AugList<T>` (as opposed to `Object`)
- `.subList(int, int)` has return type `AugList<T>` (as opposed to `List<T>`)
- `isEquals()` overridden to match AugLists, Lists and Strings so that for `AugList<T>` A, `A.clone().equals(A) == true`
- `toString()` overridden to give an actually acceptable representation
- `List.add(int, T)` renamed to `insert(int, T)`
- `List<T>.addAll(int, Collection<?>)` replaced by `insertAll(int, AugList<T>)`

On top of encapsulating most `ArrayList<T>` methods, `AugList<T>` V1 also adds the following methods:

- `allSatisfy(Predicate)`, `anySatisfy(Predicate)`, `containsAny(Predicate)`
- `applyAll(Function)`, `oneToOneMap(Function)`
- `chunk(int)`
- `countsOfElements()`
- `distinctSelf()`, `distinctCopy()`
- `filterSelf(Predicate)`, `filterCopy(Predicate)`
- `forEach()`
- `isRearrangement()`
- `listDifference(AugList)`, `listIntersection(AugList)`, `listUnion(AugList)`
- `pairUp(AugList)`
- `setDifference(AugList)`, `setIntersection(AugList)`, `setUnion(AugList)`
- `skipWhile(Predicate)`, `takeWhile(Predicate)`,
- `swap(int, int)`
- `without(T)`, `withoutAll(AugList)`, `withoutIndex(int)`, `withoutLast()`, `withoutWhere(Predicate)`
- `shuffleCopy()`

These methods have applications in set theory, probability, statistics, functional programming and sql-style queries.

## Known Problems

- Objects considered equal to an AugList will not necessarily have an identical hashcode to AugList's hashcode.
- For AugList X and Object Y, X.equals(Y) does not mean Y.equals(X).
- `toString()` bug where attempting to print a list with nulls throws a `NullPointerException`.
- Constructors bug where supplying null threw an `NullPointerException`.
- `isEqual()` bug where `"[1.0]"` != `"[1.0]"`.
- `addAll(AugList<T>)` / `addAll(T...)` bug where passing a `null` throws `NullPointerException`
- `clone()` bug where `this.getClass() != this.clone().getClass()`
- Insufficient tests in `testToString()` to test for an empty list or a list with nulls
- No `testSwap()`
- Documentation talks about adding the method `getAndAppendIfEmpty()`  method was commented out and never available to use.
- Documentation in `listIntersection()` is incorrectly repeated over `listDifference()`, `listUnion()`, `setDifference()`, `setIntersection()` and `setUnion()`
- Documentation occasionally refers to parameters by the wrong name (i.e. `insertAll(int T...)` refers to its' varargs parameter as "pAugList" when it is actually called "elements".)
- Inconsistent parameter naming scheme
- Overly expressive commentary in certain methods (i.e. `allIndicesOf()`, `equals()`)
- Occasional references to AugList as "Auglist"
- Minor spelling mistakes
