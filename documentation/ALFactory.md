# AugList V2

A class providing static methods to aid in the casting to and from specifically parameterised AugLists.

## Copyright and Licensing notice

This file is supplied under a modified GNU LGPL V2.1 License.
Please see the provided LICENSE file for more information.

## Dependencies

- Java JVM
- `java.lang.*`
- `java.io.*`
- `java.nio.*`
- `java.util.*`
- `src/AugList.java`

## Feature List

Provided with ALFactory V1 are the following:

Casts to AugList

- `AugList<Byte>        fromByteBuff(ByteBuffer)`
- `AugList<Character>   fromCharBuff(CharBuffer)`
- `AugList<Character>   fromCharItr(CharaterIterator)`
- `AugList<Character>   fromCharSq(CharSequence)`
- `AugList<Character>   fromStr(String)`
- `AugList<Character>   fromStrBuff(StringBuffer)`
- `AugList<Double>      fromDblBuff(DoubleBuffer)`
- `AugList<Double>      fromDblStream(DoubleStream)`
- `AugList<Float>       fromFloatBuff(FloatBuffer)`
- `AugList<Integer>     fromIntBuff(IntBuffer)`
- `AugList<Integer>     fromDigits(int)`
- `AugList<Integer>     fromUnicode(String)`
- `AugList<Integer>     fromIntStream(IntStream)`
- `AugList<Integer>     identityAL(int)`
- `AugList<Long>        fromLongBuff(LongBuffer)`
- `AugList<Long>        fromLongStream(LongStream)`
- `AugList<Short>       fromShortBuff(ShortBuffer)`
- `AugList<String>      fromDelimitedString(String, String)`
- `AugList<String>      fromFile(Path)`
- `AugList<String>      fromFile(String)`
- `AugList<String>      fromNewLinedString(String)`

Casts from AugList

- `ByteBuffer       BBFromALB(AugList<Byte>)`
- `CharBuffer       CBFromALC(AugList<Character>)`
- `CharSequence     CSFromALC(AugList<Character>)`
- `DoubleBuffer     DBFromALD(AugList<Double>)`
- `DoubleStream     DSFromALD(AugList<Double>)`
- `FloatBuffer      FBFromALF(AugList<Float>)`
- `IntBuffer        IBFromALI(AugList<Integer>)`
- `IntStream        ISFromALI(AugList<Integer>)`
- `LongBuffer       LBFromALL(AugList<Long>)`
- `LongStream       LSFromALL(AugList<Long>)`
- `String           StrFromALC(AugList<Character>)`
- `String           StrFtomALS(AugList<String>)`

Object inherited methods

- `boolean          equals(Object)`
- `Class<?>         getClass()`
- `int              hashCode()`
- `void             notify()`
- `void             notifyAll()`
- `void             wait()`
- `void             wait(long)`
- `void             wait(long, int)`

## See Also

- `src/AugList.java`
- `tests/ALFactoryTest.java` (eventually)
