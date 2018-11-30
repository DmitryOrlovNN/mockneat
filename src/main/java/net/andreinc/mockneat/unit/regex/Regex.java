package net.andreinc.mockneat.unit.regex;

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

import com.mifmif.common.regex.Generex;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;
import static net.andreinc.mockneat.utils.ValidationUtils.validRegex;

public class Regex implements MockUnitString {

    public static Regex regex(String regex) {
        return MockNeat.threadLocal().regex(regex);
    }

    private final String regex;

    public Regex(String regex) {
        this.regex = regex;
    }

    @Override
    public Supplier<String> supplier() {
        notNull(regex, "regex");
        validRegex(regex);
        return () -> new Generex(regex).random();
    }
}
