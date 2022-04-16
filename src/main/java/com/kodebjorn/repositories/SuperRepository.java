package com.kodebjorn.repositories;

import com.kodebjorn.models.utils.StateContainerEntity;
import com.kodebjorn.models.utils.SuperEntity;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Repository used to persist or delete any entity which extends SuperEntity/StateContainerEntity.
 */
@Repository
@Transactional
public abstract class SuperRepository {
    @PersistenceContext
    private EntityManager em;

    public <T extends SuperEntity<?>> T save(T entity) {
        if (entity instanceof StateContainerEntity<?> sce) {
            deleteChildren(sce);
        }

        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        }

        return em.merge(entity);
    }

    public void delete(SuperEntity<?> entity) {
        if (entity instanceof StateContainerEntity<?> sce) {
            deleteChildren(sce);
        }
        em.remove(entity);
    }

    private void deleteChildren(StateContainerEntity<?> sce) {
        sce.addAllChildren();
        sce.getChildren().forEach(this::delete);
    }
}
