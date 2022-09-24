package com.kodebjorn.repositories;

import com.kodebjorn.models.utils.SuperEntity;
import com.kodebjorn.models.utils.WithChildrenEntity;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Repository used to persist or delete any entity which extends SuperEntity.
 */
@Repository
@Transactional
public abstract class SuperRepository {

    @PersistenceContext
    private EntityManager em;

    public <T extends SuperEntity<?>> T save(T entity) {
        if (entity instanceof WithChildrenEntity<?> wce) {
            deleteChildren(wce);
        }

        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        }

        return em.merge(entity);
    }

    public void delete(SuperEntity<?> entity) {
        if (entity instanceof WithChildrenEntity<?> wce) {
            deleteChildren(wce);
        }
        em.remove(entity);
    }

    private void deleteChildren(WithChildrenEntity<?> wce) {
        wce.addAllChildren();
        wce.getChildren().forEach(this::delete);
    }
}
