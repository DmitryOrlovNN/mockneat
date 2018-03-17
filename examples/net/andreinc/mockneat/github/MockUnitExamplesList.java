package net.andreinc.mockneat.github;

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
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MockUnitExamplesList {
    public static void main(String[] args) {
        MockNeat m = MockNeat.old();

        List<String> countries = m.countries().names().list(100).val();
        System.out.println(countries);

        List<String> emails = m.emails().list(() -> new ArrayList<>(), 10).val();

        MockUnitInt lengthGenerator = m.ints().range(1, 100);
        List<String> creditCards = m.creditCards().list(() -> new LinkedList<>(), lengthGenerator).val();
    }
}
