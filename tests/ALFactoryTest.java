/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
package tests;

import static org.junit.Assert.*;

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
    
    AugList<Byte> ALB;
    AugList<Character> ALC;
    AugList<Double> ALD;
    AugList<Float> ALF;
    AugList<Integer> ALI;
    AugList<Long> ALL;
    AugList<Short> ALsh;
    AugList<String> ALst;

    public void setupTestData() {
        /*
         * Since ALFactory contains only static methods, it does not require instantiation.
         * Hence, it is marked abstract - thus it cannot be instantiated.
         * However, there is still certainly need for AugList test data.
         */
        ALB = new AugList<Byte>((byte)0, (byte)1, (byte)7, (byte)255);
        ALC = new AugList<Character>('h', 'o', 'c', 'u', 's', ' ', 'p', 'o', 'c', 'u', 's');
        ALD = new AugList<Double>(1.0, 2.0, 7.11, -2.5, 3.1415926);
        ALF = new AugList<Float>(12.0f, 7.0f, -12.2f, 3.4f, 5.87f, 12.0f);
        ALI = new AugList<Integer>(7, 11, 19, -24, 117, 175, 56, 43);
        ALL = new AugList<Long>(4l, 16l, 37l, 58l, 89l, 145l, 42l, 20l);
        ALsh = new AugList<Short>((short)-17, (short)-50, (short)-25, (short)-74, (short)-37, (short)-110, (short)-55, (short)-164, (short)-82, (short)-41, (short)-122, (short)-61, (short)-182, (short)-91, (short)-272, (short)-136, (short)-68, (short)-34);
        ALst = new AugList<String>("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy dog");
        /*
         * As strings, the test data is:
         * [0, 1, 7, 255]
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
        testCSFromALC();
        testDBfromALD();
        testDSFromALD();
        testFBfromALF();
        testIBfromALI();
        testISFromALI();
        testLBfromALL();
        testLSFromALL();
        testStrFromALC();
        testStrFromALS();
    }

    //#region Casts to AL

    @Test
    public void testFromByteBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromCharBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromCharItr() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromCharSq() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromDblBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromDblStream() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromDelimitedString() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromDigits() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromFile() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromFileString() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromFloatBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromIntBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromIntStream() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromLongBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromLongStream() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromNewLinedString() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromShortBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromStr() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromStrBuff() {
        setupTestData();
        fail();
    }

    @Test
    public void testFromUnicode() {
        setupTestData();
        fail();
    }

    @Test
    public void testIdentityAL() {
        setupTestData();
        fail();
    }

    //#endregion

    //#region Casts from AL
    @Test
    public void testBBfromALB() {
        setupTestData();
        fail();
    }

    @Test
    public void testCBfromALC() {
        setupTestData();
        fail();
    }

    @Test
    public void testCSFromALC() {
        setupTestData();
        fail();
    }

    @Test
    public void testDBfromALD() {
        setupTestData();
        fail();
    }

    @Test
    public void testDSFromALD() {
        setupTestData();
        fail();
    }

    @Test
    public void testFBfromALF() {
        setupTestData();
        fail();
    }

    @Test
    public void testIBfromALI() {
        setupTestData();
        fail();
    }

    @Test
    public void testISFromALI() {
        setupTestData();
        fail();
    }

    @Test
    public void testLBfromALL() {
        setupTestData();
        fail();
    }

    @Test
    public void testLSFromALL() {
        setupTestData();
        fail();
    }

    @Test
    public void testStrFromALC() {
        setupTestData();
        fail();
    }

    @Test
    public void testStrFromALS() {
        setupTestData();
        fail();
    }
}
