# Roadmap

## TODO

- Ensure (List/Set)(Diff/Inter/Union) don't alter parameter state.
- Look into (List/Set)(Diff/Inter/Union) tests and ensure all cases properly tested
- Change testAdd family of tests to check the returned AugList
- Test pulling from dev to a d- branch
- Demonstrate the unfixable problems with `equals()` in tests

## Possible features

### AugList V2 Planned

- `AugList` implements methods from `Collection<T>` (In progress on d-classAndInterface)
- `AugList` casts to `Collection<T>` (In progress on d-classAndInterface)
- `AugList` casts to `Enumeration<T>` on d-classAndInterface
- MAYBE AugList implements `Serializable` on d-classAndInterface
- AugList implements `Cloneable` on d-classAndInterface
- AugList implements remaining methods from `List<T>` on d-classAndInterface
- AugList implements all `java.lang` Interfaces that can be reasonably implemented on d-classAndInterface
- Introduce Array Constructor on d-instantiation?
- Introduce ListIterator Constructor on d-instantiation?
- Introduce `overwriteRandom(T)`, `massOverwriteRandom(AugList<T>)` on d-random?
- `AugList<T>.isEquals()` attempts to match any object that could be constructed into `AugList<T>`
- `setMany(AugList<Integer> indices, AugList<T> values)`
- `countCombinations()` (combinatorics)
- Parameters that take `AugList<T>` changed to take any `Iterable<T>`
- Introduce `setRange(int, AugList<T>)`, a bulk mutator that sets a range of values to the provided values, starting from the provided index. Should lengthen the AugList if necessary.
- `split3(int)` returns `AugList<AugList<T>>>` with `[0]` equal to left, `[1]` equal to val at Index and `[2]` equal to right.
- `isSet()` sees if all elements unique (I.e. `this == this.distinctCopy()`)
- `applyAll(Function)` changed to `applyAll(UnaryOperation)`
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

- `BoolAlg` class provides Boolean Algebra functions NOT, AND, OR, XOR, Implies, LSL, LSR, ADD, Compliment, SUB, toLong, fromLong
- `ALHamcrestCompat` class provides casts from `Hamcrest-core` classes to `AugList`
- Statistics Library with features from R and SAS
- `interface UI` with methods like `getBool()`, `getInt()`, `getDouble()`, `getStr()`, `writeStr()`
- `CLI implements UI`
- `GUI implements UI`
- `HTMLUI implements UI`
- `class Transition`
- `class State`
- `class FSM` composes `Transition`, `State` and provides methods to traverse FSM
- `interface SqTile<T>`
- `BlankTile<T>`, `ValueTile<T>`, `DiagDivisorTile<T>`, `MultiTile<T>` implements `SqTile`
- `interface HexTile`
- Hex grid handler
- `FileHandler` helper class that provides easy Read/Write functions
- Simple example programs that are built on `BigMLib` features ex Sudoku, Crossword, Kakuro, Minesweeper, Chess
- `class FibonacciHeap`
- `interface SortingAlgorithm`
- `BubbleSort`, `SelectionSort`, `MergeSort`, `BinInsertionSort`, `HeapSort`, `QuickSort` implements `SortingAlgorithm`

## Completed

- Mass changing `@code` to `@link` where possible
- Parameter name unification
