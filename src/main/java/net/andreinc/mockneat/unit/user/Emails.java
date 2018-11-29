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
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.function.Supplier;
import static net.andreinc.mockneat.types.enums.DictType.DOMAIN_EMAIL;

public class Emails extends MockUnitBase implements MockUnitString {

    public Emails() {
    }

    public Emails(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return () -> mockNeat.users().val() + "@" + mockNeat.dicts().type(DOMAIN_EMAIL).val();
    }

    /**
     * <p>Generates a new {@code MockUnitString} that can be used to generate emails that have specific domains (eg.: "gmail.com").</p>
     *
     * <p>This is particularly useful when the requirement is to generate "company/enterprise" emails.</p>
     *
     * @param domains A var-arg String array containing the list of the domains to be used.
     * @return A new {@code MockUnitString}.
     */
    public MockUnit<String> domains(String... domains) {
        Supplier<String> supp = () -> {
            String user = mockNeat.users().val();
            String domain = mockNeat.from(domains).val();
            return user + "@" + domain;
        };
        return () -> supp;
    }

    /**
     * <p>Generates a new {@code MockUnitString} that can be used to generate emails that have a specific domain (eg.: "gmail.com").</p>
     *
     * <p>This is particularly useful when the requirement is to generate "company/enterprise" emails.</p>
     *
     * @param domain The domain to be used.
     * @return A new {@code MockUnitString}.
     */
    public MockUnit<String> domain(String domain) {
        return domains(domain);
    }
}
