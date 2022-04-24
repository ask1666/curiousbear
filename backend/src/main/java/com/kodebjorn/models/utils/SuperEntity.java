package com.kodebjorn.models.utils;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;

/**
 * Interface to be implemented by Entities.
 *
 * Entities need to implement this interface to be able
 * to use SuperRepository with its type.
 */
@Introspected
public interface SuperEntity<ID> extends Serializable {

    ID getId();

}
