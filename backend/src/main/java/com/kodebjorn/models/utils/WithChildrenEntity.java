package com.kodebjorn.models.utils;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.Transient;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Abstract class with methods for keeping track of children.
 *
 * Should be implemented by entities containing references to other entities,
 * to be able to keep track of children, when deleting or persisting.
 */
@Introspected
public abstract class WithChildrenEntity<ID> implements SuperEntity<ID> {

    @Transient
    private final Set<SuperEntity<?>> children = new LinkedHashSet<>();

    public abstract void addAllChildren();

    protected void addChild(SuperEntity<?> child) {
        children.add(child);
    }

    public Set<SuperEntity<?>> getChildren() {
        return Collections.unmodifiableSet(children);
    }
}
