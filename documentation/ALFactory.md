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

### Casts to AugList

|Return Type         |Method Name                          |
|--------------------|-------------------------------------|
|`AugList<Byte>`     |`fromByteBuff(ByteBuffer)`           |
|`AugList<Character>`|`fromCharBuff(CharBuffer)`           |
|`AugList<Character>`|`fromCharItr(CharaterIterator)`      |
|`AugList<Character>`|`fromCharSq(CharSequence)`           |
|`AugList<Character>`|`fromStr(String)`                    |
|`AugList<Character>`|`fromStrBuff(StringBuffer)`          |
|`AugList<Double>`   |`fromDblBuff(DoubleBuffer)`          |
|`AugList<Double>`   |`fromDblStream(DoubleStream)`        |
|`AugList<Float>`    |`fromFloatBuff(FloatBuffer)`         |
|`AugList<Integer>`  |`fromDigits(int)`                    |
|`AugList<Integer>`  |`fromIntBuff(IntBuffer)`             |
|`AugList<Integer>`  |`fromIntStream(IntStream)`           |
|`AugList<Integer>`  |`fromUnicode(String)`                |
|`AugList<Integer>`  |`identityAL(int)`                    |
|`AugList<Long>`     |`fromLongBuff(LongBuffer)`           |
|`AugList<Long>`     |`fromLongStream(LongStream)`         |
|`AugList<Short>`    |`fromShortBuff(ShortBuffer)`         |
|`AugList<String>`   |`fromDelimitedString(String, String)`|
|`AugList<String>`   |`fromFile(Path)`                     |
|`AugList<String>`   |`fromFile(String)`                   |
|`AugList<String>`   |`fromNewLinedString(String)`         |

### Casts from AugList

|Return Type        |Method Name                     |
|-------------------|--------------------------------|
|`ByteBuffer`       |`BBfromALB(AugList<Byte>)`      |
|`CharBuffer`       |`CBfromALC(AugList<Character>)` |
|`CharacterIterator`|`CIfromALC(AugList<Character>)` |
|`CharSequence`     |`CSfromALC(AugList<Character>)` |
|`DoubleBuffer`     |`DBfromALD(AugList<Double>)`    |
|`DoubleStream`     |`DSfromALD(AugList<Double>)`    |
|`FloatBuffer`      |`FBfromALF(AugList<Float>)`     |
|`IntBuffer`        |`IBfromALI(AugList<Integer>)`   |
|`IntStream`        |`ISfromALI(AugList<Integer>)`   |
|`LongBuffer`       |`LBfromALL(AugList<Long>)`      |
|`LongStream`       |`LSfromALL(AugList<Long>)`      |
|`ShortBuffer`      |`ShBfromALSh(AugList<Short>)`   |
|`String`           |`StrFromALC(AugList<Character>)`|
|`String`           |`StrFromALSt(AugList<String>)`  |
|`StringBuffer`     |`StBfromALSt(AugList<String>)`  |

### Object inherited methods

These methods are not tested.

|Return Type|Method Name      |
|-----------|-----------------|
|`boolean`  |`equals(Object)` |
|`Class<?>` |`getClass()`     |
|`int`      |`hashCode()`     |
|`void`     |`notify()`       |
|`void`     |`notifyAll()`    |
|`void`     |`wait()`         |
|`void`     |`wait(long)`     |
|`void`     |`wait(long, int)`|
|`String`   |`toString()`     |

## See Also

- `src/AugList.java`
- `tests/ALFactoryTest.java`
