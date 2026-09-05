# Roadmap

## TODO

- Ensure (List/Set)(Diff/Inter/Union) don't alter parameter state.
- Look into (List/Set)(Diff/Inter/Union) tests and ensure all cases properly tested
- Test pulling from dev to a d- branch

## Possible features

- `AugList` implements methods from `Collection<T>` (In progress on d-classAndInterface)
- `AugList` casts to `Collection<T>` (In progress on d-classAndInterface)
- Introduce Array Constructor on d-instantiation?
- Introduce ListIterator Constructor on d-instantiation?
- Introduce `overwriteRandom(T)`, `massOverwriteRandom(AugList<T>)` on d-random?
- Make `containsAll(AugList<T>)` into `containsAll(Collection<T>)` on d-classAndInterface
- `AugList` casts to `Enumeration<T>` on d-classAndInterface
- AugList implements `Serializable` (If exists) on d-classAndInterface
- AugList implements `Cloneable` (If exists) on d-classAndInterface
- AugList implements remaining methods from List on d-classAndInterface

- Parameters that take `AugList<T>` changed to take any `Iterable<T>`
- Introduce `setRange(int, AugList<T>)`, a bulk mutator that sets a range of values to the provided values, starting from the provided index. Should lengthen the AugList if necessary.
- Statistics Library with features from R and SAS
- `interface UI` with methods like `getBool()`, `getInt()`, `getDouble()`, `getStr()`, `writeStr()`
- `CLI implements UI`
- `GUI implements UI`
- `HTMLUI implements UI`
- `AugList<T>.isEquals()` attempts to match any object that could be constructed into `AugList<T>`
- `setMany(AugList<Integer> indices, AugList<T> values)`
- AugList combinatorics
- `interface Tile<T>`
- `BlankTile<T>`, `ValueTile<T>`, `DiagDivisorTile<T>`, `MultiTile<T>` implements `Tile`
- `interface HexTile`
- `FileHandler` helper class that provides easy Read/Write functions
- Simple example programs that use `BigMLib` ex Sudoku, Crossword, Kakuro, Minesweeper, Chess
- Push AugList V2 to main without terminating dev

## Completed

- Mass changing `@code` to `@link` where possible (In progress on d-documentation)
- Parameter name unification (In progress on d-documentation)
