package com.mockneat.mock.unit.networking;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.enums.DomainSuffixType;

import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public class Domains implements MockUnitString {

    private MockNeat mock;

    public Domains(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return type(POPULAR)::val;
    }

    public MockUnitString type(DomainSuffixType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "type");
        return () -> mock.dicts().type(type.getDictType())::val;
    }

    public MockUnitString types(DomainSuffixType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        range(0, types.length).forEach(i ->
                notNull(types[i], INPUT_PARAMETER_NOT_NULL, "types[" + i + "]"));
        DomainSuffixType type = mock.from(types).val();
        return type(type);
    }
}
