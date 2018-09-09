package net.andreinc.mockneat.unit.networking;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.MACAddressFormatType;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static net.andreinc.mockneat.types.enums.MACAddressFormatType.COLON_EVERY_2_DIGITS;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Macs extends MockUnitBase implements MockUnitString {

    public Macs(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return type(COLON_EVERY_2_DIGITS)::val;
    }

    /**
     * <p>This method can be used to setup the format of the MAC address.</p>
     *
     * @param type The format.
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString type(MACAddressFormatType type) {
        notNull(type, "type");
        Supplier<String> supplier = () -> {
            StringBuilder buff = new StringBuilder();
            IntStream.range(0, 12).forEach(i -> type.getConsumer().consume(i, buff, this.mockNeat));
            return buff.deleteCharAt(0).toString();
        };
        return () -> supplier;
    }
}
