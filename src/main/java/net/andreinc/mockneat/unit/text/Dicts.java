package net.andreinc.mockneat.unit.text;

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

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.file.FileManager;

import java.util.Collections;
import java.util.List;

import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Dicts extends MockUnitBase {

    private final FileManager fm = FileManager.getInstance();

    public static Dicts dicts() {
        return MockNeat.threadLocal().dicts();
    }

    protected Dicts() {}

    public Dicts(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate arbitrary values from a Dictionary ({@link DictType}.</p>
     *
     * @param type The type of the dictionary.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString type(DictType type) {
        notNull(type, "type");
        return () -> mockNeat.fromStrings(fm.getLines(type))::val;
    }

    /**
     * Returns a new {@code MockUnitString} that can be used to generate arbitrary values from a list of dictionaries {@link DictType}.
     *
     * @param types A var-arg array containing a list of possible {@code DictType}s.
     * @return A mew {@code MockUnitString}
     */
    public MockUnitString types(DictType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> {
            DictType type = mockNeat.from(types).val();
            return mockNeat.fromStrings(fm.getLines(type))::val;
        };
    }

    /**
     * Returns all data from the dictionary as an immutable list.
     *
     * @param type the dictionary type we want to retrieve
     * @return The list of lines from the dict
     */
    public List<String> data(DictType type) {
        notNull(type, "type");
        return Collections.unmodifiableList(fm.getLines(type));
    }
}
