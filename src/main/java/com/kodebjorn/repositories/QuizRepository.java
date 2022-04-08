package com.kodebjorn.repositories;

import com.kodebjorn.models.Quiz;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
