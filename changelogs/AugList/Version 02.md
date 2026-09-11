# AugList V2

## Additions

- New Constructor from `? implements Iterable<T>`, replaces Constructors from `ArrayList<T>` and `List<T>`
- New Constructor from `Spliterator<T>`
- New Constructor from `Stream<T>`
- New Constructor from a list of values and a list of counts (value-count pairs), [`public AugList(AugList<T>, AugList<Integer>)`]
- New Random "family" of methods: `getRandom()`, `removeRandom()`, `insertAtRandom(T)`, `insertAllAtRandom(AugList)`, `insertAllAtRandom(T...)`
- And new Random-adjacent methods too: `fragment(int)`, `sample(int, boolean)`, `shuffleCopy()`

## Behavioural changes

- `AugList<T>.toString()` now represents `null` as `"*null*"`.

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

- `toString()` no longer throws `nullPointerException` when attempting to represent `null`
- `isEqual()` now evaluates i.e. `"[1.0]" == "[1.0]"` and `"[Hello, World]" == "[Hello, World]"` to `true` (as opposed to `false`)

## Testing changes

- Tests for ALL the new features
- Expanded `toString()` tests
- `swap()` tests

## Documentation changes

Methods are categorised by their `@tags`:

- `Constructor` (Constructs the object.)
- `Mutator` (Mutates the object. Previously dubbed "Stream-oriented approach", enables code to be written in the functional-programming paradigm. If no other tags are present, also returns the mutated AugList.)
- `Creator` (Creates a new AugList. Does not alter state of this AugList or any of its parameters.)
- `Converter` (Creates a non-AugList with _useful_ methods.)
- `Terminator` (Returns a non-AugList **without** _useful_ methods, or returns either `T` or `void`.)

Separately:

- References to other Java methods and classes generally are now annotated `@link` (as opposed to `@code`)
- `@apiNote` and `@implNote` annotations replaced with custom `@note`
- New section annotated with `@overloads` links to all overloads of a method
- `@see` section links to similar non-overload methods a nd tests
- Minor documentation errors fixed (e.g. `containsAny(T...)` contained typo "id contained", `hashCode()`'s `@apiNote` having `getLast()`'s description, `insertAll(int, AugList<T>)` having `addAll(AugList<T>)`'s parameter description, `set(int, T)` was reported to throw `OutOfRangeException` when it actually throws `IndexOutOfBoundsException`, many of the `without` family of functions talking about encapsulating methods they did not actually encapsulate)
- Documentation wording has been standardised across methods

## Known problems

- (UNFIXABLE, since V1) Equality does not guarantee equal hash codes
- (UNFIXABLE, since V1) Equality is not Symmetric
