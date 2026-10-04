# AugList V2

## Copyright and Licensing notice

This file is supplied under a modified GNU LGPL V2.1 License.
Please see the provided LICENSE file for more information.

## Major changes

Due to all the changes between versions (below), code written with V1 will be largely incompatible with V2.
It seems unlikely this will be a problem, as the first version of AugList released to the public is V2, hence the only code written to be compatible with V1 will be a few personal projects (as of the V2 release date, anyway.)

- `equals(Object)` and `hashCode()` reverted to `Object.equals(Object)` / `Object.hashCode()` behaviour. For an enhanced version of V1 `equals(Object)`'s behaviour, use the newly added `isEquivalent(Object)` instead.
- For the above reasons, Deprecated `AugList.equals(Object)`.
- New Constructor from `Iterable<T>`, replaces V1 Constructors from `ArrayList<T>` and `List<T>`
- New Constructors from `Spliterator<T>`, `Stream<T>` and value-count pairs [`new AugList<T>(Iterable<T>, Iterable<Integer>)`].
- `pairUp(AugList)` return type and method signature changed (to `AugList<SimpleEntry<T,U>> pairUp(Iterable<? super T>`)
- New Random family of methods: `getRandom()`, `removeRandom()`, `insertAtRandom(T)`, `insertAllAtRandom(AugList)`, `insertAllAtRandom(T...)`, `overwriteRandom(T)`, `massOverwriteRandom(Iterable<T>)`, `massOverwriteRandom(T...)`
- New Random-adjacent methods: `fragment(int)`, `sample(int, boolean)`, `shuffleCopy()`
- New method `parameterizedTypeDesc()` describes the parameterized type of this AugList.
- New method `countOf(T)` as an individual alternative to `countsOfElements()` that also supports `null`s.
- New methods `setMany(Iterable<Integer>, Iterable<? super T>)`, `setFromCallable(int, int, Callable<Boolean>)` for bulk setting
- New property querying methods `isPalindrome()`, `isSet()`, `isSorted(Comparator<? super T>)`
- New splitting methods `split3(int)`, `splitDelim(Function<? super T, Boolean>)`
- New set theory method `crossProduct(Iterable<? super T>)`, takes the cross product with the given elements.
- New bulk swapping method `swapRanges(int, int, int, int)`
- Methods that used to take `AugList<T>` parameters now take `Iterable<? super T>` instead, increasing flexibility.
- `AugList<T> implements Cloneable`
- Complimentary classes added alongside V2:
  - `ALFactory` V1
  - `AlFactoryTest` V1
  - `Ticker` V1
  - `TickerTest` V1

## Behavioural changes

- `toString()` now represents `null` as `"*null*"`.
- V1 `equals()` behaviour reverted to `Object.equals()`. Use `isEquivalent()` for enhanced V1 behaviour.
- V1 `applyAll(Function<? super T, T>)` changed to V2 `applyAll(UnaryOperator<T>)`
- V1 `Hashtable<T, U> pairUp(AugList<T>)` changed to V2 `AugList<SimpleEntry<T, U>> pairUp(Iterable<? super T>)`
- `forEach(null)` now does nothing (as opposed to throwing `NullPointerException`)

## Naming changes

Parameters have (except where additional context is helpful) been renamed to follow the convention below:

- `AugList<T>` parameters: "augListB" or "elements", based on context
- varargs parameters (`T...` or `Object...`): "elements"
- `T` parameters: "element"
- `Predicate<? super T>` parameters: "condition" or "filter", based on context
- `Function<? super T, T>` parameters: "func"
- `Consumer<? super T>` parameters: "action"
- `Object` parameters: "o"
- `Comparator<? super T>` parameters: "comparator"

## Bugfixes

- `clone()`'s, `filterCopy()`, `filterSelf()`, `listIntersection()`, `setIntersection()`, `setUnion()`, `skipWhile()` and `takeWhile()`'s Class now matches this Class i.e. `this.getClass() == this.clone().getClass()`
- `isEquivalent()` now evaluates i.e. `"[1.0]" == "[1.0]"` and `"[Hello, World]" == "[Hello, World]"` to `true` (as opposed to V1 `equals()` behaviour evaluating the above to `false`)
- Passing `null` into `addAll(AugList<T>)` / `addAll(T...)` returns this (as opposed to throwing `NullPointerException`)
- All of the following now work as expected when handling `null`s (as opposed to throwing `NullPointerException`):
  - Constructors,
  - Bulk Satisfaction family of methods,
  - Bulk Containment family of methods,
  - Filter family of methods,
  - Bulk Insertion family of methods,
  - "List Theory" family of methods,
  - Bulk Removal family of methods,
  - Set Theory family of methods,
  - Do While family of methods,
  - `allIndicesOf(T)`,
  - `countsOfElements()`,
  - `forEach(Consumer<? super T>)`,
  - `isEquivalent()`,
  - `removeIf(Predicate<? super T>)`,
  - `toString()`,
  - `sort(Comparator<? super T>)`.
- `shuffleCopy()` Now properly shuffles the elements.

## Testing changes

- Tests for ALL the new features.
- Tests now check for return types (except for when the return type is primitive.)
- Expanded `toString()` tests
- V1 `testEquals()` enhanced and moved to `testIsEquivalent()`
- `swap()` gets the tests it was missing in V1.

## Documentation changes

- Methods are categorised by their `@tags`:
  - `Constructor` (Constructs the object.)
  - `Mutator` (Mutates the object. Previously dubbed "Stream-oriented approach", enables code to be written in the functional-programming paradigm. If no other tags are present, also returns the mutated object.)
  - `Creator` (Creates a new AugList. Does not alter state of this AugList or any of its parameters.)
  - `Converter` (Creates a non-AugList with _useful_ methods.)
  - `Terminator` (Returns a non-AugList **without** _useful_ methods, or returns either `T` or `void`.)
- References to other Java methods and classes generally are now annotated `@link` (as opposed to `@code`)
- `@apiNote` and `@implNote` annotations replaced with `@note`, except where those annotations were used correctly.
- `@overloads` section links to all overloads of a method
- `@see` section links to related methods and tests
- `@since` section differentiates between V1 & V2 methods. Also mentions version of deprecation, if relevant.
- Methods marked with javadoc `@deprecated` also marked with `@Deprecated(since, forRemoval)`
- Minor documentation errors fixed (e.g:

  - `containsAny(T...)` contained typo "id contained",
  - `hashCode()`'s `@apiNote` having `getLast()`'s description,
  - `insertAll(int, AugList<T>)` having `addAll(AugList<T>)`'s parameter description,
  - `set(int, T)` was reported to throw `OutOfRangeException` when it actually throws `IndexOutOfBoundsException`,
  - Many of the `without` family of functions talking about encapsulating methods they did not actually encapsulate)

- Documentation wording has been standardised across methods

## Known problems

None

## Potential issues

- As some areas of code perform unchecked casts, it is possible (though unlikely) that some exceptions could be thrown.
- Behaviour of certain methods may be unpredictable when 2 or more threads attempt to modify AugList at once.
  That being said, as most of AugList is built off the concurrent modification protection that ArrayList provides, this may not be a particularly large problem.
