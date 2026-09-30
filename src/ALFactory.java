/**
 * Copyright and Licensing notice:
 * 
 * This file is supplied under a modified GNU LGPL V2.1 License.
 * Please see the provided LICENSE file for more information.
 */
package src;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.file.Path;
import java.text.CharacterIterator;
// import java.util.NoSuchElementException;
// import java.util.Comparator;
// import java.util.DoubleSummaryStatistics;
// import java.util.NoSuchElementException;
import java.util.Objects;
// import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.function.ToDoubleFunction;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.atomic.DoubleAccumulator;
// import java.util.function.BiConsumer;
// import java.util.function.DoubleBinaryOperator;
// import java.util.function.DoubleConsumer;
// import java.util.function.DoubleFunction;
// import java.util.function.DoublePredicate;
// import java.util.function.DoubleToIntFunction;
// import java.util.function.DoubleToLongFunction;
// import java.util.function.DoubleUnaryOperator;
// import java.util.function.ObjDoubleConsumer;
// import java.util.function.Supplier;
import java.util.regex.PatternSyntaxException;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
// import javax.naming.OperationNotSupportedException;

/**
 * A Factory class containing static methods for casting to and from specific {@link AugList} types.
 * @since   AugList V2
 * @author  "https://github.com/sm9798282" aka "https://csgitlab.reading.ac.uk/yn019034"
 * @see     src.AugList
 * @see     tests.ALFactoryTest
 */
public abstract class ALFactory {
    //#region Casts to AugList

    /**
     * Create a new {@link AugList} of {@link Byte} from the given {@link ByteBuffer}.
     * @param   bb
     *          The {@link ByteBuffer} in question.
     * @return  A new {@link AugList} of {@link Byte}.
     * @tags    Constructor
     */
    public static AugList<Byte> fromByteBuff(ByteBuffer bb) {
        if (Objects.isNull(bb)) {
            return new AugList<Byte>();
        }
        AugList<Byte> ret = new AugList<Byte>();
        for (int i = 0; i < bb.capacity(); i++) {
            ret.add(bb.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Character} from the given {@link CharBuffer}.
     * @param   cb
     *          The {@link CharBuffer} in question.
     * @return  A new {@link AugList} of {@link Character}.
     * @tags    Constructor
     */
    public static AugList<Character> fromCharBuff(CharBuffer cb) {
        if (Objects.isNull(cb)) {
            return new AugList<Character>();
        }
        AugList<Character> ret = new AugList<Character>();
        for (int i = 0; i < cb.capacity(); i++) {
            ret.add(cb.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Character} from the given {@link CharacterIterator}.
     * @param   ci
     *          The {@link CharacterIterator} in question.
     * @return  A new {@link AugList} of {@link Character}.
     * @tags    Constructor
     */
    public static AugList<Character> fromCharItr(CharacterIterator ci) {
        if (Objects.isNull(ci)) {
            return new AugList<Character>();
        }
        AugList<Character> ret = new AugList<Character>();
        ret.add(ci.first());
        while (ci.current() != (CharacterIterator.DONE)) {
            ret.add(ci.next());
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Character} from the given {@link CharacterSequence}.
     * @param   cs
     *          The {@link CharacterSequence} in question.
     * @return  A new {@link AugList} of {@link Character}.
     * @tags    Constructor
     */
    public static AugList<Character> fromCharSq(CharSequence cs) {
        if (Objects.isNull(cs)) {
            return new AugList<Character>();
        }
        AugList<Character> ret = new AugList<Character>();
        for (int i = 0; i < cs.length(); i++) {
            ret.add(cs.charAt(i));
        }
        return ret;
    }

    
    /**
     * Create a new {@link AugList} of {@link Character} from the given {@link String}.
     * @param   s
     *          The {@link String} in question.
     * @return  A new {@link AugList} of {@link Character}.
     * @tags    Constructor
     */
    public static AugList<Character> fromStr(String s) {
        if (Objects.isNull(s)) {
            return new AugList<Character>();
        }
        AugList<Character> ret = new AugList<Character>();
        for (int i = 0; i < s.length(); i++) {
            ret.add(s.toCharArray()[i]);
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link String} from the given {@link StringBuffer}.
     * @param   sb
     *          The {@link StingBuffer} in question.
     * @return  A new {@link AugList} of {@link String}.
     * @tags    Constructor
     */
    public static AugList<Character> fromStrBuff(StringBuffer sb) {
        if (Objects.isNull(sb)) {
            return new AugList<Character>();
        }
        AugList<Character> ret = new AugList<Character>();
        for (int i = 0; i < sb.capacity(); i++) {
            ret.add(sb.charAt(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Double} from the given {@link DoubleBuffer}.
     * @param   db
     *          The {@link DoubleBuffer} in question.
     * @return  A new {@link AugList} of {@link Double}.
     * @tags    Constructor
     */
    public static AugList<Double> fromDblBuff(DoubleBuffer db) {
        if (Objects.isNull(db)) {
            return new AugList<Double>();
        }
        AugList<Double> ret = new AugList<Double>();
        for (int i = 0; i < db.capacity(); i++) {
            ret.add(db.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Double} from the given {@link DoubleStream}.
     * @param   ds
     *          The {@link DoubleStream} in question.
     * @return  A new {@link AugList} of {@link Double}.
     * @tags    Constructor
     */
    public static AugList<Double> fromDblStream(DoubleStream ds) {
        if (Objects.isNull(ds)) {
            return new AugList<Double>();
        }
        return new AugList<Double>(ds.boxed());
    }

    /**
     * Create a new {@link AugList} of {@link Float} from the given {@link FloatBuffer}.
     * @param   fb
     *          The {@link FloatBuffer} in question.
     * @return  A new {@link AugList} of {@link Float}.
     * @tags    Constructor
     */
    public static AugList<Float> fromFloatBuff(FloatBuffer fb) {
        if (Objects.isNull(fb)) {
            return new AugList<Float>();
        }
        AugList<Float> ret = new AugList<Float>();
        for (int i = 0; i < fb.capacity(); i++) {
            ret.add(fb.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Integer} from the given {@link IntBuffer}.
     * @param   ib
     *          The {@link IntBuffer} in question.
     * @return  A new {@link AugList} of {@link Integer}.
     * @tags    Constructor
     */
    public static AugList<Integer> fromIntBuff(IntBuffer ib) {
        if (Objects.isNull(ib)) {
            return new AugList<Integer>();
        }
        AugList<Integer> ret = new AugList<Integer>();
        for (int i = 0; i < ib.capacity(); i++) {
            ret.add(ib.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Integer} from the given int.
     * Each digit will become its own entry.
     * @param   i
     *          The int in question.
     * @return  A new {@link AugList} of {@link Integer}.
     * @tags    Constructor
     */
    public static AugList<Integer> fromDigits(int i) {
        if (i < 0) {
            i = -i;
        }
        String iString = String.valueOf(i);
        AugList<Integer> ret = new AugList<Integer>();
        for (int j = 0; j < iString.length(); j++) {
            ret.add(Integer.valueOf(iString.toCharArray()[i] + ""));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Integer} from the given {@link String}.
     * Each character is translated into its Unicode code point.
     * @param   i
     *          The int in question.
     * @return  A new {@link AugList} of {@link Integer}.
     * @tags    Constructor
     */
    public static AugList<Integer> fromUnicode(String s) {
        if (Objects.isNull(s)) {
            return new AugList<Integer>();
        }
        AugList<Integer> ret = new AugList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            ret.add(Integer.valueOf(s.codePointAt(i) + ""));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Integer} from the given {@link IntStream}.
     * @param   is
     *          The {@link IntStream} in question.
     * @return  A new {@link AugList} of {@link Integer}.
     * @tags    Constructor
     */
    public static AugList<Integer> fromIntStream(IntStream is) {
        if (Objects.isNull(is)) {
            return new AugList<Integer>();
        }
        return new AugList<Integer>(is.boxed());
    }

    /**
     * Create a new identity {@link AugList} of {@link Integer} of the given size.
     * @param   size
     *          The size of the resultant {@link AugList}.
     * @return  A new Identity {@link AugList} of the given size.
     * @tags    Constructor
     */
    public static AugList<Integer> identityAL(int size) {
        AugList<Integer> ret = new AugList<Integer>();
        for (int i = 0; i < size; i++) {
            ret.add(i);
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Long} from the given {@link LongBuffer}.
     * @param   lb
     *          The {@link LongBuffer} in question.
     * @return  A new {@link AugList} of {@link Long}.
     * @tags    Constructor
     */
    public static AugList<Long> fromLongBuff(LongBuffer lb) {
        if (Objects.isNull(lb)) {
            return new AugList<Long>();
        }
        AugList<Long> ret = new AugList<Long>();
        for (int i = 0; i < lb.capacity(); i++) {
            ret.add(lb.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link Long} from the given {@link LongStream}.
     * @param   ls
     *          The {@link LongStream} in question.
     * @return  A new {@link AugList} of {@link Long}.
     * @tags    Constructor
     */
    public static AugList<Long> fromLongStream(LongStream ls) {
        if (Objects.isNull(ls)) {
            return new AugList<Long>();
        }
        return new AugList<Long>(ls.boxed());
    }

    /**
     * Create a new {@link AugList} of {@link Short} from the given {@link ShortBuffer}.
     * @param   sb
     *          The {@link ShortBuffer} in question.
     * @return  A new {@link AugList} of {@link Short}.
     * @tags    Constructor
     */
    public static AugList<Short> fromShortBuff(ShortBuffer sb) {
        if (Objects.isNull(sb)) {
            return new AugList<Short>();
        }
        AugList<Short> ret = new AugList<Short>();
        for (int i = 0; i < sb.capacity(); i++) {
            ret.add(sb.get(i));
        }
        return ret;
    }

    /**
     * Create a new {@link AugList} of {@link String} from the given delimited {@link String}.
     * @param   s
     *          The {@link String} in question.
     * @param   regex
     *          The regular expression by which s will be split.
     * @return  A new {@link AugList} of {@link String}.
     * @throws  PatternSyntaxException
     *          If the supplied regex string has invalid syntax.
     * @tags    Constructor
     */
    public static AugList<String> fromDelimitedString(String s, String regex) {
        if (Objects.isNull(s) || Objects.isNull(regex)) {
            return new AugList<String>();
        }
        return new AugList<String>(s.split(regex));
    }

    /**
     * Create a new {@link AugList} of {@link String} from the contents of the file on the given {@link Path}.
     * @param       p
     *              The {@link Path} in question.
     * @return      A new {@link AugList} of {@link String}.
     *              Will be empty if an {@link IOException} is thrown.
     * @overloads   {@link #fromFile(Path)}, {@link #fromFile(String)}
     * @tags        Constructor
     */
    public static AugList<String> fromFile(Path p) {
        if (Objects.isNull(p)) {
            return new AugList<String>();
        }
        try {
            Scanner reader = new Scanner(p);
            AugList<String> ret = new AugList<String>();
            while (reader.hasNext()) {
                ret.add(reader.nextLine());
            }
            reader.close();
            return ret;
        } catch (Exception e) {
            return new AugList<String>();
        }
    }

    /**
     * Create a new {@link AugList} of {@link String} from the contents of the file found on the given pathString.
     * @param       pathString
     *              The pathString in question.
     * @return      A new {@link AugList} of {@link String}.
     *              Will be empty if an {@link IOException} is thrown.
     * @overloads   {@link #fromFile(Path)}, {@link #fromFile(String)}
     * @tags        Constructor
     */
    public static AugList<String> fromFile(String pathString) {
        return fromFile(Path.of(pathString));
    }

    /**
     * Create a new {@link AugList} of {@link String} from the given {@link String}.
     * Splits by the newline control characters.
     * @param   s
     *          The {@link String} in question.
     * @return  A new {@link AugList} of {@link String}.
     * @tags    Constructor
     */
    public static AugList<String> fromNewLinedString(String s) {
        if (Objects.isNull(s)) {
            return new AugList<String>();
        }
        return new AugList<String>(s.lines());
    }

    //#endregion

    //#region Casts from AugList
    /*
     * Java's methods are typically written in camelCase, as opposed to PascalCase.
     * BigMLib generally follows this example, but these methods are the exception.
     * These methods are formatted in this manner:
     * The first 2 or 3 letters correspond to the return type
     * Then the word "From"
     * Then the last 3 letters correspond to the specific AugList type taken
     * For example:
     * 
     * BBFromALB is short for ByteBufferFromAugListByte
     */

    /**
     * Creates a new {@link ByteBuffer} from the given {@link AugList} of {@link Byte}.
     * @param   ALB
     *          The {@link AugList} in question.
     * @return  A new {@link ByteBuffer}.
     * @throws  NullPointerException
     *          ALB is null.
     * @tags    Converter
     */
    public static ByteBuffer BBfromALB(AugList<Byte> ALB) {
        ByteBuffer bb = ByteBuffer.allocate(ALB.size()); // Implicit null check (and throw)
        for (int i = 0; i < ALB.size(); i++) {
            bb.put(ALB.get(i));
        }
        return bb;
    }

    /**
     * Creates a new {@link CharBuffer} from the given {@link AugList} of {@link Character}.
     * @param   ALC
     *          The {@link AugList} in question.
     * @return  A new {@link CharBuffer}.
     * @throws  NullPointerException
     *          ALC is null.
     * @tags    Converter
     */
    public static CharBuffer CBfromALC(AugList<Character> ALC) {
        CharBuffer bb = CharBuffer.allocate(ALC.size()); // Implicit null check (and throw)
        for (int i = 0; i < ALC.size(); i++) {
            bb.put(ALC.get(i));
        }
        return bb;
    }

    /**
     * Creates a new {@link CharSequence} from the given {@link AugList} of {@link Character}.
     * @param   ALC
     *          The {@link AugList} in question.
     * @return  A new {@link CharSequence}.
     * @throws  NullPointerException
     *          ALC is null.
     * @tags    Converter
     */
    public static CharSequence CSFromALC(AugList<Character> ALC) {
        if (Objects.isNull(ALC)) {
            throw new NullPointerException();
        }
        return new CharSequence() {
            /**
             * @return  The number of {@code char}s in this sequence, or the size of the underlying {@link AugList}.
             */
            public int length() {
                return ALC.size();
            }

            /**
             * @return  The specified {@code char} value.
             */
            public char charAt(int index) {
                return ALC.get(index);
            }

            /**
             * @return  The specified subsequence
             */
            public CharSequence subSequence(int start, int end) {
                return CSFromALC(ALC.subList(start, end));
            }

            /**
             * @return  A string consisting of exactly this sequence of characters
             */
            public String toString() {
                String ret = "";
                ALC.forEach(c -> ret.concat(c + ""));
                return ret;
            }
        };
    }

    /**
     * Creates a new {@link DoubleBuffer} from the given {@link AugList} of {@link Double}.
     * @param   ALD
     *          The {@link AugList} in question.
     * @return  A new {@link DoubleBuffer}.
     * @throws  NullPointerException
     *          ALD is null.
     * @tags    Converter
     */
    public static DoubleBuffer DBfromALD(AugList<Double> ALD) {
        DoubleBuffer bb = DoubleBuffer.allocate(ALD.size()); // Implicit null check (and throw)
        for (int i = 0; i < ALD.size(); i++) {
            bb.put(ALD.get(i));
        }
        return bb;
    }

    /**
     * Creates a new {@link DoubleStream} from the given {@link AugList} of {@link Double}.
     * @param   ALD
     *          The {@link AugList} in question.
     * @return  A new {@link DoubleStream}.
     * @throws  NullPointerException
     *          ALD is null.
     * @note    Within the comments is a half-finished implementation of {@link DoubleStream}.
     *          <p>This was made redundant by the discovery of {@link Stream#mapToDouble(ToDoubleFunction)}.
     * @tags    Converter
     */
    public static DoubleStream DSFromALD(AugList<Double> ALD) {
        if (Objects.isNull(ALD)) {
            throw new NullPointerException();
        }
        return ALD.stream().mapToDouble(d -> d);
        //#region Attempted Implementation
        // return new DoubleStream() {
        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      * @throws  NullPointerException
        //      *          predicate is null.
        //      */
        //     public DoubleStream filter(DoublePredicate predicate) {
        //         AugList<Double> res = new AugList<Double>();
        //         for (int i = 0; i < ALD.size(); i++) {
        //             if (predicate.test(ALD.get(i))) {
        //                 res.add(ALD.get(i));
        //             }
        //         }
        //         return DSFromALD(res);
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      * @throws  NullPointerException
        //      *          mapper is null.
        //      */
        //     public DoubleStream map(DoubleUnaryOperator mapper) {
        //         AugList<Double> res = new AugList<Double>();
        //         for (int i = 0; i < ALD.size(); i++) {
        //             res.add(mapper.applyAsDouble(ALD.get(i)));
        //         }
        //         return DSFromALD(res);
        //     }

        //     /**
        //      * @return  The new {@link Stream}.
        //      * @throws  NullPointerException
        //      *          mapper is null.
        //      */
        //     public <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
        //         AugList<U> res = new AugList<U>();
        //         for (int i = 0; i < ALD.size(); i++) {
        //             res.add(mapper.apply(ALD.get(i)));
        //         }
        //         return res.stream();
        //     }

        //     /**
        //      * @return  The new {@link IntStream}.
        //      * @throws  NullPointerException
        //      *          mapper is null.
        //      */
        //     public IntStream mapToInt(DoubleToIntFunction mapper) {
        //         AugList<Integer> res = new AugList<Integer>();
        //         for (int i = 0; i < ALD.size(); i++) {
        //             res.add(mapper.applyAsInt(ALD.get(i)));
        //         }
        //         return ISFromALI(res);
        //     }

        //     /**
        //      * @return  The new {@link LongStream}.
        //      * @throws  NullPointerException
        //      *          mapper is null.
        //      */
        //     public LongStream mapToLong(DoubleToLongFunction mapper) {
        //         AugList<Long> res = new AugList<Long>();
        //         for (int i = 0; i < ALD.size(); i++) {
        //             res.add(mapper.applyAsLong(ALD.get(i)));
        //         }
        //         return LSFromALL(res);
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      * @throws  NullPointerException
        //      *          mapper is null.
        //      */
        //     public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
        //         AugList<Double> res = new AugList<Double>();
        //         for (int i = 0; i < ALD.size(); i++) {
        //             res.add(ALFactory.fromDblStream((DoubleStream)mapper.apply(ALD.get(i))).get(0));
        //         }
        //         return DSFromALD(res);
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      */
        //     public DoubleStream distinct() {
        //         return DSFromALD(ALD.distinctCopy());
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      */
        //     public DoubleStream sorted() {
        //         return DSFromALD(ALD.sort(new Comparator<Double>() {

        //             @Override
        //             public int compare(Double o1, Double o2) {
        //                 return Double.compare(o1, o2);
        //             }
        //         }));
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      * @throws  NullPointerException
        //      *          action is null.
        //      */
        //     public DoubleStream peek(DoubleConsumer action) {
        //         ALD.forEach(d -> action.accept(d));
        //         return DSFromALD(ALD);
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      */
        //     public DoubleStream limit(long maxSize) {
        //         if (maxSize >= ALD.size()) {
        //             return DSFromALD(ALD);
        //         }
        //         return DSFromALD(ALD.subList(0, (int)maxSize));
        //     }

        //     /**
        //      * @return  The new {@link DoubleStream}.
        //      */
        //     public DoubleStream skip(long n) {
        //         if (n < 0) {
        //             throw new IllegalArgumentException();
        //         }
        //         if (ALD.size() <= n) {
        //             return DSFromALD(new AugList<Double>());
        //         }
        //         return DSFromALD(ALD.subList((int)n, ALD.size()));
        //     }

        //     /**
        //      * @return      The new {@link DoubleStream}.
        //      * @throws      NullPointerException
        //      *              action is null.
        //      * @implNote    Identical to {@link #forEachOrdered(DoubleConsumer)}
        //      *              (Due to being unable to figure out how to parallelise the operation)
        //      */
        //     public void forEach(DoubleConsumer action) {
        //         forEachOrdered(action);
        //         // ExecutorService es = Executors.newFixedThreadPool(ALD.size() / 8);
        //         // es.execute(() -> {
        //         //     for (int i = 0; i < array.length; i++) {
                        
        //         //     }
        //         // });
        //         // try {
        //         //     es.awaitTermination(1500, TimeUnit.MILLISECONDS);
        //         // } catch (InterruptedException e) {
        //         //     forEachOrdered(action);
        //         // }
        //     }

        //     /**
        //      * @return      The new {@link DoubleStream}.
        //      * @throws      NullPointerException
        //      *              action is null.
        //      * @implNote    Identical to {@link #forEach(DoubleConsumer)}
        //      *              (Due to being unable to figure out how to parallelise the operation)
        //      */
        //     public void forEachOrdered(DoubleConsumer action) {
        //         for (int i = 0; i < ALD.size(); i++) {
        //             action.accept(ALD.get(i));
        //         }
        //     }

        //     /**
        //      * @return  An array containing the elements of this stream
        //      */
        //     public double[] toArray() {
        //         double[] ret = new double[ALD.size()];
        //         for (int i = 0; i < ret.length; i++) {
        //             ret[i] = ALD.get(i);
        //         }
        //         return ret;
        //     }

        //     /**
        //      * @return      The result of the reduction.
        //      * @implNote    Based off the recommended implementation in {@link DoubleStream#reduce(DoubleBinaryOperator)}'s doc string.
        //      *              <p>(Although the code provided seemingly does not actually work on its own?)
        //      * @see         #sum()
        //      * @see         #min()
        //      * @see         #max()
        //      * @see         #average()
        //      */
        //     public double reduce(double identity, DoubleBinaryOperator op) {
        //         double result = identity;
        //         DoubleAccumulator accumulator = new DoubleAccumulator(op, identity);
        //         for (double element : ALD)
        //             accumulator.accumulate(element);
        //         return result;
        //     }

        //     /**
        //      * @return      The result of the reduction.
        //      * @implNote    Assumes the identity is 0. (This means any {@link DoubleBinaryOperator} relying on multiplication will not work as expected.)
        //      *              <p>In that case, please use {@link #reduce(double, DoubleBinaryOperator)}.
        //      */
        //     public OptionalDouble reduce(DoubleBinaryOperator op) {
        //         if (ALD.isEmpty()) {
        //             return OptionalDouble.empty();
        //         }
        //         double res = 0;
        //         for (int i = 0; i < ALD.size(); i++) {
        //             res = op.applyAsDouble(res, i);
        //         }
        //         return OptionalDouble.of(res);
        //     }

        //     /**
        //      * @return      The result of the reduction
        //      * @implNote    Uses the recommended implementation.
        //      */
        //     public <R> R collect(Supplier<R> supplier,
        //                          ObjDoubleConsumer<R> accumulator,
        //                          BiConsumer<R, R> combiner) {
        //         R result = supplier.get();
        //         for (double element : ALD)
        //             accumulator.accept(result, element);
        //         return result;
        //     }

        //     /**
        //      * @return      The sum of elements in this {@link DoubleStream}.
        //      * @implNote    Uses the recommended implementation.
        //      */
        //     public double sum() {
        //         return reduce(0, Double::sum);
        //     }

        //     /**
        //      * @return      An {@link OptionalDouble} which is either empty or contains the smallest element in this {@link DoubleStream}.
        //      * @implNote    Uses the recommended implementation.
        //      */
        //     public OptionalDouble min() {
        //         return reduce(Double::min);
        //     }

        //     /**
        //      * @return      An {@link OptionalDouble} which is either empty or contains the largest element in this {@link DoubleStream}.
        //      * @implNote    Uses the recommended implementation.
        //      */
        //     public OptionalDouble max() {
        //         return reduce(Double::max);
        //     }

        //     /**
        //      * @return  The count of elements in this {@link DoubleStream}, or the {@link AugList#size()} of the underlying {@link AugList}.
        //      */
        //     public long count() {
        //         return ALD.size();
        //     }

        //     /**
        //      * @return  An {@link OptionalDouble} which is either empty or contains the average of this {@link DoubleStream}.
        //      */
        //     public OptionalDouble average() {
        //         OptionalDouble sum = reduce(Double::sum);
        //         if (sum.isEmpty()) {
        //             return sum;
        //         }
        //         // Cannot divide by 0 as sum will be empty when ALD is empty.
        //         // (And this code is only reachable if ALD is non-empty.)
        //         return OptionalDouble.of(sum.getAsDouble() / ALD.size());
        //     }

        //     /** 
        //      * @return  A {@link DoubleSummaryStatistics} describing this {@link DoubleStream}.
        //      * @throws  IllegalStateException
        //      *          {@code count == 0}
        //      */
        //     public DoubleSummaryStatistics summaryStatistics() {
        //         if (count() == 0) {
        //             throw new IllegalStateException();
        //         }
        //         OptionalDouble min = min(), max = max();
        //         return new DoubleSummaryStatistics(count(), min.getAsDouble(), max.getAsDouble(), sum());
        //     }

        //     /**
        //      * @return  {@code true} if any elements of this {@link DoubleStream} match the provided {@link Predicate} (and {@code false} otherwise.)
        //      */
        //     public boolean anyMatch(DoublePredicate predicate) {
        //         for (int i = 0; i < ALD.size(); i++) {
        //             if (predicate.test(ALD.get(i))) {
        //                 return true;
        //             }
        //         }
        //         return false;
        //     }

        //     /**
        //      * @return  {@code true} if all elements of this {@link DoubleStream} match the provided {@link Predicate} (and {@code false} otherwise.)
        //      */
        //     public boolean allMatch(DoublePredicate predicate) {
        //         for (int i = 0; i < ALD.size(); i++) {
        //             if (!predicate.test(ALD.get(i))) {
        //                 return false;
        //             }
        //         }
        //         return true;
        //     }

        //     /**
        //      * @return  {@code true} if no element of this {@link DoubleStream} matches the provided {@link Predicate} (and {@code false} otherwise.)
        //      */
        //     public boolean noneMatch(DoublePredicate predicate) {
        //         return anyMatch(predicate.negate());
        //     }

        //     /**
        //      * @return  An {@link OptionalDouble} which is either empty or contains the first element of this {@link DoubleStream}.
        //      */
        //     public OptionalDouble findFirst() {
        //         if (ALD.isEmpty()) {
        //             return OptionalDouble.empty();
        //         }
        //         return OptionalDouble.of(ALD.get(0));
        //     }

        //     /**
        //      * @return  An {@link OptionalDouble} which is either empty or contains an element of this {@link DoubleStream}.
        //      */
        //     public OptionalDouble findAny() {
        //         if (ALD.isEmpty()) {
        //             return OptionalDouble.empty();
        //         }
        //         return OptionalDouble.of(ALD.getRandom());
        //     }

        //     /**
        //      * @return  A {@link Stream} consistent with this {@link DoubleStream}, with each element boxed as a {@link Double}.
        //      */
        //     public Stream<Double> boxed() {
        //         return ALD.stream();
        //     }

        //     /**
        //      * @return  An "equivalent" sequential {@link DoubleStream} - or this.
        //      */
        //     public DoubleStream sequential() {
        //         return ALD.stream().mapToDouble(d -> d);
        //     }

        //     /**
        //      * Supposed to return an equivalent parallel {@link DoubleStream}.
        //      * Whilst {@link AugList} can return both sequential and parallel {@link Stream Streams},
        //      * <p>there is no mechanism (at least to my knowledge) to convert from a {@code Stream<Double>} to a {@link DoubleStream}.
        //      * @throws  IllegalStateException
        //      *          Always.
        //      */
        //     public DoubleStream parallel() {
        //         return ALD.parallelStream().mapToDouble(d -> d);
        //     }
        // };
        //#endregion
    }

    /**
     * Creates a new {@link FloatBuffer} from the given {@link AugList} of {@link Float}.
     * @param   ALF
     *          The {@link AugList} in question.
     * @return  A new {@link FloatBuffer}.
     * @throws  NullPointerException
     *          ALF is null.
     * @tags    Converter
     */
    public static FloatBuffer FBfromALF(AugList<Float> ALF) {
        FloatBuffer bb = FloatBuffer.allocate(ALF.size()); // Implicit null check (and throw)
        for (int i = 0; i < ALF.size(); i++) {
            bb.put(ALF.get(i));
        }
        return bb;
    }

    /**
     * Creates a new {@link IntBuffer} from the given {@link AugList} of {@link Integer}.
     * @param   ALI
     *          The {@link AugList} in question.
     * @return  A new {@link IntBuffer}.
     * @throws  NullPointerException
     *          ALB is null.
     * @tags    Converter
     */
    public static IntBuffer IBfromALI(AugList<Integer> ALI) {
        IntBuffer bb = IntBuffer.allocate(ALI.size()); // Implicit null check (and throw)
        for (int i = 0; i < ALI.size(); i++) {
            bb.put(ALI.get(i));
        }
        return bb;
    }

    /**
     * Creates a new {@link IntStream} from the given {@link AugList} of {@link Integer}.
     * @param   ALI
     *          The {@link AugList} in question.
     * @return  A new {@link IntStream}.
     * @throws  NullPointerException
     *          ALI is null.
     * @tags    Converter
     */
    public static IntStream ISFromALI(AugList<Integer> ALI) {
        if (Objects.isNull(ALI)) {
            throw new NullPointerException();
        }
        return ALI.stream().mapToInt(i -> i);
    }

    /**
     * Creates a new {@link LongBuffer} from the given {@link AugList} of {@link Long}.
     * @param   ALL
     *          The {@link AugList} in question.
     * @return  A new {@link LongBuffer}.
     * @throws  NullPointerException
     *          ALL is null.
     * @tags    Converter
     */
    public static LongBuffer LBfromALL(AugList<Long> ALL) {
        LongBuffer bb = LongBuffer.allocate(ALL.size()); // Implicit null check (and throw)
        for (int i = 0; i < ALL.size(); i++) {
            bb.put(ALL.get(i));
        }
        return bb;
    }

    /**
     * Creates a new {@link LongStream} from the given {@link AugList} of {@link Long}.
     * @param   ALL
     *          The {@link AugList} in question.
     * @return  A new {@link LongStream}.
     * @throws  NullPointerException
     *          ALL is null.
     * @tags    Converter
     */
    public static LongStream LSFromALL(AugList<Long> ALL) {
        if (Objects.isNull(ALL)) {
            throw new NullPointerException();
        }
        return ALL.stream().mapToLong(l -> l);
    }

    /**
     * Concatenates all the characters in the given {@link AugList} of {@link Character Characters}.
     * @param   ALC
     *          The {@link AugList} in question.
     * @return  The elements of ALC concatenated together.
     * @tags    Terminator
     */
    public static String StrFromALC(AugList<Character> ALC) {
        String ret = "";
        ALC.forEach(c -> ret.concat(c + ""));
        return ret;
    }

    /**
     * Concatenates all the characters in the given {@link AugList} of {@link String Characters}.
     * @param   ALS
     *          The {@link AugList} in question.
     * @return  The elements of ALC concatenated together. DOES NOT insert new lines or spaces.
     * @tags    Terminator
     */
    public static String StrFromALS(AugList<String> ALS) {
        String ret = "";
        ALS.forEach(s -> ret.concat(s + ""));
        return ret;
    }

    //#endregion
}