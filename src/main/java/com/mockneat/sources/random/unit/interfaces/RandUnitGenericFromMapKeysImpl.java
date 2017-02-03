package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class RandUnitGenericFromMapKeysImpl<T, R> implements RandUnitGeneric<T> {

    private Rand rand;
    private List<T> keyArray;

    public RandUnitGenericFromMapKeysImpl(Rand rand, Map<T,R> map) {
        this.rand = rand;
        this.keyArray = new ArrayList<>();
        keyArray.addAll(map.keySet());
    }

    @Override
    public T val() {
        return rand.objs().from(keyArray).val();
    }
}
