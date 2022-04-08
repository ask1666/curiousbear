package com.kodebjorn.repositories;

import com.kodebjorn.models.QuizEntry;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface QuizEntryRepository extends CrudRepository<QuizEntry, Integer> {
}
