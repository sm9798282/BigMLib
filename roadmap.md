# Roadmap

## TODO

- Ensure (List/Set)(Diff/Inter/Union) don't alter parameter state.
- Look into (List/Set)(Diff/Inter/Union) tests and ensure all cases properly tested
- Test pulling from dev to a d- branch
- Test with AugList<?>

## Possible features

### AugList V2 Planned

- Introduce `overwriteRandom(T)`, `massOverwriteRandom(AugList<T>)` on d-write
- `setMany(AugList<Integer> indices, AugList<T> values)`  on d-write
- Introduce `setRange(int, AugList<T>)`, a bulk mutator that sets a range of values to the provided values, starting from the provided index. Should lengthen the AugList if necessary.  on d-write
- `countCombinations()` (combinatorics) on d-csma
- `isPalindrome()` sees if AugList is palindrome on d-csma
- `isSet()` sees if all elements unique (I.e. `this == this.distinctCopy()`) on d-csma
- `split3(int)` returns `AugList<AugList<T>>>` with `[0]` equal to left, `[1]` equal to val at Index and `[2]` equal to right. on d-csma
- `crossProduct(Iterable<U>)` d-csma (Source from previous projects)
- `crossProduct(Iterable<Iterable<U>>)` d-csma (Source from previous projects)
- AugList Factory class `ALFactory` handles constructors and casts to and from specific AugList Parameterized Types
- PLANNED `AugList<Byte> ALFactory.fromByteBuff(ByteBuffer)`
- PLANNED `AugList<Character> ALFactory.fromCharBuff(CharBuffer)`
- PLANNED `AugList<Character> ALFactory.fromStr(String)`
- PLANNED `AugList<Character> ALFactory.fromCharIter(CharacterIterator)`
- PLANNED `AugList<Double> ALFactory.fromDblBuff(DoubleBuffer)`
- PLANNED `AugList<Double> ALFactory.fromDblStream(DoubleStream)`
- PLANNED `AugList<Float> ALFactory.fromFloatBuff(FloatBuffer)`
- PLANNED `AugList<Integer> ALFactory.fromIntBuff(IntBuffer)`
- PLANNED `AugList<Integer> ALFactory.fromDigits(int)`
- PLANNED `AugList<Integer> ALFactory.fromUnicode(String)`
- PLANNED `AugList<Integer> ALFactory.fromIntStream(IntStream)`
- PLANNED `AugList<Long> ALFactory.fromLongBuff(LongBuffer)`
- PLANNED `AugList<Long> ALFactory.fromLongStream(LongStream)`
- PLANNED `AugList<Short> ALFactory.fromShortBuff(ShortBuffer)`
- PLANNED `AugList<String> ALFactory.fromCharSq(CharSequence)`
- PLANNED `AugList<String> ALFactory.fromFile(Path)`
- PLANNED `AugList<String> ALFactory.fromFile(String)`
- PLANNED `AugList<String> ALFactory.fromRepString(String)`
- PLANNED `AugList<String> ALFactory.fromStrBuff(StringBuffer)`
- PLANNED `ByteBuffer ALFactory.fromALByte(AugList<Byte>)`
- PLANNED `CharBuffer ALFactory.fromALChar(AugList<Character>)`
- PLANNED `CharSequence ALFactory.ALStrToCharSq(AugList<String>)`
- PLANNED `DoubleBuffer ALFactory.DBfromALDbl(AugList<Double>)`
- PLANNED `DoubleStream ALFactory.DSfromALDbl(AugList<Double>)`
- PLANNED `FloatBuffer ALFactory.fromALFloat(AugList<Float>)`
- PLANNED `IntBuffer ALFactory.IBFromALInt(AugList<Integer>)`
- PLANNED `IntStream ALFactory.ISfromALInt(AugList<Integer>)`
- PLANNED `LongBuffer ALFactory.LBfromALLong(AugList<Long>)`
- PLANNED `LongStream ALFactory.LSfromALLong(AugList<Long>)`
- `int ALFactory.quickSelectKth(AugList<Integer>, int k)`
- `double ALFactory.quickSelectKth(AugList<Double>, int k)`
- Push AugList V2 to main without terminating dev

## Other planned

- `BoolAlg` class provides Boolean Algebra functions NOT, AND, OR, XOR, Implies, LSL, LSR, ADD, Compliment, SUB, MUX, toLong, fromLong
- `ALHamcrestCompat` class provides casts from `Hamcrest-core` classes to `AugList`
- `class Statistics` with features from R and SAS (Arithmetic/Geometric/Harmonic Sum, Product, Mean; Median, Mode, Standard Deviation etc) (Source from previous projects)
- `class Mathematics` with features like Lerp, PolarToFromRectangular, DistPointToLine, VectorAngle, (Source from previous projects) QuaternionToFromEuler?
- `class Generics` with an implementation of `parameterizedTypeDesc()`
- `interface UI` with methods like `getBool()`, `getInt()`, `getDouble()`, `getStr()`, `writeStr()`
- `CLI implements UI`
- `GUI implements UI`
- `HTMLUI implements UI`
- `class Transition`
- `class State`
- `class FSM` composes `Transition`, `State` and provides methods to traverse FSM
- `class Graph` with `GetHeadings()` (Maybe source from previous projects)
- `interface Distance`
- `class PythagDist implements Distance`
- `class TaxicabDist implements Distance`
- `class MaxDist implements Distance`
- `interface Interpolate`
- `class Lerp implements Interpolate`
- `class EaseIn implements Interpolate`
- `class EaseOut implements Interpolate`
- `class EaseInOut implements Interpolate`
- `FileHandler` helper class that provides easy Read/Write functions
- `class FibonacciHeap`
- `interface SortingAlgorithm`
- `BubbleSort`, `SelectionSort`, `MergeSort`, `BinInsertionSort`, `HeapSort`, `QuickSort` implements `SortingAlgorithm`
- `interface SqTile<T>`
- `BlankTile<T>`, `ValueTile<T>`, `DiagDivisorTile<T>`, `MultiTile<T>` implements `SqTile`
- `interface HexTile`
- Hex grid handler
- Floyd's Algorithm (Maybe source from previous projects)

- Simple example programs that are built on `BigMLib` features ex Sudoku, Crossword, Kakuro, Minesweeper, Chess

## Completed

- Where possible, parameters that have param type `T` changed to `? super T` on d-param
- Most parameters that take `AugList<T>` changed to take any `Iterable<T>` on d-param
- Test nulls
- Combine Basic Overloads
- Test non-primitive return types
- Test gets from empty lists (and throw `NoSuchElementException` where necessary)
- `applyAll(Function)` changed to `applyAll(UnaryOperation)` on d-param
- Change testAdd family of tests to check the returned AugList
- `AugList<T>.isEquivalent()` attempts to match any object that could be constructed into `AugList<T>`
- Separate out Special Inits by constructor
- FIX `new AugList<T>(AugList<Integer>, AugList<T>)` to not throw if given nulls
- Check other constructors also don't throw when fed nulls
- Introduce Array Constructor on d-instantiation?
- Introduce ListIterator Constructor on d-instantiation?
- AugList implements remaining methods from `List<T>` on d-classAndInterface
- `AugList` casts to `Enumeration<T>` on d-classAndInterface
- AugList implements `Cloneable` on d-classAndInterface
- AugList implements all `java.lang` Interfaces that can be reasonably implemented on d-classAndInterface
- `AugList` implements methods from `Collection<T>` (In progress on d-classAndInterface)
- `AugList` casts to `Collection<T>` (In progress on d-classAndInterface)
- FIX `equals()` "UNFIXABLES" by moving functionality to `isEquivalent()` and returning to default `Object.equals()` behaviour
- Mass changing `@code` to `@link` where possible
- Parameter name unification
