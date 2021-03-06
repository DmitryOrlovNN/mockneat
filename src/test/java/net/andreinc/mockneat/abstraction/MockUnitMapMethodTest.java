package net.andreinc.mockneat.abstraction;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.MockNeat;
import org.junit.Test;

import java.time.LocalDate;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.MOCK_CYCLES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitMapMethodTest {
    @Test
    public void testMapToIntInts() {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                    r.ints()
                        .range(0, 5)
                        .mapToInt(x -> x + 5)
                        .intStream().val()
                        .limit(10)
                        .forEach(x -> assertTrue(x >= 5 && x < 10))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToIntsNullFunct() {
        M.ints().range(0, 5).mapToInt(null).val();
    }

    @Test
    public void testMapToLong() {
        loop(MOCK_CYCLES, () ->
            stream(MOCKS).forEach(r ->
                    r.longs()
                        .range(0, 5)
                        .mapToLong(x -> x +5)
                        .longStream().val()
                        .limit(10)
                        .forEach(x -> assertTrue(x >= 5l && x < 10l))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToLongNullFunct() {
        M.longs().range(0l, 5l).mapToLong(null).val();
    }

    @Test
    public void testMapToDoubleDoubles() {
        loop(MOCK_CYCLES, () ->
            stream(MOCKS).forEach(r ->
                        r.doubles()
                            .range(0.5, 5.0)
                            .mapToDouble(x -> x + 5.0)
                            .doubleStream().val()
                            .limit(10)
                            .forEach(x -> assertTrue(x >= 5.0 && x < 10.0))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToDoubleNullFunct() {
        M.doubles().range(0.5, 10.0).mapToDouble(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapNullFunct() {
        M.ints().from(new int[]{ 5, 7, 8}).map(null).val();
    }

    @Test
    public void testMap() {
        Function<Object, String> f = (x) -> x.toString() + "x";
        MockUnit r = M.ints().from(new int[]{5});
        int i = 100;
        while(i-->0) r = r.map(f);
        String result = r.valStr();
        for(int j = 1; j < result.length(); ++j) {
            assertTrue(result.charAt(j)=='x');
        }
    }

    @Test(expected = NullPointerException.class)
    public void testMapFunct() {
        M.ints().from(new int[]{5}).map(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapToLocalDateNullDateTransformer() {
        M.ints().mapToLocalDate(null);
    }

    @Test
    public void testMapLocalDate() {
       loop(MOCK_CYCLES,
            MOCKS,
            m -> m.ints()
                  .from(new int[] { 2015, 2012, 2013 })
                  .mapToLocalDate(i -> LocalDate.of(i, 01, 01)).get(),
            ld -> assertTrue(ld.getYear() == 2015 || ld.getYear() == 2012 || ld.getYear() == 2013));
    }
}
