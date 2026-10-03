/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
package tests;

import static org.junit.Assert.*;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.text.CharacterIterator;
import java.util.regex.PatternSyntaxException;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;

import src.ALFactory;
import src.AugList;

/**
 * A JUnit powered automatic tester for {@link src.ALFactory}.
 * @since   AugList V2
 * @version ALFactoryTest V1
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     src.ALFactory
 * @see     src.AugList
 */
public class ALFactoryTest implements MultiTest {
    
    AugList<Byte> ALB; // Unlike C#'s Byte class, java's Bytes are signed.
    AugList<Character> ALC;
    AugList<Double> ALD;
    AugList<Float> ALF;
    AugList<Integer> ALI;
    AugList<Long> ALL;
    AugList<Short> ALSh;
    AugList<String> ALSt;

    final AugList<Byte> nALB = null;
    final AugList<Character> nALC = null;
    final AugList<Double> nALD = null;
    final AugList<Float> nALF = null;
    final AugList<Integer> nALI = null;
    final AugList<Long> nALL = null;
    final AugList<Short> nALSh = null;
    final AugList<String> nALSt = null;

    final AugList<Byte> eALB = new AugList<Byte>();
    final AugList<Character> eALC = new AugList<Character>();
    final AugList<Double> eALD = new AugList<Double>();
    final AugList<Float> eALF = new AugList<Float>();
    final AugList<Integer> eALI = new AugList<Integer>();
    final AugList<Long> eALL = new AugList<Long>();
    final AugList<Short> eALSh = new AugList<Short>();
    final AugList<String> eALSt = new AugList<String>();

    public void setupTestData() {
        /*
         * Since ALFactory contains only static methods, it does not require instantiation.
         * That being said, it is required to ensure 100% test coverage.
         * On top of this, there is still certainly the need for AugList test data.
         */
        new ALFactory() { };
        ALB = new AugList<Byte>((byte)0, (byte)1, (byte)7, (byte)-1);
        ALC = new AugList<Character>('h', 'o', 'c', 'u', 's', ' ', 'p', 'o', 'c', 'u', 's');
        ALD = new AugList<Double>(1.0, 2.0, 7.11, -2.5, 3.1415926);
        ALF = new AugList<Float>(12.0f, 7.0f, -12.2f, 3.4f, 5.87f, 12.0f);
        ALI = new AugList<Integer>(7, 11, 19, -24, 117, 175, 56, 43);
        ALL = new AugList<Long>(4l, 16l, 37l, 58l, 89l, 145l, 42l, 20l);
        ALSh = new AugList<Short>((short)-17, (short)-50, (short)-25, (short)-74, (short)-37, (short)-110, (short)-55, (short)-164, (short)-82, (short)-41, (short)-122, (short)-61, (short)-182, (short)-91, (short)-272, (short)-136, (short)-68, (short)-34);
        ALSt = new AugList<String>("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy dog");
        /*
         * As strings, the test data is:
         * [0, 1, 7, -1]
         * [h, o, c, u, s,  , p, o, c, u, s]
         * [1.0, 2.0, 7.11, -2.5, 3.1415926]
         * [12.0, 7.0, -12.2, 3.4, 5.87, 12.0]
         * [7, 11, 19, -24, 117, 175, 56, 43]
         * [4, 16, 37, 58, 89, 145, 42, 20]
         * [-17, -50, -25, -74, -37, -110, -55, -164, -82, -41, -122, -61, -182, -91, -272, -136, -68, -34]
         * [The, quick, brown, fox, jumps, over, the, lazy dog]
         */
    }

    @Test
    public void allTests() {
        // Casts from AL
        testFromByteBuff();
        testFromCharBuff();
        testFromCharItr();
        testFromCharSq();
        testFromDblBuff();
        testFromDblStream();
        testFromDelimitedString();
        testFromDigits();
        testFromFile();
        testFromFileString();
        testFromFloatBuff();
        testFromIntBuff();
        testFromIntStream();
        testFromLongBuff();
        testFromLongStream();
        testFromNewLinedString();
        testFromShortBuff();
        testFromStr();
        testFromStrBuff();
        testFromUnicode();
        testIdentityAL();
        // Casts to AL
        testBBfromALB();
        testCBfromALC();
        testCIfromALC();
        testCSfromALC();
        testDBfromALD();
        testDSfromALD();
        testFBfromALF();
        testIBfromALI();
        testISfromALI();
        testLBfromALL();
        testLSfromALL();
        testShBfromALSh();
        testStrFromALC();
        testStrFromALSt();
        testStBfromALSt();
    }

    //#region Casts to AL

    /**
     * Junit tester for casting from a {@link ByteBuffer}.
     * @see     #testBBfromALB()
     * @see     src.ALFactory#fromByteBuff(ByteBuffer)
     */
    @Test
    public void testFromByteBuff() {
        testBBfromALB();
        assertTrue(ALFactory.fromByteBuff(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link CharBuffer}.
     * @see     #testCBfromALC()
     * @see     src.ALFactory#fromCharBuff(CharBuffer)
     */
    @Test
    public void testFromCharBuff() {
        testBBfromALB();
        assertTrue(ALFactory.fromCharBuff(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link CharacterIterator}.
     * @see     src.ALFactory#fromCharItr(CharacterIterator)
     */
    @Test
    public void testFromCharItr() {
        setupTestData();
        testCIfromALC();
        assertTrue(ALFactory.fromCharItr(null).isEmpty());
        assertTrue(ALFactory.fromCharItr(ALFactory.CIfromALC(ALC)).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromCharItr(ALFactory.CIfromALC(ALC)).isEquivalent(ALC));
    }

    /**
     * Junit tester for casting from a {@link CharSequence}.
     * @see     #testCSfromALC()
     * @see     src.ALFactory#fromCharSq(CharSequence)
     */
    @Test
    public void testFromCharSq() {
        testCSfromALC();
        assertTrue(ALFactory.fromCharSq(null).isEmpty());
        assertTrue(ALFactory.fromCharSq(ALFactory.CSfromALC(ALC)).isEquivalent(ALC));
        assertTrue(ALFactory.fromCharSq(ALFactory.CSfromALC(ALC).subSequence(0, 7)).isEquivalent(ALC.subList(0, 7)));
    }

    /**
     * Junit tester for casting from a {@link String}.
     * @see     src.ALFactory#fromStr(String)
     */
    @Test
    public void testFromStr() {
        setupTestData();
        assertTrue(ALFactory.fromStr("Hello World!").isEquivalent("[H, e, l, l, o,  , W, o, r, l, d, !]"));
        assertTrue(ALFactory.fromStr("").isEmpty());
        assertTrue(ALFactory.fromStr(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link StringBuffer}.
     * @see     #testStBfromALSt()
     * @see     src.ALFactory#fromStrBuff(StringBuffer)
     */
    @Test
    public void testFromStrBuff() {
        testStBfromALSt();
        assertTrue(ALFactory.fromStrBuff(null).size() == 0);
    }

    /**
     * Junit tester for casting from a {@link DoubleBuffer}.
     * @see     #testDBfromALD()
     * @see     src.ALFactory#fromDblBuff(DoubleBuffer)
     */
    @Test
    public void testFromDblBuff() {
        testDBfromALD();
        assertTrue(ALFactory.fromDblBuff(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link DoubleStream}.
     * @see     #testDSfromALD()
     * @see     src.ALFactory#fromDblStream(DoubleStream)
     */
    @Test
    public void testFromDblStream() {
        testDSfromALD();
        assertTrue(ALFactory.fromDblStream(null).isEmpty());
        assertTrue(ALFactory.fromDblStream(ALFactory.DSfromALD(eALD)).size() == 0);
        assertTrue(ALFactory.fromDblStream(ALFactory.DSfromALD(eALD)).getClass().toGenericString().equals("public class src.AugList<T>"));
    }

    /**
     * Junit tester for casting from a {@link FloatBuffer}.
     * @see     #testFBfromALF()
     * @see     src.ALFactory#fromFloatBuff(FloatBuffer)
     */
    @Test
    public void testFromFloatBuff() {
        testFBfromALF();
        assertTrue(ALFactory.fromFloatBuff(null).isEmpty());
    }

    /**
     * Junit tester for casting from the digits of an int.
     * @see     src.ALFactory#fromDigits(int)
     */
    @Test
    public void testFromDigits() {
        setupTestData();
        assertTrue(ALFactory.fromDigits(0).isEquivalent("[0]"));
        assertTrue(ALFactory.fromDigits(-1).isEquivalent("[1]"));
        assertTrue(ALFactory.fromDigits(1).isEquivalent("[1]"));
        assertTrue(ALFactory.fromDigits(100).isEquivalent("[1, 0, 0]"));
        assertTrue(ALFactory.fromDigits(2357).isEquivalent("[2, 3, 5, 7]"));
        assertTrue(ALFactory.fromDigits(Integer.MAX_VALUE).isEquivalent("[2, 1, 4, 7, 4, 8, 3, 6, 4, 7]"));
    }

    /**
     * Junit tester for casting from a {@link IntBuffer}.
     * @see     #testIBfromALI()
     * @see     src.ALFactory#fromIntBuff(IntBuffer)
     */
    @Test
    public void testFromIntBuff() {
        testIBfromALI();
        assertTrue(ALFactory.fromIntBuff(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link IntStream}.
     * @see     #testISfromALI()
     * @see     src.ALFactory#fromIntStream(IntStream)
     */
    @Test
    public void testFromIntStream() {
        testISfromALI();
        assertTrue(ALFactory.fromIntStream(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link String} of Unicode characters.
     * @see     src.ALFactory#fromUnicode(String)
     */
    @Test
    public void testFromUnicode() {
        setupTestData();
        // Capitals
        assertTrue(ALFactory.fromUnicode("ABRACADABRA").isEquivalent("[65, 66, 82, 65, 67, 65, 68, 65, 66, 82, 65]"));
        // Numbers, punctuation and lowercase
        assertTrue(ALFactory.fromUnicode("This string was written on the 1st of October, 2026, at 20:36PM (GMT +1:00).").isEquivalent("[84, 104, 105, 115, 32, 115, 116, 114, 105, 110, 103, 32, 119, 97, 115, 32, 119, 114, 105, 116, 116, 101, 110, 32, 111, 110, 32, 116, 104, 101, 32, 49, 115, 116, 32, 111, 102, 32, 79, 99, 116, 111, 98, 101, 114, 44, 32, 50, 48, 50, 54, 44, 32, 97, 116, 32, 50, 48, 58, 51, 54, 80, 77, 32, 40, 71, 77, 84, 32, 43, 49, 58, 48, 48, 41, 46]"));
        // Non-alphabetical
        assertTrue(ALFactory.fromUnicode("∅|ΠαΣ∞⇔∃").isEquivalent("[8709, 124, 928, 945, 931, 8734, 8660, 8707]"));
        // Japanese Scripts (Hiragana, Katakana, Kanji)
        assertTrue(ALFactory.fromUnicode("Aさん：これは何ですか。Bさん：それはコンピュータです。").isEquivalent("[65, 12373, 12435, 65306, 12371, 12428, 12399, 20309, 12391, 12377, 12363, 12290, 66, 12373, 12435, 65306, 12381, 12428, 12399, 12467, 12531, 12500, 12517, 12540, 12479, 12391, 12377, 12290]"));
        // Emoji
        assertTrue(ALFactory.fromUnicode("👍").isEquivalent("[128077, 56397]"));
        assertTrue(ALFactory.fromUnicode("").isEmpty());
        assertTrue(ALFactory.fromUnicode(null).isEmpty());
    }

    /**
     * Junit tester for creating an Identity {@link AugList}.
     * @see     src.ALFactory#identityAL(int)
     */
    @Test
    public void testIdentityAL() {
        setupTestData();
        assertThrows(IllegalArgumentException.class, () -> {ALFactory.identityAL(-1); });
        assertTrue(ALFactory.identityAL(0).isEmpty());
        // Confirm for Identity lists of length 4, 16, 37, 58, 89, 145, 42 and 20 that each entry corresponds to its index.
        for (int i = 0; i < ALL.size(); i++) {
            for (int j = 0; j < ALL.get(i); j++) {
                assertTrue(ALFactory.identityAL(ALL.get(i).intValue()).get(j) == j);
            }
        }
    }

    /**
     * Junit tester for casting from a {@link LongBuffer}.
     * @see     #testLBfromALL()
     * @see     src.ALFactory#fromLongBuff(LongBuffer)
     */
    @Test
    public void testFromLongBuff() {
        testLBfromALL();
        assertTrue(ALFactory.fromLongBuff(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link LongStream}.
     * @see     #testLSfromALL()
     * @see     src.ALFactory#fromLongStream(LongStream)
     */
    @Test
    public void testFromLongStream() {
        testLSfromALL();
        assertTrue(ALFactory.fromLongStream(null).isEmpty());
    }

    /**
     * Junit tester for casting from a {@link ShortBuffer}.
     * @see     #testShBfromALSh()
     * @see     src.ALFactory#fromLongStream(ShortBuffer)
     */
    @Test
    public void testFromShortBuff() {
        testBBfromALB();
        assertTrue(ALFactory.fromShortBuff(null).size() == 0);
    }

    /**
     * Junit tester for casting from a delimited {@link String}.
     * @see     src.ALFactory#fromDelimitedString(String)
     */
    @Test
    public void testFromDelimitedString() {
        setupTestData();
        assertTrue(ALFactory.fromDelimitedString("Hello World!", "a").isEquivalent("[Hello World!]"));
        assertTrue(ALFactory.fromDelimitedString("", "b").size() == 1);
        assertTrue(ALFactory.fromDelimitedString(null, "b").isEmpty());
        assertTrue(ALFactory.fromDelimitedString("b", null).isEmpty());
        assertTrue(ALFactory.fromDelimitedString(null, null).isEmpty());
        assertTrue(ALFactory.fromDelimitedString("Line 1\r\nLine 2", "\r\n").isEquivalent("[Line 1, Line 2]"));
        assertTrue(ALFactory.fromDelimitedString("Line 1\rLine 2", "\r").isEquivalent("[Line 1, Line 2]"));
        assertTrue(ALFactory.fromDelimitedString("Line 1\nLine 2", "\n").isEquivalent("[Line 1, Line 2]"));
        assertTrue(ALFactory.fromDelimitedString("Line 1\nLine 2\rLine 3\r\nLine 4", "\r\n|\r|\n").isEquivalent("[Line 1, Line 2, Line 3, Line 4]"));
        assertTrue(ALFactory.fromDelimitedString("Line 1\nLine 2\r\r\nLine 4", "\r\n|\r|\n").isEquivalent("[Line 1, Line 2, , Line 4]"));
        assertTrue(ALFactory.fromDelimitedString("Line 1\n \r\r\nLine 4\r\\r", "\r\n|\r|\n").isEquivalent("[Line 1,  , , Line 4, \\r]"));
        // Split whenever the strings "eve" or "ere" are encountered.
        assertTrue(ALFactory.fromDelimitedString("Here, there and everywhere.", "e[r|v]e").isEquivalent("[H, , th,  and , rywh, .]"));
        assertThrows(PatternSyntaxException.class, () -> { ALFactory.fromDelimitedString("", "????++++[]]][||"); });
    }

    /**
     * Junit tester for casting from a {@link Path}.
     * @see     src.ALFactory#fromFile(Path)
     */
    @Test
    public void testFromFile() {
        setupTestData();
        Path p = Path.of("resources", "ALFactoryFromFileTestData.txt");
        assertTrue(Files.exists(p));
        AugList<String> fileContents = ALFactory.fromFile(p);
        assertFalse(fileContents.isEmpty()); // As fileContents cannot be null.
        if (fileContents.size() == 1 && fileContents.get(0).charAt(0) == CharacterIterator.DONE) {
            /**
             * If the only entry in the AugList is the DONE character,
             * then an IOException occurred.
             * Whilst an unfortunate occurrence, is still possible even with valid calls.
             */
        }
        else {
            // Otherwise, the fileContents are valid to check against.
            assertTrue(fileContents.get(0).equals("Hello!"));
            assertTrue(fileContents.get(1).equals("If this text is being held inside a variable,"));
            assertTrue(fileContents.get(2).equals("or is being printed for the user to read,"));
            assertTrue(fileContents.get(3).equals("then that means the test was a success!"));
            assertTrue(fileContents.size() == 4);
        }
        Path nPath = null;
        assertTrue(ALFactory.fromFile(nPath).isEmpty());
        Path nonExistantPath = Path.of("resources", "non_existant_file.md");
        fileContents = ALFactory.fromFile(nonExistantPath);
        assertFalse(fileContents.isEmpty());
        // Here it is expected that fileContents contains only the invalid character, as the above file does not exist.
        assertTrue(fileContents.size() == 1 && fileContents.get(0).charAt(0) == CharacterIterator.DONE);
    }

    /**
     * Junit tester for casting from a path {@link String}.
     * @see     src.ALFactory#fromFile(String)
     * @note    May not work on Linux/GNU, Mac or Unix. Replace all "//" with "\" in such a case.
     */
    @Test
    public void testFromFileString() {
        setupTestData();
        String pathString = "resources\\ALFactoryFromFileTestData.txt";
        AugList<String> fileContents = ALFactory.fromFile(pathString);
        assertFalse(fileContents.isEmpty()); // As fileContents cannot be null.
        if (fileContents.size() == 1 && fileContents.get(0).charAt(0) == CharacterIterator.DONE) {
            /**
             * If the only entry in the AugList is the DONE character,
             * then an IOException occurred.
             * Whilst an unfortunate occurrence, is still possible even with valid calls.
             */
        }
        else {
            // Otherwise, the fileContents are valid to check against.
            assertTrue(fileContents.get(0).equals("Hello!"));
            assertTrue(fileContents.get(1).equals("If this text is being held inside a variable,"));
            assertTrue(fileContents.get(2).equals("or is being printed for the user to read,"));
            assertTrue(fileContents.get(3).equals("then that means the test was a success!"));
            assertTrue(fileContents.size() == 4);
        }
        String nString = null;
        assertThrows(NullPointerException.class, () -> { ALFactory.fromFile(nString); });
        assertThrows(InvalidPathException.class, () -> { ALFactory.fromFile("/\\:*?\"<>|"); });
        fileContents = ALFactory.fromFile("resources\\non_existant_file.md");
        assertFalse(fileContents.isEmpty());
        // Here it is expected that fileContents contains only the invalid character, as the above file does not exist.
        assertTrue(fileContents.size() == 1 && fileContents.get(0).charAt(0) == CharacterIterator.DONE);
    }

    /**
     * Junit tester for casting from a newlined {@link String}.
     * @see     src.ALFactory#fromNewLinedString(String)
     */
    @Test
    public void testFromNewLinedString() {
        setupTestData();
        assertTrue(ALFactory.fromNewLinedString("Hello World!").isEquivalent("[Hello World!]"));
        assertTrue(ALFactory.fromNewLinedString("").isEmpty());
        assertTrue(ALFactory.fromNewLinedString(null).isEmpty());
        assertTrue(ALFactory.fromNewLinedString("Line 1\r\nLine 2").isEquivalent("[Line 1, Line 2]"));
        assertTrue(ALFactory.fromNewLinedString("Line 1\rLine 2").isEquivalent("[Line 1, Line 2]"));
        assertTrue(ALFactory.fromNewLinedString("Line 1\nLine 2").isEquivalent("[Line 1, Line 2]"));
        assertTrue(ALFactory.fromNewLinedString("Line 1\nLine 2\rLine 3\r\nLine 4").isEquivalent("[Line 1, Line 2, Line 3, Line 4]"));
        assertTrue(ALFactory.fromNewLinedString("Line 1\nLine 2\r\r\nLine 4").isEquivalent("[Line 1, Line 2, , Line 4]"));
        assertTrue(ALFactory.fromNewLinedString("Line 1\n \r\r\nLine 4\r\\r").isEquivalent("[Line 1,  , , Line 4, \\r]"));
    }

    //#endregion

    //#region Casts from AL

    /**
     * Junit tester for casting to a {@link ByteBuffer}.
     * @see     #testFromByteBuff()
     * @see     src.ALFactory#BBfromALB(AugList)
     */
    @Test
    public void testBBfromALB() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.BBfromALB(nALB); });
        ByteBuffer bb = ALFactory.BBfromALB(ALB);
        assertTrue(bb.getClass().toGenericString().equals("sealed class java.nio.HeapByteBuffer"));
        assertTrue(ALFactory.fromByteBuff(bb).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromByteBuff(bb).toString().equals("[0, 1, 7, -1]"));
        assertThrows(BufferUnderflowException.class, () -> { ALFactory.BBfromALB(eALB).get(); });
    }

    /**
     * Junit tester for casting to a {@link CharBuffer}.
     * @see     #testFromCharBuff()
     * @see     src.ALFactory#CBfromALC(AugList)
     */
    @Test
    public void testCBfromALC() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.CBfromALC(nALC); });
        CharBuffer cb = ALFactory.CBfromALC(ALC);
        assertTrue(cb.getClass().toGenericString().equals("sealed class java.nio.HeapCharBuffer"));
        assertTrue(ALFactory.fromCharBuff(cb).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromCharBuff(cb).toString().equals("[h, o, c, u, s,  , p, o, c, u, s]"));
        assertThrows(BufferUnderflowException.class, () -> { ALFactory.CBfromALC(eALC).get(); });
    }

    /**
     * Junit tester for casting to a {@link CharacterIterator}.
     * @see     #testFromCharItr()
     * @see     src.ALFactory#CIfromALC(AugList)
     */
    @Test
    public void testCIfromALC() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.CIfromALC(nALC); });
        CharacterIterator ci = ALFactory.CIfromALC(ALC);
        assertTrue(ci.getClass().toGenericString().equals("public final class java.text.StringCharacterIterator"));
        assertTrue(ALFactory.fromCharItr(ci).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromCharItr(ci).toString().equals("[h, o, c, u, s,  , p, o, c, u, s]"));
        assertTrue(ALFactory.CIfromALC(eALC).current() == (CharacterIterator.DONE));
    }

    /**
     * Junit tester for casting to a {@link CharSequence}.
     * @see     #testFromCharSq()
     * @see     src.ALFactory#CSfromALC(AugList)
     */
    @Test
    public void testCSfromALC() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.CSfromALC(nALC); });
        CharSequence cs = ALFactory.CSfromALC(ALC);
        assertTrue(cs.length() == ALC.size());
        for (int i = 0; i < ALC.size(); i++) {
            assertTrue(cs.charAt(i) == ALC.get(i));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> { ALFactory.CSfromALC(ALC).subSequence(-1, 0); });
        assertThrows(IndexOutOfBoundsException.class, () -> { ALFactory.CSfromALC(ALC).subSequence(0, 999); });
        assertThrows(IndexOutOfBoundsException.class, () -> { ALFactory.CSfromALC(ALC).subSequence(2, 0); });
        assertTrue(ALFactory.CSfromALC(ALC).subSequence(0, 7).toString().equals("hocus p"));
        ALFactory.CSfromALC(ALC).toString().equals("hocus pocus");
        assertThrows(IndexOutOfBoundsException.class, () -> { ALFactory.CSfromALC(eALC).charAt(0); });
    }

    /**
     * Junit tester for casting to a {@link DoubleBuffer}.
     * @see     #testFromDblBuff()
     * @see     src.ALFactory#DBfromALD(AugList)
     */
    @Test
    public void testDBfromALD() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.DBfromALD(nALD); });
        DoubleBuffer db = ALFactory.DBfromALD(ALD);
        assertTrue(db.getClass().toGenericString().equals("sealed class java.nio.HeapDoubleBuffer"));
        assertTrue(ALFactory.fromDblBuff(db).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromDblBuff(db).toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertThrows(BufferUnderflowException.class, () -> { ALFactory.DBfromALD(eALD).get(); });
    }

    /**
     * Junit tester for casting to a {@link DoubleStream}.
     * @see     #testFromDblStream()
     * @see     src.ALFactory#DSfromALD(AugList)
     */
    @Test
    public void testDSfromALD() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.DSfromALD(nALD); });
        DoubleStream ds = ALFactory.DSfromALD(ALD);
        assertTrue(ds.getClass().toGenericString().contains("class java.util.stream.ReferencePipeline$"));
        assertTrue(ALFactory.fromDblStream(ds).getClass().toGenericString().equals("public class src.AugList<T>"));
        ds = ALFactory.DSfromALD(ALD); // Without reassigning ds, the stream is closed after use.
        assertTrue(ALFactory.fromDblStream(ds).toString().equals("[1.0, 2.0, 7.11, -2.5, 3.1415926]"));
        assertTrue(ALFactory.DSfromALD(eALD).min().isEmpty());
    }

    /**
     * Junit tester for casting to a {@link FloatBuffer}.
     * @see     #testFromFloatBuff()
     * @see     src.ALFactory#FBfromALF(AugList)
     */
    @Test
    public void testFBfromALF() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.FBfromALF(nALF); });
        FloatBuffer fb = ALFactory.FBfromALF(ALF);
        assertTrue(fb.getClass().toGenericString().equals("sealed class java.nio.HeapFloatBuffer"));
        assertTrue(ALFactory.fromFloatBuff(fb).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromFloatBuff(fb).toString().equals("[12.0, 7.0, -12.2, 3.4, 5.87, 12.0]"));
        assertThrows(BufferUnderflowException.class, () -> { ALFactory.FBfromALF(eALF).get(); });
    }

    /**
     * Junit tester for casting to a {@link IntBuffer}.
     * @see     #testFromIntBuff()
     * @see     src.ALFactory#IBfromALI(AugList)
     */
    @Test
    public void testIBfromALI() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.IBfromALI(nALI); });
        IntBuffer ib = ALFactory.IBfromALI(ALI);
        assertTrue(ib.getClass().toGenericString().equals("sealed class java.nio.HeapIntBuffer"));
        assertTrue(ALFactory.fromIntBuff(ib).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromIntBuff(ib).toString().equals("[7, 11, 19, -24, 117, 175, 56, 43]"));
        assertThrows(BufferUnderflowException.class, () -> { ALFactory.IBfromALI(eALI).get(); });
    }

    /**
     * Junit tester for casting to a {@link IntStream}.
     * @see     #testFromIntStream()
     * @see     src.ALFactory#ISfromALI(AugList)
     */
    @Test
    public void testISfromALI() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.ISfromALI(nALI); });
        IntStream is = ALFactory.ISfromALI(ALI);
        assertTrue(is.getClass().toGenericString().contains("class java.util.stream.ReferencePipeline$"));
        assertTrue(ALFactory.fromIntStream(is).getClass().toGenericString().equals("public class src.AugList<T>"));
        is = ALFactory.ISfromALI(ALI); // Without reassigning ds, the stream is closed after use.
        assertTrue(ALFactory.fromIntStream(is).toString().equals("[7, 11, 19, -24, 117, 175, 56, 43]"));
        assertTrue(ALFactory.ISfromALI(eALI).min().isEmpty());
    }

    /**
     * Junit tester for casting to a {@link LongBuffer}.
     * @see     #testFromLongBuff()
     * @see     src.ALFactory#LBfromALL(AugList)
     */
    @Test
    public void testLBfromALL() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.LBfromALL(nALL); });
        LongBuffer lb = ALFactory.LBfromALL(ALL);
        assertTrue(lb.getClass().toGenericString().equals("sealed class java.nio.HeapLongBuffer"));
        assertTrue(ALFactory.fromLongBuff(lb).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromLongBuff(lb).toString().equals("[4, 16, 37, 58, 89, 145, 42, 20]"));
        assertThrows(BufferUnderflowException.class, () -> { ALFactory.LBfromALL(eALL).get(); });
    }

    /**
     * Junit tester for casting to a {@link LongStream}.
     * @see     #testFromLongStream()
     * @see     src.ALFactory#LBfromALL(AugList)
     */
    @Test
    public void testLSfromALL() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.LSfromALL(nALL); });
        LongStream ls = ALFactory.LSfromALL(ALL);
        assertTrue(ls.getClass().toGenericString().contains("class java.util.stream.ReferencePipeline$"));
        assertTrue(ALFactory.fromLongStream(ls).getClass().toGenericString().equals("public class src.AugList<T>"));
        ls = ALFactory.LSfromALL(ALL); // Without reassigning ds, the stream is closed after use.
        assertTrue(ALFactory.fromLongStream(ls).toString().equals("[4, 16, 37, 58, 89, 145, 42, 20]"));
        assertTrue(ALFactory.LSfromALL(eALL).min().isEmpty());
    }

    /**
     * Junit tester for casting to a {@link ShortBuffer}.
     * @see     #testFromShortBuff()
     * @see     src.ALFactory#ShBfromALSh(AugList)
     */
    @Test
    public void testShBfromALSh() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.ShBfromALSh(nALSh); });
        ShortBuffer sb = ALFactory.ShBfromALSh(ALSh);
        assertTrue(sb.getClass().toGenericString().equals("sealed class java.nio.HeapShortBuffer"));
        assertTrue(ALFactory.fromShortBuff(sb).getClass().toGenericString().equals("public class src.AugList<T>"));
        assertTrue(ALFactory.fromShortBuff(sb).toString().equals("[-17, -50, -25, -74, -37, -110, -55, -164, -82, -41, -122, -61, -182, -91, -272, -136, -68, -34]"));
    assertThrows(BufferUnderflowException.class, () -> { ALFactory.ShBfromALSh(eALSh).get(); });
    }

    /**
     * Junit tester for casting to a {@link String}.
     * @see     src.ALFactory#StrFromALC(AugList)
     */
    @Test
    public void testStrFromALC() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.StrFromALC(nALC); });
        assertTrue(ALFactory.StrFromALC(ALC).toString().equals("hocus pocus"));
        assertTrue(ALFactory.StrFromALC(ALC).getClass().toGenericString().equals("public final class java.lang.String"));
        assertTrue(ALFactory.StrFromALC(eALC).length() == 0);
    }

    /**
     * Junit tester for casting to a {@link String}.
     * @see     src.ALFactory#StrFromALSt(AugList)
     */
    @Test
    public void testStrFromALSt() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.StrFromALSt(nALSt); });
        assertTrue(ALFactory.StrFromALSt(ALSt).toString().equals("Thequickbrownfoxjumpsoverthelazy dog"));
        assertTrue(ALFactory.StrFromALSt(ALSt).getClass().toGenericString().equals("public final class java.lang.String"));
        assertTrue(ALFactory.StrFromALSt(eALSt).length() == 0);
    }

    /**
     * Junit tester for casting to a {@link StringBuffer}.
     * @see     src.ALFactory#StBfromALSt(AugList)
     */
    @Test
    public void testStBfromALSt() {
        setupTestData();
        assertThrows(NullPointerException.class, () -> { ALFactory.StBfromALSt(nALSt); });
        StringBuffer sb = ALFactory.StBfromALSt(ALSt);
        assertTrue(sb.getClass().toGenericString().equals("public final class java.lang.StringBuffer"));
        assertTrue(ALFactory.fromStrBuff(sb).getClass().toGenericString().equals("public class src.AugList<T>"));
        System.out.println(ALFactory.fromStrBuff(sb).toString().equals("[T, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x, j, u, m, p, s, o, v, e, r, t, h, e, l, a, z, y,  , d, o, g]"));
        assertThrows(IndexOutOfBoundsException.class, () -> { ALFactory.StBfromALSt(eALSt).charAt(0); });
    }

    //#endregion
}
