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
import net.andreinc.mockneat.utils.file.FileManager;

import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;

public class FromFiles extends MockUnitBase {

    private final FileManager fm = FileManager.getInstance();

    public FromFiles(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Generates a new {@code MockUnitString} that can be used to generate String lines from the given {@code path}.</p>
     *
     * <p><em>Note:</em> The files needs to exist and have read rights.</p>
     *
     * @param path The path of the file from the disk.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString from(String path) {
        notEmpty(path, "path");
        return () -> mockNeat.fromStrings(fm.getLines(path)).format(LOWER_CASE)::val;
    }
}
