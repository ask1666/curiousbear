package com.kodebjorn.models.utils;

import io.micronaut.core.annotation.Introspected;

/**
 * Interface to be used for lazy fetching.
 */
@FunctionalInterface
@Introspected
public interface Fetcher<T extends SuperEntity<?>>{

    void doFetch(T target);

    default T fetch(T target) {
        doFetch(target);
        return target;
    }

}
