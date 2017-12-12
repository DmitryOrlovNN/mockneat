package net.andreinc.mockneat.unit.user;

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
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.NameType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.NameType.*;
import static net.andreinc.mockneat.utils.ValidationUtils.betweenClosed;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static org.apache.commons.lang3.Validate.notNull;

public class Names extends MockUnitBase implements MockUnitString {

    public Names(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return full().supplier();
    }

    public MockUnitString first() { return type(FIRST_NAME); }

    // TODO document and test
    public MockUnitString firstAndMale() { return type(FIRST_NAME_MALE); }

    //TODO document and test
    public MockUnitString firstAndFemale() { return type(FIRST_NAME_FEMALE); }

    public MockUnitString last() { return type(LAST_NAME); }

    public MockUnitString full() {
        Supplier<String> supp = () -> first().val() + " " + last().val();
        return () -> supp;
    }

    public MockUnitString full(double middleInitialProbability) {
        betweenClosed(middleInitialProbability, 0.0, 100.0);
        Supplier<String> supp = () -> {
            boolean middleName = mockNeat.bools().probability(middleInitialProbability).val();
            String initial = (middleName) ? " " + mockNeat.chars().upperLetters().val() + "." : "";
            return first().val() + initial + " " + last().val();
        };
        return () -> supp;
    }

    public MockUnitString types(NameType... types) {
        notEmptyOrNullValues(types, "types");
        NameType nameType = mockNeat.from(types).val();
        return type(nameType);
    }

    public MockUnitString type(NameType type) {
        notNull(type, "type");
        DictType dictType = mockNeat.from(type.getDictionaries()).val();
        return () -> mockNeat.dicts().type(dictType)::val;
    }
}
